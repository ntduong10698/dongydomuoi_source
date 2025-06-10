<%--
  Created by IntelliJ IDEA.
  User: hieuv
  Date: 07-Aug-20
  Time: 4:40 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link type="text/css" href="css/quill.css" rel="stylesheet">
<script type="text/javascript" src="resources/js/quill/quill.js"></script>
<script type="text/javascript" src="resources/js/quill/quill_resize.js"></script>
<script type="text/javascript" src="resources/js/ajax/edu/course.js"></script>
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

                <div class="search-by-table col-lg-2" style="margin-top: 10px">
                    <select class="form-control" id="select-sort">
                        <option value="date&asc=false">Mới nhất</option>
                        <option value="date&asc=true">Cũ nhất</option>
                        <option value="view&asc=true">Xem ít nhất</option>
                        <option value="view&asc=false">Xem nhiều nhất</option>
                        <option value="price&asc=true">Giá tăng dần</option>
                        <option value="price&asc=false">Giá giảm dần</option>
                    </select>
                </div>

                <div class="search-by-table col-lg-4" style="margin-top: 10px">
                    <input class="form-control" id="text-search" type="search"
                           placeholder="Tìm kiếm tên, mã">
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
                    <th>Tên khóa học</th>
                    <th>Loại</th>
                    <th>Danh mục</th>
                    <th>Giảng viên</th>
                    <th>Giá</th>
                    <th>Xem</th>
                    <th>Học viên</th>
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
                <h5 class="modal-title">Thông tin khóa học</h5>
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
                                        <label class="col-form-label">Tên khóa học<span
                                                style="color: red">*</span></label>
                                        <input id="input-name" class="form-control here" type="text">
                                    </div>

                                    <div class="form-group col-lg-3">
                                        <label class="col-form-label">Mã khóa học (nếu có)<span
                                                style="color: red"></span></label>
                                        <input id="input-model"
                                               class="form-control here" required="required" type="text">
                                    </div>
                                    <div class="form-group col-lg-3">
                                        <label class="col-form-label">Loại khóa học<span
                                                style="color: red"> *</span></label>
                                        <select class="form-control" id="input-type">
                                            <option value="1">Online</option>
                                            <option value="2">Offline</option>
                                        </select>
                                    </div>

                                </div>

                                <div class="form-group row" style="justify-content: space-between">
                                    <div class="form-group col-lg-6">
                                        <label class="col-form-label">Danh mục (có thể chọn nhiều)<span
                                                style="color: red"> *</span></label>
                                        <select id="input-danh-muc" class="select2" multiple="multiple"></select>
                                    </div>
                                    <div class="form-group col-lg-6">
                                        <label class="col-form-label">Giảng viên<span
                                                style="color: red"> *</span></label>
                                        <select id="input-giang-vien" class="form-control"></select>
                                    </div>
                                </div>

                                <div class="form-group row" style="justify-content: left">
                                    <div class="form-group col-lg-6">
                                        <label class="col-form-label">Giá khóa học (VNĐ)<span
                                                style="color: red"></span></label>
                                        <input id="input-cost" value="0" min="0"
                                               class="form-control here" required="required" type="number">
                                    </div>

                                    <div class="form-group col-lg-3" id="div-maxView">
                                        <label class="col-form-label">Lượt xem video tối đa (online)<span
                                                style="color: red"></span></label>
                                        <input id="input-max-view" value="10" min="0"
                                               class="form-control here" required="required" type="number">
                                    </div>

                                    <div class="form-group col-lg-3" id="div-member">
                                        <label class="col-form-label">Số lượng học viên<span
                                                style="color: red"> *</span></label>
                                        <input id="input-quantity" value="0" min="0" disabled
                                               class="form-control here" required="required" type="number">
                                    </div>

                                </div>

                                <div class="form-group row" style="justify-content: space-between">
                                    <div class="form-group col-lg-6">
                                        <label class="col-form-label">Tải lên ảnh khóa học<span
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
                                        <label class="col-form-label">Mô tả khóa học<span
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
                                            Giới thiệu khóa học <i class="fa fa-angle-down"
                                                                   style="margin-left: 5px"></i>
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

