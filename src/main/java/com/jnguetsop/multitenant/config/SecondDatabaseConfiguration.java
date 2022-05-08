package com.jnguetsop.multitenant.config;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
@ConfigurationProperties(prefix = "second.datasource")
public class SecondDatabaseConfiguration {

    private String url;
    private String username;
    private String password;
}
