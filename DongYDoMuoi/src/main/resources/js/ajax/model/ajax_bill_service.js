const URL_BILL_SERIVCE = 'bill-service/api/';

//BILL_API
function billCreate(bill) {
    return ajaxPost(`${URL_BILL_SERIVCE}v1/public/bill`, bill);
}

function billFindByCustomer(id, page = 1, size = 20) {
    return ajaxGet(`${URL_BILL_SERIVCE}v1/user/bills/customer/${id}?page=${page}&size=${size}`);
}
//END_BILL_API

//STATUS_BILL_API
function statusBillFindAll() {
    return ajaxGet(`${URL_BILL_SERIVCE}v1/public/statuses`);
}
//END_STATUS_BILL_API