package com.pletcherwebdesign.actions;

import com.opensymphony.xwork2.ActionSupport;
import com.pletcherwebdesign.beans.Contact;
import com.pletcherwebdesign.beans.interfaces.FormRequirements;
import com.pletcherwebdesign.dao.ContactDao;
import com.pletcherwebdesign.jdbcproperties.JdbcConfiguration;
import com.pletcherwebdesign.utils.FormSubmissionUtils;
import com.pletcherwebdesign.utils.PletcherWebDesignUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.dao.DataAccessException;

/**
 * Created by Seth on 2/7/2017.
 */
public class ContactUs extends ActionSupport implements FormRequirements {

    private Contact contact = new Contact();
    private FormSubmissionUtils formSubmissionUtils = new FormSubmissionUtils();
    private String errorMessage;

    private Logger logger = LoggerFactory.getLogger(ContactUs.class);

    public String execute() {
        ApplicationContext context = new AnnotationConfigApplicationContext(JdbcConfiguration.class);
        ContactDao contactDao = context.getBean(ContactDao.class);

        try {
            if (!isContactFormInputValid(contact)) {
                return ERROR;
            }
            contactDao.insertContactRecordIntoDb(contact);
            formSubmissionUtils.sendNotificationEmail("contact", emailMessage());
        } catch (DataAccessException e) {
            logger.error("There was a problem submitting the contact record: \n" + e);
            setErrorMessage("<p>There was a problem submitting your inquiry. " +
                    "Please review the fields and <a href=\"../contact/contact.jsp\">try again</a>.</p>" +
                    formError());
            return ERROR;
        }
        return SUCCESS;
    }

    private Boolean isContactFormInputValid(Contact contact) {
        if (!PletcherWebDesignUtils.isEmailAddressValid(contact.getEmail())) {
            setErrorMessage("<p>The email, " + contact.getEmail() + ", you entered is not a valid email address. " +
                    "Please <a href=\"../contact/contact.jsp\">try again</a>.</p>");
            logger.error("Review email is not valid: " + contact.getEmail());
            return false;
        }
        if (contact.getName().equals("") || contact.getName() == null) {
            setErrorMessage("<p>The name, " + contact.getName() + ", you entered is not a valid name. " +
                    "Please <a href=\"../contact/contact.jsp\">try again</a>.</p>");
            logger.error("Name is not valid: " + contact.getName());
            return false;
        }
        if (contact.getMessage().equals("") || contact.getMessage() == null) {
            setErrorMessage("<p>You did not enter a valid message. " +
                    "Please <a href=\"../contact/contact.jsp\">try again</a>.</p>");
            return false;
        }
        return true;
    }

    public String formError() {
        return "<table class=\"table table-borderless\">\n" +
                "    <tr>\n" +
                "        <td align=\"left\" colspan=\"2\"><b>Contact Form Submission</b></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "        <td><b>Name:</b></td>\n" +
                "        <td>" + contact.getName() + "</td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "        <td><b>Email:</b></td>\n" +
                "        <td>" + contact.getEmail() + "</td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "        <td><b>Phone Number:</b></td>\n" +
                "        <td>" + contact.getPhoneNumber() + "</td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "        <td><b>Comment:</b></td>\n" +
                "        <td>" + contact.getMessage() + "</td>\n" +
                "    </tr>\n" +
                "</table>";
    }

    public String emailMessage() {
        return "The following person has submitted an inquiry for Pletcher Web Design: \n" +
                "Name: " + contact.getName() + "\n" +
                "Email: " + contact.getEmail() + "\n" +
                "Phone Number: " + contact.getPhoneNumber() + "\n" +
                "Message: " + contact.getMessage();
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}