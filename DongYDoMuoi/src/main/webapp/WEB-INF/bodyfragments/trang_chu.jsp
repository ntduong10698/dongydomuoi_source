<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="js/home.js"></script>
<script src="https://cdn.bksoftwarevn.com/resources/library_js/ajax_micro_service/ajax_edu_service.js"></script>
<script src="https://cdn.bksoftwarevn.com/resources/library_js/ajax_micro_service/ajax_marketing_service.js"></script>
<script src="ajax/pages/page_trang_chu.js"></script>
<!-- ============= Custom Css ============= -->
<link rel="stylesheet" href="css/trang_chu.css">
<!-- Quảng cáo -->
<section class="popup-ad d-none">
    <div class="popup-wrapper" id="set-popup">
        <a href="trang-chu">
            <img src="" alt="">
            <div class="close-popup">
                [x]
            </div>
        </a>
    </div>
</section>
<section class="hero d-none d-md-block">
    <div class="container">
        <div class="row">
            <div class="col-12">
                <div class="hero-wrapper row">
                    <div class="hero__category col-md-4 col-lg-3" id="hero__category">
                        <ul class="hero__category-list" id="nav-category">
                            <div class="nav-category">
                                <i class="fas fa-bars"></i>
                                <span>Danh mục sản phẩm</span>
                            </div>
                            <li class="li-1 d-none" id="li-nav-temp">
                                <a href="" class="nav-href" data-id="">
                                    <img src=""  class="nav-img">
                                    <span class="nav-text"></span>
                                </a>
                                <ul class="d-none nav1" data-id="">
                                    <li data-id="">
                                        <a href="" class="nav1-href"></a>
                                        <ul class="class-n">
                                            <li data-id="">
                                                <a href=""></a>
                                            </li>
                                        </ul>
                                    </li>
                                </ul>
                            </li>
                        </ul>
                    </div>
                    <div class="hero__banner col-md-8 col-lg-9 pl-0">
                        <div class="banner" id="banner-main">
                            <a href="" class="slick-item" id="banner-item-temp">
                                <img src="" >
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<section class="catalog">
    <div class="container">
        <div class="row">
            <div class="col-12 col-md-3 mt-4">
                <a href="" id="img-gioi-thieu-1">
                    <img src="" >
                </a>
            </div>
            <div class="col-12 col-md-3 mt-4">
                <a href="" id="img-gioi-thieu-2">
                    <img src="" >
                </a>
            </div>
            <div class="col-12 col-md-3 mt-4">
                <a href="" id="img-gioi-thieu-3">
                    <img src="" >
                </a>
            </div>
            <div class="col-12 col-md-3 mt-4">
                <a href="" id="img-gioi-thieu-4">
                    <img src="" >
                </a>
            </div>
        </div>
    </div>
</section>
<section class="introduce">
    <div class="container">
        <div class="row">
            <div class="col-12 col-lg-8">
                <h3 class="text-center text-md-left" id="gioi-thieu-1"><span class="text-gioi-thieu"></span></h3>
                <p class="intro-slogan text-center text-md-left" id="gioi-thieu-2"><span class="text-gioi-thieu"></span></p>
                <p class="intro-content text-justify text-md-left" id="gioi-thieu-3"><span class="text-gioi-thieu"></span></p>
                <p class="intro-bt text-center text-md-left mb-5"><a href="gioi-thieu" class="btn btn-main-co btn-lg">Tìm
                    hiểu thêm</a></p>
            </div>
            <div class="col-12 col-lg-4">
                <div class="row mr-0 ml-0">
                    <div class="col-6 intro-item">
                        <div class="intro-inner" id="gioi-thieu-4">
                            <div class="intro-icon text-center">
                                <a href="" class="href-gioi-thieu">
                                    <img src=""  class="d-none">
                                </a>
                            </div>
                            <h2 class="intro-heading text-center">
                                <a href="" class="href-gioi-thieu text-gioi-thieu">
                                </a>
                            </h2>
                        </div>
                    </div>
                    <div class="col-6 intro-item">
                        <div class="intro-inner" id="gioi-thieu-5">
                            <div class="intro-icon text-center">
                                <a href="" class="href-gioi-thieu">
                                    <img src=""  class="d-none">
                                </a>
                            </div>
                            <h2 class="intro-heading text-center">
                                <a href="" class="href-gioi-thieu text-gioi-thieu">
                                </a>
                            </h2>
                        </div>
                    </div>
                    <div class="col-6 intro-item">
                        <div class="intro-inner" id="gioi-thieu-6">
                            <div class="intro-icon text-center">
                                <a href="" class="href-gioi-thieu">
                                    <img src=""  class="d-none">
                                </a>
                            </div>
                            <h2 class="intro-heading text-center">
                                <a href="" class="href-gioi-thieu text-gioi-thieu">
                                </a>
                            </h2>
                        </div>
                    </div>
                    <div class="col-6 intro-item">
                        <div class="intro-inner" id="gioi-thieu-7">
                            <div class="intro-icon text-center">
                                <a href="" class="href-gioi-thieu">
                                    <img src=""  class="d-none">
                                </a>
                            </div>
                            <h2 class="intro-heading text-center">
                                <a href="" class="href-gioi-thieu text-gioi-thieu">
                                </a>
                            </h2>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<section class="doctors">
    <div class="container">
        <div class="row">
            <div class="col-12">
                <h3 class="section-title text-center">Đội ngũ bác sĩ - Nơi tinh hoa hội tụ</h3>
                <p class="doctors-decs text-center" id="text-doi-ngu-bac-si-trang-chu"></p>
            </div>
            <div class="col-12">
                <div class="doctors-carousel" id="doctors-carousel">
                    <div class="doctor-item d-none" id="doctor-item-temp">
                        <div class="doctor-img">
                            <img src=""  class="doctor-item-img">
                        </div>
                        <div class="doctor-text">
                            <h3 class="doctor-name text-center doctor-item-name"></h3>
                            <strong class="d-block text-center doctor-item-position"></strong>
                            <p class="text-center doctor-item-des"></p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

