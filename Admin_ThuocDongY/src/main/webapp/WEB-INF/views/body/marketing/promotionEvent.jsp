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
<script type="text/javascript" src="resources/js/ajax/marketing/promotionEvent.js"></script>
<main class="app-content">
    <div class="card">
        <div class="table-responsive" style="overflow-x:auto;overflow-y: auto">
            <div class="title-table"></div>
            <div class="row" style="width: 100%; justify-content: left; padding-bottom: 15px">
                <div class="search-by-table col-lg-2" style="margin-top: 10px">
                    <button id="btn-add" class="btn btn-primary col-lg-12" type="button" data-toggle="modal"
                            data-target="#myModal">
                        <a style="color: white">
                            <i class="fa fa-fw fa-lg fa-plus-circle"></i>Thêm</a>
                    </button>
                </div>

                <%--                <div class="search-by-table col-lg-2" style="margin-top: 10px">--%>
                <%--                    <select class="form-control" id="select-category">--%>
                <%--                        <option value="0">Danh mục</option>--%>
                <%--                    </select>--%>
                <%--                </div>--%>

                <div class="search-by-table col-lg-4" style="margin-top: 10px">
                    <input class="form-control" id="text-search" type="search"
                           placeholder="Nhập tên chương trình khuyến mãi">
                </div>

                <div class="search-by-table col-lg-1" style="margin-top: 10px">
                    <button id="btn-search" style="width: 100%" class="btn btn-primary"><i
                            class="fa fa-fw fa-lg fa-search"></i>
                    </button>
                </div>
                <%--                <div class="search-by-table col-lg-2" style="margin-top: 10px">--%>
                <%--                    <button id="btn-advance-search" style="width: 100%" class="btn btn-primary">Tìm kiếm nâng cao</i>--%>
                <%--                    </button>--%>
                <%--                </div>--%>
            </div>
            <table class="table text-center table-bordered table-sm" style="">
                <thead>
                <tr class="active my-th">
                    <th>Mã</th>
                    <th>Chương trình khuyến mãi</th>
                    <th>Loại khuyến mãi</th>
                    <th>Áp dụng cho</th>
                    <th>Ngày bắt đầu</th>
                    <th>Ngày kết thúc</th>
                    <th><i class="fas fa-cog" title="Chức năng"></i></th>
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
                <h5 class="modal-title">Thông tin chương trình khuyến mãi</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="row" style="justify-content: center">
                            <div class="col-lg-12">
                                <div class="form-group row" style="justify-content: flex-start">
                                    <div class="form-group col-lg-9">
                                        <label class="col-form-label">Tên chương trình khuyến mãi<span
                                                style="color: red">*</span></label>
                                        <input id="input-name" class="form-control here" type="text">
                                    </div>
                                    <div class="form-group col-lg-3">
                                        <label class="col-form-label">Loại khuyến mãi<span
                                                style="color: red"></span></label>
                                        <select id="input-loai-khuyen-mai" class="form-control">
                                            <option value="1">Quà tặng</option>
                                            <option value="2">Giảm tiền</option>
                                            <option value="3">Giảm phần trăm</option>
                                        </select>
                                    </div>
                                </div>

                                <div class="form-group row" style="justify-content: flex-start">
                                    <div class="form-group col-lg-3">
                                        <label class="col-form-label">Áp dụng cho<span
                                                style="color: red"></span></label>
                                        <select id="input-pv-khuyen-mai" class="form-control">
                                            <option value="1">Đơn hàng</option>
                                            <option value="0">Sản phẩm</option>
                                        </select>
                                    </div>

                                    <div class="form-group col-lg-3" id="div-decrease">
                                        <label class="col-form-label" id="label-decrease">Giá trị khuyến mãi<span
                                                style="color: red"> *</span></label>
                                        <input id="input-gtkm"
                                               class="form-control here" required="required" type="number">
                                    </div>

                                    <div class="form-group col-lg-3" id="div-max">
                                        <label class="col-form-label">Số tiền giảm tối đa<span
                                                style="color: red"> *</span></label>
                                        <input id="input-gttd" min="1000" step="1000"
                                               class="form-control here" required="required" type="number">
                                    </div>

                                    <div class="form-group col-lg-3" id="div-min">
                                        <label class="col-form-label">Giá trị đơn hàng tối thiểu<span
                                                style="color: red"></span></label>
                                        <input id="input-gttt" min="0" step="1000"
                                               class="form-control here" required="required" type="number">
                                    </div>
                                </div>

                                <div class="form-group col-lg-12" id="div-gift">
                                    <label class="col-form-label">Nội dung quà tặng<span
                                            style="color: red"> *</span></label>
                                    <textarea id="input-gift" rows="3"
                                              class="form-control here" required="required" type="text"></textarea>
                                </div>

                                <div class="form-group row " style="justify-content: start" id="wrap-more-info">
                                    <div class="form-group col-lg-4">
                                        <label class="col-form-label">Thời gian áp dụng<span
                                                style="color: red"></span></label>
                                        <select id="input-tgad" class="form-control">
                                            <option value="1">Giới hạn</option>
                                            <option value="0">Vô thời hạn</option>
                                        </select>
                                    </div>
                                    <div class="form-group col-lg-4" id="wrap-ngay-bd">
                                        <label class="col-form-label">Ngày bắt đầu<span
                                                style="color: red">*</span></label>
                                        <input id="input-start-date" value=""
                                               class="form-control here" required="required" type="datetime-local">
                                    </div>

                                    <div class="form-group col-lg-4" id="wrap-ngay-kt">
                                        <label class="col-form-label">Ngày kết thúc<span
                                                style="color: red">*</span></label>
                                        <input id="input-end-date"
                                               class="form-control here" required="required" type="datetime-local">
                                    </div>

                                </div>

                                <div class="form-group row" style="justify-content: space-between">
                                    <div class="form-group col-lg-6">
                                        <label class="col-form-label">Tải lên ảnh khuyến mãi<span
                                                style="color: red"></span></label>
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
                                        <label class="col-form-label">Ghi chú (dành cho quản trị viên)<span
                                                style="color: red"></span></label>
                                        <textarea id="input-note" rows="6"
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
</div>

