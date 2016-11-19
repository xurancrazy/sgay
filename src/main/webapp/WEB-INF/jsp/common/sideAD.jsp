<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>

<!-- span4 Start -->
<div class="span4">
    <div class="sidebox sy_ad01">
        <div class="mbox">
        </div>
    </div>
    <div class="sidebox">
        <div class="hotlist">
            <div class="hd">
                <h2 class="heading pull-left">热门推荐</h2>
                <div class="tab pull-right">
                    <ul class="toptabs">
                        <li class="active"><a href="#br" data-toggle="tab">本日</a></li>
                        <li><a href="#by" data-toggle="tab">本月</a></li>
                        <li><a href="#bz" data-toggle="tab">本周</a></li>
                    </ul>
                </div>
            </div>
            <div class="tab-content">
                <ol id="br" class="diggArea tab-pane fade active in">
                    <c:forEach items="${todayPopularMovies}" var="movie" varStatus="s">
                        <li>
                            <div><span class="diggNum h">${s.index+1}</span><a
                                    href="/teachers/${movie.teacher}/${movie.fanhao}" target="_blank"
                                    title="${movie.title}">${movie.fanhao}${movie.title}</a>
                            </div>
                        </li>
                    </c:forEach>
                </ol>
                <ol id="by" class="diggArea tab-pane fade">
                    <c:forEach items="${lastWeekPopularMovies}" var="movie" varStatus="s">
                        <li>
                            <div><span class="diggNum h">${s.index+1}</span><a
                                    href="/teachers/${movie.teacher}/${movie.fanhao}"
                                    target="_blank"
                                    title="${movie.title}">${movie.fanhao}${movie.title}</a>
                            </div>
                        </li>
                    </c:forEach>
                </ol>
                <ol id="bz" class="diggArea tab-pane fade">
                    <c:forEach items="${lastMonthPopularMovies}" var="movie" varStatus="s">
                        <li>
                            <div><span class="diggNum h">${s.index+1}</span><a
                                    href="/teachers/${movie.teacher}/${movie.fanhao}" target="_blank"
                                    title="${movie.title}">${movie.fanhao}${movie.title}</a>
                            </div>
                        </li>
                    </c:forEach>
                </ol>
            </div>
        </div>
    </div>
    <!--热门结束-->
    <div class="sidebox">
        <div class="hd">
            <h2 class="heading">编辑推荐</h2>
        </div>
        <ul class="ph-tx-list clearfix">
            <c:forEach items="${editorRecommendMovies}" var="movie">
                <li>
                    <div class="pic pull-left"><a href="/teachers/${movie.teacher}/${movie.fanhao}" target="_blank"
                                                  title="${movie.title}" rel="nofollow"><img
                            src="/uploads/images/cover/full/${movie.imghref}"
                            alt="${movie.fanhao}"></a></div>
                    <div class="dec"><h3><a href="/teachers/${movie.teacher}/${movie.fanhao}" target="_blank"
                                            title="${movie.title}">${movie.title}</a></h3>
                        <p><span>${movie.publishtime}</span><span>${movie.viewsnum}人浏览</span></p></div>
                </li>
            </c:forEach>
        </ul>
    </div>
    <div class="sidebox">
        <div class="hd">
            <h2 class="heading">随机推荐</h2>
        </div>
        <ul class="tx-list">

            <c:forEach items="${randomMovies}" var="movie">
                <li>
                    <font style="color:#F26C4F;">Hot</font>


                    <a href="/teachers/${movie.teacher}/${movie.fanhao}" target="_blank"
                       title="${movie.title}">${movie.fanhao}${movie.title}</a>
                </li>
            </c:forEach>
        </ul>
    </div>
    <div class="sidebox">
        <div class="hd">
            <h2 class="heading">图文推荐</h2>
        </div>
        <!--广告1-->

        <!--广告1 End-->
        <ul class="photo-list">
            <c:forEach items="${imageAndTextRecommendMovies}" var="movie">
                <li>
                    <div class="pic"><a href="/teachers/${movie.teacher}/${movie.fanhao}" target="_blank"
                                        title="${movie.title}"
                                        rel="nofollow"><img src="/uploads/images/cover/full/${movie.imghref}"
                                                            alt="${movie.fanhao}"></a></div>
                    <a href="/teachers/${movie.teacher}/${movie.fanhao}" target="_blank"
                       title="${movie.title}">${movie.title}</a>
                </li>
            </c:forEach>
        </ul>

    </div>
    <div class="sidebox sy_ad02">
        <!-- 广告位：橱窗广告-首页 -->
    </div>
</div>
<!-- span4 End-->
