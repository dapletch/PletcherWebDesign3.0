<%--
  Created by IntelliJ IDEA.
  User: Seth
  Date: 2/21/2017
  Time: 10:04 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Pletcher Web Design - Forgot Login Success</title>
</head>
<body>

<c:set var="emailAddress" value="${email}"/>

<h1>An Email Has Been Sent to You</h1>

<p>Please check your email, <b><c:out value="${emailAddress}"/></b>, for your login credentials. If you are still having a difficult time
    logging in. Fill out our <a href="../contact/contact.jsp">contact form</a> and we will assist you shortly.</p>

</body>
</html>