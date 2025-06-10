<%--
  Created by IntelliJ IDEA.
  User: hieuv
  Date: 06-Aug-20
  Time: 3:05 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<script type="text/javascript" src="resources/js/ajax/edu/student_product.js"></script>

<main class="app-content">

    <div class="card">
        <div class="table-responsive" style="overflow-x:auto;overflow-y: auto">
            <div class="title-table"></div>
            <ul class="app-nav row"
                style=" width: 100%; display: flex; justify-content: left; padding-top: 10px; padding-bottom: 10px">
                <li class="col-lg-2">
                    <button id="btn-add" class="btn btn-primary col-lg-12" type="button" data-toggle="modal"
                            data-target="#myModal">
                        <a style="color: white">
                            <i class="fa fa-fw fa-lg fa-plus-circle"></i>Thêm</a>
                    </button>
                </li>
                <li class="col-lg-4">
                    <select id="select-course" class="form-control">

                    </select>
                </li>
                <li class="col-lg-2">
                    <select id="select-index" class="form-control">
                        <option value="0" selected>--Trang chủ--</option>
                        <option value="true">Hiển thị</option>
                        <option value="false">Ản</option>
                    </select>
                </li>
                <li class="col-lg-2">
                    <select id="select-online" class="form-control">
                        <option value="0" selected>--Học online--</option>
                        <option value="true">Hiển thị</option>
                        <option value="false">Ản</option>
                    </select>
                </li>
                <li class="col-lg-2">
                    <select id="select-offline" class="form-control">
                        <option value="0" selected>--Học offline--</option>
                        <option value="true">Hiển thị</option>
                        <option value="false">Ản</option>
                    </select>
                </li>
            </ul>
            <table class="table text-center table-bordered table-sm" style="">
                <thead>
                <tr class="active my-th">
                    <th>Mã</th>
                    <th>Tiêu đề</th>
                    <th>Khóa học</th>
                    <th>Hình ảnh</th>
                    <th>Hiện thị</th>
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

<div id="myModal" class="modal fade" data-backdrop="static" role="dialog">
    <div class="modal-dialog modal-lg">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Sản phẩm học viên</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="row" style="justify-content: center">
                            <div class="col-lg-12 d-lg-flex justify-content-between">
                                <div class="form-group col-lg-6">
                                    <label class="col-form-label">Tiêu đề<span
                                            style="color: red">*</span></label>
                                    <input id="input-name" class="form-control here" type="text">
                                </div>
                                <div class="form-group col-lg-6">
                                    <label class="col-form-label">Khóa học<span
                                            style="color: red">*</span></label>
                                    <select id="input-course" class="form-control here" >
                                    </select>
                                </div>

                            </div>

                            <div class="col-lg-12 d-flex" style="justify-content: space-between">
                                <div class="form-group col-lg-6">
                                    <label class="col-form-label">Tải lên ảnh<span
                                            style="color: red"> *</span></label>
                                    <div class="input-group" style="display: none">
                                        <div class="custom-file">
                                            <form method="post" enctype="multipart/form-data"
                                                  id="form-upload-image">
                                                <input type="file" accept="image/*" class="custom-file-input"
                                                       id="choose-file">
                                            </form>
                                        </div>
                                    </div>
                                    <div id="div-img-image" class="row"
                                         style="justify-content: center;">
                                        <img id="img-preview" src="resources/image/camera2.png"
                                             style="max-width: 200px" alt="Hình ảnh"
                                             onclick="$('#choose-file').trigger('click')">
                                    </div>
                                </div>
                                <div class="form-group col-lg-2">
                                    <label class="col-form-label">Trang chủ<span
                                            style="color: red"></span></label>
                                    <select id="input-index" class="form-control here" >
                                        <option value="false">Ẩn</option>
                                        <option value="true">Hiện</option>
                                    </select>
                                </div>
                                <div class="form-group col-lg-2">
                                    <label class="col-form-label">Khóa online<span
                                            style="color: red"></span></label>
                                    <select id="input-online" class="form-control here" >
                                        <option value="false">Ẩn</option>
                                        <option value="true">Hiện</option>
                                    </select>
                                </div>
                                <div class="form-group col-lg-2">
                                    <label class="col-form-label">Khóa offline<span
                                            style="color: red"></span></label>
                                    <select id="input-offline" class="form-control here" >
                                        <option value="false">Ẩn</option>
                                        <option value="true">Hiện</option>
                                    </select>
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
                    <h4 class="modal-title font-weight-normal"><i class="fas fa-trash-alt"></i>&nbsp;Xóa sản phẩm học viên
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
