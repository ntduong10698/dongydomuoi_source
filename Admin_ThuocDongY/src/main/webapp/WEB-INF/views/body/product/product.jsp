<%--
  Created by IntelliJ IDEA.
  User: hieuv
  Date: 03-Aug-20
  Time: 1:49 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<script type="text/javascript" src="http://js.nicedit.com/nicEdit-latest.js"></script>--%>

<link href="css/quill.css" rel="stylesheet">
<script type="text/javascript" src="resources/js/quill/quill.js"></script>
<script type="text/javascript" src="resources/js/quill/quill_resize.js"></script>
<script type="text/javascript" src="resources/js/template/jscolor.js"></script>
<script type="text/javascript" src="resources/js/ajax/product/product.js"></script>

<main class="app-content">
    <div class="card">
        <div class="table-responsive" style="overflow-x:auto;overflow-y: auto">
            <div class="title-table"></div>
            <div class="col-lg-12" style="display: flex; justify-content: center;margin-bottom: 2px">
                <div class="search-by-table col-lg-2" style="margin-top: 10px;">
                    <button id="btn-add" class="btn btn-primary" type="button" data-toggle="modal" style="width: 100%"
                            data-target="#myModal">
                        <a style="color: white">
                            <i class="fa fa-fw fa-lg fa-plus-circle"></i>Thêm</a>
                    </button>
                </div>
                <div class="search-by-table col-lg-4" style="margin-top: 10px">
                    <select class="form-control" id="select-product-type">
                        <option value="0">Loại sản phẩm</option>
                    </select>
                </div>
                <div class="search-by-table col-lg-4" style="margin-top: 10px">
                    <select class="form-control" id="select-category">
                        <option value="0">Danh mục</option>
                    </select>
                </div>

                <div class="search-by-table col-lg-2" style="margin-top: 10px">
                    <select class="form-control" id="select-brand">
                        <option value="0">Thương hiệu</option>
                    </select>
                </div>
            </div>
            <div class="col-lg-12" style="display: flex; justify-content: center;margin-bottom: 10px">
                <div class="search-by-table col-lg-2" style="margin-top: 10px">
                    <select class="form-control" id="select-status">
                        <option value="0">Trạng thái</option>
                        <option value="1">Đang kinh doanh</option>
                        <option value="2">Ngừng kinh doanh</option>
                        <option value="3">Tạm hết hàng</option>
                    </select>
                </div>

                <div class="search-by-table col-lg-4" style="margin-top: 10px">
                    <select class="form-control" id="select-sort">
                        <option value="date&asc=false">Mới nhất</option>
                        <option value="date&asc=true">Cũ nhất</option>
                        <option value="price&asc=true">Giá tăng dần</option>
                        <option value="price&asc=false">Giá giảm dần</option>
                        <option value="view&asc=true">Xem ít nhất</option>
                        <option value="view&asc=false">Xem nhiều nhất</option>
                        <option value="sold&asc=true">Bán ít nhất</option>
                        <option value="sold&asc=false">Bán chạy nhất</option>
                    </select>
                </div>

                <div class="search-by-table col-lg-4" style="margin-top: 10px">
                    <input class="form-control" id="text-search" type="search"
                           placeholder="Tìm kiếm tên, mã">
                </div>

                <div class="search-by-table col-lg-2" style="margin-top: 10px;">
                    <button id="btn-search" style="width: 100%" class="btn btn-primary"><i
                            class="fa fa-fw fa-lg fa-search"></i>
                    </button>
                </div>
            </div>
        </div>
        <table class="table text-center table-bordered table-sm" style="">
            <thead>
            <tr class="active my-th">
                <th>Mã</th>
                <th>Tên SP</th>
                <th>Model</th>
                <th>Danh mục SP</th>
                <th>Giá bán</th>
                <th>Đơn vị</th>
                <th>Xem</th>
                <th>Bán</th>
                <th>Tồn kho</th>
                <th>Trạng thái</th>
                <th><i class="fas fa-cog"></i></th>
                <%--                    <th><input id="check-all" type="checkbox"></th>--%>
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
                <h5 class="modal-title">Thông tin sản phẩm</h5>
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
                                        <label class="col-form-label">Tên sản phẩm<span
                                                style="color: red">*</span></label>
                                        <input id="input-name" class="form-control here" type="text">
                                    </div>

                                    <div class="form-group col-lg-3">
                                        <label class="col-form-label">Model sản phẩm<span
                                                style="color: red"></span></label>
                                        <input id="input-model"
                                               class="form-control here" required="required" type="text">
                                    </div>

                                    <div class="form-group col-lg-3">
                                        <label class="col-form-label">Trạng thái<span
                                                style="color: red"></span></label>
                                        <select id="input-trang-thai" class="form-control">
                                            <option value="1">Đang kinh doanh</option>
                                            <option value="2">Ngừng kinh doanh</option>
                                            <option value="3">Tạm hết hàng</option>
                                        </select>
                                    </div>
                                </div>

                                <div class="row" style="justify-content: space-between">
                                    <div class="form-group col-lg-6">
                                        <label class="col-form-label">Loại sản phẩm<span
                                                style="color: red"></span></label>
                                        <select id="input-product-type" class="form-control">
                                        </select>
                                    </div>
                                    <div class="form-group col-lg-6">
                                        <label class="col-form-label">Danh mục (có thể chọn nhiều)<span
                                                style="color: red"> *</span></label>
                                        <select id="input-danh-muc" class="select2" multiple="multiple"></select>
                                    </div>
                                </div>

                                <div class="form-group row" style="justify-content: space-between">
                                    <div class="form-group col-lg-4">
                                        <label class="col-form-label">Thương hiệu<span
                                                style="color: red"></span></label>
                                        <select id="input-thuong-hieu" class="form-control">
                                            <option value="0">--Chọn thương hiệu--</option>
                                        </select>
                                    </div>
                                    <div class="form-group col-lg-4">
                                        <label class="col-form-label">Bảo hành (tháng)<span
                                                style="color: red"></span></label>
                                        <input id="input-guarantee" value="0" min="0"
                                               class="form-control here" required="required" type="number">
                                    </div>
                                    <div class="form-group col-lg-4">
                                        <label class="col-form-label">Tồn kho<span
                                                style="color: red"> *</span></label>
                                        <input id="input-quantity" value="0" min="0"
                                               class="form-control here" required="required" type="number">
                                    </div>
                                </div>

                                <div class="form-group row" style="justify-content: space-between">
                                    <div class="form-group col-lg-6">
                                        <label class="col-form-label">Giá sản phẩm (VNĐ)<span
                                                style="color: red"> *</span></label>
                                        <input id="input-cost" value="0" min="0"
                                               class="form-control here" required="required" type="number">
                                    </div>
                                    <div class="form-group col-lg-3">
                                        <label class="col-form-label">Đơn vị<span
                                                style="color: red"> *</span></label>
                                        <input id="input-unit"
                                               class="form-control here" required="required" type="text">
                                    </div>

                                    <div class="form-group col-lg-3">
                                        <label class="col-form-label">Trọng lượng (gram)<span
                                                style="color: red"></span></label>
                                        <input id="input-weight" value="0" min="0"
                                               class="form-control here" required="required" type="number">
                                    </div>

                                </div>

                                <div class="form-group row" style="justify-content: space-between">
                                    <div class="form-group col-lg-6">
                                        <label class="col-form-label">Tải lên ảnh sản phẩm<span
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
                                        <label class="col-form-label">Mô tả sản phẩm<span
                                                style="color: red"></span></label>
                                        <textarea id="input-preview" rows="6"
                                                  class="form-control here" required="required" type="text"></textarea>
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

                                <div class="card" style="width: 100%; margin-bottom: 5px">
                                    <div class="card-header my-header" style="height: 40px">
                                        <button style="padding: unset" is-showing="1"
                                                class="btn float-left btn-collapse text-white collapsed"
                                                data-toggle="collapse"
                                                data-target="#collapse-properties" aria-expanded="false"
                                                aria-controls="#collapse-properties">
                                            Thông số sản phẩm
                                        </button>
                                    </div>
                                    <div class="card-body collapse" id="collapse-properties">

                                    </div>
                                </div>
                                <div class="card" style="width: 100%; margin-bottom: 5px">
                                    <div class="card-header my-header" style="height: 40px">
                                        <button style="padding: unset" is-showing="1"
                                                class="btn float-left btn-collapse text-white collapsed"
                                                data-toggle="collapse"
                                                data-target="#collapse-introduction" aria-expanded="false"
                                                aria-controls="#collapse-introduction">
                                            Giới thiệu sản phẩm
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

