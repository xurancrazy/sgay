package com.sgay.giligili.redis;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.data.redis.core.ZSetOperations;

import java.util.Set;

/**
 * Created by xurancrazy on 2016/11/27.
 */
public abstract class RedisSessionCallBack implements SessionCallback{

    public Set<ZSetOperations.TypedTuple<Object>> set;

    @Override
    public Object execute(RedisOperations redisOperations) throws DataAccessException {
        customizeWatch(redisOperations);
        customizeBehavior(redisOperations);
        Object rs = redisOperations.exec();
        return rs;
    }

    public Set<ZSetOperations.TypedTuple<Object>> getSet(){
        return set;
    }

    public Set<ZSetOperations.TypedTuple<Object>> setSet(ZSetOperations.TypedTuple<Object> set){
        return this.set;
    }

    protected abstract void customizeBehavior(RedisOperations redisOperations);

    protected void customizeWatch(RedisOperations redisOperations) {
    }

}
