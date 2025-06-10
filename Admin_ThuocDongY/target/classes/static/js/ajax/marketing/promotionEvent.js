let listObject = [];
let listProductObject = [];
let idPromotionToPostProductAndCategory;
let idsCategoryProductModal;
let textSearch;
let textProductSearch;

$(function () {
    checkAll();

    $('#choose-file').on('change', function () {
        previewImage(this, '#img-preview');
    });

    loadCategories();

    pageObject(1, 15);

    pageProductObject(1, 1, 10);

    addEvent();

    searchEvent();

    searchProductEvent();

    addPromotionToProductByCategory();

});

//===============handle Data=============
async function loadList(page = 1, size = 15) {
    let data;
    let uri = `v1/admin/promotions/company/${COMPANY_ID}?page=${page}&size=${size}`;
    textSearch = $('#text-search').val() !== null ? $('#text-search').val() : "";

    if ($('#text-search').val() !== null && $('#text-search').val().length !== 0) {
        let textPara = `&text=${textSearch}`;
        uri = uri.concat(textPara);
    }

    data = await callGetAjax(MARKETING_SERVICE, uri)
        .then(rs => {
            listObject = rs.data.content;
            return rs.data;
        }).catch();

    return data;
}

//categories: list id của danh mục
//type: 1: dung cu, 2: dich vu, 3: khoa hoc
async function loadListProduct(type = 1, page = 1, size = 10) {
    let data;
    let uri = `v1/public/products/filter?product-type-id=${type}&status=0&sort-type=date&asc=true&page=${page}&size=${size}`;

    textProductSearch = $('#text-search-product').val() !== null ? $('#text-search-product').val() : "";
    idsCategoryProductModal = $('#select-category-product').select2().val();

    if ($('#text-search-product').val() !== null && $('#text-search-product').val().length !== 0) {
        let textPara = `&text=${textProductSearch}`;
        uri = uri.concat(textPara);
    }

    if (idsCategoryProductModal.length > 0) {
        idsCategoryProductModal.forEach(val => {
            let cateId = `&categories=${val}`;
            uri = uri.concat(cateId);
        });
    }

    data = await callGetAjax(PRODUCT_SERVICE, uri)
        .then(rs => {
            listProductObject = rs.data.content;
            return rs.data;
        }).catch(err => {
            error('Lấy dữ liệu sản phẩm thất bại');
        });
    return data
}

function loadCategories() {
    $('#select-type-product').change(function () {
        let typePrId = $(this).val();
        let url1 = `v1/public/categorys/product-type/${typePrId}`;
        let templateDanhMucOption1 = ``;
        callGetAjax(PRODUCT_SERVICE, url1).then(rs => {
            if (rs.success) {
                rs = rs.data;
                templateDanhMucOption1 += rs.map(rs1 => `<option value="${rs1.id}">${rs1.name}</option>`).join('');
                $('#select-category-product').html(templateDanhMucOption1);
            }
        }).catch(err => {
            error(`Hiển thị danh mục thất bại`);
        });
    });

    let typeProductId = parseInt($('#select-type-product').val());
    let url = `v1/public/categorys/product-type/${typeProductId}`;
    let templateDanhMucOption = ``;
    callGetAjax(PRODUCT_SERVICE, url).then(rs => {
        if (rs.success) {
            rs = rs.data;
            templateDanhMucOption += rs.map(rs1 => `<option value="${rs1.id}">${rs1.name}</option>`).join('');
            $('#select-category-product').html(templateDanhMucOption);
        }
    }).catch(err => {
        error(`Hiển thị danh mục thất bại`);
    });
}

function getListProduct(listProductIds) {
    let uri = `v1/public/products?`;
    if (listProductIds.length !== 0) {
        uri += listProductIds.map(val => `ids=${val}`).join('&');
    }
    return callGetAjax(PRODUCT_SERVICE, uri);
}

function ajaxUpdatePromotion(payload) {
    callPutAjax(MARKETING_SERVICE, 'v1/admin/promotion', payload)
        .then(saved => {
            success("Chỉnh sửa khuyến mãi thành công");
            $('#close-add').trigger('click');
            pageObject();
        }).catch(ex => {
        error("Chỉnh sửa khuyến mãi thất bại");
    });
}

//===============handle Data=============

//=========hien thi len table + phan trang=========
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
                loadList(pagination.pageNumber, size).then(rs1 => {
                    fillTable(listObject);
                })
            }
        })
    }).catch();
}

