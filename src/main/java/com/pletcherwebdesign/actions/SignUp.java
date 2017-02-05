package com.pletcherwebdesign.actions;

import com.opensymphony.xwork2.ActionSupport;
import com.pletcherwebdesign.beans.Users;
import com.pletcherwebdesign.dao.SignUpDao;
import com.pletcherwebdesign.jdbcproperties.JdbcConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.dao.DataAccessException;

/**
 * Created by Seth on 2/4/2017.
 */
public class SignUp extends ActionSupport {

    private Users users = new Users();
    private String errorMessage;

    public String execute() {

        ApplicationContext context = new AnnotationConfigApplicationContext(JdbcConfiguration.class);
        SignUpDao signUpDao = context.getBean(SignUpDao.class);

        try {
            // Check if passwords entered match
            if (!users.getPassword().equals(users.getCheckPassword())) {
                setErrorMessage("<p>The passwords you entered do not match. " +
                        "Please <a href=\"../signup/signup.jsp\">try again</a>.</p>");
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
        } catch (DataAccessException e) {
            System.out.println("There was an issue with submitting the record: \n" + e);
            setErrorMessage("<p>The credentials you submitted were unable to be processed. " +
                    "Please review the credentials and <a href=\"../signup/signup.jsp\">try again</a>.</p>" +
                    userSignUpError());
            return ERROR;
        }
        return SUCCESS;
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

    private String userSignUpError() {
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
