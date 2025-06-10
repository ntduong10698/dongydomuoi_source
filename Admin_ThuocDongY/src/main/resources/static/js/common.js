const tokenHeader_value = getCookie("Authorization");
var perfixPrintThis = "/";

const COMPANY_ID = 3;

const MAIN_SERVICE = "api/";

// const PRODUCT_SERVICE = "https://dev.bksoftwarevn.com/product-service/api/";
//fix_url
const PRODUCT_SERVICE = MAIN_SERVICE;

// const BILL_SERVICE = "https://dev.bksoftwarevn.com/bill-service/api/";
//fix_url
const BILL_SERVICE = MAIN_SERVICE;

// const EDU_SERVICE = "https://dev.bksoftwarevn.com/edu-service/api/";
//fix_url
const EDU_SERVICE = MAIN_SERVICE;

// const NEWS_SERVICE = "https://dev.bksoftwarevn.com/news-service/api/";
//fix_url
const NEWS_SERVICE = `${MAIN_SERVICE}news/`;

// const MARKETING_SERVICE = "https://dev.bksoftwarevn.com/marketing-service/api/";
//fix_url
const MARKETING_SERVICE = MAIN_SERVICE;

// const USER_SERVICE = "https://dev.bksoftwarevn.com/user-service/api/";
//fix_url
const USER_SERVICE = MAIN_SERVICE;

// const IS_SERVICE = "https://dev.bksoftwarevn.com/infor-system-service/api/";
//fix_url
const IS_SERVICE = MAIN_SERVICE;

// const UPLOAD_SERVICE = "https://cdn.bksoftwarevn.com/bk_cdn/api/";
//fix_url
const UPLOAD_SERVICE = MAIN_SERVICE;

// const DOWNLOAD_PREFIX = 'https://cdn.bksoftwarevn.com/resources/micro-upload/dong-y/'
//fix_url
const DOWNLOAD_PREFIX = 'https://cdn.dongydomuoi.com/resources/micro-upload/dong-y/'

//alias

//alias danh mục
const CATEGORY_ALIAS = 16;
const CATEGORY_ALIAS_PATH = "danh-muc";

//alias sản phẩm
const PRODUCT_ALIAS = 14;
const PRODUCT_ALIAS_PATH = "chi-tiet-san-pham";

//alias giới thiệu
const INTRODUCTION_ALIAS = 17;
const INTRODUCTION_ALIAS_PATH = "chi-tiet-gioi-thieu";

//alias nghiên cứu
const RESEARCH_ALIAS = 16;
const RESEARCH_ALIAS_PATH = "chi-tiet-nghien-cuu";

//alias tuyển dụng
const RECRUITMENT_ALIAS = 18;
const RECRUITMENT_ALIAS_PATH = "chi-tiet-tuyen-dung";

//alias bài thuốc
const REMEDY_ALIAS = 19;
const REMEDY_ALIAS_PATH = "chi-tiet-bai-thuoc";


//thong bao
function notify(mess) {
    $.notify(mess,
        {
            style: 'bootstrap',
            position: 'top-right'
        });
}

function getCookie(name) {
    let match = document.cookie.match(new RegExp('(^| )' + name + '=([^;]+)'));
    if (match) {
        return match[2];
    } else {
        return null;
        console.log('--something went wrong---');
    }
}

function success(mess) {
    $.notify(mess, "success", {
        showAnimation: "slideUp",
        hideAnimation: "slideToggle"
    });
}

function warning(mess) {
    $.notify(mess, "warn", {
        showAnimation: "slideUp",
        hideAnimation: "slideToggle"
    });
}

function error(mess) {
    $.notify(mess, "error", {
        showAnimation: "slideUp",
        hideAnimation: "slideToggle"
    });
}

function info(mess) {
    $.notify(mess, "info", {
        showAnimation: "slideUp",
        hideAnimation: "slideToggle"
    });
}

function convertDate(str) {
    let part = str.split('/');
    return part[2] + '/' + part[1] + '/' + part[0];
}

function getSizeImage(url, callback) {
    let data;
    let img = new Image();
    img.src = url;
    img.onload = function () {
        data = {
            width: this.width,
            height: this.height
        };
        callback(data);
    };
}

async function callGetAjax(service, uri) {
    //console.log(tokenHeader_value)
    if (uri.includes("?"))
        uri = uri.concat("&token=" + tokenHeader_value)
    else uri = uri.concat("?token=" + tokenHeader_value)
    let data;
    await $.ajax({
        type: "GET",
        url: service + uri,
        cache: false,
        success: function (result) {
            if (result.success === true)
                data = result;
        },
        error: function (error) {
            console.log(error);
            console.log("error get");
        }
    });
    return data;
}

