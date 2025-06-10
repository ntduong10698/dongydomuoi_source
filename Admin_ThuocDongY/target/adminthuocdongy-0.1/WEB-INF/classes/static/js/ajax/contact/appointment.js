let listObject = []

$(function () {
    $('#select-checked').val(1);
    pageObject(1, 15);
    searchEvent();
});

async function loadList(page = 1, size = 15) {
    let data;
    await callGetAjax(IS_SERVICE, `v1/admin/appointments/company/${COMPANY_ID}?`
        .concat(`status=${$('#select-checked').val()}`)
        .concat(`&page=${page}&size=${size}`)).then(rs => {
        listObject = rs.data.content;
        data = rs.data;
    }).catch(ex => error("Tải thông tin lịch khám thất bại"));
    return data;
}

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
                loadList(pagination.pageNumber - 1, size).then(rs => {
                    fillTable()
                })
            }
        })
    }).catch();
}

function fillTable() {
    let list = listObject;
    let content = '';
    list.map((object, index) => {
        let trangThai = object.status === 1 ? 'Mới' : (object.status === 2 ? 'Đã liên hệ' : 'Đã khám');
        content += ` <tr class="my-item" style="">
                                <td>${index + 1}</td>
                                <td class="text-left">${object.name ? object.name : ''}</td>                              
                                <td>${object.phone ? object.phone : ''}</td>                              
                            
                                <td>${object.branch.name}</td>                          
                                <td>${viewDateTime(object.time)}</td>      
                                <td>${trangThai}</td>                                 
                                <td style="max-width: 6%">
                                    <div class="dropdown">
                                        <button type="button" class="btn btn-action dropdown-toggle" style="height: 30px"
                                                data-toggle="dropdown">
                                            <i class="fas fa-cog"></i>
                                        </button>
                                        <ul class="dropdown-menu" aria-labelledby="menu-drop-1" style="">
                                            <li>
                                                <button class="drop-1 btn btn-action font-weight-normal col-lg-12"
                                                        style="text-align: left" index="${object.id}" data-toggle="modal"
                                                        data-target="#detail-modal">
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

function changeEvent() {
    $('.drop-1').off('click').on('click', function () {
        let id = $(this).attr("index");
        let contact = listObject.filter(c => c.id == id)[0];
        console.log(contact);
        $('#input-name').val(contact.name);
        $('#input-phone').val(contact.phone);
        $('#input-address').val(contact.address);
        $('#input-status').val(contact.status);
        $('#input-reason').val(contact.reason);
        $('#input-branch').val(contact.branch.address);
        callGetAjax(EDU_SERVICE, 'v1/public/lecturers/' + contact.personId)
            .then(rs => $('#input-doctor').val(rs.data.name))
            .catch(e => $('#input-doctor').val('Không chọn'));
        $('#submit').off('click').on('click', function () {
            contact.status = $('#input-status').val();
            callPutAjax(IS_SERVICE, 'v1/admin/appointment', contact)
                .then(rs => {
                    success("Cập nhật thành công");
                    $('#detail-modal').modal('hide');
                    getNewAppointments();
                    pageObject();
                }).catch(ex => error("Cập nhật thất bại"));

        })
    })
}

function deleteEvent() {
    $('.drop-2').off('click').on('click', function () {
        let id = $(this).attr("index");
        $('#submit-delete').off('click').on('click', function () {
            callDeleteAjax(IS_SERVICE, 'v1/admin/contact/' + id)
                .then(rs => {
                    success("Đã xóa");
                    $('#delete-modal').modal('hide');
                    pageObject();
                }).catch(ex => error("Có lỗi xảy ra"));

        })
    })
}

function searchEvent() {
    $('#select-checked').on('change', function () {
        pageObject();
    })
}