package com.pk.model;

public class UserSignUpModel {
    String name, yearOfStudy, email, phoneNumber;

    public UserSignUpModel() {
    }

    public UserSignUpModel(String name, String yearOfStudy, String email, String phoneNumber) {
        this.name = name;
        this.yearOfStudy = yearOfStudy;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYearOfStudy() {
        return yearOfStudy;
    }

    public void setYearOfStudy(String yearOfStudy) {
        this.yearOfStudy = yearOfStudy;
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
}
