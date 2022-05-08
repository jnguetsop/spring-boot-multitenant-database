package com.jnguetsop.multitenant.datasource;

import com.jnguetsop.multitenant.config.FirstDatabaseConfiguration;
import com.jnguetsop.multitenant.config.SecondDatabaseConfiguration;
import com.jnguetsop.multitenant.config.ThirdDatabaseConfiguration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.HashMap;

@Component
public class DataSourceRouting extends AbstractRoutingDataSource {

    private final FirstDatabaseConfiguration firstDatabaseConfiguration;
    private final SecondDatabaseConfiguration secondDatabaseConfiguration;

    private final ThirdDatabaseConfiguration thirdDatabaseConfiguration;
    private final DataSourceContextHolder dataSourceContextHolder;

    public DataSourceRouting(FirstDatabaseConfiguration firstDatabaseConfiguration,
                             SecondDatabaseConfiguration secondDatabaseConfiguration,
                             ThirdDatabaseConfiguration thirdDatabaseConfiguration,
                             DataSourceContextHolder dataSourceContextHolder) {

        this.firstDatabaseConfiguration = firstDatabaseConfiguration;
        this.secondDatabaseConfiguration = secondDatabaseConfiguration;
        this.thirdDatabaseConfiguration = thirdDatabaseConfiguration;
        this.dataSourceContextHolder = dataSourceContextHolder;

        var dataSourceMap = new HashMap<>();
        dataSourceMap.put(DataSourceEnum.FIRST_DATABASE, firstDataSource());
        dataSourceMap.put(DataSourceEnum.SECOND_DATABASE, secondDataSource());
        dataSourceMap.put(DataSourceEnum.THIRD_DATABASE, thirdDataSource());
        this.setTargetDataSources(dataSourceMap);
        this.setDefaultTargetDataSource(firstDataSource());
    }

    @Override
    protected Object determineCurrentLookupKey() {
        return dataSourceContextHolder.getBranchContext();
    }

    private DataSource firstDataSource() {
        var dataSource = new DriverManagerDataSource();
        dataSource.setUrl(firstDatabaseConfiguration.getUrl());
        dataSource.setUsername(firstDatabaseConfiguration.getUsername());
        dataSource.setPassword(firstDatabaseConfiguration.getPassword());

        return dataSource;
    }

    private DataSource secondDataSource() {
        var dataSource = new DriverManagerDataSource();
        dataSource.setUrl(secondDatabaseConfiguration.getUrl());
        dataSource.setUsername(secondDatabaseConfiguration.getUsername());
        dataSource.setPassword(secondDatabaseConfiguration.getPassword());

        return dataSource;
    }

    private DataSource thirdDataSource() {
        var dataSource = new DriverManagerDataSource();
        dataSource.setUrl(thirdDatabaseConfiguration.getUrl());
        dataSource.setUsername(thirdDatabaseConfiguration.getUsername());
        dataSource.setPassword(thirdDatabaseConfiguration.getPassword());

        return dataSource;
    }
}
