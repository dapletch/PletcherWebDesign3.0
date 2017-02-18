package com.pletcherwebdesign.beans.interfaces;

import com.pletcherwebdesign.email.beans.MessageBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Seth on 2/7/2017.
 */
public interface FormSubmission {

    void sendNotificationEmail(String formSubmission, String emailMessage);

    MessageBody getMessageBodyForm(String formSubmission, String emailMessage);

    String getTicketsForAdmin(String adminUsername);

    String getTicketListForClient(String clientUsername);

    Boolean isCaptchaValid(HttpServletRequest request);
}
