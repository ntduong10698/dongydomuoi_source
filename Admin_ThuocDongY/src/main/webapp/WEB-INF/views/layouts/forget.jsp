<%--
  Created by IntelliJ IDEA.
  User: hieuv
  Date: 28-Mar-20
  Time: 10:56 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="../../library/libraby_js.jsp" %>
    <%@include file="../../library/library_css.jsp" %>
    <!-- Font-icon css-->

    <link rel="stylesheet" type="text/css"
          href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <title>Quên mật khẩu</title>
    <link rel="shortcut icon" href="resources/image/ico.png" />
    <!-- ajax login -->
    <script src="resources/js/template/jquery-3.5.1.min.js"></script>

    <script src="resources/js/common.js"></script>
    <script src="resources/js/ajax/forget.js"></script>
</head>
<body style="background-color: #dedede !important;">

    <div class="row" style="text-align: left">
        <div class="col-sm-2"></div>
        <div class="col-sm-8">
            <div class="card">
                <div class="card-body">

                    <div class="row">
                        <div class="col-md-12">
                            <form>
                                <div class="form-group row">
                                    <label class="col-4 col-form-label">Mật khẩu mới</label>
                                    <div class="col-8">
                                        <input id="new-pass" name="newpass" placeholder="Tối thiểu 6 ký tự"
                                               class="form-control here" type="password">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label  id="label-password" class="col-4 col-form-label">Xác nhận mật khẩu mới</label>
                                    <div class="col-8">
                                        <input id="confirm-pass" name="newpass" placeholder="Tối thiểu 6 ký tự"
                                               class="form-control here" type="password">
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>

                    <div class="row" style="text-align: center">
                        <div class="col-md-12" style="display: flex; justify-content: center; width: 100%">
                            <button id="submit" type="button" style="display: inline-block" class="btn btn-primary">Đổi mật khẩu</button>
                        </div>
                    </div>

                </div>
            </div>
        </div>
        <div class="col-sm-2"></div>
    </div>

</body>
</html>
