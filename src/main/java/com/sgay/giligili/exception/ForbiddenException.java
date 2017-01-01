package com.sgay.giligili.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by xurancrazy on 2017/1/1.
 */

@ResponseStatus(HttpStatus.FORBIDDEN)
public class ForbiddenException extends RuntimeException {
    private String customizeMessage;

    public ForbiddenException(String message){
        this.customizeMessage = message;
    }

    public String getCustomizeMessage() {
        return customizeMessage;
    }

    public void setCustomizeMessage(String customizeMessage) {
        this.customizeMessage = customizeMessage;
    }
}
