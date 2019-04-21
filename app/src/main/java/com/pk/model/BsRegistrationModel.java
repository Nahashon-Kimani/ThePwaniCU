package com.pk.model;

public class BsRegistrationModel {
    String semester, year, link, inputDate;

    public BsRegistrationModel() {
    }

    public BsRegistrationModel(String semester, String year, String link, String inputDate) {
        this.semester = semester;
        this.year = year;
        this.link = link;
        this.inputDate = inputDate;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getInputDate() {
        return inputDate;
    }

    public void setInputDate(String inputDate) {
        this.inputDate = inputDate;
    }
}
