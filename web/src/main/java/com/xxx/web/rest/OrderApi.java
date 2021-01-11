package com.xxx.web.rest;

import com.xxx.core.annotaions.RouteHandler;
import com.xxx.core.annotaions.RouteMapping;
import com.xxx.core.annotaions.RouteMethod;
import com.xxx.core.base.BaseRestApi;
import com.xxx.core.model.JsonResult;
import com.xxx.core.util.AsyncServiceUtil;
import com.xxx.core.util.ParamUtil;
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

    @RouteMapping(value = "/listOrderPage/:current", method = RouteMethod.GET)
    public Handler<RoutingContext> listOrderPage() {
        return ctx -> {
            String current = ctx.pathParam("current");
            if (StringUtils.isBlank(current)) {
                sendError(400, ctx);
            } else {
                orderService.listOrderPage(Integer.valueOf(current), ar -> {
                    if (ar.succeeded()) {
                        fireJsonResponse(ctx, new JsonResult(ar.result()));
                    } else {
                        fireErrorJsonResponse(ctx, ar.cause().getMessage());
                    }
                });
            }
        };
    }

    @RouteMapping(value = "/deleteById/:orderId", method = RouteMethod.DELETE)
    public Handler<RoutingContext> deleteById() {
        return ctx -> {
            String orderId = ctx.pathParam("orderId");
            if (StringUtils.isBlank(orderId)) {
                sendError(400, ctx);
            } else {
                orderService.deleteOrder(Long.valueOf(orderId), ar -> {
                    if (ar.succeeded()) {
                        fireJsonResponse(ctx, new JsonResult(ar.result()));
                    } else {
                        fireErrorJsonResponse(ctx, ar.cause().getMessage());
                    }
                });
            }
        };
    }

    @RouteMapping(value = "/saveOrder", method = RouteMethod.POST)
    public Handler<RoutingContext> saveOrder() {
        return ctx -> {
            JsonObject params = ParamUtil.getRequestParams(ctx);
            orderService.saveOrder(params, ar -> {
                if (ar.succeeded()) {
                    fireJsonResponse(ctx, new JsonResult(ar.result()));
                } else {
                    fireErrorJsonResponse(ctx, ar.cause().getMessage());
                }
            });
        };
    }
}
