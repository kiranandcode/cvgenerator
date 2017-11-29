package com.gopiandcode.graphics.models;

import com.gopiandcode.document.Entry;
import com.gopiandcode.document.Subsection;
import com.gopiandcode.graphics.components.*;
import com.gopiandcode.graphics.views.EntryView;

import javax.swing.*;
import javax.swing.event.SwingPropertyChangeSupport;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;
import javax.swing.text.StringContent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class SubsectionModel {
    private final Document titleModel;
    private final EntryListModel entriesModel;
    private Subsection subsection;
    private SwingPropertyChangeSupport support = new SwingPropertyChangeSupport(this, true);

    public SubsectionModel(Subsection subsection) {
        this.subsection = subsection;
        this.titleModel = new PlainDocument();

        try {
            this.titleModel.insertString(0,this.subsection.getTitle(), null );
        } catch (BadLocationException e) {
            e.printStackTrace();
        }

        titleModel.addDocumentListener(new FunctionalDocumentListener(subsection::setTitle));
        this.titleModel.addDocumentListener(new FunctionalDocumentListener((s) -> {
            support.firePropertyChange(new PropertyChangeEvent(this, "title", "", s));
        }));

        this.entriesModel = new EntryListModel(subsection.getEntries());
    }
    public Document getTitleModel() {
        return titleModel;
    }

    public ListModel<Entry> getEntriesModel() {
        return entriesModel;
    }

    public ComponentGenerator<Entry> getEntryGenerator() {
        return (Entry model) -> {
          return new EntryView(new EntryModel(model));
        };
    }

    public ActionListener getAddEntryActionListener(JList<Entry> entryJList, JTabbedPaneList<Entry> entryPane) {
        return (ActionEvent e) -> {
            this.entriesModel.addElement(new Entry());
        };
    }

    public ActionListener getRemoveEntryActionListener(JList<Entry> entryJList, JTabbedPaneList<Entry> entryPane) {
        return e -> {
            int selectedIndex = entryJList.getSelectedIndex();
            if(selectedIndex != -1 && selectedIndex < entriesModel.getSize()){
                entriesModel.removeElementAt(selectedIndex);
            }
        };
    }

    public PropertyChangeListenerGenerator<Entry> getEntryPropertyChangeListenerGenerator() {
        return (actual_model, index) -> {
                return e -> {
                    String propertyName = e.getPropertyName();
                    if (propertyName == "title" || propertyName == "date" || propertyName == "location" || propertyName == "details") {
                        EntryListModel actual = (EntryListModel) actual_model;
                        actual.notifyEntryChanged(index);
                    } else {
                    }
                };
            };
    }


    public void addPropertyChangeListener(PropertyChangeListener listener) {
        this.support.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        this.support.removePropertyChangeListener(listener);
    }


    public ListCellRenderer<? super Entry> getListCellRenderer() {
        return new EntryListCellRenderer(); //(ListCellRenderer<Entry>) (list, value, index, isSelected, cellHasFocus) -> new JLabel(value.getTitle() + ", " + value.getLocation() + " " + value.getDate() + "(" + value.getDetails().size() + ")");
    }
}

