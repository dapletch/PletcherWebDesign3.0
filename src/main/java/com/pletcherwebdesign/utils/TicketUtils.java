package com.pletcherwebdesign.utils;

import com.pletcherwebdesign.beans.Ticket;

import java.util.List;

/**
 * Created by Seth on 2/9/2017.
 */
public class TicketUtils {

    public static String getTicketsClientToSee(List<Ticket> userTicketsList) {
        StringBuilder sb = new StringBuilder();
        sb.append("<table class=\"table table-bordered\">\n" +
                "<thead>\n" +
                "<tr>\n" +
                "<th>ID</th>\n" +
                "<th>Subject</th>\n" +
                "<th>Description</th>\n" +
                "<th>Priority Level</th>\n" +
                "<th>Progress</th>\n" +
                "<th>Developer Comment</th>\n" +
                "<th>Deadline</th>\n" +
                "<th>Ticket Date</th>\n" +
                "<th>Ticket Open</th>\n" +
                "</tr>\n" +
                "</thead>\n" +
                "<tbody>\n");
        for (Ticket ticket : userTicketsList) {
            sb.append("<tr>");
            sb.append("<td>").append(ticket.getTicketId()).append("</td>\n");
            sb.append("<td>").append(ticket.getSubject()).append("</td>\n");
            sb.append("<td>").append(ticket.getProjectOrder()).append("</td>\n");
            sb.append("<td>").append(ticket.getPriorityLevel()).append("</td>\n");
            sb.append("<td>").append(ticket.getProgress()).append("</td>\n");
            sb.append("<td>").append(ticket.getDevComment()).append("</td>\n");
            sb.append("<td>").append(ticket.getDeadline()).append("</td>\n");
            sb.append("<td>").append(ticket.getTicketDate()).append("</td>\n");
            sb.append("<td>").append(ticket.getTicketOpen()).append("</td>\n");
            sb.append("</tr>");
        }
        sb.append("</tbody>\n" + "</table>\n");
        return sb.toString();
    }

    public static String getTicketsForAdmin(List<Ticket> adminTicketList, String adminUserName) {
        StringBuilder sb = new StringBuilder();
        sb.append("<table class=\"table table-bordered\">\n" +
                "<thead>\n" +
                "<tr>\n" +
                "<th>ID</th>\n" +
                "<th>Username</th>\n" +
                "<th>Subject</th>\n" +
                "<th>Description</th>\n" +
                "<th>Priority Level</th>\n" +
                "<th>Progress</th>\n" +
                "<th>Developer Comment</th>\n" +
                "<th>Deadline</th>\n" +
                "<th>Ticket Date</th>\n" +
                "<th>Ticket Open</th>\n" +
                "<th>Update Ticket</th>\n" +
                "<th>Delete Ticket</th>\n" +
                "</tr>\n" +
                "</thead>\n" +
                "<tbody>\n");
        for (Ticket ticket : adminTicketList) {
            sb.append("<tr>");
            sb.append("<td>").append(ticket.getTicketId()).append("</td>\n");
            sb.append("<td>").append(ticket.getUsername()).append("</td>\n");
            sb.append("<td>").append(ticket.getSubject()).append("</td>\n");
            sb.append("<td>").append(ticket.getProjectOrder()).append("</td>\n");
            sb.append("<td>").append(ticket.getPriorityLevel()).append("</td>\n");
            sb.append("<td>").append(ticket.getProgress()).append("</td>\n");
            sb.append("<td>").append(ticket.getDevComment()).append("</td>\n");
            sb.append("<td>").append(ticket.getDeadline()).append("</td>\n");
            sb.append("<td>").append(ticket.getTicketDate()).append("</td>\n");
            sb.append("<td>").append(ticket.getTicketOpen()).append("</td>\n");
            sb.append("<td>" +
                    "<form method=\"post\" action=\"selectTicketForUpdate.action\">\n" +
                    "<input type=\"hidden\" name=\"ticketId\" value=\"").append(ticket.getTicketId()).append("\"/>\n" +
                    "<input type=\"hidden\" name=\"username\" value=\"").append(adminUserName).append("\"/>\n" +
                    "<input type=\"submit\" value=\"Update\" class=\"btn-submit btn-default-green\"/>\n" +
                    "</form>\n" +
                    "</td>\n");
            sb.append("<td>" +
                    "<form method=\"post\" action=\"deleteTicket.action\">\n" +
                    "<input type=\"hidden\" name=\"ticketId\" value=\"").append(ticket.getTicketId()).append("\"/>\n" +
                    "<input type=\"hidden\" name=\"username\" value=\"").append(adminUserName).append("\"/>\n" +
                    "<input type=\"submit\" value=\"Delete\" class=\"btn-submit btn-default-green\"/>\n" +
                    "</form>\n" +
                    "</td>\n");
            sb.append("</tr>");
        }
        sb.append("</tbody>\n" + "</table>\n");
        return sb.toString();
    }
}
