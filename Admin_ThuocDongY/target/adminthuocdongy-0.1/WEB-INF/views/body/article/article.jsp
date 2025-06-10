<%--
  Created by IntelliJ IDEA.
  User: hieuv
  Date: 06-Aug-20
  Time: 3:53 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<script type="text/javascript" src="http://js.nicedit.com/nicEdit-latest.js"></script>--%>
<script type="text/javascript" src="resources/js/quill/quill.js"></script>
<link rel="stylesheet" href="resources/css/quill.css">
<script type="text/javascript" src="resources/js/quill/quill_resize.js"></script>
<script type="text/javascript" src="resources/js/ajax/article/article.js"></script>
<main class="app-content">
    <div class="card">
        <div class="table-responsive" style="overflow-x:auto;overflow-y: auto">
            <div class="title-table"></div>
            <div class="row" style="width: 100%; justify-content: center;padding-bottom: 15px">
                <div class="search-by-table col-lg-2" style="margin-top: 10px">
                    <button id="btn-add" class="btn btn-primary col-lg-12" type="button" data-toggle="modal"
                            data-target="#myModal">
                        <a style="color: white">
                            <i class="fa fa-fw fa-lg fa-plus-circle"></i>Thêm</a>
                    </button>
                </div>

                <div class="search-by-table col-lg-2" style="margin-top: 10px">
                    <select class="form-control" id="select-category">
                        <option value="0">Danh mục</option>
                    </select>
                </div>

                <div class="search-by-table col-lg-6" style="margin-top: 10px">
                    <input class="form-control" id="text-search" type="search"
                           placeholder="Tìm kiếm tên">
                </div>

                <div class="search-by-table col-lg-2" style="margin-top: 10px">
                    <button id="btn-search" style="width: 100%" class="btn btn-primary"><i
                            class="fa fa-fw fa-lg fa-search"></i>
                    </button>
                </div>
            </div>
            <table class="table text-center table-bordered table-sm" style="">
                <thead>
                <tr class="active my-th">
                    <th>Mã</th>
                    <th>Tên bài viết</th>
                    <th>Danh mục</th>
                    <th>Tags</th>
                    <th>Ngày tạo</th>
                    <th>Xem</th>
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
    <div class="modal-dialog mw-100 w-75">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Thông tin bài viết</h5>
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
                                    <div class="form-group col-lg-12">
                                        <label class="col-form-label">Tiêu đề bài viết<span
                                                style="color: red">*</span></label>
                                        <input id="input-name" class="form-control here" type="text">
                                    </div>

                                </div>

                                <div class="form-group row" style="justify-content: space-between">
                                    <div class="form-group col-lg-4">
                                        <label class="col-form-label">Danh mục<span
                                                style="color: red"> *</span></label>
                                        <select id="input-danh-muc" class="form-control"></select>
                                    </div>
                                    <div class="form-group col-lg-4">
                                        <label class="col-form-label">Tên người viết<span
                                                style="color: red"></span></label>
                                        <input id="input-author" value=""
                                               class="form-control here" required="required" type="text">
                                    </div>
                                    <div class="form-group col-lg-4">
                                        <label class="col-form-label">Tag (các tag ngăn cách nhau bởi dấu |)</label>
                                        <input id="input-tags" value="" placeholder="ví dụ: trending|Nổi bật|..."
                                               class="form-control here" required="required" type="text">
                                    </div>
                                </div>

                                <div class="form-group row" style="justify-content: space-between">
                                    <div class="form-group col-lg-6">
                                        <label class="col-form-label">Tải lên ảnh bài viết<span
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
                                                 style="max-width: 30%" alt="Hình ảnh"
                                                 onclick="$('#choose-file').trigger('click')">
                                        </div>
                                    </div>
                                    <div class="form-group col-lg-6">
                                        <label class="col-form-label">Mô tả bài viết<span
                                                style="color: red"></span></label>
                                        <textarea id="input-preview" rows="6"
                                                  class="form-control here" required="required" type="text"></textarea>
                                    </div>
                                </div>

                                <div class="form-group col-lg-12"
                                     style=" display: flex;justify-content: space-between">
                                    <label class="col-form-label">Đường dẫn thay thế:<span
                                            style="color: red"></span></label>
                                    <input id="input-alias" type="text" class="form-control col-lg-5"
                                           placeholder="Viết liền không dấu">
                                    <button id="btn-generate-alias" class="btn-primary form-control col-lg-2">Tạo tự
                                        động
                                    </button>
                                    <button id="btn-check-alias" class="btn-primary form-control col-lg-2">Kiểm tra
                                    </button>
                                </div>

                                <div class="card" style="width: 100%; margin-bottom: 5px">
                                    <div class="card-header my-header" style="height: 40px">
                                        <button style="padding: unset" is-showing="1"
                                                class="btn float-left btn-collapse text-white collapsed"
                                                data-toggle="collapse"
                                                data-target="#collapse-introduction" aria-expanded="false"
                                                aria-controls="#collapse-introduction">
                                            Chi tiết bài viết <i class="fa fa-angle-down" style="margin-left: 5px"></i>
                                        </button>
                                    </div>
                                    <div class="card-body collapse" id="collapse-introduction">
                                        <div id="input-content" style="height: 500px; width: 100%"></div>
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
                    <h4 class="modal-title font-weight-normal"><i class="fas fa-trash-alt"></i>&nbsp;Xóa bài viết
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

