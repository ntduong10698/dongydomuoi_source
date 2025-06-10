<%--
  Created by IntelliJ IDEA.
  User: hieuv
  Date: 03-Aug-20
  Time: 1:49 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<script type="text/javascript" src="resources/js/ajax/article/categoryArticle.js"></script>

<main class="app-content">
    <div class="card">
        <div class="table-responsive" style="overflow-x:auto;overflow-y: auto">
            <div class="title-table"></div>
            <div class="row text-md-left d-flex justify-content-md-start" style="width: 100%; justify-content: center; margin-top: 20px;">
<%--                <div class="search-by-table col-lg-2 " style="">--%>
<%--                    <select class="form-control" id="select-category">--%>
<%--                        &lt;%&ndash;                        <option value="0">Loại danh mục</option>&ndash;%&gt;--%>

<%--                    </select>--%>
<%--                </div>--%>
                <div class="col-lg-2">
                    <button id="btn-add" class="btn btn-primary" type="button" data-toggle="modal"
                            data-target="#myModal">
                        <a style="color: white">
                            <i class="fa fa-fw fa-lg fa-plus-circle"></i>Thêm</a>
                    </button>
                </div>

            </div>
            <table class="table text-center table-bordered table-sm" style="margin-top: 20px;">
                <thead>
                <tr class="active my-th">
                    <th>Số thứ tự</th>
                    <th>Danh mục</th>
                    <th>Mặc định</th>
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
<div id="myModal" class="modal fade" data-backdrop="static" role="dialog">
    <div class="modal-dialog mw-40 w-50">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Thông tin danh mục</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="row" style  ="justify-content: center">
                            <div class="col-lg-12">
                                <div class="row" style="justify-content: space-between">
                                    <div class="form-group col-lg-12">
                                        <label class="col-form-label">Tên danh mục<span
                                                style="color: red">*</span></label>
                                        <input id="input-name" class="form-control here" type="text">
                                    </div>

<%--                                    <div class="form-group col-lg-3">--%>
<%--                                        <label class="col-form-label">Số thứ tự<span--%>
<%--                                                style="color: red"></span></label>--%>
<%--                                        <input id="input-stt" value="0" min="0"--%>
<%--                                               class="form-control here" required="required" type="number">--%>
<%--                                    </div>--%>

<%--                                    <div class="form-group col-lg-3">--%>
<%--                                        <label class="col-form-label">Loại danh mục<span--%>
<%--                                                style="color: red">*</span></label>--%>
<%--                                        <select class="form-control" id="modal-select-category">--%>
<%--                                            <option value="0">Loại danh mục</option>--%>
<%--                                        </select>--%>

<%--                                    </div>--%>
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

