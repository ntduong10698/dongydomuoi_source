<%--
  Created by IntelliJ IDEA.
  User: hieuv
  Date: 16-Aug-20
  Time: 10:40 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript" src="resources/js/ajax/contact/contact.js"></script>
<main class="app-content">
    <div class="card">
        <div class="table-responsive" style="overflow-x:auto;overflow-y: auto">
            <div class="title-table"></div>
            <div class="row" style="width: 100%;padding-bottom: 15px">
                <div class="search-by-table col-lg-3" style="margin-top: 10px">
                    <select class="form-control" id="select-checked">
                        <option value="0">Tất cả trạng thái</option>
                        <option value="1">Đã liên hệ</option>
                        <option value="2">Chưa liên hệ</option>
                    </select>
                </div>
            </div>
            <table class="table text-center table-bordered table-sm" style="">
                <thead>
                <tr class="active my-th">
                    <th>Mã</th>
                    <th>Khách hàng</th>
                    <th>Điện thoại</th>
                    <th>Email</th>
                    <th>Trạng thái</th>
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

<div id="detail-modal" class="modal fade" data-backdrop="static" role="dialog">
    <div class="modal-dialog mw-100 w-75">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Thông tin liên hệ</h5>
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
                                    <div class="form-group col-lg-8">
                                        <label class="col-form-label">Khách hàng<span
                                                style="color: red"></span></label>
                                        <input id="input-name" class="form-control here" type="text" disabled>
                                    </div>
                                    <div class="form-group col-lg-4">
                                        <label class="col-form-label">Điện thoại<span
                                                style="color: red"></span></label>
                                        <input id="input-phone" class="form-control here" type="text" disabled>
                                    </div>
                                </div>

                                <div class="row" style="justify-content: space-between">
                                    <div class="form-group col-lg-8">
                                        <label class="col-form-label">Email<span
                                                style="color: red"></span></label>
                                        <input id="input-email" class="form-control here" type="text" disabled>
                                    </div>
                                    <div class="form-group col-lg-4">
                                        <label class="col-form-label">Tình trạng<span
                                                style="color: red">*</span></label>
                                        <select id="select-check" class="form-control here">
                                            <option value="true">Đã liên hệ</option>
                                            <option value="false">Chưa liên hệ</option>
                                        </select>
                                    </div>
                                </div>

                                <div class="row" style="justify-content: space-between">
                                    <div class="form-group col-lg-12">
                                        <label class="col-form-label">Nội dung<span
                                                style="color: red"></span></label>
                                        <textarea id="input-content" class="form-control here" type="text" rows="4" disabled>
                                        </textarea>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="padding: 10px 10px">
                <div class="row" style=" justify-content: center">
                    <div class="col-lg-3" style=" display: flex; justify-content: center">
                        <button id="submit" type="button" class="btn btn-danger">&nbsp;Xác nhận</button>
                        <button id="close" style="margin-left: 5%" type="button" class="btn btn-secondary"
                                data-dismiss="modal">
                            &nbsp;Hủy bỏ
                        </button>
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
                    <h4 class="modal-title font-weight-normal"><i class="fas fa-trash-alt"></i>&nbsp;Xóa liên hệ
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
