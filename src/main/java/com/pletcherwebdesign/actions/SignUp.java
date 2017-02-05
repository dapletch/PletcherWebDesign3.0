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
                        "Please <a style=\"color:#008000;text-decoration:none;\" href=\"../signup/signup.jsp\">try again</a>.</p>");
                return ERROR;
            }
            // Check to see if the user already exists in the database
            if (signUpDao.isUserAlreadyInDb(users)) {
                setErrorMessage("<p>The username has already been taken. " +
                        "Please <a style=\"color:#008000;text-decoration:none;\" href=\"../signup/signup.jsp\">try again</a>.</p>" +
                        userAlreadyInDbError());
                return ERROR;
            }
            // Insert the user into the database
            signUpDao.insertUser(users);
        } catch (DataAccessException e) {
            System.out.println("There was an issue with submitting the record: \n" + e);
            setErrorMessage("<p>The credentials you submitted were unable to be processed. " +
                    "Please review the credentials and <a style=\"color:#008000;text-decoration:none;\" href=\"../signup/signup.jsp\">try again</a>.</p>" +
                    userSignUpError());
            return ERROR;
        }
        return SUCCESS;
    }

    private String userAlreadyInDbError() {
        return "<p><b>User Credentials</b></p>\n" +
                " <ul style=\"list-style: none;\">\n" +
                "   <li class=\"list-group-item-error\"><b>First Name: </b>" + users.getFirstName() + "</li>\n" +
                "   <li class=\"list-group-item-error\"><b>Last Name: </b>" + users.getLastName() + "</li>\n" +
                "   <li class=\"list-group-item-error\"><b>Username: </b>" + users.getUsername() + "</li>\n" +
                "   <li class=\"list-group-item-error\"><b>Password: </b>" + users.getPassword() + "</li>\n" +
                " </ul>\n";
    }

    private String userSignUpError() {
        return "<p><b>User Credentials</b></p>\n" +
                " <ul style=\"list-style: none;\">\n" +
                "   <li class=\"list-group-item-error\"><b>First Name: </b>" + users.getFirstName() + "</li>\n" +
                "   <li class=\"list-group-item-error\"><b>Last Name: </b>" + users.getLastName() + "</li>\n" +
                "   <li class=\"list-group-item-error\"><b>Email: </b>" + users.getEmail() + "</li>\n" +
                "   <li class=\"list-group-item-error\"><b>Username: </b>" + users.getUsername() + "</li>\n" +
                "   <li class=\"list-group-item-error\"><b>Password: </b>" + users.getPassword() + "</li>\n" +
                "   <li class=\"list-group-item-error\"><b>Check Password: </b>" + users.getCheckPassword() + "</li>\n" +
                "   <li class=\"list-group-item-error\"><b>Street Address: </b>" + users.getStreetAddress() + "</li>\n" +
                "   <li class=\"list-group-item-error\"><b>City: </b>" + users.getCity() + "</li>\n" +
                "   <li class=\"list-group-item-error\"><b>State: </b>" + users.getState() + "</li>\n" +
                "   <li class=\"list-group-item-error\"><b>Zip Code: </b>" + users.getZipCode() + "</li>\n" +
                "   <li class=\"list-group-item-error\"><b>Phone Number: </b>" + users.getPhoneNumber() + "</li>\n" +
                " </ul>\n";
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
