package com.pletcherwebdesign.actions;

import com.opensymphony.xwork2.ActionSupport;
import com.pletcherwebdesign.beans.interfaces.FormRequirements;
import com.pletcherwebdesign.beans.InfoSession;
import com.pletcherwebdesign.dao.InfoSessionDao;
import com.pletcherwebdesign.jdbcproperties.JdbcConfiguration;
import com.pletcherwebdesign.utils.FormSubmissionUtils;
import com.pletcherwebdesign.utils.PletcherWebDesignUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.dao.DataAccessException;

/**
 * Created by Seth on 2/6/2017.
 */
public class SubmitInfoSession extends ActionSupport implements FormRequirements {

    private InfoSession infoSession = new InfoSession();
    private FormSubmissionUtils formSubmissionUtils = new FormSubmissionUtils();
    private String errorMessage;

    private Logger logger = LoggerFactory.getLogger(SubmitInfoSession.class);

    public String execute() {

        ApplicationContext context = new AnnotationConfigApplicationContext(JdbcConfiguration.class);
        InfoSessionDao infoSessionDao = context.getBean(InfoSessionDao.class);
        try {
            if (!isInfoSessionFormInputValid(infoSession)) {
                return ERROR;
            }
            infoSessionDao.insertInfoSessionRecord(infoSession);
            formSubmissionUtils.sendNotificationEmail("infosession", emailMessage());
        } catch (DataAccessException e) {
            logger.error("There was a problem submitting the info session record: \n" + e);
            setErrorMessage("<p>There was a problem submitting your request for an informational session. " +
                    "Please review the fields and <a href=\"../infosession/infosession.jsp\">try again</a>.</p>" +
                    formError());
            return ERROR;
        }
        return SUCCESS;
    }

    private Boolean isInfoSessionFormInputValid(InfoSession infoSession) {
        // Checking the email
        if (!PletcherWebDesignUtils.isEmailAddressValid(infoSession.getEmail())) {
            setErrorMessage("<p>The email, " + infoSession.getEmail() + ", you entered is not a valid email address. " +
                    "Please <a href=\"../infosession/infosession.jsp\">try again</a>.</p>");
            logger.error("Review email is not valid: " + infoSession.getEmail());
            return false;
        }
        // Checking the phone number
        if (!PletcherWebDesignUtils.isPhoneNumberValid(infoSession.getPhoneNumber())) {
            setErrorMessage("<p>The phone number, " + infoSession.getPhoneNumber() + ", you entered is not a valid phone number. " +
                    "Please <a href=\"../infosession/infosession.jsp\">try again</a>.</p>");
            logger.error("Review phone number is not valid: " + infoSession.getPhoneNumber());
            return false;
        }
        // Checking the meeting time to see if the user actually selected one
        if (infoSession.getBestTime() == -1) {
            setErrorMessage("<p>Please select a meeting time and <a href=\"../infosession/infosession.jsp\">try again</a>.</p>");
            return false;
        }
        return true;
    }

    public String formError() {
        return "<table class=\"table table-borderless\">\n" +
                "    <tr>\n" +
                "        <td align=\"left\" colspan=\"2\"><b>Informational Session Form Submission</b></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "        <td><b>First Name:</b></td>\n" +
                "        <td>" + infoSession.getFirstName() + "</td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "        <td><b>Last Name:</b></td>\n" +
                "        <td>" + infoSession.getLastName() + "</td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "        <td><b>Email:</b></td>\n" +
                "        <td>" + infoSession.getEmail() + "</td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "        <td><b>Phone Number:</b></td>\n" +
                "        <td>" + infoSession.getPhoneNumber() + "</td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "        <td><b>Best Time to Reach You:</b></td>\n" +
                "        <td>" + infoSession.getBestTime() + "</td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "        <td><b>Description:</b></td>\n" +
                "        <td>" + infoSession.getDescription() + "</td>\n" +
                "    </tr>\n" +
                "</table>";
    }

    public String emailMessage() {
        return "The following person has just signed up for FREE informational session through Pletcher Web Design: \n" +
                "First Name: " + infoSession.getFirstName() + "\n" +
                "Last Name: " + infoSession.getLastName() + "\n" +
                "Email: " + infoSession.getEmail() + "\n" +
                "Phone Number: " + infoSession.getPhoneNumber() + "\n" +
                "Best Time: " + infoSession.getBestTime() + "\n" +
                "Description: " + infoSession.getDescription();
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public InfoSession getInfoSession() {
        return infoSession;
    }

    public void setInfoSession(InfoSession infoSession) {
        this.infoSession = infoSession;
    }

}