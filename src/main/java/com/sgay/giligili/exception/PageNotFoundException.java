package com.sgay.giligili.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by xurancrazy on 2016/10/23.
 */

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PageNotFoundException extends RuntimeException {

    private String customizeMessage;

    public PageNotFoundException(String message){
        this.customizeMessage = message;
    }

    public String getCustomizeMessage() {
        return customizeMessage;
    }

    public void setCustomizeMessage(String customizeMessage) {
        this.customizeMessage = customizeMessage;
    }
}
