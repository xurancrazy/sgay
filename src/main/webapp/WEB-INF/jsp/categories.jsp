<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/WEB-INF/jsp/common/sourceCommon.jsp" %>
    <script type="text/javascript">
        $(function(){
            $.extend($.fn.bootstrapTable.defaults, $.fn.bootstrapTable.locales['zh-CN']);
            $('#table').bootstrapTable({
                method:'post',
                contentType: "application/x-www-form-urlencoded",
                url:'/categories',
                cache: false,
                pagination: true,
                pageNumber:1,
                paginationLoop:false,
                responseHandler:formatHref,
                uniqueId: "id",
                pageSize: 50,
                pageList: [10, 25, 50, 100],
                sidePagination: "server",
                columns: [{
                    field: 'id',
                    title: '编号'
                }, {
                    field: 'name',
                    title: '作品分类'
                },{
                    field: 'movieNum',
                    title: '作品数量'
                }],
                onLoadSuccess:function(data){
                    $('#table').bootstrapTable('load', data);
                }
            });
        });

        function formatHref(res) {
            $.each(res.rows,function (index, item) {
                var name = item.name;
                var id = item.id;
                var href = "<a id = 'category:"+ id + "' href = '/categories/"+ name + "'>" + name + "</a>";
                item.name = href;
            });
            return res;
        }

    </script>
</head>
<body>
<%@include file="/WEB-INF/jsp/common/navigate.jsp" %>
<div class="content container">
    <div class="weizhi2">当前位置：<a href="/">番号站</a> &gt; <a href="/categories">作品分类</a>
    </div>
    <div class="row">
        <div id="contrainer" class="span8">
            <div class="article" style="margin-top:10px;">
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