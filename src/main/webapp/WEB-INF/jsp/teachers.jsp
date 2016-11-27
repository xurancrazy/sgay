<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/WEB-INF/jsp/common/sourceCommon.jsp"%>
    <script type="text/javascript">
        jQuery(document).ready(
                function ($) {
                    $(".box img").lazyload({
                        placeholder: "http://img.yixieshi.com/style/yixieshi/img/grey.gif",
                        effect: "fadeIn"
                    });
                });
    </script>
    <script type="text/javascript">
        $(function () {
            $(".bottom a").click(function () {
                var zan = $(this);
                var id = zan.attr("rel"); //对应id
                $.ajax({
                    type:"POST",
                    url:"/vote/teachers/"+id,
                    cache:false,
                    dataType:"json",
                    success:function(msg){
                        if(!msg.success){
                            alert("您已经投过该老师了!");
                        }else{
                            alert("感谢您的参与!");
                        }
                        zan.html(msg.num);
                        zan.fadeIn(300); //渐显效果
                    }
                });
                return false;
            });
        });
    </script>
</head>
<body>
<%@include file="/WEB-INF/jsp/common/navigate.jsp" %>
<div class="inner">
    <div class="uu_sy">
        <ul>
            <li class="active"><a href="#all" data-toggle="tab" aria-expanded="true">默认</a></li>
            <c:forEach items="${pinyinTable}" var="pinyin">
                <li><a href="#${pinyin}" data-toggle="tab">${pinyin}</a></li>
            </c:forEach>
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
                    <div class="bottom"><span><a href="#" title="投她一票" class="img_on" rel="${teacher.id}">投票</a></span>
                        <h2 class="name">${teacher.name}</h2>
                        <p>票数:${teacher.likesnum}</p></div>
                </div>
            </c:forEach>
        </div>

        <c:forEach items="${pinyinTable}" var="pinyin">
            <div class="vote-list box tab-pane fade" id="${pinyin}">
                <c:set var="tmp" value="${'list'}${pinyin}"/>
                <c:forEach items="${requestScope.get(tmp)}" var="teacher" varStatus="s">
                    <div class="vote item box">
                        <div class="head"><span class="no fl">TOP.${s.index+1}</span><span
                                class="hot fr">人气：${teacher.viewsnum}</span></div>
                        <a href="/teachers/${teacher.name}"><img
                                src="http://img.yixieshi.com/style/yixieshi/img/grey.gif"
                                alt="${teacher.name}"
                                data-original="uploads/images/icon/full/${teacher.img}"
                                style="display: inline;"></a>
                        <div class="bottom"><span><a href="#" title="投她一票" class="img_on" rel="134">投票</a></span>
                            <h2 class="name">${teacher.name}</h2>
                            <p>票数:${teacher.likesnum}</p></div>
                    </div>
                </c:forEach>
            </div>
        </c:forEach>
    </div>
</div>
<%@include file="/WEB-INF/jsp/common/footer.jsp" %>
</body>
</html>