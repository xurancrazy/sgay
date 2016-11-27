package com.sgay.giligili.redis;

import com.sgay.giligili.utils.Constants;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.SessionCallback;

/**
 * Created by xurancrazy on 2016/11/27.
 */
public abstract class RedisSessionCallBack implements SessionCallback{

    @Override
    public Object execute(RedisOperations redisOperations) throws DataAccessException {
        customizeWatch(redisOperations);
        customizeBehavior(redisOperations);
        Object rs = redisOperations.exec();
        return rs;
    }

    protected abstract void customizeBehavior(RedisOperations redisOperations);

    protected void customizeWatch(RedisOperations redisOperations) {
    }

}
