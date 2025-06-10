<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<script src="resources/js/ajax/header.js"></script>
<!-- Navbar-->
<header class="app-header"><a class="app-header__logo" href="#">Đông y Đỗ Mười</a>
    <!-- Sidebar toggle button--><a class="app-sidebar__toggle" href="#" data-toggle="sidebar"
                                    aria-label="Hide Sidebar"></a>
    <!-- Navbar Right Menu-->
    <ul class="app-nav">
<%--        <li class="row" style="margin-right: 5%">--%>
<%--            <button id="btn-change-company" class="btn my-btn">--%>
<%--                <img id="logo-com" src="resources/images/groupIcon.png" width="30px" height="30x">--%>
<%--                <span style="color: #fff; margin-left: 5px; font-size: 20px" class="font-weight-bold"--%>
<%--                      id="company-name"></span>--%>
<%--            </button>--%>
<%--        </li>--%>
        <!--Notification Menu-->
<%--        <li class="dropdown"><a id="noti-bell" class="app-nav__item" href="#" data-toggle="dropdown"--%>
<%--                                aria-label="Show notifications"><i--%>
<%--                class="fa fa-bell-o fa-lg"></i>--%>
<%--            <span id="noti-num">0</span>--%>
<%--        </a>--%>
<%--            <ul class="app-notification dropdown-menu dropdown-menu-right">--%>
<%--                <li class="app-notification__title"><a id="read-all-noti" href="#">Đánh dấu tất cả đã đọc</a></li>--%>
<%--                <button id="btn-view-noti" style="display: none" data-toggle="modal"--%>
<%--                        data-target="#bad-answer-modal"></button>--%>
<%--                <div class="app-notification__content">--%>

<%--                </div>--%>
<%--                <li class="app-notification__footer"><a id="read-more-noti" href="thong-bao">Xem thêm thông báo.</a>--%>
<%--                </li>--%>
<%--            </ul>--%>
<%--        </li>--%>
        <!-- User Menu-->
        <li class="dropdown"><a class="app-nav__item" href="#" data-toggle="dropdown" aria-label="Open Profile Menu"><i
                class="fa fa-user fa-lg"></i></a>
            <ul class="dropdown-menu settings-menu dropdown-menu-right">
                <li><a class="dropdown-item" href="admin-tai-khoan"><i class="fa fa-user fa-lg"></i> Thông tin cá nhân</a></li>
                <li><a class="dropdown-item" href="admin-doi-mat-khau"><i class="fa fa-key fa-lg"></i> Đổi mật khẩu</a></li>
                <li><a class="dropdown-item" href="login?logout=true"><i class="fa fa-sign-out fa-lg"></i> Đăng xuất</a></li>
            </ul>
        </li>
    </ul>
</header>

<!-- Modal -->
<div id="bad-answer-modal" class="modal fade" data-backdrop="static" role="dialog">
    <div class="modal-dialog modal-lg">
        <!-- Modal content-->
        <div class="modal-content shadow-modal">
            <div class="modal-header">
                <div class="row col-lg-12" style="justify-content: center">
                    <h4 id="title-dialog-noti" class="modal-title">Chi tiết phản hồi</h4>
                </div>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="row" style="justify-content: center">
                            <div class="col-lg-12">
                                <div class="row">
                                    <div class="form-group col-lg-12">
                                        <label class="col-form-label">Khảo sát<span
                                                style="color: red"></span></label>
                                        <input id="bad-survey-name" disabled placeholder=""
                                               class="form-control here" type="text">
                                    </div>
                                </div>
                                <div class="row" style="justify-content: space-between">
                                    <div class="form-group col-lg-6">
                                        <label class="col-form-label">Thiết bị<span
                                                style="color: red"></span></label>
                                        <input id="bad-device" disabled placeholder=""
                                               class="form-control here" type="text">
                                    </div>
                                    <div class="form-group col-lg-6">
                                        <label class="col-form-label">Đối tượng<span
                                                style="color: red"></span></label>
                                        <input id="bad-object" disabled placeholder="Email"
                                               class="form-control here" required="required" type="email">
                                    </div>
                                </div>

                                <div class="row" style="justify-content: space-between">
                                    <div class="form-group col-lg-6">
                                        <label class="col-form-label">Thời gian<span
                                                style="color: red"></span></label>
                                        <input id="bad-time" disabled class="form-control here" type="text">
                                    </div>
                                </div>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row" style=" padding-bottom: 4%; justify-content: center">
                <div class="col-lg-3" style=" display: flex; justify-content: center">
                    <button style="margin-left: 5%" type="button" class="btn btn-secondary"
                            data-dismiss="modal">Đóng
                    </button>
                </div>
            </div>
        </div>
    </div>
</div>

<style>
    .my-btn {
        color: #fff;
    }

    .my-btn:hover {
        background-color: #ffc700;
        color: #222c36;
        transition: 0.6s;
        transform: scale(1.2);
    }

    #noti-bell {
        position: relative;
        display: inline-block;
    }

    #noti-num {
        position: absolute;
        top: 0;
        padding: 1px 7px;
        border-radius: 50%;
        background: red;
        color: white;
    }

</style>

