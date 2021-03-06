package com.sgay.giligili.quartz;

import com.sgay.giligili.service.IMovieService;
import com.sgay.giligili.service.ITeacherService;
import com.sgay.giligili.utils.Constants;
import com.sgay.giligili.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 业务相关的作业调度
 *
 字段               允许值                           允许的特殊字符
 秒	 	            0-59	 	, - * /
 分	 	            0-59	 	, - * /
 小时	 	        0-23	 	, - * /
 日期	 	        1-31	 	, - * ? / L W C
 月份	 	        1-12 或者 JAN-DEC	 	, - * /
 星期	 	        1-7 或者 SUN-SAT	 	, - * ? / L C #
 年（可选）	 	    留空, 1970-2099	 	, - * /

 *  字符代表所有可能的值
 /  字符用来指定数值的增量
 L  字符仅被用于天（月）和天（星期）两个子表达式，表示一个月的最后一天或者一个星期的最后一天
 6L 可以表示倒数第６天
 *
 */
public class BaseQuartz {

    @Autowired
    protected RedisTemplate<String, Object> mRedisTemplate;

    @Autowired
    protected DataSourceTransactionManager dataSourceTransactionManager;

    @Autowired
    protected IMovieService mMovieService;

    @Autowired
    protected ITeacherService mTeacherService;

    protected Logger logger= LoggerFactory.getLogger(this.getClass());

    protected final static String RANDOM_MOVIES_KEY = "randomMovies";

    protected final static String RECOMMEND_MOVIES_KEY = "recommendMovies";

    protected final static String EDITOR_RECOMMEND_MOVIES_KEY = "editorRecommendMovies";

    protected final static String IMAGE_AND_TEXT_MOVIES_KEY = "imageAndTextRecommendMovies";

    protected final static String TEACHERS_KEY = "teachers";

}
