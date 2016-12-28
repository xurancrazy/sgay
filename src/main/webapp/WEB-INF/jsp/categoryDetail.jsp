<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/WEB-INF/jsp/common/sourceCommon.jsp" %>
    <script type="text/javascript">
        $(function () {
            $.extend($.fn.bootstrapTable.defaults, $.fn.bootstrapTable.locales['zh-CN']);
            $('#table').bootstrapTable({
                method: 'post',
                contentType: "application/x-www-form-urlencoded",
                url: '/categories/'+$(".hidden").text(),
                cache: false,
                pagination: true,
                pageNumber: 1,
                paginationLoop: false,
                queryParams: queryParams,
                responseHandler: formatHref,
                pageSize: 50,
                pageList: [10, 25, 50, 100],
                sidePagination: "server",
                columns: [{
                    field: 'fanhao',
                    title: '作品番号'
                }, {
                    field: 'teacher',
                    title: '出演老师'
                }, {
                    field: 'publishtime',
                    title: '发布时间'
                }],
                onLoadSuccess: function (data) {
                    $('#table').bootstrapTable('load', data);
                }
            });
        });
        function queryParams(params) {
            var tmp = {
                limit: params.limit,
                offset: params.offset,
                order: params.order,
                category: $(".hidden").text()
            };
            return tmp;
        }
        function formatHref(res) {
            $.each(res.rows, function (index, item) {
                var fanhao = item.fanhao;
                var teacher = item.teacher;
                var fanhaoHref = "<a target='_blank' href = '/movies/"+ fanhao + "'>" + fanhao + "</a>";
                var teacherHref = "<a href = '/teachers/"+ teacher + "'>" + teacher + "</a>";
                item.fanhao = fanhaoHref;
                item.teacher = teacherHref;
            });
            return res;
        }

    </script>
</head>
<body>
<%@include file="/WEB-INF/jsp/common/navigate.jsp" %>
<div class="content container">
    <div class="weizhi2">当前位置：<a href="/">番号站</a> &gt; <a href="/category">作品分类</a> &gt; <a
            href="/category/${category}">${category}</a>
    </div>
    <div class="row">
        <div id="contrainer" class="span8">
            <div class="article"  style="margin-top:10px;" >
                <scan class="hidden">${category}</scan>
                <table id="table"></table>
            </div>
        </div>
        <!--左侧文章结束-->
        <%@include file="/WEB-INF/jsp/common/sideAD.jsp" %>

    </div>

</div>
<%@include file="/WEB-INF/jsp/common/footer.jsp" %>
</body>
</html>