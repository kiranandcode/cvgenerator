package com.gopiandcode.document;


import java.util.ArrayList;

public class Document {

    private String title;
    private String taster;

    private ContactDetails details;
    private ArrayList<InformationList> content;

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

    public ArrayList<InformationList> getContent() {
        return content;
    }

    public void setContent(ArrayList<InformationList> content) {
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
