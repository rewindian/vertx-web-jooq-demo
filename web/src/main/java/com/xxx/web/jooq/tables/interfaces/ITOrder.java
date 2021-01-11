/*
 * This file is generated by jOOQ.
 */
package com.xxx.web.jooq.tables.interfaces;


import io.github.jklingsporn.vertx.jooq.shared.internal.VertxPojo;

import java.io.Serializable;
import java.time.LocalDateTime;

import static io.github.jklingsporn.vertx.jooq.shared.internal.VertxPojo.setOrThrow;

/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public interface ITOrder extends VertxPojo, Serializable {

    /**
     * Setter for <code>demo.t_order.id</code>. 主键
     */
    public ITOrder setId(Long value);

    /**
     * Getter for <code>demo.t_order.id</code>. 主键
     */
    public Long getId();

    /**
     * Setter for <code>demo.t_order.name</code>. 名称
     */
    public ITOrder setName(String value);

    /**
     * Getter for <code>demo.t_order.name</code>. 名称
     */
    public String getName();

    /**
     * Setter for <code>demo.t_order.create_time</code>. 创建时间
     */
    public ITOrder setCreateTime(LocalDateTime value);

    /**
     * Getter for <code>demo.t_order.create_time</code>. 创建时间
     */
    public LocalDateTime getCreateTime();

    /**
     * Setter for <code>demo.t_order.user_name</code>. 订单所属用户名
     */
    public ITOrder setUserName(String value);

    /**
     * Getter for <code>demo.t_order.user_name</code>. 订单所属用户名
     */
    public String getUserName();

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    /**
     * Load data from another generated Record/POJO implementing the common interface ITOrder
     */
    public void from(ITOrder from);

    /**
     * Copy data into another generated Record/POJO implementing the common interface ITOrder
     */
    public <E extends ITOrder> E into(E into);

    @Override
    public default ITOrder fromJson(io.vertx.core.json.JsonObject json) {
        setOrThrow(this::setId,json::getLong,"id","java.lang.Long");
        setOrThrow(this::setName,json::getString,"name","java.lang.String");
        setOrThrow(this::setCreateTime,key -> {String s = json.getString(key); return s==null?null: LocalDateTime.parse(s);},"create_time","java.time.LocalDateTime");
        setOrThrow(this::setUserName,json::getString,"user_name","java.lang.String");
        return this;
    }


    @Override
    public default io.vertx.core.json.JsonObject toJson() {
        io.vertx.core.json.JsonObject json = new io.vertx.core.json.JsonObject();
        json.put("id",getId());
        json.put("name",getName());
        json.put("create_time",getCreateTime()==null?null:getCreateTime().toString());
        json.put("user_name",getUserName());
        return json;
    }

}
