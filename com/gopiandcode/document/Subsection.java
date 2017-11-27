package com.gopiandcode.document;


import java.util.ArrayList;

public class Subsection {
    private String title;
    private ArrayList<Entry> entries;

    public Subsection() {
        this("", new ArrayList<>());
    }
    public Subsection(String title, ArrayList<Entry> entries) {
        this.title = title;
        this.entries = entries;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<Entry> getEntries() {
        return entries;
    }

    public void setEntries(ArrayList<Entry> entries) {
        this.entries = entries;
    }
}
