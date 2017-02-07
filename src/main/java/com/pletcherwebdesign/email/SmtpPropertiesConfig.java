package com.pletcherwebdesign.email;

import com.pletcherwebdesign.email.beans.SmtpProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

/**
 * Created by Seth on 2/6/2017.
 */
@Configuration
@ComponentScan(basePackages = "com.pletcherwebdesign.*")
@PropertySource("classpath:smtp.properties")
public class SmtpPropertiesConfig {

    private Environment env;

    @Autowired
    public SmtpPropertiesConfig(Environment env) {
        this.env = env;
    }

    @Bean
    public SmtpProperties getStmpProperties() {
        return new SmtpProperties(env.getProperty("smtp.host"), env.getProperty("smtp.port")
                , env.getProperty("smtp.username"), env.getProperty("smtp.password")
                , env.getProperty("smtp.auth"), env.getProperty("smtp.starttls.enable")
                , env.getProperty("smtp.transport.protocol"), env.getProperty("smtp.debug"));
    }
}