<%--Chapter and lesson--%>

<div id="lesson-modal" class="modal fade" data-backdrop="static" role="dialog">
    <div class="modal-dialog mw-100 w-75">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Nội dung bài học</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>

            <div class="modal-body">
                <div id="lesson-content">

                </div>
                <div class="row" style=" padding-top: 10px; justify-content: center">
                    <div class="col-lg-3" style=" display: flex; justify-content: center">
                        <button id="add-chapter" type="button" class="btn btn-success" data-toggle="modal"
                                data-target="#chapter-modal">&nbsp;Thêm
                            chương
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

<div id="lesson-detail-modal" class="modal fade" data-backdrop="static" role="dialog">
    <div class="modal-dialog modal-lg">
        <!-- Modal content-->
        <div class="modal-content"
             style="margin-top: 10%; box-shadow: 0 0 20px 20px rgba(0, 0, 0, 0.2), 0 0 20px 2000px rgba(0, 0, 0, 0.2);">
            <div class="modal-header">
                <h5 class="modal-title">Thông tin bài học</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>

            <div class="modal-body">
                <div class="col-lg-12">
                    <div class="row" style="justify-content: left">
                        <div class="form-group col-lg-6">
                            <label class="col-form-label">Tên bài học<span
                                    style="color: red">*</span></label>
                            <input id="input-lesson-name" class="form-control here" type="text">
                        </div>
                        <div class="form-group col-lg-2">
                            <label class="col-form-label">Bài học số<span
                                    style="color: red">*</span></label>
                            <input id="input-lesson-order" class="form-control here" min="1" value="1" type="number">
                        </div>
                        <div class="form-group col-lg-2 group-online">
                            <label class="col-form-label">Thời lượng<span
                                    style="color: red"></span></label>
                            <input id="input-lesson-time" class="form-control here" disabled  type="text">
                        </div>
                        <div class="form-group col-lg-2 text-center group-online" >
                            <label class="col-form-label">Miễn phí<span
                                    style="color: red"></span></label>
                            <input id="input-free" class="form-control here"  type="checkbox">
                        </div>
                    </div>
                    <div class="form-group row group-online" style="justify-content: space-between">
                        <div class="form-group col-lg-6">
                            <label class="col-form-label">Tải lên video<span
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
                                <input id="input-youtube" class="form-control" placeholder="https://www.youtube.com/embed/...">
                            </div>
                        </div>
                        <div class="form-group col-lg-6">
                            <label class="col-form-label">Video bài học <span
                                    style="color: red"></span></label>
                            <div id="lesson-video">

                            </div>
                        </div>
                    </div>
                </div>
                <div class="row" style=" padding-top: 10px; justify-content: center">
                    <div class="col-lg-3" style=" display: flex; justify-content: center">
                        <button id="add-lesson" type="button" class="btn btn-success"
                        >&nbsp;Lưu lại
                        </button>
                        <button id="close-lesson-detail-model" style="margin-left: 5%" type="button"
                                class="btn btn-secondary"
                                data-dismiss="modal">Quay lại
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<div id="chapter-modal" class="modal fade" data-backdrop="static" role="dialog">
    <div class="modal-dialog modal-lg">
        <!-- Modal content-->
        <div class="modal-content"
             style="margin-top: 10%; box-shadow: 0 0 20px 20px rgba(0, 0, 0, 0.2), 0 0 20px 2000px rgba(0, 0, 0, 0.2);">
            <div class="modal-header">
                <h5 class="modal-title">Thông tin chuơng</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <div class="col-lg-12">
                    <div class="row" style="justify-content: space-between">
                        <div class="form-group col-lg-6">
                            <label class="col-form-label">Chương số<span
                                    style="color: red">*</span></label>
                            <input id="input-chapter-order" class="form-control here" min="1" value="1" type="number">
                        </div>
                        <div class="form-group col-lg-6">
                            <label class="col-form-label">Tên chương<span
                                    style="color: red">*</span></label>
                            <input id="input-chapter-name" class="form-control here" type="text">
                        </div>
                    </div>
                </div>
                <div class="row" style=" padding-top: 10px; justify-content: center">
                    <div class="col-lg-3" style=" display: flex; justify-content: center">
                        <button id="submit-chapter" type="button" class="btn btn-success">&nbsp;Lưu lại</button>
                        <button id="close-chapter" style="margin-left: 5%" type="button"
                                class="btn btn-secondary"
                                data-dismiss="modal">Quay lại
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<div id="delete-modal" class="modal fade" role="dialog">
    <div class="modal-dialog">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header" style="padding: 10px 10px">
                <div class="row col-lg-12" style="justify-content: center">
                    <h4 class="modal-title font-weight-normal"><i class="fas fa-trash-alt"></i>&nbsp;Xóa
                        khóa học
                    </h4>
                </div>
            </div>
            <div class="modal-body">
                <span class="font-weight-normal">Bạn có chắc chắn muốn xóa không?</span>
            </div>
            <div class="modal-footer" style="padding: 10px 10px">
                <div class="row" style=" justify-content: center">
                    <div class="col-lg-3" style=" display: flex; justify-content: center">
                        <button id="submit-delete" type="button" class="btn btn-danger">&nbsp;Xác
                            nhận
                        </button>
                        <button id="close-delete" style="margin-left: 5%" type="button"
                                class="btn btn-secondary"
                                data-dismiss="modal">
                            &nbsp;Hủy bỏ
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<div id="delete-chapter-modal" class="modal fade" role="dialog">
    <div class="modal-dialog">
        <!-- Modal content-->
        <div class="modal-content"
             style="margin-top: 30%; box-shadow: 0 0 20px 20px rgba(0, 0, 0, 0.2), 0 0 20px 2000px rgba(0, 0, 0, 0.2);">
            <div class="modal-header" style="padding: 10px 10px">
                <div class="row col-lg-12" style="justify-content: center">
                    <h4 class="modal-title font-weight-normal"><i class="fas fa-trash-alt"></i>&nbsp;Xóa
                        chương
                    </h4>
                </div>
            </div>
            <div class="modal-body">
                <span class="font-weight-normal">Bạn có chắc chắn muốn xóa không?</span>
            </div>
            <div class="modal-footer" style="padding: 10px 10px">
                <div class="row" style=" justify-content: center">
                    <div class="col-lg-3" style=" display: flex; justify-content: center">
                        <button id="submit-delete-chapter" type="button" class="btn btn-danger">
                            &nbsp;Xác nhận
                        </button>
                        <button id="close-delete-chapter" style="margin-left: 5%" type="button"
                                class="btn btn-secondary"
                                data-dismiss="modal">
                            &nbsp;Hủy bỏ
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<div id="delete-lesson-modal" class="modal fade" role="dialog">
    <div class="modal-dialog">
        <!-- Modal content-->
        <div class="modal-content shadow-modal">
            <div class="modal-header" style="padding: 10px 10px">
                <div class="row col-lg-12" style="justify-content: center">
                    <h4 class="modal-title font-weight-normal"><i class="fas fa-trash-alt"></i>&nbsp;Xóa
                        bài học
                    </h4>
                </div>
            </div>
            <div class="modal-body">
                <span class="font-weight-normal">Bạn có chắc chắn muốn xóa không?</span>
            </div>
            <div class="modal-footer" style="padding: 10px 10px">
                <div class="row" style=" justify-content: center">
                    <div class="col-lg-3" style=" display: flex; justify-content: center">
                        <button id="submit-delete-lesson" type="button" class="btn btn-danger">
                            &nbsp;Xác nhận
                        </button>
                        <button id="close-delete-lesson" style="margin-left: 5%" type="button"
                                class="btn btn-secondary"
                                data-dismiss="modal">
                            &nbsp;Hủy bỏ
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<%--Question and option--%>

