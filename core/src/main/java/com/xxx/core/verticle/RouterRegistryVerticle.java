package com.xxx.core.verticle;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.core.json.JsonObject;
import io.vertx.core.shareddata.LocalMap;
import io.vertx.core.shareddata.SharedData;
import io.vertx.ext.web.Router;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 路由发布
 */
public class RouterRegistryVerticle extends AbstractVerticle {

    private static final Logger LOGGER = LoggerFactory.getLogger(RouterRegistryVerticle.class);

    private static final int HTTP_PORT = 8080;

    private int port = HTTP_PORT;

    private HttpServer server;

    private Router router;

    public RouterRegistryVerticle(Router router) {
        this.router = router;
    }

    public RouterRegistryVerticle(Router router, int port) {
        this.router = router;
        if (port > 0) {
            this.port = port;
        }
    }

    @Override
    public void start(Promise<Void> startPromise) {
        LOGGER.info("To start listening to port {} ......", port);
        SharedData sharedData = vertx.sharedData();
        LocalMap<String, Object> localMap = sharedData.getLocalMap("demo");
        JsonObject envConfig = (JsonObject) localMap.get("envConfig");
        HttpServerOptions options;
        if (envConfig.containsKey("http") && null != envConfig.getValue("http")) {
            options = new HttpServerOptions(envConfig.getJsonObject("http"));
        } else {
            options = new HttpServerOptions();
        }
        options.setPort(port);
        server = vertx.createHttpServer(options).requestHandler(router).listen(ar -> {
            if (ar.succeeded()) {
                startPromise.complete();
            } else {
                startPromise.fail(ar.cause());
            }
        });
    }

    @Override
    public void stop(Promise<Void> stopPromise) {
        if (server == null) {
            stopPromise.complete();
            return;
        }
        server.close(result -> {
            if (result.failed()) {
                stopPromise.fail(result.cause());
            } else {
                stopPromise.complete();
            }
        });
    }
}
