package com.pletcherwebdesign.jdbcproperties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

/**
 * Created by Seth on 2/4/2017.
 */
@Configuration
@ComponentScan(basePackages = "com.pletcherwebdesign.*")
@PropertySource("classpath:jdbcProperties.properties")
public class JdbcPropertiesConfig {

    private Environment env;

    @Autowired
    public JdbcPropertiesConfig(Environment env) {
        this.env = env;
    }

    @Bean
    public JdbcProperties getJdbcProperties() {
        return new JdbcProperties(env.getProperty("jdbc.username"), env.getProperty("jdbc.password")
                , env.getProperty("jdbc.url"), env.getProperty("jdbc.driver"));
    }
}