<section class="h-product">
    <div class="container">
        <div class="row">
            <div class="col-12">
                <h3 class="text-center section-title">Sản phẩm của chúng tôi</h3>
            </div>
        </div>
    </div>
    <div class="container product-wrapper d-none" id="list-product-temp" data-product-type="">
        <div class="row" >
            <div class="col-12 mb-5">
                <div class="col-inner">
                    <div class="inner-item">
                        <h3>
                            <a href="" class="lp-product-type-link">
                                <img src=""  class="lp-product-type-img">
                                <span class="lp-product-type-name"></span>
                            </a>
                        </h3>
                        <div class="see-more">
                            <a href="" class="lp-product-type-link">Xem thêm</a>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-6 col-md-4 col-lg-3 mb-2 mb-md-5 product-temp d-none" id="product-temp">
                <a href="" class="product product-link">
                    <div class="product-inner">
                        <div class="product__img">
                            <img src=""  class="product-img">
                            <div class="product__promo product-promo">
                                <div class="promo-sale"></div>
                                <div class="promo-gift" data-placement="bottom" data-toggle="tooltip"></div>
                            </div>
                        </div>
                        <div class="product__text">
                            <span class="text-center product-name"></span>
                            <div class="product-price text-center">
                                <del></del>
                                <span></span>
                            </div>
                            <div class="bt-add-cart text-center">
                                <button type="button" class="btn btn-primary btn-add-cart">Thêm vào giỏ</button>
                            </div>
                        </div>
                    </div>
                </a>
            </div>
        </div>
    </div>
</section>
<section class="lopHoc d-none d-lg-block">
    <div class="container">
        <div class="row">
            <div class="col-12">
                <h3 class="section-title text-center">Vì sao nên chọn sản phẩm của chúng tôi</h3>
            </div>
            <div class="col-12 col-md-5 d-flex justify-content-center align-items-center mb-5  order-md-1">
                <div class="lopHoc-text text-center" id="vi-sao-1-1">
                    <img src="" class="d-none">
                    <h3 class="text-vi-sao"></h3>
                    <p id="vi-sao-1-2"><span class="text-vi-sao"></span></p>
                </div>
            </div>
            <div class="col-12 col-md-7 d-flex justify-content-center align-items-center  mb-5 order-md-2">
                <div class="lopHoc-img multi-img">
                    <div class="lh-img1" id="vi-sao-1-3">
                        <img src="" class="d-none">
                    </div>
                    <div class="lh-img2" id="vi-sao-1-4">
                        <img src="" class="d-none">
                    </div>
                    <div class="lh-img3" id="vi-sao-1-5">
                        <img src="" class="d-none">
                    </div>
                    <div class="lh-img4" id="vi-sao-1-6">
                        <img src="" class="d-none">
                    </div>
                    <div class="lh-img5" id="vi-sao-1-7">
                        <img src="" class="d-none">
                    </div>
                    <div class="lh-img6" id="vi-sao-1-8">
                        <img src="" class="d-none">
                    </div>
                </div>
            </div>
            <div class="col-12 col-md-7 d-flex justify-content-center align-items-center  mb-5  order-md-4">
                <div class="lopHoc-text text-center" id="vi-sao-2-1">
                    <img src="" class="d-none">
                    <h3 class="text-vi-sao"></h3>
                    <p id="vi-sao-2-2"><span class="text-vi-sao"></span></p>
                </div>
            </div>
            <div class="col-12 col-md-5 d-flex justify-content-center align-items-center  mb-5  order-md-3">
                <div class="lopHoc-img" id="vi-sao-2-3">
                    <img src="" class="d-none">
                </div>
            </div>
            <div class="col-12 col-md-5 d-flex justify-content-center align-items-center mb-5  order-md-5">
                <div class="lopHoc-text text-center" id="vi-sao-3-1">
                    <img src="" class="d-none">
                    <h3 class="text-vi-sao"></h3>
                    <p id="vi-sao-3-2"><span class="text-vi-sao"></span></p>
                </div>
            </div>
            <div class="col-12 col-md-7 d-flex justify-content-center align-items-center mb-5  order-md-6">
                <div class="lopHoc-img" id="vi-sao-3-3">
                    <img src="" class="d-none">
                </div>
            </div>
        </div>
    </div>
</section>
<section class="custommer">
    <div class="container">
        <div class="row">
            <div class="col-12">
                <h3 class="section-title text-center">Khách hàng của chúng tôi</h3>
            </div>
            <div class="col-12">
                <div class="customer-carousel" id="customer-carousel">
                    <div class="doctor-item d-none" id="customer-item-temp">
                        <div class="doctor-img">
                            <img src=""  class="customer-item-img">
                        </div>
                        <div class="doctor-text">
                            <h3 class="doctor-name text-center customer-item-name"></h3>
                            <p class="text-center customer-item-des"></p>
                        </div>
                    </div>
                </div>
                </div>
            </div>
        </div>
    </div>
    </div>
</section>
