package com.laioffer.staybooking.exception;
// 后端出错，后台配置有问题，google地图停用...
public class GeoCodingException extends RuntimeException {
    public GeoCodingException(String message) {
        super(message);
    }
}
