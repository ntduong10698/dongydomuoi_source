let listObject = [];

$(function () {
    $('#choose-file').on('change', function () {
        previewImage(this, '#img-preview');
    });
    pageObject(1, 15);
    addEvent();
});

async function loadList(page = 1, size = 15) {
    let data;
    await callGetAjax(PRODUCT_SERVICE, `v1/public/brands/company/${COMPANY_ID}`)
        .then(rs => {
            listObject = rs.data.content;
            return data = rs.data;
        }).catch()
    return data;
}

function checkInput() {
    let count = 1;

    if ($('#input-name').val().length === 0) {
        info("Vui lòng điền tên thương hiệu");
        count--;
    }

    return count === 1;
}

function pageObject(page = 1, size = 15) {
    let container = $('#page');
    container.html('');
    loadList(page, size).then(rs => {
        container.pagination({
            dataSource: 'https://api.flickr.com/services/feeds/photos_public.gne?tags=cat&tagmode=any&format=json&jsoncallback=?',
            locator: 'items',
            totalNumber: rs.totalPages,
            pageSize: 1,
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
                loadList(pagination.pageNumber).then(rs => {
                    fillTable(listObject)
                })
            }
        })
    }).catch();
}

function fillTable(list) {
    let content = '';
    list.map((object, index) => {
        content += ` <tr class="my-item" style="">
                                <td>${object.id}</td>
                                <td>${object.name ? object.name : ''}</td>                            
                                <td class="text-left">${object.description ? object.description : ''}</td>
                                <td>${object.image ? `<img style="max-width: 200px" src="${ viewSourceFile(object.image)}"/>` : ''}</td>                                                 
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
    $('#row-ajax').html(content);
    changeEvent();
    deleteEvent();
}

function addEvent() {
    $('#btn-add').off('click').on('click', function () {
        //clear content
        $('#input-name').val('')
        $('#input-preview').val('')
        $('#img-preview').attr('src', 'resources/image/camera2.png');
        $('#choose-file').val('')
        $('#submit').off('click').on('click', function () {
            if (checkInput()) {
                let object = {
                    name: $('#input-name').val(),
                    description: $('#input-preview').val(),
                    companyId: COMPANY_ID,
                    image: null
                };
                if ($('#choose-file').get(0).files.length > 0) {
                    let formData = new FormData();
                    formData.append("files", $('#choose-file').get(0).files[0]);
                    callUploadFile(formData).then(rs => {
                        object.image = rs.data[0].uri
                        postThuongHieu(object)
                    }).catch(ex => error("Tải hình ảnh thất bại"))
                } else postThuongHieu(object);
            }
        })
    });
}

function postThuongHieu(object) {
    callPostAjax(PRODUCT_SERVICE, 'v1/admin/brand', object)
        .then(saved => {
            success("Tạo mới thuơng hiệu thành công");
            $('#close-add').trigger('click');
            pageObject();
        }).catch(ex => error("Tạo mới thuơng hiệu thất bại"))
}

function changeEvent() {

    $('.drop-1').off('click').on('click', function () {
        let id = $(this).attr('index');
        //clear content
        $('#input-name').val('')
        $('#input-preview').val('')
        $('#choose-file').val('')
        callGetAjax(PRODUCT_SERVICE, `v1/public/brands/${id}`)
            .then(rs => {
                    let data = rs.data;
                    $('#input-name').val(data.name)
                    $('#input-preview').val(data.description)
                    try {
                        if (data.image)
                            $('#img-preview').attr('src', viewSourceFile(data.image));
                    } catch (e) {
                        console.log("error load image")
                    }
                    $('#submit').off('click').on('click', function () {

                        data.name = $('#input-name').val()
                        data.description = $('#input-preview').val()

                        if (checkInput()) {
                            if ($('#choose-file').get(0).files.length > 0) {
                                let formData = new FormData();
                                formData.append("files", $('#choose-file').get(0).files[0]);
                                callUploadFile(formData).then(rs => {
                                    data.image = rs.data[0].uri
                                    putThuongHieu(data)
                                }).catch(ex => error("Tải hình ảnh thất bại"))
                            } else putThuongHieu(data)
                        }
                    })
                }
            ).catch()
    });
}

function putThuongHieu(object) {
    callPutAjax(PRODUCT_SERVICE, 'v1/admin/brand', object)
        .then(saved => {
            success("Chỉnh sửa thuơng hiệu thành công");
            $('#close-add').trigger('click');
            pageObject();
        }).catch(ex => error("Chỉnh sửa thuơng hiệu thất bại"))
}

function deleteEvent() {
    $('.drop-2').off('click').on('click', function () {
        let id = $(this).attr("delete-id");
        $('#submit-delete').off('click').on('click', function () {
            callDeleteAjax(PRODUCT_SERVICE, 'v1/admin/brand/' + id)
                .then(rs => {
                    pageObject();
                    $('#close-delete').trigger('click');
                    success("Xóa thuơng hiệu thành công");
                }).catch(ex => error("Thương hiệu vẫn còn sản phẩm"));
        })
    });
}




