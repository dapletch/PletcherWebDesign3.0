package com.pletcherwebdesign.actions;

import com.opensymphony.xwork2.ActionSupport;
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
 * Created by Seth on 2/17/2017.
 */
public class TicketDelete extends ActionSupport implements SessionAware {

    private String username;
    private Integer ticketId;
    private String errorMessage;
    private String adminTickets;
    private Map<String, Object> sessionMap;
    private FormSubmissionUtils formSubmissionUtils = new FormSubmissionUtils();
    private Logger logger = LoggerFactory.getLogger(TicketDelete.class);

    public String execute() {

        ApplicationContext context = new AnnotationConfigApplicationContext(JdbcConfiguration.class);
        TicketDao ticketDao = context.getBean(TicketDao.class);
        try {
            if (sessionMap.containsValue(username)) {
                ticketDao.deleteTicketFromDb(ticketId);
                setUsername(username);
                setAdminTickets(formSubmissionUtils.getTicketsForAdmin(username));
            } else {
                setUsername(username);
                setErrorMessage("<p>The administrator session has timed out. Please log back in and try again.</p>");
            }
        } catch (DataAccessException e) {
            logger.error("There was an error deleting the record from the database: " + e);
            setErrorMessage("<p>There was an error with deleting the record. Please log back in and try again.</p>");
            return ERROR;
        }
        return SUCCESS;
    }

    public void setSession(Map<String, Object> sessionMap) {
        this.sessionMap = sessionMap;
    }

    public Map<String, Object> getSession() {
        return sessionMap;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getTicketId() {
        return ticketId;
    }

    public void setTicketId(Integer ticketId) {
        this.ticketId = ticketId;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getAdminTickets() {
        return adminTickets;
    }

    private void setAdminTickets(String adminTickets) {
        this.adminTickets = adminTickets;
    }
}