//loadListProduct(type, page, size, categoryId = 0, text)

function pageProductObject(type = 1, page = 1, size = 16) {
    let container = $('#page-product');
    container.html('');
    // let idCategory = parseInt($('#select-category').children('option:selected').val());
    loadListProduct(type, page, size).then(rs => {
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
                loadListProduct(type, pagination.pageNumber, size).then(rs1 => {
                    fillProductTable(listProductObject);
                })
            }
        })
    }).catch();
}

function pageCategoryObject() {
    let container = $('#page-category');
    container.html('');
    // let idCategory = parseInt($('#select-category').children('option:selected').val());
    fillCategoryTable(page, size).then(rs => {
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
                loadListProduct(pagination.pageNumber, size).then(rs1 => {
                    fillProductTable(listProductObject);
                })
            }
        })
    }).catch();
}

//=========end hien thi len table + phan trang=========
//tao template

//===============fill tables ==============
function fillTable(list) {
    let content = '';
    list.forEach((object) => {
        function templateGiam() {
            let templateInfo;
            let giaTri;
            if (object.type === 3) {
                giaTri = object.decrease * 100 + '%';
                templateInfo = `<div>Giá trị: giảm ${giaTri}</div>`;
            } else if (object.type === 2) {
                giaTri = viewPriceVND(object.decrease);
                templateInfo = `<div>Giá trị: giảm ${giaTri}</div>`;
            } else {
                giaTri = object.content;
                templateInfo = `<div>Giá trị: ${giaTri}</div>`;
            }
            return templateInfo;
        }

        let loai = object.type === 1 ? 'Quà tặng' : (object.type === 2 ? 'Giảm tiền' : 'Giảm %');
        content += ` <tr class="my-item" style="">
                                <td>${object.id}</td>
                                <td class="text-left" style="max-width: 300px">
                                    <div style="text-align: start;">Tên:&nbsp;${object.name ? object.name : ''}</div>
                                    ${templateGiam()}
                                </td>       
                                <td>${loai}</td> 
                                <td>${object.global ? 'Đơn hàng' : 'Sản phẩm'}</td> 
                                <td>${object.start > 0 ? viewDateTextVn(convertMilisecondToDate(object.start), true) : ''}</td>           
                                <td>${object.start > 0 ? viewDateTextVn(convertMilisecondToDate(object.end), true) : 'Không giới hạn'}</td> 
                                <td style="max-width: 6%">
                                    <div class="dropdown">
                                        <button id="menu-drop-1" type="button" type-btn="btn-dropdown" class="btn btn-action dropdown-toggle" style="height: 30px"
                                                data-toggle="dropdown" global="${object.global}">
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
                                                        style="text-align: left" index="${object.id}" data-toggle="modal"
                                                        data-target="#productsModal">
                                                    <i class="fas fa-list-ul"></i>&nbsp;&nbsp;Danh sách sản phẩm
                                                </button>
                                            </li>
                                            
                                            <li>
                                                <button class="drop-3 btn btn-action font-weight-normal col-lg-12"
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
    addProductEvent();
    deleteEvent();
}

//---productModal---
function checkAll() {
    $('#check-all').change(() => {
        $('.check-object').prop('checked', $('#check-all').prop('checked'));
    });
}

function pageAddedProducts(data, size = 15) {
    let container = $('#page-added-product');
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
            fillAddedPromotionProductTable(response, pagination.pageNumber);
        }
    })

}

function fillProductTable(list) {
    let content = '';
    list.forEach((object) => {
        let promotions = object.promotions.length !== 0 ? object.promotions.map((val) => {
            return `${val.name} - mã ${val.id}`;
        }).join(`<br>`) : "";
        content += ` <tr class="my-item" style="">
                                <td>${object.id}</td>
                                <td>
                                    ${object.name}
                                </td>       
                                <td>${viewPriceVND(object.cost)}</td> 
                                <td class="text-center">
                                    ${promotions}
                                </td>  
                                <td><input class="check-object" type="checkbox"  index="${object.id}"></td>
                            </tr>`;
    });
    $('#row-list-product').html(content);

    addProductWithPromotionEvent();

}

function fillCategoryTable(list, listIdPromotion) {
    let content;
    listIdPromotion = listIdPromotion.join(', ');
    list.forEach((object) => {

        content += ` <tr class="my-item" style="">
                                <td>${object.id}</td>
                                <td class="d-flex flex-column justify-content-start align-items-baseline">
                                    ${object.name}
                                </td>
                                <td>${listIdPromotion}</td>
                                <td style="max-width: 6%">
                                    <div class="dropdown">
                                        <button id="menu-drop-1" type="button" class="btn btn-action dropdown-toggle" style="height: 30px"
                                                data-toggle="dropdown">
                                            <i class="fas fa-cog"></i>
                                        </button>
                                        <ul aria-labelledby="menu-drop-1" style="">
                                            <li>
                                                <button class="drop-4 btn btn-action font-weight-normal col-lg-12"
                                                        style="text-align: left" index="${object.id}" data-toggle="modal"
                                                        data-target="#myModal">
                                                        <i class="fas fa-pen"></i>&nbsp;&nbsp;Thêm khuyến mãi
                                                </button>
                                            </li>
                                        </ul>
                                    </div>
                                </td>
                            </tr>`;
    });
    $('#row-list-product').html(content);

}

//---end productModal---
//---fill Category table---

function fillAddedPromotionProductTable(list, page) {
    let content;
    if (list.length !== 0) {
        list.forEach((object, index) => {
            content += ` <tr class="my-item" style="">
                                <td>${(page + 1) * 10 + index}</td>
                                <td>${object.id}</td>
                                <td>
                                    ${object.name}
                                </td>       
                                <td style="max-width: 6%">
                                    <div class="dropdown">
                                        <button id="menu-drop-3" type="button" class="btn btn-action dropdown-toggle" style="height: 30px"
                                                data-toggle="dropdown">
                                            <i class="fas fa-cog"></i>
                                        </button>
                                        <ul class="dropdown-menu" aria-labelledby="menu-drop-3" style="">
                                            <li>
                                                <button class="drop-5 btn btn-action font-weight-normal col-lg-12"
                                                        style="text-align: left" delete-index="${object.id}">
                                                        <i class="fas fa-trash"></i>&nbsp;&nbsp;Xoá sản phẩm
                                                </button>
                                            </li>
                                       
                                        </ul>
                                    </div>
                                </td>
                            </tr>`;
        });
        $('#row-list-addedPromotion-product').html(content);
    } else {
        $('#row-list-addedPromotion-product').html('');
    }
    deleteProductEvent();
}

//--- end fill Category table---
//===============end fill tables ==============

//===========check input=========
function checkInput() {
    let count = 7;

    if ($('#input-name').val().length === 0) {
        info("Vui lòng điền tên chương trình khuyến mãi");
        count--;
    }

    let typeKM = parseInt($('#input-loai-khuyen-mai').val());

    switch (typeKM) {
        case 1:
            if ($('#input-gift').val().length === 0) {
                info("Vui lòng điền nội dung quà");
                count--;
            }
            break;
        case 2:
            if ($('#input-gtkm').val().length === 0) {
                info("Vui lòng điền số tiền giảm");
                count--;
            }
            break;
        case 3:
            if ($('#input-gtkm').val().length === 0) {
                info("Vui lòng điền số % giảm");
                count--;
            }
            if ($('#input-gttt').val().length === 0) {
                info("Vui lòng điền số tiền giảm tối đa");
                count--;
            }
            break;
    }

    if ($('#input-tgad').val() == 1) {
        if ($('#input-start-date').val().length === 0) {
            info("Vui lòng điền thời gian bắt đầu");
            count--;
        }
        if ($('#input-end-date').val().length === 0) {
            info("Vui lòng điền thời gian kết thúc");
            count--;
        }
    }
    return count === 7;
}

//===========end check input=========

//==========handle event for each element=========
function addEvent() {
    $('#input-loai-khuyen-mai').on('change', function () {
        let idLoaiKM1 = parseInt($(this).val());
        switch (idLoaiKM1) {
            case 1:
                $('#div-decrease').hide();
                $('#div-max').hide();
                $('#div-gift').show();
                break;
            case 2:
                $('#div-decrease').show();
                $('#label-decrease').html(`Số tiền giảm <span style="color: red"> *</span>`)
                $('#div-max').hide();
                $('#div-gift').hide();
                break;
            case 3:
                $('#div-decrease').show();
                $('#label-decrease').html(`Số % giảm <span style="color: red"> *</span>`)
                $('#div-max').show();
                $('#div-gift').hide();
                break;
        }
    });
    $('#input-tgad').change(() => {
        let isGioiHanTime1 = $('#input-tgad').children('option:selected').val() == 0 ? true : false;
        if (isGioiHanTime1) {
            $('#wrap-ngay-bd').addClass('d-none');
            $('#wrap-ngay-kt').addClass('d-none');
        } else {
            $('#wrap-ngay-bd').removeClass('d-none');
            $('#wrap-ngay-kt').removeClass('d-none');
        }
    });
    $('#btn-add').off('click').on('click', function () {
        //clear content
        $('#input-name').val('');
        $('#input-loai-khuyen-mai').val('1');
        $('#choose-file').val('');
        $('#img-preview').attr('src', 'resources/image/camera2.png');
        $('#input-loai-khuyen-mai').attr('disabled', false)
        $('#input-gtkm').val(0)
        $('#input-gttt').val(0);
        $('#input-gttd').val(0);
        //kiểm tra loại khuyến mãi.
        //loại 1 + 2 thì giá trị khuyến mãi có step = 1000 vnđ
        //nếu loại 3 (%) thì giới hạn trong khoảng 1 và step = 0.1
        $('#input-loai-khuyen-mai').trigger('change');
        //check gioi han thoi gian
        //nếu giới hạn => hiện lên phần điền thời gian
        //nếu không => ẩn

        let isGioiHanTime2 = $('#input-tgad').children('option:selected').val() == 0 ? true : false;

        if (isGioiHanTime2) {
            $('#wrap-ngay-bd').addClass('d-none');
            $('#wrap-ngay-kt').addClass('d-none');
        } else {
            $('#wrap-ngay-bd').removeClass('d-none');
            $('#wrap-ngay-kt').removeClass('d-none');
        }

        $('#submit').off('click').on('click', function () {
            if (checkInput(true)) {
                let formData = new FormData();
                formData.append("files", $('#choose-file').get(0).files[0]);
                callUploadFile(formData).then(rs => {
                    let global = $('#input-pv-khuyen-mai').val() == 1 ? true : false;
                    let ngayBatDau = 0;
                    let ngayKetThuc = 0;
                    // neu k bị giới hạn thời gian sẽ lấy giá trị từ input
                    let isGioiHanTime3 = $('#input-tgad').children('option:selected').val() == 0 ? true : false;
                    if (!isGioiHanTime3) {
                        ngayBatDau = new Date($('#input-start-date').val());
                        ngayBatDau = ngayBatDau * 1;
                        ngayKetThuc = new Date($('#input-end-date').val());
                        ngayKetThuc = ngayKetThuc * 1;
                    }

                    let idLoaiKM3 = $(`#input-loai-khuyen-mai`).children('option:selected').val();
                    let gtkm = parseFloat($('#input-gtkm').val());
                    if (idLoaiKM3 == 3)
                        gtkm /= 100;
                    let object = {
                        companyId: COMPANY_ID,
                        content: $('#input-gift').val(),
                        decrease: gtkm,
                        end: ngayKetThuc,
                        global: global,
                        image: rs.data[0].uri,
                        maxDiscount: parseInt($('#input-gttd').val()),
                        minimum: parseInt($('#input-gttt').val()),
                        name: $('#input-name').val(),
                        note: $('#input-note').val(),
                        start: ngayBatDau,
                        type: parseInt(idLoaiKM3)
                    };

                    callPostAjax(MARKETING_SERVICE, `v1/admin/promotion`, object)
                        .then(() => {
                            success("Tạo chương trình khuyến mãi thành công");
                            $('#close-add').trigger('click');
                            pageObject(1, 15);
                        }).catch(ex => {

                        error("Tạo chương trình khuyến mãi thất bại");
                    })
                }).catch(ex => {

                    error("Tải hình ảnh thất bại");
                })
            }
        })
    });
}

