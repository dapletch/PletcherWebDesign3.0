<%--
  Created by IntelliJ IDEA.
  User: Seth
  Date: 2/7/2017
  Time: 1:51 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Pletcher Web Design - Contact Us</title>
</head>
<body>

<div class="container-fluid">
    <div class="row">
        <div class="col-xs-12">
            <h1>Contact Us</h1>
            <p>Do you have a question about the services we provide? We would be more than happy to answer any questions
                that you may have. Just fill out the form and we will contact you shortly.</p>
        </div>
        <div class="col-xs-8">
            <form method="post" action="contact.action">
                <div class="form-group">
                    <label for="name">Name:</label>
                    <input type="text" class="form-control" placeholder="Name" name="contact.name"
                           id="name">
                </div>
                <div class="form-group">
                    <label for="email">Email:</label>
                    <input type="email" class="form-control" placeholder="Email" name="contact.email" id="email">
                </div>
                <div class="form-group">
                    <label for="phoneNumber">Phone Number:</label>
                    <input type="text" class="form-control" placeholder="Phone Number" name="contact.phoneNumber"
                           id="phoneNumber">
                </div>
                <div class="form-group">
                    <label for="message">Comment:</label>
                    <textarea class="form-control" rows="5" name="contact.message" id="message"></textarea>
                </div>
                <div class="form-group">
                    <div class="g-recaptcha"
                         data-sitekey="6LeYChYUAAAAAKqoDwAuWPBVnV8bHJJCRkpfhJR3">
                    </div>
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
