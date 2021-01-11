package com.xxx.web.service.impl;

import com.xxx.core.base.BaseAsyncService;
import com.xxx.web.dao.JooqDaoHolder;
import com.xxx.web.jooq.tables.daos.TOrderDao;
import com.xxx.web.jooq.tables.daos.TOrderItemDao;
import com.xxx.web.jooq.tables.pojos.TOrder;
import com.xxx.web.jooq.tables.pojos.TOrderItem;
import com.xxx.web.service.OrderService;
import com.xxx.web.util.PojoUtil;
import io.vertx.core.AsyncResult;
import io.vertx.core.CompositeFuture;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;

import java.util.Collections;
import java.util.List;

/**
 * @author Ian
 * @date 2021/1/11 9:55
 */
public class OrderServiceImpl extends BaseAsyncService implements OrderService {

    @Override
    public void findOrderAndItemById(Long orderId, Handler<AsyncResult<JsonObject>> resultHandler) {
        TOrderDao orderDao = JooqDaoHolder.getDaoInstance(TOrderDao.class);
        TOrderItemDao orderItemDao = JooqDaoHolder.getDaoInstance(TOrderItemDao.class);
        Future<TOrder> future1 = orderDao.findOneById(orderId);
        Future<List<TOrderItem>> future2 = orderItemDao.findManyByOrderId(Collections.singleton(orderId));
        CompositeFuture.all(future1, future2).onSuccess(ar -> {
            if (ar.succeeded()) {
                TOrder tOrder = ar.resultAt(0);
                JsonObject result = tOrder.toJson();
                List<TOrderItem> tOrderItemList = ar.resultAt(1);
                result.put("children", PojoUtil.convertToArray(tOrderItemList));
                resultHandler.handle(Future.succeededFuture(result));
            } else {
                handleException(ar.cause(), resultHandler);
            }
        });
    }
}
