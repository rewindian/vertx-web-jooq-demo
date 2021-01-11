package com.xxx.core.base;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.serviceproxy.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Xu Haidong
 * @date 2018/8/15
 */
public abstract class BaseAsyncService {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    public String getAddress() {
        String className = this.getClass().getName();
        return className.substring(0, className.lastIndexOf("Impl")).replace(".impl", "");
    }

    public Class getAsyncInterfaceClass() throws ClassNotFoundException {
        String className = this.getClass().getName();
        return Class.forName(className.substring(0, className.lastIndexOf("Impl")).replace(".impl", ""));
    }

    protected <T> void handleException(Throwable throwable, Handler<AsyncResult<T>> resultHandler) {
        LOGGER.error(throwable.getMessage(), throwable);
        resultHandler.handle(ServiceException.fail(1, throwable.getMessage()));
    }
}
