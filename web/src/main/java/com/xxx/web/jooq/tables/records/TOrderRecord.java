/*
 * This file is generated by jOOQ.
 */
package com.xxx.web.jooq.tables.records;


import com.xxx.web.jooq.tables.TOrder;
import com.xxx.web.jooq.tables.interfaces.ITOrder;
import io.github.jklingsporn.vertx.jooq.shared.internal.VertxPojo;
import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record4;
import org.jooq.Row4;
import org.jooq.impl.UpdatableRecordImpl;

import java.time.LocalDateTime;

/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class TOrderRecord extends UpdatableRecordImpl<TOrderRecord> implements VertxPojo, Record4<Long, String, LocalDateTime, String>, ITOrder {

    private static final long serialVersionUID = 1695844467;

    /**
     * Setter for <code>demo.t_order.id</code>. 主键
     */
    @Override
    public TOrderRecord setId(Long value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>demo.t_order.id</code>. 主键
     */
    @Override
    public Long getId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>demo.t_order.name</code>. 名称
     */
    @Override
    public TOrderRecord setName(String value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>demo.t_order.name</code>. 名称
     */
    @Override
    public String getName() {
        return (String) get(1);
    }

    /**
     * Setter for <code>demo.t_order.create_time</code>. 创建时间
     */
    @Override
    public TOrderRecord setCreateTime(LocalDateTime value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>demo.t_order.create_time</code>. 创建时间
     */
    @Override
    public LocalDateTime getCreateTime() {
        return (LocalDateTime) get(2);
    }

    /**
     * Setter for <code>demo.t_order.user_name</code>. 订单所属用户名
     */
    @Override
    public TOrderRecord setUserName(String value) {
        set(3, value);
        return this;
    }

    /**
     * Getter for <code>demo.t_order.user_name</code>. 订单所属用户名
     */
    @Override
    public String getUserName() {
        return (String) get(3);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Long> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record4 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row4<Long, String, LocalDateTime, String> fieldsRow() {
        return (Row4) super.fieldsRow();
    }

    @Override
    public Row4<Long, String, LocalDateTime, String> valuesRow() {
        return (Row4) super.valuesRow();
    }

    @Override
    public Field<Long> field1() {
        return TOrder.T_ORDER.ID;
    }

    @Override
    public Field<String> field2() {
        return TOrder.T_ORDER.NAME;
    }

    @Override
    public Field<LocalDateTime> field3() {
        return TOrder.T_ORDER.CREATE_TIME;
    }

    @Override
    public Field<String> field4() {
        return TOrder.T_ORDER.USER_NAME;
    }

    @Override
    public Long component1() {
        return getId();
    }

    @Override
    public String component2() {
        return getName();
    }

    @Override
    public LocalDateTime component3() {
        return getCreateTime();
    }

    @Override
    public String component4() {
        return getUserName();
    }

    @Override
    public Long value1() {
        return getId();
    }

    @Override
    public String value2() {
        return getName();
    }

    @Override
    public LocalDateTime value3() {
        return getCreateTime();
    }

    @Override
    public String value4() {
        return getUserName();
    }

    @Override
    public TOrderRecord value1(Long value) {
        setId(value);
        return this;
    }

    @Override
    public TOrderRecord value2(String value) {
        setName(value);
        return this;
    }

    @Override
    public TOrderRecord value3(LocalDateTime value) {
        setCreateTime(value);
        return this;
    }

    @Override
    public TOrderRecord value4(String value) {
        setUserName(value);
        return this;
    }

    @Override
    public TOrderRecord values(Long value1, String value2, LocalDateTime value3, String value4) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        return this;
    }

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    @Override
    public void from(ITOrder from) {
        setId(from.getId());
        setName(from.getName());
        setCreateTime(from.getCreateTime());
        setUserName(from.getUserName());
    }

    @Override
    public <E extends ITOrder> E into(E into) {
        into.from(this);
        return into;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached TOrderRecord
     */
    public TOrderRecord() {
        super(TOrder.T_ORDER);
    }

    /**
     * Create a detached, initialised TOrderRecord
     */
    public TOrderRecord(Long id, String name, LocalDateTime createTime, String userName) {
        super(TOrder.T_ORDER);

        set(0, id);
        set(1, name);
        set(2, createTime);
        set(3, userName);
    }

    public TOrderRecord(io.vertx.core.json.JsonObject json) {
        this();
        fromJson(json);
    }
}