<div id="questions-modal" class="modal fade" data-backdrop="static" role="dialog">
    <div class="modal-dialog mw-100 w-75">
        <!-- Modal content-->
        <div class="modal-content shadow-modal">
            <div class="modal-header">
                <h5 class="modal-title">Câu hỏi bài học</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>

            <div class="modal-body">
                <div id="questions-content">

                </div>
                <div class="row" style=" padding-top: 10px; justify-content: center">
                    <div class="col-lg-3" style=" display: flex; justify-content: center">
                        <button id="add-question" type="button" class="btn btn-success" data-toggle="modal"
                                data-target="#question-modal">&nbsp;Thêm câu hỏi
                        </button>
                        <button id="close-questions-model" style="margin-left: 5%" type="button"
                                class="btn btn-secondary"
                                data-dismiss="modal">Quay lại
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<div id="question-modal" class="modal fade" data-backdrop="static" role="dialog">
    <div class="modal-dialog modal-lg">
        <!-- Modal content-->
        <div class="modal-content shadow-modal">
            <div class="modal-header">
                <h5 class="modal-title">Thông tin câu hỏi</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <div class="col-lg-12">
                    <div class="row" style="justify-content: space-between">
                        <div class="form-group col-lg-6">
                            <label class="col-form-label">Câu hỏi số<span
                                    style="color: red">*</span></label>
                            <input id="input-question-order" class="form-control here" min="1" value="1" type="number">
                        </div>
                        <div class="form-group col-lg-6">
                            <label class="col-form-label">Loại câu hỏi<span
                                    style="color: red">*</span></label>
                            <select id="select-question-type" class="form-control">
                                <option value="1">Chọn một đáp án</option>
                                <option value="2">Chọn nhiều đáp án</option>
                                <option value="3">Câu hỏi mở</option>
                            </select>
                        </div>
                    </div>

                    <div class="row" style="justify-content: space-between">
                        <div class="form-group col-lg-6">
                            <label class="col-form-label">Nội dung câu hỏi<span
                                    style="color: red">*</span></label>
                            <textarea id="input-question-content" class="form-control" rows="3">
                            </textarea>
                        </div>
                        <div class="form-group col-lg-6" id="answer-div">
                            <label class="col-form-label">Đáp án (cho câu hỏi mở)<span
                                    style="color: red"></span></label>
                            <textarea id="input-question-answer" class="form-control" rows="3">
                            </textarea>
                        </div>
                    </div>
                </div>
                <div class="row" style=" padding-top: 10px; justify-content: center">
                    <div class="col-lg-3" style=" display: flex; justify-content: center">
                        <button id="submit-question" type="button" class="btn btn-success">&nbsp;Lưu lại</button>
                        <button id="close-question" style="margin-left: 5%" type="button"
                                class="btn btn-secondary"
                                data-dismiss="modal">Quay lại
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<div id="delete-question-modal" class="modal fade" role="dialog">
    <div class="modal-dialog">
        <!-- Modal content-->
        <div class="modal-content shadow-modal">
            <div class="modal-header" style="padding: 10px 10px">
                <div class="row col-lg-12" style="justify-content: center">
                    <h4 class="modal-title font-weight-normal"><i class="fas fa-trash-alt"></i>&nbsp;Xóa câu hỏi
                    </h4>
                </div>
            </div>
            <div class="modal-body">
                <span class="font-weight-normal">Bạn có chắc chắn muốn xóa không?</span>
            </div>
            <div class="modal-footer" style="padding: 10px 10px">
                <div class="row" style=" justify-content: center">
                    <div class="col-lg-3" style=" display: flex; justify-content: center">
                        <button id="submit-delete-question" type="button" class="btn btn-danger">
                            &nbsp;Xác nhận
                        </button>
                        <button id="close-delete-question" style="margin-left: 5%" type="button"
                                class="btn btn-secondary"
                                data-dismiss="modal">
                            &nbsp;Hủy bỏ
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<div id="option-modal" class="modal fade" data-backdrop="static" role="dialog">
    <div class="modal-dialog mw-100 w-75">
        <!-- Modal content-->
        <div class="modal-content shadow-modal">
            <div class="modal-header">
                <h5 class="modal-title">Thông tin lựa chọn</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <div class="col-lg-12">
                    <div id="options">

                    </div>

                    <div class="row" style="justify-content: space-between">
                        <div class="form-group col-lg-12">
                            <button class="btn btn-primary col-lg-2" id="btn-more-option">Thêm lựa chọn</button>
                        </div>
                    </div>
                </div>
                <div class="row" style=" padding-top: 10px; justify-content: center">
                    <div class="col-lg-3" style=" display: flex; justify-content: center">
                        <button id="submit-option" type="button" class="btn btn-success">&nbsp;Lưu lại</button>
                        <button id="close-option" style="margin-left: 5%" type="button"
                                class="btn btn-secondary"
                                data-dismiss="modal">Quay lại
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<div id="delete-option-modal" class="modal fade" role="dialog">
    <div class="modal-dialog">
        <!-- Modal content-->
        <div class="modal-content shadow-modal">
            <div class="modal-header" style="padding: 10px 10px">
                <div class="row col-lg-12" style="justify-content: center">
                    <h4 class="modal-title font-weight-normal"><i class="fas fa-trash-alt"></i>&nbsp;Xóa lựa chọn
                    </h4>
                </div>
            </div>
            <div class="modal-body">
                <span class="font-weight-normal">Bạn có chắc chắn muốn xóa không?</span>
            </div>
            <div class="modal-footer" style="padding: 10px 10px">
                <div class="row" style=" justify-content: center">
                    <div class="col-lg-3" style=" display: flex; justify-content: center">
                        <button id="submit-delete-option" type="button" class="btn btn-danger">
                            &nbsp;Xác nhận
                        </button>
                        <button id="close-delete-option" style="margin-left: 5%" type="button"
                                class="btn btn-secondary"
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
                        <div  class="row" style="justify-content: center;">
                            <img  src="resources/image/upload-file.png"
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

