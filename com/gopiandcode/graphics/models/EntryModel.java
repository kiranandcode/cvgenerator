package com.gopiandcode.graphics.models;

import com.gopiandcode.document.Entry;
import com.gopiandcode.graphics.components.FunctionalDocumentListener;

import javax.swing.*;
import javax.swing.event.EventListenerList;
import javax.swing.event.SwingPropertyChangeSupport;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.plaf.basic.BasicListUI;
import javax.swing.table.TableModel;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class EntryModel {
    private final SwingPropertyChangeSupport support = new SwingPropertyChangeSupport(this,true);
    private final Document titleDocument;
    private final Document dateDocument;
    private final Document locationDocument;
    private final DetailsTableModel detailsTableModel;
    private Entry entry;

    public EntryModel(Entry entry) {
        this.entry = entry;

        this.titleDocument = new PlainDocument();

         try {
            this.titleDocument.insertString(0,this.entry.getTitle(), null );
        } catch (BadLocationException e) {
            e.printStackTrace();
        }


        this.titleDocument.addDocumentListener(new FunctionalDocumentListener((String s) -> this.entry.setTitle(s)));
        this.titleDocument.addDocumentListener(new FunctionalDocumentListener((String s) -> {
            this.support.firePropertyChange(new PropertyChangeEvent(this.titleDocument, "title",
                    this.entry.getTitle(), s));
        }));

        this.dateDocument = new PlainDocument();

        try {
            this.dateDocument.insertString(0,this.entry.getDate(), null );
        } catch (BadLocationException e) {
            e.printStackTrace();
        }


        this.dateDocument.addDocumentListener(new FunctionalDocumentListener((String s) -> this.entry.setDate(s)));
        this.dateDocument.addDocumentListener(new FunctionalDocumentListener((String s) -> {
            this.support.firePropertyChange(new PropertyChangeEvent(this.dateDocument, "date",
                    this.entry.getDate(), s));
        }));




        this.locationDocument = new PlainDocument();

        try {
            this.locationDocument.insertString(0,this.entry.getLocation(), null );
        } catch (BadLocationException e) {
            e.printStackTrace();
        }


        this.locationDocument.addDocumentListener(new FunctionalDocumentListener((String s) -> this.entry.setLocation(s)));
        this.locationDocument.addDocumentListener(new FunctionalDocumentListener((String s) -> {
            this.support.firePropertyChange(new PropertyChangeEvent(this.locationDocument, "location",
                    this.entry.getLocation(), s));
        }));

        this.detailsTableModel = new DetailsTableModel(this.entry.getDetails());
        this.detailsTableModel.addTableModelListener(e -> {
           this.support.firePropertyChange("details", this.entry.getDetails().size(), this.entry.getDetails());
        });
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



    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
