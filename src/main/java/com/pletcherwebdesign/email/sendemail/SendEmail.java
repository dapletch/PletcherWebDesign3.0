package com.pletcherwebdesign.email.sendemail;

import com.pletcherwebdesign.email.beans.MessageBody;
import com.pletcherwebdesign.email.beans.SmtpProperties;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * Created by Seth on 2/7/2017.
 */
@Service
@Configuration
@ComponentScan(basePackages = "com.pletcherwebdesign.*")
public class SendEmail {

    private JavaMailSender mailSender;
    private SmtpProperties smtpProperties;

    private Logger logger = Logger.getLogger(SendEmail.class);

    @Autowired
    public SendEmail(JavaMailSender mailSender, SmtpProperties smtpProperties) {
        this.mailSender = mailSender;
        this.smtpProperties = smtpProperties;
    }

    public void sendEmailNoAttachment(MessageBody messageBody) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(messageBody.getRecipient());
        message.setSubject(messageBody.getSubject());
        message.setText(messageBody.getMessage());
        mailSender.send(message);
    }

    public void sendEmailWithAttachment(MessageBody messageBody) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom(smtpProperties.getUsername());
            helper.setTo(messageBody.getRecipient());
            helper.setSubject(messageBody.getSubject());
            helper.setText(messageBody.getMessage());
            // Adding the attachment
            FileSystemResource attachment = new FileSystemResource(messageBody.getAttachment());
            helper.addAttachment(attachment.getFilename(), attachment);
            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            logger.error("There was an error sending the email: ", e);
        }
    }
}
