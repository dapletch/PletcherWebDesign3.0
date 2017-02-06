package com.pletcherwebdesign.utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

/**
 * Created by Seth on 2/5/2017.
 */
public class PletcherWebDesignUtils {

    private static Logger logger = LoggerFactory.getLogger(PletcherWebDesignUtils.class);

    public static Boolean isEmailAddressValid(String email) {
        Boolean isValid = false;
        try {
            InternetAddress internetAddress = new InternetAddress(email);
            internetAddress.validate();
            isValid = true;
        } catch (AddressException e) {
            logger.error("Something went wrong while validating the email address: \n" + e);
        }
        return isValid;
    }

    public static Boolean isPhoneNumberValid(String phoneNo) {
        //validate phone numbers of format "1234567890"
        if (phoneNo.matches("\\d{10}")) return true;
            //validating phone number with -, . or spaces
        else if(phoneNo.matches("\\d{3}[-\\.\\s]\\d{3}[-\\.\\s]\\d{4}")) return true;
            //validating phone number with extension length from 3 to 5
        else if(phoneNo.matches("\\d{3}-\\d{3}-\\d{4}\\s(x|(ext))\\d{3,5}")) return true;
            //validating phone number where area code is in braces ()
        else if(phoneNo.matches("\\(\\d{3}\\)-\\d{3}-\\d{4}")) return true;
            //return false if nothing matches the input
        else
            logger.error("Malformed phone number: ", phoneNo);
            return false;

    }
}