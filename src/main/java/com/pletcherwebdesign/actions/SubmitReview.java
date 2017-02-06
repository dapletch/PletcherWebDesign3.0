package com.pletcherwebdesign.actions;

import com.opensymphony.xwork2.ActionSupport;
import com.pletcherwebdesign.beans.Review;
import com.pletcherwebdesign.dao.ReviewDao;
import com.pletcherwebdesign.jdbcproperties.JdbcConfiguration;
import com.pletcherwebdesign.utils.PletcherWebDesignUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.dao.DataAccessException;

/**
 * Created by Seth on 2/5/2017.
 */
public class SubmitReview extends ActionSupport {

    private Review review = new Review();
    private String errorMessage;
    private Logger logger = LoggerFactory.getLogger(SubmitReview.class);

    public String execute() {

        ApplicationContext context = new AnnotationConfigApplicationContext(JdbcConfiguration.class);
        ReviewDao reviewDao = context.getBean(ReviewDao.class);

        try {
            if (!isReviewFormInputValid(review)) {
                return ERROR;
            }
        } catch (DataAccessException e) {
            logger.error("There was a problem submitting the review: \n", e);
            setErrorMessage("<p>There was a problem submitting your review. " +
                    "Please review the fields and <a href=\"../review/review.jsp\">try again</a>.</p>" +
                    reviewSubmitError());
            return ERROR;
        }
        reviewDao.insertReview(review);
        return SUCCESS;
    }

    private Boolean isReviewFormInputValid(Review review) {
        if (!PletcherWebDesignUtils.isEmailAddressValid(review.getEmail())) {
            setErrorMessage("<p>The email, " + review.getEmail() + ", you entered is not a valid email address. " +
                    "Please <a href=\"../review/review.jsp\">try again</a>.</p>");
            logger.error("Review email is not valid: " + review.getEmail());
            return false;
        }
        if (!PletcherWebDesignUtils.isPhoneNumberValid(review.getPhoneNumber())) {
            setErrorMessage("<p>The phone number, " + review.getEmail() + ", you entered is not a valid phone number. " +
                    "Please <a href=\"../review/review.jsp\">try again</a>.</p>");
            logger.error("Review phone numbe is not valid: " + review.getPhoneNumber());
            return false;
        }
        return true;
    }

    private String reviewSubmitError() {
        return "<table class=\"table table-borderless\">\n" +
                "    <tr>\n" +
                "        <td align=\"left\" colspan=\"2\"><b>User Credentials</b></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "        <td><b>First Name:</b></td>\n" +
                "        <td>" + review.getFirstName() + "</td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "        <td><b>Last Name:</b></td>\n" +
                "        <td>" + review.getLastName() + "</td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "        <td><b>Email:</b></td>\n" +
                "        <td>" + review.getEmail() + "</td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "        <td><b>Phone Number:</b></td>\n" +
                "        <td>" + review.getPhoneNumber() + "</td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "        <td><b>Phone Number:</b></td>\n" +
                "        <td>" + review.getComment() + "</td>\n" +
                "    </tr>\n" +
                "</table>";
    }

    public Review getReview() {
        return review;
    }

    public void setReview(Review review) {
        this.review = review;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
