<%--
  Created by IntelliJ IDEA.
  User: hieuv
  Date: 07-Dec-19
  Time: 10:09 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../../library/taglib.jsp" %>
<html lang="vi">
<head>
    <title><tiles:getAsString name="title"/></title>
    <%@include file="../../library/library_css.jsp" %>

    <meta name="description"
          content="Vali is a responsive and free admin theme built with Bootstrap 4, SASS and PUG.js. It's fully customizable and modular.">
    <!-- Twitter meta-->
    <meta property="twitter:card" content="summary_large_image">
    <meta property="twitter:site" content="@pratikborsadiya">
    <meta property="twitter:creator" content="@pratikborsadiya">
    <!-- Open Graph Meta-->
    <meta property="og:type" content="website">
    <meta property="og:site_name" content="Vali Admin">
    <meta property="og:title" content="Vali - Free Bootstrap 4 admin theme">
    <meta property="og:url" content="http://pratikborsadiya.in/blog/vali-admin">
    <meta property="og:image" content="http://pratikborsadiya.in/blog/vali-admin/hero-social.png">
    <meta property="og:description"
          content="Vali is a responsive and free admin theme built with Bootstrap 4, SASS and PUG.js. It's fully customizable and modular.">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="shortcut icon" href="resources/image/ico.png" />

    <script src="resources/js/template/jquery-3.5.1.min.js"></script>

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/gridstack@1.1.0/dist/gridstack.min.css"/>
    <script src="resources/js/template/gridstack.js"></script>

    <%--Select2--%>
    <link rel="stylesheet" href="resources/js/template/plugins/select2/css/select2.css">
    <link rel="stylesheet" href="resources/js/template/plugins/select2-bootstrap4-theme/select2-bootstrap4.min.css">
    <script src="resources/js/template/plugins/select2/js/select2.full.min.js"></script>

    <script src="resources/js/template/pagination.js"></script>
    <link href="https://fonts.googleapis.com/css?family=Roboto+Slab&display=swap" rel="stylesheet">
    <script src="resources/js/common.js"></script>
</head>
<body class="app sidebar-mini rtl">

<tiles:insertAttribute name="menu"/>

<tiles:insertAttribute name="header"/>

<tiles:insertAttribute name="body"/>
<!----------------------Script------------------------------------ -->
<%@include file="../../library/libraby_js.jsp" %>

</body>
</html>
