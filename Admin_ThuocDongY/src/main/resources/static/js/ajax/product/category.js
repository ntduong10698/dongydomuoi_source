let idCategory;
let listProductTypeData;
let flatCategories = [];

$(function () {

    $('#choose-image').on('change', function () {
        previewImage(this, '#img-preview');
    });

    $('#choose-icon').on('change', function () {
        previewImage(this, '#icon-preview');
    });

    checkAliasEvent();

    loadSelectCategory();

    loadCategoriesToModal();

    clickAddCategory();
});

// lấy data
async function getListProductTypeData() {
    let listProductType = [];
    await callGetAjax(PRODUCT_SERVICE, `v1/public/product-types/company/${COMPANY_ID}`).then(rs => {
        if (rs.success === true) {
            listProductType = rs.data;
        }
    }).catch(err => {
        console.log(err);
    });
    return listProductType;

}

function getCategoryByIdProductType(idProductType) {
    return callGetAjax(PRODUCT_SERVICE, `v1/public/categorys/product-type/${idProductType}`);
}

function getCategoryById(idCategory) {
    return callGetAjax(PRODUCT_SERVICE, `v1/public/categorys/${idCategory}`);
}

//mapping data to jsp

function showData() {
    getCategoryByIdProductType($('#select-product-type').val()).then(rs => {
        let cateArr = [];

        function putToArr(cates) {
            cates.forEach(cate => {
                cateArr.push(cate);
                if (cate.childs.length > 0)
                    putToArr(cate.childs);
            })
        }

        putToArr(rs.data);

        flatCategories = cateArr;

        let templateBodyTable = ``;
        templateBodyTable += cateArr.map((object, index) => {
            return `<tr class="my-item" id-category="${object.id}">
                                <th class="text-left font-weight-normal">${object.level}</th>
                                <td style="text-align: left;${object.level.split(".").length > 1 ? `padding-left:${(object.level.split(".").length - 1) * 5}%` : ''}" >${object.name ? object.name : ''}</td>                                             
                                <td style="max-width: 6%">
                                    <div class="dropdown">
                                        <button id="menu-drop-1" type="button" class="btn btn-action dropdown-toggle" style="height: 30px"
                                                data-toggle="dropdown">
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
                                          
                                                                                                                                
                                            <li>
                                                <button class="drop-2 btn btn-action font-weight-normal col-lg-12" type-btn="btn-delete-cate"
                                                        style="text-align: left" delete-id="${object.id}" data-toggle="modal"
                                                        data-target="#delete-modal">
                                                    <i class="fas fa-trash-alt"></i>&nbsp;&nbsp;Xóa
                                                </button>
                                            </li>
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
        console.log(err);
    });
}

function loadSelectCategory() {
    getListProductTypeData().then(rs => {
        listProductTypeData = rs;
        let templateOptions = rs.map(rs1 => `<option value="${rs1.id}">${rs1.name}</option>`).join('');
        $('#select-product-type').html(templateOptions);
        $('#modal-select-category')
        $('#select-product-type').val(listProductTypeData[0].id);
        showData();
    }).catch(err => {
        error('Hiển thị danh sách danh mục thất bại')
    });

    $("#select-product-type").change(function () {
        showData();
    });
}

function clickAddCategory() {
    $('#btn-add').click(() => {
        $('#input-name').val('');
        $('#input-stt').val(1);
        $('#file-name').html('');
        $('#input-alias').val('');
        $('#img-preview').attr('src', 'resources/image/camera2.png');
        $('#icon-preview').attr('src', 'resources/image/camera2.png');
        $('#modal-select-category').prop('disabled', false);
        $('#input-parent-category').prop('disabled', false);
        $('#modal-select-category').val($('#select-product-type').val())
        $('#modal-select-category').trigger('change');
        $('#submit').off('click').click(event => {
            let nameCate = $('#input-name').val();
            let sttCate = parseInt($('#input-stt').val());
            let idProductType = $('#modal-select-category').val();
            let description = $('#input-preview').val();
            let parentId = $('#input-parent-category').val() > 0 ? $('#input-parent-category').val() : null;
            let uri = `v1/admin/category?product-type=${idProductType}`;
            if (checkInput()) {
                let payload = {
                    name: nameCate,
                    icon: null,
                    image: null,
                    alias: $('#input-alias').val(),
                    attachment: null,
                    description: description,
                    parentId: parentId,
                    smallest: true,
                    stt: sttCate
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
                                    postCategory(uri, payload, idProductType, function (id) {
                                        callPostAjax(IS_SERVICE, `v1/admin/url-alias?danh-muc-alias-id=${CATEGORY_ALIAS}`, {
                                            alias: $('#input-alias').val(),
                                            url: `${CATEGORY_ALIAS_PATH}?root=0&id=` + id
                                        }).then().catch(ex => {
                                            error("Tạo alias lỗi!")
                                        });
                                    });

                                } else error("Đường dẫn đã được sử dụng")
                            });

                    }).catch(e => error("Tải lên file lỗi"))
            }
        });
    })
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

function postCategory(uri, payload, idProductType, callBack) {
    callPostAjax(PRODUCT_SERVICE, uri, payload).then((rs) => {
        success(`Thêm thành công`);
        callBack(rs.data.id);
        $('#close-add').trigger('click');
        $('#select-product-type').val(idProductType)
        showData();
    }).catch(err => {
        error(`Thêm danh mục thất bại`);
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

//handling modal

function loadCategoriesToModal() {
    getListProductTypeData().then(rs => {
        let templateOption = ``;
        templateOption += rs.map(rs1 => `<option value="${rs1.id}">${rs1.name}</option>`).join('');
        $('#modal-select-category').html(templateOption);
        $('#modal-select-category').off('change').on('change', function () {
            getCategoryByIdProductType($(this).val()).then(rs => {
                let cateArr = [];

                function putToArr(cates) {
                    cates.forEach(cate => {
                        cateArr.push(cate);
                        if (cate.childs.length > 0)
                            putToArr(cate.childs);
                    })
                }

                putToArr(rs.data);

                $('#input-parent-category').html('<option value="0">--Không chọn--</option>'
                    + cateArr.map(cate => {
                        let level = cate.level.split(".").length;
                        return `<option style="${level > 1 ? `font-weight:600` : ''}" value="${cate.id}">${cate.level}&nbsp;-&nbsp;${cate.name}</option>`
                    }).join(''))
            }).catch(e => {
                console.log(e);
            })
        })
    }).catch(err => console.log(err));
}

function clickUpdateData() {
    $(`button[type-btn='btn-update-cate']`).click(function () {
        idCategory = parseInt($(this).attr('index'));
        getCategoryById(idCategory).then(rs => {
            let payload = rs.data;
            $('#input-name').val(payload.name);
            $('#input-stt').val(payload.stt);
            $('#input-alias').val(payload.alias ? payload.alias : '');
            $('#input-preview').val(payload.description);
            $('#input-alias').val(payload.alias);
            $('#modal-select-category').val(payload.productType.id);
            $('#modal-select-category').trigger('change');
            $('#modal-select-category').prop('disabled', true);
            setTimeout(function () {
                if (payload.parentId) {
                    $('#input-parent-category').val(payload.parentId);
                } else $('#input-parent-category').prop('selectedIndex', 0);
                $('#input-parent-category').prop('disabled', true);
            }, 500)

            if (payload.image) {
                $('#img-preview').attr('src', viewSourceFile(payload.image));
            }

            if (payload.icon) {
                $('#file-preview').attr('src', viewSourceFile(payload.icon));
            }
            $('#submit').off('click').click(event => {
                if (checkInput()) {
                    payload.name = $('#input-name').val();
                    payload.stt = parseInt($('#input-stt').val());
                    payload.description = $('#input-preview').val();

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
                                putCate(payload);
                            }).catch(e => error("Tải lên file lỗi"))
                    } else {
                        putCate(payload);
                    }
                }
            });
        }).catch(err => console.log(err));
    });
}

function putCate(payload) {
    console.log(payload)
    if (!payload.alias && $('#input-alias').val().length > 0) {
        checkAlias($('#input-alias').val())
            .then(rs => {
                if (!rs) {
                    callPostAjax(IS_SERVICE, `v1/admin/url-alias?danh-muc-alias-id=${CATEGORY_ALIAS}`, {
                        alias: $('#input-alias').val(),
                        url: `${CATEGORY_ALIAS_PATH}?root=0&id=` + payload.id
                    }).then(rs => {
                        payload.alias = $('#input-alias').val();
                        callPutCate(payload)
                    }).catch(ex => {
                        error("Tạo alias lỗi!")
                    });
                }
            }).catch(e => {
            error("Có lỗi xảy ra")
        });
    } else if (payload.alias && payload.alias !== $('#input-alias').val()) {
        checkAlias($('#input-alias').val())
            .then(rs => {
                if (!rs) {
                    callPutAjax(IS_SERVICE, 'v1/admin/url-alias', {
                        alias: payload.alias,
                        companyId: COMPANY_ID,
                        newAlias: $('#input-alias').val()
                    }).then().catch(ex => error("Thay đổi alias lỗi"));
                    payload.alias = $('#input-alias').val();
                    callPutCate(payload)
                } else error("Đường dẫn đã được sử dụng")
            });
    } else {
        callPutCate(payload)
    }
}

function callPutCate(payload) {
    callPutAjax(PRODUCT_SERVICE, `v1/admin/category?change-parent=false`, payload).then(() => {
        success(`Chỉnh sửa Thành công`);
        $('#close-add').trigger('click');
        showData();
    }).catch(err => {
        error(`Chỉnh sửa thất bại`);
    });
}

function clickDelete() {

    $(`button[type-btn='btn-delete-cate']`).click((event) => {
        idCategory = $(event.target).attr('delete-id');
    });

    $("#submit-delete").off('click').click(function () {
        if (idCategory !== null) {
            callDeleteAjax(PRODUCT_SERVICE, `v1/admin/category/${idCategory}`).then(() => {
                let idProductType = $('#select-category').children('option:selected').val();
                success(`Xoá thành công`);
                $('#close-delete').trigger('click');
                //delete alias
                let cate = flatCategories.filter(c => c.id == idProductType)[0];
                callDeleteAjax(IS_SERVICE, `v1/admin/url-alias/alias?alias=${cate.alias}&company-id=${COMPANY_ID}`)
                    .then(rs => {
                    }).catch(e => {
                    console.log(e)
                })
                showData(idProductType);
            }).catch(err => {
                error('Xoá thất bại');
                console.log(err);
            });
        } else {

        }

    });
}






