<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/common/header.jsp" %>
<body>
<script type="text/javascript">
    jQuery(document).ready(
            function ($) {
                $(".post-list img").lazyload({
                    placeholder: "http://img.yixieshi.com/style/yixieshi/img/grey.gif",
                    effect: "fadeIn"
                });
            });
</script>
<%@include file="/WEB-INF/jsp/common/navigate.jsp" %>
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
                                <a href="/teachers/${movie.teacher}/${movie.fanhao}" target="_blank"
                                   title="${movie.fanhao}">${movie.fanhao}</a>
                            </h2>
                            <div class="info"><span>发片时间：${movie.publishtime}</span> <span><i
                                    class="icons th-list-icon"></i><span class="hidden">老师：</span><a
                                    href="/teachers/${movie.teacher}">${movie.teacher}</a></span>
                            </div>

                            <div class="main row-fluid">
                                <div class="thumbnail pull-left"><a href="/teachers/${movie.teacher}/${movie.fanhao}"
                                                                    class="imgview"
                                                                    target="_blank" rel="nofollow"><img
                                        src="http://img.yixieshi.com/style/yixieshi/img/grey.gif"
                                        data-original="uploads/images/cover/full/${movie.imghref}"
                                        align="right"
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
        <%@include file="/WEB-INF/jsp/common/sideAD.jsp" %>
    </div>
</div>
<%@include file="/WEB-INF/jsp/common/footer.jsp" %>
</body>
</html>

