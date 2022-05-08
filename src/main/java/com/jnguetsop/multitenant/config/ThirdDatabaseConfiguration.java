package com.jnguetsop.multitenant.config;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
@ConfigurationProperties(prefix = "third.datasource")
public class ThirdDatabaseConfiguration {

    private String url;
    private String username;
    private String password;
}
