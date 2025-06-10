<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 9/11/2020
  Time: 8:23 PM
  To change this template use File | Settings | File Templates.
--%>
<%--
  Created by IntelliJ IDEA.
  User: hieuv
  Date: 03-Aug-20
  Time: 1:49 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<script type="text/javascript" src="resources/js/ajax/product/product_type.js"></script>

<main class="app-content">
    <div class="card">
        <div class="table-responsive" style="overflow-x:auto;overflow-y: auto">
            <div class="title-table"></div>
            <div class="row text-md-left d-flex justify-content-md-start"
                 style="width: 100%; justify-content: center; margin-top: 20px;">
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
                    <th>Loại sản phẩm</th>
                    <th>Hiển thị trang chủ</th>
                    <th><i class="fas fa-cog"></i></th>
                </tr>
                </thead>
                <tbody style="" id="row-ajax">

                </tbody>
            </table>

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
                <h5 class="modal-title">Thông tin loại sản phẩm</h5>
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
                                        <label class="col-form-label">Tên loại sản phẩm<span
                                                style="color: red">*</span></label>
                                        <input id="input-name" class="form-control here" type="text">
                                    </div>


                                    <div class="form-group col-lg-2">
                                        <label class="col-form-label">Thứ tự<span
                                                style="color: red"></span></label>
                                        <input id="input-stt" value="0" min="0"
                                               class="form-control here" required="required" type="number">
                                    </div>
                                    <div class="form-group col-lg-2">
                                        <label class="col-form-label">Hiện trang chủ<span
                                                style="color: red"></span></label>
                                        <select class="form-control" id="enable-view">
                                            <option value="true">Có</option>
                                            <option value="false">Không</option>
                                        </select>
                                    </div>
                                </div>

                                <div class="row" style="justify-content: space-between">
                                    <div class="form-group col-lg-6">
                                        <label class="col-form-label">Tải lên icon <span
                                                style="color: red"></span></label>
                                        <div class="input-group" style="display: none">
                                            <div class="custom-file">
                                                <form method="post" enctype="multipart/form-data">
                                                    <input type="file" class="custom-file-input"
                                                           id="choose-icon">
                                                </form>
                                            </div>
                                        </div>
                                        <div class="row"
                                             style="justify-content: center; margin-top: 5%">
                                            <img id="icon-preview" src="resources/image/upload-file.png"
                                                 style="max-width: 20%"
                                                 onclick="$('#choose-icon').trigger('click')">
                                        </div>
                                    </div>
                                    <div class="form-group col-lg-6">
                                        <label class="col-form-label">Tải lên ảnh <span
                                                style="color: red"></span></label>
                                        <div class="input-group" style="display: none">
                                            <div class="custom-file">
                                                <form method="post" enctype="multipart/form-data">
                                                    <input type="file" accept="image/*" class="custom-file-input"
                                                           id="choose-image">
                                                </form>
                                            </div>
                                        </div>
                                        <div class="row"
                                             style="justify-content: center; margin-top: 5%">
                                            <img id="img-preview" class="col-10" src="resources/image/camera2.png"
                                                 alt="Hình ảnh"
                                                 onclick="$('#choose-image').trigger('click')">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group row" style="justify-content: space-between">
                                    <div class="form-group col-lg-12">
                                        <label class="col-form-label">Mô tả danh mục<span
                                                style="color: red"></span></label>
                                        <textarea id="input-preview" rows="4"
                                                  class="form-control here" required="required"
                                                  type="text"></textarea>
                                    </div>
                                </div>
                                <div class="form-group col-lg-12"
                                     style=" display: flex;justify-content: space-between">
                                    <label class="col-form-label">Đường dẫn thay thế<span
                                            style="color: red"> *</span></label>
                                    <input id="input-alias" type="text" class="form-control col-lg-5"
                                           placeholder="Viết liền không dấu">
                                    <button id="btn-generate-alias" class="btn-primary form-control col-lg-2">Tạo tự
                                        động
                                    </button>
                                    <button id="btn-check-alias" class="btn-primary form-control col-lg-2">Kiểm tra
                                    </button>
                                </div>


                                <div class="row" style=" padding-top: 10px; justify-content: center">
                                    <div class="col-lg-3" style=" display: flex; justify-content: center">
                                        <button id="submit" type="button" class="btn btn-success">&nbsp;Lưu lại
                                        </button>
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
                    <h4 class="modal-title font-weight-normal"><i class="fas fa-trash-alt"></i>&nbsp;Xóa loại sản
                        phẩm
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

