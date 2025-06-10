$(function () {
    getRobot()
});

function getRobot() {
    callGetAjax(UPLOAD_SERVICE, 'v1/admin/robot/company/' + COMPANY_ID)
        .then(rs => {
            $('#input-robot').val(rs.data);
        }).catch(e => {
        console.log(e)
        error("Tải dữ liệu thất bại")
    });

    $('#submit').on('click', function () {
        let text = $('#input-robot').val();
        callPutAjax(UPLOAD_SERVICE, 'v1/admin/upload/robot/company/' + COMPANY_ID, {content: text})
            .then(rs => {
                success("Cập nhật thành công");
            }).catch(ex => error("Cập nhật thất bại"))

    })

    $('#update-sitemap').on('click', function () {
        callPutAjax(IS_SERVICE, 'v1/private/site-map/' + COMPANY_ID, null)
            .then(rs => {
                success("Cập nhật sitemap thành công");
            }).catch(ex => error("Cập nhật thất bại"))

    })
}
