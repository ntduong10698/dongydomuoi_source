$(function () {
    changeEvent();
})

function clearContent() {
    $('#old-pass').val('')
    $('#new-pass').val('')
    $('#confirm-pass').val('')
}

function changeEvent() {
    $('#submit').on('click', function () {

        if ($('#old-pass').val().length === 0 || $('#new-pass').val().length === 0 || $('#confirm-pass').val().length === 0) {
            info("Vui lòng điền đầy đủ các trường thông tin");
            return;
        }

        if($('#new-pass').val() == $('#old-pass').val()){
            info("Mật khẩu mới không được trùng với mật khẩu hiện tại");
            return;
        }

        if ( $('#new-pass').val().length < 6){
            info("Mật khẩu cần tối thiểu 6 ký tự");
            return;
        }

        if ($('#new-pass').val() === $('#confirm-pass').val()) {
            callPutAjax(USER_SERVICE, 'v1/user/profile/password', {
                oldPass: $('#old-pass').val(),
                newPass: $('#new-pass').val()
            }).then(rs => {
                clearContent();
                success("Đổi mật khẩu thành công");
            }).catch(e => error("Mật khẩu hiện tại không chính xác"))
        } else
            info("Mật khẩu không trùng khớp");
    })
}