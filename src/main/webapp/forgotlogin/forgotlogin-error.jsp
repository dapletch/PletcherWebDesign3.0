<%--
  Created by IntelliJ IDEA.
  User: Seth
  Date: 2/21/2017
  Time: 10:03 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Pletcher Web Design - Forgot Login Error</title>
</head>
<body>

<c:set var="error" value="${errorMessage}" />
<c:out value="${error}" escapeXml="false" />

</body>
</html>
