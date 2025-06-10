let productTypes = [];

$(function () {
    $('#choose-image').on('change', function () {
        previewImage(this, '#img-preview');
    });

    $('#choose-icon').on('change', function () {
        previewImage(this, '#icon-preview');
    });

    checkAliasEvent();

    fillTable();

    clickAdd();
});

// lấy data


function getProductTypes() {
    return callGetAjax(PRODUCT_SERVICE, `v1/public/product-types/company/${COMPANY_ID}`);
}

function getProductTypeById(id) {
    return callGetAjax(PRODUCT_SERVICE, `v1/public/product-types/${id}`);
}

//mapping data to jsp

function fillTable() {
    getProductTypes().then(rs => {

        let templateBodyTable = ``;
        rs = rs.data;
        productTypes = rs;
        templateBodyTable += rs.map((object, index) => {
            return `<tr>
                                <td>${index + 1}</td>
                                <td class="text-left">${object.name ? object.name : ''}</td>     
                                 <td>${object.enableView ? '<i class="fa fa-check"></i>' : ''}</td>                                                       
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

    }).catch(err => {
        error(`Tải thông tin danh mục thất bại`);
    });
}

function clickAdd() {
    $('#btn-add').click(() => {
        $('#input-name').val('');
        $('#input-stt').val(1);
        $('#file-name').html('');
        $('#input-alias').val('');
        $('#icon-preview').attr('src', 'resources/image/camera2.png');
        $('#img-preview').attr('src', 'resources/image/camera2.png');
        // $('#file-preview').attr('src', 'resources/image/upload-file.png');
        $('#submit').off('click').click(() => {
            let name = $('#input-name').val();
            let stt = parseInt($('#input-stt').val());
            let description = $('#input-preview').val();
            let uri = `v1/admin/product-type`;
            let payload = {
                companyId: COMPANY_ID,
                name: name,
                stt: stt,
                enableView: $('#enable-view').val(),
                icon: null,
                image: null,
                alias: $('#input-alias').val(),
                attachment: null,
                description: description,
            };
            Promise.all(uploadFiles())
                .then(rs => {
                    try {
                        let imageUrl = rs.filter(data => data.label === "image")[0];
                        let iconUrl = rs.filter(data => data.label === "icon")[0];

                        if (imageUrl) payload.image = imageUrl.file;
                        if (iconUrl) payload.icon = iconUrl.file;
                    } catch (e) {
                    }
                    checkAlias($('#input-alias').val())
                        .then(rs => {
                            if (!rs) {
                                post(uri, payload, function (id) {
                                    callPostAjax(IS_SERVICE, `v1/admin/url-alias?danh-muc-alias-id=${CATEGORY_ALIAS}`, {
                                        alias: $('#input-alias').val(),
                                        url: `${CATEGORY_ALIAS_PATH}?root=1&id=` + id
                                    }).then().catch(ex => {
                                        error("Tạo alias lỗi!");
                                    });
                                });
                            } else error("Đường dẫn đã được sử dụng")
                        });
                }).catch(e => error("Tải lên file lỗi"))
        });
    })

}

function post(uri, payload, callBack) {
    callPostAjax(PRODUCT_SERVICE, uri, payload).then(rs => {
        success(`Thêm thành công`);
        let property = {
            name: "Số ngày sử dụng",
            code: "use_day",
            type: 5,
            defaultValue: 0,
            required: true
        }
        callPostAjax(PRODUCT_SERVICE, 'v1/admin/property?product-type=' + rs.data.id, property)
            .then(rs => {
            }).catch(e => console.log(e))

        callBack(rs.data.id);
        $('#close-add').trigger('click');
        fillTable();
    }).catch(err => {
        console.log(err)
        error(`Thêm loại sản phẩm thất bại`);
    });
}

function checkInput() {
    let count = 3;

    if ($('#input-name').val().length === 0) {
        count--;
        info("Vui lòng điền tên danh mục")
    }

    if ($('#input-stt').val().length === 0) {
        count--;
        info("Vui lòng điền số thứ tự")
    }

    if ($('#input-alias').val().length === 0) {
        count--;
        info("Vui lòng điền đường dẫn thay thế")
    }

    return count === 3;
}

function uploadFiles() {

    let promises = [];

    if ($('#choose-image').get(0).files.length > 0) {
        let formData = new FormData();
        formData.append("files", $('#choose-image').get(0).files[0]);
        promises.push(callUploadFile(formData, true, "image"));
    }

    if ($('#choose-icon').get(0).files.length > 0) {
        let formData = new FormData();
        formData.append("files", $('#choose-icon').get(0).files[0]);
        promises.push(callUploadFile(formData, true, "icon"));
    }

    return promises;
}

//handling modal

function clickUpdateData() {

    $(`button[type-btn='btn-update-cate']`).click(function () {
        let id = $(this).attr('index');
        getProductTypeById(id).then(rs => {
            let payload = rs.data;

            $('#input-name').val(payload.name);
            $('#input-stt').val(payload.stt);
            $('#enable-view').val(payload.enableView ? 'true' : 'false');
            $('#input-alias').val(payload.alias ? payload.alias : '');
            $('#input-preview').val(payload.description);
            $('#input-alias').val(payload.alias);

            if (payload.icon) {
                $('#icon-preview').attr('src', viewSourceFile(payload.icon));
            }

            if (payload.image) {
                $('#img-preview').attr('src', viewSourceFile(payload.image));
            }

            if (payload.attachment) {
                $('#file-name').html(payload.attachment.split("/").pop());
                $('#file-preview').attr('src', 'resources/image/file.png');
            }

            $('#submit').off('click').click(event => {
                if (checkInput()) {
                    payload.name = $('#input-name').val();
                    payload.stt = parseInt($('#input-stt').val());
                    payload.description = $('#input-preview').val();
                    payload.enableView = $('#enable-view').val();
                    if ($('#choose-image').get(0).files.length > 0 || $('#choose-icon').get(0).files.length > 0) {
                        Promise.all(uploadFiles())
                            .then(rs => {
                                try {
                                    let imageUrl = rs.filter(data => data.label === "image")[0];
                                    let iconUrl = rs.filter(data => data.label === "icon")[0];

                                    if (imageUrl) payload.image = imageUrl.file;
                                    if (iconUrl) payload.icon = iconUrl.file;
                                } catch (e) {
                                }
                                put(payload);
                            }).catch(e => error("Tải lên file lỗi"))
                    } else {
                        put(payload);
                    }

                }
            });
        }).catch(err => error('Hiển thị thất bại'));

    });
}

function put(payload) {
    if (payload.alias != null && payload.alias != $('#input-alias').val()) {
        checkAlias($('#input-alias').val())
            .then(rs => {
                if (!rs) {
                    callPutAjax(IS_SERVICE, 'v1/admin/url-alias', {
                        alias: payload.alias,
                        companyId: COMPANY_ID,
                        newAlias: $('#input-alias').val()
                    }).then().catch(ex => error("Thay đổi alias lỗi"));
                    payload.alias = $('#input-alias').val();
                    callPut(payload)
                } else error("Đường dẫn đã được sử dụng")
            });
    } else if (!payload.alias && $('#input-alias').val().length > 0) {
        checkAlias($('#input-alias').val())
            .then(rs => {
                if (!rs) {
                    callPostAjax(IS_SERVICE, `v1/admin/url-alias?danh-muc-alias-id=${CATEGORY_ALIAS}`, {
                        alias: $('#input-alias').val(),
                        url: `${CATEGORY_ALIAS_PATH}?root=1&id=` + payload.id
                    }).then(rs => {
                        payload.alias = $('#input-alias').val();
                        callPut(payload)
                    }).catch(ex => {
                        error("Tạo alias lỗi!")
                    });
                }
            }).catch(e => {
            error("Có lỗi xảy ra")
        });
    } else {
        callPut(payload)
    }
}

function callPut(payload) {
    callPutAjax(PRODUCT_SERVICE, `v1/admin/product-type`, payload).then(() => {
        success(`Chỉnh sửa Thành công`);
        $('#close-add').trigger('click');
        fillTable();
    }).catch(err => {
        error(`Chỉnh sửa thất bại`);
    });
}

function clickDelete() {
    $('button[type-btn="btn-dropdown"]').off('click').click(function () {
        let idCate = parseInt($(this).attr('index'));
        $("#submit-delete").off('click').click(function () {
            callDeleteAjax(PRODUCT_SERVICE, `v1/admin/product-type/${idCate}`).then(() => {
                success(`Xoá thành công`);
                $('#close-delete').trigger('click');
                //delete alias
                let productType = productTypes.filter(pt => pt.id === idCate)[0];
                callDeleteAjax(IS_SERVICE, `v1/admin/url-alias/alias?alias=${productType.alias}&company-id=${COMPANY_ID}`)
                    .then(rs => {
                    }).catch(e => {
                    console.log(e)
                })
                fillTable();
            }).catch(err => {
                error('Xoá thất bại');
            });
        });
    });
}