<div id="properties-example" hidden>
    <div id="single-select-ex">
        <div class="form-group col-lg-12">
            <label class="col-form-label label-property"></label>
            <select class="form-control input-property" >
            </select>
        </div>
    </div>
    <div id="multi-select-ex">
        <div class="form-group col-lg-12">
            <label class="col-form-label label-property"></label>
            <select class="select-2 input-property" multiple="multiple">
            </select>
        </div>
    </div>
    <div id="input-text-ex">
        <div class="form-group col-lg-12">
            <label class="col-form-label label-property"></label>
            <input class="form-control input-property" type="text">
        </div>
    </div>
    <div id="input-number-ex">
        <div class="form-group col-lg-12">
            <label class="col-form-label label-property"></label>
            <input class="form-control input-property" value="0" type="number">
        </div>
    </div>
</div>

<div id="delete-modal" class="modal fade" data-backdrop="static" role="dialog">
    <div class="modal-dialog">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header" style="padding: 10px 10px">
                <div class="row col-lg-12" style="justify-content: center">
                    <h4 class="modal-title font-weight-normal"><i class="fas fa-trash-alt"></i>&nbsp;Xóa sản phẩm
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

<div id="image-modal" class="modal fade" data-backdrop="static" role="dialog">
    <div class="modal-dialog mw-100 m-75" style="max-width: 90% !important;">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <div class="row col-lg-12" style="justify-content: center">
                    <h4 class="modal-title">Danh sách hình ảnh</h4>
                </div>
            </div>
            <div class="modal-body">
                <div class="row" style="justify-content: center">
                    <div class="grid-stack " style="width: 90%">

                    </div>
                </div>
            </div>
            <div class="modal-footer" style="padding: 10px 10px">
                <div class="row" style=" justify-content: center; width: 100%">
                    <div class="col-lg-3" style=" display: flex; justify-content: space-between">
                        <button id="upload-image" type="button" class="btn btn-success" data-toggle="modal"
                                data-target="#upload-modal">&nbsp;Tải lên ảnh
                        </button>
                        <button id="close-image" type="button" class="btn btn-secondary"
                                data-dismiss="modal">Quay lại
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<div id="delete-image-modal" class="modal fade" role="dialog">
    <div class="modal-dialog">
        <!-- Modal content-->
        <div class="modal-content"
             style="margin-top: 30%; box-shadow: 0 0 20px 20px rgba(0, 0, 0, 0.2), 0 0 20px 2000px rgba(0, 0, 0, 0.2);">
            <div class="modal-header" style="padding: 10px 10px">
                <div class="row col-lg-12" style="justify-content: center">
                    <h4 class="modal-title font-weight-normal"><i class="fas fa-trash-alt"></i>&nbsp;Xóa hình ảnh
                    </h4>
                </div>
            </div>
            <div class="modal-body">
                <span class="font-weight-normal">Bạn có chắc chắn muốn xóa không?</span>
            </div>
            <div class="modal-footer" style="padding: 10px 10px">
                <div class="row" style=" justify-content: center">
                    <div class="col-lg-3" style=" display: flex; justify-content: center">
                        <button id="submit-delete-img" type="button" class="btn btn-danger">&nbsp;Xác nhận</button>
                        <button id="close-delete-img" style="margin-left: 5%" type="button"
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

