package com.pletcherwebdesign.test;

import com.pletcherwebdesign.email.beans.MessageBody;
import com.pletcherwebdesign.email.dao.EmailFormDao;
import com.pletcherwebdesign.email.sendemail.EmailConfig;
import com.pletcherwebdesign.email.sendemail.SendEmail;
import com.pletcherwebdesign.jdbcproperties.JdbcConfiguration;
import com.pletcherwebdesign.utils.PletcherWebDesignUtils;
import org.apache.struts2.StrutsTestCase;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.dao.DataAccessException;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Created by Seth on 2/5/2017.
 */
public class PletcherWebDesignTestCases extends StrutsTestCase {

    private MessageBody messageBody = new MessageBody();

    @Test
    public void testEmailValidation() throws Exception {
        String email1 = "seth.pletcher@gmail.com";
        String email2 = "Megan.Barr@state.vt.us";

        System.out.println("The result of test 1 is: " + PletcherWebDesignUtils.isEmailAddressValid(email1));
        System.out.println("The result of test 2 is: " + PletcherWebDesignUtils.isEmailAddressValid(email2));
    }

    @Test
    public void testEmailForm() throws Exception {
        // Sender's email ID needs to be mentioned
        String from = "pletcherwebdesign@yahoo.com";
        String pass = "Crazie4you!";
        // Recipient's email ID needs to be mentioned.
        String to = "seth.pletcher@gmail.com";
        String host = "smtp.mail.yahoo.com";

        // Get system properties
        Properties properties = System.getProperties();
        // Setup mail server
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.user", from);
        properties.put("mail.smtp.password", pass);
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");

        // Get the default Session object.
        Session session = Session.getDefaultInstance(properties);

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(to));

            // Set Subject: header field
            message.setSubject("This is the Subject Line!");

            // Now set the actual message
            message.setText("This is actual message");

            // Send message
            Transport transport = session.getTransport("smtp");
            transport.connect(host, from, pass);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
        /*
        String formToBeSearched = "infosession";
        ApplicationContext context = new AnnotationConfigApplicationContext(JdbcConfiguration.class);
        EmailFormDao emailFormDao = context.getBean(EmailFormDao.class);
        try {
            messageBody = emailFormDao.getHtmlFormInfoForEmail(formToBeSearched);

            System.out.println("The message body object: " + messageBody.toString());
        } catch (DataAccessException e) {
            System.out.println(e);
        }
        */
    }

    @Test
    public void sendEmailUsingSessionValidation() throws Exception {
        // MessageBody properties
        MessageBody messageBody = new MessageBody();
        messageBody.setRecipient("seth.pletcher@gmail.com");
        messageBody.setSubject("This is a test");
        messageBody.setMessage("This is a test to see if the session validation works.");

        ApplicationContext context = new AnnotationConfigApplicationContext(EmailConfig.class);
        SendEmail sendEmail = context.getBean(SendEmail.class);
        sendEmail.sendEmailNoAttachment(messageBody);

        System.out.println("Email was submitted successfully...");
    }

    /*
    @Test
    public void sendEmailViaYahoo() throws Exception {
        // Sender's email ID needs to be mentioned
        String from = "test123@yahoo.com";
        String pass = "test123";
        // Recipient's email ID needs to be mentioned.
        String to = "riponalwasim@yahoo.com";
        String host = "smtp.mail.yahoo.com";

        // Get system properties
        Properties properties = System.getProperties();
        // Setup mail server
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.user", from);
        properties.put("mail.smtp.password", pass);
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");

        // Get the default Session object.
        Session session = Session.getDefaultInstance(properties);

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(to));

            // Set Subject: header field
            message.setSubject("This is the Subject Line!");

            // Now set the actual message
            message.setText("This is actual message");

            // Send message
            Transport transport = session.getTransport("smtp");
            transport.connect(host, from, pass);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
    */
}