package com.xxx.core.util;

import com.xxx.core.verticle.AsyncRegistryVerticle;
import com.xxx.core.verticle.RouterRegistryVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.ext.web.Router;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 开始注册vertx相关服务
 */
public class DeployVertxServer {

    private static Logger LOGGER = LoggerFactory.getLogger(DeployVertxServer.class);

    public static void startDeploy(Router router, String asyncServiceImplPackages, int port, int asyncServiceInstances) {
        LOGGER.debug("Start Deploy....");
        LOGGER.debug("Start registry router....");
        VertxHolder.getVertxInstance().deployVerticle(new RouterRegistryVerticle(router, port));
        LOGGER.debug("Start registry service....");
        if (asyncServiceInstances < 1) {
            asyncServiceInstances = 1;
        }
        for (int i = 0; i < asyncServiceInstances; i++) {
            VertxHolder.getVertxInstance().deployVerticle(new AsyncRegistryVerticle(asyncServiceImplPackages), new DeploymentOptions().setWorker(true));
        }
    }

    public static void startDeploy(Router router, String asyncServiceImplPackages, int asyncServiceInstances) {
        LOGGER.debug("Start Deploy....");
        LOGGER.debug("Start registry router....");
        VertxHolder.getVertxInstance().deployVerticle(new RouterRegistryVerticle(router));
        LOGGER.debug("Start registry service....");
        if (asyncServiceInstances < 1) {
            asyncServiceInstances = 1;
        }
        for (int i = 0; i < asyncServiceInstances; i++) {
            VertxHolder.getVertxInstance().deployVerticle(new AsyncRegistryVerticle(asyncServiceImplPackages), new DeploymentOptions().setWorker(true));
        }
    }
}
