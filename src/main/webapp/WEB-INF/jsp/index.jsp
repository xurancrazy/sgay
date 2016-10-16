<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="番号">
    <meta name="keywords" content="番号">
    <link rel="icon" href="/source/images/ipz722pl-lp.jpg">

    <title>番号站</title>

    <!-- Bootstrap core CSS -->
    <link href="/source/bootstrap-3.3.5-dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="/source/css/index.css" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link href="/source/css/jumbotron.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="http://cdn.bootcss.com/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>

<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
                    aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">番号站</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
                <li class="active"><a href="#">首页</a></li>
                <li><a href="teachers">女优排名</a></li>
                <li><a href="#contact">站长推荐</a></li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">Dropdown <span class="caret"></span></a>
                    <ul class="dropdown-menu" role="menu">
                        <li><a href="#">Action</a></li>
                        <li><a href="#">Another action</a></li>
                        <li><a href="#">Something else here</a></li>
                        <li class="divider"></li>
                        <li class="dropdown-header">Nav header</li>
                        <li><a href="#">Separated link</a></li>
                        <li><a href="#">One more separated link</a></li>
                    </ul>
                </li>
            </ul>
        </div><!--/.nav-collapse -->
    </div>
</nav>
<!-- Main jumbotron for a primary marketing message or call to action -->
<div class="jumbotron">
    <div class="container">
        <h1>番号站！</h1>
        <p>请输入你要搜索的番号或者女优AAAB</p>
        <form role="form">
            <div class="form-group">
                <label for="fanhao">番号</label>
                <input type="text" class="form-control" id="fanhao" placeholder="番号">
            </div>
            <div class="form-group">
                <label for="exampleInputPassword1">女优名</label>
                <input type="text" class="form-control" id="exampleInputPassword1" placeholder="女优名">
            </div>
            <button type="submit" class="btn btn-primary btn-lg">确定</button>
        </form>
    </div>
</div>

<div class="content container">
    <!--幻灯结束-->
    <div class="row">
        <div id="contrainer" class="span8">
            <!--文章列表开始-->
            <div class="post-list ">
                <ul class="clearfix">
                        <c:forEach items="${recommendMovies}" var="movie">
                            <li>
                            <h2 class="heading"><span class="new-icon"></span>
                                <a href="/teachers/${movie.teacher}/${movie.fanhao}" target="_blank" title="${movie.fanhao}">${movie.fanhao}</a>
                            </h2>
                            <div class="info"><span>发片时间：${movie.publishtime}</span> <span><i
                                    class="icons th-list-icon"></i><span class="hidden">老师：</span><a href="/teachers/${movie.teacher}"
                                                                                                     target="_blank">${movie.teacher}</a></span>
                            </div>

                            <div class="main row-fluid">
                                <div class="thumbnail pull-left"><a href="/teachers/${movie.teacher}/${movie.fanhao}" class="imgview"
                                                                    target="_blank" rel="nofollow"><img
                                        src="uploads/images/cover/full/${movie.imghref}" align="right"
                                        alt="《兽王鲨柳美稀》动物战队兽王者的美女泳装写真出炉"></a></div>
                                <div class="desc pull-left"><p>
                                    　　说到日本的战队系列不知道萤幕前的各位是不是都还有在追呢？像阿漆小时候最迷的就是鸟人战队，除了当时会到录影带店(就说我很老)租片子看以外~还会去买红白机上面的游戏来玩，...</p>
                                </div>
                            </div>
                        </li>
                        </c:forEach>
                </ul>
            </div>
            <!--列表结束-->
        </div>
        <%@include file="/WEB-INF/jsp/common/sideAD.jsp"%>
    </div>
</div>
<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>
<script src="/source/bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>
<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<script src="/source/js/ie10-viewport-bug-workaround.js"></script>
</body>
</html>

