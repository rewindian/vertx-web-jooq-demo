/*
 * This file is generated by jOOQ.
 */
package com.xxx.web.jooq;


import com.xxx.web.jooq.tables.TOrder;
import com.xxx.web.jooq.tables.TOrderItem;
import org.jooq.Catalog;
import org.jooq.Table;
import org.jooq.impl.SchemaImpl;

import java.util.Arrays;
import java.util.List;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Demo extends SchemaImpl {

    private static final long serialVersionUID = -1162982615;

    /**
     * The reference instance of <code>demo</code>
     */
    public static final Demo DEMO = new Demo();

    /**
     * The table <code>demo.t_order</code>.
     */
    public final TOrder T_ORDER = TOrder.T_ORDER;

    /**
     * 订单商品表
     */
    public final TOrderItem T_ORDER_ITEM = TOrderItem.T_ORDER_ITEM;

    /**
     * No further instances allowed
     */
    private Demo() {
        super("demo", null);
    }


    @Override
    public Catalog getCatalog() {
        return DefaultCatalog.DEFAULT_CATALOG;
    }

    @Override
    public final List<Table<?>> getTables() {
        return Arrays.<Table<?>>asList(
            TOrder.T_ORDER,
            TOrderItem.T_ORDER_ITEM);
    }
}