<%--
  Created by IntelliJ IDEA.
  User: viethoang
  Date: 9/8/2020
  Time: 2:10 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="css/common_category.css">
<script src="js/utils.js"></script>
<script src="ajax/pages/page_common_category.js"></script>

<section id="tinTuc">
    <div class="container">
        <div class="tinTuc__title row">
            <div class="col-12">
                <h2 class="text-uppercase text-center "></h2>
            </div>
        </div>
        <div class="row">

            <tiles:insertAttribute name="view-tt"/>

            <!-- right side -->
            <div class="col-12 col-md-4 col-lg-3 mt-5 mt-md-5">
                <div class="row">
                    <%--===tong dai tu van===--%>
                    <div class="tinTuc__tdtv col-12 col-sm-5 col-md-12">
                        <div class="tinTuc__tdtv--wrap">
                            <h3 class="text-uppercase"><i class="fas fa-comment-medical"></i>Tổng đài tư vấn</h3>
                            <ul class="text-center text-sm-left" id="branch-info-box">
                                <%--===hidden contact===--%>
                                <li class="d-none" id="hidden-branchInfo">
                                    <div class="tinTuc__tdtv--nameContact branch-name">Chi Nhánh Quảng Ninh</div>
                                    <div class="tinTuc__tdtv--details branch-phone">
                                        <a href="tel:(024)7109 6699">SĐT: (024)7109 6699</a>
                                    </div>
                                </li>
                                <%--===end hidden contact===--%>



                            </ul>
                        </div>

                    </div>
                    <%--=== end tong dai tu van===--%>

                    <%-- === gui cau hoi tu van ===--%>
                        <div class="tinTuc__sendQuestion col-12 col-sm-7 col-md-12">
                            <div class="tinTuc__sendQuestion--wrap">
                                <h3 class="text-center">Gửi câu hỏi tư vấn</h3>
                                <div class="tinTuc__sendQuestion--input">
                                    <input id="input-name" class="form-control" type="text" placeholder="Họ tên">
                                    <div class="invalid-feedback">
                                        Looks good!
                                    </div>
                                </div>
                                <div class="tinTuc__sendQuestion--input">
                                    <input id="input-phone" class="form-control" type="text" placeholder="Số điện thoại">
                                    <div class="invalid-feedback">
                                        Looks good!
                                    </div>
                                </div>
                                <div class="tinTuc__sendQuestion--input">
                                    <input id="input-email" class="form-control" type="text" placeholder="email">
                                    <div class="invalid-feedback">
                                        Looks good!
                                    </div>
                                </div>
                                <div class="tinTuc__sendQuestion--input form-group">
                                    <textarea id="input-content" row="5" class="form-control" placeholder="Nội dung">
                                    </textarea>
                                </div>
                                <div class="text-center tinTuc__sendQuestion--button">
                                    <button id="btn-sendInfo" class="btn btn-success">GỬI CÂU HỎI</button>
                                </div>
                            </div>
                        </div>
                    <%--=== end gui cau hoi tu van ===--%>
                </div>

            </div>
            <!-- end right side -->
        </div>


    </div>
</section>


