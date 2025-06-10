var textName, textAge, textPhone, selectCoSo, selectBS, timePicker, datePicker, textAddress, textareaReason;

$(function (){
    textName = $("#text-name");
    textAge = $("#text-age");
    textPhone = $("#text-phone");
    selectCoSo = $("#select-coso");
    selectBS = $("#select-bs");
    timePicker = $("#time-picker");
    datePicker = $("#date-picker");
    textAddress = $("#text-address");
    textareaReason = $("#textarea-reason");

    viewSelectCoSo();
    viewSelectBacSi();
})

function checkThongTinTen() {
    let rs = false;
    let size = 50;
    let val = textName.val();
    if (regexTen(val) && checkSize(size,val)) {
        rs = true;
        hiddenError(textName);
    } else viewError(textName,INVALID_NAME);
    return {check : rs, val: val};
}

function checkThongTinSDT() {
    let rs = false;
    let val = textPhone.val();
    if (regexDienThoai(val)) {
        rs = true;
        hiddenError(textPhone);
    } else viewError(textPhone,INVALID_PHONE_NUMBER);
    return {check : rs, val: val};
}

function checkThongTinDiaChi() {
    let rs = false;
    let size = 100;
    let val = textAddress.val();
    if ((regexTen(val) && checkSize(size,val)) || val.length === 0) {
        rs = true;
        hiddenError(textAddress);
    } else viewError(textAddress,INVALID_ADDRESS);
    return {check : rs, val: val};
}

function checkNote() {
    let rs = false;
    let size = 255;
    let val = textareaReason.val();
    if (checkSize(size,val) || val.length === 0) {
        rs = true;
        hiddenError(textareaReason);
    } else viewError(textareaReason,INVALID_TEXTAREA_NOTE);
    return {check : rs, val: val};
}

function checkDate() {
    let rs = false;
    let val = datePicker.val();
    if (val.length > 0 && regexDate(val)) {
        rs = true;
    } else {alertDanger(INVALID_INPUT_DATE)}
    return {check : rs, val: val};
}

function checkTime() {
    let rs = false;
    let val = timePicker.val();
    if (val.length > 0) {
        rs = true;
    } else {{alertDanger(INVALID_INPUT_TIME)}}
    return {check : rs, val: val};
}

function clickDatLichKham()  {
    let {check: cTen,val: vTen} = checkThongTinTen();
    let {check: cSDT,val: vSDT} = checkThongTinSDT();
    let {check: cDiaChi,val: vDiaChi} = checkThongTinDiaChi();
    let {check: cNote,val: vNote} = checkNote();
    let {check: cDate, val: vDate} = checkDate();
    let {check: cTime, val: vTime} = checkTime();
    if(cTen && cSDT && cDiaChi && cNote && cDate && cTime) {
        let valBacSi = selectBS.val();
        vDate = vDate.trim().split("/").reverse().join("-");
        let longDate = new Date(vDate + " " + vTime).getTime();
        let longDateNow = new Date().getTime();
        if(longDate > longDateNow) {
            loadingBtn();
            valBacSi = valBacSi.length === 0 ? null: valBacSi;
            let appointment = {
                address: vDiaChi,
                gender: $("input[name='sex']").val(),
                name: vTen,
                personId: valBacSi,
                phone: vSDT,
                reason: vNote,
                status: 1,
                time: longDate
            }
            appointmentUpload(appointment, selectCoSo.val()).then(rs => {
                if(rs) {
                    textName.val("");
                    textPhone.val("");
                    textAddress.val("");
                    textareaReason.val("");
                }
                alertSuccess(SUCCESS_UPLOAD_APPOINTMENT);
            }).catch(err => {
                console.log(err);
                alertDanger(DANGER_UPLOAD_APPOINTMENT);
            }).finally(() => {
                hiddenLoadingBtn();
            })
        } else {
            alertInfo(INVALID_INPUT_VALUE_DATE);
        }
    }
}

function viewSelectCoSo() {
    brachFindByCompany(COMPANY_ID).then(rs => {
        if(rs) {
            let listOption = rs.map(data => {
                let option = selectCoSo.find("option").clone().removeClass("d-none");
                option.val(data.id);
                option.html(`${viewField(data.name)} - ${viewField(data.address)}`);
                return option;
            })
            selectCoSo.html(listOption);
        }
    }).catch(err => {
        console.log(err);
        alertDanger(DANGER_BRANCH_COMPANY);
    })
}

function viewSelectBacSi() {
    lecturerFindByCompany(COMPANY_ID).then(rs => {
        if(rs) {
            let listOption = rs.map(data => {
                let option = selectBS.find("option").clone();
                option.val(data.id);
                option.html(`${viewField(data.name)}`);
                return option;
            })
            selectBS.append(listOption);
        }
    }).catch(err => {
        console.log(err);
        alertDanger(DANGER_LECTURER);
    })
}