package com.pletcherwebdesign.email.sendemail;

import com.pletcherwebdesign.email.beans.SmtpProperties;
import com.pletcherwebdesign.email.dao.EmailFormDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

/**
 * Created by Seth on 2/6/2017.
 */
@Configuration
@ComponentScan(basePackages = "com.pletcherwebdesign.*")
public class EmailConfig {

    private SmtpProperties smtpProperties;

    private Logger logger = LoggerFactory.getLogger(EmailConfig.class);

    @Autowired
    public EmailConfig(SmtpProperties smtpProperties) {
        this.smtpProperties = smtpProperties;
    }

    @Bean
    public JavaMailSender javaMailService() {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        logger.info("SmtpProperties: " + smtpProperties.toString());

        javaMailSender.setHost(smtpProperties.getHost());
        javaMailSender.setPort(Integer.parseInt(smtpProperties.getPort()));
        javaMailSender.setUsername(smtpProperties.getUsername());
        javaMailSender.setPassword(smtpProperties.getPassword());

        Properties javaMailProperties = new Properties();
        javaMailProperties.put("mail.smtp.auth", smtpProperties.getAuthentication());
        javaMailProperties.put("mail.smtp.starttls.enable", smtpProperties.getStartTlsEnable());
        javaMailProperties.put("mail.transport.protocol", smtpProperties.getTransportProtocol());
        javaMailProperties.put("mail.debug", smtpProperties.getMailDebug());
        javaMailSender.setJavaMailProperties(javaMailProperties);
        return javaMailSender;
    }
}
