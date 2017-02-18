package com.pletcherwebdesign.beans;

/**
 * Created by Seth on 2/17/2017.
 */
public class UpdateTicketInfo {

    private Integer ticketId;
    private String ticketAdmin;
    private String priorityLevel;
    private String progress;
    private String projectOrder;
    private String devComment;
    private Boolean ticketOpen;

    public UpdateTicketInfo() {
        super();
    }

    public UpdateTicketInfo(Integer ticketId, String ticketAdmin, String priorityLevel, String progress, String projectOrder, String devComment, Boolean ticketOpen) {
        this.ticketId = ticketId;
        this.ticketAdmin = ticketAdmin;
        this.priorityLevel = priorityLevel;
        this.progress = progress;
        this.projectOrder = projectOrder;
        this.devComment = devComment;
        this.ticketOpen = ticketOpen;
    }

    public Integer getTicketId() {
        return ticketId;
    }

    public void setTicketId(Integer ticketId) {
        this.ticketId = ticketId;
    }

    public String getTicketAdmin() {
        return ticketAdmin;
    }

    public void setTicketAdmin(String ticketAdmin) {
        this.ticketAdmin = ticketAdmin;
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

    public String getProjectOrder() {
        return projectOrder;
    }

    public void setProjectOrder(String projectOrder) {
        this.projectOrder = projectOrder;
    }

    public String getDevComment() {
        return devComment;
    }

    public void setDevComment(String devComment) {
        this.devComment = devComment;
    }

    public Boolean getTicketOpen() {
        return ticketOpen;
    }

    public void setTicketOpen(Boolean ticketOpen) {
        this.ticketOpen = ticketOpen;
    }

    @Override
    public String toString() {
        return "UpdateTicketInfo{" +
                "ticketId=" + ticketId +
                ", ticketAdmin='" + ticketAdmin + '\'' +
                ", priorityLevel='" + priorityLevel + '\'' +
                ", progress='" + progress + '\'' +
                ", projectOrder='" + projectOrder + '\'' +
                ", devComment='" + devComment + '\'' +
                ", ticketOpen=" + ticketOpen +
                '}';
    }
}
