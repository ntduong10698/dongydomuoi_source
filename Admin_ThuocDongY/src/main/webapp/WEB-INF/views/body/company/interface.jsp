<%--
  Created by IntelliJ IDEA.
  User: hieuv
  Date: 22-Aug-20
  Time: 10:54 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript" src="resources/js/ajax/company/interface.js"></script>
<main class="app-content">
    <div class="card">
        <div class="card-header" style="display: flex; justify-content: space-around">
            <div class="col-lg-3"></div>
            <h5 class="colg-lg-4">Chỉnh sửa giao diện</h5>
            <select class="form-control col-lg-3" id="select-part">

            </select>
            <div class="col-lg-2"></div>
        </div>
        <div class="col-lg-12 card-body">

            <table class="table text-center table-bordered table-sm" style="margin-top: 20px;">
                <thead>
                <tr class="active my-th">
                    <th>STT</th>
                    <th>Vị trí</th>
                    <th>Hình ảnh</th>
                    <th>Nội dung</th>
                    <th><i class="fas fa-cog"></i></th>
                </tr>
                </thead>
                <tbody style="" id="row-ajax">

                </tbody>
            </table>

        </div>
    </div>
</main>

<div id="detail-modal" class="modal fade" data-backdrop="static" role="dialog">
    <div class="modal-dialog modal-lg">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Chỉnh sửa nội dung</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="row" style="justify-content: center">
                            <div class="col-lg-12">
                                <label class="col-form-label">Vị trí<span
                                        style="color: red">*</span></label>
                                <input id="input-position" class="form-control here" type="text">
                            </div>
                            <div id="if-image" style="display: contents">
                                <div class="form-group col-lg-12" >
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
                                         style="justify-content: center; margin-top: 5%">
                                        <img id="img-preview" src="resources/image/camera2.png"
                                             style="width: 200px" alt="Hình ảnh"
                                             onclick="$('#choose-file').trigger('click')">
                                    </div>
                                </div>
                                <div class="col-lg-12">
                                    <label class="col-form-label">Đường dẫn khi click<span
                                            style="color: red"></span></label>
                                    <input id="input-link" class="form-control here" type="text">
                                </div>
                            </div>
                            <div class="form-group col-lg-12" id="if-text">
                                <label class="col-form-label">Nội dung<span
                                        style="color: red"></span></label>
                                <textarea id="input-text" rows="6"
                                          class="form-control here" required="required" type="text"></textarea>
                            </div>
                            <div class="col-lg-12" id="if-video">
                                <div class="form-group row" style="justify-content: space-between">
                                    <div class="form-group col-lg-6">
                                        <label class="col-form-label">Tải lên video (mp4)<span
                                                style="color: red"> *</span></label>
                                        <div class="input-group" style="display: none">
                                            <div class="custom-file">
                                                <form method="post" enctype="multipart/form-data"
                                                      id="form-upload-video">
                                                    <input type="file" accept="video/mp4"
                                                           id="choose-file-video">
                                                </form>
                                            </div>
                                        </div>
                                        <div class="row"
                                             style="justify-content: center; margin-top: 5%">
                                            <img src="resources/image/camera2.png" id="img-choose-video"
                                                 style="max-width: 20%"
                                                 onclick="$('#choose-file-video').trigger('click')">
                                        </div>
                                        <label class="col-form-label">Hoặc nhập URL (Youtube embed):<span
                                                style="color: red"></span></label>
                                        <div class="input-group">
                                            <input id="input-youtube" class="form-control"
                                                   placeholder="https://www.youtube.com/embed/...">
                                        </div>
                                    </div>
                                    <div class="form-group col-lg-6">
                                        <label class="col-form-label">Video <span
                                                style="color: red"></span></label>
                                        <div id="div-video">

                                        </div>
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
