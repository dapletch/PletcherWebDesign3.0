package com.pletcherwebdesign.utils;

import com.pletcherwebdesign.beans.Ticket;
import com.pletcherwebdesign.beans.interfaces.FormSubmission;
import com.pletcherwebdesign.dao.TicketDao;
import com.pletcherwebdesign.email.beans.MessageBody;
import com.pletcherwebdesign.email.dao.EmailFormDao;
import com.pletcherwebdesign.email.sendemail.EmailConfig;
import com.pletcherwebdesign.email.sendemail.SendEmail;
import com.pletcherwebdesign.jdbcproperties.JdbcConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

/**
 * Created by Seth on 2/17/2017.
 */
public class FormSubmissionUtils implements FormSubmission {

    public void sendNotificationEmail(String formSubmission, String emailMessage) {
        ApplicationContext context = new AnnotationConfigApplicationContext(EmailConfig.class);
        SendEmail sendEmail = context.getBean(SendEmail.class);
        sendEmail.sendEmailNoAttachment(getMessageBodyForm(formSubmission, emailMessage));
    }

    public MessageBody getMessageBodyForm(String formSubmission, String emailMessage) {
        ApplicationContext context = new AnnotationConfigApplicationContext(JdbcConfiguration.class);
        EmailFormDao emailFormDao = context.getBean(EmailFormDao.class);
        MessageBody messageBody = emailFormDao.getHtmlFormInfoForEmail(formSubmission);
        return PletcherWebDesignUtils.setMessageBodyNoAttachment(messageBody, emailMessage);
    }

    public String getTicketsForAdmin(String adminUsername) {
        ApplicationContext context = new AnnotationConfigApplicationContext(JdbcConfiguration.class);
        TicketDao ticketDao = context.getBean(TicketDao.class);
        List<Ticket> adminTicketList = ticketDao.selectTicketsForAdmin();
        return TicketUtils.getTicketsForAdmin(adminTicketList, adminUsername);
    }

    public String getTicketListForClient(String clientUsername) {
        ApplicationContext context = new AnnotationConfigApplicationContext(JdbcConfiguration.class);
        TicketDao ticketDao = context.getBean(TicketDao.class);
        List<Ticket> clientTicketList = ticketDao.selectTicketsForClient(clientUsername);
        return TicketUtils.getTicketsClientToSee(clientTicketList);
    }
}
