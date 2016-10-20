<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/common/header.jsp"%>
<body>
<%@include file="/WEB-INF/jsp/common/navigate.jsp"%>
<div class="content container">
    <div id="contrainer" class="333">
        <div class="top_box">
            <div class="well">
                <div class="weizhi"><a href="/">番号站</a> &gt; <a href="/teachers/${teacher.name}">${teacher.name}</a>
                    &gt; </div>
                <script type="text/javascript">
                    $(function () {
                        $(".list_tp a").click(function () {
                            var zan = $(this);
                            var id = zan.attr("rel"); //对应id
                            zan.fadeOut(300); //渐隐效果
                            return false;
                        });
                    });
                </script>
                <div class="well_tit" style="width:670px;display:inline-block;">
                    <h2>${teacher.name}<span class="list_tp"><a href="#" title="投她一票" class="img_on"
                                                                rel="1">投票</a></span></h2>
                    <img src="/uploads/images/icon/full/${teacher.img}" width="125" height="125" alt="${teacher.name}"
                         title="${teacher.name}" style="float: left;">
                    <p class="avms">天海翼（天海つばさ / あまみ つばさ），1988年出生，2009年出道，出道以来一直在idea
                        pocket公司发片，属于IP公司的台柱子，天海翼身高160cm，E罩杯的魔鬼身材，加上清纯的天使面孔，idea
                        pocket的优质封面设计和良好的拍摄技巧，也是她的事业平步青云，如虎添翼，让她俘获了众多两岸三地骚年的心，如果说天海翼有什么缺点的话，她的声音不是很好听似乎是她唯一的弱点。</p>
                    <%--<div class="nian btn-group btn-group-justified">--%>
                    <%--<button type="button" class="btn btn-default"><a href="/tianhaiyi/2016/">2016</a></button>--%>
                    <%--<button type="button" class="btn btn-default"><a href="/tianhaiyi/2015/">2015</a></button>--%>
                    <%--<button type="button" class="btn btn-default"><a href="/tianhaiyi/2014/">2014</a></button>--%>
                    <%--<button type="button" class="btn btn-default"><a href="/tianhaiyi/2013/">2013</a></button>--%>
                    <%--<button type="button" class="btn btn-default"><a href="/tianhaiyi/2012/">2012</a></button>--%>
                    <%--<button type="button" class="btn btn-default"><a href="/tianhaiyi/2011/">2011</a></button>--%>
                    <%--<button type="button" class="btn btn-default"><a href="/tianhaiyi/2010/">2010</a></button>--%>
                    <%--<button type="button" class="btn btn-default"><a href="/tianhaiyi/2009/">2009</a></button>--%>
                    <%--</div>--%>
                    <div class="nian btn-group">
                        <c:forEach items="${years}" var="year" varStatus="s">
                            <c:choose>
                                <c:when test="${year == 'all'}">
                                    <button type="button" class="btn btn-default"><a
                                            href="/teachers/${teacher.name}">全部</a></button>
                                </c:when>
                                <c:otherwise>
                                    <button type="button" class="btn btn-default"><a
                                            href="/teachers/${teacher.name}?year=${year}">${year}</a></button>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>
        <div class="clear"></div>
        <div class="list_box 233">
            <div class="list2 233">
                <ul class="clear 233" id="content">
                    <c:forEach items="${movies}" var="movie">
                        <li class="post">
                            <div class="link">
			<span class="list_img" style="width:210px;">
			<a href="/teachers/${movie.teacher}/${movie.fanhao}" target="_blank">
			<img src="/uploads/images/cover/full/${movie.imghref}"
                 data-original="/uploads/images/cover/full/${movie.imghref}" style="display: inline;"></a>
			<span class="arr"><span></span></span>			</span>
			<span class="list_text">
			<em><b><a
                    href="/teachers/${movie.teacher}/${movie.fanhao}">${movie.fanhao}</a></b><p><strong>${movie.title}</strong>...  </p></em>
            <p class="info">
                </p><div class="view" title="${movie.viewsnum}℃">${movie.viewsnum}</div>
                <div class="good" title="发行日期">${movie.publishtime}</div>
             <p></p>
            </span>
                            </div>
                        </li>
                    </c:forEach>
                </ul>
            </div>
            <h2 class="next_page" style="font-size:10px;" rel="nofollow">
                <li><span class="pageinfo">共 <strong>1</strong>页<strong>95</strong>条记录</span></li>

            </h2>
        </div>
    </div>
</div>
<%@include file="/WEB-INF/jsp/common/footer.jsp"%>
</body>
</html>
