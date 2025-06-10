<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="css/chi_tiet_mot_san_pham.css">
<script src="plugins/inputSpinnerBootstrap/bootstrap-input-spinner.js"></script>
<script src="plugins/elevatezoom-master/jquery.elevatezoom.js"></script>
<script src="js/chi-tiet-san-pham.js"></script>
<script src="ajax/pages/page_chi_tiet_san_pham.js"></script>
<div class="d-none" id="page-id" data-page-id="<%=request.getParameter("id")%>"></div>
<section class="p-breadcrumb">
    <div class="container">
        <div class="row">
            <div class="col-12">
                <nav aria-label="breadcrumb ">
                    <ol class="breadcrumb d-flex justify-content-center justify-content-md-start align-items-center">
                        <li class="breadcrumb-item"><a href="trang-chu">Trang chủ</a></li>
                        <li class="breadcrumb-item active" aria-current="page"><%=request.getAttribute("title")%></li>
                    </ol>
                </nav>
            </div>
        </div>
    </div>
</section>
<section class="product-detail">
    <div class="container">
        <div class="row">
            <div class="col-12 col-md-6 col-lg-4">
                <div class="product-detail__img">
                    <img src="" data-zoom-image="" class="img-product d-none">
                    <div class="p-promo" id="pro-infor-product">
                        <div class="promo-sale d-none"></div>
                        <div class="promo-gift d-none" data-placement="bottom" data-toggle="tooltip"></div>
                    </div>
                    <div id="gal1" class="list-img-product">
                        <a href="#" data-image="" data-zoom-image="" class="temp-list-img-product d-none">
                            <img src="" alt=""/>
                        </a>
                    </div>
                </div>
            </div>
            <div class="col-12 col-md-6 col-lg-5">
                <div class="product-detail_decs">
                    <h1 class="pdc-name" id="name-infor-product"></h1>
                    <p class="pdc-price" id="cost-infor-product">
                        <del></del>
                        <span></span>
                    </p>
                    <p class="pdc-pdecs" id="preview-infor-product">
                    </p>
                    <p class="pdc-status">
                        <span>Tình trạng:</span>
                        <span id="status-infor-product"></span>
                    </p>
                    <p class="pdc-contact">
                        <span>Liên hệ được giá tốt:</span>
                        <a href="" class="link-phone-comany text-phone-company"></a>
                    </p>
                    <p class="pdc-status ">
                        <span>Đơn vị:</span>
                        <span id="unit-infor-product"></span>
                    </p>
                    <div class="pdc-inbut d-none" id="group-input-quantity">
                        <div class="bt-gr">
                            <label>Ngày sử dụng:</label>
                            <input type="number" value="0" min="1" max="" step="1" id="input-date-use"/>
                        </div>
                        <div class="bt-gr">
                            <label>Số lượng:</label>
                            <input type="number" value="0" min="1" max="" step="1" id="input-quantity"/>
                        </div>
                        <button class="brn btn-danger buynow" onclick="addToCart()">Mua ngay</button>
                    </div>
                    <p class="pdc-parameter">Thông số kĩ thuật:</p>
                    <table class="table table-striped pdc-parameter-detail">
                        <tbody id="properties-infor-product" class="d-none">
                        <tr>
                            <th></th>
                            <td></td>
                        </tr>
                        </tbody>
                    </table>
                    <p class="pdc-code">Mã: <span id="model-infor-product"></span></p>
                    <div class="pdc-sharefb">
                        <div class="fb-like" data-href="<%=request.getAttribute("url")%>" data-width="" data-layout="button" data-action="like" data-size="small" data-share="true"></div>
                    </div>
                </div>
            </div>
            <div class="col-12 col-lg-3">
                <div class="pd-cs">
                    <img src="https://www.thuocdantoc.org/wp-content/themes/tdt-r9/images/img-chinh-sach.png">
                    <h3 class="pd-cs-title text-left">Chính sách của trung tâm</h3>
                    <ul>
                        <li> Cam kết hàng chính hãng</li>
                        <li> Đổi trả hàng trong 15 ngày</li>
                        <li> Giao hàng toàn quốc, nhận hàng trả tiền</li>
                        <li> Dược sĩ giỏi tư vấn trực tiếp</li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</section>
<section class="product-decs">
    <div class="container">
        <div class="row">
            <div class="col-12 pd-title">
                <span>Mô tả</span>
            </div>
            <div class="col-12 pd-content mt-10" id="introduction-infor-product"></div>
            <div class="col-12 pd-content mt-10">
                <div class="fb-comments" data-order-by="reverse_time" data-href="<%=request.getAttribute("url")%>"  data-numposts="5" data-width="100%"></div>
            </div>
        </div>
    </div>
</section>
<section class="product-related">
    <div class="container">
        <div class="row">
            <div class="col-12">
                <p class="product-related__title">Sản phẩm liên quan</p>
            </div>
            <div class="col-12">
                <div class="product-related-carousel" id="list-product-related">
                    <div class="prc-item d-none" id="product-temp">
                        <a href="" class="product product-link">
                            <div class="product-inner">
                                <div class="product__img">
                                    <img src="" alt="" class="product-img">
                                    <div class="product__promo product-promo">
                                        <div class="promo-sale"></div>
                                        <div class="promo-gift" data-placement="bottom" data-toggle="tooltip"></div>
                                    </div>
                                </div>
                                <div class="product__text">
                                    <span class="d-block text-center product-name"></span>
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
        </div>
    </div>
</section>