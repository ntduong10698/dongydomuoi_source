$(function () {
    let url = new URL(window.location.href)
    let token = url.searchParams.get("token");

    if (token){
        $('#submit').on('click', function () {
            if ( $('#new-pass').val().length === 0 || $('#confirm-pass').val().length === 0) {
                info("Vui lòng điền đầy đủ các trường thông tin");
                return;
            }

            if ( $('#new-pass').val().length < 6){
                info("Mật khẩu cần tối thiểu 6 ký tự");
                return;
            }

            if ($('#new-pass').val() === $('#confirm-pass').val()) {
                callPutAjax(USER_SERVICE, 'v1/public/password?token='+token, {
                    oldPass: '',
                    newPass: $('#new-pass').val()
                }).then(rs => {
                    success("Đổi mật khẩu thành công");
                    location.href = "login";
                }).catch(e => error("Thay đổi mật khẩu thất bại"))
            } else
                info("Mật khẩu không trùng khớp");
        })
    }
})