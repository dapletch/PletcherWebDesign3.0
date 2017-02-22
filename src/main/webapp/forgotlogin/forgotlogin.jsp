<%--
  Created by IntelliJ IDEA.
  User: Seth
  Date: 2/21/2017
  Time: 10:03 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Pletcher Web Design - Forgot Login</title>
</head>
<body>

<div class="container-fluid">
    <div class="row">
        <div class="col-xs-12">
            <h1>Forgot Your Login Credentials?</h1>
            <p>Do not worry! Here at Pletcher Web Design we are here to help. Please enter your the email address you used to
                register for an account and we will email the login credentials to you.</p>
        </div>
        <div class="col-xs-4">
            <form method="post" action="forgotLogin.action">
                <div class="form-group">
                    <label for="email">Name:</label>
                    <input type="text" class="form-control" placeholder="Email" name="email"
                           id="email">
                </div>
                <div class="form-group">
                    <button type="submit" value="Submit" class="btn-submit btn-default-submit">Submit</button>
                </div>
            </form>
        </div>
    </div>
</div>

</body>
</html>