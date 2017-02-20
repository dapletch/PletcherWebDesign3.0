/**
 * Created by Seth on 2/20/2017.
 */
function validateContactForm() {

    var contact = {
        name: document.forms["contactForm"]["contact.name"].value,
        email: document.forms["contactForm"]["contact.email"].value,
        message: document.forms["contactForm"]["contact.message"].value,
    };

    if (contact["name"] === "" || contact["name"] === null) {
        console.log("Name: " + contact["name"]);
        document.getElementById("contactError").innerHTML = '<div class="alert alert-danger"><strong>Error! </strong>You must provide a name.</div>';
        return false;
    } else if (contact["email"] === "" || contact["email"] === null) {
        console.log("Email: " + contact["email"]);
        document.getElementById("contactError").innerHTML = '<div class="alert alert-danger"><strong>Error! </strong>You must provide an email.</div>';
        return false;
    } else if (!isEmailValid(contact["email"])) {
        console.log("Email: " + contact["email"]);
        document.getElementById("contactError").innerHTML = '<div class="alert alert-danger"><strong>Error! </strong>The email you entered is not valid.</div>';
        return false;
    } else if (contact["message"] === "" || contact["message"] === null) {
        console.log("Message: " + contact["message"]);
        document.getElementById("contactError").innerHTML = '<div class="alert alert-danger"><strong>Error! </strong>You must enter a message.</div>';
        return false;
    }
}