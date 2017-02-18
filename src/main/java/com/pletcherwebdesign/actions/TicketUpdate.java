package com.pletcherwebdesign.actions;

import com.opensymphony.xwork2.ActionSupport;
import com.pletcherwebdesign.beans.Ticket;
import com.pletcherwebdesign.beans.UpdateTicketInfo;
import com.pletcherwebdesign.dao.TicketDao;
import com.pletcherwebdesign.jdbcproperties.JdbcConfiguration;
import com.pletcherwebdesign.utils.FormSubmissionUtils;
import com.pletcherwebdesign.utils.TicketUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.dao.DataAccessException;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Seth on 2/17/2017.
 */
public class TicketUpdate extends ActionSupport implements SessionAware {

    private String username;
    private String errorMessage;
    private Map<String, Object> sessionMap;
    private String adminTickets;
    private FormSubmissionUtils formSubmissionUtils = new FormSubmissionUtils();

    private Logger logger = LoggerFactory.getLogger(TicketUpdate.class);

    public String execute() {

        HttpServletRequest request = ServletActionContext.getRequest();
        UpdateTicketInfo updateTicketInfo = getUpdateTicketInfo(request);

        ApplicationContext context = new AnnotationConfigApplicationContext(JdbcConfiguration.class);
        TicketDao ticketDao = context.getBean(TicketDao.class);

        try {
            if (sessionMap.containsValue(updateTicketInfo.getTicketAdmin())) {
                setUsername(updateTicketInfo.getTicketAdmin());
                ticketDao.updateTicketInfo(updateTicketInfo);
                setAdminTickets(formSubmissionUtils.getTicketsForAdmin(updateTicketInfo.getTicketAdmin()));
            } else {
                setUsername(updateTicketInfo.getTicketAdmin());
                setErrorMessage("<p>The administrator session has timed out. Please log back in and try again.</p>");
                return ERROR;
            }
        } catch (DataAccessException e) {
            logger.error("There was a problem inserting the record: " + e);
            setUsername(updateTicketInfo.getTicketAdmin());
            setErrorMessage("<p>There was an issue with updating the record</p>");
            return ERROR;
        }
        return SUCCESS;
    }

    private UpdateTicketInfo getUpdateTicketInfo(HttpServletRequest request) {
        UpdateTicketInfo updateTicketInfo = new UpdateTicketInfo();
        updateTicketInfo.setTicketAdmin(request.getParameter("ticketAdmin"));
        updateTicketInfo.setTicketId(Integer.parseInt(request.getParameter("ticketId")));
        updateTicketInfo.setPriorityLevel(request.getParameter("priorityLevel"));
        updateTicketInfo.setProgress(request.getParameter("progress"));
        updateTicketInfo.setProjectOrder(request.getParameter("projectOrder"));
        updateTicketInfo.setDevComment(request.getParameter("devComment"));
        updateTicketInfo.setTicketOpen(Boolean.valueOf(request.getParameter("ticketOpen")));
        return updateTicketInfo;
    }

    public String getAdminTickets() {
        return adminTickets;
    }

    private void setAdminTickets(String adminTickets) {
        this.adminTickets = adminTickets;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Map<String, Object> getSession() {
        return sessionMap;
    }

    public void setSession(Map<String, Object> sessionMap) {
        this.sessionMap = sessionMap;
    }
}