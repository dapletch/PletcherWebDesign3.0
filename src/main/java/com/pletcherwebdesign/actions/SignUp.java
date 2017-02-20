package com.pletcherwebdesign.actions;

import com.opensymphony.xwork2.ActionSupport;
import com.pletcherwebdesign.beans.interfaces.FormRequirements;
import com.pletcherwebdesign.beans.Users;
import com.pletcherwebdesign.dao.SignUpDao;
import com.pletcherwebdesign.jdbcproperties.JdbcConfiguration;
import com.pletcherwebdesign.utils.FormSubmissionUtils;
import com.pletcherwebdesign.utils.PletcherWebDesignUtils;
import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.dao.DataAccessException;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Seth on 2/4/2017.
 */
public class SignUp extends ActionSupport implements FormRequirements {

    private Users users = new Users();
    private FormSubmissionUtils formSubmissionUtils = new FormSubmissionUtils();
    private String errorMessage;

    private Logger logger = LoggerFactory.getLogger(SignUp.class);

    public String execute() {

        ApplicationContext context = new AnnotationConfigApplicationContext(JdbcConfiguration.class);
        SignUpDao signUpDao = context.getBean(SignUpDao.class);
        HttpServletRequest request = ServletActionContext.getRequest();

        try {
            if (!isSignUpFormInputValid(users, request)) {
                return ERROR;
            }
            // Check to see if the user already exists in the database
            if (signUpDao.isUserAlreadyInDb(users)) {
                setErrorMessage("<p>The username has already been taken. " +
                        "Please <a href=\"../signup/signup.jsp\">try again</a>.</p>" +
                        userAlreadyInDbError());
                return ERROR;
            }
            // Insert the user into the database
            signUpDao.insertUser(users);
            // Send notification email as text message to my personal phone
            formSubmissionUtils.sendNotificationEmail("signup", emailMessage());
        } catch (DataAccessException e) {
            logger.error("There was an issue with submitting the record: \n", e);
            setErrorMessage("<p>The credentials you submitted were unable to be processed. " +
                    "Please review the credentials and <a href=\"../signup/signup.jsp\">try again</a>.</p>" +
                    formError());
            return ERROR;
        }
        logger.info("User submitted to the database: " + users.toString());
        return SUCCESS;
    }

    private Boolean isSignUpFormInputValid(Users users, HttpServletRequest request) {
        // Email needs to be validated to stop/prevent spam
        if (!PletcherWebDesignUtils.isEmailAddressValid(users.getEmail())) {
            setErrorMessage("<p>The email, " + users.getEmail() + ", you entered is not a valid email address. " +
                    "Please <a href=\"../signup/signup.jsp\">try again</a>.</p>");
            logger.error("User email is invalid: " + users.getEmail());
            return false;
        }
        // The username cannot be admin, for the admin password is in the
        if (users.getUsername().equals("admin")) {
            setErrorMessage("<p>The username you have chosen is not a valid username. " +
                    "Please <a href=\"../signup/signup.jsp\">try again</a>.</p>");
            logger.error("User attempted to create admin account: " + users.getUsername());
            return false;
        }
        if (users.getPassword().equals("") || users.getPassword() == null) {
            setErrorMessage("<p>You must enter a password. " +
                    "Please <a href=\"../signup/signup.jsp\">try again</a>.</p>");
            return false;
        }
        if (users.getCheckPassword().equals("") || users.getCheckPassword() == null) {
            setErrorMessage("<p>You must validate your password. " +
                    "Please <a href=\"../signup/signup.jsp\">try again</a>.</p>");
            return false;
        }
        // Check if passwords entered match
        if (!users.getPassword().equals(users.getCheckPassword())) {
            setErrorMessage("<p>The passwords you entered do not match. " +
                    "Please <a href=\"../signup/signup.jsp\">try again</a>.</p>");
            logger.error("User passwords do not match: " + users.getPassword() + "\n" + users.getCheckPassword());
            return false;
        }
        // Validating the phone number
        if (!PletcherWebDesignUtils.isPhoneNumberValid(users.getPhoneNumber())) {
            setErrorMessage("<p>The phone number, " + users.getPhoneNumber() + ", you entered is not a valid phone number. " +
                    "Please <a href=\"../signup/signup.jsp\">try again</a>.</p>");
            logger.error("User phone number is invalid: ", users.getPhoneNumber());
            return false;
        }
        if (!formSubmissionUtils.isCaptchaValid(request)) {
            setErrorMessage("<p>The captcha you entered was found to be incorrect. Please <a href=\"../signup/signup.jsp\">try again</a>.</p>");
            logger.error("The captcha entered was found to be incorrect");
            return false;
        }
        return true;
    }

    public String formError() {
        return "<table class=\"table table-borderless\">\n" +
                "    <tr>\n" +
                "        <td align=\"left\" colspan=\"2\"><b>User Credentials</b></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "        <td><b>First Name:</b></td>\n" +
                "        <td>" + users.getFirstName() + "</td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "        <td><b>Last Name:</b></td>\n" +
                "        <td>" + users.getLastName() + "</td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "        <td><b>Email:</b></td>\n" +
                "        <td>" + users.getEmail() + "</td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "        <td><b>Username:</b></td>\n" +
                "        <td>" + users.getUsername() + "</td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "        <td><b>Password:</b></td>\n" +
                "        <td>" + users.getPassword() + "</td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "        <td><b>Check Password:</b></td>\n" +
                "        <td>" + users.getCheckPassword() + "</td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "        <td><b>Street Address:</b></td>\n" +
                "        <td>" + users.getStreetAddress() + "</td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "        <td><b>City:</b></td>\n" +
                "        <td>" + users.getCity() + "</td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "        <td><b>State:</b></td>\n" +
                "        <td>" + users.getState() + "</td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "        <td><b>Zip Code:</b></td>\n" +
                "        <td>" + users.getZipCode() + "</td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "        <td><b>Phone Number:</b></td>\n" +
                "        <td>" + users.getPhoneNumber() + "</td>\n" +
                "    </tr>\n" +
                "</table>";
    }

    public String emailMessage() {
        return "The following user has just signed up for Pletcher Web Design: \n" +
                "First Name: " + users.getFirstName() + "\n" +
                "Last Name: " + users.getLastName() + "\n" +
                "Email: " + users.getEmail() + "\n" +
                "Username: " + users.getUsername() + "\n" +
                "Password: " + users.getPassword() + "\n" +
                "Street Address: " + users.getStreetAddress() + "\n" +
                "City: " + users.getCity() + "\n" +
                "State: " + users.getState() + "\n" +
                "Zip Code: " + users.getZipCode() + "\n" +
                "Phone Number: " + users.getPhoneNumber();
    }

    private String userAlreadyInDbError() {
        return "<table class=\"table table-borderless\">\n" +
                "    <tr>\n" +
                "        <td align=\"left\" colspan=\"2\"><b>User Credentials</b></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "        <td><b>First Name:</b></td>\n" +
                "        <td>" + users.getFirstName() + "</td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "        <td><b>Last Name:</b></td>\n" +
                "        <td>" + users.getLastName() + "</td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "        <td><b>Username:</b></td>\n" +
                "        <td>" + users.getUsername() + "</td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "        <td><b>Password:</b></td>\n" +
                "        <td>" + users.getPassword() + "</td>\n" +
                "    </tr>\n" +
                "</table>";
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

}
