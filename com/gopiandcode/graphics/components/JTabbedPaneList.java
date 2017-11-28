package com.gopiandcode.graphics.components;

import javax.swing.*;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class JTabbedPaneList<T> extends JTabbedPane {
    private ArrayList cache = new ArrayList();
    private ListModel<T> model;
    private ComponentGenerator generator;
    private final PropertyChangeListenerGenerator listenerGenerator;
    private final TabNameGenerator titleGenerator;

    public JTabbedPaneList(ListModel<T> model, ComponentGenerator<T> generator, PropertyChangeListenerGenerator<T> listenerGenerator, TabNameGenerator<T> titleGenerator) {
        this.model = model;
        this.generator = generator;
        this.listenerGenerator = listenerGenerator;
        this.titleGenerator = titleGenerator;

        model.addListDataListener(new TabbedPaneListDataListener<>(generator));
    }

    private class TabbedPaneListDataListener<T> implements ListDataListener {
        private ComponentGenerator<T> generator;
        private Map<T, Component> elements = new HashMap<>();

        public TabbedPaneListDataListener(ComponentGenerator<T> generator) {
            this.generator = generator;
        }

        @Override
        public void intervalAdded(ListDataEvent e) {
            ListModel<T> list = (ListModel<T>) e.getSource();

            for (int i = e.getIndex1(); i >= e.getIndex1(); i--) {
                T item = list.getElementAt(i);
                Component component = generator.renderModel(item);
                component.addPropertyChangeListener(listenerGenerator.generatePropertyChangeListener(JTabbedPaneList.this.model, i));
                cache.add(i, item);
                elements.put(item, component);
                String title = titleGenerator.generateName(item);
                if(title.isEmpty()){
                    title = "[empty entry]";
                }
                addTab(title, component);
            }
        }

        @Override
        public void intervalRemoved(ListDataEvent e) {
            ListModel<T> list = (ListModel<T>) e.getSource();
            for (int i = e.getIndex1(); i >= e.getIndex0(); i--) {
                System.out.println("Trying to delete index " + i);

                T elementAt = null;
                if (i < list.getSize()) {
                    System.out.println("bingo");

                    elementAt = list.getElementAt(i);
                } else if (i < cache.size()) {
                    System.out.println("bingo");
                    elementAt = (T) cache.get(i);
                    cache.remove(i);
                }
                if (elementAt != null) {
                    removeModelTab(elementAt);
                    elements.remove(elementAt);
                }
            }
        }

        private void removeModelTab(T elementAt) {
            Component c = elements.get(elementAt);
            for (int j = 0; j < getTabCount(); j++) {
                Component tabComponentAt = getComponentAt(j);
                if (tabComponentAt == c) {
                    removeTabAt(j);
                }
            }
        }

        @Override
        public void contentsChanged(ListDataEvent e) {
            ListModel<T> list = (ListModel<T>) e.getSource();
            for (int i = e.getIndex1(); i >= e.getIndex0(); i--) {
                // Remove existing tab

                T elementAt = null;
                if (i < list.getSize()) {
                    System.out.println("bingo");

                    elementAt = list.getElementAt(i);
                } else if (i < cache.size()) {
                    System.out.println("bingo");
                    elementAt = (T) cache.get(i);
                }
                if (elementAt != null) {
//                    removemodeltab(elementat);
//                    elements.remove(elementat);
//
//                    component container = generator.rendermodel(elementat);
//                    container.addpropertychangelistener(listenergenerator.generatepropertychangelistener(jtabbedpanelist.this.model, i));
//                    elements.put(elementat, container);
//                    addtab(titlegenerator.generatename(elementat), container);
                    setTitleAt(cache.indexOf(elementAt), titleGenerator.generateName(elementAt));
                }


            }
        }
    }
}
