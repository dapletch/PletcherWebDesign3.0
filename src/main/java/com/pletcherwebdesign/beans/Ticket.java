package com.pletcherwebdesign.beans;

import org.joda.time.DateTime;

/**
 * Created by Seth on 2/8/2017.
 */
public class Ticket {

    private Integer ticketId;
    private String ticketAdmin;
    private String username;
    private String subject;
    private String projectOrder;
    private String priorityLevel;
    private String progress;
    private String devComment;
    private String deadline;
    private DateTime ticketDate;
    private Boolean ticketOpen;

    public Ticket() {
        super();
    }

    public Ticket(String username, String subject, String projectOrder, String priorityLevel, String progress, String devComment, String deadline) {
        this.username = username;
        this.subject = subject;
        this.projectOrder = projectOrder;
        this.priorityLevel = priorityLevel;
        this.progress = progress;
        this.devComment = devComment;
        this.deadline = deadline;
    }

    public Ticket(Integer ticketId, String username, String subject, String projectOrder, String priorityLevel, String progress, String devComment, String deadline, DateTime ticketDate, Boolean ticketOpen) {
        this.ticketId = ticketId;
        this.username = username;
        this.subject = subject;
        this.projectOrder = projectOrder;
        this.priorityLevel = priorityLevel;
        this.progress = progress;
        this.devComment = devComment;
        this.deadline = deadline;
        this.ticketDate = ticketDate;
        this.ticketOpen = ticketOpen;
    }

    public Integer getTicketId() {
        return ticketId;
    }

    public void setTicketId(Integer ticketId) {
        this.ticketId = ticketId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    // Only for use while administrating tickets
    public String getTicketAdmin() {
        return ticketAdmin;
    }

    public void setTicketAdmin(String ticketAdmin) {
        this.ticketAdmin = ticketAdmin;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getProjectOrder() {
        return projectOrder;
    }

    public void setProjectOrder(String projectOrder) {
        this.projectOrder = projectOrder;
    }

    public String getPriorityLevel() {
        return priorityLevel;
    }

    public void setPriorityLevel(String priorityLevel) {
        this.priorityLevel = priorityLevel;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }

    public String getDevComment() {
        return devComment;
    }

    public void setDevComment(String devComment) {
        this.devComment = devComment;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public DateTime getTicketDate() {
        return ticketDate;
    }

    public void setTicketDate(DateTime ticketDate) {
        this.ticketDate = ticketDate;
    }

    public Boolean getTicketOpen() {
        return ticketOpen;
    }

    public void setTicketOpen(Boolean ticketOpen) {
        this.ticketOpen = ticketOpen;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "ticketId=" + ticketId +
                ", username='" + username + '\'' +
                ", subject='" + subject + '\'' +
                ", projectOrder='" + projectOrder + '\'' +
                ", priorityLevel='" + priorityLevel + '\'' +
                ", progress='" + progress + '\'' +
                ", devComment='" + devComment + '\'' +
                ", deadline='" + deadline + '\'' +
                ", ticketDate=" + ticketDate +
                '}';
    }
}