function changeEvent() {
    $('.drop-1').off('click').on('click', function () {
        let id = $(this).attr('index');

        callGetAjax(MARKETING_SERVICE, `v1/public/promotions/${id}`)
            .then(rs => {
                let payload = rs.data.promotion;

                //fill content
                $('#input-name').val(payload.name);
                $('#input-loai-khuyen-mai').val(payload.type);
                $('#input-loai-khuyen-mai').trigger('change');
                $('#input-loai-khuyen-mai').attr("disabled", true);
                $('#choose-file').val('');

                if (payload.image){
                    $('#img-preview').attr('src', viewSourceFile(payload.image));
                }

                $('#input-gift').val('');
                $('#input-pv-khuyen-mai').val(payload.global ? '1' : '0');
                $('#input-gtkm').val(payload.type === 3?payload.decrease*100:payload.decrease);
                $('#input-gttt').val(payload.minimum);
                $('#input-gttd').val(payload.maxDiscount);
                $('#input-gift').val(payload.content ? payload.content : '');
                $('#input-note').val(payload.note ? payload.note : '');
                //check gioi han thoi gian
                //nếu giới hạn => hiện lên phần điền thời gian
                //nếu không => ẩn

                function checkTimeIsNull() {
                    return !(payload.start !== null && payload.end !== null);

                }

                if (checkTimeIsNull()) {
                    $('#input-tgad').val('0');
                    $('#wrap-ngay-bd').addClass('d-none');
                    $('#wrap-ngay-kt').addClass('d-none');
                } else {
                    $('#wrap-ngay-bd').removeClass('d-none');
                    $('#input-start-date').val(formatTimeForChangeEvent(payload.start));
                    $('#wrap-ngay-kt').removeClass('d-none');
                    $('#input-end-date').val(formatTimeForChangeEvent(payload.end));
                }

                $('#submit').off('click').on('click', function () {
                    let isGioiHanTime3 = $('#input-tgad').children('option:selected').val() == 1;
                    if (isGioiHanTime3) {
                        ngayBatDau = new Date($('#input-start-date').val());
                        ngayBatDau = ngayBatDau * 1;
                        ngayKetThuc = new Date($('#input-end-date').val());
                        ngayKetThuc = ngayKetThuc * 1;
                    }
                    payload.name = $('#input-name').val();
                    payload.type = parseInt($('#input-loai-khuyen-mai').children('option:selected').val());
                    payload.content = $('#input-gift').val();
                    let decrease = parseFloat($('#input-gtkm').val());
                    if (payload.type == 3)
                        decrease /= 100;
                    payload.decrease = decrease;
                    payload.global = $('#input-pv-khuyen-mai') == 1;
                    payload.start = isGioiHanTime3 ? ngayBatDau : null;
                    payload.end = isGioiHanTime3 ? ngayKetThuc : null;
                    payload.note = $('#input-note').val();
                    if (checkInput(false)) {
                        if ($('#choose-file').get(0).files.length > 0) {
                            let formData = new FormData();
                            formData.append("files", $('#choose-file').get(0).files[0]);
                            callUploadFile(formData).then(rs => {
                                payload.image = rs.data[0].uri;
                                ajaxUpdatePromotion(payload);
                            }).catch(ex => error("Tải hình ảnh thất bại"))
                        } else ajaxUpdatePromotion(payload);
                    }
                })
            }).catch(e => {

            error("Tải thông tin lỗi");
        })
    });
}

