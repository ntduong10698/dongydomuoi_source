const URL_USER_SERIVCE = 'user-service/api/';

function userCheck(token) {
    return ajaxGet(`${URL_USER_SERIVCE}v1/public/check?token=${token}`);
}

function userPrivateGetProfile() {
    return ajaxGet(`${URL_USER_SERIVCE}v1/user/profile`);
}

function userPrivatePutProfile(appUser) {
    return ajaxGet(`${URL_USER_SERIVCE}v1/user/profile`, appUser);
}

function userPrivateChangePassword(form) {
    return ajaxGet(`${URL_USER_SERIVCE}v1/user/profile/password`, form);
}

function userGetFeedbackByCompany(id) {
    return ajaxGet(`${URL_USER_SERIVCE}v1/public/feedbacks/company/${id}`);
}

function userForgetPassword(email, id) {
    return ajaxGet(`${URL_USER_SERIVCE}v1/public/forget-password/${email}/company/${id}`);
}

function userLogin(form) {
    return ajaxPost(`v1/public/login`, form);
}

function userLoginAdmin(form) {
    return ajaxPost(`v1/public/login-admin`, form);
}

function userRegister(form) {
    return ajaxPost(`v1/public/register`, form);
}

function userForgetChangePassword(form) {
    return ajaxPost(`v1/public/password`, form);
}