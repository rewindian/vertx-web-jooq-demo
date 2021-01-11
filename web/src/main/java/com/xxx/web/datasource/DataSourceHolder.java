package com.xxx.web.datasource;

import com.zaxxer.hikari.HikariDataSource;
import io.vertx.core.json.JsonObject;
import org.apache.commons.lang3.StringUtils;


/**
 * @author Ian
 * @date 2021/1/8 16:35
 */
public class DataSourceHolder {
    private static HikariDataSource dataSource;

    public static void init(JsonObject dataSourceConfig) {
        //校验配置
        validateConfig(dataSourceConfig);
        HikariDataSource hikariDataSource = new HikariDataSource();
        hikariDataSource.setDriverClassName(dataSourceConfig.getString("driverClassName"));
        hikariDataSource.setJdbcUrl(dataSourceConfig.getString("jdbcUrl"));
        hikariDataSource.setUsername(dataSourceConfig.getString("username"));
        hikariDataSource.setPassword(dataSourceConfig.getString("password"));
        if (null != dataSourceConfig.getValue("maximumPoolSize")) {
            hikariDataSource.setMaximumPoolSize(dataSourceConfig.getInteger("maximumPoolSize"));
        }
        dataSource = hikariDataSource;
    }

    public static HikariDataSource getDataSource() {
        return dataSource;
    }

    private static void validateConfig(JsonObject dataSourceConfig) {
        String driverClassName = dataSourceConfig.getString("driverClassName");
        if (StringUtils.isBlank(driverClassName)) {
            throw new NullPointerException("dataSource.driverClassName is empty");
        }
        String jdbcUrl = dataSourceConfig.getString("jdbcUrl");
        if (StringUtils.isBlank(jdbcUrl)) {
            throw new NullPointerException("dataSource.jdbcUrl is empty");
        }
        String username = dataSourceConfig.getString("username");
        if (StringUtils.isBlank(username)) {
            throw new NullPointerException("dataSource.username is empty");
        }
        String password = dataSourceConfig.getString("password");
        if (StringUtils.isBlank(password)) {
            throw new NullPointerException("dataSource.password is empty");
        }
    }
}
