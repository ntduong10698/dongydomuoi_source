<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="css/gio_hang.css">
<script src="plugins/inputSpinnerBootstrap/bootstrap-input-spinner.js"></script>
<script src="ajax/pages/page_gio_hang.js"></script>
<section class="cart">
    <div class="container">
        <div class="row">
            <div class="col-12 col-md-8 cart-bor">
                <div class="cart__items">
                    <table class="table table-striped">
                        <thead>
                        <tr>
                            <th class="product-name" colspan="3">Sản phẩm</th>
                            <th class="product-price d-none d-md-block">Giá</th>
                            <th class="product-quantity">Số lượng</th>
                            <th class="product-subtotal">Tổng</th>
                        </tr>
                        </thead>
                        <tbody id="list-product-cart">
                        <tr class="cart_item d-none" id="cart-product-temp">
                            <td class="cart-premove">
                                <span class="remove" title="Xóa sản phẩm này" onclick="removeProductCart()">×</span>
                            </td>
                            <td class="cart-img">
                                <a href="" class="href-product-cart">
                                    <img src="" alt="" class="img-product-cart">
                                </a>
                            </td>
                            <td class="cart-name">
                                <a href="" class="href-product-cart name-product-cart"></a>
                            </td>
                            <td class="cart-price  d-none d-md-block" data-title="Giá">
                                <span class="cost-product-cart"></span>
                            </td>
                            <td class="cart-quantity" data-title="Số lượng">
                                <div class="quantity buttons_added">
                                    <input type="number" value="1" min="0" max="100" step="1" class="input-product-cart" onchange="inputChangeProductCart()"/>
                                </div>
                            </td>
                            <td class="cart-subtotal" data-title="Tổng">
                                <span class="total-product-cart"></span>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
                <div class="cart__control">
                    <a href="trang-chu" class="btn btn-info btn-lg"><i class="fas fa-long-arrow-alt-left"></i>&nbsp;Tiếp tục mua
                        hàng</a>
                    <%--                    <span class="btn btn-danger">Cập nhật giỏ hàng</span>--%>
                </div>
            </div>
            <div class="col-12 col-md-4">
                <div class="cart_totals ">
                    <table class="table">
                        <thead>
                        <tr>
                            <th class="product-name" colspan="2" style="border-width:3px;">Cart Totals</th>
                        </tr>
                        </thead>
                    </table>
                    <h2 class="d-none">Cộng giỏ hàng</h2>
                    <table class="table">
                        <tbody>
                        <tr class="cart-psubtotal">
                            <th>Tạm tính</th>
                            <td data-title="Tạm tính">
                                <span id="temp-sum-total"></span>
                            </td>
                        </tr>
                        <tr class="order-total">
                            <th>Tổng</th>
                            <td data-title="Tổng">
                                <span id="sum-total"></span>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                    <div class="pay-page">
                        <button class="btn btn-danger btn-lg" onclick="clickBtnThanhToan()">Tiến hành thanh toán</button>
                    </div>
                </div>
                <%--                <form class="checkout_coupon">--%>
                <%--                    <div class="coupon">--%>
                    <%--                        <h3 class=""><i class="fas fa-tag"></i> Phiếu ưu đãi</h3>--%>
                    <%--                        <input type="text" name="coupon_code" class="" id="" placeholder="Mã ưu đãi">--%>
                    <%--                        <button class="btn btn-primary btn-lg">Áp dụng Coupon</button>--%>
                    <%--                    </div>--%>
                <%--                </form>--%>
            </div>
        </div>
    </div>
</section>
