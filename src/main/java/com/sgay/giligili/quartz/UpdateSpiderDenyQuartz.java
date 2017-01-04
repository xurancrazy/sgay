package com.sgay.giligili.quartz;

import com.sgay.giligili.utils.Constants;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by xurancrazy on 2017/1/2.
 */
@Component
public class UpdateSpiderDenyQuartz extends BaseQuartz {

    @Scheduled(cron = "0 0/1 * * * ? ")
    public void updateUserAccessOneMinute() {
        logger.info("updateUserAccessOneMinute" + System.currentTimeMillis());
        mRedisTemplate.delete(Constants.USER_ACCESS_0NE_MINUTE);
    }

    @Scheduled(cron = "0 0 0/1 * * ? ")
    public void updateUserAccessOneHour() {
        logger.info("updateUserAccessOneMinute" + System.currentTimeMillis());
        mRedisTemplate.delete(Constants.USER_ACCESS_0NE_HOUR);
    }

    @Scheduled(cron = "0 30 5 * * ? ")
    public void updateUserAccessDeny() {
        logger.info("updateUserAccessOneMinute" + System.currentTimeMillis());
        mRedisTemplate.delete(Constants.USER_ACCESS_DENY_SET);
    }

}
