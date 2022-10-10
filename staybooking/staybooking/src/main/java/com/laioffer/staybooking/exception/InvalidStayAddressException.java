package com.laioffer.staybooking.exception;
// 用户提供的地址有问题
public class InvalidStayAddressException extends RuntimeException {
    public InvalidStayAddressException(String message) {
        super(message);
    }
}
