<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<link href="css/quill.css" rel="stylesheet">
<script type="text/javascript" src="resources/js/template/print.js"></script>
<script type="text/javascript" src="resources/js/ajax/bill/cart.js"></script>
<main class="app-content">

    <div class="card">
        <div class="table-responsive" style="overflow-x:auto;overflow-y: auto">
            <div class="title-table"></div>
            <div class="col-lg-12" style="display: flex; justify-content: center;margin-bottom: 10px">
                <%--                <div class="search-by-table col-lg-1" style="margin-top: 10px; padding-left: unset">--%>
                <%--                    <button id="btn-add" class="btn btn-primary" type="button" data-toggle="modal"--%>
                <%--                            data-target="#myModal">--%>
                <%--                        <a style="color: white">--%>
                <%--                            <i class="fa fa-fw fa-lg fa-plus-circle"></i>Thêm</a>--%>
                <%--                    </button>--%>
                <%--                </div>--%>
                <div class="search-by-table col-lg-3" style="margin-top: 10px">
                    <select class="form-control" id="select-status">
                    </select>
                </div>

                <div class="search-by-table col-lg-2" style="margin-top: 10px">
                    <select class="form-control" id="select-date">
                        <option value="false">Mới nhất</option>
                        <option value="true">Cũ nhất</option>
                    </select>
                </div>

                <div class="search-by-table col-lg-2" style="margin-top: 10px">
                    <input class="form-control" id="text-code" type="search"
                           placeholder="Mã đơn hàng">
                </div>

                <div class="search-by-table col-lg-4" style="margin-top: 10px">
                    <input class="form-control" id="text-search" type="search"
                           placeholder="Tên ,email ,điện thoại khách hàng">
                </div>

                <div class="search-by-table col-lg-1" style="margin-top: 10px; padding-right: unset;">
                    <button id="btn-search" style="width: 100%" class="btn btn-primary"><i
                            class="fa fa-fw fa-lg fa-search"></i>
                    </button>
                </div>
            </div>
            <table class="table text-center table-bordered table-sm" style="">
                <thead>
                <tr class="active my-th">
                    <th>STT</th>
                    <th>Mã số</th>
                    <th>Khách hàng</th>
                    <th>Số điện thoại</th>
                    <th>Giá trị</th>
                    <th>Thời gian</th>
                    <th>Tình trạng</th>
                    <th><i class="fas fa-cog"></i></th>
                </tr>
                </thead>
                <tbody style="" id="row-ajax">

                </tbody>
            </table>

            <div class="col-lg-12" style="display: flex; justify-content: center;margin-top: 5px">
                <button id="check-all" class="col-lg-3 btn btn-success">Đánh dấu tất cả đã kiểm tra</button>
            </div>

            <div id="page">
            </div>
        </div>
    </div>
</main>

