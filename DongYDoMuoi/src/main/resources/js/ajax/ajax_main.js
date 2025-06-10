var FILE_PREFIX = 'https://cdn.bksoftwarevn.com/resources/micro-upload/dong-y/';
const PREFIX_LIBRARY_JS = 'https://cdn.bksoftwarevn.com/resources/library_js/ajax_micro_service/';
const TIME_ALTER = 3000;
const TIME_RUN_TOAST = 30000;
const COMPANY_ID = 3 ;
const NGHIEN_CUU_TYPE = 1;
const TUYEN_DUNG_TYPE = 2;
const GIOI_THIEU_TYPE = 3;
const BAI_THUOC_TYPE = 4;
const LIST_CUSTOMER = [
    "Ông Nguyễn Bình Minh - 0915770xxx",
    "Bà Lê Thanh Hương - 0942458xxx",
    "Ông Trần Đình Khang - 0822222xxx" ,
    "Bà Nguyễn Thị Kim Anh - 0898873xxx",
    "Ông Phạm Văn Hải - 0968.11.0xxx",
    "Ông Nguyễn Hữu Đức - 0974575xxx",
    "Bà Vũ Tuyết Trinh - 0978409xxx",
    "Ông Nguyễn Nhật Quang - 098641xxx",
    "Bà Nguyễn Thị Oanh - 0985884xxx",
    "Ông Thân Quang Khoát - 0972441xxx",
    "Ông Nguyễn Kiêm Hiếu - 0918209xxx",
    "Ông Nguyễn Hồng Phương - 0986619xxx",
    "Ông Trần Việt Trung - 0972095xxx",
    "Ông Nguyễn Việt Hoàng - 0972085xxx"
];
function requireJs(listUrl) {
    if(listUrl && listUrl.length > 0) {
        let scriptNone = $("#script-none");
        let scriptClone = scriptNone.clone().removeAttr("id");
        let listScript = listUrl.map(data => {
            return scriptClone.clone().attr("src", data);
        });
        scriptNone.before(listScript);
    }
}

function getUrlLibraryJs(fileName) {
    if(fileName) return PREFIX_LIBRARY_JS + fileName;
}

//view Field
function viewField(data) {
    return data ? data : "";
}

//view error
function viewError(selector, message) {
    $(selector).addClass("is-invalid");
    $(selector).siblings(".invalid-feedback").text(`${message}. Vui lòng nhập lại!`);
}

//hidden error
function hiddenError(selector) {
    $(selector).removeClass("is-invalid");
}

//view error
function viewSuccess(selector, message) {
    $(selector).addClass("is-valid");
    $(selector).siblings(".valid-feedback").text(`${message}.`);
}

//hidden error
function hiddenSuccess(selector) {
    $(selector).removeClass("is-valid");
}

//*****************regex field

function removeAscent(str) {
    if (str === null || str === undefined) return str;
    str = str.toLowerCase();
    str = str.replace(/à|á|ạ|ả|ã|â|ầ|ấ|ậ|ẩ|ẫ|ă|ằ|ắ|ặ|ẳ|ẵ/g, "a");
    str = str.replace(/è|é|ẹ|ẻ|ẽ|ê|ề|ế|ệ|ể|ễ/g, "e");
    str = str.replace(/ì|í|ị|ỉ|ĩ/g, "i");
    str = str.replace(/ò|ó|ọ|ỏ|õ|ô|ồ|ố|ộ|ổ|ỗ|ơ|ờ|ớ|ợ|ở|ỡ/g, "o");
    str = str.replace(/ù|ú|ụ|ủ|ũ|ư|ừ|ứ|ự|ử|ữ/g, "u");
    str = str.replace(/ỳ|ý|ỵ|ỷ|ỹ/g, "y");
    str = str.replace(/đ/g, "d");
    return str;
}

function regexTen(name) {
    return /^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$/g.test(removeAscent(name));
}

////////////////
function regexUserName(userName) {
    return /^(?=[a-zA-Z0-9._]{2,20}$)(?!.*[_.]{2})[^_.].*[^_.]$/.test(userName)
}

function regexDate(date) {
    return /^([0-2][0-9]|(3)[0-1])(\/)(((0)[0-9])|((1)[0-2]))(\/)\d{4}$/i.test(date);
}

////////////////
function regexEmail(email) {
    return /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(email)
}

////////////////
function regexDienThoai(soDienThoai) {
    return /((09|03|07|08|05)+([0-9]{8})\b)/g.test(soDienThoai)
}

function regexNumber(text) {
    return /^\d+$/.test(text);
}

//
function checkSize(size, text) {
    return text.length > 0 && text.length <= size;
}

