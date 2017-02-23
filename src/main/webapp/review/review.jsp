<%--
  Created by IntelliJ IDEA.
  User: Seth
  Date: 2/5/2017
  Time: 6:52 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Pletcher Web Design - Review</title>
    <meta charset="UTF-8">
    <meta name="description"
          content="Please leave a review to tell us how we did. We would love to hear your feedback,
          for life is a continuous process of life learning, and we want to learn how to better suit your needs.">
    <meta name="keywords" content="Brattleboro, Vermmont, Brattleboro VT, VT, Web Design, Visual Branding
          , Web Developer, Web Development, Review, Submit Review, Review Submission">
    <meta name="author" content="Seth Pletcher">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
<h1>Leave a Review</h1>
<p>Please leave your comment below to tell us how we did.</p>

<div class="container-fluid">
    <div class="row">
        <div class="col-xs-8">
            <form name="reviewForm" method="post" action="review.action" onsubmit="return validateReviewForm()">
                <div id="reviewError"></div>
                <div class="form-group">
                    <label for="firstName">First Name:</label>
                    <input type="text" class="form-control" placeholder="First Name" name="review.firstName"
                           id="firstName">
                </div>
                <div class="form-group">
                    <label for="lastName">Last Name:</label>
                    <input type="text" class="form-control" placeholder="Last Name" name="review.lastName"
                           id="lastName">
                </div>
                <div class="form-group">
                    <label for="email">Email:</label>
                    <input type="email" class="form-control" placeholder="Email" name="review.email" id="email">
                </div>
                <div class="form-group">
                    <label for="phoneNumber">Phone Number:</label>
                    <input type="text" class="form-control" placeholder="Phone Number" name="review.phoneNumber"
                           id="phoneNumber">
                </div>
                <div class="form-group">
                    <label for="comment">Comment:</label>
                    <textarea class="form-control" rows="5" name="review.comment" id="comment"></textarea>
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
        <div class="col-xs-4">
            <h2>How Did We Do?</h2>
            <p>Here at Pletcher Web Design we strive for excellence. We would like to hear what you have to say. Please
                fill in the form and tell us how we did.</p>
        </div>
    </div>
</div>

</body>
</html>