let listObject = [];
//grid
let listImage = [];
let grid;
let widgets = [];
let activeProductId = 0;
//
let selectObject = [];
let categories = [];
let brands = [];
let productTypes = [];
let properties = [];
let editor;

//
let currentPage = 1;

$(function () {
    editor = initQuill('#input-content');
    $('#choose-file').on('change', function () {
        previewImage(this, '#img-preview');
    });

    $('#upload-image').on('click', function () {
        $('.custom-file-label').html('');
    })

    toggleInfo();
    $('#choose-file-image').on('change', function () {
        previewImage(this, '#img-preview-image');
    });
    //grid
    uploadImgEvent();
    gridInitEvent()
    //
    checkAliasEvent();
    loadProductTypes();
    loadCategories();
    loadBrands();
    pageObject(1, 16);
    addEvent();
    searchEvent();
    addPromoEvent();
});

function addPromoEvent() {
    let container = $('#page-promo');
    getListPromos().then(rs => {
        container.pagination({
            dataSource: [],
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
                getListPromos(pagination.pageNumber, 10).then(rs => {
                    fillTablePromo(rs.content);
                })
            }
        })
    })

    function fillTablePromo(list) {
        $('#row-promos').html(list.map((promo, index) => {
            let value = '';
            switch (promo.type) {
                case 1:
                    value = promo.content;
                    break;
                case 2:
                    value = `Giảm ${formatNumber(promo.decrease)} VNĐ`;
                    break;
                case 3:
                    value = `Giảm ${promo.decrease * 100} % giá trị`;
            }

            let loai = promo.type === 1 ? 'Quà tặng' : (promo.type === 2 ? 'Giảm tiền' : 'Giảm %')

            return `<tr>
                                <td>${promo.id}</td>
                                <td style="text-align: left; max-width: 250px">${promo.name}</td>
                                <td>${loai}</td>
                                <td>${value}</td>
                                <td>${promo.start > 0 ? viewDateTextVn(new Date(promo.start)) : ''}</td>           
                                <td>${promo.start > 0 ? viewDateTextVn(new Date(promo.end)) : 'Không giới hạn'}</td>
                                <td><button class="btn btn-primary btn-add-promo" index="${promo.id}"><i class="fa fa-plus-circle"></i></button></td>
                        </tr>`
        }).join(''))
        addPromoToProductEvent();
    }

    function addPromoToProductEvent() {
        $('.btn-add-promo').off('click').on('click', function () {
            let promoId = $(this).attr("index");
            callPostAjax(MARKETING_SERVICE, `v1/admin/promotion/${promoId}/products?ids=${activeProductId}`)
                .then(rs => {
                    success("Thêm thành công");
                    getAddedPromos();
                }).catch(ex => error("Thêm thất bại"))
        })
    }
}

async function getListPromos(page = 1, size = 10) {
    let data = null;
    await callGetAjax(MARKETING_SERVICE, `v1/admin/promotions/company/${COMPANY_ID}?global=false`)
        .then(rs => {
            data = rs.data;
        })
    return data;
}

function getAllProperties(callBack) {
    callGetAjax(PRODUCT_SERVICE, 'v1/public/properties/product-type/' + $('#input-product-type').val())
        .then(rs => {
            properties = rs.data;
            $('#collapse-properties').html('');
            //create form properties
            properties.forEach((prop, index) => {
                let form;
                let options = prop.options ? '<option value="-69">Không chọn</option>' + prop.options.split("|").map((o, index) => `<option value="${index}">${o}</option>`).join('') : '';
                switch (prop.type) {
                    case 2:
                        form = $('#single-select-ex').clone();
                        form.find('.input-property').html(options);
                        break;
                    case 3:
                        form = $('#multi-select-ex').clone();
                        form.find('.input-property').html(options);
                        form.find('.input-property').select2();
                        break;
                    case 4:
                        form = $('#input-text-ex').clone();
                        form.find('.input-property').attr('value', prop.defaultValue ? prop.defaultValue : '');
                        break;
                    case 5:
                        form = $('#input-number-ex').clone();
                        form.find('.input-property').attr('value', prop.defaultValue ? prop.defaultValue : '');
                        break;
                }
                form.find('.input-property').attr('index', prop.id);
                form.find('.input-property').attr('required', prop.required);

                if (prop.required) prop.name += '(*)';
                form.find('.label-property').text(prop.name);
                $('#collapse-properties').append(form.html());
                if (callBack) callBack();
            })
        }).catch(e => error("Tải thông tin thuộc tính lỗi"));
}

