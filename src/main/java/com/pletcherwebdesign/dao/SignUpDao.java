package com.pletcherwebdesign.dao;

import com.pletcherwebdesign.beans.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Created by Seth on 2/4/2017.
 */
@Configuration
@ComponentScan(basePackages = "com.pletcherwebdesign.*")
public class SignUpDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public SignUpDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private String checkIfUserExists = "select count(*) " +
            "from users " +
            "where username = ? " +
            "and email = ?;";

    private String checkIfEmailUsed = "select count(*) from users where email = ?;";

    private String insertIntoUsers = "insert into users (first_name, last_name, email, username, password" +
            ", check_password, street_address, city, st, zip, phone) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

    public Boolean isUserAlreadyInDb(Users user) {
        Integer userInt = jdbcTemplate.queryForObject(checkIfUserExists, Integer.class
                , user.getUsername(), user.getEmail());
        return userInt == 1;
    }

    public Boolean isEmailAlreadyUsed(Users user) {
        Integer userInt = jdbcTemplate.queryForObject(checkIfEmailUsed, Integer.class
                , user.getEmail());
        return userInt == 1;
    }

    public void insertUser(Users user) {
        jdbcTemplate.update(insertIntoUsers, user.getFirstName()
        , user.getLastName()
        , user.getEmail()
        , user.getUsername()
        , user.getPassword()
        , user.getCheckPassword()
        , user.getStreetAddress()
        , user.getCity()
        , user.getState()
        , user.getZipCode()
        , user.getPhoneNumber());
    }
}
