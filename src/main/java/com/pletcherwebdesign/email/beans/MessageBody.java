package com.pletcherwebdesign.email.beans;

import java.io.File;

/**
 * Created by Seth on 2/6/2017.
 */
public class MessageBody {

    private String recipient;
    private String subject;
    private String message;
    private File attachment;

    public MessageBody() {
        super();
    }

    public MessageBody(String recipient, String subject) {
        this.recipient = recipient;
        this.subject = subject;
    }

    public MessageBody(String recipient, String subject, String message) {
        this.recipient = recipient;
        this.subject = subject;
        this.message = message;
    }

    public MessageBody(String recipient, String subject, String message, File attachment) {
        this.recipient = recipient;
        this.subject = subject;
        this.message = message;
        this.attachment = attachment;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public File getAttachment() {
        return attachment;
    }

    public void setAttachment(File attachment) {
        this.attachment = attachment;
    }

    @Override
    public String toString() {
        return "MessageBody{" +
                "recipient='" + recipient + '\'' +
                ", subject='" + subject + '\'' +
                ", message='" + message + '\'' +
                ", attachment=" + attachment +
                '}';
    }
}