<div id="code-modal" class="modal fade" role="dialog">
    <div class="modal-dialog">
        <!-- Modal content-->
        <div class="modal-content shadow-modal">
            <div class="modal-header" style="padding: 10px 10px">
                <div class="row col-lg-12" style="justify-content: center">
                    <h4 class="modal-title font-weight-normal">&nbsp;Mã kích hoạt khóa học
                    </h4>
                </div>
            </div>
            <div class="modal-body">
               <textarea class="form-control" cols="3" id="course-code">
               </textarea>
            </div>
            <div class="modal-footer" style="padding: 10px 10px">
                <div class="row" style=" justify-content: center">
                    <div class="col-lg-3" style=" display: flex; justify-content: center">
                        <button id="copy-code" type="button" class="btn btn-danger">
                            &nbsp;Sao chép
                        </button>
                        <button  style="margin-left: 5%" type="button"
                                class="btn btn-secondary"
                                data-dismiss="modal">
                            &nbsp;Đóng
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<div id="student-modal" class="modal fade" data-backdrop="static" role="dialog">
    <div class="modal-dialog modal-lg">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Danh sách học viên</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="table-responsive" style="overflow-x:auto;overflow-y: auto">
                        <div class="title-table"></div>

                        <table class="table text-center table-bordered table-sm" style="">
                            <thead>
                            <tr class="active my-th">
                                <th>STT</th>
                                <th>Mã</th>
                                <th>Tài khoản</th>
                                <th>Tên khách hàng</th>
                                <th>Email</th>
                                <th>Điện thoại</th>
                            </tr>
                            </thead>
                            <tbody style="" id="row-students">
                            </tbody>
                        </table>
                        <div id="page-student">
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>