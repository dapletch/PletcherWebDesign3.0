package com.pletcherwebdesign.email.dao;

import com.pletcherwebdesign.email.beans.MessageBody;
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
 * Created by Seth on 2/6/2017.
 */
@Configuration
@ComponentScan(basePackages = "com.pletcherwebdesign.*")
public class EmailFormDao {

    private JdbcTemplate jdbcTemplate;
    private Logger logger = LoggerFactory.getLogger(EmailFormDao.class);

    @Autowired
    public EmailFormDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private String selectHtmlEmailFormQuery = "SELECT recipient, subject FROM email_form_info WHERE form = ?";

    public MessageBody getHtmlFormInfoForEmail(String form) {
        try {
            MessageBody messageBody = jdbcTemplate.queryForObject(selectHtmlEmailFormQuery, new Object[]{form},
                    new RowMapper<MessageBody>() {
                        public MessageBody mapRow(ResultSet rs, int rowNum) throws SQLException {

                            MessageBody messageBody = new MessageBody();
                            messageBody.setRecipient(rs.getString(1));
                            messageBody.setSubject(rs.getString(2));

                            return messageBody;
                        }
                    });
            return messageBody;
        } catch (EmptyResultDataAccessException e) {
            logger.error("There was an error retrieving the recipient, and subject: \n" + e);
            return null;
        }
    }
}
