let listObject = [];

$(function () {

    $('#choose-image').on('change', function () {
        previewImage(this, '#img-preview');
    });

    loadList()
    addEvent();
    changeEvent();
    deleteEvent();
});

function loadList() {
    callGetAjax(IS_SERVICE, `v1/public/customers/company/${COMPANY_ID}`)
        .then(rs => {
            listObject = rs.data;
            fillTable(listObject);
        }).catch()
}

function checkInput(checkFile = true) {
    let count = 3;

    if ($('#input-name').val().length === 0) {
        info("Vui lòng điền tên khách hàng");
        count--;
    }

    if (checkFile && $('#choose-image').get(0).files.length === 0) {
        info("Vui lòng chọn ảnh");
        count--;
    }

    if ($('#input-feedback').val().length === 0) {
        info("Vui lòng điền phản hồi");
        count--;
    }

    return count === 3;
}

function fillTable(list) {
    let content = '';
    list.map((object, index) => {
        content += ` <tr class="my-item" style="">
                                <td>${index + 1}</td>
                                <td class="text-left">${object.name}</td>         
                                <td class="text-left" style="max-width: 400px">${object.feedback}</td>                          
                                <td><img style="max-width: 100px" src="${viewSourceFile(object.image)}"></td>                            
                                                  
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
                                                        data-target="#customer-modal">
                                                        <i class="fas fa-pen"></i>&nbsp;&nbsp;Chỉnh sửa
                                                </button>
                                            </li>
                                            
                                             <li>
                                                <button class="drop-2 btn btn-action font-weight-normal col-lg-12"
                                                        style="text-align: left" index="${object.id}" data-toggle="modal"
                                                        data-target="#delete-modal">
                                                        <i class="fas fa-trash"></i>&nbsp;&nbsp;Xoá
                                                </button>
                                            </li>
                                        </ul>
                                    </div>
                                </td>                             
                            </tr>`;
    });
    $('#row-ajax').html(content);
}

function addEvent() {
    $('#btn-add').click(() => {
        $('#input-name').val('');
        $('#input-feedback').val('');
        $('#img-preview').attr('src', 'resources/image/camera2.png');
        $('#choose-file').val('');

        $('#submit').off('click').click(() => {
            if (checkInput()) {
                let payload = {
                    name: $('#input-name').val(),
                    feedback: $('#input-feedback').val(),
                    image: null,
                    deleted: false
                };
                let formData = new FormData();
                formData.append("files", $('#choose-image').get(0).files[0]);
                callUploadFile(formData).then(rs => {
                    payload.image = rs.data[0].uri;

                    callPostAjax(IS_SERVICE, 'v1/admin/customer', payload).then(() => {
                        info(`Thêm thành công`);
                        $('#close-add').trigger('click');
                        loadList();
                    }).catch(err => {
                        error(`Thêm thất bại`);
                    });
                }).catch(e => error("Tải lên ảnh lỗi"))
            }
        });
    })
}

function changeEvent() {
    $('tbody').on('click', '.drop-1', function () {
            let id = $(this).attr('index');
            //clear content

            let customer = listObject.filter(c => c.id == id)[0];
            $('#input-name').val(customer.name);
            $('#input-feedback').val(customer.feedback);
            if (customer.image) {
                $('#img-preview').attr('src', viewSourceFile(customer.image));
            }

            $('#submit').off('click').on('click', function () {
                if (checkInput(false)) {
                    customer.name = $('#input-name').val();
                    customer.feedback = $('#input-feedback').val();
                    if ($('#choose-image').get(0).files.length > 0) {
                        let formData = new FormData();
                        formData.append("files", $('#choose-file').get(0).files[0]);
                        callUploadFile(formData).then(rs => {
                            customer.image = rs.data[0].uri;
                            callPutAjax(IS_SERVICE, 'v1/admin/customer', customer).then(rs => {
                                success("Chỉnh sửa thành công");
                                $('#customer-modal').modal('hide');
                                loadList();
                            }).catch(e => error("Chỉnh sửa lỗi"))
                        });
                    } else {
                        callPutAjax(USER_SERVICE, 'v1/admin/customer', customer).then(rs => {
                            success("Chỉnh sửa thành công");
                            $('#customer-modal').modal('hide');
                            loadList();
                        }).catch(e => error("Chỉnh sửa lỗi"))
                    }

                }
            })
        }
    );
}

function deleteEvent(){
    $('tbody').on('click', '.drop-1', function () {
        let id = $(this).attr('index');

        callDeleteAjax(IS_SERVICE, 'v1/admin/customer/'+id)
            .then(rs => {
                success("Xoá thành công");
                loadList();
                $('#delete-modal').modal('hide');
            }).catch(e => error("Xoá thất bại"));
    })
}






