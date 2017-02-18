package com.pletcherwebdesign.actions;

import com.opensymphony.xwork2.ActionSupport;
import com.pletcherwebdesign.beans.interfaces.FormRequirements;
import com.pletcherwebdesign.beans.Ticket;
import com.pletcherwebdesign.dao.TicketDao;
import com.pletcherwebdesign.jdbcproperties.JdbcConfiguration;
import com.pletcherwebdesign.utils.FormSubmissionUtils;
import org.apache.struts2.interceptor.SessionAware;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.dao.DataAccessException;

import java.util.Map;


/**
 * Created by Seth on 2/9/2017.
 */
public class SubmitTicket extends ActionSupport implements FormRequirements, SessionAware {

    private Ticket ticket = new Ticket();
    private Map<String, Object> sessionMap;
    private String username;
    private String errorMessage;
    private String userTickets;
    private FormSubmissionUtils formSubmissionUtils = new FormSubmissionUtils();

    private Logger logger = LoggerFactory.getLogger(SubmitTicket.class);

    public String execute() {

        ApplicationContext context = new AnnotationConfigApplicationContext(JdbcConfiguration.class);
        TicketDao ticketDao = context.getBean(TicketDao.class);
        try {
            if (!isTicketInputValid(ticket)) {
                return ERROR;
            }
            ticketDao.insertTicketIntoDb(ticket);
            setUserTickets(formSubmissionUtils.getTicketListForClient(ticket.getUsername()));
            formSubmissionUtils.sendNotificationEmail("ticket", emailMessage());
        } catch (DataAccessException e) {
            logger.error("The ticket submission was invalid: " + ticket.toString());
            setErrorMessage("<p>There was a problem submitting your ticket. Please try again.</p>");
            return ERROR;
        }
        return SUCCESS;
    }

    private Boolean isUserLoggedInSession(String username) {
        if (sessionMap.containsValue(username)) {
            setUsername(username);
            return true;
        } else {
            return false;
        }
    }

    private Boolean isTicketInputValid(Ticket ticket) {
        if ((ticket.getUsername().equals("") || ticket.getUsername() == null) || !isUserLoggedInSession(ticket.getUsername())) {
            setErrorMessage("<p>Your session has timed out. Please back login and <a href=\"../index.jsp\">try again</a>.</p>");
            return false;
        }
        if ((ticket.getSubject().equals("") || ticket.getSubject() == null)
                || (ticket.getProjectOrder().equals("") || ticket.getProjectOrder() == null)
                || (ticket.getPriorityLevel().equals("") || ticket.getPriorityLevel() == null)
                || (ticket.getDeadline() == null)) {
            setErrorMessage("<p>Please make sure all the inputs are filled in and try again</p><br>" + formError());
            return false;
        }
        return true;
    }

    public String formError() {
        return "<table class=\"table table-borderless\">\n" +
                "    <tr>\n" +
                "        <td align=\"left\" colspan=\"2\"><b>Ticket Submission/b></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "        <td><b>Username:</b></td>\n" +
                "        <td>" + ticket.getUsername() + "</td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "        <td><b>Subject:</b></td>\n" +
                "        <td>" + ticket.getSubject() + "</td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "        <td><b>Description:</b></td>\n" +
                "        <td>" + ticket.getProjectOrder() + "</td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "        <td><b>Priority Level:</b></td>\n" +
                "        <td>" + ticket.getPriorityLevel() + "</td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "        <td><b>Progress:</b></td>\n" +
                "        <td>" + ticket.getProgress() + "</td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "        <td><b>Developer Comment:</b></td>\n" +
                "        <td>" + ticket.getDevComment() + "</td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "        <td><b>Developer Comment:</b></td>\n" +
                "        <td>" + ticket.getDeadline() + "</td>\n" +
                "    </tr>\n" +
                "</table>";
    }

    public String emailMessage() {
        return "The following user has just submitted a ticket through Pletcher Web Design: \n" +
                "Username: " + ticket.getUsername() + "\n" +
                "Subject: " + ticket.getSubject() + "\n" +
                "Description: " + ticket.getProjectOrder() + "\n" +
                "Priority Level: " + ticket.getPriorityLevel() + "\n" +
                "Deadline: " + ticket.getDeadline();
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserTickets() {
        return userTickets;
    }

    private void setUserTickets(String userTickets) {
        this.userTickets = userTickets;
    }

    public void setSession(Map<String, Object> sessionMap) {
        this.sessionMap = sessionMap;
    }
}
