let listObject = [];
let currentPage = 1;
let statuses = [];

$(function () {
    //print event
    clickPrintElement("#print-div")
    checkAll();
    loadAllStatuses().then(rs => {
        pageObject(1, 15);
        searchEvent();
    }).catch(ex => {
        error("Có lỗi xảy ra")
    });
});

function checkAll(){
    $('#check-all').on('click', function () {
        callPutAjax(BILL_SERVICE, `v1/admin/bill/check?ids=${listObject.map(o => o.id)}`, null).then(rs => {
            success("Đã cập nhật")
            pageObject();
            getUncheckCart();
        }).catch(ex => error('Cập nhật thất bại'));
    })
}

async function loadList(page = currentPage , size = 15) {
    let data;

    await callGetAjax(BILL_SERVICE, `v1/admin/bills/filter/v2?company=${COMPANY_ID}`
        .concat(`&code=${$('#text-code').val()}`)
        .concat(`&text=${$('#text-search').val()}`)
        .concat(`&status=${$('#select-status').val()}`)
        .concat(`&dateAsc=${$('#select-date').val()}`)
        .concat(`&page=${page}&size=${size}`))
        .then(rs => {
            listObject = rs.data.content;
            return data = rs.data;
        }).catch();
    return data;
}

async function loadAllStatuses() {
    await callGetAjax(BILL_SERVICE, 'v1/public/statuses')
        .then(rs => {
            statuses = rs.data;
            $('#select-status').html(`<option value="0">--Chọn tình trạng--</option>` + statuses.map(stt => `<option value="${stt.id}">${stt.nameStatus}</option>`))
            $('#select-status').val(0);
            $('#select-status-input').html(statuses.map(stt => `<option value="${stt.id}">${stt.nameStatus}</option>`))
        })
    return null;
}

function pageObject(page = currentPage, size = 15) {
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
                currentPage = pagination.pageNumber;
                loadList(currentPage, size).then(rs => {
                    fillTable(listObject)
                })
            }
        })
    }).catch();
}

