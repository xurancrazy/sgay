<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/WEB-INF/jsp/common/sourceCommon.jsp"%>
    <script type="text/javascript">
        jQuery(document).ready(
                function ($) {
                    $(".list_box img").lazyload({
                        placeholder: "http://img.yixieshi.com/style/yixieshi/img/grey.gif",
                        effect: "fadeIn"
                    });
                });
    </script>
    <script type="text/javascript">
        $(function () {
            $(".list_tp a").click(function () {
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
<%@include file="/WEB-INF/jsp/common/navigate.jsp"%>
<div class="content container">
    <div id="contrainer" class="333">
        <div class="top_box">
            <div class="well">
                <div class="weizhi"><a href="/">番号站</a> &gt; <a href="/teachers/${teacher.name}">${teacher.name}</a></div>
                <div class="well_tit" style="width:670px;display:inline-block;">
                    <h2>${teacher.name}<span class="list_tp"><a href="#" title="投她一票" class="img_on"
                                                                rel="${teacher.id}">投票</a></span></h2>
                    <img src="/http://fanhaozhan-1253139182.cosgz.myqcloud.com/images/icon/full/${teacher.img}" width="125" height="125" alt="${teacher.name}"
                         title="${teacher.name}" style="float: left;">
                    <p class="avms">${teacherDescription}</p>
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
			<a href="/movies/${movie.fanhao}" target="_blank">
			<img src="http://img.yixieshi.com/style/yixieshi/img/grey.gif"
                 data-original="http://fanhaozhan-1253139182.cosgz.myqcloud.com/images/cover/full/${movie.imghref}" style="display: inline;" alt="${movie.fanhao}"></a>
			<span class="arr"><span></span></span>			</span>
			<span class="list_text">
			<em><b><a
                    href="/movies/${movie.fanhao}">${movie.fanhao}</a></b><p><strong>${movie.title}</strong>...  </p></em>
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
        </div>
    </div>
</div>
<%@include file="/WEB-INF/jsp/common/footer.jsp"%>
</body>
</html>
