let listObject = [];

$(function () {

    $('#choose-file').on('change', function () {
        $('#file-preview').attr('src', 'resources/image/upload-file.png')
    })

    loadList()
    addEvent();
    changeEvent();
    deleteEvent();
});

function loadList() {
    callGetAjax(PRODUCT_SERVICE, `v1/public/documents/company/${COMPANY_ID}`)
        .then(rs => {
            listObject = rs.data;
            fillTable(listObject);
        }).catch()
}

function checkInput(checkFile = true) {
    let count = 2;

    if ($('#input-name').val().length === 0) {
        info("Vui lòng điền tên");
        count--;
    }

    if (checkFile && $('#choose-file').get(0).files.length === 0) {
        info("Vui lòng chọn tài liệu");
        count--;
    }

    return count === 2;
}

function fillTable(list) {
    let content = '';
    list.map((object, index) => {
        content += ` <tr class="my-item" style="">
                                <td>${index + 1}</td>
                                <td class="text-left"><a href="${viewSourceFile(object.file)}">${object.name}</a></td>         
                                <td class="text-left" style="max-width: 200px">${object.description}</td>                                               
                                                  
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
                                                        data-target="#upload-modal">
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
        $('#input-description').val('');
        $('#file-preview').attr('src', 'resources/image/upload-file.png');
        $('#choose-file').val('');
        $('#choose-image').val('');
        $('#link-file').hide();

        $('#submit').off('click').click(() => {
            if (checkInput()) {
                let payload = {
                    name: $('#input-name').val(),
                    description: $('#input-description').val(),
                    image: null,
                    file: null,
                    companyId: COMPANY_ID,
                    deleted: false
                };
                $('#file-preview').attr('src', 'resources/image/uploading.gif')
                Promise.all(uploadFiles())
                    .then(rs => {
                        $('#file-preview').attr('src', 'resources/image/file.png')
                        try {
                            let fileUrl = rs.filter(data => data.label === "file")[0];
                            if (fileUrl) payload.file = fileUrl.file;
                        } catch (e) {
                            console.log(e)
                        }
                        callPostAjax(PRODUCT_SERVICE, 'v1/admin/document', payload).then(() => {
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

            let data = listObject.filter(c => c.id == id)[0];
            $('#input-name').val(data.name);
            $('#input-description').val(data.description);
            $('#file-preview').attr('src', 'resources/image/file.png');
            $('#link-file').show();
            $('#link-file').attr('href', viewSourceFile(data.file));
            $('#link-file').html(data.file.split("/").pop())

            $('#submit').off('click').on('click', function () {
                    if (checkInput(false)) {
                        data.name = $('#input-name').val();
                        data.description = $('#input-description').val();
                        if ($('#choose-image').get(0).files.length > 0 || $('#choose-file').get(0).files.length > 0) {
                            Promise.all(uploadFiles())
                                .then(rs => {
                                    try {
                                        let fileUrl = rs.filter(data => data.label === "file")[0];
                                        if (fileUrl) data.file = fileUrl.file;
                                    } catch (e) {
                                    }
                                    callPutAjax(PRODUCT_SERVICE, 'v1/admin/document', data).then(rs => {
                                        success("Chỉnh sửa thành công");
                                        $('#customer-modal').modal('hide');
                                        loadList();
                                    }).catch(e => error("Chỉnh sửa lỗi"))
                                }).catch(e => error("Tải lên file lỗi"));
                        } else {
                            callPutAjax(PRODUCT_SERVICE, 'v1/admin/document', data).then(rs => {
                                success("Chỉnh sửa thành công");
                                $('#customer-modal').modal('hide');
                                loadList();
                            }).catch(e => error("Chỉnh sửa lỗi"))
                        }

                    }
                }
            )
        }
    );
}

function deleteEvent() {
    $('tbody').on('click', '.drop-2', function () {
        let id = $(this).attr('index');
        $('#submit-delete').off('click').on('click', function () {
            callDeleteAjax(PRODUCT_SERVICE, 'v1/admin/document/' + id)
                .then(rs => {
                    success("Xoá thành công");
                    loadList();
                    $('#delete-modal').modal('hide');
                }).catch(e => error("Xoá thất bại"));
        })
    })
}

function uploadFiles() {

    let promises = [];

    if ($('#choose-file').get(0).files.length > 0) {
        let formData = new FormData();
        formData.append("files", $('#choose-file').get(0).files[0]);
        promises.push(callUploadFile(formData, true, "file"));
    }

    return promises;
}






