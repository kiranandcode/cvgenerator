package com.gopiandcode.graphics.models;

import com.gopiandcode.document.Entry;

import javax.swing.*;
import javax.swing.event.ListDataListener;
import java.util.ArrayList;

public class EntryListModel extends AbstractListModel<Entry> implements ListModel<Entry>{
    private ArrayList<Entry> entries;

    public EntryListModel(ArrayList<Entry> entries) {
        this.entries = entries;
    }

    @Override
    public int getSize() {
        return entries.size();
    }

    @Override
    public Entry getElementAt(int index) {
        return entries.get(index);
    }


    public void addElement(Entry entry) {
        this.entries.add(entry);
        fireIntervalAdded(this, this.entries.size()-1, this.entries.size()-1);
    }

    public void removeElementAt(int selectedIndex) {
        this.entries.remove(selectedIndex);
        fireIntervalRemoved(this,  selectedIndex, selectedIndex);
    }

    public void notifyEntryChanged(int index) {
        fireContentsChanged(this, index, index);
    }

}
