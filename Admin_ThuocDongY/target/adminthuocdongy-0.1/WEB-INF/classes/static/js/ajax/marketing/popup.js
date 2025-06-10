let popups = []

$(function () {
    $('#choose-file').on('change', function () {
        previewImage(this, '#img-preview');
    });
    loadList();
    addEvent();
});

function loadList() {
    callGetAjax(MARKETING_SERVICE, `v1/admin/popups/company/${COMPANY_ID}`)
        .then(rs => {
            popups = rs.data;
            pageObject(rs.data)
        }).catch()
}

function checkInput(checkFile = true) {
    let count = 3;

    if ($('#input-name').val().length === 0) {
        info("Vui lòng điền tên popup");
        count--;
    }

    if ($('#input-link').val().length === 0) {
        info("Vui lòng điền đường dẫn");
        count--;
    }

    if (checkFile && $('#choose-file').get(0).files.length === 0) {
        info("Vui lòng tải lên ảnh");
        count--;
    }

    return count === 3;
}

function pageObject(data, page = 1, size = 15) {
    let container = $('#page');
    container.html('');
    container.pagination({
        dataSource: data,
        locator: 'items',
        pageSize: size,
        showPageNumbers: true,
        showPrevious: true,
        showNext: true,
        showGoInput: true,
        showGoButton: true,
        showNavigator: true,
        formatNavigator: `<span style="color: #000000"> đi đến trang`,
        showFirstOnEllipsisShow: true,
        showLastOnEllipsisShow: true,
        callback: function (response, pagination) {
            fillTable(response);
        }
    })

}

function fillTable(list) {
    let content = '';
    list.map((object, index) => {
        content += ` <tr class="my-item" style="">
                                <td>${index + 1}</td>
                                <td>${object.name}</td>                                                        
                                <td style="max-width: 350px"><img style="max-width: 300px" src="${viewSourceFile(object.url)}"/></td> 
                                <td>${object.link}</td>
                                <td>${object.active ? '<i class="fa fa-check"></i>' : ''}</td>                                                
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
                                                        data-target="#popup-modal">
                                                        <i class="fas fa-pen"></i>&nbsp;&nbsp;Chỉnh sửa
                                                </button>
                                            </li>
                                            
                                             <li>
                                                <button class="drop-3 btn btn-action font-weight-normal col-lg-12"
                                                        style="text-align: left" index="${object.id}">
                                                        <i class="fas fa-check"></i>&nbsp;&nbsp;Kích hoạt
                                                </button>
                                            </li>
                                            ${object.active ?
            `<li>
                                                <button class="drop-4 btn btn-action font-weight-normal col-lg-12"
                                                        style="text-align: left" index="${object.id}">
                                                        <i class="fas fa-trash"></i>&nbsp;&nbsp;Tắt popup
                                                </button>
                                            </li>` : ''}
                                                                                                                                
                                            <li>
                                                <button class="drop-2 btn btn-action font-weight-normal col-lg-12"
                                                        style="text-align: left" delete-id="${object.id}" data-toggle="modal"
                                                        data-target="#delete-modal">
                                                    <i class="fas fa-trash-alt"></i>&nbsp;&nbsp;Xóa
                                                </button>
                                            </li>
                                        </ul>
                                    </div>
                                </td>                             
                            </tr>`;
    });
    $('#row-ajax').html(content);
    changeEvent();
    activeEvent();
    deleteEvent();
    removePopupEvent();
}

function addEvent() {
    //clear content

    $('#btn-add').off('click').on('click', function () {
        $('#input-name').val('')
        $('#input-link').val('')
        $('#choose-file').val('')
        $('#img-preview').attr('src', 'resources/image/camera2.png');
        $('#submit').off('click').on('click', function () {
            if (checkInput(true)) {
                let object = {
                    name: $('#input-name').val(),
                    link: $('#input-link').val(),
                    companyId: COMPANY_ID,
                    deleted: false,
                    active: false,
                    url: null
                };
                if ($('#choose-file').get(0).files.length > 0) {
                    let formData = new FormData();
                    formData.append("files", $('#choose-file').get(0).files[0]);
                    callUploadFile(formData).then(rs => {
                        object.url = rs.data[0].uri
                        postPopup(object)
                    }).catch(ex => {
                        console.log(ex);
                        error("Tải hình ảnh thất bại")
                    })
                } else postPopup(object);
            }
        })
    });
}

function postPopup(object) {
    callPostAjax(MARKETING_SERVICE, 'v1/admin/popup', object)
        .then(saved => {
            success("Tạo mới popup thành công");
            $('#close-add').trigger('click');
            loadList();
        }).catch(ex => error("Tạo mới popup thất bại"))
}

function changeEvent() {
    $('.drop-1').off('click').on('click', function () {
        let id = $(this).attr('index');
        //clear content

        let data = popups.filter(p => p.id == id)[0];
        $('#input-name').val(data.name)
        $('#input-link').val(data.link)
        $('#img-preview').attr('src', viewSourceFile(data.url));

        $('#submit').off('click').on('click', function () {
            data.name = $('#input-name').val()
            data.link = $('#input-link').val()
            if (checkInput(false)) {
                if ($('#choose-file').get(0).files.length > 0) {
                    let formData = new FormData();
                    formData.append("files", $('#choose-file').get(0).files[0]);
                    callUploadFile(formData).then(rs => {
                        data.url = rs.data[0].uri
                        putPopup(data)
                    }).catch(ex => error("Tải hình ảnh thất bại"))
                } else putPopup(data)
            }
        });

    })
}

function removePopupEvent() {
    $('.drop-4').off('click').on('click', function () {
        let id = $(this).attr('index');
        //clear content

        let data = popups.filter(p => p.id == id)[0];
        data.active = false;
        putPopup(data)
    })
}

function putPopup(object) {
    callPutAjax(MARKETING_SERVICE, 'v1/admin/popup', object)
        .then(saved => {
            success("Chỉnh sửa popup thành công");
            $('#close-add').trigger('click');
            loadList();
        }).catch()
}

function activeEvent() {
    $('.drop-3').off('click').on('click', function () {
        let id = $(this).attr("index");
        callPutAjax(MARKETING_SERVICE, `v1/admin/popup/${id}/show`)
            .then(rs => {
                loadList();
                $('#close-delete').trigger('click');
                success("Kích hoạt thành công");
            }).catch(ex => error("Kích hoạt popup thất bại"));
    });
}

function deleteEvent() {
    $('.drop-2').off('click').on('click', function () {
        let id = $(this).attr("delete-id");
        $('#submit-delete').off('click').on('click', function () {
            callDeleteAjax(MARKETING_SERVICE, 'v1/admin/popup/' + id)
                .then(rs => {
                    loadList();
                    $('#close-delete').trigger('click');
                    success("Xóa popup thành công");
                }).catch(ex => error("Xóa popup thất bại"));
        })
    });
}
