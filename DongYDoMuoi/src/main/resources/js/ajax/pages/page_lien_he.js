let textName;
let textPhoneNumber;
let textEmail;
$(function () {
    hiddenNavHero();
    textName = $("#input-name");
    textPhoneNumber = $("#input-phone");
    textEmail = $("#input-email");
    let branchListSelector = $('#branches-list');
    // InfoAdmin.fillInfo();
    InfoCustomer.init();
    // initCompanyInfo(branchListSelector).then();
    initBranchInfo(branchListSelector).then();
});
let InfoAdmin = {
    getCompanyInfo: async function () {
        let data;
        data = await Promise.resolve(companyFindById(COMPANY_ID)).then((rs) => rs);
        return data;
    },
    fillInfo: function () {
        this.getCompanyInfo().then((rs) => {
            $("#name-company").text(rs.nameCompany);
            $("#address-company").html(`<strong>Địa chỉ:</strong> ${rs.address}`);
            // $("#phonenumber-company").html(
            //     `<strong>Số điện thoại:</strong> 0981.044.566 - 0916.688.211`
            // );
            $("#email-company").html(
                `<strong>Email:</strong> <a href="#"> ${rs.email}</a>`
            );
            $("#website-company").html(
                ` <strong>Website:</strong> <a href="https://www.${rs.website}">${rs.website} </a>`
            );
            $("#map__iframe").attr("src", rs.map);
        });
    },
};
let InfoCustomer = {
    init: function () {
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
    } else viewError(textName, INVALID_NAME);
    return {check: rs, val: val};
}

function checkThongTinSDT() {
    let rs = false;
    let val = textPhoneNumber.val();
    if (regexDienThoai(val)) {
        rs = true;
        hiddenError(textPhoneNumber);
    } else viewError(textPhoneNumber, INVALID_PHONE_NUMBER);
    return {check: rs, val: val};
}

function checkThongTinEmail() {
    let rs = false;
    let val = textEmail.val();
    if (regexEmail(val)) {
        rs = true;
        hiddenError(textEmail);
    } else viewError(textEmail, INVALID_EMAIL);
    return {check: rs, val: val};
}


//thong tin cong ty
function CompanyInfoController(){
    this.getCompanyInfo = function () {
        return companyFindById(COMPANY_ID);
    };

    this.generateCompanyTemplate = function (data) {
        let template = $('#hiddenBranchElement').clone().removeAttr('id').removeClass('d-none');
        template.find('.branches__element--name').text(data.nameCompany);
        template.find('h4').text(data.address);
        template.find('p').html(`<span>Điện thoại: </span><a href="tel:${data.phone}">${data.phone}</a>`);
        template.find('.branches__element--map iframe').attr('src', data.map);
        return template;
    };

    this.mappingCompanyInfo = function (data, branchListSelector, callback) {
        let listTemplate;
        console.log(data);
        if(callback){
            listTemplate = callback(data);
        }
        $('#branches-list').prepend(listTemplate);
    }
}
async function initCompanyInfo(companyListSelector){
    let companyInfoController = new CompanyInfoController();
    let listData;
    listData = await companyInfoController.getCompanyInfo()
        .then(rs=>rs)
        .catch(err=>console.log(err));
    companyInfoController.mappingCompanyInfo(listData, companyListSelector, companyInfoController.generateCompanyTemplate);

}
// end thong tin cong ty

//thong tin chi nhanh

function BranchInfoController() {
    this.getListBranchInfo = function () {
        return ajaxGet(`infor-system-service/api/v1/public/branches/company/${COMPANY_ID}`);
    };
    this.generateBranchTemplate = function (data) {
        let template = $('#hiddenBranchElement').clone().removeAttr('id').removeClass('d-none');
        template.find('.branches__element--name').text(data.name);
        template.find('h4').text(data.address);
        template.find('p').html(`<span>Điện thoại: </span><a href="tel:${data.phone}">${data.phone}</a>`);
        template.find('.branches__element--map iframe').attr('src', data.map);
        return template;
    };
    this.mappingBranchInfo = function (listData, branchListSelector, callback) {
        let listTemplate;
        listTemplate = listData.map(rs1 => {
            let template = '';
            if(callback){
                template = callback(rs1);
            }
            return template;
        });
        $('#branches-list').append(listTemplate);
    }
}
async function initBranchInfo(branchListSelector){
    let branchInfoController = new BranchInfoController();
    let listData;
    let listTemplateData;
    listData = await branchInfoController.getListBranchInfo()
        .then(rs=>rs)
        .catch(err=>console.log(err));
    branchInfoController.mappingBranchInfo(listData, branchListSelector, branchInfoController.generateBranchTemplate);

}

//thong tin chi nhanh
