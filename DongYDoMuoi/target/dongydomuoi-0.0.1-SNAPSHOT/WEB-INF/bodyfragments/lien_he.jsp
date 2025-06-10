<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<link rel="stylesheet" href="css/common_category.css">

<link rel="stylesheet" href="css/lien_he.css">
<script src="ajax/pages/page_lien_he.js"></script>
<script src="ajax/model/ajax_bk_cdn.js"></script>
<%--<script src="js/lienhe.js"></script>--%>

<section id="sectionLienHe" class="lienHe">
    <div class="container">
        <div class="row">
            <div class="col-12 col-lg-10 offset-lg-1 lienHe__userInfo mt-3 ">
                <div class="lienHe__userInfo--box row pl-3 pr-3 pl-md-0 pr-md-0">
                    <h3 class="col-12 lienHe__userInfo--title text-uppercase text-center mb-md-5">
                        Thông tin liên hệ
                    </h3>
                    <div class="col-6 lienHe__userInfo--name mt-3">
                        <label for="input-name"><strong>Họ tên<span style="color: red;">(*)</span></strong></label>
                        <input type="text" id="input-name" class="form-control">
                        <div class="invalid-feedback">
                            Looks good!
                        </div>
                    </div>

                    <div class="col-6 lienHe__userInfo--email  mt-3" >
                        <label for="input-email"><strong>Email<span style="color: red;">(*)</span></strong></label>
                        <input type="text" id="input-email" class="form-control">
                        <div class="invalid-feedback">
                            Looks good!
                        </div>
                    </div>

                    <div class="col-6 lienHe__userInfo--phone  mt-3" >
                        <label for="input-phone"><strong>Số điện thoại<span style="color: red;">(*)</span></strong></label>
                        <input type="text" id="input-phone" class="form-control">
                        <div class="invalid-feedback">
                            Looks good!
                        </div>
                    </div>

                    <div class="col-6 lienHe__userInfo--phone  mt-3" >
                        <label for="input-phone"><strong>Địa chỉ</strong></label>
                        <input type="text" id="input-address" class="form-control">
                        <div class="invalid-feedback">
                            Looks good!
                        </div>
                    </div>

                    <div class="col-12 lienHe__userInfo--content mt-3" >
                        <label for="input-content"><strong>Nội dung liên hệ</strong></label>
                        <textarea name="" id="input-content" cols="30" rows="5" class="form-control" ></textarea>
                    </div>

                    <div class="col-12 lienHe__userInfo--sentBtn mt-3 d-flex justify-content-end">
                        <button class="btn btn-primary" id="btn-sendInfo">Gửi liên hệ</button>
                    </div>
                </div>
            </div>

<%--            <div class="col-12 col-md-5 lienHe__contact mt-5">--%>
<%--                <div class="lienHe__contact--wrapper">--%>
<%--                    <div class="lienHe__contact--box pl-3 pr-3 pl-md-0 pr-md-0">--%>
<%--                        <h3 class="lienHe__contact--title">--%>
<%--                            TRUNG TÂM NGHIÊN CỨU VÀ ỨNG DỤNG THUỐC DÂN TỘC--%>
<%--                        </h3>--%>
<%--                        <ul class="lienHe__contact--listDetails">--%>
<%--                            <li><i style="margin-right: 10px;" class="fas fa-globe-americas"></i><a href="https://www.thuocdantoc.org">www.thuocdantoc.org</a></li>--%>
<%--                            <li><i style="margin-right: 10px;" class="fas fa-envelope"></i><a href="mailto:info@thuocdantoc.org">info@thuocdantoc.org</a></li>--%>

<%--                        </ul>--%>
<%--                    </div>--%>

<%--                    <div class="lienHe__workSchedule--box pl-3 pr-3 pl-md-0 pr-md-0">--%>
<%--                        <h3 class="lienHe__workSchedule--title">--%>
<%--                            Lịch làm việc--%>
<%--                        </h3>--%>
<%--                        <div class="lienHe__workSchedule--detail">--%>
<%--                            <p>Tất cả các ngày trong tuần</p>--%>
<%--                            <ul>--%>
<%--                                <li>Sáng: 9h-10h</li>--%>
<%--                                <li>Chiều: 13h-14h</li>--%>
<%--                            </ul>--%>
<%--                        </div>--%>

<%--                    </div>--%>
<%--                </div>--%>

<%--            </div>--%>


        </div>
    </div>

</section>

<section id="sectionBranches" class="branches">
    <div class="container branches__list" id="branches-list">
        <!--=== hidden branch element=== -->
        <div class="row branches__element mt-5 d-none" id="hiddenBranchElement">
            <div class="col-12 col-lg-7 d-flex justify-content-start">
                        <span class="branches__element--name text-uppercase">
                            Quảng Bình
                        </span>
                <span class="branches__element--detail">
                            <h4 class="font-weight-bold">Biệt thự B31 ngõ 70 Nguyễn Thị Định,Thanh Xuân - Hà Nội</h4>
                            <p><span>Điện thoại: </span><a href="tel:(024)7109 6699">(024)7109 6699</a></p>
                        </span>
            </div>
            <div class="col-12 col-lg-5 branches__element--map mt-5 mt-md-0">
                <iframe src="https://www.google.com/maps/embed?pb=!1m14!1m12!1m3!1d29803.124231097172!2d105.84077260000001!3d20.976977050000002!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!5e0!3m2!1sen!2s!4v1600053976474!5m2!1sen!2s" frameborder="0" style="border:0;" allowfullscreen="" aria-hidden="false" tabindex="0"></iframe>
            </div>
        </div><!-- end div branch element-->
        <!--=== end hidden branch element=== -->
    </div>
</section>