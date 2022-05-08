package com.jnguetsop.multitenant.config;

import com.jnguetsop.multitenant.datasource.DataSourceRouting;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;


@Component
@EnableJpaRepositories(
        basePackages = "com.jnguetsop.multitenant.repository",
        transactionManagerRef = "transactionManager",
        entityManagerFactoryRef = "entityManager"
)
@EnableTransactionManagement
@RequiredArgsConstructor
@DependsOn("dataSourceRouting")
public class DataSourceConfiguration {

    private final DataSourceRouting dataSourceRouting;

    @Bean
    @Primary
    public DataSource dataSource() {
        return dataSourceRouting;
    }

    @Bean(name = "entityManager")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder, DataSource dataSource) {
        return builder.dataSource(dataSource).packages("com.jnguetsop.multitenant.domain").build();
    }

    @Bean(name = "transactionManager")
    public JpaTransactionManager transactionManager(@Autowired @Qualifier("entityManager") LocalContainerEntityManagerFactoryBean entityManagerFactoryBean) {
        return new JpaTransactionManager(entityManagerFactoryBean.getObject());
    }
}


