package com.pletcherwebdesign.dao;

import com.pletcherwebdesign.beans.InfoSession;
import com.pletcherwebdesign.jdbcproperties.JdbcConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Created by Seth on 2/6/2017.
 */
@Configuration
@ComponentScan(basePackages = "com.pletcherwebdesign.*")
public class InfoSessionDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public InfoSessionDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private String insertInfoSessionSignUpQuery = "insert into info_session (first_name, last_name, phone, email, best_time, description)" +
            " values (?, ?, ?, ?, ?, ?);";

    public void insertInfoSessionRecord(InfoSession infoSession) {
        jdbcTemplate.update(insertInfoSessionSignUpQuery
                , infoSession.getFirstName()
                , infoSession.getLastName()
                , infoSession.getPhoneNumber()
                , infoSession.getEmail()
                , infoSession.getBestTime()
                , infoSession.getDescription());
    }
}
