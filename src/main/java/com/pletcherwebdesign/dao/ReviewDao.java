package com.pletcherwebdesign.dao;

import com.pletcherwebdesign.beans.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Created by Seth on 2/5/2017.
 */
@Configuration
@ComponentScan(basePackages = "com.pletcherwebdesign.*")
public class ReviewDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public ReviewDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private String insertIntoUsers = "insert into review (first_name, last_name, email, phone, user_comment) values (?, ?, ?, ?, ?);";

    public void insertReview(Review review) {
        jdbcTemplate.update(insertIntoUsers, review.getFirstName()
        , review.getLastName()
        , review.getEmail()
        , review.getPhoneNumber()
        , review.getComment());
    }
}
