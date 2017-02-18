package com.pletcherwebdesign.dao;

import com.pletcherwebdesign.beans.Ticket;
import com.pletcherwebdesign.beans.UpdateTicketInfo;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
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

    private String selectTicketsForClientQuery = "select ticket_id, username, subject, project_order, priority_level, progress, dev_comment, deadline, ticket_date, ticket_open from ticket where username = ?;";

    private String selectTicketsForAdminQuery = "select ticket_id, username, subject, project_order, priority_level, progress, dev_comment, deadline, ticket_date, ticket_open from ticket;";

    private String insertTicketIntoDbQuery = "insert into ticket (username, subject, project_order, priority_level, progress, dev_comment, deadline, ticket_open) values (?, ?, ?, ?, ?, ?, ?, ?);";

    private String selectTicketByIdQuery = "select ticket_id, username, subject, project_order, priority_level, progress, dev_comment, deadline, ticket_date, ticket_open from ticket where ticket_id = ?;";

    private String updateTicketInfoQuery = "update ticket set priority_level = ?, progress = ?, project_order = ?, dev_comment = ?, ticket_open = ? where ticket_id = ?;";

    public List<Ticket> selectTicketsForClient(String username) {
        // Need to use the BeanPropertyRowMapper in order to get the ticket_id from the database
        return jdbcTemplate.query(selectTicketsForClientQuery, new Object[]{username}, new BeanPropertyRowMapper<Ticket>() {
            public Ticket mapRow(ResultSet rs, int i) throws SQLException {
                Ticket ticket = new Ticket();
                ticket.setTicketId(rs.getInt(1));
                ticket.setUsername(rs.getString(2));
                ticket.setSubject(rs.getString(3));
                ticket.setProjectOrder(rs.getString(4));
                ticket.setPriorityLevel(rs.getString(5));
                ticket.setProgress(rs.getString(6));
                ticket.setDevComment(rs.getString(7));
                ticket.setDeadline(rs.getString(8));
                ticket.setTicketDate(new DateTime(rs.getTimestamp(9)));
                ticket.setTicketOpen(rs.getBoolean(10));
                return ticket;
            }
        });
    }

    public List<Ticket> selectTicketsForAdmin() {
        // Need to use the BeanPropertyRowMapper in order to get the ticket_id from the database
        return jdbcTemplate.query(selectTicketsForAdminQuery, new BeanPropertyRowMapper<Ticket>() {
            public Ticket mapRow(ResultSet rs, int i) throws SQLException {
                Ticket ticket = new Ticket();
                ticket.setTicketId(rs.getInt(1));
                ticket.setUsername(rs.getString(2));
                ticket.setSubject(rs.getString(3));
                ticket.setProjectOrder(rs.getString(4));
                ticket.setPriorityLevel(rs.getString(5));
                ticket.setProgress(rs.getString(6));
                ticket.setDevComment(rs.getString(7));
                ticket.setDeadline(rs.getString(8));
                ticket.setTicketDate(new DateTime(rs.getTimestamp(9)));
                ticket.setTicketOpen(rs.getBoolean(10));
                return ticket;
            }
        });
    }

    public void insertTicketIntoDb(Ticket ticket) {
        // Setting the ticket open status to true for it will be open when it is first inserted into the database
        ticket.setTicketOpen(true);
        jdbcTemplate.update(insertTicketIntoDbQuery, ticket.getUsername()
                , ticket.getSubject()
                , ticket.getProjectOrder()
                , ticket.getPriorityLevel()
                , ticket.getProgress()
                , ticket.getDevComment()
                , ticket.getDeadline()
                , ticket.getTicketOpen());
    }

    public Ticket selectTicketById(Integer ticketId) {
        try {
            Ticket ticket = jdbcTemplate.queryForObject(selectTicketByIdQuery, new Object[]{ticketId},
                    new RowMapper<Ticket>() {
                        public Ticket mapRow(ResultSet rs, int rowNum) throws SQLException {

                            Ticket ticket = new Ticket();
                            ticket.setTicketId(rs.getInt(1));
                            ticket.setUsername(rs.getString(2));
                            ticket.setSubject(rs.getString(3));
                            ticket.setProjectOrder(rs.getString(4));
                            ticket.setPriorityLevel(rs.getString(5));
                            ticket.setProgress(rs.getString(6));
                            ticket.setDevComment(rs.getString(7));
                            ticket.setDeadline(rs.getString(8));
                            ticket.setTicketDate(new DateTime(rs.getTimestamp(9)));
                            ticket.setTicketOpen(rs.getBoolean(10));

                            return ticket;
                        }
                    });
            return ticket;
        } catch (EmptyResultDataAccessException e) {
            logger.error("There was an error retrieving the recipient, and subject: \n" + e);
            return null;
        }
    }

    public void updateTicketInfo(UpdateTicketInfo ticket) {
        jdbcTemplate.update(updateTicketInfoQuery, ticket.getPriorityLevel()
                , ticket.getProgress()
                , ticket.getProjectOrder()
                , ticket.getDevComment()
                , ticket.getTicketOpen()
                , ticket.getTicketId());
    }
}
