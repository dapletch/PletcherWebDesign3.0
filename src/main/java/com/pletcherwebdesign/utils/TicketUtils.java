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
                "<th>Progress</th>\n" +
                "<th>Developer Comment</th>\n" +
                "<th>Deadline</th>\n" +
                "<th>Ticket Date</th>\n" +
                "</tr>\n" +
                "</thead>\n" +
                "<tbody>\n");
        for (Ticket ticket : userTicketsList) {
            sb.append("<tr>");
            sb.append("<td>").append(ticket.getTicketId()).append("</td>\n");
            sb.append("<td>").append(ticket.getSubject()).append("</td>\n");
            sb.append("<td>").append(ticket.getProjectOrder()).append("</td>\n");
            sb.append("<td>").append(ticket.getProgress()).append("</td>\n");
            sb.append("<td>").append(ticket.getDevComment()).append("</td>\n");
            sb.append("<td>").append(ticket.getDeadline()).append("</td>\n");
            sb.append("<td>").append(ticket.getTicketDate()).append("</td>\n");
            sb.append("</tr>");
        }
        sb.append("</tbody>\n" + "</table>\n");
        return sb.toString();
    }
}
