/**
 * Created by Seth on 2/18/2017.
 */
function validateSignUpForm() {

    // Creating the signUp object from the form inputs
    var signUp = {
        fistName: document.forms["signUpForm"]["users.firstName"].value,
        lastName: document.forms["signUpForm"]["users.lastName"].value,
        email: document.forms["signUpForm"]["users.email"].value,
        username: document.forms["signUpForm"]["users.username"].value,
        password: document.forms["signUpForm"]["users.password"].value,
        checkPassword: document.forms["signUpForm"]["users.checkPassword"].value,
        streetAddress: document.forms["signUpForm"]["users.streetAddress"].value,
        city: document.forms["signUpForm"]["users.city"].value,
        state: document.forms["signUpForm"]["users.state"].value,
        zipCode: document.forms["signUpForm"]["users.zipCode"].value,
        phoneNumber: document.forms["signUpForm"]["users.phoneNumber"].value
    };

    // validate user's first name
    if (signUp["firstName"] === "" || signUp["firstName"] === null) {
        console.log("First Name: " + signUp["firstName"]);
        document.getElementById("signUpError").innerHTML = '<div class="alert alert-danger"><strong>Error! </strong>You must provide a first name.</div>';
        return false;
        // validate the user's last name
    } else if (signUp["lastName"] === "" || signUp["lastName"] === null) {
        console.log("Last Name: " + signUp["lastName"]);
        document.getElementById("signUpError").innerHTML = '<div class="alert alert-danger"><strong>Error! </strong>You must provide a last name.</div>';
        return false;
        // validate the email to see if it is empty or null
    } else if (signUp["email"] === "" || signUp["email"] === null) {
        console.log("Email: " + signUp["email"]);
        document.getElementById("signUpError").innerHTML = '<div class="alert alert-danger"><strong>Error! </strong>You must provide an email.</div>';
        return false;
        // validate that the email is a properly formatted email address
    } else if (!isEmailValid(signUp["email"])) {
        console.log("Email: " + signUp["email"]);
        document.getElementById("signUpError").innerHTML = '<div class="alert alert-danger"><strong>Error! </strong>The email entered is not a valid email.</div>';
        return false;
        // validate user's password
    } else if (signUp["username"] === "" || signUp["username"] === null) {
        console.log("Username: " + signUp["username"]);
        document.getElementById("signUpError").innerHTML = '<div class="alert alert-danger"><strong>Error! </strong>You must enter a username.</div>';
        return false;
        // validate the user's password
    } else if (signUp["password"] === "" || signUp["password"] === null) {
        console.log("Password: " + signUp["password"]);
        document.getElementById("signUpError").innerHTML = '<div class="alert alert-danger"><strong>Error! </strong>You must enter a password.</div>';
        return false;
        // validate the second password
    } else if (signUp["checkPassword"] === "" || signUp["checkPassword"] === null) {
        console.log("Check Password: " + signUp["checkPassword"]);
        document.getElementById("signUpError").innerHTML = '<div class="alert alert-danger"><strong>Error! </strong>You must validate your password.</div>';
        return false;
        // check if the passwords match
    } else if (!doPasswordsMatch(signUp["password"], signUp["checkPassword"])) {
        console.log("Password: " + signUp["password"] + "\n" + "CheckPassword: " + signUp["checkPassword"]);
        document.getElementById("signUpError").innerHTML = '<div class="alert alert-danger"><strong>Error! </strong>The passwords you have entered do not match.</div>';
        return false;
        // check to see if the user entered a street address
    } else if (signUp["streetAddress"] === "" || signUp["streetAddress"] === null) {
        console.log("Street Address: " + signUp["streetAddress"]);
        document.getElementById("signUpError").innerHTML = '<div class="alert alert-danger"><strong>Error! </strong>You must enter a street address.</div>';
        return false;
        // check to see if the user entered a city
    } else if (signUp["city"] === "" || signUp["city"] === null) {
        console.log("City: " + signUp["city"]);
        document.getElementById("signUpError").innerHTML = '<div class="alert alert-danger"><strong>Error! </strong>You must enter a city.</div>';
        return false;
        // check to see if user selected a state
    } else if (signUp["state"] === "" || signUp["state"] === null || signUp["state"] === "-1") {
        console.log("State: " + signUp["state"]);
        document.getElementById("signUpError").innerHTML = '<div class="alert alert-danger"><strong>Error! </strong>You must enter a state.</div>';
        return false;
    } else if (signUp["zipCode"] === "" || signUp["zipCode"] === null) {
        console.log("ZipCode: " + signUp["zipCode"]);
        document.getElementById("signUpError").innerHTML = '<div class="alert alert-danger"><strong>Error! </strong>You must enter a zipCode.</div>';
        return false;
    } else if (signUp["phoneNumber"] === "" || signUp["phoneNumber"] === null) {
        console.log("Phone Number: " + signUp["phoneNumber"]);
        document.getElementById("signUpError").innerHTML = '<div class="alert alert-danger"><strong>Error! </strong>You must enter a phone number.</div>';
        return false;
    } else if (!isPhoneNumberValid(signUp["phoneNumber"])) {
        console.log("Phone Number: " + signUp["phoneNumber"]);
        document.getElementById("signUpError").innerHTML = '<div class="alert alert-danger"><strong>Error! </strong>The phone number entered is not valid.</div>';
        return false;
    }
}