package com.sgay.giligili.service.impl;

import com.sgay.giligili.dao.MoviesMapper;
import com.sgay.giligili.dao.TeachersMapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by xurancrazy on 2016/10/16.
 */
public class BaseService {
    @Autowired
    protected MoviesMapper mMoviesMapper;

    @Autowired
    protected TeachersMapper mTeachersMapper;

}
