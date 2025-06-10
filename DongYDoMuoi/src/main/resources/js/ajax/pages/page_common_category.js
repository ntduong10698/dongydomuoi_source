let textName;
let textPhoneNumber;
let textEmail;

$(function () {
    hiddenNavHero();
    let parentSelector = $('#branch-info-box');
    let childSelector = $('#hidden-branchInfo');
    textName = $("#input-name");
    textPhoneNumber = $("#input-phone");
    textEmail = $("#input-email");
    InfoCustomer.init();
    InfoCompany.mappingTemplate(parentSelector, childSelector);

});


let InfoCompany = {
    getListInfoBranch: async function(){
        return brachFindByCompany(COMPANY_ID);
    },
    generateTemplateInfo: function(data, templateSelector, callback){
        let template = '';
        if(callback){
            template = callback(data, templateSelector);
        }
        return template;
    },
    mappingTemplate: function(parentSelector, childSelector){
        this.getListInfoBranch().then(rs=>{
            rs.forEach(rs1=>{
                let templateEl = InfoCompany.generateTemplateInfo(rs1, childSelector, function(rs1, childSelector){
                        return TemplateInfo.generateInfoBranchTemplate(rs1, childSelector)
                    }
                );
                $(parentSelector).append(templateEl);
            });
        })
    }
}
let TemplateInfo = {
    generateInfoBranchTemplate: function(data, selector){
        let template = $(selector).clone().removeAttr('id').removeClass('d-none');
        template.find('.branch-name').text(data.name);
        template.find('.branch-phone').html(`<a href="tel:${data.phone}">SĐT: ${data.phone}</a>`);
        return template;
    }
}

let InfoCustomer = {
    init: function () {
        this.resetInput();
        this.clickBtnSendInfo();
    },

    resetInput: function () {
        $('#input-name').val('');
        $('#input-email').val('');
        $('#input-phone').val('');
        $('#input-content').val('');
    },

    getInfo: function () {
        let customerInfo = {};
        customerInfo.name = $("#input-name").val();
        customerInfo.email = $("#input-email").val();
        customerInfo.phone = $("#input-phone").val();
        customerInfo.content = $("#input-content").val();
        customerInfo.companyId = COMPANY_ID;
        return customerInfo;
    },

    clickBtnSendInfo: function () {
        $("#btn-sendInfo")
            .off("click")
            .click(function () {
                let {check: cTen, val: vTen} = checkThongTinTen();
                let {check: cSDT, val: vSDT} = checkThongTinSDT();
                let {check: cEmail, val: vEmail} = checkThongTinEmail();
                let customerInfo = InfoCustomer.getInfo();
                if (cTen && cSDT && cEmail) {
                    $("#btn-sendInfo").text('Đang xử lý...').prop('disabled', true);
                    InfoSystemAPI.postInfo(customerInfo)
                        .then(rs1 => {
                            alertSuccess('Gửi thông tin liên hệ thành công');
                            $("#btn-sendInfo").text('Gửi đi').prop('disabled', false);
                            setTimeout(function () {
                                InfoCustomer.resetInput()
                            }, 2000);
                        }).catch(() => {
                        $("#btn-sendInfo").text('Gửi đi').prop('disabled', false);
                        alertWarning('Gửi thông tin thất bại');
                    });
                }
            });
    },
};

let InfoSystemAPI = {
    prefix: `infor-system-service/api/`,
    postInfo: function (data) {
        let uri = `${this.prefix}v1/public/contact`;
        return ajaxPost(uri, data);
    }
};


function checkThongTinTen() {
    let rs = false;
    let size = 50;
    let val = textName.val();
    if (regexTen(val) && checkSize(size, val)) {
        rs = true;
        hiddenError(textName);
    } else ViewInfo.viewError(textName, INVALID_NAME);
    return {check: rs, val: val};
}

function checkThongTinSDT() {
    let rs = false;
    let val = textPhoneNumber.val();
    if (regexDienThoai(val)) {
        rs = true;
        hiddenError(textPhoneNumber);
    } else ViewInfo.viewError(textPhoneNumber, INVALID_PHONE_NUMBER);
    return {check: rs, val: val};
}

function checkThongTinEmail() {
    let rs = false;
    let val = textEmail.val();
    if (regexEmail(val)) {
        rs = true;
        hiddenError(textEmail);
    } else ViewInfo.viewError(textEmail, INVALID_EMAIL);
    return {check: rs, val: val};
}
let ViewInfo = {
    viewError: function(selector, message){
        $(selector).addClass("is-invalid");
        $(selector).siblings(".invalid-feedback").text(`${message}`);
    }
}


