<%--
  Created by IntelliJ IDEA.
  User: Seth
  Date: 2/7/2017
  Time: 7:05 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Pletcher Web Design - Admin Login</title>
</head>
<body>

<c:set var="userName" value="${username}" />
<c:set var="error" value="${errorMessage}"/>
<c:set var="admnTickets" value="${adminTickets}"/>
<h1>Welcome to the Admin Page, <c:out value="${userName}" /></h1>

<c:out value="${error}" escapeXml="false"/>
<c:out value="${admnTickets}" escapeXml="false"/>

</body>
</html>