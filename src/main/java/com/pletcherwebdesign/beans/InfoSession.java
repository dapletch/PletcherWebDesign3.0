package com.pletcherwebdesign.beans;

/**
 * Created by Seth on 2/6/2017.
 */
public class InfoSession {

    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private Integer bestTime;
    private String description;

    public InfoSession() {
        super();
    }

    public InfoSession(String firstName, String lastName, String email, String phoneNumber, Integer bestTime, String description) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.bestTime = bestTime;
        this.description = description;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public Integer getBestTime() {
        return bestTime;
    }

    public void setBestTime(Integer bestTime) {
        this.bestTime = bestTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "InfoSession{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", bestTime=" + bestTime +
                ", description='" + description + '\'' +
                '}';
    }
}
