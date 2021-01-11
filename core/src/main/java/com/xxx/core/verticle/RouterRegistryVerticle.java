package com.xxx.core.verticle;

import io.vertx.config.ConfigRetriever;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 路由发布
 */
public class RouterRegistryVerticle extends AbstractVerticle {

    private static final Logger LOGGER = LoggerFactory.getLogger(RouterRegistryVerticle.class);


    private static final int HTTP_PORT = 8080;

    private int port = HTTP_PORT;

    private static final String ENV = "dev";

    private HttpServer server;

    private Router router;

    private String env = ENV;

    public RouterRegistryVerticle(Router router) {
        this.router = router;
    }

    public RouterRegistryVerticle(Router router, int port, String env) {
        this.router = router;
        if (port > 0) {
            this.port = port;
        }
        if (StringUtils.isNotBlank(env)) {
            this.env = env;
        }
    }

    @Override
    public void start(Promise<Void> startPromise) {
        LOGGER.info("To start listening to port {} ......", port);
        ConfigRetriever retriever = ConfigRetriever.create(vertx);
        //获取conf/config.json中的配置
        retriever.getConfig(json -> {
            JsonObject config = json.result();
            JsonObject envConfig = config.getJsonObject(env);
            HttpServerOptions options;
            if (envConfig.containsKey("http")) {
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