async function callPostAjax(service, uri, payload) {
    if (uri.includes("?"))
        uri = uri.concat("&token=" + tokenHeader_value)
    else uri = uri.concat("?token=" + tokenHeader_value)
    let data;
    await $.ajax({
        type: "POST",
        url: service + uri,
        contentType: "application/json",
        data: JSON.stringify(payload),
        cache: false,
        success: function (result) {
            data = result;
        },
        error: function (error) {
            console.log(error);

        }
    });
    return data;
}

async function callPutAjax(service, uri, payload) {
    if (uri.includes("?"))
        uri = uri.concat("&token=" + tokenHeader_value)
    else uri = uri.concat("?token=" + tokenHeader_value)
    let data;
    await $.ajax({
        type: "PUT",
        url: service + uri,
        contentType: "application/json",
        data: JSON.stringify(payload),
        success: function (result) {
            data = result;
        },
        error: function (error) {
            console.log(error);
        }
    });
    return data;
}

async function callDeleteAjax(service, uri) {
    if (uri.includes("?"))
        uri = uri.concat("&token=" + tokenHeader_value)
    else uri = uri.concat("?token=" + tokenHeader_value)
    let data;
    await $.ajax({
        type: "DELETE",
        url: service + uri,
        success: function (result) {
            data = result;
        },
        error: function (error) {
            console.log(error);
            console.log(this.url);
        }
    });
    return data;
}

async function callUploadFile(file, wrap = false, label = "") {
    let data;
    await $.ajax({
        type: "POST",
        url: UPLOAD_SERVICE + "v1/public/upload/company/" + COMPANY_ID,
        data: file,
        cache: false,
        contentType: false,
        enctype: 'multipart/form-data',
        processData: false,
        success: function (result) {
            data = result;
        },
        error: function (err) {
            console.log(err);
        }
    });

    if (wrap){
        return {
            label: label,
            file: data.data[0].uri
        }
    }

    return data;
}

async function callUploadLogo(logo) {
    let data;
    await $.ajax({
        type: "POST",
        url: UPLOAD_SERVICE + "v1/admin/upload/logo/company/" + COMPANY_ID,
        data: logo,
        cache: false,
        contentType: false,
        enctype: 'multipart/form-data',
        processData: false,
        success: function (result) {
            data = result;
        },
        error: function (err) {
            console.log(err);
        }
    });
    return data;
}

function clickPrintElement(selector) {
    $('#btn-print').on("click", function () {
        $(selector).printThis({
            importCSS: true,
            printDelay: 333,
        });
    });
}

function imageHandler(editor) {
    //create tempo input
    const input = document.createElement('input');
    input.setAttribute('type', 'file');
    input.click();
    // Listen upload local image and save to server
    input.onchange = () => {
        const file = input.files[0];
        // file type is only image.
        if (/^image\//.test(file.type)) {
            let formData = new FormData();
            formData.append("files", file);
            callUploadFile(formData).then(rs => {
                //insert image to editor
                const range = editor.getSelection();
                editor.insertEmbed(range.index, 'image', DOWNLOAD_PREFIX + rs.data[0].uri);
            }).catch(ex => {
                let reader = new FileReader();
                reader.onload = function (e) {
                    const range = editor.getSelection();
                    editor.insertEmbed(range.index, 'image', e.target.result);
                };
                reader.readAsDataURL(input.files[0]);
            })
        } else {
            warning('Bạn chỉ được phép tải lên ảnh');
        }
    };

}

function initQuill(selector) {
    //quill editor
    let toolbarOptions = [
        ['bold', 'italic', 'underline', 'strike'],
        [{'font': []}],
        [{'align': []}],
        [{'size': []}],
        [{'color': []}],
        [{'header': [1, 2, 3, 4, false]}],
        ['blockquote', 'code-block'],
        ['link', 'image'],
        [{'list': 'ordered'}, {'list': 'bullet'}],
        [{'indent': '-1'}, {'indent': '+1'}],

    ];
    let editor = new Quill(selector, {
        modules: {
            imageResize: {
                displaySize: true
            },
            toolbar: toolbarOptions
        },
        theme: 'snow',
    })
    editor.getModule("toolbar").addHandler("image", () => imageHandler(editor));
    return editor;
}

async function callUploadExcel(url, file) {
    let data;
    await $.ajax({
        type: "POST",
        url: link + url,
        data: file,
        cache: false,
        contentType: false,
        enctype: 'multipart/form-data',
        processData: false,
        success: function (result) {
            data = result.data;
        },
        error: function (err) {
            console.log(err);
        }
    });
    return data;
}

