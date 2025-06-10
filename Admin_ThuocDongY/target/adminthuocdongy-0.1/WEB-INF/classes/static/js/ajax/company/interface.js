let contents = [];

$(function () {
    $('#choose-file').on('change', function () {
        previewImage(this, '#img-preview');
    });
    $('#input-youtube').on('change', function () {
        try {
            if ($(this).val().includes("/embed/")) {
                $('#div-video').html(`<iframe style="max-width: 100%" src="${$(this).val()}"></iframe>`)
            } else {
                $('#div-video').html(``)
            }
        } catch (e) {

        }
    })
    getAllContents();
})

function getAllContents(modifier = "") {
    callGetAjax(IS_SERVICE, 'v1/public/contents/company/' + COMPANY_ID)
        .then(rs => {
            contents = rs.data;
            fillSelectPart(modifier);
        }).catch(ex => {
        console.log(ex);
        error("Tải dữ liệu thất bại")
    })
}

function fillSelectPart(modifier = "") {
    let options = contents.map(content => `<option value="${content.part.code}">${content.part.name}</option>`).join('');
    $('#select-part').html(options);
    $('#select-part').on('change', function () {
        let content = contents.filter(content => content.part.code == $(this).val())[0];
        let details = content.partDetails;
        fillDetails(details);
        if (content.part.code === "slide") {
            $('#type-slide').show();
            if (content.part.video)
                $('#slide-video').prop('checked', true);
            else $('#slide-image').prop('checked', true);

            $('#slide-video').off('click').on('click', function () {
                callPutAjax(IS_SERVICE, `v1/admin/part/${content.part.id}/video/true`)
                    .then(rs => {
                        success("Đã cập nhật")
                    }).catch(ex => error("Có lỗi xảy ra"));
            })
            $('#slide-image').off('click').on('click', function () {
                callPutAjax(IS_SERVICE, `v1/admin/part/${content.part.id}/video/false`)
                    .then(rs => {
                        success("Đã cập nhật")
                    }).catch(ex => error("Có lỗi xảy ra"));
            })
        } else $('#type-slide').hide();
    });
    $('#select-part').prop('selectedIndex', 0);
    if (modifier.length > 0)
        $('#select-part').val(modifier);
    else
        $('#select-part').prop('selectedIndex', 0);
    $('#select-part').trigger('change');
}

function fillDetails(details) {

    let rows = details.map((detail, index) => {
        let divImg = '';
        if (detail.url) {
            //img
            if (detail.type === 1 || detail.type === 4) {
                divImg = `<img width="200px" src="${viewSourceFile(detail.url)}"/>`;
            } else if (detail.url.startsWith("http")) {
                //youtube
                divImg = `<iframe style="width: 200px" src="${detail.url}"></iframe>`
            } else {
                //video
                divImg = `<video style="width: 200px" controls="controls" preload="metadata">
                                        <source src="${viewSourceFile(detail.url)}" type="video/mp4">
                                       </video>`;
            }
        }
        return `<tr>
                                <td>${index + 1}</td>
                                <td>${detail.position}</td>
                                <td>${divImg}</td>
                                <td style="text-align: justify ;max-width: 400px; word-wrap: break-word" >${detail.text ? detail.text : ''}</tds> 
                                <td style="max-width: 6%">
                                    <div class="dropdown">
                                        <button id="menu-drop-1" type="button" class="btn btn-action dropdown-toggle" style="height: 30px"
                                                data-toggle="dropdown">
                                            <i class="fas fa-cog"></i>
                                        </button>
                                        <ul class="dropdown-menu" aria-labelledby="menu-drop-1" style="">
                                            <li>
                                                <button class="drop-detail-1 btn btn-action font-weight-normal col-lg-12"
                                                        style="text-align: left" index="${detail.id}" data-toggle="modal"
                                                        data-target="#detail-modal">
                                                        <i class="fas fa-pen"></i>&nbsp;&nbsp;Chỉnh sửa
                                                </button>
                                            </li>
                                        </ul>
                                    </div>
                                </td> 
                            </tr>`
    }).join('');
    $('#row-ajax').html(rows);
    changeDetails();
}

