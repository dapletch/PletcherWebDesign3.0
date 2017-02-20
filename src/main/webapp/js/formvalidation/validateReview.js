/**
 * Created by Seth on 2/20/2017.
 */
function validateReviewForm() {

    // Creating the review object from the form inputs
    var review = {
        firstName: document.forms["reviewForm"]["review.firstName"].value,
        lastName: document.forms["reviewForm"]["review.lastName"].value,
        email: document.forms["reviewForm"]["review.email"].value,
        comment: document.forms["reviewForm"]["review.comment"].value
    };

    // Checking the form input
    if (review["firstName"] === "" || review["firstName"] === null) {
        console.log("First Name: " + review["firstName"]);
        document.getElementById("reviewError").innerHTML = '<div class="alert alert-danger"><strong>Error! </strong>You must provide a first name.</div>';
        return false;
    } else if (review["lastName"] === "" || review["lastName"] === null) {
        console.log("Last Name: " + review["lastName"]);
        document.getElementById("reviewError").innerHTML = '<div class="alert alert-danger"><strong>Error! </strong>You must provide a last name.</div>';
        return false;
    } else if (review["email"] === "" || review["email"] === null) {
        console.log("Email: " + review["email"]);
        document.getElementById("reviewError").innerHTML = '<div class="alert alert-danger"><strong>Error! </strong>You must provide an email.</div>';
        return false;
        // validate that the email is a properly formatted email address
    } else if (!isEmailValid(review["email"])) {
        console.log("Email: " + review["email"]);
        document.getElementById("reviewError").innerHTML = '<div class="alert alert-danger"><strong>Error! </strong>The email entered is not a valid email.</div>';
        return false;
    } else if (review["comment"] === "" || review["comment"] === null || review["comment"].length < 145) {
        console.log("Comment: " + review["comment"]);
        document.getElementById("reviewError").innerHTML = '<div class="alert alert-danger"><strong>Error! </strong>You either did not enter a comment, or it was less than 145 characters.</div>';
        return false;
    }
}