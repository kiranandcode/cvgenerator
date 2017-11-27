package com.gopiandcode.graphics.models;

import com.gopiandcode.document.Entry;
import com.gopiandcode.document.Subsection;
import com.gopiandcode.graphics.components.FunctionalDocumentListener;
import com.gopiandcode.graphics.components.JTabbedPaneComponentGenerator;
import com.gopiandcode.graphics.views.EntryView;

import javax.swing.*;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;
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
        return (Entry model) ->  new EntryView(new EntryModel(model));
    }

    public ActionListener getAddEntryActionListener() {
        return e -> {
            this.entriesModel.addElement(new Entry());
        };
    }

    public ActionListener getRemoveEntryActionListener(JList<Entry> entryJList) {
        return e -> {
            int selectedIndex = entryJList.getSelectedIndex();
            if(selectedIndex != -1 && selectedIndex < entriesModel.getSize()){
                entriesModel.removeElementAt(selectedIndex);
            }
        };
    }
}
