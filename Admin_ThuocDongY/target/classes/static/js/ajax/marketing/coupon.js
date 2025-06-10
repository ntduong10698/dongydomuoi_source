let listObject = [];
let textSearch;
$(function () {
    // editor = initQuill('#input-content');

    pageObject(1, 15);

    addEvent();

    searchEvent();

});

//===============handle Data=============
async function loadList(page = 1, size = 15) {
    let data;
    let uri = `v1/admin/coupons/company/${COMPANY_ID}?&page=${page}&size=${size}`;
    textSearch = $('#text-search').val() !== null ? $('#text-search').val() : "";

    data = await callGetAjax(MARKETING_SERVICE, uri)
        .then(rs => {
            listObject = rs.data.content;
            return rs.data;
        }).catch();

    return data;
}

//categories: list id của danh mục
//type: 1: dung cu, 2: dich vu, 3: khoa hoc
function ajaxUpdatePromotion(payload) {
    callPutAjax(MARKETING_SERVICE, 'v1/admin/coupon', payload)
        .then(saved => {
            success("Chỉnh sửa mã khuyến mãi thành công");
            $('#close-add').trigger('click');
            pageObject();
        }).catch(ex => {
        console.log(ex);
        error("Chỉnh sửa mã khuyến mãi thất bại")
    });
}

//===============handle Data=============

//=========hien thi len table + phan trang=========
function pageObject(page = 1, size = 15) {
    let container = $('#page');
    container.html('');
    loadList(page, size).then(rs => {
        container.pagination({
            dataSource: 'https://api.flickr.com/services/feeds/photos_public.gne?tags=cat&tagmode=any&format=json&jsoncallback=?',
            locator: 'items',
            totalNumber: rs.totalPages,
            pageSize: 1,
            showPageNumbers: true,
            showPrevious: true,
            showNext: true,
            showGoInput: true,
            showGoButton: true,
            showNavigator: true,
            formatNavigator: `<span style="color: #000000"> đi đến trang`,
            showFirstOnEllipsisShow: true,
            showLastOnEllipsisShow: true,
            callback: function (response, pagination) {
                loadList(pagination.pageNumber, size).then(rs1 => {
                    fillTable(listObject);
                })
            }
        })
    }).catch();
}

//=========end hien thi len table + phan trang=========
//tao template

//===============fill tables ==============
function fillTable(list) {
    let content = '';
    list.forEach((object) => {
        function templateGiam() {
            let templateInfo;
            let giaTri;
            if (object.type === 3) {
                giaTri = object.decrease * 100 + '%';
                templateInfo = `<div>Giá trị: giảm ${giaTri}</div>`;
            } else if (object.type === 2) {
                giaTri = viewPriceVND(object.decrease);
                templateInfo = `<div>Giá trị: giảm ${giaTri}</div>`;
            } else {
                templateInfo = `<div>Quà tặng: ${object.content}</div>`;
            }
            return templateInfo;
        }

        let loai = object.type === 1 ? 'Quà tặng' : (object.type === 2 ? 'Giảm tiền' : 'Giảm %')
        content += ` <tr class="my-item" style="">
                                <td>${object.id}</td>
                                <td class="text-left" style="max-width: 300px">
                                    <div style="text-align: start;">Mã: ${object.code}</div>
                                    ${templateGiam()}
                                </td>       
                                <td>${loai}</td>
                                <td>${object.global ? 'Hiển thị' : 'Ẩn'}</td> 
                                <td>${object.end ? viewDateTextVn(object.end, true) : 'Không giới hạn'}</td> 
                                <td>${object.quantity}</td>
                                <td>${object.used}</td>
                                <td style="max-width: 6%">
                                    <div class="dropdown">
                                        <button id="menu-drop-1" type="button" class="btn btn-action dropdown-toggle" style="height: 30px"
                                                data-toggle="dropdown">
                                            <i class="fas fa-cog"></i>
                                        </button>
                                        <ul class="dropdown-menu" aria-labelledby="menu-drop-1" style="">
                                            <li>
                                                <button class="drop-1 btn btn-action font-weight-normal col-lg-12"
                                                        style="text-align: left" index="${object.id}" data-toggle="modal"
                                                        data-target="#myModal">
                                                        <i class="fas fa-pen"></i>&nbsp;&nbsp;Chỉnh sửa
                                                </button>
                                            </li>
                                        
                                            <li>
                                                <button class="drop-3 btn btn-action font-weight-normal col-lg-12"
                                                        style="text-align: left" delete-id="${object.id}" data-toggle="modal"
                                                        data-target="#delete-modal">
                                                    <i class="fas fa-trash-alt"></i>&nbsp;&nbsp;Xóa
                                                </button>
                                            </li>
                                        </ul>
                                    </div>
                                </td>
                            </tr>`;
    });
    $('#row-ajax').html(content);
    changeEvent();
    deleteEvent();
}

