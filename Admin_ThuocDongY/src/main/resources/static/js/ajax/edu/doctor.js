$(function () {
    $('#choose-file').on('change', function () {
        previewImage(this, '#img-preview');
    });

    loadList();
    addEvent();
});

function loadList() {
    callGetAjax(EDU_SERVICE, `v1/public/lecturers/company/${COMPANY_ID}`)
        .then(rs => {
            pageObject(rs.data)
        }).catch()
}

function checkInput(checkFile = true) {
    let count = 3;

    if ($('#input-name').val().length === 0) {
        info("Vui lòng điền tên bác sĩ");
        count--;
    }

    if ($('#input-position').val().length === 0) {
        info("Vui lòng điền vị trí");
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
    try {
        list.map((object, index) => {
            content += ` <tr class="my-item" style="">
                                <td>${index + 1}</td>
                                <td>${object.name}</td>   
                                <td>${object.position}</td>                                                     
                                <td style="max-height: 200px">${object.image ? `<img style="max-height: 150px" src="${viewSourceFile(object.image)}"/>` : ''}</td>                                                 
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
                                                        data-target="#myModal">
                                                        <i class="fas fa-pen"></i>&nbsp;&nbsp;Chỉnh sửa
                                                </button>
                                            </li>
                                                                                                                                
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
    } catch (e) {
        console.log("load image error")
    }
    $('#row-ajax').html(content);
    changeEvent();
    deleteEvent();
}

function addEvent() {
    //clear content
    $('#btn-add').off('click').on('click', function () {
        //clear content
        $('#input-name').val('')
        $('#input-position').val('')
        $('#input-preview').val('');
        $('#img-preview').attr('src', 'resources/image/camera2.png');
        $('#choose-file').val('')
        $('#submit').off('click').on('click', function () {
            if (checkInput(true)) {
                let object = {
                    name: $('#input-name').val(),
                    position: $('#input-position').val(),
                    introduction: $('#input-preview').val(),
                    companyId: COMPANY_ID,
                    image: null,
                    image2: null,
                    image3: null
                };
                let formData = new FormData();
                formData.append("files", $('#choose-file').get(0).files[0]);
                callUploadFile(formData).then(rs1 => {
                    object.image = rs1.data[0].uri;
                    postGiangVien(object);
                }).catch(ex => {
                    console.log(ex);
                    error("Tải hình ảnh thất bại")
                })

            }
        })
    });
}

function postGiangVien(object) {
    callPostAjax(EDU_SERVICE, 'v1/admin/lecturer', object)
        .then(saved => {
            success("Tạo mới thành công");
            $('#close-add').trigger('click');
            loadList();
        }).catch(ex => error("Tạo mới thất bại"))
}

function changeEvent() {
    $('.drop-1').off('click').on('click', function () {
        let id = $(this).attr('index');
        //clear content
        $('#choose-file').val('')
        callGetAjax(EDU_SERVICE, `v1/public/lecturers/${id}`)
            .then(rs => {
                    let data = rs.data;
                    $('#input-name').val(data.name)
                    $('#input-position').val(data.position)
                    $('#input-preview').val(data.introduction)
                    $('#img-preview').attr('src', viewSourceFile(data.image));

                    $('#submit').off('click').on('click', function () {
                        data.name = $('#input-name').val();
                        data.position = $('#input-position').val();
                        data.introduction = $('#input-preview').val()
                        if (checkInput(false)) {
                            if ($('#choose-file').get(0).files.length > 0) {


                                let formData = new FormData();
                                formData.append("files", check1 ? $('#choose-file').get(0).files[0] : getRandomFile());

                                callUploadFile(formData).then(rs1 => {
                                    data.image = rs1.data[0].uri
                                    putGiangVien(data);
                                }).catch(ex => {
                                    console.log(ex);
                                    error("Tải hình ảnh thất bại")
                                })
                            } else putGiangVien(data)
                        }
                    })
                }
            ).catch()
    });
}

function putGiangVien(object) {
    callPutAjax(EDU_SERVICE, 'v1/admin/lecturer', object)
        .then(saved => {
            success("Chỉnh sửa bác sĩ thành công");
            $('#close-add').trigger('click');
            loadList();
        }).catch()
}

function deleteEvent() {
    $('.drop-2').off('click').on('click', function () {
        let id = $(this).attr("delete-id");
        $('#submit-delete').off('click').on('click', function () {
            callDeleteAjax(EDU_SERVICE, 'v1/admin/lecturer/' + id)
                .then(rs => {
                    loadList();
                    $('#close-delete').trigger('click');
                    success("Xóa thành công");
                }).catch(ex => error("Xoá thất bại"));
        })
    });
}
