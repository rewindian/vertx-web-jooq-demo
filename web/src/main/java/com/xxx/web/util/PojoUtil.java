package com.xxx.web.util;

import io.github.jklingsporn.vertx.jooq.shared.internal.VertxPojo;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Objects;


/**
 * @author Ian
 * @date 2021/1/11 11:01
 */
public final class PojoUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(PojoUtil.class);

    public static <T> JsonArray convertToArray(Collection<T> collection) {
        Objects.requireNonNull(collection, "collection must not be empty");
        JsonArray jsonArray = new JsonArray();
        collection.forEach(t -> {
            Method[] methods = t.getClass().getMethods();
            for (Method method : methods) {
                if (method.getName().equals("toJson")) {
                    try {
                        Object obj = method.invoke(t);
                        jsonArray.add(obj);
                        break;
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        LOGGER.error(e.getMessage(), e);
                    }

                }
            }
        });
        return jsonArray;
    }
}