//===============end fill tables ==============

//===========check input=========
function checkInput() {
    let count = 5;

    let typeKM = parseInt($('#input-loai-khuyen-mai').val());

    switch (typeKM) {
        case 1:
            if ($('#input-content').val().length === 0) {
                info("Vui lòng điền nội dung quà");
                count--;
            }
            break;
        case 2:
            if ($('#input-gtkm').val().length === 0) {
                info("Vui lòng điền số tiền giảm");
                count--;
            }
            break;
        case 3:
            if ($('#input-gtkm').val().length === 0) {
                info("Vui lòng điền số % giảm");
                count--;
            }
            if ($('#input-gttt').val().length === 0) {
                info("Vui lòng điền số tiền giảm tối đa");
                count--;
            }
            break;
    }

    if ($('#input-tgad').val() == 1) {
        if ($('#input-end-date').val().length === 0) {
            info("Vui lòng điền thời gian kết thúc");
            count--;
        }
    }
    return count === 5;
}

//===========end check input=========

async function checkCode(code) {
    let data = null;
    await callGetAjax(MARKETING_SERVICE, `v1/admin/coupon/check?company=${COMPANY_ID}&code=${code}`)
        .then(rs => {
            data = true;
        }).catch(ex => data = false)
    return data;
}

//==========handle event for each element=========
function addEvent() {
    $('#input-loai-khuyen-mai').on('change', function () {
        let idLoaiKM1 = parseInt($(this).val());
        switch (idLoaiKM1) {
            case 1:
                $('#div-decrease').hide();
                $('#div-max').hide();
                break;
            case 2:
                $('#div-decrease').show();
                $('#label-decrease').html(`Số tiền giảm <span style="color: red"> *</span>`)
                $('#div-max').hide();
                break;
            case 3:
                $('#div-decrease').show();
                $('#label-decrease').html(`Số % giảm <span style="color: red"> *</span>`)
                $('#div-max').show();
                break;
        }
    });

    //check gioi han thoi gian
    //nếu giới hạn => hiện lên phần điền thời gian
    //nếu không => ẩn
    $('#input-tgad').change(() => {
        let isGioiHanTime1 = $('#input-tgad').children('option:selected').val() == 0 ? true : false;
        if (isGioiHanTime1) {
            $('#wrap-ngay-kt').addClass('d-none');
        } else {
            $('#wrap-ngay-kt').removeClass('d-none');
            $('#input-end-date').val('');
        }
    });

    $('#btn-add').off('click').on('click', function () {
        //clear content
        $('#input-code').val('');
        $('#input-code').attr('disabled', false);
        $('#input-code').on('change', function () {
            checkCode($(this).val())
                .then(rs => {
                    if (!rs) {
                        info("Mã đã tồn tại");
                        $(this).val('');
                    }
                }).catch(ex => {
                info("Mã đã tồn tại");
                $(this).val('');
            });
        })
        $('#input-loai-khuyen-mai').trigger('change');
        $('#input-loai-khuyen-mai').attr('disabled', false);
        $('#input-gtkm').val(0)
        $('#input-gttt').val(0);
        $('#input-gttd').val(0);
        $('#input-quantity').val(0);
        $('#input-content').val('');

        let isGioiHanTime2 = $('#input-tgad').children('option:selected').val() == 0;
        if (isGioiHanTime2) {
            $('#wrap-ngay-kt').addClass('d-none');
        } else {
            $('#wrap-ngay-kt').removeClass('d-none');
            $('#input-end-date').val('');
        }

        $('#submit').off('click').on('click', function () {
            if (checkInput()) {
                let global = $('#input-global').val() == 1;
                let ngayKetThuc = null;
                // neu k bị giới hạn thời gian sẽ lấy giá trị từ input
                let isGioiHanTime3 = $('#input-tgad').children('option:selected').val() == 0;
                if (!isGioiHanTime3) {
                    ngayKetThuc = formatStartTime($('#input-end-date').val());
                }
                let idLoaiKM3 = $(`#input-loai-khuyen-mai`).children('option:selected').val();
                let gtkm = parseFloat($('#input-gtkm').val());
                if (idLoaiKM3 == 3)
                    gtkm /= 100;
                let object = {
                    code: $('#input-code').val(),
                    companyId: COMPANY_ID,
                    content: $('#input-content').val(),
                    decrease: gtkm,
                    end: ngayKetThuc,
                    global: global,
                    maxDiscount: parseInt($('#input-gttd').val()),
                    minimum: parseInt($('#input-gttt').val()),
                    quantity: parseInt($(`#input-quantity`).val()),
                    type: parseInt(idLoaiKM3),
                    used: 0
                };
                callPostAjax(MARKETING_SERVICE, `v1/admin/coupon`, object)
                    .then(() => {
                        success("Tạo mã khuyến mãi thành công");
                        $('#close-add').trigger('click');
                        pageObject(1, 15);
                    }).catch(ex => {
                    console.log(ex);
                    error("Tạo mã khuyến mãi thất bại");
                });
            }
        })
    });
}

