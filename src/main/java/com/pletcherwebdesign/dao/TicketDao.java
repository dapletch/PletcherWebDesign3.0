package com.pletcherwebdesign.dao;

import com.pletcherwebdesign.beans.Ticket;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Seth on 2/8/2017.
 */
@Configuration
@ComponentScan(basePackages = "com.pletcherwebdesign.*")
public class TicketDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public TicketDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private Logger logger = LoggerFactory.getLogger(TicketDao.class);

    private String selectTicketsForClientQuery = "select username, subject, project_order, priority_level, progress, dev_comment, deadline, ticket_date from ticket where username = ?";

    private String insertTicketIntoDbQuery = "insert into ticket (username, subject, project_order, priority_level, progress, dev_comment, deadline) values (?, ?, ?, ?, ?, ?, ?)";

    public List<Ticket> selectTicketsForClient(String username) {
        return jdbcTemplate.query(selectTicketsForClientQuery, new Object[]{username}, new RowMapper<Ticket>() {
            public Ticket mapRow(ResultSet rs, int i) throws SQLException {
                Ticket ticket = new Ticket();
                ticket.setTicketId(i + 1);
                ticket.setUsername(rs.getString(1));
                ticket.setSubject(rs.getString(2));
                ticket.setProjectOrder(rs.getString(3));
                ticket.setPriorityLevel(rs.getString(4));
                ticket.setProgress(rs.getString(5));
                ticket.setDevComment(rs.getString(6));
                ticket.setDeadline(rs.getString(7));
                ticket.setTicketDate(new DateTime(rs.getTimestamp(8)));
                return ticket;
            }
        });
    }

    public void insertTicketIntoDb(Ticket ticket) {
        jdbcTemplate.update(insertTicketIntoDbQuery, ticket.getUsername()
                , ticket.getSubject()
                , ticket.getProjectOrder()
                , ticket.getPriorityLevel()
                , ticket.getProgress()
                , ticket.getDevComment()
                , ticket.getDeadline());
    }
}
