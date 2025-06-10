function getUncheckCart(){
    callGetAjax(BILL_SERVICE, `v1/admin/bills/company/${COMPANY_ID}/uncheck`)
        .then(rs => {
            if (rs.data > 0) {
                $('.fa-shopping-cart').css('color','yellow')
                $('#new-cart').html(` (${rs.data})`)
            }
        }).catch(ex => {
    })
}

function getUnCheckContact(){
    callGetAjax(IS_SERVICE, `v1/admin/contacts/company/${COMPANY_ID}/unchecked`)
        .then(rs => {
            if (rs.data > 0) {
                $('.user-menu').css('color','yellow')
                $('#new-contact').html(` (${rs.data})`)
            }
        }).catch(ex => {
        console.log(ex);
    })
}

function getNewAppointments(){
    callGetAjax(IS_SERVICE, `v1/admin/appointments/new/company/${COMPANY_ID}`)
        .then(rs => {
            if (rs.data > 0) {
                $('.appointment-menu').css('color','yellow')
                $('#new-appointment').html(` (${rs.data})`)
            }
        }).catch(ex => {
        console.log(ex);
    })
}

$(function () {
    getUncheckCart();
    getUnCheckContact();
    getNewAppointments();
    let previousClick = sessionStorage.getItem("previousClick");
    let preHref = sessionStorage.getItem("preHref");
    if (previousClick !== null) {
        // $(`#${previousClick}`).addClass('click-menu');

        if ($(`#${previousClick}`).hasClass('treeview')) {
            $(`#${previousClick}`).addClass('is-expanded');
        }

        if ($(`#${previousClick}`).hasClass('single')) {
            $(`#${previousClick}`).addClass('click-item');
            $(`.app-menu a[href='${preHref}']`).removeClass('click-item');
        }
    }
    if (preHref !== null) {
        $(`.app-menu a[href='${preHref}']`).addClass('click-item');
    }

    $('.treeview').each((index, select) => {
        $(select).click(() => {
            // $(`#${previousClick}`).removeClass('click-menu');
            sessionStorage.setItem("previousClick", $(select).attr("id"));
        })
    })


    $('.treeview-item').click(function () {
        sessionStorage.setItem("preHref", $(this).attr("href"));
    });
});