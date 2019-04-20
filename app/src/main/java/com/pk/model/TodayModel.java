package com.pk.model;

public class TodayModel {
    private String tDate, tDayVerse, tVerseNarration, tThoughtOfDay, tPrayerOfDay;

    public TodayModel() {
    }

    public TodayModel(String tDate, String tDayVerse, String tVerseNarration, String tThoughtOfDay, String tPrayerOfDay) {
        this.tDate = tDate;
        this.tDayVerse = tDayVerse;
        this.tVerseNarration = tVerseNarration;
        this.tThoughtOfDay = tThoughtOfDay;
        this.tPrayerOfDay = tPrayerOfDay;
    }

    public String gettDate() {
        return tDate;
    }

    public void settDate(String tDate) {
        this.tDate = tDate;
    }

    public String gettDayVerse() {
        return tDayVerse;
    }

    public void settDayVerse(String tDayVerse) {
        this.tDayVerse = tDayVerse;
    }

    public String gettVerseNarration() {
        return tVerseNarration;
    }

    public void settVerseNarration(String tVerseNarration) {
        this.tVerseNarration = tVerseNarration;
    }

    public String gettThoughtOfDay() {
        return tThoughtOfDay;
    }

    public void settThoughtOfDay(String tThoughtOfDay) {
        this.tThoughtOfDay = tThoughtOfDay;
    }

    public String gettPrayerOfDay() {
        return tPrayerOfDay;
    }

    public void settPrayerOfDay(String tPrayerOfDay) {
        this.tPrayerOfDay = tPrayerOfDay;
    }
}
