<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/common/header.jsp"%>
<body>
<script type="text/javascript">
    jQuery(document).ready(
            function($){
                $(".box img").lazyload({
                    placeholder : "http://img.yixieshi.com/style/yixieshi/img/grey.gif",
                    effect      : "fadeIn"
                });
            });
</script>
<%@include file="/WEB-INF/jsp/common/navigate.jsp"%>
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
                            class="hot fr">人气：${teacher.viewsnum}</span></div>
                    <a href="/teachers/${teacher.name}"><img src="http://img.yixieshi.com/style/yixieshi/img/grey.gif"
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
<%@include file="/WEB-INF/jsp/common/footer.jsp"%>
</body>
</html>