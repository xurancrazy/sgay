package com.sgay.giligili.quartz;

import com.sgay.giligili.BaseTest;
import org.junit.Test;

/**
 * Created by xurancrazy on 2016/11/27.
 */
public class RedisCachePersistenceQuartzTest extends BaseTest {

    @Test
    public void updateMovieViewNum(){
        mRedisCachePersistenceQuartz.updateMovieViewNum();
    }

    @Test
    public void updateTeacherViewNum(){
        mRedisCachePersistenceQuartz.updateTeacherViewNum();
    }

    @Test
    public void updateTeacherLikeNum(){
        mRedisCachePersistenceQuartz.updateTeacherLikeNum();
    }
}
