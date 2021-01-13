package com.xxx.web.service.impl;

import com.xxx.core.base.BaseAsyncService;
import com.xxx.core.util.IdWorker;
import com.xxx.web.dao.JooqDaoHolder;
import com.xxx.web.jooq.tables.daos.TOrderDao;
import com.xxx.web.jooq.tables.daos.TOrderItemDao;
import com.xxx.web.jooq.tables.pojos.TOrder;
import com.xxx.web.jooq.tables.pojos.TOrderItem;
import com.xxx.web.jooq.tables.records.TOrderRecord;
import com.xxx.web.service.OrderService;
import com.xxx.web.util.PojoUtil;
import io.vertx.core.AsyncResult;
import io.vertx.core.CompositeFuture;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import org.jooq.SortField;
import org.jooq.UpdateQuery;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author Ian
 * @date 2021/1/11 9:55
 */
public class OrderServiceImpl extends BaseAsyncService implements OrderService {

    private TOrderDao orderDao = JooqDaoHolder.getDaoInstance(TOrderDao.class);

    private TOrderItemDao orderItemDao = JooqDaoHolder.getDaoInstance(TOrderItemDao.class);

    @Override
    public void findOrderAndItemById(Long orderId, Handler<AsyncResult<JsonObject>> resultHandler) {
        Future<TOrder> future1 = orderDao.findOneById(orderId);
        Future<List<TOrderItem>> future2 = orderItemDao.findManyByOrderId(Collections.singleton(orderId));
        CompositeFuture.all(future1, future2).onSuccess(ar -> {
            if (ar.succeeded()) {
                TOrder tOrder = ar.resultAt(0);
                if (null == tOrder) {
                    resultHandler.handle(Future.succeededFuture(null));
                } else {
                    JsonObject result = tOrder.toJson();
                    List<TOrderItem> tOrderItemList = ar.resultAt(1);
                    result.put("children", PojoUtil.convertToArray(tOrderItemList));
                    resultHandler.handle(Future.succeededFuture(result));
                }
            } else {
                handleException(ar.cause(), resultHandler);
            }
        });
    }

    @Override
    public void listOrderPage(Integer current, Handler<AsyncResult<JsonObject>> resultHandler) {
        SortField<?> sortField = com.xxx.web.jooq.tables.TOrder.T_ORDER.CREATE_TIME.desc();
        JsonObject page = new JsonObject();
        page.put("current", current);
        page.put("size", 10);
        int offset = (current - 1) * 10;
        orderDao.queryExecutor().query(dslContext ->
                dslContext.selectCount().from(com.xxx.web.jooq.tables.TOrder.T_ORDER)
        ).compose(queryResult -> {
            page.put("total", queryResult.get(0, int.class));
            return orderDao.findManyByCondition(null, 10, offset, sortField);
        }).onComplete(ar -> {
            if (ar.succeeded()) {
                page.put("rows", PojoUtil.convertToArray(ar.result()));
                resultHandler.handle(Future.succeededFuture(page));
            } else {
                handleException(ar.cause(), resultHandler);
            }
        });
    }

    @Override
    public void deleteOrder(Long orderId, Handler<AsyncResult<Void>> resultHandler) {
        //无事务
//        orderDao.deleteById(orderId).compose(integer ->
//                orderItemDao.deleteByCondition(com.xxx.web.jooq.tables.TOrderItem.T_ORDER_ITEM.ORDER_ID.eq(orderId))
//        ).onComplete(ar -> {
//            if (ar.succeeded()) {
//                resultHandler.handle(Future.succeededFuture());
//            } else {
//                handleException(ar.cause(), resultHandler);
//            }
//        });
        //有事务
        orderDao.queryExecutor().executeAny(dslContext -> {
                    dslContext.transaction(c -> {
                        c.dsl().deleteFrom(com.xxx.web.jooq.tables.TOrder.T_ORDER)
                                .where(com.xxx.web.jooq.tables.TOrder.T_ORDER.ID.eq(orderId)).execute();
                        c.dsl().deleteFrom(com.xxx.web.jooq.tables.TOrderItem.T_ORDER_ITEM)
                                .where(com.xxx.web.jooq.tables.TOrderItem.T_ORDER_ITEM.ORDER_ID.eq(orderId)).execute();
                    });
                    return 0;
                }
        ).onComplete(ar -> {
            if (ar.succeeded()) {
                resultHandler.handle(Future.succeededFuture());
            } else {
                handleException(ar.cause(), resultHandler);
            }
        });
    }

    @Override
    public void saveOrder(JsonObject json, Handler<AsyncResult<JsonObject>> resultHandler) {
        TOrder tOrder = new TOrder(json);
        Future<Integer> future;
        if (json.containsKey("id") && null != json.getValue("id")) {
            future = orderDao.queryExecutor().executeAny(dslContext -> {
                        UpdateQuery<TOrderRecord> updateQuery = dslContext.updateQuery(com.xxx.web.jooq.tables.TOrder.T_ORDER);
                        Map<String, Object> map = new HashMap<>();
                        if (null != tOrder.getName()) {
                            map.put("name", tOrder.getName());
                        }
                        if (null != tOrder.getUserName()) {
                            map.put("user_name", tOrder.getUserName());
                        }
                        updateQuery.addValues(map);
                        updateQuery.addConditions(com.xxx.web.jooq.tables.TOrder.T_ORDER.ID.eq(tOrder.getId()));
                        return updateQuery.execute();
                    }
            );
            //修改所有字段
//            future = orderDao.update(tOrder);
        } else {
            tOrder.setId(IdWorker.getId()).setCreateTime(LocalDateTime.now());
            future = orderDao.insert(tOrder);
        }
        future.onComplete(ar -> {
            if (ar.succeeded()) {
                resultHandler.handle(Future.succeededFuture(tOrder.toJson()));
            } else {
                handleException(ar.cause(), resultHandler);
            }
        });
    }
}
