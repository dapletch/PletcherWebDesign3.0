package com.pletcherwebdesign.dao;

import com.pletcherwebdesign.beans.Admin;
import com.pletcherwebdesign.beans.Login;
import com.pletcherwebdesign.beans.Users;
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
 * Created by Seth on 2/7/2017.
 */
@Configuration
@ComponentScan(basePackages = "com.pletcherwebdesign.*")
public class LoginDao {

    private Logger logger = LoggerFactory.getLogger(LoginDao.class);

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public LoginDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private String selectAdminUserFromDbQuery = "select admin_id, username from administrator where username = ? and password = ?";

    private String selectClientUserFromDbQuery= "select usr_id, first_name, last_name, email, username" +
            ", street_address, city, st, zip, phone from users where username = ? and password = ?";

    public Admin getAdminUserInfo(Login login) {
        try {
            Admin admin = jdbcTemplate.queryForObject(selectAdminUserFromDbQuery, new Object[]{login.getUsername(), login.getPassword()},
                    new RowMapper<Admin>() {
                        public Admin mapRow(ResultSet rs, int rowNum) throws SQLException {

                            Admin admin = new Admin();
                            admin.setAdminId(rs.getInt(1));
                            admin.setUsername(rs.getString(2));

                            return admin;
                        }
                    });
            return admin;
        } catch (EmptyResultDataAccessException e) {
            logger.error("There was an error retrieving the recipient, and subject: \n" + e);
            return null;
        }
    }

    public Users getClientUserInfo(Login login) {
        try {
             Users user = jdbcTemplate.queryForObject(selectClientUserFromDbQuery, new Object[]{login.getUsername(), login.getPassword()},
                    new RowMapper<Users>() {
                        public Users mapRow(ResultSet rs, int rowNum) throws SQLException {

                            Users user = new Users();
                            user.setUserId(rs.getInt(1));
                            user.setFirstName(rs.getString(2));
                            user.setLastName(rs.getString(3));
                            user.setEmail(rs.getString(4));
                            user.setUsername(rs.getString(5));
                            user.setStreetAddress(rs.getString(6));
                            user.setCity(rs.getString(7));
                            user.setState(rs.getString(8));
                            user.setZipCode(rs.getString(9));
                            user.setPhoneNumber(rs.getString(10));

                            return user;
                        }
                    });
            return user;
        } catch (EmptyResultDataAccessException e) {
            logger.error("There was an error retrieving the recipient, and subject: \n" + e);
            return null;
        }
    }
}
