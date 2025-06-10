let selectCategoryTypeValue;
let idCategory;
let listProductTypeData;
let upload;
$(function () {

    fillTable();

    clickAddCategory();
});

// lấy data

function getNewsCategory() {
    return callGetAjax(NEWS_SERVICE, `v1/public/categories/company/${COMPANY_ID}`);
}

function getCategoryById(idCategory) {
    return callGetAjax(NEWS_SERVICE, `v1/public/categories/${idCategory}`);
}

//mapping data to jsp

function fillTable() {
    getNewsCategory().then(rs => {
        let templateBodyTable = ``;
        if (rs.success) {
            rs = rs.data;
            templateBodyTable += rs.map((object, index) => {
                return `<tr class="my-item" id-category="${object.id}">
                                <td>${index + 1}</td>
                                <td>${object.name ? object.name : ''}</td>   
                                <td>${object.code ? '<i class="fa fa-check"></i>' : ''}</td>                                                        
                                <td style="max-width: 6%">
                                    <div class="dropdown">
                                        <button id="menu-drop-1" type="button" class="btn btn-action dropdown-toggle" style="height: 30px"
                                                data-toggle="dropdown" type-btn="btn-dropdown" index="${object.id}">
                                            <i class="fas fa-cog"></i>
                                        </button>
                                        <ul class="dropdown-menu" aria-labelledby="menu-drop-1" style="">
                                            <li>
                                                <button class="drop-1 btn btn-action font-weight-normal col-lg-12"
                                                        type-btn="btn-update-cate"
                                                        style="text-align: left" index="${object.id}" data-toggle="modal"
                                                        data-target="#myModal">
                                                        <i class="fas fa-pen"></i>&nbsp;&nbsp;Chỉnh sửa
                                                </button>
                                            </li>
                                            ${object.code ? '' :
                    `<li>
                                                <button class="drop-2 btn btn-action font-weight-normal col-lg-12" type-btn="btn-delete-cate"
                                                        style="text-align: left" delete-id="${object.id}" data-toggle="modal"
                                                        data-target="#delete-modal">
                                                    <i class="fas fa-trash-alt"></i>&nbsp;&nbsp;Xóa
                                                </button>
                                            </li>`}
                                        </ul>
                                    </div>
                                </td>                             
                            </tr>`
            }).join('');

        }

        $('#row-ajax').html(templateBodyTable);
        clickDelete();
        clickUpdateData();

    }).catch(err => {
        error(`Tải thông tin danh mục thất bại`);
    });
}

function clickAddCategory() {
    $('#btn-add').click(() => {
        $('#input-name').val('');
        $('#submit').off('click').click(() => {
            let nameCate = $('#input-name').val();
            let uri = `v1/admin/category`;
            let payload = {
                "companyId": COMPANY_ID,
                "name": nameCate
            };
            callPostAjax(NEWS_SERVICE, uri, payload).then(() => {
                info(`Thêm thành công`);
                $('#close-add').trigger('click');
                fillTable();
            }).catch(err => {
                error(`Thêm thất bại`);
            });
        });
    })

}

//handling modal

function clickUpdateData() {
    let nameCateInput = $('#input-name');
    $(`button[type-btn='btn-update-cate']`).click(function () {
        let idCategory = $(this).attr('index');
        getCategoryById(idCategory).then(rs => {
            let payload = rs.data;
            nameCateInput.val(payload.name);

            $('#submit').off('click').click(() => {
                let nameCateData = $('#input-name').val();
                let uri = `v1/admin/category`;

                payload.name = nameCateData;
                callPutAjax(NEWS_SERVICE, uri, payload).then(() => {
                    success(`Chỉnh sửa Thành công`);
                    $('#close-add').trigger('click');
                    fillTable();
                }).catch(err => {
                    error(`Chỉnh sửa thất bại`);
                });

            });
        }).catch(err => error('Hiển thị danh mục theo ID thất bại'));

    });
}

function clickDelete() {
    $('button[type-btn="btn-dropdown"]').off('click').click(function () {
        let id = parseInt($(this).attr('index'));
        $("#submit-delete").off('click').click(function () {
            callDeleteAjax(NEWS_SERVICE, `v1/admin/category/${id}`).then(() => {
                success(`Xoá thành công`);
                $('#close-delete').trigger('click');
                fillTable();
            }).catch(err => {
                error('Xoá thất bại');
            });
        })
    })
}






