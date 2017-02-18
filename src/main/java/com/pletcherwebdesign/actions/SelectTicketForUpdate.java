package com.pletcherwebdesign.actions;

import com.opensymphony.xwork2.ActionSupport;
import com.pletcherwebdesign.beans.Ticket;
import com.pletcherwebdesign.dao.TicketDao;
import com.pletcherwebdesign.jdbcproperties.JdbcConfiguration;
import org.apache.struts2.interceptor.SessionAware;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.dao.DataAccessException;

import java.util.Map;

/**
 * Created by Seth on 2/12/2017.
 */
public class SelectTicketForUpdate extends ActionSupport implements SessionAware {

    private String username;
    private Integer ticketId;
    private String errorMessage;
    private Ticket ticket = new Ticket();
    private Map<String, Object> sessionMap;

    private Logger logger = LoggerFactory.getLogger(SelectTicketForUpdate.class);

    public String execute() {

        ApplicationContext context = new AnnotationConfigApplicationContext(JdbcConfiguration.class);
        TicketDao ticketDao = context.getBean(TicketDao.class);

        try {
            // Check to see whether the administrative session is valid or not, if so proceed
            if (sessionMap.containsValue(username)) {
                setTicket(ticketDao.selectTicketById(ticketId));
                setUsername(username);
            } else {
                setUsername(username);
                setErrorMessage("<p>The administrative session has expired. Please log in and try again.</p>");
                return ERROR;
            }
        } catch (DataAccessException e) {
            logger.error("There was a problem updating/retrieving the record: \n" + e);
            return ERROR;
        }
        return SUCCESS;
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

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public Map<String, Object> getSession() {
        return sessionMap;
    }

    public void setSession(Map<String, Object> sessionMap) {
        this.sessionMap = sessionMap;
    }
}
