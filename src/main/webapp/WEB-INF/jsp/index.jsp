<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/WEB-INF/jsp/common/sourceCommon.jsp"%>
    <script type="text/javascript">
        jQuery(document).ready(
                function ($) {
                    $(".post-list img").lazyload({
                        placeholder: "http://img.yixieshi.com/style/yixieshi/img/grey.gif",
                        effect: "fadeIn"
                    });
                });
    </script>
</head>
<body>
<%@include file="/WEB-INF/jsp/common/navigate.jsp" %>
<div class="content container">
    <div style="width: 960px;height: 100px;">
        <script async src="//pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>
        <!-- ad -->
        <ins class="adsbygoogle"
             style="display:block"
             data-ad-client="ca-pub-6258591717321961"
             data-ad-slot="8127484934"
             data-ad-format="auto"></ins>
        <script>
            (adsbygoogle = window.adsbygoogle || []).push({});
        </script>
    </div>
    <!--幻灯结束-->
    <div class="row">
        <div id="contrainer" class="span8">
            <!--文章列表开始-->
            <div class="post-list ">
                <ul class="clearfix">
                    <c:forEach items="${recommendMovies}" var="movie">
                        <li>
                            <h2 class="heading"><span class="new-icon"></span>
                                <a href="/movies/${movie.fanhao}" target="_blank"
                                   title="${movie.fanhao}">${movie.fanhao}</a>
                            </h2>
                            <div class="info"><span>发片时间：${movie.publishtime}</span> <span><i
                                    class="icons th-list-icon"></i><span class="hidden">老师：</span><a
                                    href="/teachers/${movie.teacher}">${movie.teacher}</a></span>
                            </div>

                            <div class="main row-fluid">
                                <div class="thumbnail pull-left"><a href="/movies/${movie.fanhao}"
                                                                    class="imgview"
                                                                    target="_blank" rel="nofollow"><img
                                        src="http://img.yixieshi.com/style/yixieshi/img/grey.gif"
                                        data-original="http://fanhaozhan-1253139182.cosgz.myqcloud.com/images/cover/full/${movie.imghref}"
                                        align="right"
                                        alt="${movie.fanhao}"></a></div>
                                <div class="desc pull-left">
                                    <p>日本AV女优<a href="/teachers/${movie.teacher}">${movie.teacher}</a>参演的作品番号<a href="/movies/${movie.fanhao}">${movie.fanhao}</a>
                                        ，片名<a href="/movies/${movie.fanhao}">${movie.title}</a>，本作品已收录于番号站，正式发片日期是${movie.publishtime}
                                    </p>
                                </div>
                            </div>
                        </li>
                    </c:forEach>
                </ul>
            </div>
            <!--列表结束-->
        </div>
        <%@include file="/WEB-INF/jsp/common/sideAD.jsp" %>
    </div>
</div>
<%@include file="/WEB-INF/jsp/common/footer.jsp" %>
</body>
</html>

