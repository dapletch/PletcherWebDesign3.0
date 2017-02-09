package com.pletcherwebdesign.actions;

import com.opensymphony.xwork2.ActionSupport;
import com.pletcherwebdesign.beans.FormSubmission;
import com.pletcherwebdesign.beans.Login;
import com.pletcherwebdesign.beans.Users;
import com.pletcherwebdesign.dao.LoginDao;
import com.pletcherwebdesign.email.beans.MessageBody;
import com.pletcherwebdesign.email.dao.EmailFormDao;
import com.pletcherwebdesign.email.sendemail.EmailConfig;
import com.pletcherwebdesign.email.sendemail.SendEmail;
import com.pletcherwebdesign.jdbcproperties.JdbcConfiguration;
import com.pletcherwebdesign.utils.PletcherWebDesignUtils;
import org.apache.struts2.interceptor.SessionAware;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.dao.DataAccessException;

import java.util.Map;

/**
 * Created by Seth on 2/7/2017.
 */
public class LoginIntoSite extends ActionSupport implements FormSubmission, SessionAware {

    private Login login = new Login();
    private Users user = new Users();
    private String errorMessage;
    private String clientUserId;
    private Boolean adminStatus;
    private Boolean sessionStatus;
    private Map<String, Object> sessionMap;

    private Logger logger = LoggerFactory.getLogger(LoginIntoSite.class);

    private static final String ADMIN_LOGIN = "admin_login";
    // This is under the assumption that there will only be one administrator for Pletcher Web Design
    private static final String ADMIN_SESSION_KEY = "administrator";

    public String execute() {

        ApplicationContext context = new AnnotationConfigApplicationContext(JdbcConfiguration.class);
        LoginDao loginDao = context.getBean(LoginDao.class);

        user = loginDao.getClientUserInfo(login);
        setClientUserId(checkUserObjectReturnUserClientKey(user));
        setAdminStatus(loginDao.isAdminUserValid(login));
        setSessionStatus(isSessionValid(getClientUserId()));

        try {
            // If the sessionStatus is true check to see what kind of session is being accessed
            if (!getSessionStatus()) {
                if (getAdminStatus()) {
                    // Adding email functionality to ensure that no one gets the admin password except me
                    // if someone logs in when I'm not accessing the admin page I'll know it.
                    sessionMap.put(ADMIN_SESSION_KEY, login.getUsername());
                    sendNotificationEmail();
                    return ADMIN_LOGIN;
                }
                if (getUser() != null) {
                    logger.info("The user has logged in: " + user.toString());
                    sessionMap.put(getClientUserId(), login.getUsername());
                    return SUCCESS;
                }
            } else {
                if (sessionMap.containsKey(getClientUserId())) {
                    return SUCCESS;
                }
                if (sessionMap.containsKey(ADMIN_SESSION_KEY)) {
                    return ADMIN_LOGIN;
                }
            }
        } catch (DataAccessException e) {
            logger.info("There was a problem with the user logging in: " + login.toString());
        }
        setErrorMessage(formError());
        return ERROR;
    }

    private String checkUserObjectReturnUserClientKey(Users user) {
        if (user != null) {
            return String.valueOf(user.getUserId());
        }
        return "noSessionKey";
    }

    private Boolean isSessionValid(String clientUserId) {
        if (clientUserId != null) {
            return sessionMap.containsKey(clientUserId);
        }
        return sessionMap.containsKey("administrator");
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

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getClientUserId() {
        return clientUserId;
    }

    public void setClientUserId(String clientUserId) {
        this.clientUserId = clientUserId;
    }

    public Boolean getAdminStatus() {
        return adminStatus;
    }

    public void setAdminStatus(Boolean adminStatus) {
        this.adminStatus = adminStatus;
    }

    public Boolean getSessionStatus() {
        return sessionStatus;
    }

    public void setSessionStatus(Boolean sessionStatus) {
        this.sessionStatus = sessionStatus;
    }
}