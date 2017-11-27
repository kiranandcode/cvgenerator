package com.gopiandcode.document;

import java.util.ArrayList;
import java.util.Date;

public class Entry {
    private String date;
    private String location;
    private String title;
    private ArrayList<String> details;

    public Entry() {
        this("", "", "", new ArrayList<>());
    }

    public Entry(String title, String location, String date, ArrayList<String> details) {
        this.title = title;
        this.location = location;
        this.date = date;
        this.details = details;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<String> getDetails() {
        return details;
    }

    public void setDetails(ArrayList<String> details) {
        this.details = details;
    }

    @Override
    public String toString() {
        return "Entry{" +
                "date='" + date + '\'' +
                ", location='" + location + '\'' +
                ", title='" + title + '\'' +
                ", details=" + details +
                '}';
    }
}

