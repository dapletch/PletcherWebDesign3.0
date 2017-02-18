package com.pletcherwebdesign.actions;

import com.opensymphony.xwork2.ActionSupport;
import com.pletcherwebdesign.beans.*;
import com.pletcherwebdesign.beans.interfaces.FormSubmission;
import com.pletcherwebdesign.dao.LoginDao;
import com.pletcherwebdesign.dao.TicketDao;
import com.pletcherwebdesign.email.beans.MessageBody;
import com.pletcherwebdesign.email.dao.EmailFormDao;
import com.pletcherwebdesign.email.sendemail.EmailConfig;
import com.pletcherwebdesign.email.sendemail.SendEmail;
import com.pletcherwebdesign.jdbcproperties.JdbcConfiguration;
import com.pletcherwebdesign.utils.PletcherWebDesignUtils;
import com.pletcherwebdesign.utils.TicketUtils;
import org.apache.struts2.interceptor.SessionAware;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.dao.DataAccessException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Seth on 2/7/2017.
 */
public class LoginIntoSite extends ActionSupport implements FormSubmission, SessionAware {

    private Login login = new Login();
    private Users user = new Users();
    private Admin admin = new Admin();
    private String errorMessage;
    private String userTickets;
    private String adminTickets;
    private String clientUserId;
    private String adminUserId;
    private Boolean sessionStatus;
    private String username;
    private Map<String, Object> sessionMap;
    private List<Ticket> clientTicketList = new ArrayList<>();
    private List<Ticket> adminTicketList = new ArrayList<>();

    private Logger logger = LoggerFactory.getLogger(LoginIntoSite.class);

    private static final String ADMIN_LOGIN = "admin_login";

    public String execute() {

        ApplicationContext context = new AnnotationConfigApplicationContext(JdbcConfiguration.class);
        LoginDao loginDao = context.getBean(LoginDao.class);

        user = loginDao.getClientUserInfo(login);
        admin = loginDao.getAdminUserInfo(login);
        checkSessionKeysSetDefault(user, admin);
        setSessionStatus(isSessionValid(getClientUserId(), getAdminUserId()));

        try {
            // Setting the username for display on the page when the user logs in
            setUsername(login.getUsername());
            // If the sessionStatus is true check to see what kind of session is being accessed
            if (!getSessionStatus()) {
                if (getAdmin() != null) {
                    // Adding email functionality to ensure that no one gets the admin password except me
                    // if someone logs in when I'm not accessing the admin page I'll know it.
                    sessionMap.put(getAdminUserId(), login.getUsername());
                    sendNotificationEmail();
                    setAdminTickets(getTicketsForAdmin());
                    return ADMIN_LOGIN;
                }
                if (getUser() != null) {
                    logger.info("The user has logged in: " + user.toString());
                    sessionMap.put(getClientUserId(), login.getUsername());
                    setUserTickets(getTicketListForClient(login.getUsername()));
                    return SUCCESS;
                }
            } else {
                if (sessionMap.containsKey(getClientUserId())) {
                    setUserTickets(getTicketListForClient(login.getUsername()));
                    return SUCCESS;
                }
                if (sessionMap.containsKey(getAdminUserId())) {
                    return ADMIN_LOGIN;
                }
            }
        } catch (DataAccessException e) {
            logger.error("There was a problem with the user logging in: " + e);
        }
        setErrorMessage(formError());
        return ERROR;
    }

    private String getTicketListForClient(String username) {
        ApplicationContext context = new AnnotationConfigApplicationContext(JdbcConfiguration.class);
        TicketDao ticketDao = context.getBean(TicketDao.class);
        clientTicketList = ticketDao.selectTicketsForClient(username);
        return TicketUtils.getTicketsClientToSee(clientTicketList);
    }

    private String getTicketsForAdmin() {
        ApplicationContext context = new AnnotationConfigApplicationContext(JdbcConfiguration.class);
        TicketDao ticketDao = context.getBean(TicketDao.class);
        adminTicketList = ticketDao.selectTicketsForAdmin();
        return TicketUtils.getTicketsForAdmin(adminTicketList, getUsername());
    }

    private void checkSessionKeysSetDefault(Users user, Admin admin) {
        if (user != null) {
            setClientUserId(String.valueOf(user.getUserId()));
        } else setClientUserId("noUserSessionKey");
        if (admin != null) {
            setAdminUserId(String.valueOf(admin.getAdminId()));
        } else setAdminUserId("noAdminSessionKey");
    }

    private Boolean isSessionValid(String clientUserId, String adminUserId) {
        if (clientUserId != null) {
            return sessionMap.containsKey(clientUserId);
        }
        return sessionMap.containsKey(adminUserId);
    }

    public String formError() {
        return "<p>The username you have entered does not exist. Please try <a href=\"../index.jsp\">try again</a>.</p>";
    }

    public void sendNotificationEmail() {
        ApplicationContext context = new AnnotationConfigApplicationContext(EmailConfig.class);
        SendEmail sendEmail = context.getBean(SendEmail.class);
        sendEmail.sendEmailNoAttachment(getMessageBodyForm());
    }

    public MessageBody getMessageBodyForm() {
        ApplicationContext context = new AnnotationConfigApplicationContext(JdbcConfiguration.class);
        EmailFormDao emailFormDao = context.getBean(EmailFormDao.class);
        MessageBody messageBody = emailFormDao.getHtmlFormInfoForEmail("admin");
        return PletcherWebDesignUtils.setMessageBodyNoAttachment(messageBody, emailMessage());
    }

    public String emailMessage() {
        return "Someone has logged into the admin user account of Pletcher Web Design on " +
                PletcherWebDesignUtils.getCurrentTimeStamp() + ".\n" +
                "Now would be the time to take action, only if you are not currently not logged in yourself.";
    }

    public void setSession(Map<String, Object> sessionMap) {
        this.sessionMap = sessionMap;
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    private Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getUserTickets() {
        return userTickets;
    }

    private void setUserTickets(String userTickets) {
        this.userTickets = userTickets;
    }

    public String getAdminTickets() {
        return adminTickets;
    }

    private void setAdminTickets(String adminTickets) {
        this.adminTickets = adminTickets;
    }

    private String getClientUserId() {
        return clientUserId;
    }

    private void setClientUserId(String clientUserId) {
        this.clientUserId = clientUserId;
    }

    private String getAdminUserId() {
        return adminUserId;
    }

    private void setAdminUserId(String adminUserId) {
        this.adminUserId = adminUserId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    private Boolean getSessionStatus() {
        return sessionStatus;
    }

    private void setSessionStatus(Boolean sessionStatus) {
        this.sessionStatus = sessionStatus;
    }
}