async function loadList(page = 1, size = 20) {
    let data;
    let cateList = null;
    if ($('#select-category').val() != 0)
        cateList = [$('#select-category').val()];
    let textSearch = $('#text-search').val() != null ? $('#text-search').val() : "";
    await callGetAjax(PRODUCT_SERVICE, `v1/admin/product/filter?company-id=${COMPANY_ID}`
        .concat(`&product-type-id=${$('#select-product-type').val()}`)
        .concat(cateList ? `&categories=${cateList}` : '')
        .concat(`&brand-id=${$('#select-brand').val()}`)
        .concat(`&text=${textSearch}`)
        .concat(`&status=${$('#select-status').val()}`)
        .concat(`&sort-type=${$('#select-sort').val()}`)
        .concat(`&page=${page}`))
        .then(rs => {
            listObject = rs.data.content;
            selectObject = listObject.map(o => {
                return {
                    id: o.id,
                    checked: false
                }
            });
            return data = rs.data;
        }).catch();
    return data;
}

function loadProductTypes() {
    callGetAjax(PRODUCT_SERVICE, 'v1/public/product-types/company/' + COMPANY_ID)
        .then(rs => {
            productTypes = rs.data;
            let content = '<option value="0">Loại sản phẩm</option>' + productTypes.map(d => `<option value="${d.id}">${d.name}</option>`).join('');
            let content2 = productTypes.map(d => `<option value="${d.id}">${d.name}</option>`).join('');
            $('#input-product-type').html(content2);
            $('#select-product-type').html(content);

            $('#input-product-type').on('change', function () {
                loadInputCategories();
                getAllProperties();
            })

            $('#select-product-type').on('change', function () {
                loadCategories();
            })
        }).catch(ex => info("Tải dữ liệu danh mục thất bại"))
}

function loadCategories() {
    callGetAjax(PRODUCT_SERVICE, 'v1/public/categorys/product-type/' + $('#select-product-type').val())
        .then(rs => {
            categories = rs.data;
            let content = categories.map(d => `<option value="${d.id}">${d.name}</option>`).join('');
            $('#select-category').html('<option value="0">Danh mục</option>' + content);
        }).catch(ex => {
        console.log(ex)
        $('#select-category').html('<option value="0">Danh mục</option>');
    })
}

function loadInputCategories(callBack) {
    callGetAjax(PRODUCT_SERVICE, 'v1/public/categorys/product-type/' + $('#input-product-type').val())
        .then(rs => {
            let cateArr = [];

            function putToArr(cates) {
                cates.forEach(cate => {
                    cateArr.push(cate);
                    if (cate.childs.length > 0)
                        putToArr(cate.childs);
                })
            }

            putToArr(rs.data);
            let content = cateArr.map(cate => {
                let level = cate.level.split(".").length;
                return `<option ${cate.smallest ? '' : 'disabled'} style="${level > 1 ? `font-weight:600` : ''}" value="${cate.id}">${cate.level}&nbsp;-&nbsp;${cate.name}</option>`
            }).join('');
            $('#input-danh-muc').html(content);
            $('#input-danh-muc').select2();
            if (callBack) callBack();
        }).catch(ex => {
        console.log(ex)
        $('#input-danh-muc').html('<option value="0">Danh mục</option>');
        $('#input-danh-muc').select2();
    })
}

function loadBrands() {
    callGetAjax(PRODUCT_SERVICE, 'v1/public/brands/company/' + COMPANY_ID + "?size=400")
        .then(rs => {
            brands = rs.data.content;
            let content = '<option value="0">Thương hiệu</option>' + brands.map(d => `<option value="${d.id}">${d.name}</option>`).join('');
            let content2 = '<option value="0">--Chọn thương hiệu--</option>' + brands.map(d => `<option value="${d.id}">${d.name}</option>`).join('');
            $('#input-thuong-hieu').html(content2);
            $('#select-brand').html(content);
        }).catch(ex => info("Tải dữ liệu thương hiệu thất bại"))
}

