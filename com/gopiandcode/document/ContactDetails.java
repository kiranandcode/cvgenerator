package com.gopiandcode.document;


import com.gopiandcode.latex.Latexizable;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ContactDetails implements Latexizable {

    private ArrayList<Map.Entry<String, String>> contact_details;

    public ContactDetails() {
        this(new ArrayList<>());
    }

    public ContactDetails(ArrayList<Map.Entry<String, String>> contact_details) {
        this.contact_details = contact_details;
    }

    public ArrayList<Map.Entry<String, String>> getContact_details() {
        return contact_details;
    }

    public void setContact_details(ArrayList<Map.Entry<String, String>> contact_details) {
        this.contact_details = contact_details;
    }


    public void addDetails(int row, String name, String details) {
        this.contact_details.add(row, new AbstractMap.SimpleEntry<>(name, details));
    }

    public void addDetails(String name, String details) {
        this.contact_details.add(new AbstractMap.SimpleEntry<>(name, details));
    }


    public void setValueAt(int row, int col, String value) {
        Map.Entry<String, String> entry = this.contact_details.get(row);
        switch(col) {
            case 0:
                String old_value = entry.getValue();
                this.contact_details.remove(row);
                this.contact_details.add(row, new AbstractMap.SimpleEntry<String, String>(value, old_value));
                break;
            case 1:
                entry.setValue(value);
                break;
            default:
                throw new RuntimeException("Invalid range for column");
        }
    }

    public String getValueAt(int row, int col) {

        Map.Entry<String, String> entry = this.contact_details.get(row);
         switch(col) {
            case 0:
                return entry.getKey();
            case 1:
                return entry.getValue();
            default:
                throw new RuntimeException("Invalid range for column");
        }
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("ContactDetails{" +
                "contact_details=");

        for(Map.Entry<String, String> entry : this.contact_details) {
            result.append(entry.getKey() + ":" +  entry.getValue());
        }
       result.append('}');

       return result.toString();
    }

    @Override
    public String toLatex() {
        String startFormat =
                "\\begin{flushleft}\n" +
                "\\begin{itemize}";
        StringBuilder detailsFormatting = new StringBuilder();
        for(Map.Entry<String,String> s : contact_details) {
                detailsFormatting.append("\\item[] " + s.getKey() + ": " + s.getValue() );
        }

        String detailsFormat = detailsFormatting.toString();
        String endFormat = "\\end{itemize}\n" +
                "\\end{flushleft}";
        return startFormat + detailsFormat + endFormat;
    }
}