function stringToDate(str) {
    let part = str.split('/');
    return part[0] + '-' + part[1] + '-' + part[2];
}

function previewImage(input, imgId) {
    if (input.files && input.files[0]) {
        let reader = new FileReader();
        reader.onload = function (e) {
            $(imgId).show();
            $(imgId).attr('src', e.target.result);
        };
        reader.readAsDataURL(input.files[0]);
    }
}


function distinct(array) {
    return array.filter((element, index) => {
        const json = JSON.stringify(element);
        return index === array.findIndex(obj => JSON.stringify(obj) === json);
    })
}

function runSelect2() {
    $('.select2bs4').select2({width: 'resolve'});
}

function viewDateTextVn(text, isStartTime) {
    if (text) {
        let date = new Date(text);
        let hours = date.getUTCHours() < 10 ? '0' + date.getUTCHours() : date.getUTCHours();
        let minutes = date.getUTCMinutes() < 10 ? '0' + date.getUTCMinutes() : date.getUTCMinutes();

        if (isStartTime) {
            return `${date.getUTCDate()}/${date.getUTCMonth() + 1}/${date.getFullYear()} - ${hours}:${minutes}`;
        } else {
            return `${date.getUTCDate()}/${date.getUTCMonth() + 1}/${date.getUTCFullYear()}`;
        }
    } else {
        return '';
    }
}

function viewDateTime(date) {
    date = new Date(date);
    let day = date.getDate();
    if (day < 10) day = '0' + day;
    let month = date.getMonth() + 1;
    if (month < 10) month = '0' + month;

    let hours = date.getHours();
    if (hours < 10) hours = '0' + hours;
    let minutes = date.getMinutes();
    if (minutes < 10) minutes = '0' + minutes;
    let seconds = date.getSeconds();
    if (seconds < 10) seconds = '0' + seconds;

    date = `${day}/${month}/${date.getFullYear()} ${hours}:${minutes}:${seconds}`;
    return date;
}

//format money
function formatNumber(nStr, decSeperate = ',', groupSeperate = ".") {
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

function viewSourceFile(url) {
    if (url.startsWith("http") || url.startsWith("www"))
        return url;
    return DOWNLOAD_PREFIX + url;
}

function getStreamURL(fileType, fileName) {
    return UPLOAD_SERVICE + `v1/public/stream/company/${COMPANY_ID}/${fileType}?fileName=${encodeURIComponent(fileName)}`;
}

function to_slug(str) {
    // Chuyển hết sang chữ thường
    str = str.toLowerCase();

    // xóa dấu
    str = str.replace(/(à|á|ạ|ả|ã|â|ầ|ấ|ậ|ẩ|ẫ|ă|ằ|ắ|ặ|ẳ|ẵ)/g, 'a');
    str = str.replace(/(è|é|ẹ|ẻ|ẽ|ê|ề|ế|ệ|ể|ễ)/g, 'e');
    str = str.replace(/(ì|í|ị|ỉ|ĩ)/g, 'i');
    str = str.replace(/(ò|ó|ọ|ỏ|õ|ô|ồ|ố|ộ|ổ|ỗ|ơ|ờ|ớ|ợ|ở|ỡ)/g, 'o');
    str = str.replace(/(ù|ú|ụ|ủ|ũ|ư|ừ|ứ|ự|ử|ữ)/g, 'u');
    str = str.replace(/(ỳ|ý|ỵ|ỷ|ỹ)/g, 'y');
    str = str.replace(/(đ)/g, 'd');

    // Xóa ký tự đặc biệt
    str = str.replace(/([^0-9a-z-\s])/g, '');

    // Xóa khoảng trắng thay bằng ký tự -
    str = str.replace(/(\s+)/g, '-');

    // xóa phần dự - ở đầu
    str = str.replace(/^-+/g, '');

    // xóa phần dư - ở cuối
    str = str.replace(/-+$/g, '');

    // return
    return str;
}

function autoAddAltImg(html) {
    let content = $(html);
    content.find("img").each(function () {
        let url = $(this).attr('src');
        let urlPart = [];
        urlPart = url.split("/");
        let fileName = urlPart[urlPart.length - 1];
        $(this).attr('alt', fileName.split('.')[0]);
    })
    let str = $("<div />").append(content.clone()).html();
    return str;
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

async function checkAlias(alias) {
    let data;
    await callGetAjax(IS_SERVICE, `v1/public/url-alias/check-exist?company-id=${COMPANY_ID}&alias=` + alias)
        .then(rs => {
            data = rs.data;
        }).catch(ex => error("Có lỗi xảy ra!"));
    return data;
}