/**
 * Created by Seth on 2/20/2017.
 */
function isEmailValid(email) {
    var atpos = email.indexOf("@");
    var dotpos = email.lastIndexOf(".");
    if (atpos < 1 || (dotpos - atpos < 2)) {
        return false;
    }
    return true;
}

function doPasswordsMatch(password, checkPassword) {
    return password === checkPassword;
}

function isPhoneNumberValid(phoneNumber) {
    console.log("Phone Number String Length: " + phoneNumber.length);
    if (phoneNumber.length >= 10 && phoneNumber.length <= 12) {
        return true;
    }
    return false;
}