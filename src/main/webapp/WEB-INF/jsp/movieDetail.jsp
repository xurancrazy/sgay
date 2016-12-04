<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/WEB-INF/jsp/common/sourceCommon.jsp" %>
</head>
<body>
<%@include file="/WEB-INF/jsp/common/navigate.jsp" %>
<div class="content container">
    <div class="weizhi2">当前位置：<a href="/">番号站</a> &gt; <a href="/teachers/${teacherName}">${teacherName}</a> &gt; <a
            href="/movies/${movie.fanhao}">${movie.fanhao}</a>
    </div>
    <div class="row">
        <div id="contrainer" class="span8">
            <div class="article">
                <h1 class="heading">${movie.title}</h1>
                <div class="meta txt-shadow">
                    <!--顶级栏目链接--> <p><span>发片时间：${movie.publishtime}</span>
<span>女优：
<a href="/teachers/${teacherName}">${teacherName}</a>
</span><span>番号：${movie.fanhao}</span><span>作品分类：<c:forEach items="${movieCategorys}" var="movieCategory"><a
                            href="/category/${movieCategory}">${movieCategory}</a>&nbsp;&nbsp;</c:forEach></span></p>
                </div>
                <div id="14849" class="artCon">
                    <img alt="${movie.title}" src="/uploads/images/cover/full/${movie.imghref}">
                </div>

                <!--种子搜索-->
                <div class="pages">
                    <%--<div class="loading" style="display: block;">请等待<span id="daojishi">15</span>秒<br>--%>
                    <%--<p style="padding:15px;mrgin-top:20px;color:#D04725;">--%>
                    <%--种子链接读不动是因为你浏览器屏蔽了广告显示，换浏览器或关闭广告插件后即可，这也是无奈之举，毕竟服务器域名都要钱，望理解，谢谢！</p>--%>
                    <%--</div>--%>
                    <div class="searchBox" style="display: block;">
                        <div class="btn">
                            <button type="submit" style="border:0;background:none;"><a
                                    href="https://btdb.in/q/${movie.fanhao}/"
                                    target="_blank"><strong>${movie.fanhao}</strong>种子</a>
                            </button>
                            　(首选通道)
                        </div>
                    </div>
                    <div class="searchBox" style="display: block;">
                        <div class="btn">
                            <button type="submit" style="border:0;background:none;"><a
                                    href="https://www.torrentkitty.tv/search/${movie.fanhao}/"
                                    target="_blank"><strong>${movie.fanhao}</strong>种子</a>
                            </button>
                            　(备用通道)
                        </div>
                        <p style="padding:15px;color:#D04725;">(种子搜索结果由第三方网站提供，弹窗广告较多请注意甄别！)</p>
                    </div>
                </div>
                <div class="arteditor">
                    <p>围观:<span id="dianji">${movie.viewsnum}</span>次 </p>
                </div>
                <!--滚动栏-->
                <!--滚动栏结束-->
                <!--同演员相关-->
                <div class="wz_xgtj">
                    <div class="blockTitle">
                        <h2>${movie.teacher}作品全集</h2>
                        <a class="more" target="_blank" href="/teachers/${movie.teacher}">更多 &gt;&gt;</a></div>

                    <div class="xg_bd">
                        <ul class="hotArticle">
                            <c:forEach items="${sameTeacherMovies}" var="sameTeacherMovie">
                                <li><a href="/movies/${sameTeacherMovie.fanhao}"
                                       rel="nofollow"><img
                                        src="/uploads/images/cover/full/${sameTeacherMovie.imghref}"
                                        alt="${sameTeacherMovie.fanhao}"></a><a class="hotArticle"
                                                                                href="/movies/${sameTeacherMovie.fanhao}">
                                    <h3>
                                            ${sameTeacherMovie.fanhao}</h3></a></li>
                            </c:forEach>
                        </ul>
                    </div>
                </div>
                <!--同类型相关-->
                <div class="wz_xgtj">
                    <div class="blockTitle">
                        <h2>同类型作品推荐</h2>
                        <a class="more" target="_blank" href="/teachers">更多 &gt;&gt;</a>
                    </div>
                    <div class="xg_bd">
                        <ul class="hotArticle">
                            <c:forEach items="${sameTypeMovies}" var="sameTypeMovie">
                                <li><a rel="nofollow"
                                       href="/movies/${sameTypeMovie.fanhao}"><img
                                        src="/uploads/images/cover/full/${sameTypeMovie.imghref}"
                                        alt="${sameTypeMovie.fanhao}"></a><a
                                        class="hotArticle"
                                        href="/movies/${sameTypeMovie.fanhao}">${sameTypeMovie.fanhao}</a>
                                </li>
                            </c:forEach>
                        </ul>
                    </div>
                </div>
                <!--同演员相关 End-->
            </div>
        </div>
        <!--左侧文章结束-->
        <%@include file="/WEB-INF/jsp/common/sideAD.jsp" %>

    </div>

</div>
<%@include file="/WEB-INF/jsp/common/footer.jsp" %>
</body>
</html>
