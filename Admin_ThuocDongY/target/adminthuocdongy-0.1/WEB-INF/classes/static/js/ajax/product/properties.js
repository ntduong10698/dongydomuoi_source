let listObject = [];
let options = [];

$(function () {

    getListProductTypeData()
        .then(rs => {
            getProductProperties();
        })

    clickAdd();
});

// lấy data

async function getListProductTypeData() {
    let listProductType = [];
    await callGetAjax(PRODUCT_SERVICE, `v1/public/product-types/company/${COMPANY_ID}`).then(rs => {
        listProductType = rs.data;
        let options = listProductType.map(type => `<option value="${type.id}">${type.name}</option>`).join('');
        $('#select-product-type').html(options);
        $('#input-product-type').html(options);
        $('#select-product-type').on('change', function () {
            getProductProperties();
        })
    }).catch(err => {
        error('Hiển thị danh sách sản phẩm theo loại thất bại');
    });

    return listProductType;
}

function getProductProperties() {
    callGetAjax(PRODUCT_SERVICE, 'v1/public/properties/product-type/' + $('#select-product-type').val())
        .then(rs => {
            listObject = rs.data;
            fillTable();
        }).catch(ex => error("Tải thông tin thuộc tính thất bại"))
}

function fillTable() {
    let templateBodyTable = ``;
    templateBodyTable += listObject.map((object, index) => {
        let loai = '';
        switch (object.type) {
            case 2:
                loai = "Chọn một";
                break;
            case 3:
                loai = "Chọn nhiều";
                break;
            case 4:
                loai = "Điền text";
                break;
            case 5:
                loai = "Điền số";
                break;
        }
        return `<tr>    
                                <td>${index + 1}</td>
                                <td class="text-left">${object.name}</td>    
                                <td>${object.required ? '<i class="fa fa-check"></i>' : ''}</td>
                                <td>${loai}</td>                                                    
                                <td style="max-width: 6%">
                                    <div class="dropdown">
                                        <button id="menu-drop-1" type="button" class="btn btn-action dropdown-toggle" style="height: 30px"
                                                data-toggle="dropdown">
                                            <i class="fas fa-cog"></i>
                                        </button>
                                        <ul class="dropdown-menu" aria-labelledby="menu-drop-1" style="">
                                            <li>
                                                <button class="drop-1 btn btn-action font-weight-normal col-lg-12"
                                                        style="text-align: left" index="${object.id}" data-toggle="modal"
                                                        data-target="#detail-modal">
                                                        <i class="fas fa-pen"></i>&nbsp;&nbsp;Chỉnh sửa
                                                </button>
                                            </li>
                                            ${object.code ? '' : ` <li>
                                                <button class="drop-2 btn btn-action font-weight-normal col-lg-12" 
                                                        style="text-align: left" index="${object.id}" data-toggle="modal"
                                                        data-target="#delete-modal">
                                                    <i class="fas fa-trash-alt"></i>&nbsp;&nbsp;Xóa
                                                </button>
                                            </li>`}                                                                                    
                                           
                                        </ul>
                                    </div>
                                </td>                             
                            </tr>`
    }).join('');


    $('#row-ajax').html(templateBodyTable);
    clickDelete();
    clickUpdateData();
}

