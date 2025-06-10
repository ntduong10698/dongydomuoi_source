let company = null;
let branchs = [];
let infos = [];
let banks = [];
let editor;

$(function () {
    editor = initQuill('#input-working-time');
    getCompanyInfo();
    changeCompany();

    getAllBranchs();
    addBranch();

    getInfos();
    changeInfos();

    getBanks();
    changeBanks();
})

//company
function getCompanyInfo() {
    callGetAjax(IS_SERVICE, 'v1/public/companies/' + COMPANY_ID)
        .then(rs => {
            //fill data;
            company = rs.data;
            $('#input-name').val(company.nameCompany);
            $('#input-slogan').val(company.slogan)
            $('#input-address').val(company.address);
            $('#input-email').val(company.email);
            $('#input-tax').val(company.taxNumber);
            $('#input-map').val(company.map);
            $('#input-description').val(company.description);
            editor.root.innerHTML = company.workingTime;

        }).catch(ex => error("Tải thông tin thất bại"));
}

function changeCompany() {
    $('#submit-company').on('click', function () {
        if (!checkCompanyInput())
            return;
        company.nameCompany = $('#input-name').val();
        company.address = $('#input-address').val();
        company.email = $('#input-email').val();
        company.slogan = $('#input-slogan').val();
        company.taxNumber = $('#input-tax').val();
        company.description =  $('#input-description').val();
        company.map = $('#input-map').val();
        company.workingTime = editor.root.innerHTML;

        callPutAjax(IS_SERVICE, 'v1/admin/company', company)
            .then(rs => {
                success("Cập nhật thông tin thành công");
            }).catch(ex => {
            console.log(ex);
            error("Có lỗi xảy ra");
        })
    })
}

function checkCompanyInput() {
    let count = 3;

    if ($('#input-name').val().length === 0) {
        info("Vui lòng điền tên doanh nghiệp");
        count--;
    }

    if ($('#input-address').val().length === 0) {
        info("Vui lòng điền địa chỉ");
        count--;
    }

    if ($('#input-map').val().length === 0) {
        info("Vui lòng điền đường dẫn google map");
        count--;
    }

    return count === 3;
}

//branch
function getAllBranchs() {
    callGetAjax(IS_SERVICE, 'v1/public/branches/company/' + COMPANY_ID)
        .then(rs => {
            branchs = rs.data;
            //fill data
            $('#row-branch').html(branchs.map((branch, index) => `<tr>
                                                            <td>${index + 1}</td>
                                                            <td>${branch.name}</td>
                                                            <td class="text-left">${branch.address}</td>
                                                            <td>${branch.phone ? branch.phone : ''}</td>
                                                             <td style="max-width: 6%">
                                    <div class="dropdown">
                                        <button id="menu-drop-1" type="button" class="btn btn-action dropdown-toggle" style="height: 30px"
                                                data-toggle="dropdown">
                                            <i class="fas fa-cog"></i>
                                        </button>
                                        <ul class="dropdown-menu" aria-labelledby="menu-drop-1" style="">
                                            <li>
                                                <button class="drop-branch-1 btn btn-action font-weight-normal col-lg-12"
                                                        style="text-align: left" index="${branch.id}" data-toggle="modal"
                                                        data-target="#branch-modal">
                                                        <i class="fas fa-pen"></i>&nbsp;&nbsp;Chỉnh sửa
                                                </button>
                                            </li>
                                                                                                                                
                                            <li>
                                                <button class="drop-branch-2 btn btn-action font-weight-normal col-lg-12"
                                                        style="text-align: left" index="${branch.id}" data-toggle="modal"
                                                        data-target="#delete-modal">
                                                    <i class="fas fa-trash-alt"></i>&nbsp;&nbsp;Xóa
                                                </button>
                                            </li>
                                        </ul>
                                    </div>
                                </td> 
                                                         </tr>`).join(''));
            changeBranch();
            deleteBranch();
        }).catch(ex => error("Tải thông tin chi nhánh lỗi"));
}

function addBranch() {
    $('#add-branch').on('click', function () {
        //clear
        $('#input-branch-name').val('')
        $('#input-branch-address').val('')
        $('#input-branch-phone').val('')
        $('#input-branch-map').val('')

        //new
        $('#submit').off('click').on('click', function () {
            if (!checkInputBranch())
                return;
            let branch = {
                name: $('#input-branch-name').val(),
                address: $('#input-branch-address').val(),
                phone: $('#input-branch-phone').val(),
                map: $('#input-branch-map').val()
            }

            callPostAjax(IS_SERVICE, 'v1/admin/branch?company-id=' + COMPANY_ID, branch)
                .then(rs => {
                    success("Thêm mới chi nhánh thành công");
                    $('#branch-modal').modal('hide');
                    getAllBranchs();
                }).catch(ex => error("Thêm mới chi nhánh lỗi"))
        })
    })
}