function changeDetails() {
    $('.drop-detail-1 ').on('click', function () {
        callGetAjax(IS_SERVICE, 'v1/admin/part-detail/' + $(this).attr("index"))
            .then(rs => {
                $('#if-image').hide();
                $('#if-video').hide();
                $('#if-text').hide();
                $('#choose-file').val('');
                let detail = rs.data;
                $('#input-position').val(detail.position);
                $('#input-link').val(detail.link ? detail.link : '');
                switch (detail.type) {
                    case 1:
                    case 3:
                    case 4:
                        if (detail.url && detail.url.length > 0) {
                            $('#if-image').show();
                            $('#img-preview').attr('src', viewSourceFile(detail.url));
                        }
                        if (detail.text && detail.text.length > 0) {
                            $('#if-text').show();
                            $('#input-text').val(detail.text);
                        }
                        $('#submit').off('click').on('click', function () {
                            if ($('#choose-file').get(0).files.length > 0) {
                                let formData = new FormData();
                                formData.append("files", $('#choose-file').get(0).files[0]);
                                callUploadFile(formData).then(rs => {
                                    detail.position = $("#input-position").val();
                                    detail.url = rs.data[0].uri
                                    detail.link = $('#input-link').val();
                                    detail.text = $('#input-text').val();
                                    callPutAjax(IS_SERVICE, 'v1/admin/part-detail', detail)
                                        .then(rs => {
                                            getAllContents(detail.part.code);
                                            success("Cập nhật thành công")
                                            $('#detail-modal').modal('hide');
                                        }).catch(e => error("Cập nhật thất bại"))
                                }).catch(ex => {
                                    console.log(ex);
                                    error("Tải hình ảnh thất bại")
                                })
                            }else {
                                detail.position = $("#input-position").val();
                                detail.link = $('#input-link').val();
                                detail.text = $('#input-text').val();
                                callPutAjax(IS_SERVICE, 'v1/admin/part-detail', detail)
                                    .then(rs => {
                                        getAllContents(detail.part.code);
                                        success("Cập nhật thành công")
                                        $('#detail-modal').modal('hide');
                                    }).catch(e => error("Cập nhật thất bại"))
                            }
                        })
                        break;
                    case 2:
                        $('#if-video').show();
                        if (detail.url.startsWith("http")) {
                            $('#input-youtube').val(detail.url)
                            try {
                                $('#div-video').html(`<iframe style="max-width: 100%" src="${lesson.url}"></iframe>`)
                            } catch (e) {

                            }
                        } else {
                            $('#div-video').html(`<video id="lesson-video-preview" style="max-width: 100%" controls="controls" preload="metadata">
                                        <source src="${viewSourceFile(detail.url)}" type="video/mp4">
                                       </video>`)
                        }
                        $('#choose-file-video').off('change').on('change', function () {
                            //upload video
                            $('#img-choose-video').attr('src', 'resources/image/uploading.gif')
                            $('#div-video').html('');
                            let form = new FormData;
                            form.append("files", $('#choose-file-video').get(0).files[0]);
                            callUploadFile(form)
                                .then(rs => {
                                    $('#img-choose-video').attr('src', 'resources/image/camera2.png')
                                    detail.url = rs.data[0].uri
                                    detail.position = $("#input-position").val();
                                    let video = `<video id="lesson-video-preview" style="max-width: 100%" controls="controls" preload="metadata">
                                        <source src="${viewSourceFile(rs.data[0].uri)}" type="video/mp4">
                                       </video>`
                                    $('#div-video').html(video);
                                    $('#input-youtube').val('');
                                }).catch(ex => {
                                console.log(ex)
                                error("Tải video thất bại")
                            })
                        })
                        $('#submit').off('click').on('click', function () {
                            if ($('#input-youtube').val().length > 0)
                                detail.url = $('#input-youtube').val();
                            callPutAjax(IS_SERVICE, 'v1/admin/part-detail', detail)
                                .then(rs => {
                                    getAllContents(detail.part.code);
                                    success("Cập nhật thành công")
                                    $('#detail-modal').modal('hide');
                                }).catch(e => error("Cập nhật thất bại"))
                        })
                        break;
                }
            }).catch(ex => {
            console.log(ex)
            error("Tải thông tin thất bại");
        })
    })
}