//***************alter
function alterImage(text) {
    $.notify({
        icon: 'resources/dist/img/logo-tamviet-01.png',
        title: 'Tavi MRS',
        message: text
    }, {
        delay: 3000,
        offset: {x: 15, y: 15},
        icon_type: 'image',
        type: 'minimalist',
        template: '<div data-notify="container" class="col-xs-11 col-sm-3 alert alert-{0}" role="alert">' +
            '<img data-notify="icon" class="img-circle pull-left">' +
            '<div class="text-mess">' +
            '<span data-notify="title">{1}</span>' +
            '<span data-notify="message">{2}</span>' +
            '</div>' +
            '</div>'
    });
}

function alertSuccess(text, time = 2000) {
    $.notify({
        icon: 'far fa-check-circle',
        message: text
    }, {
        delay: time,
        offset: {x: 15, y: 15},
        type: 'success',
    });
}

function alertInfo(text, time = 2000) {
    $.notify({
        icon: 'fas fa-info-circle',
        message: text
    }, {
        delay: time,
        offset: {x: 15, y: 15},
        type: 'info',
    });
}

function alertWarning(text, time = 2000) {
    $.notify({
        icon: 'fas fa-exclamation',
        message: text
    }, {
        delay: time,
        offset: {x: 15, y: 15},
        type: 'warning',
    });
}

function alertDanger(text, time = 2000) {
    $.notify({
        icon: 'fas fa-exclamation-triangle',
        message: text
    }, {
        delay: time,
        offset: {x: 15, y: 15},
        type: 'danger',
    });
}

function viewSrcFile(src) {
    if(src) {
        if(src.indexOf("http") === 0) return src;
        return FILE_PREFIX + src;
    }
    return "";
}

function textToIconFile(nameFile) {
    if ((/(.doc|.docx)$/ig).test(nameFile)) return '<i class="fas fa-file-word text-primary"></i>';
    if ((/(xls|xlsx)$/ig).test(nameFile)) return '<i class="fas fa-file-excel text-success"></i>';
    if ((/(ppt|pptx)$/ig).test(nameFile)) return '<i class="fas fa-file-powerpoint text-danger"></i>';
    if ((/(zip|rar|tar|gzip|gz|7z)$/ig).test(nameFile)) return '<i class="fas fa-file-archive text-muted"></i>';
    if ((/(htm|html)$/ig).test(nameFile)) return '<i class="fas fa-file-code text-info"></i>';
    if ((/(txt|ini|csv|java|php|js|css)$/ig).test(nameFile)) return '<i class="fas fa-file-text text-info"></i>';
    if ((/(avi|mpg|mkv|mov|mp4|3gp|webm|wmv)$/ig).test(nameFile)) return '<i class="fas fa-file-movie-o text-warning"></i>';
    if ((/(mp3|wav)$/ig).test(nameFile)) return '<i class="fas fa-file-audio text-warning"></i>';
    if ((/(.jpg|.png|.gif)$/ig).test(nameFile)) return '<i class="fas fa-file-image text-primary"></i>';
    if ((/(.pdf)$/ig).test(nameFile)) return '<i class="fas fa-file-pdf text-danger"></i>';
    return '<i class="fas fa-file"></i>'
}

//format money
function formatNumber(nStr, decSeperate, groupSeperate) {
    nStr += '';
    let x = nStr.split(decSeperate);
    let x1 = x[0];
    let x2 = x.length > 1 ? '.' + x[1] : '';
    let rgx = /(\d+)(\d{3})/;
    while (rgx.test(x1)) {
        x1 = x1.replace(rgx, '$1' + groupSeperate + '$2');
    }
    return x1 + x2;
}

//view date text vn
function viewDateTextVn(text) {
    let date = new Date(text);
    return `${date.getDay() != 0 ? `Thứ ${date.getDay() + 1}`: "Chủ nhật"}, ${date.getDate()}/${date.getMonth()+1}/${date.getFullYear()} - ${date.getHours()}:${date.getMinutes()}`;
};

function viewDateVn() {
    if ($(".date-vn").length > 0) {
        $(".date-vn").datepicker({
            language: "vi"
        });
    }
}

//
function reverseDate(date) {
    let adate = date.split("-");
    return adate[2]+"/"+adate[1]+"/"+adate[0];
}
function convertDateYMD(date) {
    let adate = date.split("/");
    return adate[2]+"-"+adate[1]+"-"+adate[0];
}

function runToolTip() {
    $('[data-toggle="tooltip"]').tooltip();
}

function viewPriceVND(price) {
    if(price) {
        return formatNumber(viewField(price),",",".") + " VNĐ";
    }
    return "";
}
////////////////seo web
function getPageId() {
    return $("#page-id").attr("data-page-id");
}

