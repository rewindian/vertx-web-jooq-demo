package com.xxx.web.dao;

import com.xxx.web.datasource.DataSourceHolder;
import org.jooq.Configuration;
import org.jooq.ConnectionProvider;
import org.jooq.SQLDialect;
import org.jooq.impl.DataSourceConnectionProvider;
import org.jooq.impl.DefaultConfiguration;

import java.util.Objects;

/**
 * @author Ian
 * @date 2021/1/8 17:02
 */
public class DaoConfigurationHolder {

    private static Configuration daoConfiguration;

    public static void init() {
        ConnectionProvider connectionProvider = new DataSourceConnectionProvider(DataSourceHolder.getDataSource());
        Configuration configuration = new DefaultConfiguration();
        configuration.set(SQLDialect.MYSQL);
        configuration.set(connectionProvider);
        daoConfiguration = configuration;
    }

    public static Configuration getDaoConfiguration() {
        Objects.requireNonNull(daoConfiguration, "daoConfiguration is not initialized");
        return daoConfiguration;
    }
}
