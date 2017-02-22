package com.pletcherwebdesign.actions;

import com.opensymphony.xwork2.ActionSupport;
import com.pletcherwebdesign.beans.LoginCredentials;
import com.pletcherwebdesign.dao.ForgotLoginDao;
import com.pletcherwebdesign.email.beans.MessageBody;
import com.pletcherwebdesign.email.sendemail.EmailConfig;
import com.pletcherwebdesign.email.sendemail.SendEmail;
import com.pletcherwebdesign.jdbcproperties.JdbcConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.dao.DataAccessException;


/**
 * Created by Seth on 2/21/2017.
 */
public class ForgotLogin extends ActionSupport {

    private String email;
    private String errorMessage;
    private LoginCredentials loginCredentials = new LoginCredentials();
    private Logger logger = LoggerFactory.getLogger(ForgotLogin.class);

    public String execute() {

        ApplicationContext context = new AnnotationConfigApplicationContext(JdbcConfiguration.class);
        ForgotLoginDao forgotLoginDao = context.getBean(ForgotLoginDao.class);
        try {

            loginCredentials = forgotLoginDao.getUserLoginCredentialsFromEmail(email);
            if (loginCredentials == null) {
                setErrorMessage("<p>The email you entered does not exist. " +
                        "Please <a href=\"../forgotlogin/forgotlogin.jsp\">try again</a>.</p>");
                return ERROR;
            }
            MessageBody messageBody = new MessageBody(loginCredentials.getEmail()
                    , "Your Login Credentials", emailMessage());
            context = new AnnotationConfigApplicationContext(EmailConfig.class);
            SendEmail sendEmail = context.getBean(SendEmail.class);
            sendEmail.sendEmailNoAttachment(messageBody);

        } catch (DataAccessException e) {
            logger.error("There following email was not found in the database: " + email);
            logger.error("There was a problem retrieving the login credentials from the database: " + e);
            return ERROR;
        }
        setEmail(email);
        return SUCCESS;
    }

    private String emailMessage() {
        return "Your login credentials are listed, as follows: \n" +
                "Username: " + loginCredentials.getUsername() + "\n" +
                "Password: " + loginCredentials.getPassword() + "\n" +
                "If these login credentials do not work. " +
                "Please feel free to contact us at http://www.pletcherwebdesign.com/contact/contact.jsp for further assistance.";
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