<div id="upload-modal" class="modal fade" data-backdrop="static" role="dialog">
    <div class="modal-dialog">
        <!-- Modal content-->
        <div class="modal-content"
             style="margin-top: 30%; box-shadow: 0 0 20px 20px rgba(0, 0, 0, 0.2), 0 0 20px 2000px rgba(0, 0, 0, 0.2);">
            <div class="modal-header" style="padding: 10px 10px">
                <div class="row col-lg-12" style="justify-content: center">
                    <h4 class="modal-title font-weight-bold" style="font-size: 20px">
                        <i style="color: red" class="fas fa-lg fa-cloud-upload-alt"></i>&nbsp;Tải lên ảnh</h4>
                </div>
            </div>
            <div class="modal-body">
                <div class="card-body">
                    <div class="row">
                        <div class="input-group">
                            <div class="input-group-prepend">
                                <span class="input-group-text" id="inputGroupFileAddon01">Tải lên</span>
                            </div>
                            <div class="custom-file">
                                <form method="post" enctype="multipart/form-data" id="form-upload">
                                    <input type="file" accept="image/*" class="custom-file-input"
                                           id="choose-file-image"
                                           aria-describedby="inputGroupFileAddon01">
                                    <label class="custom-file-label" for="choose-file">Chọn ảnh từ máy tính</label>
                                </form>
                            </div>
                        </div>
                    </div>

                    <div id="div-img" class="row" style="justify-content: center; margin-top: 5%">
                        <img id="img-preview-image" src="resources/image/camera2.png" style="max-width: 50%"
                             alt="Hình ảnh" onclick="$('#choose-file-image').trigger('click')">
                    </div>

                </div>
            </div>
            <div class="modal-footer" style="padding: 10px 10px">
                <div class="row" style=" justify-content: center; width: 100%">
                    <div class="col-lg-4" style=" display: flex; justify-content: center">
                        <button id="submit-upload" type="button" class="btn btn-danger">&nbsp;Xác nhận</button>
                        <button id="close-upload" style="margin-left: 10%" type="button" class="btn btn-secondary"
                                data-dismiss="modal">
                            &nbsp;Hủy bỏ
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<div id="promo-modal" class="modal fade" data-backdrop="static" role="dialog">
    <div class="modal-dialog mw-100 w-75">
        <!-- Modal content-->
        <div class="modal-content shadow-modal">
            <div class="modal-header">
                <button class="btn btn-primary" id="btn-add-products-modal" data-toggle="modal"
                        data-target="#add-promo-modal"><i class="fa fa-plus-circle"></i>&nbsp;Thêm khuyến mãi
                </button>
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
                                <th>Tên khuyến mãi</th>
                                <th>Giá trị</th>
                                <th>Bắt đầu</th>
                                <th>Kết thúc</th>
                                <th><i class="fas fa-cog"></i></th>
                            </tr>
                            </thead>
                            <tbody style="" id="row-added-promo">
                            </tbody>
                        </table>
                    </div>
                </div>

            </div>
        </div>
    </div>
</div>

<div id="add-promo-modal" class="modal fade" data-backdrop="static" role="dialog">
    <div class="modal-dialog mw-100 w-75">
        <!-- Modal content-->
        <div class="modal-content shadow-modal">
            <div class="modal-header">
                <h4>Danh sách khuyến mãi</h4>
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
                                <th>Mã</th>
                                <th>Tên khuyến mãi</th>
                                <th>Loại</th>
                                <th>Giá trị</th>
                                <th>Bắt đầu</th>
                                <th>Kết thúc</th>
                                <th><i class="fas fa-cog"></i></th>
                            </tr>
                            </thead>
                            <tbody style="" id="row-promos">
                            </tbody>
                        </table>
                        <div id="page-promo">
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </div>
</div>