package com.xxx.core.util;

import io.vertx.ext.web.Router;

/**
 * @author Ian
 * @date 2020/12/17 16:00
 */
public class RouterUtil {
    private static Router ourInstance;

    public static Router getInstance() {
        if (ourInstance == null) {
            ourInstance = Router.router(VertxUtil.getVertxInstance());
        }
        return ourInstance;
    }

    private RouterUtil() {
    }
}
