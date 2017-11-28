package com.gopiandcode.graphics.models;

import com.gopiandcode.document.Entry;
import com.gopiandcode.document.Subsection;
import com.gopiandcode.graphics.components.FunctionalDocumentListener;
import com.gopiandcode.graphics.components.JTabbedPaneComponentGenerator;
import com.gopiandcode.graphics.components.JTabbedPaneList;
import com.gopiandcode.graphics.components.PropertyChangeListenerGenerator;
import com.gopiandcode.graphics.views.EntryView;

import javax.swing.*;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SubsectionModel {
    private final Document titleModel;
    private final EntryListModel entriesModel;
    private Subsection subsection;

    public SubsectionModel(Subsection subsection) {
        this.subsection = subsection;
        this.titleModel = new PlainDocument();
        titleModel.addDocumentListener(new FunctionalDocumentListener(subsection::setTitle));
        this.entriesModel = new EntryListModel(subsection.getEntries());
    }
    public Document getTitleModel() {
        return titleModel;
    }

    public ListModel<Entry> getEntriesModel() {
        return entriesModel;
    }

    public JTabbedPaneComponentGenerator<Entry> getEntryGenerator() {
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
                        System.out.println(e);
                    }
                };
            };
    }

    public ListCellRenderer<? super Entry> getListCellRenderer() {
        return (ListCellRenderer<Entry>) (list, value, index, isSelected, cellHasFocus) -> new JLabel(value.getTitle() + ", " + value.getLocation() + " " + value.getDate() + "(" + value.getDetails().size() + ")");
    }
}

