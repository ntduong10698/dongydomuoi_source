<%--
  Created by IntelliJ IDEA.
  User: hieuv
  Date: 06-Aug-20
  Time: 3:05 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<script type="text/javascript" src="resources/js/ajax/edu/doctor.js"></script>

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
            </ul>
            <table class="table text-center table-bordered table-sm" style="">
                <thead>
                <tr class="active my-th">
                    <th>STT</th>
                    <th>Tên bác sĩ</th>
                    <th>Vị trí</th>
                    <th>Hình ảnh</th>
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
                <h5 class="modal-title">Thông tin bác sĩ</h5>
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
                                    <label class="col-form-label">Tên bác sĩ<span
                                            style="color: red">*</span></label>
                                    <input id="input-name" class="form-control here" type="text">
                                </div>
                                    <div class="form-group col-lg-6">
                                    <label class="col-form-label">Vị trí<span
                                            style="color: red">*</span></label>
                                    <input id="input-position" class="form-control here" type="text">
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
                                             style="max-width: 30%" alt="Hình ảnh"
                                             onclick="$('#choose-file').trigger('click')">
                                    </div>
                                </div>
                                <div class="form-group col-lg-6">
                                    <label class="col-form-label">Mô tả<span
                                            style="color: red"></span></label>
                                    <textarea id="input-preview" rows="6"
                                              class="form-control here" required="required" type="text"></textarea>
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
                    <h4 class="modal-title font-weight-normal"><i class="fas fa-trash-alt"></i>&nbsp;Xóa giảng viên
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