function addProductEvent() {
    $('button[type-btn="btn-dropdown"]').click(function(){
        let isGlobal = $(this).attr('global') === 'true';
        if(isGlobal){
            $('.drop-2').off('click').hide();
        }else{
            $('.drop-2').off('click').show().click((event) => {
                event.preventDefault();
                idPromotionToPostProductAndCategory = $(event.target).attr('index');
                callGetAjax(MARKETING_SERVICE, `v1/public/promotions/${idPromotionToPostProductAndCategory}`).then(rs => {
                    rs = rs.data.products;
                    getListProduct(rs).then(rs1 => {
                        rs1 = rs1.data;
                        pageAddedProducts(rs1)
                    }).catch(err => {
                        info(`Hiện chưa có sản phẩm với mã khuyến mãi ${idPromotionToPostProductAndCategory}`);
                        fillAddedPromotionProductTable([]);
                    });
                });
            });
        }

    })

}

function addProductWithPromotionEvent() {
    $('#btn-add-all-product').off('click').click(function (event) {

        event.preventDefault();

        let idProducts = [];
        $('.check-object').each((index, checkbox) => {
            if ($(checkbox).is(`:checked`)) {
                idProducts.push(parseInt($(checkbox).attr("index")));
            }
        });

        callPostAjax(MARKETING_SERVICE, `v1/admin/promotion/${idPromotionToPostProductAndCategory}/products?ids=${idProducts}`)
            .then(() => {
                callGetAjax(MARKETING_SERVICE, `v1/public/promotions/${idPromotionToPostProductAndCategory}`).then(rs => {
                    rs = rs.data.products;
                    getListProduct(rs).then(rs1 => {
                        rs1 = rs1.data;
                        pageAddedProducts(rs1);
                    }).catch(err => {
                        info(`Hiện chưa có sản phẩm với mã khuyến mãi ${idPromotionToPostProductAndCategory}`);
                        fillAddedPromotionProductTable([]);
                    });
                });
                success(`Thêm thành công`);
            }).catch();
    })
}

