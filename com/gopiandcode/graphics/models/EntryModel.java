package com.gopiandcode.graphics.models;

import com.gopiandcode.document.Entry;
import com.gopiandcode.graphics.components.FunctionalDocumentListener;

import javax.swing.*;
import javax.swing.table.TableModel;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;
import java.awt.event.ActionListener;

public class EntryModel {

    private final Document titleDocument;
    private final Document dateDocument;
    private final Document locationDocument;
    private final DetailsTableModel detailsTableModel;
    private Entry entry;

    public EntryModel(Entry entry) {
        this.entry = entry;
        this.titleDocument = new PlainDocument();
        this.titleDocument.addDocumentListener(new FunctionalDocumentListener((String s) -> this.entry.setTitle(s)));
        this.dateDocument = new PlainDocument();
        this.dateDocument.addDocumentListener(new FunctionalDocumentListener((String s) -> this.entry.setDate(s)));
        this.locationDocument = new PlainDocument();
        this.locationDocument.addDocumentListener(new FunctionalDocumentListener((String s) -> this.entry.setLocation(s)));
        this.detailsTableModel = new DetailsTableModel(this.entry.getDetails());
    }

    public Document getDateModel() {
        return this.dateDocument;
    }

    public Document getLocationModel() {
        return this.locationDocument;
    }

    public Document getTitleModel() {
        return this.titleDocument;
    }

    public TableModel getDetailsModel() {
        return detailsTableModel;
    }

    public Entry getEntry() {
        return entry;
    }

    public ActionListener getAddActionListener(JTable detailsTable, JTextArea addTextArea) {
        return (e -> {
            detailsTableModel.addRow(addTextArea.getText());
            addTextArea.setText("");
            detailsTable.updateUI();
        });
    }

    public ActionListener getRemoveActionListener(JTable detailsTable) {
        return e -> {
            int selectedRow = detailsTable.getSelectedRow();
            if (selectedRow != -1 && selectedRow < detailsTableModel.getRowCount()) {
                detailsTableModel.removeRow(selectedRow);
            }
            detailsTable.updateUI();
        };
    }
}
