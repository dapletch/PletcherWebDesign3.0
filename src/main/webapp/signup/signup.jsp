<%--
  Created by IntelliJ IDEA.
  User: Seth
  Date: 2/3/2017
  Time: 1:04 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Benefits of Having an Account</h1>
<p>By signing up for an account you can keep track of your change requests on our website. Being a client means you have a lot of power in terms of the design and development of your website.
    We do our best to make sure we are coding to fit your every one of specifications. This is also a great way to keep tabs on how your site is progressing as well.
    Signing up gives you the power to submit tickets which help us keep tabs on incoming work, while keeping up with specified deadlines.</p>

<p>All fields must be filled out, for the form to submit.</p>

<form>
    <div class="form-group">
        <label for="firstName">First Name:</label>
        <input type="text" class="form-control" placeholder="First Name" id="firstName">
    </div>
    <div class="form-group">
        <label for="lastName">Last Name:</label>
        <input type="text" class="form-control" placeholder="Last Name" id="lastName">
    </div>
    <div class="form-group">
        <label for="email">Email:</label>
        <input type="email" class="form-control" placeholder="Email" id="email">
    </div>
    <div class="form-group">
        <label for="username">Username:</label>
        <input type="text" class="form-control" placeholder="Username" id="username">
    </div>
    <div class="form-group">
        <label for="pwd">Password:</label>
        <input type="password" class="form-control" placeholder="Password" id="pwd">
    </div>
    <div class="form-group">
        <label for="verifyPwd">Verify Password:</label>
        <input type="password" class="form-control" placeholder="Verify Password" id="verifyPwd">
    </div>
    <div class="form-group">
        <label for="streetAddress">Street Address:</label>
        <input type="text" class="form-control" placeholder="Street Address" id="streetAddress">
    </div>
    <div class="form-group">
        <label for="city">City:</label>
        <input type="text" class="form-control" placeholder="City" id="city">
    </div>
    <div class="form-group">
        <label for="state">State:</label>
        <select class="form-control" id="state">
            <option value="-1">Please Select A State</option>
            <option value="AL">Alabama</option>
            <option value="AK">Alaska</option>
            <option value="AZ">Arizona</option>
            <option value="AR">Arkansas</option>
            <option value="CA">California</option>
            <option value="CO">Colorado</option>
            <option value="CT">Connecticut</option>
            <option value="DE">Delaware</option>
            <option value="DC">District Of Columbia</option>
            <option value="FL">Florida</option>
            <option value="GA">Georgia</option>
            <option value="HI">Hawaii</option>
            <option value="ID">Idaho</option>
            <option value="IL">Illinois</option>
            <option value="IN">Indiana</option>
            <option value="IA">Iowa</option>
            <option value="KS">Kansas</option>
            <option value="KY">Kentucky</option>
            <option value="LA">Louisiana</option>
            <option value="ME">Maine</option>
            <option value="MD">Maryland</option>
            <option value="MA">Massachusetts</option>
            <option value="MI">Michigan</option>
            <option value="MN">Minnesota</option>
            <option value="MS">Mississippi</option>
            <option value="MO">Missouri</option>
            <option value="MT">Montana</option>
            <option value="NE">Nebraska</option>
            <option value="NV">Nevada</option>
            <option value="NH">New Hampshire</option>
            <option value="NJ">New Jersey</option>
            <option value="NM">New Mexico</option>
            <option value="NY">New York</option>
            <option value="NC">North Carolina</option>
            <option value="ND">North Dakota</option>
            <option value="OH">Ohio</option>
            <option value="OK">Oklahoma</option>
            <option value="OR">Oregon</option>
            <option value="PA">Pennsylvania</option>
            <option value="RI">Rhode Island</option>
            <option value="SC">South Carolina</option>
            <option value="SD">South Dakota</option>
            <option value="TN">Tennessee</option>
            <option value="TX">Texas</option>
            <option value="UT">Utah</option>
            <option value="VT">Vermont</option>
            <option value="VA">Virginia</option>
            <option value="WA">Washington</option>
            <option value="WV">West Virginia</option>
            <option value="WI">Wisconsin</option>
            <option value="WY">Wyoming</option>
        </select>
    </div>
    <div class="form-group">
        <label for="zipCode">Zip Code:</label>
        <input type="text" class="form-control" placeholder="Zip Code" id="zipCode">
    </div>
    <div class="form-group">
        <label for="phoneNumber">Phone Number</label>
        <input type="text" class="form-control" placeholder="Phone Number" id="phoneNumber">
    </div>
    <button type="submit" class="btn btn-default">Submit</button>
</form>

</body>
</html>
