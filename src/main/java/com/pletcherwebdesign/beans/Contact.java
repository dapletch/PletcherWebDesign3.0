package com.pletcherwebdesign.beans;

import org.joda.time.DateTime;

import java.sql.Timestamp;

/**
 * Created by Seth on 2/7/2017.
 */
public class Contact {

    private String name;
    private String email;
    private String phoneNumber;
    private String message;
    private DateTime dateEntered;

    public Contact() {
        super();
    }

    public Contact(String name, String email, String phoneNumber, String message, DateTime dateEntered) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.message = message;
        this.dateEntered = dateEntered;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DateTime getDateEntered() {
        return dateEntered;
    }

    public void setDateEntered(DateTime dateEntered) {
        this.dateEntered = dateEntered;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", message='" + message + '\'' +
                ", dateEntered=" + dateEntered +
                '}';
    }
}
