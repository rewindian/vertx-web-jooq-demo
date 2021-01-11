package com.xxx.web.rest;

import com.xxx.core.annotaions.RouteHandler;
import com.xxx.core.annotaions.RouteMapping;
import com.xxx.core.annotaions.RouteMethod;
import com.xxx.core.base.BaseRestApi;
import com.xxx.core.model.JsonResult;
import com.xxx.core.util.AsyncServiceUtil;
import com.xxx.web.service.OrderService;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;
import org.apache.commons.lang3.StringUtils;

/**
 * @author Ian
 * @date 2021/1/11 9:19
 */
@RouteHandler(value = "orderApi")
public class OrderApi extends BaseRestApi {

    private OrderService orderService = AsyncServiceUtil.getAsyncServiceInstance(OrderService.class);

    @RouteMapping(value = "/findOrderById/:orderId", method = RouteMethod.GET)
    public Handler<RoutingContext> findOrderById() {
        return ctx -> {
            String orderId = ctx.pathParam("orderId");
            if (StringUtils.isBlank(orderId)) {
                sendError(400, ctx);
            } else {
                orderService.findOrderAndItemById(Long.valueOf(orderId), ar -> {
                    if (ar.succeeded()) {
                        JsonObject product = ar.result();
                        fireJsonResponse(ctx, new JsonResult(product));
                    } else {
                        fireErrorJsonResponse(ctx, ar.cause().getMessage());
                    }
                });
            }
        };
    }

}
