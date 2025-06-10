<%--
  Created by IntelliJ IDEA.
  User: hieuv
  Date: 14-Aug-20
  Time: 4:14 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript" src="resources/js/ajax/bill/transport.js"></script>
<main class="app-content">

    <div class="card">
        <button class="btn btn-success" data-toggle="modal" data-target="#myModal">Giao hàng</button>
    </div>
</main>

<div id="myModal" class="modal fade" data-backdrop="static" role="dialog">
    <div class="modal-dialog mw-100 w-75">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Thông tin vận chuyển</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">

                <div class="col-lg-12">
                    <div class="row" style="justify-content: space-between">
                        <div class="form-group col-lg-6">
                            <label class="col-form-label">Từ tỉnh<span
                                    style="color: red">*</span></label>
                            <select id="select-from-city" class="form-control here">
                            </select>
                        </div>

                        <div class="form-group col-lg-6">
                            <label class="col-form-label">Từ huyện<span
                                    style="color: red"></span></label>
                            <select id="select-from-district" class="form-control here">
                            </select>
                        </div>
                    </div>

                    <div class="row" style="justify-content: space-between">
                        <div class="form-group col-lg-6">
                            <label class="col-form-label">Đến tỉnh<span
                                    style="color: red">*</span></label>
                            <select id="select-to-city" class="form-control here">
                            </select>
                        </div>

                        <div class="form-group col-lg-6">
                            <label class="col-form-label">Đến huyện<span
                                    style="color: red"></span></label>
                            <select id="select-to-district" class="form-control here">
                            </select>
                        </div>
                    </div>

                    <div class="form-group row" style="justify-content: space-between">
                        <div class="form-group col-lg-12">
                            <label class="col-form-label">Giá trị đơn hàng (VNĐ)<span
                                    style="color: red"> *</span></label>
                            <input id="input-price" value="0" min="0"
                                   class="form-control here" type="number">
                        </div>
                    </div>

                    <div class="form-group row" style="justify-content: space-between">
                        <div class="form-group col-lg-3">
                            <label class="col-form-label">Chiều dài (cm)<span
                                    style="color: red"> *</span></label>
                            <input id="input-length" value="10" min="0"
                                   class="form-control here" type="number">
                        </div>
                        <div class="form-group col-lg-3">
                            <label class="col-form-label">Chiều rộng (cm)<span
                                    style="color: red"> *</span></label>
                            <input id="input-width" value="10" min="0"
                                   class="form-control here" type="number">
                        </div>
                        <div class="form-group col-lg-3">
                            <label class="col-form-label">Chiều cao (cm)<span
                                    style="color: red"> *</span></label>
                            <input id="input-height" value="0" min="0"
                                   class="form-control here" type="number">
                        </div>
                        <div class="form-group col-lg-3">
                            <label class="col-form-label">Khối lượng (gram)<span
                                    style="color: red"> *</span></label>
                            <input id="input-weight" value="0" min="0"
                                   class="form-control here" type="number">
                        </div>
                    </div>

                </div>
                <div class="row" style=" padding-top: 10px; justify-content: center">
                    <div class="col-lg-3" style=" display: flex; justify-content: center">
                        <button id="calculate" type="button" class="btn btn-success" >&nbsp;Tính toán
                        </button>
                        <button id="close-lesson-model" style="margin-left: 5%" type="button"
                                class="btn btn-secondary"
                                data-dismiss="modal">Quay lại
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