function clickAdd() {
    //prepare upload
    $('#input-type').on('change', function () {
        switch (parseInt($(this).val())) {
            case 2:
            case 3:
                $('#optional-div').html($('#options-ex').html());
                $('#optional-div').show();
                $('#div-default').hide();
                addOptionEvent();
                break;
            case 4:
                $('#div-default').show();
                $('#optional-div').hide();
                $('#input-default').prop('type', 'text');
                break;
            case 5:
                $('#div-default').show();
                $('#optional-div').hide();
                $('#input-default').prop('type', 'number');
                break;

        }
    });

    $('#btn-add').click(() => {
        $('#input-name').val('');
        $('#input-require').val('false');
        $('#input-default').val('');
        $('#input-type').val('4');
        $('#input-type').trigger('change');
        $('#input-type').prop('disabled', false);
        $('#input-product-type').val($('#select-product-type').val());
        options = [];

        $('#submit').off('click').click(() => {
            reNewOptions();
            let payload = {
                name: $('#input-name').val(),
                type: $('#input-type').val(),
                required: $('#input-require').val(),
                options: options.map(o => o.value).join('|'),
                defaultValue: $('#input-default').val()
            };

            if (payload.name.length === 0) {
                info("Vui lòng điền tên thuộc tính");
                return;
            }

            callPostAjax(PRODUCT_SERVICE, `v1/admin/property?product-type=${$('#input-product-type').val()}`, payload).then(() => {
                success(`Thêm thành công`);
                $('#close-add').trigger('click');
                getProductProperties();
            }).catch(err => {
                error(`Thêm thất bại`);
            });
        });
    })

}

//add option
function fillOptions() {
    let content = options.map((option, index) => `<tr>
                                        <td>${index + 1}</td>
                                        <td><input class="form-control option-value" value="${option.value}"></td>
                                        <td>
                                        <button class="btn btn-danger btn-delete-option" index="${option.id}">
                                            <i class="fa fa-trash"></i>
                                        </button>
                                        </td>
                                    </div>
                                </td> 
                                    </tr>`).join('');
    $('#row-options').html(content);

    $('.btn-delete-option').off('click').on('click', function () {
        let id = $(this).attr("index");
        options.forEach((option, index) => {
            option.id = index + 1;
            option.value = $($(`.option-value`)[index]).val()
        });
        options = options.filter(option => option.id != id)
        fillOptions();
    })
}

function reNewOptions() {
    options.forEach((option, index) => {
        option.id = index + 1;
        option.value = $($(`.option-value`)[index]).val()
    });
}

function addOptionEvent() {
    $('#add-option').off('click').on('click', function () {

        reNewOptions();

        let option = {
            id: options.length + 1,
            value: ""
        };

        options.push(option);
        //fill table
        fillOptions();
    })

}

//handling modal

function clickUpdateData() {
    $(`.drop-1`).off('click').on('click', function () {
        let id = $(this).attr('index');
        callGetAjax(PRODUCT_SERVICE, 'v1/public/properties/' + id).then(rs => {
            let payload = rs.data;
            $('#input-name').val(payload.name);
            $('#input-default').val(payload.defaultValue ? payload.defaultValue : '');
            $('#input-require').val(payload.required ? 'true' : 'false');
            $('#input-type').val(payload.type);
            $('#input-type').trigger('change');
            $('#input-type').prop('disabled', true);
            $('#input-product-type').val(payload.productType.id);
            $('#input-product-type').prop('disabled', true);
            if (payload.options && payload.options.length > 0) {
                options = payload.options.split("|").map((path, index) => {
                    return {
                        id: index + 1,
                        value: path
                    }
                });
                fillOptions();
            } else options = [];

            $('#submit').off('click').click(() => {
                reNewOptions();
                payload.name = $('#input-name').val();
                payload.required = $('#input-require').val();
                payload.defaultValue = $('#input-default').val();
                payload.options = options.map(o => o.value).join('|');

                callPutAjax(PRODUCT_SERVICE, 'v1/admin/property', payload).then(() => {
                    success(`Chỉnh sửa Thành công`);
                    $('#close-add').trigger('click');
                    getProductProperties();
                }).catch(err => {
                    error(`Chỉnh sửa thất bại`);
                });
            });
        }).catch(err => {
            console.log(err)
            error('Có lỗi xảy ra')
        });

    });
}

function clickDelete() {
    $('.drop-2').off('click').click(function () {
        let id = parseInt($(this).attr('index'));

        $("#submit-delete").off('click').click(function () {
            callDeleteAjax(PRODUCT_SERVICE, `v1/admin/property/${id}`).then(() => {
                success(`Xoá thành công`);
                $('#close-delete').trigger('click');
                getProductProperties();
            }).catch(err => {
                error('Xoá thất bại');
            });
        });
    });
}






