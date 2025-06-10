<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 9/9/2020
  Time: 2:09 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<script type="text/javascript" src="resources/js/ajax/product/properties.js"></script>

<main class="app-content">
    <div class="card">
        <div class="table-responsive" style="overflow-x:auto;overflow-y: auto">
            <div class="title-table"></div>
            <div class="row text-md-left d-flex justify-content-md-start"
                 style="width: 100%; justify-content: center; margin-top: 20px;">

                <div class="col-lg-2">
                    <button id="btn-add" class="btn btn-primary" type="button" data-toggle="modal"
                            data-target="#detail-modal">
                        <a style="color: white">
                            <i class="fa fa-fw fa-lg fa-plus-circle"></i>Thêm</a>
                    </button>
                </div>

                <div class="col-lg-4">
                    <select class="form-control" id="select-product-type">
                    </select>
                </div>

            </div>
            <table class="table text-center table-bordered table-sm" style="margin-top: 20px;">
                <thead>
                <tr class="active my-th">
                    <th>STT</th>
                    <th>Tên thuộc tính</th>
                    <th>Bắt buộc</th>
                    <th>Loại</th>
                    <th><i class="fas fa-cog"></i></th>
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
<div id="detail-modal" class="modal fade" data-backdrop="static" role="dialog">
    <div class="modal-dialog modal-lg">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Thông tin thuộc tính</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="row" style="justify-content: center">
                            <div class="col-lg-12">
                                <div class="row" style="justify-content: space-between">
                                    <div class="form-group col-lg-6">
                                        <label class="col-form-label">Tên thuộc tính<span
                                                style="color: red">*</span></label>
                                        <input id="input-name" class="form-control here" type="text">
                                    </div>
                                    <div class="form-group col-lg-6">
                                        <label class="col-form-label">Loại sản phẩm<span
                                                style="color: red"></span></label>
                                        <select class="form-control" id="input-product-type">

                                        </select>
                                    </div>
                                </div>

                                <div class="row" style="justify-content: left">

                                    <div class="form-group col-lg-4">
                                        <label class="col-form-label">Loại thuộc tính<span
                                                style="color: red"></span></label>
                                        <select class="form-control" id="input-type">
                                            <option selected value="2">Lựa chọn</option>
<%--                                            <option value="3">Chọn nhiều</option>--%>
                                            <option value="4">Điền text</option>
                                            <option value="5">Điền số</option>
                                        </select>
                                    </div>
                                    <div class="form-group col-lg-2">
                                        <label class="col-form-label">Bắt buộc<span
                                                style="color: red"></span></label>
                                        <select class="form-control" id="input-require">
                                            <option value="true">Có</option>
                                            <option value="false">Không</option>
                                        </select>
                                    </div>
                                    <div class="form-group col-lg-6" id="div-default">
                                        <label class="col-form-label">Giá trị mặc định<span
                                                style="color: red"></span></label>
                                        <input class="form-control" id="input-default">
                                    </div>
                                </div>

                                <div id="optional-div">

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
                    <h4 class="modal-title font-weight-normal"><i class="fas fa-trash-alt"></i>&nbsp;Xóa danh mục
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

<div id="options-ex" hidden>
    <label  class="col-form-label">Danh sách lựa chọn</label>
    <table class="table text-center table-bordered table-sm">
        <thead>
        <tr class="active my-th">
            <th>STT</th>
            <th>Nội dung</th>
            <th><i class="fas fa-cog"></i></th>
        </tr>
        </thead>
        <tbody style="" id="row-options">

        </tbody>
    </table>
    <div class="row" style=" padding-bottom: 10px; justify-content: center">
        <div class="col-lg-5" style=" display: flex; justify-content: space-around">
            <button id="add-option" type="button" class="btn btn-primary">&nbsp;Thêm</button>
        </div>
    </div>
</div>


