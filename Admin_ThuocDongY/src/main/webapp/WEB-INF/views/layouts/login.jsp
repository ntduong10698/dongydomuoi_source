<%--
  Created by IntelliJ IDEA.
  User: hieuv
  Date: 07-Dec-19
  Time: 9:46 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="../../library/library_css.jsp" %>
    <!-- Font-icon css-->

    <link rel="stylesheet" type="text/css"
          href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <title>Đăng nhập</title>
    <link rel="shortcut icon" href="resources/image/ico.png" />
    <!-- ajax login -->
    <script src="resources/js/template/jquery-3.5.1.min.js"></script>

    <script src="resources/js/common.js"></script>
    <script src="resources/js/ajax/login.js"></script>

</head>
<body>

<section class="material-half-bg">
    <div class="cover"></div>
</section>
<section class="login-content">
    <%--    <div class="logo">--%>
    <%--        <img src="resources/images/bksoftwareLogo.png"--%>
    <%--             alt="" class="img-fluid">--%>
    <%--    </div>--%>
        <div class="login-box">
            <div class="login-form">
                <div class="app-sidebar__user"
                     style="justify-content: center;margin: 0;padding: 0; color: #222c36;font-size: 24px;font-weight: 600;">
                    Đăng nhập
                </div>
                <div class="form-group" style="margin-top: 10%">
                    <label class="control-label">Tên đăng nhập</label>
                    <input class="form-control" type="text" placeholder="Tên đăng nhập" autofocus id="username">
                </div>
                <div class="form-group">
                    <label class="control-label">Mật khẩu</label>
                    <input class="form-control" type="password" placeholder="Mật khẩu" id="password">
                </div>
                <div class="form-group">
                    <div class="utility">
                        <p class="semibold-text mb-2"><a href="" data-toggle="modal"
                                                         data-target="#forget-modal">Quên mật khẩu</a></p>
                    </div>
                </div>
                <div class="form-group btn-container" style="margin-top: 15%">
                    <button class="btn btn-primary btn-block btn-login"><i class="fa fa-sign-in fa-lg fa-fw"></i>ĐĂNG
                        NHẬP
                    </button>
                </div>
            </div>
            <%--        <div class="forget-form">--%>
            <%--            <h3 class="login-head"><i class="fa fa-lg fa-fw fa-lock"></i>Đăng ký tài khoản</h3>--%>

            <%--            <div class="form-group">--%>
            <%--                <label  class="control-label">Tên đăng nhập</label>--%>
            <%--                <input id="register-username" class="form-control" type="text" placeholder="Tên đăng nhập">--%>
            <%--            </div>--%>

            <%--            <div class="form-group">--%>
            <%--                <label class="control-label">Mật khẩu</label>--%>
            <%--                <input id="register-password" class="form-control" type="text" placeholder="Email">--%>
            <%--            </div>--%>

            <%--            <div class="form-group">--%>
            <%--                <label class="control-label">Nhập lại mật khẩu</label>--%>
            <%--                <input id="register-password-again" class="form-control" type="text" placeholder="Email">--%>
            <%--            </div>--%>

            <%--            <div class="form-group">--%>
            <%--                <label  class="control-label">Số điện thoại</label>--%>
            <%--                <input id="register-phone" class="form-control" type="text" placeholder="Username">--%>
            <%--            </div>--%>

            <%--            <div class="form-group">--%>
            <%--                <label class="control-label">Email</label>--%>
            <%--                <input id="forget-email" class="form-control" type="text" placeholder="Email">--%>
            <%--            </div>--%>

            <%--            <div class="form-group btn-container">--%>
            <%--                <button id="btn-forget" class="btn btn-primary btn-block btn-login"><i class="fa fa-unlock fa-lg fa-fw"></i>GỬI--%>
            <%--                </button>--%>
            <%--            </div>--%>
            <%--            <div class="form-group mt-3">--%>
            <%--                <p class="semibold-text mb-0"><a href="#" data-toggle="flip"><i class="fa fa-angle-left fa-fw"></i> Quay lại</a></p>--%>
            <%--            </div>--%>
            <%--        </div>--%>
        </div>
</section>
<div id="forget-modal" class="modal fade" data-backdrop="static" role="dialog">
    <div class="modal-dialog">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <div class="row col-lg-12" style="justify-content: center">
                    <h4 id="title-dialog" class="modal-title">Quên mật khẩu</h4>
                </div>
            </div>
            <div class="modal-body">
                <div class="row" style="justify-content: center">
                    <div class="col-lg-12">

                        <div class="form-group row">
                            <label class="col-lg-2 col-form-label">Email<span
                                    style="color: red"> *</span></label>
                            <div class="col-lg-10">
                                <input id="input-email" placeholder="email@gmail.com"
                                       class="form-control here" type="email">
                            </div>
                        </div>

                    </div>
                </div>
            </div>
            <div class="row" style=" padding-bottom: 4%; justify-content: center">
                <div class="col-lg-3" style=" display: flex; justify-content: center">
                    <button id="submit-forget" type="button" class="btn btn-success">&nbsp;Quên mật khẩu</button>
                    <button id="close-forget" style="margin-left: 5%" type="button" class="btn btn-secondary"
                            data-dismiss="modal">Quay lại
                    </button>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- Essential javascripts for application to work-->
<%@include file="../../library/libraby_js.jsp" %>

</body>
</html>

