<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="css/product.css">
<script src="ajax/pages/page_danh_muc.js"></script>
<div class="d-none" id="page-id" data-page-id="<%=request.getParameter("id")%>"></div>
<div class="d-none" id="root-category" data-root-category="<%=request.getParameter("root")%>"></div>
<section class="p-breadcrumb">
    <div class="container">
        <div class="row">
            <div class="col-12 col-md-6 ">
                <nav aria-label="breadcrumb">
                    <ol class="breadcrumb d-flex justify-content-center justify-content-md-start align-items-center">
                        <li class="breadcrumb-item"><a href="trang-chu">Trang chủ</a></li>
                        <li class="breadcrumb-item active" aria-current="page"><%=request.getAttribute("title")%></li>
                    </ol>
                </nav>
            </div>
            <div class="col-12 col-md-6 d-flex justify-content-center justify-content-md-end">
                <select class="orderby" onchange="sortListProduct()" id="order-by-category">
                    <option value="sort=date&asc=false" selected>Mới nhất</option>
                    <option value="sort=price&asc=true">Thứ tự theo giá: thấp đến cao</option>
                    <option value="sort=price&asc=false">Thứ tự theo giá: cao xuống thấp</option>
                    <option value="sort=view&asc=false">Thứ tự theo số lượt xem</option>
                    <option value="sort=sold&asc=false">Thứ tự theo số lượt mua</option>
                </select>
            </div>
        </div>
    </div>
</section>
<section class="p-catalog d-none" id="catalog">
    <div class="container">
        <div class="row">
            <div class="col-12">
                <div class="clearfix">
                    <img src="" alt="" id="img-catalog">
                    <h1 id="name-catalog"></h1>
                    <div class="term-description">
                        <div id="des-catalog"></div>
                        </p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<section class="p-product p-product-cate">
    <div class="container product-wrapper" id="list-product-temp">
        <div class="row list-product">
            <div class="col-6 col-md-4 col-lg-3 mb-2 mb-md-5 product-temp d-none" id="product-temp">
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
        <div class="row">
            <div class="col-12 text-center">
                <button class="see-more-button btn btn-primary btn-lg btn-page btn-loading d-none" onclick="loadMoreProduct()">
                    <img src="file/icon/gifdot.gif" alt="gif-load" class="d-none">
                    <span class="d-block">Xem thêm</span>
                </button>
            </div>
        </div>
    </div>
</section>

