<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="plugins/gijgo-combined-1.9.13/css/gijgo.min.css">
<link rel="stylesheet" href="css/datlichkham.css">
<script src="plugins/gijgo-combined-1.9.13/js/gijgo.min.js"></script>
<script src="plugins/inputSpinnerBootstrap/bootstrap-input-spinner.js"></script>
<script src="js/datlichkham.js"></script>
<script src="https://cdn.bksoftwarevn.com/resources/library_js/ajax_micro_service/ajax_edu_service.js"></script>
<script src="ajax/pages/page_dat_lich_kham.js"></script>
<section class="dat-lich">
    <div class="container">
        <div class="row">
            <div class="col-12 col-md-8">
                <div class="row">
                    <div class="col-12">
                        <h3>Thông tin đặt lịch</h3>
                    </div>
                    <div class="col-12 col-md-6">
                        <div class="form-group">
                            <label>Họ Tên <span class="text-danger">(*)</span></label>
                            <input type="text" class="form-control" id="text-name" placeholder="Họ và tên">
                            <div class="invalid-feedback">
                                Looks good!
                            </div>
                        </div>
                    </div>
                    <div class="col-12 col-md-6">
                        <div class="row">
                            <div class="col-6">
                                <div class="form-group dlk-age">
                                    <label>Tuổi <span class="text-danger">(*)</span></label>
                                    <input type="number" class="form-control" value="1" min="1" max="150" step="1" id="text-age" placeholder="Tuổi"/>
                                </div>
                            </div>
                            <div class="col-6">
                                <label class="d-block">Giới tính</label>
                                <div class="form-check form-check-inline">
                                    <input class="form-check-input" type="radio" name="sex"
                                            value="true" checked>
                                    <label class="form-check-label">Nam</label>
                                </div>
                                <div class="form-check form-check-inline">
                                    <input class="form-check-input" type="radio" name="sex" value="false">
                                    <label class="form-check-label">Nữ</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-12 col-md-6">
                        <div class="form-group">
                            <label>Số điện thoại <span class="text-danger">(*)</span></label>
                            <input type="text" class="form-control" id="text-phone" placeholder="Số điện thoại">
                            <div class="invalid-feedback">
                                Looks good!
                            </div>
                        </div>
                    </div>
                    <div class="col-12 col-md-6">
                        <div class="form-group">
                            <label>Chọn cơ sở</label>
                            <select class="form-control" id="select-coso">
                                <option class="d-none"></option>
                            </select>
                        </div>
                    </div>
                    <div class="col-12 col-md-6">
                        <div class="form-group">
                            <label>Chọn bác sĩ</label>
                            <select class="form-control" id="select-bs">
                                <option value="">Tùy chọn</option>
                            </select>
                        </div>
                    </div>
                    <div class="col-12 col-md-6">
                        <div class="row">
                            <div class="col-6">
                                <label for="date-picker">Ngày đặt lịch</label>
                                <div class="form-group ficon">
                                    <input type="text" class="form-control" id="date-picker">
                                    <div class="invalid-feedback">
                                        Looks good!
                                    </div>
                                    <div class="input-group-append d-none">
                                                <span class="input-group-text" id="basic-addon1"><i
                                                        class="far fa-calendar-check"></i></span>
                                    </div>
                                </div>
                            </div>
                            <div class="col-6">
                                <label for="time-picker">Chọn giờ</label>
                                <div class="form-group ficon">
                                    <input type="text" class="form-control" id="time-picker">
                                    <div class="invalid-feedback">
                                        Looks good!
                                    </div>
                                    <div class="input-group-append d-none">
                                                <span class="input-group-text" id="basic-addon2"><i
                                                        class="far fa-clock"></i></span>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-12 col-md-6">
                        <div class="form-group">
                            <label>Địa chỉ liên hệ</label>
                            <input type="text" class="form-control" id="text-address" placeholder="Địa chỉ">
                        </div>
                    </div>
                    <div class="col-12">
                        <div class="form-group">
                            <label>Lí do khám</label>
                            <textarea name="" id="textarea-reason" cols="30" rows="10"></textarea>
                        </div>
                    </div>
                    <div class="col-12 col-md-6 text-center text-md-left">
                        <button class="btn-primary btn-lg btn-loading" onclick="clickDatLichKham()">
                            <img src="file/icon/gifdot.gif" alt="gif-load" class="d-none">
                            <span class="d-block">Đặt lịch khám</span>
                        </button>
                    </div>
                </div>
            </div>
            <div class="col-12 col-md-4 mt-5 m-md-0">
                <div class="widget widget-calendar">
                    <h3 class="widget-title text-center">Lịch làm việc</h3>
                    <div class="widget-content text-working-time">
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
