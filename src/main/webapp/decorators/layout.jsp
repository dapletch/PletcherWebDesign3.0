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
    <script src="../js/recaptcha/api.js"></script>
    <script src="../js/formvalidation/validateFormUtils.js"></script>
    <script src="../js/formvalidation/validateSignUp.js"></script>
    <script src="../js/formvalidation/validateReview.js"></script>
    <script src="../js/formvalidation/validateInfoSession.js"></script>
    <script src="../js/formvalidation/validateContact.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
    <link rel="shortcut icon" href="../favicon.ico"/>
    <script>
        (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
                (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
            m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
        })(window,document,'script','https://www.google-analytics.com/analytics.js','ga');

        ga('create', 'UA-92507207-1', 'auto');
        ga('send', 'pageview');

    </script>
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
    <div class="col-lg-9">
        <decorator:body/>
    </div>
    <%-- End of Body Container --%>
</div>
<%@ include file="../includes/footer.jsp" %>
</body>
</html>