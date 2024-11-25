package com.maliroso.tms.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class TmsException extends RuntimeException {
    private HttpStatus httpStatus;
    public TmsException(String message){
        super(message);
    }
    public TmsException(String message, HttpStatus httpStatus){
        super(message);
        this.httpStatus = httpStatus;
    }

    public TmsException(String message, Throwable cause){
        super(message, cause);
    }

}