function deleteEvent() {
    $('.drop-3').off('click').on('click', function () {
        let id = $(this).attr("delete-id");
        $('#submit-delete').off('click').on('click', function () {
            callDeleteAjax(MARKETING_SERVICE, `v1/admin/promotion/${id}`)
                .then(rs => {
                    pageObject();
                    $('#close-delete').trigger('click');
                    success("Xóa thành công");
                }).catch(ex => {
                error("Xóa khuyến mãi thất bại")
            });
        })
    });
}

function deleteProductEvent() {
    $(`.drop-5`).click(function () {
        let idProduct = $(this).attr('delete-index');
        callDeleteAjax(MARKETING_SERVICE, `v1/admin/promotion/${idPromotionToPostProductAndCategory}/products?ids=${idProduct}`).then(() => {
            callGetAjax(MARKETING_SERVICE, `v1/public/promotions/${idPromotionToPostProductAndCategory}`).then(rs => {
                rs = rs.data.products;
                getListProduct(rs).then(rs1 => {
                    rs1 = rs1.data;
                    pageAddedProducts(rs1);
                }).catch(err => {
                    info(`Hiện chưa có sản phẩm với mã khuyến mãi ${idPromotionToPostProductAndCategory}`);
                    fillAddedPromotionProductTable([]);
                });
            });
            success('Xoá thành công');
        }).catch(er => error(`Xoá thất bại`));
    });
}

