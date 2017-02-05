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

    public String execute() {

        ApplicationContext context = new AnnotationConfigApplicationContext(JdbcConfiguration.class);
        SignUpDao signUpDao = context.getBean(SignUpDao.class);

        try {
            if (signUpDao.isUserAlreadyInDb(users)) {
                return ERROR;
            }
            signUpDao.insertUser(users);
        } catch (DataAccessException e) {
            System.out.println("There was an issue with submitting the record: \n" + e);
            return ERROR;
        }
        return SUCCESS;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }
}