<div id="files-modal" class="modal fade" data-backdrop="static" role="dialog">
    <div class="modal-dialog modal-lg">
        <!-- Modal content-->
        <div class="modal-content shadow-modal">
            <div class="modal-header">
                <h5 class="modal-title">Danh sách tài liệu</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <table class="table text-center table-bordered table-sm" style="">
                    <thead>
                    <tr class="active my-th">
                        <th>STT</th>
                        <th>Tên file</th>
                        <th><i class="fas fa-cog"></i></th>
                    </tr>
                    </thead>
                    <tbody style="" id="row-file">

                    </tbody>

                </table>
                <div class="form-group row" style="justify-content: space-between">
                    <div class="form-group col-lg-12">
                        <label class="col-form-label">Tải lên tài liệu<span
                                style="color: red"></span></label>
                        <div class="input-group" style="display: none">
                            <div class="custom-file">
                                <form method="post" enctype="multipart/form-data">
                                    <input type="file" name="files" class="custom-file-input"
                                           id="choose-file-doc" multiple="multiple">
                                </form>
                            </div>
                        </div>
                        <div class="row" style="justify-content: center;">
                            <img src="resources/image/upload-file.png"
                                 style="max-width: 10%" alt="Hình ảnh"
                                 onclick="$('#choose-file-doc').trigger('click')">
                        </div>
                    </div>
                </div>
                <div class="row" style=" padding-top: 10px; justify-content: center">
                    <div class="col-lg-3" style=" display: flex; justify-content: center">
                        <button id="close-file" style="margin-left: 5%" type="button"
                                class="btn btn-secondary"
                                data-dismiss="modal">Quay lại
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<div id="video-modal" class="modal fade" data-backdrop="static" role="dialog">
    <div class="modal-dialog modal-lg">
        <!-- Modal content-->
        <div class="modal-content shadow-modal">
            <div class="modal-header">
                <h5 class="modal-title">Video công trình</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">

                <div class="form-group row" style="justify-content: left">
                    <div class="custom-control custom-switch" style="margin-left: 4%">
                        <input type="checkbox" class="custom-control-input" id="enable-video" checked>
                        <label class="custom-control-label" for="enable-video">Hiển thị video thay thế ảnh</label>
                    </div>
                </div>

                <div class="form-group row" style="justify-content: space-between">
                    <div class="form-group col-lg-6">
                        <label class="col-form-label">Tải lên video (mp4)<span
                                style="color: red"></span></label>
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

                <div class="row" style=" padding-top: 10px; justify-content: center">
                    <div class="col-lg-3" style=" display: flex; justify-content: center">
                        <button id="submit-video" type="button" class="btn btn-success">&nbsp;Lưu lại</button>
                        <button style="margin-left: 5%" type="button"
                                class="btn btn-secondary"
                                data-dismiss="modal">Quay lại
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>