<!-- Modal -->
<%--upload modal--%>
<div id="myModal" class="modal fade" data-backdrop="static" role="dialog">
    <div class="modal-dialog mw-100 w-75">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Thông tin đơn hàng</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="row" style="justify-content: center" id="print-div">
                            <div class="col-lg-12">
                                <div class="row" style="justify-content: space-between">
                                    <div class="form-group col-lg-6">
                                        <label class="col-form-label">Tên khách hàng<span
                                                style="color: red">*</span></label>
                                        <input id="input-name" class="form-control here" disabled type="text">
                                    </div>

                                    <div class="form-group col-lg-3">
                                        <label class="col-form-label">Số điện thoại<span
                                                style="color: red">*</span></label>
                                        <input id="input-phone"
                                               class="form-control here" required="required" disabled type="number">
                                    </div>

                                    <div class="form-group col-lg-3">
                                        <label class="col-form-label">Email<span
                                                style="color: red">*</span></label>
                                        <input id="input-email"
                                               class="form-control here" required="required" disabled type="email">
                                    </div>
                                </div>

                                <div class="row" style="justify-content: space-between">
                                    <div class="form-group col-lg-6">
                                        <label class="col-form-label">Địa chỉ nhận hàng<span
                                                style="color: red"> *</span></label>
                                        <input id="input-address"
                                               class="form-control here" required="required" disabled type="text">
                                    </div>
                                    <div class="form-group col-lg-3">
                                        <label class="col-form-label">Thời gian<span
                                                style="color: red"></span></label>
                                        <input id="input-time" disabled
                                               class="form-control here" required="required" type="text">
                                    </div>
                                    <div class="form-group col-lg-3">
                                        <label class="col-form-label">Tình trạng<span
                                                style="color: red"></span></label>
                                        <select class="form-control" id="select-status-input">

                                        </select>
                                    </div>
                                </div>

                                <div class="form-group row" style="justify-content: space-between">

                                    <div class="form-group col-lg-6">
                                        <label class="col-form-label">Ghi chú hóa đơn<span
                                                style="color: red"></span></label>
                                        <textarea id="input-note" rows="4"
                                                  class="form-control here" required="required" type="text"></textarea>
                                    </div>
                                    <div class="form-group col-lg-6">
                                        <label class="col-form-label">Khuyến mãi<span
                                                style="color: red"></span></label>
                                        <div id="input-promo"></div>
                                    </div>
                                </div>

                                <div class="card" style="width: 100%; margin-bottom: 5px">
                                    <div class="card-header my-header" style="height: 40px">
                                        <button style="padding: unset" is-showing="1"
                                                class="btn float-left btn-collapse text-white col-lg-12"
                                                data-toggle="collapse"
                                                data-target="#collapse-div" aria-expanded="true"
                                                aria-controls="#collapse-div">
                                            Danh sách sản phẩm
                                        </button>
                                    </div>
                                    <div class="card-body collapse show" id="collapse-div">
                                        <table class="table text-center table-bordered table-sm" style="">
                                            <thead>
                                            <tr class="active my-th">
                                                <th>Hình ảnh</th>
                                                <th>Mã</th>
                                                <th>Sản phẩm</th>
                                                <th>Giá gốc</th>
                                                <th>Giá bán</th>
                                                <th>Số lượng</th>
                                                <th>Thành tiền</th>
                                                <th>Ghi chú</th>
                                            </tr>
                                            </thead>
                                            <tbody style="" id="row-products">

                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                                <div class=" col-lg-12">
                                    <p style="color: red; font-size: 24px" id="total-momey"></p>
                                </div>

                            </div>
                        </div>
                        <div class="row" style=" padding-top: 10px; justify-content: center">
                            <div class="col-lg-5" style=" display: flex; justify-content: space-between">
                                <button id="submit" type="button" class="btn btn-success col-lg-3">&nbsp;Lưu lại
                                </button>

                                <button id="btn-print" type="button" class="btn btn-success col-lg-3">&nbsp;In hóa đơn
                                </button>

                                <button id="close-add" style="" type="button"
                                        class="btn btn-secondary col-lg-3"
                                        data-dismiss="modal">Quay lại
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<div id="delete-modal" class="modal fade" data-backdrop="static" role="dialog">
    <div class="modal-dialog">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header" style="padding: 10px 10px">
                <div class="row col-lg-12" style="justify-content: center">
                    <h4 class="modal-title font-weight-normal"><i class="fas fa-trash-alt"></i>&nbsp;Xóa sản phẩm
                    </h4>
                </div>
            </div>
            <div class="modal-body">
                <span class="font-weight-normal">Bạn có chắc chắn muốn xóa không?</span>
            </div>
            <div class="modal-footer" style="padding: 10px 10px">
                <div class="row" style=" justify-content: center">
                    <div class="col-lg-3" style=" display: flex; justify-content: center">
                        <button id="submit-delete" type="button" class="btn btn-danger">&nbsp;Xác nhận</button>
                        <button id="close-delete" style="margin-left: 5%" type="button" class="btn btn-secondary"
                                data-dismiss="modal">
                            &nbsp;Hủy bỏ
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
