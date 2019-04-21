package com.pk.model;

public class NoticeModel {
    String noticeTitle, noticeDate, noticeCategory, noticeInputDate, noticeDesc;

    public NoticeModel() {
    }

    public NoticeModel(String noticeTitle, String noticeDate, String noticeCategory, String noticeInputDate, String noticeDesc) {
        this.noticeTitle = noticeTitle;
        this.noticeDate = noticeDate;
        this.noticeCategory = noticeCategory;
        this.noticeInputDate = noticeInputDate;
        this.noticeDesc = noticeDesc;
    }

    public String getNoticeTitle() {
        return noticeTitle;
    }

    public void setNoticeTitle(String noticeTitle) {
        this.noticeTitle = noticeTitle;
    }

    public String getNoticeDate() {
        return noticeDate;
    }

    public void setNoticeDate(String noticeDate) {
        this.noticeDate = noticeDate;
    }

    public String getNoticeCategory() {
        return noticeCategory;
    }

    public void setNoticeCategory(String noticeCategory) {
        this.noticeCategory = noticeCategory;
    }

    public String getNoticeInputDate() {
        return noticeInputDate;
    }

    public void setNoticeInputDate(String noticeInputDate) {
        this.noticeInputDate = noticeInputDate;
    }

    public String getNoticeDesc() {
        return noticeDesc;
    }

    public void setNoticeDesc(String noticeDesc) {
        this.noticeDesc = noticeDesc;
    }
}
