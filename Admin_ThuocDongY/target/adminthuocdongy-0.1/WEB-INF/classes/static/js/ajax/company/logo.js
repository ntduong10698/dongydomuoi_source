$(function () {
    $('#choose-file').on('change', function () {
        previewImage(this, "#img-preview");
    })
    getLogo()
});

function getLogo() {
    callGetAjax(UPLOAD_SERVICE, 'v1/public/logo/company/' + COMPANY_ID)
        .then(rs => {
            let linkLogo = rs.data;
            $('#img-preview').attr('src', linkLogo);
        });

    $('#submit').on('click', function () {
        let formData = new FormData();
        formData.append("files", $('#choose-file').get(0).files[0])
        callUploadLogo(formData)
            .then(rs => {
                success("Thay đổi logo thành công");
            }).catch(e => {
            console.log(e)
            error("Có lỗi xảy ra")
        })
    })

}