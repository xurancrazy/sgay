package com.sgay.giligili;

import com.sgay.giligili.quartz.GeneratePopularMoviesQuartz;
import com.sgay.giligili.quartz.RedisCachePersistenceQuartz;
import com.sgay.giligili.service.IMovieService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by xurancrazy on 2016/11/27.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring-context.xml")
public class BaseTest {
    @Autowired
    protected IMovieService mMovieService;

    @Autowired
    protected RedisCachePersistenceQuartz mRedisCachePersistenceQuartz;

    @Autowired
    protected GeneratePopularMoviesQuartz mGeneratePopularMoviesQuartz;
}
