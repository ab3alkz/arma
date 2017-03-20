<%@ page import="java.util.Date" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Страница администратора</title>
    <script src="/arma/plugin/jquery/jquery-1.11.3.min.js" type="text/javascript"></script>
    <link href="/arma/css/app.css" rel="stylesheet" type="text/css"/>
    <script src="/arma/js/locale.js" type="text/javascript"></script>
    <link href="/arma/plugin/webix/codebase/webix.css" rel="stylesheet" type="text/css"/>
    <script src="/arma/plugin/webix/codebase/webix.js" type="text/javascript"></script>
    <link href="/arma/plugin/bootstrap-notify/animate.css" rel="stylesheet" type="text/css"/>
    <script src="/arma/plugin/bootstrap-notify/bootstrap-notify.min.js" type="text/javascript"></script>
    <script src="/arma/js/admin.js" type="text/javascript"></script>
    <script src="/arma/js/common.js" type="text/javascript"></script>
    <script src="/arma/js/newutils.js?version=<%= new Date()%>" type="text/javascript"></script>


    <%--<script src="http://cdn.webix.com/components/mercury/mercury.js"></script>--%>
</head>
<body>
<div id="menu">
    <%@include file='/incloudes/menu.jsp' %>
</div>

<div>
    <div class="panel panel-default">
        <div class="panel-body">
            <div >
                <div id="mainContainer"></div>
                <div id="sldesContainer"></div>
                <div id="slidesTablePager"></div>
                <div id="archiveTablePager"></div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