function changeEvent() {
    $('.drop-1').off('click').on('click', function () {

        let id = $(this).attr('index');

        callGetAjax(MARKETING_SERVICE, `v1/admin/coupon/${id}`)
            .then(rs => {
                let payload = rs.data;
                //clear content
                $('#input-code').val(payload.code);
                $('#input-code').attr("disabled", true);
                $('#input-code').off('change');
                $('#input-loai-khuyen-mai').val(payload.type);
                $('#input-loai-khuyen-mai').trigger('change');
                $('#input-loai-khuyen-mai').attr("disabled", true);
                $('#input-global').val(payload.global ? '1' : '0');
                if (payload.type == 3) payload.decrease *= 100;
                $('#input-gtkm').val(payload.decrease);
                $('#input-gttt').val(payload.minimum);
                $('#input-gttd').val(payload.maxDiscount);
                $('#input-quantity').val(payload.quantity);
                $('#input-content').val(payload.content);

                //check gioi han thoi gian
                //nếu giới hạn => hiện lên phần điền thời gian
                //nếu không => ẩn
                $('#input-tgad').change(() => {
                    let isGioiHanTime1 = $('#input-tgad').val() == 0;
                    if (isGioiHanTime1) {
                        $('#wrap-ngay-kt').addClass('d-none');
                    } else {
                        $('#wrap-ngay-kt').removeClass('d-none');
                    }
                });

                function checkTimeIsNull() {
                    if (payload.end !== null) {
                        return false;
                    }
                    return true
                }

                if (checkTimeIsNull()) {
                    $('#input-tgad').val('0');
                    $('#wrap-ngay-kt').addClass('d-none');
                } else {
                    $('#wrap-ngay-kt').removeClass('d-none');
                    $('#input-end-date').val(new Date(payload.end).toISOString().slice(0, 16));
                }

                $('#submit').off('click').on('click', function () {
                    let isVoThoiHan = $('#input-tgad').val() == 0;
                    payload.code = $('#input-code').val();
                    payload.type = parseInt($('#input-loai-khuyen-mai').children('option:selected').val());
                    payload.content = $('#input-content').val();
                    let decrease = parseFloat($('#input-gtkm').val());
                    if (payload.type == 3)
                        decrease /= 100;
                    payload.decrease = decrease;
                    payload.global = $('#input-global').val() == 1;
                    payload.minimum = parseInt($('#input-gttt').val());
                    payload.maxDiscount = parseInt($('#input-gttd').val());
                    payload.end = isVoThoiHan ? null : formatStartTime($('#input-end-date').val());
                    payload.quantity = parseInt($('#input-quantity').val());
                    if (checkInput()) {
                        ajaxUpdatePromotion(payload);
                    }

                })
            }).catch(ex => {
            console.log(ex);
            error("Có lỗi xảy ra")
        })
    });
}

function deleteEvent() {
    $('.drop-3').off('click').on('click', function () {
        let id = $(this).attr("delete-id");
        $('#submit-delete').off('click').on('click', function () {
            callDeleteAjax(MARKETING_SERVICE, `v1/admin/coupon/${id}`)
                .then(rs => {
                    console.log(rs);
                    pageObject();
                    $('#close-delete').trigger('click');
                    success("Xóa thành công");
                }).catch(ex => {
                console.log(ex);
                error("Xóa khuyến mãi thất bại")
            });
        })
    });
}

//==========end handle event for each element=========

//============ handle time view, price==============

function viewDateTextVn(text, isStartTime) {
    if (text) {
        let date = new Date(text);
        let hours = date.getUTCHours() < 10 ? '0' + date.getUTCHours() : date.getUTCHours();
        let minutes = date.getUTCMinutes() < 10 ? '0' + date.getUTCMinutes() : date.getUTCMinutes();

        if (isStartTime) {
            return `${date.getUTCDate()}/${date.getUTCMonth() + 1}/${date.getFullYear()} - ${hours}:${minutes}`;
        } else {
            return `${date.getUTCDate()}/${date.getUTCMonth() + 1}/${date.getUTCFullYear()}`;
        }
    } else {
        return '';
    }
}

function formatStartTime(time) {
    if (time.length > 0 && time !== null) {
        let timeData = `${time}:00.000Z`;
        let timeDate = new Date(timeData);
        return timeDate.toISOString();
    }
    return null;
}

function viewField(data) {
    return data ? data : "";
}

function viewPriceVND(price) {
    if (price) {
        return formatNumber(viewField(price), ",", ".") + " VNĐ";
    }
    return "";
}

//============end handle time view, price==============

//=============handle common event=============
function searchEvent() {
    $('#btn-search').on('click', function () {
        pageObject();
    });
}

//=============end handle common event=============
