package com.pletcherwebdesign.dao;

import com.pletcherwebdesign.beans.LoginCredentials;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Seth on 2/21/2017.
 */
@Configuration
@ComponentScan(basePackages = "com.pletcherwebdesign.*")
public class ForgotLoginDao {

    private Logger logger = LoggerFactory.getLogger(ForgotLoginDao.class);

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public ForgotLoginDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private String selectUserCredentialsQuery = "SELECT username, password, email FROM users WHERE email = ?";

    public LoginCredentials getUserLoginCredentialsFromEmail(String email) {
        try {
            LoginCredentials loginCredentials = jdbcTemplate.queryForObject(selectUserCredentialsQuery, new Object[]{ email },
                    new RowMapper<LoginCredentials>() {
                        public LoginCredentials mapRow(ResultSet rs, int rowNum) throws SQLException {
                            LoginCredentials loginCredentials = new LoginCredentials();
                            loginCredentials.setUsername(rs.getString(1));
                            loginCredentials.setPassword(rs.getString(2));
                            loginCredentials.setEmail(rs.getString(3));
                            return loginCredentials;
                        }
                    });
            return loginCredentials;
        } catch (EmptyResultDataAccessException e) {
            logger.error("There was an error retrieving the login credentials: " + e);
            return null;
        }
    }
}
