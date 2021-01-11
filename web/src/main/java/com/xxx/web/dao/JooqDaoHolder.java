package com.xxx.web.dao;

import com.xxx.core.util.ReflectionUtil;
import com.xxx.core.util.VertxUtil;
import com.xxx.web.jooq.tables.daos.TOrderDao;
import io.vertx.core.Vertx;
import io.vertx.serviceproxy.ServiceException;
import org.jooq.Configuration;
import org.reflections.Reflections;
import io.github.jklingsporn.vertx.jooq.shared.internal.AbstractVertxDAO;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * @author Ian
 * @date 2021/1/8 16:54
 */
public class JooqDaoHolder {

    private static Map<Class<? extends AbstractVertxDAO>, Object> daoInstanceMap = new HashMap<>();

    public static void init(String daoLocations) {
        Reflections reflections = ReflectionUtil.getReflections(daoLocations);
        Set<Class<? extends AbstractVertxDAO>> daoSet = reflections.getSubTypesOf(AbstractVertxDAO.class);
        daoSet.forEach(aClass -> {
            try {
                Constructor c1 = aClass.getDeclaredConstructor(new Class[]{Configuration.class, Vertx.class});
                c1.setAccessible(true);
                daoInstanceMap.put(aClass, c1.newInstance(new Object[]{DaoConfigurationHolder.getDaoConfiguration(), VertxUtil.getVertxInstance()}));
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }

        });
    }

    public static Map<Class<? extends AbstractVertxDAO>, Object> getDaoInstanceMap() {
        return daoInstanceMap;
    }

    public static <T extends AbstractVertxDAO> T getDaoInstance(Class<T> tClass) {
        Object o = daoInstanceMap.get(tClass);
        if (null != o) {
            return (T) o;
        } else {
            throw new ServiceException(1, "获取" + tClass.getName() + "实例失败");
        }
    }
}
