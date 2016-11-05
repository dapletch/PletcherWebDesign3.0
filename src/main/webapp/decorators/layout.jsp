<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Pletcher Web Design</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="../css/bootstrap.css">
    <script src="../jquery/1.12.4/jquery.min.js"></script>
    <script src="../js/bootstrap.min.js"></script>
    <decorator:head/>
</head>

<body>
<%@ include file="../includes/header.jsp" %>
<div class="container-fluid">
    <%-- Start for Container for the Side Navigation Bar --%>
    <div class="col-lg-3">
        <%@ include file="../includes/navigation.jsp" %>
    </div>
    <%-- End of Navigation for Side Navigation Bar --%>

    <%-- Start of Body Container --%>
    <decorator:body/>
    <%-- End of Body Container --%>
</div>
<%@ include file="../includes/footer.jsp" %>
</body>
</html>