function viewHrefAlias(alias, hrefPerfix) {
    let rs = hrefPerfix;
    if(alias && alias.length > 0) {
        rs = alias;
    }
    return rs;
}

function setDateTimePicker(selector, time) {
    $(selector).datepicker('setDate',new Date(time));
}

function getItemLocalStorage(key) {
    let rs = localStorage.getItem(key);
    rs = rs ? JSON.parse(rs) : null;
    return rs;
}

function setItemLocalStorage(key, val) {
    localStorage.setItem(key, JSON.stringify(val));
}

function getItemSessionStorage(key) {
    let rs = sessionStorage.getItem(key);
    rs = rs ? JSON.parse(rs) : null;
    return rs;
}

function setItemSessionStorage(key, val) {
    sessionStorage.setItem(key, JSON.stringify(val));
}

function removeItemLocalStorage(key) {
    localStorage.removeItem(key);
}

function removeItemSessionStorage(key) {
    sessionStorage.removeItem(key);
}

function loadingButton(selector) {
    $(selector).prop("disabled", true);
    $(selector).html(`<img src="file/loading-button.gif"/>`)
}

function unLoadingButton(selector, text) {
    $(selector).prop("disabled", false);
    $(selector).html(text);
}

function setTitlePage(title = null, desPage = null, imagePage = null) {
    if(title) {
        $("#title-page").html(title+" Nataliepmu.com");
        $(".main-title-page").attr("content",title+" Nataliepmu.com");
    }
    $("#facebook-url-page").attr("content",location.href);
    if(desPage) $(".main-description-page").attr("content", desPage);
    if(imagePage) $(".main-image-page").attr("content", imagePage);
}

function getStreamURL(fileType,fileName) {
    return STREAM_PREFIX + `v1/public/stream/company/1/${fileType}?fileName=${encodeURIComponent(fileName)}`;
}

//ALIAS
function viewAlias(alias, hrefPrefix) {
    let rs = hrefPrefix;
    if(alias && alias.length > 0) {
        rs = alias;
    }
    return rs;
}

function viewAliasProductType(alias, id) {
    return viewAlias(alias, `danh-muc?id=${id}&root=1`);
}

function viewAliasCategory(alias, id) {
    return viewAlias(alias, `danh-muc?id=${id}&root=0`);
}

function viewAliasProduct(alias, id) {
    return viewAlias(alias, `chi-tiet-san-pham?id=${id}`);
}

function viewAliasConstruction(alias, id) {
    return viewAlias(alias, `chi-tiet-cong-trinh?id=${id}`);
}

function viewAliasArticle(alias, endpoint = `chi-tiet-tuyen-dung`, id){
    return viewAlias(alias, `${endpoint}?id=${id}`);
}

function viewAliasInfo(alias, id) {
    return viewAlias(alias, `chi-tiet-gioi-thieu?id=${id}`);
}
//END_ALIAS

//PRODUCT
function viewPromotionCostProduct(listPromotions, cost) {
    let minusPrice = 0;
    let viewTitleGift = "";
    let viewDiscount = "";
    if(listPromotions != null) {
        listPromotions.map((item, index) => {
            switch (item.type) {
                case 1:
                    viewTitleGift += viewTitleGift.length === 0 ? `- ${item.content}` : `&#10;- ${item.content}`;
                    break;
                case 2:
                    viewDiscount += `<span>${item.name}</span>`;
                    minusPrice += item.decrease;
                    break;
                case 3:
                    viewDiscount += `<span>${item.name}</span>`;
                    minusPrice += cost*item.decrease;
                    break;
                default:
                    break;
            }
        })
    }
    return {minusPrice, viewTitleGift, viewDiscount};
}
//END_PRODUCT

function runToolTip() {
    $('[data-toggle="tooltip"]').tooltip();
}

function viewPriceVND(price) {
    if(price) {
        return formatNumber(viewField(price),",",".") + " VNĐ";
    }
    return "";
}

function hiddenNavHero() {
    $("#nav-hero").addClass("hero-a");
}

function viewBtnPage() {
    $(".btn-page").removeClass("d-none");
}

function removeBtnPage() {
    $(".btn-page").remove();
}

function loadingBtn() {
    $(".btn-loading img").removeClass("d-none");
    $(".btn-loading img").addClass("d-block");
    $(".btn-loading span").removeClass("d-block");
    $(".btn-loading span").addClass("d-none");
    $(".btn-loading").prop("disabled", true);
}

function hiddenLoadingBtn() {
    $(".btn-loading span").removeClass("d-none");
    $(".btn-loading span").addClass("d-block");
    $(".btn-loading img").removeClass("d-block");
    $(".btn-loading img").addClass("d-none");
    $(".btn-loading").prop("disabled", false);
}

