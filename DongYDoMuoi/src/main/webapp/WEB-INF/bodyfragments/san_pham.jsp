<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- ============= Custom Css ============= -->
<link rel="stylesheet" href="css/trang_chu.css">
<script src="ajax/pages/page_san_pham.js"></script>
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