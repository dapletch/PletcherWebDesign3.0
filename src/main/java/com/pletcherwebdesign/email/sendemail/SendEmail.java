package com.pletcherwebdesign.email.sendemail;

import com.pletcherwebdesign.email.beans.MessageBody;
import com.pletcherwebdesign.email.beans.SmtpProperties;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

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
        logger.info("SmtpProperties for Send Email: " + smtpProperties.toString());
    }

    public void sendEmailNoAttachment(MessageBody messageBody) {
        Properties props = new Properties();
        props.put("mail.smtp.starttls.enable", smtpProperties.getStartTlsEnable());
        props.put("mail.smtp.host", smtpProperties.getHost());
        props.put("mail.smtp.user", smtpProperties.getUsername());
        props.put("mail.smtp.password", smtpProperties.getPassword());
        props.put("mail.smtp.port", smtpProperties.getPort());
        props.put("mail.smtp.auth", smtpProperties.getAuthentication());
        props.put("mail.smtp.debug", smtpProperties.getMailDebug());

        logger.info("Properties: " + props.toString());
        logger.info("SmtpProperties: " + smtpProperties.toString());

        Session session = Session.getInstance(props);

        try {
            // Create a default MimeMessage object
            Message message = new MimeMessage(session);
            // Set From: header field of the header
            message.setFrom(new InternetAddress(smtpProperties.getUsername()));
            // Set To: header field of the header
            message.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(messageBody.getRecipient()));
            // Set Subject: header field
            message.setSubject(messageBody.getSubject());
            // Now set the actual message
            message.setText(messageBody.getMessage());
            // Send message
            Transport transport = session.getTransport("smtp");
            transport.connect(smtpProperties.getHost(), smtpProperties.getUsername(), smtpProperties.getPassword());
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
            logger.info("The message has been sent successfully");
        } catch (MessagingException e) {
            logger.error("There was an error sending the email to admin: \n" + e);
        }
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
