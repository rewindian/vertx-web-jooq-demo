package com.xxx.web;

import com.xxx.core.handlerfactory.RouterHandlerFactory;
import com.xxx.core.util.DeployVertxServer;
import com.xxx.core.util.VertxHolder;
import com.xxx.web.dao.DaoConfigurationHolder;
import com.xxx.web.dao.JooqDaoHolder;
import com.xxx.web.datasource.DataSourceHolder;
import io.vertx.config.ConfigRetriever;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.json.JsonObject;
import io.vertx.core.shareddata.LocalMap;
import io.vertx.core.shareddata.SharedData;
import io.vertx.ext.web.Router;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



/**
 * 程序入口
 */
public class AppMain {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppMain.class);

    private static String env = "dev";

    public static void main(String[] args) {
        if (args.length > 0) {
            env = args[0];
        }
        Vertx tempVertx = Vertx.vertx();
        ConfigRetriever retriever = ConfigRetriever.create(tempVertx);
        //获取conf/config.json中的配置
        retriever.getConfig(ar -> {
            tempVertx.close();
            JsonObject result = ar.result();
            LOGGER.info("配置读取成功：" + result.encode());
            //默认读取dev开发环境配置
            JsonObject envConfig = result.getJsonObject(env);
            JsonObject serverConfig = envConfig.getJsonObject("server");
            JsonObject vertxConfig = envConfig.getJsonObject("vertx");
            JsonObject dataSourceConfig = envConfig.getJsonObject("dataSource");
            JsonObject customConfig = envConfig.getJsonObject("custom");
            Vertx vertx = Vertx.vertx(new VertxOptions(vertxConfig));
            VertxHolder.init(vertx);
            //配置保存在共享数据中
            SharedData sharedData = vertx.sharedData();
            LocalMap<String, Object> localMap = sharedData.getLocalMap("demo");
            localMap.put("env", env);
            localMap.put("envConfig", envConfig);
            //先初始化再发布Http服务
            vertx.executeBlocking(p -> {
                //顺序不能乱
                try {
                    //初始化数据源
                    DataSourceHolder.init(dataSourceConfig);
                    //初始化jooq dao配置
                    DaoConfigurationHolder.init();
                    //初始化dao
                    JooqDaoHolder.init(customConfig.getString("daoLocations"));
                    p.complete();
                } catch (Exception e) {
                    p.fail(e);
                }
            }).onComplete(ar2 -> {
                if (ar2.succeeded()) {
                    Router router = new RouterHandlerFactory(customConfig.getString("routerLocations"), serverConfig.getString("contextPath")).createRouter();
                    DeployVertxServer.startDeploy(router, customConfig.getString("handlerLocations"), serverConfig.getInteger("port"),
                            customConfig.getInteger("asyncServiceInstances"));
                } else {
                    LOGGER.error(ar.cause().getMessage(), ar.cause());
                }
            });
        });
    }
}
