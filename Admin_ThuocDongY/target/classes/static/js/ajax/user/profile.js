$(function () {
    getProfile();
});

function regexDienThoai(soDienThoai) {
    return /((09|03|07|08|05)+([0-9]{8})\b)/g.test(soDienThoai)
}

function getProfile() {
    callGetAjax(USER_SERVICE, 'v1/user/profile')
        .then(rs => {
            let user = rs.data;
            $('#input-name').val(user.name);
            $('#input-email').val(user.email);
            $('#input-address').val(user.address);
            $('#input-phone').val(user.phone);

            $('#submit').on('click', function () {
                user.name = $('#input-name').val();
                user.address = $('#input-address').val();
                user.phone = $('#input-phone').val();
                if (regexDienThoai(user.phone))
                    callPutAjax(USER_SERVICE, 'v1/user/profile', user)
                        .then(rs => {
                            success("Cập nhật thành công");
                        }).catch(e => error("Cập nhật thất bại"))
                else info("Số điện thoại không đúng định dạng");
            })
        }).catch(ex => error("Tải thông tin lỗi"))
}