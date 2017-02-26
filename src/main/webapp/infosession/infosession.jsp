<%--
  Created by IntelliJ IDEA.
  User: Seth
  Date: 2/6/2017
  Time: 6:21 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Pletcher Web Design - Informational Session</title>
    <meta charset="UTF-8">
    <meta name="description" content="Interested in getting a website created for your business?
    Then look no further, for Pletcher Web Design is for you!
    All you need to do is fill out the form below and you can sign up for a free consultation today.">
    <meta name="keywords" content="Brattleboro, Vermmont, Brattleboro VT, VT, Web Design, Visual Branding,
    Web Developer, Web Development, Free Consultation, Informational Session, Info Session, Info. Session">
    <meta name="author" content="Seth Pletcher">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>

<div class="container-fluid">
    <div class="row">
        <div class="col-xs-12">
            <h1>Informational Session</h1>
            <p>Sign up for an free Informational Session today.</p>

            <h3>How Does a Session Work?</h3>
            <p>The answer to that question is really quite simple. Just fill out the form, along with a brief
                description of what the desired project is, the best time to reach you, and you will receive a phone
                call, and or, email in the next five business days.</p>
            <p>Also feel free to email us at <a href="mailto:pletcherwebdesign@gmail.com">pletcherwebdesign@gmail.com</a></p>
            <p>All fields must be filled out in order, for the form to submit.</p>
        </div>
        <div class="col-xs-8">
            <form name="infoSessionForm" method="post" action="infoSession.action" onsubmit="return validateInfoSessionForm()">
                <div id="infoSessionError"></div>
                <div class="form-group">
                    <label for="firstName">First Name:</label>
                    <input type="text" class="form-control" placeholder="First Name" name="infoSession.firstName"
                           id="firstName">
                </div>
                <div class="form-group">
                    <label for="lastName">Last Name:</label>
                    <input type="text" class="form-control" placeholder="Last Name" name="infoSession.lastName"
                           id="lastName">
                </div>
                <div class="form-group">
                    <label for="email">Email:</label>
                    <input type="email" class="form-control" placeholder="Email" name="infoSession.email" id="email">
                </div>
                <div class="form-group">
                    <label for="phoneNumber">Phone Number (###-###-####):</label>
                    <input type="text" class="form-control" placeholder="Phone Number" name="infoSession.phoneNumber"
                           id="phoneNumber">
                </div>
                <div class="form-group">
                    <label for="bestTime">Best Time to Reach You:</label>
                    <select class="form-control" name="infoSession.bestTime" id="bestTime">
                        <option value="-1">Please Select A Time</option>
                        <option value="0">12:00 am</option>
                        <option value="1">1:00 am</option>
                        <option value="2">2:00 am</option>
                        <option value="3">3:00 am</option>
                        <option value="4">4:00 am</option>
                        <option value="5">5:00 am</option>
                        <option value="6">6:00 am</option>
                        <option value="7">7:00 am</option>
                        <option value="8">8:00 am</option>
                        <option value="9">9:00 am</option>
                        <option value="10">10:00 am</option>
                        <option value="11">11:00 am</option>
                        <option value="12">12:00 pm</option>
                        <option value="13">1:00 pm</option>
                        <option value="14">2:00 pm</option>
                        <option value="15">3:00 pm</option>
                        <option value="16">4:00 pm</option>
                        <option value="17">5:00 pm</option>
                        <option value="18">6:00 pm</option>
                        <option value="19">7:00 pm</option>
                        <option value="20">8:00 pm</option>
                        <option value="21">9:00 pm</option>
                        <option value="22">10:00 pm</option>
                        <option value="23">11:00 pm</option>
                    </select>
                </div>
                <div class="form-group">
                    <label for="description">Comment:</label>
                    <textarea class="form-control" rows="5" name="infoSession.description" id="description"></textarea>
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