/**
 * Created by Seth on 2/20/2017.
 */
function validateInfoSessionForm() {

    // creating the infoSession object
    var infoSession = {
        firstName: document.forms["infoSessionForm"]["infoSession.firstName"].value,
        lastName: document.forms["infoSessionForm"]["infoSession.lastName"].value,
        email: document.forms["infoSessionForm"]["infoSession.email"].value,
        phoneNumber: document.forms["infoSessionForm"]["infoSession.phoneNumber"].value,
        bestTime: document.forms["infoSessionForm"]["infoSession.bestTime"].value,
        description: document.forms["infoSessionForm"]["infoSession.description"].value
    };

    if (infoSession["firstName"] === "" || infoSession["firstName"] === null) {
        console.log("First Name: " + infoSession["firstName"]);
        document.getElementById("infoSessionError").innerHTML = '<div class="alert alert-danger"><strong>Error! </strong>You must provide a first name.</div>';
        return false;
    } else if (infoSession["lastName"] === "" || infoSession["lastName"] === null) {
        console.log("Last Name: " + infoSession["lastName"]);
        document.getElementById("infoSessionError").innerHTML = '<div class="alert alert-danger"><strong>Error! </strong>You must provide a last name.</div>';
        return false;
    } else if (infoSession["email"] === "" || infoSession["email"] === null) {
        console.log("Email: " + infoSession["email"]);
        document.getElementById("infoSessionError").innerHTML = '<div class="alert alert-danger"><strong>Error! </strong>You must provide an email.</div>';
        return false;
        // validate that the email is a properly formatted email address
    } else if (!isEmailValid(infoSession["email"])) {
        console.log("Email: " + infoSession["email"]);
        document.getElementById("infoSessionError").innerHTML = '<div class="alert alert-danger"><strong>Error! </strong>The email entered is not a valid email.</div>';
        return false;
    } else if (infoSession["phoneNumber"] === "" || infoSession["phoneNumber"] === null) {
        console.log("Phone Number: " + infoSession["phoneNumber"]);
        document.getElementById("infoSessionError").innerHTML = '<div class="alert alert-danger"><strong>Error! </strong>You must enter a phone number.</div>';
        return false;
    } else if (!isPhoneNumberValid(infoSession["phoneNumber"])) {
        console.log("Phone Number: " + infoSession["phoneNumber"]);
        document.getElementById("infoSessionError").innerHTML = '<div class="alert alert-danger"><strong>Error! </strong>You must enter a phone number.</div>';
        return false;
    } else if (infoSession["bestTime"] === "" || infoSession["bestTime"] === null || infoSession["bestTime"] === "-1") {
        console.log("Best Time: " + infoSession["bestTime"]);
        document.getElementById("infoSessionError").innerHTML = '<div class="alert alert-danger"><strong>Error! </strong>You must select a best time.</div>';
        return false;
    } else if (infoSession["description"] === "" || infoSession["description"] === null || infoSession["description"].length < 500) {
        console.log("Description: " + infoSession["description"]);
        document.getElementById("infoSessionError").innerHTML = '<div class="alert alert-danger"><strong>Error! </strong>You either did not enter a description, or the description entered was less than 500 characters.</div>';
        return false;
    }
}