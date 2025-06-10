<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="css/thanh_toan.css">
<script src="plugins/clipboard/clipboard.min.js"></script>
<script src="https://cdn.bksoftwarevn.com/resources/library_js/ajax_micro_service/ajax_marketing_service.js"></script>
<script src="https://cdn.bksoftwarevn.com/resources/library_js/ajax_micro_service/ajax_bill_service.js"></script>
<script src="ajax/pages/page_thanh_toan.js"></script>
<!-- Modal -->
<div class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true" data-backdrop="static">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h3 class="modal-title" id="exampleModalLongTitle">Thông tin đơn hàng</h3>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body" id="view-infor-bill">
                <table class="table table-sm d-none mt-2" id="table-chuyen-khoan">
                    <thead>
                    <tr>
                        <th scope="col">NGÂN HÀNG</th>
                        <th scope="col">SỐ TÀI KHOẢN</th>
                        <th scope="col">CHỦ TÀI KHOẢN</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <th scope="row" class="name-bank"></th>
                        <td class="account-bank"></td>
                        <td class="owner-account-bank"></td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
<section class="payment">
    <div class="container">
        <div class="row">
            <div class="col-12 col-md-7 cart-bor">
                <div class="customer-detail">
                    <h3 class="cd-title">Thông tin thanh toán</h3>
                    <div class="customer-field">
                        <div class="row">
                            <div class="col-12">
                                <div class="filed-item">
                                    <label>Họ Tên (*)</label>
                                    <input type="text" class="form-control" id="name">
                                    <div class="invalid-feedback">
                                        Looks good!
                                    </div>
                                </div>
                            </div>
                            <%--                            <div class="col-12">--%>
                            <%--                                <div class="filed-item">--%>
                            <%--                                    <label for="">Tên công ty (tùy chọn)</label>--%>
                            <%--                                    <input type="text" class="form-control" id="">--%>
                            <%--                                    <div class="invalid-feedback">--%>
                            <%--                                        Looks good!--%>
                            <%--                                    </div>--%>
                            <%--                                </div>--%>
                            <%--                            </div>--%>
                            <%--                            <div class="col-12">--%>
                            <%--                                <div class="filed-item">--%>
                            <%--                                    <label for="">Quốc gia (*)</label>--%>
                            <%--                                    <select name="" id="">--%>
                            <%--                                        <option value="vn">Việt Nam</option>--%>
                            <%--                                        <option value="jp">Nhật Bản</option>--%>
                            <%--                                    </select>--%>
                            <%--                                </div>--%>
                            <%--                            </div>--%>
                            <div class="col-12">
                                <div class="filed-item">
                                    <label>Địa chỉ (*)</label>
                                    <input type="text" class="form-control" id="address">
                                    <div class="invalid-feedback">
                                        Looks good!
                                    </div>
                                </div>
                            </div>
                            <%--                            <div class="col-12">--%>
                            <%--                                <div class="filed-item">--%>
                            <%--                                    <label for="">Mã bưu điện (Tùy chọn)</label>--%>
                            <%--                                    <input type="text" class="form-control" id="">--%>
                            <%--                                    <div class="invalid-feedback">--%>
                            <%--                                        Looks good!--%>
                            <%--                                    </div>--%>
                            <%--                                </div>--%>
                            <%--                            </div>--%>
                            <div class="col-12">
                                <div class="filed-item">
                                    <label>Tỉnh/Thành phố (*)</label>
                                    <input type="text" class="form-control" id="province">
                                    <div class="invalid-feedback">
                                        Looks good!
                                    </div>
                                </div>
                            </div>
                            <div class="col-12">
                                <div class="filed-item">
                                    <label>Số điện thoại (*)</label>
                                    <input type="text" class="form-control" id="phone-number">
                                    <div class="invalid-feedback">
                                        Looks good!
                                    </div>
                                </div>
                            </div>
                            <div class="col-12">
                                <div class="filed-item">
                                    <label>Địa chỉ email (*)</label>
                                    <input type="text" class="form-control" id="email">
                                    <div class="invalid-feedback">
                                        Looks good!
                                    </div>
                                </div>
                            </div>
                            <div class="col-12">
                                <div class="filed-item">
                                    <h3>Thông tin bổ xung</h3>
                                    <label>Ghi chú đơn hàng (Tùy chọn)</label>
                                    <textarea class="form-control" id="note" placeholder="Ghi chú về đơn hàng, ví dụ: thời gian hay chỉ dẫn thời gian, địa chỉ giao hàng cụ thể hơn"></textarea>
                                    <div class="invalid-feedback">
                                        Looks good!
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-12 col-md-5">
                <div class="checkout-order">
                    <div class="checkout-sidebar">
                        <h3>Đơn hàng của bạn</h3>
                        <div id="order_review" class="">
                            <table class="table table-striped">
                                <thead>
                                <tr>
                                    <th>Sản phẩm</th>
                                    <th>Tạm tính</th>
                                </tr>
                                </thead>
                                <tbody id="list-product-total">
                                <tr class="d-none" id="product-total-temp">
                                    <td class="name-number-product"></td>
                                    <td class="cart-totalprice">
                                        <span class="product-total-price"></span>
                                    </td>
                                </tr>
                                </tbody>
                                <tfoot>
                                <tr class="cart-subtotal">
                                    <th>Tạm tính</th>
                                    <td><span id="temp-total-price"></span>
                                    </td>
                                </tr>
                                <tr class="cart-promo">
                                    <th>Khuyến mãi</th>
                                    <td><span id="promo-price"></span>
                                    </td>
                                </tr>
                                <tr class="order-total">
                                    <th>Tổng</th>
                                    <td>
                                        <span class="sum-cost" id="sum-cost"></span>
                                    </td>
                                </tr>
                                </tfoot>
                            </table>
                            <div class="checkout-payment">
                                <ul class="payment_methods ">
                                    <li class="">
                                        <input type="radio" id="payment_method_bacs" value="3" name="payment_method" checked="checked">
                                        <label for="payment_method_bacs">Chuyển khoản qua ngân hàng</label>
                                        <div class="payment_box payment_method_bacs">
                                            <p>Thực hiện thanh toán vào ngay tài khoản ngân hàng của chúng tôi.
                                                Vui lòng sử dụng <strong>Mã Đơn Hàng</strong> của bạn như một phần để tham khảo
                                                khi thanh toán. Đơn hàng của bạn sẽ không được vận chuyển cho
                                                tới khi tiền được gửi vào tài khoản của chúng tôi.</p>
                                        </div>
                                    </li>
                                    <li class="wc_payment_method payment_method_cod">
                                        <input type="radio" id="payment_method_cod" value="2" name="payment_method">
                                        <label for="payment_method_cod">Trả tiền mặt khi giao hàng </label>
                                        <div class="payment_box payment_method_cod" style="display:none;">
                                            <p>Trả tiền mặt khi giao hàng</p>
                                        </div>
                                    </li>
                                </ul>
                                <div class="coupon_current">
                                    <h3 class=""><i class="fas fa-tag"></i> Mã ưu đãi:</h3>
                                    <ul class="curent-code" id="list-coupon">
                                        <li class="d-none" id="coupon-temp"><span class="coupon-name"></span>:&nbsp;<span class="coupon-des"></span></li>
                                    </ul>
                                </div>
                                <div class="place-order">
                                    <div class="checkout_coupon">
                                        <div class="coupon">
                                            <h3 class=""><i class="fas fa-tag"></i> Bạn có mã ưu đãi?</h3>
                                            <input type="text" name="coupon_code" placeholder="Mã ưu đãi" id="text-coupon">
                                            <button class="btn btn-primary btn-lg" id="btn-check-coupon">Áp dụng Coupon</button>
                                        </div>
                                    </div>
                                    <button type="submit" class="btn btn-danger btn-lg btn-loading" id="btn-order">
                                        <img src="file/icon/gifdot.gif" alt="gif-load" class="d-none">
                                        <span class="d-block">Đặt hàng</span>
                                    </button>
                                    <!-- Button trigger modal -->
                                    <button type="button" class="btn btn-success btn-lg d-none" data-toggle="modal" data-target="#exampleModalCenter" id="btn-view-bill">
                                        Xem thông tin đơn hàng
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>