<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isErrorPage="true"%>
<% response.setStatus(403); %>
<html>
<head>
    <meta http-equiv="Page-Enter" content="blendTrans(Duration=0.5)">
    <meta http-equiv="Page-Exit" content="blendTrans(Duration=0.5)">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>出错啦! - 番号站</title>
    <style type="text/css">
        body {
            vertical-align: middle;
        }

        div.center {
            position: absolute;
            top: 50%;
            left: 50%;
            margin: -25% 0 0 -320px;
            width: 640px;
            min-height: 427px;
            padding: 0px;
        }

        div.errmsg {
            text-align: left;
            width: 640px;
            line-height: 150%;
        }

        a {
            text-decoration: none;
            color: red
        }

        a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body style="direction: ltr;">
<div class="center">
    <img id="img_error" src="http://fanhaozhan-1253139182.cosgz.myqcloud.com/source/images/403.png" height="427" width="640"><br>
    <div class="errmsg">
        <b>返回主页: </b><a href="/">点击返回</a><br>
        <%--<b>处理服务器:</b> <%= request.getLocalName()%><br>--%>
        <%--<b>请求地址:</b> <%= request.getContextPath()%><%=request.getAttribute("javax.servlet.error.request_uri")%><br>--%>
        <b>错误号:</b> 403<br>
        <%--<b>用户IP:</b><%= request.getRemoteAddr()%><br>--%>
    </div>
</div>
</body>
</html>