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
<c:set var="userName" value="${login.username}" />
<h1>Welcome to the Client Login, <c:out value="${userName}" /></h1>

<p></p>

</body>
</html>
