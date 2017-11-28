package com.gopiandcode.graphics.models;

import com.gopiandcode.document.Subsection;
import com.gopiandcode.graphics.components.ComponentGenerator;
import com.gopiandcode.graphics.views.SubsectionView;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.SwingPropertyChangeSupport;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.*;
import java.util.List;

public class SubsectionListModel extends AbstractListModel<Subsection> implements ComboBoxModel<Subsection>, SingleSelectionModel {
    private Optional<Integer> selectedItem = Optional.empty();
    private List<Subsection> entries;
    private List<String> names = new ArrayList<>();

    private SwingPropertyChangeSupport support = new SwingPropertyChangeSupport(this, true);
    private HashMap<ChangeListener, PropertyChangeListener> listnerMap = new HashMap<>();

    public SubsectionListModel(List<Subsection> entries) {
        this.entries = entries;
    }

    @Override
    public void setSelectedItem(Object anItem) {
        findIndexOf((Subsection)anItem).ifPresent(integer -> {
            selectedItem = Optional.of(integer);
        });
    }

    private Optional<Integer> findIndexOf(Subsection anItem) {
        for(int i = 0; i < entries.size(); i++){
            if(entries.get(i) == anItem){
               return Optional.of(i);
            }
        }
        return Optional.empty();
    }

    @Override
    public Object getSelectedItem() {
        if(selectedItem.isPresent()) {
            return entries.get(selectedItem.get());
        } else {
            return null;
        }
    }

    @Override
    public int getSize() {
        return entries.size();
    }

    @Override
    public Subsection getElementAt(int index) {
        return entries.get(index);
    }



    public void addElement(Subsection subsection) {
        String name = subsection.toString();
        JPanel display = new SubsectionView(new SubsectionModel(subsection));
        display.setName(name);
        this.entries.add(subsection);
        fireIntervalAdded(this, 0, this.entries.size() - 1);
    }

    public void removeElementAt(int selectedIndex) {
        Subsection s = this.entries.get(selectedIndex);
        this.entries.remove(selectedIndex);

        fireIntervalRemoved(this, selectedIndex, selectedIndex);
    }

    public List<Subsection> getEntries() {
        return entries;
    }

    @Override
    public int getSelectedIndex() {
        if(selectedItem.isPresent()) {
            return selectedItem.get();
        } else {
            return -1;
        }
    }

    @Override
    public void setSelectedIndex(int index) {
        if(index < this.entries.size()){
            support.firePropertyChange(new PropertyChangeEvent(this, "selected", selectedItem, Optional.of(index)));
            selectedItem = Optional.of(index);
        }
    }

    @Override
    public void clearSelection() {
        support.firePropertyChange(new PropertyChangeEvent(this, "selected", selectedItem, Optional.empty()));
        selectedItem = Optional.empty();
    }

    @Override
    public boolean isSelected() {
        return selectedItem.isPresent();
    }

    @Override
    public void addChangeListener(ChangeListener listener) {
        PropertyChangeListener propertyChangeListener = evt -> listener.stateChanged(new ChangeEvent(evt.getSource()));
        listnerMap.put(listener, propertyChangeListener);
        support.addPropertyChangeListener(propertyChangeListener);
    }

    @Override
    public void removeChangeListener(ChangeListener listener) {
        PropertyChangeListener propertyChangeListener = listnerMap.get(listener);
        support.removePropertyChangeListener(propertyChangeListener);
        listnerMap.remove(listener);
    }


    public ItemListener getItemListener() {
        return e -> {
            System.out.println("Binging dinging: " + e.getItem().toString());
            support.firePropertyChange(new PropertyChangeEvent(this, "selected", "0", "1"));
        };
    }

     public ComponentGenerator<Subsection> getSubsectionComponentGenerator() {
        return modelItem -> {
            SubsectionModel model = new SubsectionModel(modelItem);
            model.addPropertyChangeListener(evt -> {
                System.out.println(evt);
                if(evt.getPropertyName() == "title") {
                    if(this.entries.size() > 0)
                        this.fireContentsChanged(this, 0, this.entries.size()-1);
                }
            });
            return new SubsectionView(model);
        };
    }


}