function fillTable(list) {
    let content = '';
    list.map((object, index) => {
        content += ` <tr class="my-item" style="${object.checked ? '' : 'background: #3f00ff3d;'}">
                                <td>${index + 1}</td>
                                <td>${object.code}</td>
                                <td class="text-left">${object.customerName}</td>                              
                                <td>${object.customerPhone}</td>                              
                                <td class="text-right">${formatNumber(object.totalMoney)}</td>                            
                                <td>${viewDateTime(object.createdTime)}</td>   
                                <td>${object.status ? object.status.nameStatus : ''}</td>                                    
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
                                                        <i class="fas fa-pen"></i>&nbsp;&nbsp;Chi tiết
                                                </button>
                                            </li>
                                                                                                                                           
                                            <li>
                                                <button class="drop-2 btn btn-action font-weight-normal col-lg-12"
                                                        style="text-align: left" index="${object.id}" data-toggle="modal"
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


// function addEvent() {
//     $('#btn-add').off('click').on('click', function () {
//         //clear content
//         $('#input-name').val('')
//         $('#input-danh-muc').val('')
//         $('#input-danh-muc').select2()
//         $('#input-cost').val(0)
//         $('#input-preview').val('')
//         $('#input-alias').val('')
//         $('#choose-file').val('')
//         $('#img-preview').attr('src', 'resources/image/camera2.png');
//
//         $('#submit').off('click').on('click', function () {
//             if (checkInput()) {
//                 checkAlias($('#input-alias').val())
//                     .then(rs => {
//                         if (!rs) {
//                             let formData = new FormData();
//                             formData.append("files", $('#choose-file').get(0).files[0]);
//                             callUploadFile(formData).then(rs => {
//                                 let object = {
//                                     name: $('#input-name').val(),
//                                     categoryIds: $('#input-danh-muc').val().map(cate => parseInt(cate)),
//                                     brandId: 0,
//                                     cost: parseInt($('#input-cost').val()),
//                                     weight: 0.0,
//                                     alias: $('#input-alias').val(),
//                                     preview: $('#input-preview').val(),
//                                     introduction: autoAddAltImg(editor.root.innerHTML),
//                                     image: rs.data[0].uri
//                                 };
//
//                                 callPostAjax(PRODUCT_SERVICE, 'v1/admin/product', object)
//                                     .then(saved => {
//                                         //create alias
//                                         callPostAjax(IS_SERVICE, 'v1/admin/url-alias?company-id=1', {
//                                             alias: $('#input-alias').val(),
//                                             url: 'chi-tiet-dich-vu?id=' + saved.data.id
//                                         }).then().catch(ex => error("Tạo alias lỗi!"))
//
//                                         success("Tạo mới dịch vụ thành công");
//                                         $('#close-add').trigger('click');
//                                         pageObject();
//                                     }).catch(ex => error("Tạo mới dịch vụ thất bại"))
//                             }).catch(ex => error("Tải hình ảnh thất bại"))
//                         } else error("Đường dẫn đã được sử dụng")
//                     })
//             }
//         })
//     });
//
// }

function changeEvent() {
    $('.drop-1').off('click').on('click', function () {
        let id = $(this).attr('index');
        callGetAjax(BILL_SERVICE, `v1/admin/bill/${id}/check/`).then(rs => {
            pageObject();
            getUncheckCart();
        }).catch(ex => error('Hiển thị hoá đơn theo mã thất bại'));
        callGetAjax(BILL_SERVICE, `v1/admin/bills/${id}`)
            .then(rs => {
                    let bill = rs.data.bill;
                    let products = rs.data.billDetails;
                    $('#input-name').val(bill.customerName)
                    $('#input-phone').val(bill.customerPhone)
                    $('#input-email').val(bill.customerEmail);
                    $('#input-address').val(bill.customerAddress);
                    $('#input-time').val(viewDateTime(bill.createdTime));
                    $('#select-status-input').val(bill.status.id);
                    $('#input-note').val(bill.noteBill);
                    $('#input-promo').html(bill.promotion ? `<ul>${bill.promotion.split("|").map(line => `<li>${line}</li>`).join("")}</ul>` : '');
                    $('#total-momey').html(`Tổng giá trị đơn hàng: ` + formatNumber(bill.totalMoney) + ` VNĐ`);
                    $('#row-products').html(products.map((product, index) => `<tr>
                                                                <td><img width="60px" src="${viewSourceFile(product.productImage)}"></td>
                                                                <td>${product.productId}</td>
                                                                <td class="text-left">${product.productName}</td>
                                                                 <td class="text-right">${formatNumber(product.originPrice)}</td>
                                                                <td class="text-right">${formatNumber(product.productPrice)}</td>
                                                                <td>${product.amount}</td>
                                                                <td class="text-right">${formatNumber(product.productPrice * product.amount)}</td>
                                                                <td><ul>${product.promotion.length > 0 ? product.promotion.split("|").map(line => `<li>${line}</li>`).join('') : ''}</ul></td>
                                                            </tr>`))
                    $('#submit').off('click').on('click', function () {
                        bill.status = statuses.filter(s => s.id == $('#select-status-input').val())[0];
                        bill.noteBill = $('#input-note').val();
                        bill.checked = true;
                        callPutAjax(BILL_SERVICE, 'v1/admin/bill', bill)
                            .then(rs => {
                                loadList().then(rs => {
                                    fillTable(listObject)
                                })
                                success("Cập nhật đơn hàng thành công");
                                $('#myModal').modal("hide");
                            }).catch(ex => {
                            error("Cập nhật đơn hàng lỗi")
                        })
                    })
                }
            ).catch()
    });
}

function deleteEvent() {
    $('.drop-2').off('click').on('click', function () {
        let id = $(this).attr("index");
        console.log(id)
        $('#submit-delete').off('click').on('click', function () {
            callDeleteAjax(BILL_SERVICE, 'v1/admin/bill/' + id)
                .then(rs => {
                    loadList().then(rs => {
                        fillTable(listObject)
                    })
                    $('#close-delete').trigger('click');
                    success("Xóa thành công");
                }).catch(ex => error("Xóa thất bại"));
        })
    });
}

function searchEvent() {
    $('#btn-search').on('click', function () {
        pageObject(1,15);
    });
}





