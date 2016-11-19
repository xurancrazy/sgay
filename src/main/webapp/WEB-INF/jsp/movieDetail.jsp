<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/common/header.jsp" %>
<body>
<%@include file="/WEB-INF/jsp/common/navigate.jsp" %>
<div class="content container">
    <div class="weizhi2">当前位置：<a href="/">番号站</a> &gt; <a href="/teachers/${teacherName}">${teacherName}</a> &gt; <a
            href="/teachers/${teacherName}/${movie.fanhao}">${movie.fanhao}</a>
    </div>
    <div class="row">
        <div id="contrainer" class="span8">
            <div class="article">
                <h1 class="heading">${movie.title}</h1>
                <div class="meta txt-shadow">
                    <!--顶级栏目链接--> <p><span>发片时间：${movie.publishtime}</span>
<span>女优：
<a href="/teachers/${teacherName}">${teacherName}</a>
</span><span>番号：${movie.fanhao}</span></p>
                </div>
                <div id="14849" class="artCon">
                    <img alt="${movie.title}" src="/uploads/images/cover/full/${movie.imghref}">
                </div>

                <!--种子搜索-->
                <div class="pages">
                    <div class="loading" style="display: none;">请等待<span id="daojishi">1</span>秒<br>
                        <p style="padding:15px;mrgin-top:20px;color:#D04725;">
                            种子链接读不动是因为你浏览器屏蔽了广告显示，换浏览器或关闭广告插件后即可，这也是无奈之举，毕竟服务器域名都要钱，忘理解，谢谢！</p></div>
                    <div class="searchBox" style="display: block;">
                        <div class="btn">
                            <button type="submit" style="border:0;background:none;"><a
                                    href="https://btdb.in/q/MEYD-202/" target="_blank"><strong>MEYD-202</strong>种子</a>
                            </button>
                            　(首选通道)
                        </div>
                    </div>
                    <form action="http://btkitty.bid/#kwd" method="post" name="search" target="_blank" rel="nofollow">
                        <div class="searchBox" style="display: block;">
                            <div class="btn">
                                <button type="submit" style="border:0;background:none;"><strong>MEYD-202</strong>种子　(备用通道)
                                </button>
                            </div>
                            <p style="padding:15px;color:#D04725;">(种子搜索结果由第三方网站BTkitty提供，弹窗广告较多请注意甄别！)</p>
                            <div class="kwd"><input type="hidden" name="keyword" id="kwd" value="MEYD-202"></div>
                            <script language="javascript"
                                    type="text/javascript">document.getElementById('kwd').value = decodeURIComponent('MEYD-202');</script>
                        </div>
                    </form>
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
                                <li><a href="/teachers/${sameTeacherMovie.teacher}/${sameTeacherMovie.fanhao}"
                                       rel="nofollow"><img
                                        src="/uploads/images/cover/full/${sameTeacherMovie.imghref}"
                                        alt="${sameTeacherMovie.fanhao}"></a><a class="hotArticle"
                                                                                href="/teachers/${sameTeacherMovie.teacher}/${sameTeacherMovie.fanhao}">
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
                                       href="/teachers/${sameTypeMovie.teacher}/${sameTypeMovie.fanhao}"><img
                                        src="/uploads/images/cover/full/${sameTypeMovie.imghref}"
                                        alt="${sameTypeMovie.fanhao}"></a><a
                                        class="hotArticle"
                                        href="/teachers/${sameTypeMovie.teacher}/${sameTypeMovie.fanhao}">${sameTypeMovie.fanhao}</a></li>
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
