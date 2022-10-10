package com.laioffer.staybooking.exception;

public class StayNotExistException extends RuntimeException { // RuntimeException: 运行起来才能发现异常，比如indexoutofbound...
    public StayNotExistException(String message) {
        super(message);
    }
}