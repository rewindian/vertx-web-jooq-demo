/*
 * This file is generated by jOOQ.
 */
package com.xxx.web.jooq.tables;


import com.xxx.web.jooq.Demo;
import com.xxx.web.jooq.Indexes;
import com.xxx.web.jooq.Keys;
import com.xxx.web.jooq.tables.records.TOrderItemRecord;
import org.jooq.*;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;


/**
 * 订单商品表
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class TOrderItem extends TableImpl<TOrderItemRecord> {

    private static final long serialVersionUID = -1105075163;

    /**
     * The reference instance of <code>demo.t_order_item</code>
     */
    public static final TOrderItem T_ORDER_ITEM = new TOrderItem();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<TOrderItemRecord> getRecordType() {
        return TOrderItemRecord.class;
    }

    /**
     * The column <code>demo.t_order_item.id</code>. 主键
     */
    public final TableField<TOrderItemRecord, Long> ID = createField(DSL.name("id"), org.jooq.impl.SQLDataType.BIGINT.nullable(false), this, "主键");

    /**
     * The column <code>demo.t_order_item.order_id</code>. 订单ID
     */
    public final TableField<TOrderItemRecord, Long> ORDER_ID = createField(DSL.name("order_id"), org.jooq.impl.SQLDataType.BIGINT, this, "订单ID");

    /**
     * The column <code>demo.t_order_item.name</code>. 名称
     */
    public final TableField<TOrderItemRecord, String> NAME = createField(DSL.name("name"), org.jooq.impl.SQLDataType.VARCHAR(20), this, "名称");

    /**
     * The column <code>demo.t_order_item.order_create_time</code>. 订单创建时间
     */
    public final TableField<TOrderItemRecord, LocalDateTime> ORDER_CREATE_TIME = createField(DSL.name("order_create_time"), org.jooq.impl.SQLDataType.LOCALDATETIME, this, "订单创建时间");

    /**
     * Create a <code>demo.t_order_item</code> table reference
     */
    public TOrderItem() {
        this(DSL.name("t_order_item"), null);
    }

    /**
     * Create an aliased <code>demo.t_order_item</code> table reference
     */
    public TOrderItem(String alias) {
        this(DSL.name(alias), T_ORDER_ITEM);
    }

    /**
     * Create an aliased <code>demo.t_order_item</code> table reference
     */
    public TOrderItem(Name alias) {
        this(alias, T_ORDER_ITEM);
    }

    private TOrderItem(Name alias, Table<TOrderItemRecord> aliased) {
        this(alias, aliased, null);
    }

    private TOrderItem(Name alias, Table<TOrderItemRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment("订单商品表"), TableOptions.table());
    }

    public <O extends Record> TOrderItem(Table<O> child, ForeignKey<O, TOrderItemRecord> key) {
        super(child, key, T_ORDER_ITEM);
    }

    @Override
    public Schema getSchema() {
        return Demo.DEMO;
    }

    @Override
    public List<Index> getIndexes() {
        return Arrays.<Index>asList(Indexes.T_ORDER_ITEM_ORDER_ID_INDEX);
    }

    @Override
    public UniqueKey<TOrderItemRecord> getPrimaryKey() {
        return Keys.KEY_T_ORDER_ITEM_PRIMARY;
    }

    @Override
    public List<UniqueKey<TOrderItemRecord>> getKeys() {
        return Arrays.<UniqueKey<TOrderItemRecord>>asList(Keys.KEY_T_ORDER_ITEM_PRIMARY);
    }

    @Override
    public TOrderItem as(String alias) {
        return new TOrderItem(DSL.name(alias), this);
    }

    @Override
    public TOrderItem as(Name alias) {
        return new TOrderItem(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public TOrderItem rename(String name) {
        return new TOrderItem(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public TOrderItem rename(Name name) {
        return new TOrderItem(name, null);
    }

    // -------------------------------------------------------------------------
    // Row4 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row4<Long, Long, String, LocalDateTime> fieldsRow() {
        return (Row4) super.fieldsRow();
    }
}