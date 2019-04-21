package com.pk.model;

public class SemProgramModel {
    String pTitle, pDesc, pDate, pInputDate;

    public SemProgramModel() {
    }

    public SemProgramModel(String pTitle, String pDesc, String pDate, String pInputDate) {
        this.pTitle = pTitle;
        this.pDesc = pDesc;
        this.pDate = pDate;
        this.pInputDate = pInputDate;
    }

    public String getpTitle() {
        return pTitle;
    }

    public void setpTitle(String pTitle) {
        this.pTitle = pTitle;
    }

    public String getpDesc() {
        return pDesc;
    }

    public void setpDesc(String pDesc) {
        this.pDesc = pDesc;
    }

    public String getpDate() {
        return pDate;
    }

    public void setpDate(String pDate) {
        this.pDate = pDate;
    }

    public String getpInputDate() {
        return pInputDate;
    }

    public void setpInputDate(String pInputDate) {
        this.pInputDate = pInputDate;
    }
}
