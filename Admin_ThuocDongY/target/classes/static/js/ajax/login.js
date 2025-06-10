$(function () {
    setEventLogin();
    setEventForget();
});

function setEventLogin() {
    $('.btn-login').click(function () {
       login();
    })

    $('body').keypress(function (e) {
        if (e.which == 13)
            login();
    })

    function login() {
        let username = $('#username');
        let password = $('#password');

        if (username.val().length === 0 || password.val().length === 0) {
            notify('Vui lòng điền đầy đủ thông tin');
        } else {
            let formLogin = {
                comId: COMPANY_ID,
                username: username.val(),
                password: password.val()
            };
            callPostAjax(USER_SERVICE,'v1/public/login-admin', formLogin)
                .then(rs => {
                    if (!rs) return;
                    document.cookie = `Authorization=${rs.data.code};max-age=`+rs.data.duration;
                    success("Đăng nhập thành công!");
                    location.href = "admin-don-hang";
                }).catch(ex => {
                console.log(ex);
                notify('Tài khoản hoặc mật khẩu không chính xác')
            })
        }
    }
}

function setEventForget() {
    $('#submit-forget').on('click', function () {
        $(this).prop('disabled', true);
        callGetAjax(USER_SERVICE, `v1/public/forget-password/${$('#input-email').val()}/company/${COMPANY_ID}`)
            .then(rs => {
                $(this).prop('disabled', false);
                success("Kiểm tra email để thay đổi mật khẩu");
                $('#forget-modal').modal('hide');
            }).catch(e => error("Có lỗi xảy ra"));
    })
}


