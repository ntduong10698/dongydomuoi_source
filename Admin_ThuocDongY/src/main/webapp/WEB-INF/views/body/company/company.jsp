<%--
  Created by IntelliJ IDEA.
  User: hieuv
  Date: 18-Aug-20
  Time: 5:16 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link href="css/quill.css" rel="stylesheet">
<script type="text/javascript" src="resources/js/quill/quill.js"></script>
<script type="text/javascript" src="resources/js/quill/quill_resize.js"></script>
<script type="text/javascript" src="resources/js/ajax/company/company.js"></script>
<main class="app-content">
    <div class="card">
        <div class="card-header" style="text-align: center">
            <h5>Thông tin doanh nghiệp</h5>
        </div>
        <div class="col-lg-12 card-body">
            <div class="row" style="justify-content: space-between">
                <div class="form-group col-lg-6">
                    <label class="col-form-label">Tên doanh nghiệp<span
                            style="color: red">*</span></label>
                    <input id="input-name" class="form-control here" type="text">
                </div>

                <div class="form-group col-lg-6">
                    <label class="col-form-label">Slogan<span
                            style="color: red"></span></label>
                    <input id="input-slogan" class="form-control here" type="text">
                </div>
            </div>

            <div class="row" style="justify-content: space-between">
                <div class="form-group col-lg-6">
                    <label class="col-form-label">Địa chỉ<span
                            style="color: red">*</span></label>
                    <input id="input-address" class="form-control here" type="text">
                </div>

                <div class="form-group col-lg-3">
                    <label class="col-form-label">Email nhận thông báo<span
                            style="color: red"></span></label>
                    <input id="input-email" class="form-control here" type="text">
                </div>

                <div class="form-group col-lg-3">
                    <label class="col-form-label">Mã số thuế<span
                            style="color: red"></span></label>
                    <input id="input-tax" class="form-control here" type="text">
                </div>

            </div>

            <div class="row" style="justify-content: space-between">

                <div class="form-group col-lg-6">
                    <label class="col-form-label">Giới thiệu<span
                            style="color: red"></span></label>
                    <textarea id="input-description" class="form-control here" type="text" rows="5">
                    </textarea>
                </div>

                <div class="form-group col-lg-6">
                    <label class="col-form-label">Địa chỉ google map<span
                            style="color: red">*</span></label>
                    <textarea id="input-map" class="form-control here" type="text" rows="5">
                    </textarea>
                </div>
            </div>
            <div class="row" style="justify-content: space-around">
                <div class="form-group col-lg-12">
                    <label class="col-form-label">Lịch làm việc<span
                            style="color: red"></span></label>
                    <div id="input-working-time" style="height: 150px; width: 100%"></div>
                </div>
            </div>
            <div class="row" style="justify-content: center">
                <div class="col-lg-3" style=" display: flex; justify-content: center">
                    <button id="submit-company" type="button" class="btn btn-success">&nbsp;Lưu lại</button>
                </div>
            </div>

        </div>
    </div>

    <div class="card" style="margin-top: 2%">
        <div class="card-header" style="text-align: center">
            <h5>Danh sách chi nhánh</h5>
        </div>
        <div class="card">
            <table class="table text-center table-bordered table-sm" style="margin-top: 20px;">
                <thead>
                <tr class="active my-th">
                    <th>STT</th>
                    <th>Tên</th>
                    <th>Địa chỉ</th>
                    <th>Điện thoại</th>
                    <th><i class="fas fa-cog"></i></th>
                </tr>
                </thead>
                <tbody style="" id="row-branch">

                </tbody>
            </table>
            <div class="row" style=" padding-bottom: 10px; justify-content: center">
                <div class="col-lg-2" style=" display: flex; justify-content: space-around">
                    <button id="add-branch" type="button" class="btn btn-primary" data-toggle="modal"
                            data-target="#branch-modal">&nbsp;Thêm mới
                    </button>
                </div>
            </div>
        </div>
    </div>

    <div style="display: flex; justify-content: space-between; margin-top: 2%">
        <div class="card" style="width: 49%">
            <div class="card-header" style="text-align: center">
                <h5>Thông tin liên hệ</h5>
            </div>
            <div class="card">
                <table class="table text-center table-bordered table-sm" style="margin-top: 20px;">
                    <thead>
                    <tr class="active my-th">
                        <th>STT</th>
                        <th>Tên</th>
                        <th>Nội dung</th>
                    </tr>
                    </thead>
                    <tbody style="" id="row-info">

                    </tbody>
                </table>
                <div class="row" style=" padding-bottom: 10px; justify-content: center">
                    <div class="col-lg-3" style=" display: flex; justify-content: center">
                        <button id="submit-info" type="button" class="btn btn-success">&nbsp;Lưu lại</button>
                    </div>
                </div>
            </div>
        </div>
        <div class="card" style="width: 49%">
            <div class="card-header" style="text-align: center">
                <h5>Thông tin chuyển khoản</h5>
            </div>
            <div class="card">
                <table class="table text-center table-bordered table-sm" style="margin-top: 20px;">
                    <thead>
                    <tr class="active my-th">
                        <th>STT</th>
                        <th>Ngân hàng</th>
                        <th>Tài khoản</th>
                        <th>Số tài khoản</th>
                        <th><i class="fas fa-cog"></i></th>
                    </tr>
                    </thead>
                    <tbody style="" id="row-bank">

                    </tbody>
                </table>
                <div class="row" style=" padding-bottom: 10px; justify-content: center">
                    <div class="col-lg-5" style=" display: flex; justify-content: space-around">
                        <button id="add-bank" type="button" class="btn btn-primary">&nbsp;Thêm mới</button>
                        <button id="submit-bank" type="button" class="btn btn-success">&nbsp;Lưu lại</button>
                    </div>
                </div>

            </div>
        </div>
    </div>

</main>

<div id="branch-modal" class="modal fade" data-backdrop="static" role="dialog">
    <div class="modal-dialog modal-lg">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Thông tin chi nhánh</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="row" style="justify-content: center">
                            <div class="col-lg-12">
                                <div class="form-group row" style="justify-content: space-between">
                                    <div class="form-group col-lg-6">
                                        <label class="col-form-label">Tên chi nhánh<span
                                                style="color: red">*</span></label>
                                        <input id="input-branch-name" class="form-control here" type="text">
                                    </div>
                                    <div class="form-group col-lg-6">
                                        <label class="col-form-label">Số điện thoại<span
                                                style="color: red"></span></label>
                                        <input id="input-branch-phone" class="form-control here" type="number">
                                    </div>
                                </div>
                            </div>


                            <div class="col-lg-12">
                                <div class="form-group row" style="justify-content: space-between">
                                    <div class="form-group col-lg-6">
                                        <label class="col-form-label">Địa chỉ<span
                                                style="color: red">*</span></label>
                                        <textarea id="input-branch-address" rows="4"
                                                  class="form-control here" required="required" type="text"></textarea>
                                    </div>
                                    <div class="form-group col-lg-6">
                                        <label class="col-form-label">Google map<span
                                                style="color: red"></span></label>
                                        <textarea id="input-branch-map" rows="4"
                                                  class="form-control here" required="required" type="text"></textarea>
                                    </div>
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

<div id="delete-modal" class="modal fade" data-backdrop="static" role="dialog">
    <div class="modal-dialog">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header" style="padding: 10px 10px">
                <div class="row col-lg-12" style="justify-content: center">
                    <h4 class="modal-title font-weight-normal"><i class="fas fa-trash-alt"></i>&nbsp;Xóa Chi nhánh
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