function checkInputBranch() {
    let count = 2;

    if ($('#input-branch-name').val().length === 0) {
        info("Vui lòng điền tên doanh nghiệp");
        count--;
    }

    if ($('#input-branch-address').val().length === 0) {
        info("Vui lòng điền địa chỉ");
        count--;
    }
    return count === 2;
}

function changeBranch() {
    $('.drop-branch-1').on('click', function () {
        //get data
        let id = $(this).attr("index");
        let branch = branchs.filter(b => b.id == id)[0];
        $('#input-branch-name').val(branch.name)
        $('#input-branch-address').val(branch.address)
        $('#input-branch-phone').val(branch.phone)
        $('#input-branch-map').val(branch.map)

        $('#submit').off('click').on('click', function () {
            if (!checkInputBranch())
                return;

            branch.name = $('#input-branch-name').val()
            branch.address = $('#input-branch-address').val()
            branch.phone = $('#input-branch-phone').val()
            branch.map = $('#input-branch-map').val()

            callPutAjax(IS_SERVICE, 'v1/admin/branch', branch)
                .then(rs => {
                    success("Chỉnh sửa chi nhánh thành công");
                    $('#branch-modal').modal('hide');
                    getAllBranchs();
                }).catch(ex => error("Chỉnh sửa chi nhánh lỗi"))
        })
    })
}

function deleteBranch() {
    $('.drop-branch-2').on('click', function () {
        //get data
        let id = $(this).attr("index");
        $('#submit-delete').off('click').on('click', function () {
            callDeleteAjax(IS_SERVICE, 'v1/admin/branch/' + id)
                .then(rs => {
                    success("Đã xóa chi nhánh");
                    $('#delete-modal').modal('hide');
                    getAllBranchs();
                }).catch(ex => error("Xóa chi nhánh thất bại"));
        })
    })
}

//infos
function getInfos() {
    callGetAjax(IS_SERVICE, 'v1/public/infos/company/' + COMPANY_ID)
        .then(rs => {
            //fill table
            infos = rs.data;
            let content = rs.data.map((info, index) => `<tr>
                                        <td>${index + 1}</td>
                                        <td>${info.name}</td>
                                        <td><input class="form-control info-content" value="${info.value}"></td>
                                       
                                    </div>
                                </td> 
                                    </tr>`).join('');
            $('#row-info').html(content);
        }).catch(err => {
        console.log(err)
        error("Tải thông tin liên hệ thất bại")
    })
}

function changeInfos() {
    $('#submit-info').on('click', function () {
        $('.info-content').each((index, content) => {
            infos[index].value = $(content).val();
        })
        callPutAjax(IS_SERVICE, 'v1/admin/infos', infos)
            .then(rs => {
                success("Cập nhật thành công");
            }).catch(ex => {
            console.log(ex);
            error("Cập nhật thất bại");
        })
    })
}

//banks
function getBanks() {
    callGetAjax(IS_SERVICE, 'v1/public/banks/company/' + COMPANY_ID)
        .then(rs => {
            //fill table
            banks = rs.data;
            fillBanks();
        }).catch(err => {
        console.log(err)
        error("Tải thông tin ngân hàng thất bại")
    })
}

function fillBanks() {
    let content = banks.filter(b => !b.deleted).map((bank, index) => `<tr>
                                        <td>${index + 1}</td>
                                        <td><input class="form-control bank-bank" value="${bank.bank}"></td>
                                        <td><input class="form-control bank-account" value="${bank.account}"></td>
                                        <td><input class="form-control bank-bankNumber" value="${bank.bankNumber}" type="number"></td>
                                        <td>
                                        <button class="btn btn-danger btn-delete" index="${bank.id}">
                                            <i class="fa fa-trash"></i>
                                        </button>
                                        </td>
                                    </div>
                                </td> 
                                    </tr>`).join('');
    $('#row-bank').html(content);
    $('.btn-delete').off('click').on('click', function () {
        let id = $(this).attr("index");
        let bank = banks.filter(bank => bank.id == id)[0]
        bank.deleted = true;
        fillBanks();
    })
}

function changeBanks() {
    $('#add-bank').on('click', function () {
        let newBank = {
            id: Math.floor(Math.random() * 100001),
            bank: "Ngân hàng",
            account: "Tài khoản",
            bankNumber: "00000",
            deleted: false,
            newBank: true,
            company: company
        }
        banks.push(newBank);
        //fill table
        fillBanks();
    })

    $('#submit-bank').on('click', function () {
        $('.bank-bank').each((index, select) => {
            banks[index].bank = $(select).val();
        })
        $('.bank-account').each((index, select) => {
            banks[index].account = $(select).val();
        })
        $('.bank-bankNumber').each((index, select) => {
            banks[index].bankNumber = $(select).val();
        })

        banks.filter(b => b.newBank).forEach(b => b.id = null);

        callPutAjax(IS_SERVICE, 'v1/admin/banks', banks)
            .then(rs => {
                success("Cập nhật thành công");
                fillBanks();
            }).catch(ex => {
            console.log(ex);
            error("Cập nhật thất bại");
        })
    })
}