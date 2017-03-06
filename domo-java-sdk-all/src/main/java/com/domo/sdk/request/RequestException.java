package com.domo.sdk.request;

public class RequestException extends RuntimeException {
    public RequestException(String msg, Throwable throwable) {
        super(msg, throwable);
    }
}
