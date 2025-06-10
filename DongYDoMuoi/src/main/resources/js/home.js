$(document).ready(function () {
});

function runSlickBannerMain() {
    $('#banner-main').slick({
        dots: true,
        infinite: true,
    });
}

function runSlickDoctor(selector) {
    $(selector).slick({
        slidesToShow: 3,
        dots: true,
        responsive: [
            {
                breakpoint: 992,
                settings: {
                    slidesToShow: 2
                }
            },
            {
                breakpoint: 576,
                settings: {
                    slidesToShow: 1
                }
            }
        ]
    });
}

function runSlickCustomer(selector) {
    selector.slick({
        slidesToShow: 3,
        dots: true,
        responsive: [
            {
                breakpoint: 992,
                settings: {
                    slidesToShow: 2
                }
            },
            {
                breakpoint: 576,
                settings: {
                    slidesToShow: 1
                }
            }
        ]
    });
}
