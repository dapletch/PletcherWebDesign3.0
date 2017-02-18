<%--
  Created by IntelliJ IDEA.
  User: Seth
  Date: 2/7/2017
  Time: 7:06 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Pletcher Web Design - Client Login</title>
</head>
<body>
<c:set var="userName" value="${username}"/>
<c:set var="clientTickets" value="${userTickets}"/>
<c:set var="error" value="${errorMessage}"/>

<div class="container-fluid">
    <div class="row">
        <div class="col-lg-12">
            <h1>Welcome to the Client Login, <c:out value="${userName}"/></h1>
            <p>This is where having an account through Pletcher Web Design really comes in handy. It is on this page you
                can submit
                and view the progress of current tickets that you have submitted.
                Please be as descriptive as you can on the ticket as possible. The more information we have the better
                we can help
                you. Another factor that is critical to the success of any project is by setting realistic deadlines. At
                Pletcher Web
                Design we strive to help you achieve your goals by meeting said deadlines.
                If you have any questions that you think would need further clarification please feel to email us at <a
                        href="mailto:pletcherwebdesign@gmail.com">pletcherwebdesign@gmail.com</a></p>

            <table class="table table-borderless">
                <thead>
                <tr>
                    <th>Ticket Priority</th>
                    <th>Ticket Priority Description</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <th>Routine Maintenance</th>
                    <td>Maintenance in the form of adding or removing content from said website, or just upgrading the
                        server the web application is currently on.
                    </td>
                </tr>
                <tr>
                    <th>High</th>
                    <td>The website has either come offline, or is inaccessible from public viewing. A feature that was
                        once working is no longer functioning properly. The business will lose revenue or credibility.
                    </td>
                </tr>
                <tr>
                    <th>Medium</th>
                    <td>There is some new functionality that should be added due to the business model changing. The
                        problem is not critical, but the problem needs to be handled eventually.
                    </td>
                </tr>
                <tr>
                    <th>Low</th>
                    <td>A new feature needs to be added, but is not critical to the business. Something that is just
                        nice to have.
                    </td>
                </tr>
                </tbody>
            </table>

            <form method="post" action="ticket.action">
                <input type="hidden" name="ticket.username" value="${userName}"/>
                <div class="form-group">
                    <label for="subject">Subject:</label>
                    <input type="text" class="form-control" placeholder="Subject" name="ticket.subject" id="subject">
                </div>
                <div class="form-group">
                    <label for="description">Description:</label>
                    <textarea class="form-control" rows="5" name="ticket.projectOrder" id="description"></textarea>
                </div>
                <div class="form-group">
                    <label for="priorityLevel">Priority Level:</label>
                    <select class="form-control" name="ticket.priorityLevel" id="priorityLevel">
                        <option value="Maintenance">Routine Maintenance</option>
                        <option value="High">High</option>
                        <option value="Medium">Medium</option>
                        <option value="Low">Low</option>
                    </select>
                </div>
                <input type="hidden" name="ticket.progress" value="Waiting for Review."/>
                <input type="hidden" name="ticket.devComment" value="Will Start Promptly."/>
                <div class="form-group">
                    <label for="deadline">Deadline:</label>
                    <input type="date" class="form-control" placeholder="Subject" name="ticket.deadline" id="deadline">
                </div>
                <div class="form-group">
                    <button type="submit" value="Submit" class="btn-submit btn-default-submit">Submit Ticket</button>
                </div>
            </form>
            <c:out value="${clientTickets}" escapeXml="false"/>
        </div>
    </div>
</div>

</body>
</html>