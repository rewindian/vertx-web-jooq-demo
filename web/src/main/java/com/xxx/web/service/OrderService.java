package com.xxx.web.service;

import io.vertx.codegen.annotations.ProxyGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;

/**
 * @author Ian
 * @date 2021/1/11 9:25
 */
@ProxyGen
public interface OrderService {

    void findOrderAndItemById(Long orderId, Handler<AsyncResult<JsonObject>> resultHandler);
}
