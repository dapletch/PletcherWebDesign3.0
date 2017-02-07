package com.pletcherwebdesign.dao;

import com.pletcherwebdesign.beans.Contact;
import com.pletcherwebdesign.utils.PletcherWebDesignUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;


/**
 * Created by Seth on 2/7/2017.
 */
@Configuration
@ComponentScan(basePackages = "com.pletcherwebdesign.*")
public class ContactDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public ContactDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private String insertContactRecordIntoDbQuery = "insert into contact (name, email, phone, message, date_entered) values (?, ?, ?, ?, ?)";

    public void insertContactRecordIntoDb(Contact contact) {
        // Setting the current timestamp for the record
        contact.setDateEntered(new DateTime());
        jdbcTemplate.update(insertContactRecordIntoDbQuery
                , contact.getName()
                , contact.getEmail()
                , contact.getPhoneNumber()
                , contact.getMessage()
                , new java.sql.Timestamp(contact.getDateEntered().getMillis()));
    }
}