function pageObject(page = currentPage, size = 15) {
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
                currentPage = pagination.pageNumber;
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
        let status = (object.status === 1 ? 'Đang kinh doanh' : (object.status === 2 ? 'Ngừng kinh doanh' : 'Tạm hết hàng'));
        content += ` <tr class="my-item" style="">
                                <td>${object.id}</td>
                                <td class="text-left">${object.name ? object.name : ''}</td>
                                <td>${object.model ? object.model : ''}</td>
                                <td>${object.categories.join(", ")}</td>                              
                                <td class="text-right">${formatNumber(object.cost)}</td>
                                <td>${object.unit ? object.unit : ''}</td>
                                <td>${object.view}</td>
                                <td>${object.sold}</td>
                                <td>${object.quantity}</td>
                                <td>${status}</td>                                                 
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
                                                <button class="drop-3 btn btn-action font-weight-normal col-lg-12"
                                                        style="text-align: left" index="${object.id}" data-toggle="modal"
                                                        data-target="#image-modal">
                                                        <i class="fas fa-image"></i>&nbsp;&nbsp;Danh sách ảnh
                                                </button>
                                            </li>
                                            
                                            <li>
                                                <button class="drop-4 btn btn-action font-weight-normal col-lg-12"
                                                        style="text-align: left" index="${object.id}" data-toggle="modal"
                                                        data-target="#promo-modal">
                                                        <i class="fas fa-percent"></i>&nbsp;&nbsp;Chương trình khuyến mãi
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
    checkEvent();
    viewPromoEvents();
    viewImageEvent();
}

function viewPromoEvents() {
    $('.drop-4').off('click').on('click', function () {
        activeProductId = $(this).attr('index');
        getAddedPromos();
    })
}

function checkInput(checkFile = true) {
    let count = 7 + properties.length;

    if ($('#input-name').val().length === 0) {
        info("Vui lòng điền tên sản phẩm");
        count--;
    }

    if ($('#input-quantity').val().length === 0) {
        info("Vui lòng điền tồn kho");
        count--;
    }

    if ($('#input-danh-muc').val().length === 0) {
        info("Vui lòng chọn danh mục");
        count--;
    }

    if ($('#input-cost').val().length === 0) {
        info("Vui lòng điền giá");
        count--;
    }

    if ($('#input-unit').val().length === 0) {
        info("Vui lòng điền đơn vị");
        count--;
    }

    if ($('#input-alias').val().length === 0) {
        info("Vui lòng điền đường dẫn thay thế");
        count--;
    }

    if (checkFile && $('#choose-file').get(0).files.length === 0) {
        info("Vui lòng tải lên ảnh");
        count--;
    }

    properties.forEach(prop => {
        let input = $(`.input-property[index='${prop.id}']`);
        if (input.attr('required') === 'required' && input.val().length === 0) {
            info("Vui lòng điền " + prop.name);
            count--;
        }
    })

    return count === 7 + properties.length;
}

function toggleInfo() {
    $('#btn-more').on('click', function () {
        if ($('#div-more').is(":hidden"))
            $('#div-more').show();
        else $('#div-more').hide();

    })
}

function addEvent() {

    $('#btn-add').off('click').on('click', function () {
        //clear content
        $('#input-name').val('')
        $('#input-model').val('')
        $('#input-quantity').val(0)
        $('#input-product-type').trigger('change');
        $('#input-thuong-hieu').val(0)
        $('#input-cost').val(0)
        $('#input-unit').val('')
        $('#input-weight').val(0)
        $('#input-preview').val('')
        $('#input-alias').val('')
        $('#choose-file').val('')
        $('#img-preview').attr('src', 'resources/image/camera2.png');
        editor.root.innerHTML = '';

        $('#submit').off('click').on('click', function () {
            if (checkInput()) {
                if ($('#input-alias').val().length > 0)
                    checkAlias($('#input-alias').val())
                        .then(rs => {
                            if (!rs) {
                                let formData = new FormData();
                                formData.append("files", $('#choose-file').get(0).files[0]);
                                callUploadFile(formData).then(rs => {
                                    let object = {
                                        name: $('#input-name').val(),
                                        model: $('#input-model').val(),
                                        quantity: parseInt($('#input-quantity').val()),
                                        categoryIds: $('#input-danh-muc').val().map(cate => parseInt(cate)),
                                        brandId: parseInt($('#input-thuong-hieu').val()),
                                        cost: parseInt($('#input-cost').val()),
                                        unit: $('#input-unit').val(),
                                        weight: parseFloat($('#input-weight').val()),
                                        alias: $('#input-alias').val(),
                                        preview: $('#input-preview').val(),
                                        introduction: autoAddAltImg(editor.root.innerHTML),
                                        image: rs.data[0].uri
                                    };
                                    callPostAjax(PRODUCT_SERVICE, 'v1/admin/product', object)
                                        .then(saved => {
                                            //create alias
                                            callPostAjax(IS_SERVICE, `v1/admin/url-alias?danh-muc-alias-id=${PRODUCT_ALIAS}`, {
                                                alias: $('#input-alias').val(),
                                                url: `${PRODUCT_ALIAS_PATH}?id=` + saved.data.id
                                            }).then().catch(ex => error("Tạo alias lỗi!"))

                                            // add properties
                                            let propsData = properties.map(prop => {
                                                let inputProp = $(`.input-property[index='${prop.id}']`);
                                                let propData = {
                                                    value: '',
                                                    propertyId: prop.id
                                                }
                                                switch (prop.type) {
                                                    case 3:
                                                        propData.value = inputProp.val().join(',');
                                                        break;
                                                    case 2:
                                                    case 4:
                                                    case 5:
                                                        propData.value = inputProp.val() === -69 ? null : inputProp.val();
                                                        break;
                                                }
                                                return propData;
                                            })
                                            callPostAjax(PRODUCT_SERVICE, `v1/admin/product/${saved.data.id}/properties`, propsData)
                                                .then().catch(ex => error("Thêm thuộc tính lỗi"))
                                            success("Tạo mới sản phẩm thành công");
                                            $('#close-add').trigger('click');
                                            pageObject();
                                        }).catch(ex => error("Tạo mới sản phẩm thất bại"))
                                }).catch(ex => error("Tải hình ảnh thất bại"))
                            } else error("Đường dẫn đã được sử dụng")
                        })
            }
        })
    });


}

function changeEvent() {
    $('.drop-1').off('click').on('click', function () {
        let id = $(this).attr('index');
        callGetAjax(PRODUCT_SERVICE, `v1/admin/product/${id}`)
            .then(rs => {
                    let data = rs.data;
                    let product = rs.data.product;
                    let cost = rs.data.costs.filter(c => c.defaultCost)[0];
                    let cateJsons = rs.data.categories;
                    let props = rs.data.properties;
                    $('#input-name').val(product.name)
                    $('#input-model').val(product.model ? product.model : '')
                    $('#input-quantity').val(product.quantity)
                    $('#input-trang-thai').val(product.status);
                    $('#input-product-type').val(cateJsons[0].productTypeId);
                    getAllProperties(function () {
                        props.forEach(prop => {
                            $(`.input-property[index='${prop.productProperty.id}']`).val(prop.value);
                        })
                    })
                    loadInputCategories(function () {
                        $('#input-danh-muc').val(cateJsons.map(cj => cj.cateId))
                        $('#input-danh-muc').select2();
                    })

                    $('#input-thuong-hieu').val(product.brand ? product.brand.id : '0')
                    $('#input-cost').val(cost.cost)
                    $('#input-unit').val(cost.unit)
                    $('#input-weight').val(cost.weight ? cost.weight : 0)
                    $('#input-alias').val(product.alias ? product.alias : '');
                    $('#input-preview').val(product.preview ? product.preview : '')
                    $('#img-preview').attr('src', viewSourceFile(product.image));
                    editor.root.innerHTML = product.introduction

                    $('#submit').off('click').on('click', function () {
                            if (!checkInput(false))
                                return;
                            //check delete category
                            let listCate = $('#input-danh-muc').val().map(id => parseInt(id))
                            cateJsons.forEach(cj => {
                                if (!listCate.includes(cj.cateId))
                                    cj.deleted = true;
                            })
                            // check new category
                            listCate.forEach(cateId => {
                                if (!cateJsons.map(cj => cj.cateId).includes(cateId))
                                    cateJsons.push(
                                        {
                                            cateId: cateId,
                                            newCate: true,
                                            deleted: false
                                        }
                                    )
                            });
                            if (product.alias != null && product.alias != $('#input-alias').val()) {
                                checkAlias($('#input-alias').val())
                                    .then(rs => {
                                        if (!rs) {
                                            callPutAjax(IS_SERVICE, 'v1/admin/url-alias', {
                                                alias: product.alias,
                                                companyId: COMPANY_ID,
                                                newAlias: $('#input-alias').val()
                                            }).then().catch(ex => error("Thay đổi alias lỗi"));
                                            product.alias = $('#input-alias').val();
                                            putProduct();
                                        } else {
                                            error("Đường dẫn đã được sử dụng");
                                        }
                                    }).catch(e => error("Có lỗi xảy ra"))
                            } else if (product.alias == null && $('#input-alias').val().length > 0) {
                                //create alias
                                checkAlias($('#input-alias').val())
                                    .then(rs => {
                                        if (!rs) {
                                            callPostAjax(IS_SERVICE, `v1/admin/url-alias?danh-muc-alias-id=${PRODUCT_ALIAS}`, {
                                                alias: $('#input-alias').val(),
                                                url: `${PRODUCT_ALIAS_PATH}?id=` + product.id
                                            }).then().catch(ex => error("Tạo alias lỗi!"))
                                            product.alias = $('#input-alias').val();
                                            putProduct();
                                        } else {
                                            error("Đường dẫn đã được sử dụng")
                                        }
                                    }).catch(e => error("Có lỗi xảy ra"))
                            } else putProduct();

                            function putProduct() {
                                product.name = $('#input-name').val();
                                product.model = $('#input-model').val();
                                product.quantity = parseInt($('#input-quantity').val());
                                product.status = $('#input-trang-thai').val();
                                cost.cost = parseInt($('#input-cost').val());
                                cost.unit = $('#input-unit').val();
                                cost.weight = parseFloat($('#input-weight').val());
                                product.preview = $('#input-preview').val();
                                product.brand = brands.filter(b => b.id == $('#input-thuong-hieu').val())[0];
                                product.status = $('#input-trang-thai').val();
                                product.introduction = autoAddAltImg(editor.root.innerHTML);

                                let currentPropIds = props.map(p => p.productProperty.id);

                                //new properties
                                let propsData = properties.filter(p => !currentPropIds.includes(p.id)).map(prop => {
                                    let inputProp = $(`.input-property[index='${prop.id}']`);
                                    let propData = {
                                        value: '',
                                        propertyId: prop.id
                                    }
                                    propData.value = inputProp.val() === -69 ? null : inputProp.val();
                                    return propData;
                                })

                                callPostAjax(PRODUCT_SERVICE, `v1/admin/product/${product.id}/properties`, propsData)
                                    .then().catch(ex => error("Thêm thuộc tính lỗi"))

                                props.forEach(prop => {
                                    prop.value = $(`.input-property[index='${prop.productProperty.id}']`).val();
                                })
                                if ($('#choose-file').get(0).files.length > 0) {
                                    let formData = new FormData();
                                    formData.append("files", $('#choose-file').get(0).files[0]);
                                    callUploadFile(formData).then(rs => {
                                        product.image = rs.data[0].uri
                                        ajaxUpdateProduct(data);
                                    }).catch(ex => error("Tải hình ảnh thất bại"))
                                } else ajaxUpdateProduct(data);
                            }
                        }
                    )
                }
            ).catch()
    });
}

function ajaxUpdateProduct(data) {
    callPutAjax(PRODUCT_SERVICE, 'v1/admin/product', data)
        .then(saved => {
            success("Chỉnh sửa sản phẩm thành công");
            $('#close-add').trigger('click');
            pageObject();
        }).catch(ex => error("Chỉnh sửa sản phẩm thất bại"))
}

function checkEvent() {
    $('.check-customer').off('change').on('change', function () {
        selectObject.filter(sc => sc.id === $(this).attr('index'))[0].checked = $(this).is(':checked');
    })
}

function checkAliasEvent() {

    $('#btn-generate-alias').on('click', function () {
        $('#input-alias').val(to_slug($('#input-name').val()));
    })

    $('#btn-check-alias').on('click', function () {
        if ($('#input-alias').val().length === 0) {
            warning("Đường dẫn không thể là khoảng trắng")
        } else checkAlias($('#input-alias').val())
            .then(rs => {
                if (!rs) success("Đường dẫn có thể sử dụng")
                else warning("Đường dẫn đã được sử dụng")
            })
    })
}

function deleteEvent() {
    $('.drop-2').off('click').on('click', function () {
        let id = $(this).attr("delete-id");
        let product = listObject.filter(p => p.id == id)[0];
        console.log(product)
        $('#submit-delete').off('click').on('click', function () {
            callDeleteAjax(PRODUCT_SERVICE, 'v1/admin/product/' + id)
                .then(rs => {
                    pageObject();
                    $('#close-delete').trigger('click');
                    success("Xóa sản phẩm thành công");

                    if (product.alias)
                        callDeleteAjax(IS_SERVICE, `v1/admin/url-alias/alias?alias=${product.alias}&company-id=${COMPANY_ID}`)
                            .then(rs => {
                            }).catch(e => {
                            console.log(e)
                        })
                }).catch(ex => error("Xóa sản phẩm thất bại"));
        })
    });
}

function searchEvent() {
    $('#btn-search').on('click', function () {
        pageObject();
    });
}

//image

function viewImageEvent() {
    $('.drop-3').off('click').on('click', function () {
        activeProductId = $(this).attr('index')
        getImages();
    });
}

function getImages() {
    callGetAjax(PRODUCT_SERVICE, `v1/public/files/product/${activeProductId}`)
        .then(rs => {
            listImage = rs.data;
            createGrid();
        }).catch(ex => {
        error('Tải dữ liệu hình ảnh thất bại');
        console.log(ex);
    });
}

async function checkAlias(alias) {
    let data;
    await callGetAjax(IS_SERVICE, `v1/public/url-alias/check-exist?company-id=${COMPANY_ID}&alias=` + alias)
        .then(rs => {
            data = rs.data;
        }).catch(ex => error("Có lỗi xảy ra!"))
    return data;
}

function createGrid() {
    grid.removeAll();
    widgets.length = 0;
    listImage.map((image, index) => {
            console.log("image " + index)
            if (image.type !== 1) return;
            let imageStyle = '';
            getSizeImage(DOWNLOAD_PREFIX + image.url, function (wh) {
                let w = wh.width;
                let h = wh.height;
                if (wh.height > 153) {
                    for (let i = 2; i < 20; i++) {
                        if (h < (153 * i)) {
                            imageStyle = `height: ${h /= i}px; width: ${w /= i}px ;padding-top: 13%`;
                            break;
                        }
                    }
                }

                let item = `<div id="img${image.id}" class="grid-stack-item" style="margin-left: 5px; padding: 2px">
                                      <div class="grid-stack-item-content" style="text-align: center; position: relative; padding: 5px">                                 
                                          <i class="fa fa-times-circle fa-2x delete-image" img-id="${image.id}" style="color: red;padding: unset;position: absolute; top: 0; left: 0" data-toggle="modal"
                                        data-target="#delete-image-modal"></i>                                   
                                      <img style="${imageStyle}" src="${DOWNLOAD_PREFIX + image.url}"/>
                                      <br/>
                                      <span style="">${Math.ceil(wh.width)}x${Math.ceil(wh.height)}</span>
                                      </div>
                                </div>`;
                if (w > 256)
                    item = `<div id="img${image.id}" class="grid-stack-item" style="margin-left: 5px; padding: 2px">
                                      <div class="grid-stack-item-content" style="text-align: center; position: relative; padding: 5px">                                 
                                          <i class="fa fa-times-circle fa-2x delete-image" img-id="${image.id}" style="color: red;padding: unset;position: absolute; top: 0; left: 0" data-toggle="modal"
                                        data-target="#delete-image-modal"></i>                                   
                                      <img style="width: 100%; padding-top: 13%" src="${DOWNLOAD_PREFIX + image.url}"/>
                                      <br/>
                                      <span style="">${Math.ceil(wh.width)}x${Math.ceil(wh.height)}</span>
                                      </div>
                                </div>`;
                let itemOptions = {
                    x: (index % 6) * 2,
                    y: Math.floor(index / 6) * 3,
                    noResize: true,
                    width: 2,
                    height: 3
                };
                widgets.push(grid.addWidget(item, itemOptions));
                deleteImageEvent();
            });
        }
    );
}

function deleteImageEvent() {
    $('.delete-image').off('click').on('click', function () {
        let id = $(this).attr('img-id');
        $('#submit-delete-img').off('click').on('click', function () {
            callDeleteAjax(PRODUCT_SERVICE, 'v1/admin/files?ids=' + id)
                .then(rs => {
                    success("Xóa hình ảnh thành công");
                    $('#close-delete-img').trigger('click');
                    getImages();
                })
                .catch(ex => error("Xóa hình ảnh thất bại"));
        })
    })
}

function uploadImgEvent() {

    $('#upload-image').on('click', function (){
        $('#img-preview-image').attr('src','resources/image/camera2.png');
        $('.custom-file-label').html('');
    })

    $('.custom-file-input').on('change', function () {
        $('.custom-file-label').html($(this).get(0).files[0].name);
    });
    $('#submit-upload').on('click', function () {
        let $img = $('#choose-file-image').get(0).files;
        if ($img.length) {
            let formData = new FormData();
            formData.append("files", $img[0]);
            callUploadFile(formData).then(
                rs => {
                    let newImg = {
                        deleted: false,
                        stt: 0,
                        url: rs.data[0].uri,
                        type: 1
                    }
                    let imgs = [];
                    imgs.push(newImg);
                    callPostAjax(PRODUCT_SERVICE, 'v1/admin/files/product/' + activeProductId, imgs)
                        .then(rs => {
                            getImages();
                            success("Tải lên ảnh thành công!");
                            $('#close-upload').trigger('click');
                        }).catch(ex => error("Tải lên ảnh thất bại"));
                    $('#close-upload').trigger('click');
                }
            ).catch(ex => error("Tải ảnh thất bại!"))
        }
    })
}

function gridInitEvent() {
    let options = {
        animation:
            {
                "startup": true,
                duration: 2000,
                easing: 'out'
            },
        removable: '.trash', // Kéo đến $('.trash') để xóa
        removeTimeout: 100,
        column: 12,
        oneColumnModeDomSort: true, // 1 cột
        verticalMargin: 20,
        disableResize: true,
        animate: true,
        float: true
    };
    grid = GridStack.init(options);
    let order = [];
    grid.on('dragstop', function (event, ui) {
        grid.compact();
        setTimeout(function () {
            order = widgets.map(node => {
                console.log(node);
                return {
                    id: $(node).attr('id').replace('img', ''),
                    x: $(node).attr('data-gs-x'),
                    y: $(node).attr('data-gs-y')
                }
            });

            order.sort(function (a, b) {
                if (a.y < b.y) return -1;
                if (a.y > b.y) return 1;
                if (a.x < b.x) return -1;
                return 1;
            });

            console.log(order);
        }, 500);

    });

    $('#save-image').off('click').on('click', function () {
        callPutAjax('admin/collection-item/reorder?item-id=' + order.map(o => o.id), null)
            .then(rs => {
                success("Lưu thành công!");
                $('#close-image').trigger('click');
            }).catch(ex => error("Lưu thất bại!"))
    })
}

//promo

function getAddedPromos() {
    callGetAjax(MARKETING_SERVICE, 'v1/admin/promotions/product/' + activeProductId)
        .then(rs => {
                let promos = rs.data;
                $('#row-added-promo').html(promos.map((promo, index) => {
                        let value = '';
                        switch (promo.type) {
                            case 1:
                                value = promo.content;
                                break;
                            case 2:
                                value = `Giảm ${formatNumber(promo.decrease)} VNĐ`;
                                break;
                            case 3:
                                value = `Giảm ${promo.decrease * 100} % giá trị`;
                        }
                        return `<tr>
                                <td>${index + 1}</td>
                                <td>${promo.name}</td>
                                <td>${value}</td>
                                <td>${promo.start > 0 ? viewDateTextVn(new Date(promo.start)) : ''}</td>           
                                <td>${promo.start > 0 ? viewDateTextVn(new Date(promo.end)) : 'Không giới hạn'}</td>
                                <td><button class="btn btn-danger btn-delete-promo" index="${promo.id}"><i class="fa fa-trash"></i></button></td>
                        </tr>`
                    })
                )
                //set delete event
                $('.btn-delete-promo').off('click').on('click', function () {
                    let id = $(this).attr('index');
                    callDeleteAjax(MARKETING_SERVICE, `v1/admin/promotion/${id}/products?ids=${activeProductId}`)
                        .then(rs => {
                            success("Đã xóa khuyến mãi");
                            getAddedPromos();
                        }).catch(ex => error("Xóa thất bại"))
                })
            }
        ).catch(ex => error("Tải dữ liệu lỗi"))

}

function viewDateTextVn(text) {
    if (text) {
        let date = new Date(text);
        let hours = date.getUTCHours() < 10 ? '0' + date.getUTCHours() : date.getUTCHours();
        let minutes = date.getUTCMinutes() < 10 ? '0' + date.getUTCMinutes() : date.getUTCMinutes();
        return `${date.getUTCDate()}/${date.getUTCMonth() + 1}/${date.getFullYear()} - ${hours}:${minutes}`;

    } else {
        return '';
    }
}


