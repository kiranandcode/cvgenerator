package com.gopiandcode.document;


import java.util.ArrayList;

public class Document {

    private String title;
    private String taster;

    private ContactDetails details;
    private ArrayList<Subsection> content;

    public Document() {
        title = "";
        taster = "";
        details = new ContactDetails();
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTaster() {
        return taster;
    }

    public void setTaster(String taster) {
        this.taster = taster;
    }

    public ContactDetails getDetails() {
        return details;
    }

    public void setDetails(ContactDetails details) {
        this.details = details;
    }

    public ArrayList<Subsection> getContent() {
        return content;
    }

    public void setContent(ArrayList<Subsection> content) {
        this.content = content;
    }


    @Override
    public String toString() {
        return "Document{" +
                "title='" + title + '\'' +
                ", taster='" + taster + '\'' +
                ", details=" + details +
                ", content=" + content +
                '}';
    }
}