function addPromotionToProductByCategory() {
    $('#select-type-product-category').change(function () {
        let type = $('#select-type-product-category').children(`option:selected`).val();
        let option = ``;
        callGetAjax(PRODUCT_SERVICE, `v1/public/categorys/product-type/${type}`).then(rs => {
            rs = rs.data;
            option += rs.map(rs1 => `<option value="${rs1.id}">${rs1.name}</option>`).join('');
            $('#select-category').html(option);
        }).catch(err => error('Hiển thị sản phẩm theo loại thất bại'));
    });
    let type1 = $('#select-type-product-category').children(`option:selected`).val();
    let option1 = ``;
    callGetAjax(PRODUCT_SERVICE, `v1/public/categorys/product-type/${type1}`).then(rs => {
        rs = rs.data;
        option1 += rs.map(rs1 => `<option value="${rs1.id}">${rs1.name}</option>`).join('');
        $('#select-category').html(option1);
    }).catch(err => error('Hiển thị sản phẩm theo loại thất bại'));

    $(`#btn-search-category`).click(function () {
        let idCategory = $(`#select-category`).children('option:selected').val();
        callPostAjax(MARKETING_SERVICE, `v1/admin/promotion/${idPromotionToPostProductAndCategory}/category/${idCategory}`).then(() => {
            success('Thêm thành công');
            $(`#close-add-category`).trigger('click');
        }).catch(err => error('Thêm theo danh mục thất bại'));
    });


}

//==========end handle event for each element=========
//============ handle time view, price==============



function formatTimeForChangeEvent(data) {
    Number.prototype.AddZero = function (b, c) {
        let l = (String(b || 10).length - String(this).length) + 1;
        return l > 0 ? new Array(l).join(c || '0') + this : this;
    };
    let d = new Date(data);
    let localDateTime = [d.getFullYear(),
            (d.getMonth() + 1).AddZero(),
            d.getDate().AddZero()].join('-') + 'T' +
        [d.getHours().AddZero(),
            d.getMinutes().AddZero()].join(':');
    return localDateTime;
}

function viewField(data) {
    return data ? data : "";
}

function viewPriceVND(price) {
    if (price) {
        return formatNumber(viewField(price), ",", ".") + " VNĐ";
    }
    return "";
}

function convertMilisecondToDate(data) {
    return new Date(data);
}

//============end handle time view, price==============

//=============handle common event=============
function searchEvent() {
    $('#btn-search').on('click', function () {
        pageObject();
    });
}

function searchProductEvent() {
    $('#btn-search-product').click(() => {
        let type = parseInt($('#select-type-product').val());
        pageProductObject(type);
    })
}

//=============end handle common event=============