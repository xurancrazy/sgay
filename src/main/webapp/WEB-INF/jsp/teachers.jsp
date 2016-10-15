<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="番号">
    <meta name="keywords" content="番号">
    <link rel="icon" href="source/imgages/ipz722pl-lp.jpg">

    <title>番号站</title>

    <!-- Bootstrap core CSS -->
    <link href="source/bootstrap-3.3.5-dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="source/css/index.css" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link href="source/css/jumbotron.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="http://cdn.bootcss.com/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<div class="inner">
    <div class="uu_sy">
        <ul>
            <li class="active"><a href="#all" data-toggle="tab" aria-expanded="true">默认</a></li>
            <li class=""><a href="#a" data-toggle="tab" aria-expanded="false">A</a></li>
            <li class=""><a href="#b" data-toggle="tab" aria-expanded="false">B</a></li>
            <li class=""><a href="#c" data-toggle="tab" aria-expanded="false">C</a></li>
            <li><a href="#d" data-toggle="tab">D</a></li>
            <li><a href="#e" data-toggle="tab">E</a></li>
            <li><a href="#f" data-toggle="tab">F</a></li>
            <li><a href="#g" data-toggle="tab">G</a></li>
            <li><a href="#h" data-toggle="tab">H</a></li>
            <li><a href="#j" data-toggle="tab">J</a></li>
            <li><a href="#k" data-toggle="tab">K</a></li>
            <li><a href="#l" data-toggle="tab">L</a></li>
            <li><a href="#m" data-toggle="tab">M</a></li>
            <li><a href="#n" data-toggle="tab">N</a></li>
            <li><a href="#p" data-toggle="tab">P</a></li>
            <li><a href="#q" data-toggle="tab">Q</a></li>
            <li><a href="#r" data-toggle="tab">R</a></li>
            <li><a href="#s" data-toggle="tab">S</a></li>
            <li><a href="#t" data-toggle="tab">T</a></li>
            <li><a href="#w" data-toggle="tab">W</a></li>
            <li><a href="#x" data-toggle="tab">X</a></li>
            <li><a href="#y" data-toggle="tab">Y</a></li>
            <li><a href="#z" data-toggle="tab">Z</a></li>
        </ul>
    </div>
    <div class="tab-content">
        <div class="vote-list box tab-pane fade active in" id="all">
            <c:forEach items="${teachers}" var="teacher" varStatus="s">
                <div class="vote item box">
                    <div class="head"><span class="no fl">TOP.${s.index+1}</span><span
                            class="hot fr">人气：${teacher.onclicknum}</span></div>
                    <a href="/${teacher.name}"><img src="uploads/images/icon/full/${teacher.img}"
                                                     alt="${teacher.name}"
                                                     data-original="uploads/images/icon/full/${teacher.img}"
                                                     style="display: inline;"></a>
                    <div class="bottom"><span><a href="#" title="投她一票" class="img_on" rel="134">投票</a></span>
                        <h2 class="name">${teacher.name}</h2>
                        <p>票数:${teacher.likesnum}</p></div>
                </div>
            </c:forEach>
        </div>
    </div>
</div>
<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>
<script src="source/bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>
<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<script src="source/js/ie10-viewport-bug-workaround.js"></script>
</body>
</html>