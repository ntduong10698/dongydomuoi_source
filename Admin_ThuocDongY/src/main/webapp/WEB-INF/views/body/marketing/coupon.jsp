<%--
  Created by IntelliJ IDEA.
  User: hieuv
  Date: 06-Aug-20
  Time: 3:53 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<script type="text/javascript" src="http://js.nicedit.com/nicEdit-latest.js"></script>--%>
<script type="text/javascript" src="resources/js/ajax/marketing/coupon.js"></script>

<main class="app-content">
    <div class="card">
        <div class="table-responsive" style="overflow-x:auto;overflow-y: auto">
            <div class="title-table"></div>
            <div class="row" style="width: 100%; justify-content: left; padding-bottom: 15px">
                <div class="search-by-table col-lg-2" style="margin-top: 10px">
                    <button id="btn-add" class="btn btn-primary col-lg-12" type="button" data-toggle="modal"
                            data-target="#myModal">
                        <a style="color: white">
                            <i class="fa fa-fw fa-lg fa-plus-circle"></i>Thêm</a>
                    </button>
                </div>

                <%--                <div class="search-by-table col-lg-4" style="margin-top: 10px">--%>
                <%--                    <input class="form-control" id="text-search" type="search"--%>
                <%--                           placeholder="Nhập mã khuyến mãi">--%>
                <%--                </div>--%>

                <%--                <div class="search-by-table col-lg-1" style="margin-top: 10px">--%>
                <%--                    <button id="btn-search" style="width: 100%" class="btn btn-primary"><i--%>
                <%--                            class="fa fa-fw fa-lg fa-search"></i>--%>
                <%--                    </button>--%>
                <%--                </div>--%>

            </div>
            <table class="table text-center table-bordered table-sm" style="">
                <thead>
                <tr class="active my-th">
                    <th>Mã</th>
                    <th>Mã khuyến mãi</th>
                    <th>Loại</th>
                    <th>Hiện thị</th>
                    <th>Ngày kết thúc</th>
                    <th>Số lượng</th>
                    <th>Đã dùng</th>
                    <th><i class="fas fa-cog" title="Chức năng"></i></th>
                </tr>
                </thead>
                <tbody style="" id="row-ajax">

                </tbody>
            </table>

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
                <h5 class="modal-title">Thông tin mã khuyến mãi</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="row" style="justify-content: center">
                            <div class="col-lg-12">
                                <div class="form-group row" style="justify-content: flex-start">
                                    <div class="form-group col-lg-9">
                                        <label class="col-form-label">Mã khuyến mãi<span
                                                style="color: red">*</span></label>
                                        <input id="input-code" class="form-control here" placeholder="Để trống để tạo mã tự động" type="text">
                                    </div>
                                    <div class="form-group col-lg-3">
                                        <label class="col-form-label">Loại khuyến mãi<span
                                                style="color: red"></span></label>
                                        <select id="input-loai-khuyen-mai" class="form-control">
                                            <option value="1">Quà tặng</option>
                                            <option value="2">Giảm tiền</option>
                                            <option value="3">Giảm phần trăm</option>
                                        </select>
                                    </div>
                                </div>

                                <div class="form-group row" style="justify-content: flex-start">
                                    <div class="form-group col-lg-3">
                                        <label class="col-form-label">Hiển thị<span
                                                style="color: red"></span></label>
                                        <select id="input-global" class="form-control">
                                            <option value="1">Hiển thị</option>
                                            <option value="0">Ẩn</option>
                                        </select>
                                    </div>

                                    <div class="form-group col-lg-3" id="div-decrease">
                                        <label class="col-form-label" id="label-decrease">Giá trị khuyến mãi<span
                                                style="color: red"> *</span></label>
                                        <input id="input-gtkm"
                                               class="form-control here" required="required" type="number">
                                    </div>

                                    <div class="form-group col-lg-3" id="div-max">
                                        <label class="col-form-label">Số tiền giảm tối đa<span
                                                style="color: red"> *</span></label>
                                        <input id="input-gttd" min="1000" step="1000"
                                               class="form-control here" required="required" type="number">
                                    </div>

                                    <div class="form-group col-lg-3" id="div-min">
                                        <label class="col-form-label">Giá trị đơn hàng tối thiểu<span
                                                style="color: red"></span></label>
                                        <input id="input-gttt" min="0" step="1000"
                                               class="form-control here" required="required" type="number">
                                    </div>
                                </div>
                                <div class="form-group row " style="justify-content: start">
                                    <div class="form-group col-lg-12">
                                        <label class="col-form-label">Nội dung khuyến mãi<span
                                                style="color: red"> *</span></label>
                                        <textarea id="input-content" rows="3"
                                                  class="form-control here" required="required" type="text"></textarea>
                                    </div>
                                </div>

                                <div class="form-group row " style="justify-content: start" id="wrap-more-info">
                                    <div class="form-group col-lg-4">
                                        <label class="col-form-label">Thời gian áp dụng<span
                                                style="color: red"></span></label>
                                        <select id="input-tgad" class="form-control">
                                            <option value="1">Giới hạn</option>
                                            <option value="0">Vô thời hạn</option>
                                        </select>
                                    </div>

                                    <div class="form-group col-lg-4" id="wrap-ngay-kt">
                                        <label class="col-form-label">Ngày kết thúc<span
                                                style="color: red">*</span></label>
                                        <input id="input-end-date"
                                               class="form-control here" required="required" type="datetime-local">
                                    </div>

                                    <div class="form-group col-lg-4">
                                        <label class="col-form-label">Số lượng mã<span
                                                style="color: red">*</span></label>
                                        <input id="input-quantity"
                                               class="form-control here" required="required" type="number">
                                    </div>
                                </div>

                                <div class="row" style=" padding-top: 10px; justify-content: center">
                                    <div class="col-lg-3" style=" display: flex; justify-content: center">
                                        <button id="submit" type="button" class="btn btn-success">&nbsp;Lưu lại</button>
                                        <button id="close-add" style="margin-left: 5%" type="button"
                                                class="btn btn-secondary"
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
    </div>
</div>

<div id="delete-modal" class="modal fade" data-backdrop="static" role="dialog">
    <div class="modal-dialog">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header" style="padding: 10px 10px">
                <div class="row col-lg-12" style="justify-content: center">
                    <h4 class="modal-title font-weight-normal"><i class="fas fa-trash-alt"></i>&nbsp;Xóa mã khuyến mãi
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

