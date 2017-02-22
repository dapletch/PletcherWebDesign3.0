<%--
  Created by IntelliJ IDEA.
  User: Seth
  Date: 2/12/2017
  Time: 9:04 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Pletcher Web Design - Ticket Update</title>
</head>
<body>

<c:set var="userName" value="${username}"/>

<h1>Update Ticket Here, <c:out value="${userName}" /></h1>

<table class="table table-borderless">
    <thead>
    <tr>
        <th>Ticket Priority</th>
        <th>Ticket Priority Description</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <th>Routine Maintenance:</th>
        <td>Maintenance in the form of adding or removing content from said website, or just upgrading the
            server the web application is currently on.
        </td>
    </tr>
    <tr>
        <th>High:</th>
        <td>The website has either come offline, or is inaccessible from public viewing. A feature that was
            once working is no longer functioning properly. The business will lose revenue or credibility.
        </td>
    </tr>
    <tr>
        <th>Medium:</th>
        <td>There is some new functionality that should be added due to the business model changing. The
            problem is not critical, but the problem needs to be handled eventually.
        </td>
    </tr>
    <tr>
        <th>Low:</th>
        <td>A new feature needs to be added, but is not critical to the business. Something that is just
            nice to have.
        </td>
    </tr>
    </tbody>
</table>
<!-- creating divider between the ticket priority types and ticket update form itself -->
<hr>
<form method="post" action="updateTicket.action">
    <table class="table table-borderless">
        <tr>
            <th>ID:</th>
            <td>
                <c:out value="${ticket.ticketId}"/>
                <div class="form-group">
                    <input type="hidden" class="form-control" name="ticketAdmin" value="${userName}">
                    <input type="hidden" class="form-control" name="ticketId" value="${ticket.ticketId}">
                </div>
            </td>
        </tr>
        <tr>
            <th>Username:</th>
            <td>
                <c:out value="${ticket.username}"/>
                <div class="form-group">
                    <input type="hidden" class="form-control" name="username" value="${ticket.username}">
                </div>
            </td>
        </tr>
        <tr>
            <th>Subject:</th>
            <td>
                <c:out value="${ticket.subject}"/>
                <div class="form-group">
                    <input type="hidden" class="form-control" name="subject" value="${ticket.subject}">
                </div>
            </td>
        </tr>
        <tr>
            <th>Description:</th>
            <td>
                <c:out value="${ticket.projectOrder}"/>
                <div class="form-group">
                    <input type="hidden" class="form-control" name="projectOrder" value="${ticket.projectOrder}">
                </div>
            <td>
        </tr>
        <tr>
            <th>Priority Level:</th>
            <td>
                <div class="form-group">
                    <input type="text" class="form-control" name="priorityLevel"
                           placeholder="${ticket.priorityLevel}">
                </div>
            </td>
        </tr>
        <tr>
            <th>Progress:</th>
            <td>
                <div class="form-group">
                    <textarea rows="5" class="form-control" name="progress"
                              placeholder="${ticket.progress}"></textarea>
                </div>
            </td>
        </tr>
        <tr>
            <th>Developer Comment:</th>
            <td>
                <div class="form-group">
                    <textarea rows="5" class="form-control" name="devComment"
                              placeholder="${ticket.devComment}"></textarea>
                </div>
            </td>
        </tr>
        <tr>
            <th>Deadline:</th>
            <td>
                <c:out value="${ticket.deadline}"/>
                <div class="form-group">
                    <input type="hidden" class="form-control" name="deadline" value="${ticket.deadline}">
                </div>
            </td>
        </tr>
        <tr>
            <th>Ticket Date:</th>
            <td>
                <c:out value="${ticket.ticketDate}"/>
                <div class="form-group">
                    <input type="hidden" class="form-control" name="ticketDate" value="${ticket.ticketDate}">
                </div>
            </td>
        </tr>
        <tr>
            <th>Ticket Open (true/false):</th>
            <td>
                <div class="form-group">
                    <input type="text" class="form-control" name="ticketOpen" placeholder="${ticket.ticketOpen}">
                </div>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <div class="form-group">
                    <button type="submit" value="Submit" class="btn-submit btn-default-submit">Update</button>
                </div>
            </td>
        </tr>
    </table>
</form>

</body>
</html>