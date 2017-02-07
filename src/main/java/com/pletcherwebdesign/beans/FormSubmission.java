package com.pletcherwebdesign.beans;

import com.pletcherwebdesign.email.beans.MessageBody;

/**
 * Created by Seth on 2/7/2017.
 */
public interface FormSubmission {

    public String formError();

    public void sendNotificationEmail();

    public MessageBody getMessageBodyForm();

    public String emailMessage();
}
