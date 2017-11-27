package com.gopiandcode.graphics.components;

import javax.swing.*;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class JTabbedPaneList<T extends Comparable<T>> extends JTabbedPane {
    private ListModel<T> model;
    private JTabbedPaneComponentGenerator<T> generator;

    public JTabbedPaneList(ListModel<T> model, JTabbedPaneComponentGenerator<T> generator){
        this.model = model;
        this.generator = generator;

        model.addListDataListener(new TabbedPaneListDataListener<>(generator));
    }

    private class TabbedPaneListDataListener<T extends Comparable<T>> implements ListDataListener {
        private JTabbedPaneComponentGenerator<T> generator;
        private java.util.List<Map.Entry<T, Component>> elements = new ArrayList<>();

        public TabbedPaneListDataListener(JTabbedPaneComponentGenerator<T> generator) {
            this.generator = generator;
        }

        @Override
        public void intervalAdded(ListDataEvent e) {
                ListModel list = (ListModel) e.getSource();

                for(int i = e.getIndex1(); i >= e.getIndex1(); i--){
                    System.out.println("i: "+i);
                    T item = (T) list.getElementAt(i);
                    Component component = generator.renderModel(item);
                    elements.add(i, new HashMap.SimpleEntry<>(item, component));
                    add(component);
               }
        }

        @Override
        public void intervalRemoved(ListDataEvent e) {
            for(int i = e.getIndex1(); i >= e.getIndex0(); i--){
                remove(i);
                elements.remove(i);
            }
        }

        @Override
        public void contentsChanged(ListDataEvent e) {
            ListModel list = (ListModel) e.getSource();
            for(int i = e.getIndex1(); i >= e.getIndex0(); i--){
                T item = (T) list.getElementAt(i);
                if(item.compareTo(elements.get(i).getKey()) != 0) {
                    Component container = generator.renderModel(item);
                    elements.set(i, new HashMap.SimpleEntry<>(item, container));
                    remove(i);
                    add(container,i);
                }
            }
        }
    }
}
