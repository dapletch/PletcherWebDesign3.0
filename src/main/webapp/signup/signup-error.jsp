<%--
  Created by IntelliJ IDEA.
  User: Seth
  Date: 2/4/2017
  Time: 2:45 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<c:set var="error" value="${errorMessage}" />
<c:out value="${error}" escapeXml="falss" />

</body>
</html>