//cart
function getCartLocalStorage() {
    let cart = [];
    if (localStorage && localStorage.getItem("cart")) {
        cart = JSON.parse(localStorage.getItem("cart"));
    }
    return cart;
}

function clickBtnAddCart() {
    $(".btn-add-cart").unbind("click").click(function () {
        addToCart($(this).attr("data-id"));
        return false;
    })
}

function addToCart(id, number = 1) {
    let cart = getCartLocalStorage();
    let check = true;
    cart = cart.map(data => {
        if(data.id == id) {
            if(data.number == number) {
                alertInfo(INFO_CART_PRODUCT_EXIT);
            } else {
                data.number = number;
                alertSuccess(SUCCESS_CART_UPDATE_NUMBER_PRODUCT);
            }
            check = false;
        }
        return data;
    })
    if(check) {
        cart.push({id, number});
        alertSuccess(SUCCESS_CART_ADD_PRODUCT);
    }
    localStorage.setItem("cart", JSON.stringify(cart));
    viewNumberCart();
}

function viewNumberCart() {
    let arrProduct = getItemLocalStorage("cart");
    let count = 0;
    if(arrProduct && arrProduct.length > 0) {
        arrProduct.map(data => {
            count += parseInt(data.number);
        })
    }
    $("#slsp-s").html(count);
    handleAnimationCart();
}

function handleAnimationCart() {
    $(".gioHang .animate__animated").addClass("animate__wobble");
    setTimeout(function () {
        $(".gioHang .animate__animated").removeClass("animate__wobble");
    },1000);
}
//end_cart

function runInputSpinner() {
    $("input[type='number']").inputSpinner();
}

function countCost(arrCart) {
    let totalCost = 0;
    arrCart.map(data => {
        if(data.number > 0) {
            let {minusPrice} = viewPromotionCostProduct(data.promotions, data.cost);
            totalCost += (data.cost - minusPrice) * data.number;
        }
    })
    sumTotal.html(totalCost == 0 ? "" : viewPriceVND(totalCost));
    tempSumTotal.html(totalCost == 0 ? "" : viewPriceVND(totalCost));
    return totalCost;
}

function checkItemCart() {
    let cart= getItemLocalStorage("cart");
    return cart && cart.length > 0;
}

function getListProduct(rs) {
    return rs.map(data => {
        let {minusPrice, viewTitleGift, viewDiscount} = viewPromotionCostProduct(data.promotions, data.cost);
        let productClone = productTemp.clone();
        productClone.removeClass("d-none");
        productClone.find(".product-link").attr("href", viewAliasProduct(data.alias, data.id));
        let productImg = productClone.find(".product-img");
        productImg.attr("src", viewSrcFile(data.image));
        productImg.attr("alt", viewField(data.name));
        productClone.find(".product-name").html(viewField(data.name));
        if(viewDiscount.length > 0) {
            productClone.find(".promo-sale").html(viewDiscount);
        } else {
            productClone.find(".promo-sale").remove();
        }
        if(viewTitleGift.length > 0) {
            productClone.find(".promo-gift").attr("title", viewTitleGift);
        } else {
            productClone.find(".promo-gift").remove();
        }
        let productPriceClone = productClone.find(".product-price");
        if(data.cost != 0) {
            if(minusPrice === 0) {
                productPriceClone.find("del").remove();
                productPriceClone.find("span").text(viewPriceVND(data.cost));
            } else {
                productPriceClone.find("del").text(viewPriceVND(data.cost));
                productPriceClone.find("span").text(viewPriceVND(data.cost - minusPrice));
            }
            productClone.find(".btn").attr("data-id", data.id);
        } else {
            productPriceClone.find("del").remove();
            productPriceClone.find("span").text("Liên Hệ");
            productClone.find(".btn-add-cart").prop('disabled', true);
        }
        if(data.quantity <= 0) {
            productClone.find(".btn-add-cart").prop('disabled', true).html("Liên Hệ");
        }
        productClone.removeAttr("id");
        return productClone;
    })
}

function runToast(text, heading) {
    $.toast({
        text: text,
        heading: heading,
        showHideTransition: 'plain',
        icon: 'success',
        allowToastClose: true,
        hideAfter: 3000,
        stack: 5,
        position: { bottom: 85, left: 10 },
        textAlign: 'left',
        loader: true,
        loaderBg: '#fff',
        bgColor: '#fff',
        textColor: '#404040',
        textAlign: 'left',
        loader: false,
    });
}

function activeToast(listProductName) {
    setInterval(function (){
        runToast(listProductName[Math.floor(Math.random() * listProductName.length)],LIST_CUSTOMER[Math.floor(Math.random() * LIST_CUSTOMER.length)]);
    },TIME_RUN_TOAST);
}