package com.pk.model;

public class ThemeVerseModel {
    String verse, narration, semester, year, version;

    public ThemeVerseModel() {
    }

    public ThemeVerseModel(String verse, String narration, String semester, String year, String version) {
        this.verse = verse;
        this.narration = narration;
        this.semester = semester;
        this.year = year;
        this.version = version;
    }

    public String getVerse() {
        return verse;
    }

    public void setVerse(String verse) {
        this.verse = verse;
    }

    public String getNarration() {
        return narration;
    }

    public void setNarration(String narration) {
        this.narration = narration;
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

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
