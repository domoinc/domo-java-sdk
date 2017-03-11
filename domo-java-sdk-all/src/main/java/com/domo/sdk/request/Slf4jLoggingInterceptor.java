package com.domo.sdk.request;

import okhttp3.logging.HttpLoggingInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Slf4jLoggingInterceptor implements HttpLoggingInterceptor.Logger {
    private static final Logger LOG = LoggerFactory.getLogger(Slf4jLoggingInterceptor.class);
    @Override
    public void log(String message) {
        LOG.info(message);
    }
}
