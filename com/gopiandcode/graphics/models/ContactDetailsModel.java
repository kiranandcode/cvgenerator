package com.gopiandcode.graphics.models;

import com.gopiandcode.document.ContactDetails;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class ContactDetailsModel {
    private ContactDetails details;

    public ContactDetailsModel(ContactDetails details) {
        this.details = details;
    }

    public TableModel getTableModel() {
        return new DefaultTableModel();
    }
}