<div id="productsModal" class="modal fade" data-backdrop="static" role="dialog">
    <div class="modal-dialog mw-100 w-75">
        <!-- Modal content-->
        <div class="modal-content shadow-modal">
            <div class="modal-header">
                <button class="btn btn-primary" id="btn-add-products-modal" data-toggle="modal" data-target="#add-products-modal"><i class="fa fa-plus-circle"></i>&nbsp;Thêm sản phẩm</button>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">

                <div class="form-group row" style="justify-content: space-between">
                    <div class="col-lg-12">
                        <table class="table text-center table-bordered table-sm" style="">
                            <thead>
                            <tr class="active my-th">
                                <th>STT</th>
                                <th>Mã sản phẩm</th>
                                <th>Tên sản phẩm</th>
                                <th><i class="fas fa-cog" title="Chức năng"></i></th>
                            </tr>
                            </thead>
                            <tbody style="" id="row-list-addedPromotion-product">
                            </tbody>
                        </table>
                        <div id="page-added-product">
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </div>
</div>

<div id="add-products-modal" class="modal fade" data-backdrop="static" role="dialog">
    <div class="modal-dialog mw-100 w-75">
        <!-- Modal content-->
        <div class="modal-content shadow-modal">
            <div class="modal-header">
                <h5 class="modal-title">Thêm sản phẩm</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <div class="col-lg-12">
                    <div class="form-group row" style="justify-content: flex-start">
                        <div class="col-12 col-lg-3">
                            <label for="select-type-product">Loại sản phẩm</label>
                            <select id="select-type-product" class="form-control">
                                <option value="1">Dụng cụ</option>
                                <option value="2">Dịch vụ</option>
                                <option value="3">Khoá học</option>
                            </select>
                        </div>
                        <div class="col-12 col-lg-3">
                            <label for="select-category-product">Danh mục(có thể chọn nhiều)</label>
                            <select id="select-category-product" class="select2" multiple="multiple">
                            </select>
                        </div>
                        <div class="col-12 col-lg-4">
                            <label for="select-type-product">Tên sản phẩm</label>
                            <input class="form-control" id="text-search-product" type="search" type="text">
                        </div>
                        <div class="col-12 col-lg-2">
                            <label for="btn-search-product">Tìm kiếm sản phẩm</label>
                            <button id="btn-search-product" class="btn btn-primary col-lg-12" type="button">
                                <a style="color: white">
                                    <i class="fas fa-search"></i>
                                </a>
                            </button>
                        </div>
                    </div>
                    <div class="form-group row" style="justify-content: space-between">
                        <div class="col-lg-12">
                            <table class="table text-center table-bordered table-sm" style="">
                                <thead>
                                <tr class="active my-th">
                                    <th>Mã sản phẩm</th>
                                    <th>Tên sản phẩm</th>
                                    <th>Giá</th>
                                    <th>Mã khuyến mãi hiện áp dụng</th>
                                    <th><input id="check-all" type="checkbox"></th>
                                </tr>
                                </thead>
                                <tbody style="" id="row-list-product">
                                </tbody>
                            </table>
                        </div>


                        <div class="col-lg-12">
                            <div id="page-product">
                            </div>
                        </div>

                        <div class="col-lg-12 d-flex justify-content-center">
                            <button id="btn-add-all-product" class="btn btn-primary">Thêm sản phẩm</button>
                            <button id="btn-add-product-by-category" class="btn btn-primary" data-toggle="modal"
                                    data-target="#categoryModal" style="margin-left: 20px;">Thêm theo danh mục</button>
                        </div>

                    </div>

                </div>
            </div>
        </div>
    </div>
</div>

<div id="categoryModal" class="modal fade" data-backdrop="static" role="dialog">
    <div class="modal-dialog mw-100 w-75">
        <div class="modal-content shadow-modal">
            <div class="modal-header">
                <h5 class="modal-title">Thông tin danh mục</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="form-group row" style="justify-content: flex-start">
                            <div class="col-12 col-lg-3">
                                <label for="select-type-product-category">Loại sản phẩm</label>
                                <select id="select-type-product-category" class="form-control">
                                    <option value="1">Dụng cụ</option>
                                    <option value="2">Dịch vụ</option>
                                    <option value="3">Khoá học</option>
                                </select>
                            </div>

                            <div class="col-12 col-lg-4">
                                <label for="select-category">Danh mục</label>
                                <select id="select-category" class="form-control">

                                </select>
                            </div>
                            <div class="col-12 col-lg-3">
                                <label for="btn-search-category">Thêm khuyến mãi vào danh mục</label>
                                <button id="btn-search-category" class="btn btn-primary col-lg-12" type="button">
                                    <a style="color: white">
                                        <i class="fas fa-plus-circle"></i> Thêm khuyến mãi
                                    </a>
                                </button>
                            </div>
                        </div>

                    </div>
                </div>

                <div class="row" style=" padding-top: 10px; justify-content: center">
                    <div class="col-lg-3" style=" display: flex; justify-content: center">
                        <button id="close-add-category" style="margin-left: 5%" type="button"
                                class="btn btn-secondary"
                                data-dismiss="modal">Quay lại
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
                    <h4 class="modal-title font-weight-normal"><i class="fas fa-trash-alt"></i>&nbsp;Xóa khuyến mãi
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

