package com.gopiandcode.graphics.controllers;

import com.gopiandcode.graphics.components.ComponentGenerator;
import com.gopiandcode.graphics.components.MultiComponentPanel;

import javax.swing.*;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import java.awt.*;
import java.awt.List;
import java.util.*;

public class MultiComponentPanelListListener<T> implements ListDataListener {

    private MultiComponentPanel<T> multiComponentPanel;
    private ComponentGenerator<T> generator;
    private java.util.List<Component> interal_list;
    private ListModel<T> itemModel;
    private HashMap<Component, T> mainComponent;
    private java.util.List<String> names;

    public MultiComponentPanelListListener(MultiComponentPanel<T> multiComponentPanel, ComponentGenerator<T> generator, java.util.List<Component> interal_list, ListModel<T> itemModel, HashMap<Component, T> mainComponent, java.util.List<String> names) {
        this.multiComponentPanel = multiComponentPanel;
        this.generator = generator;
        this.interal_list = interal_list;
        this.itemModel = itemModel;
        this.mainComponent = mainComponent;
        this.names = names;
    }

    @Override
    public void intervalAdded(ListDataEvent e) {
        System.out.println("Interval added " + e);
        for (int i = e.getIndex1(); i >= e.getIndex0(); i--) {
            Component generatedComponent = generator.renderModel(itemModel.getElementAt(i));

            String name = itemModel.getElementAt(i).toString();

            multiComponentPanel.add(generatedComponent, name);
            CardLayout layout = (CardLayout) multiComponentPanel.getLayout();
            layout.addLayoutComponent(generatedComponent, name);

            names.add(i, name);
            interal_list.add(i, generatedComponent);

            mainComponent.put(generatedComponent, itemModel.getElementAt(i));
        }
        multiComponentPanel.updateCurrentlySelected(itemModel.getSize());
    }

    @Override
    public void intervalRemoved(ListDataEvent e) {
        System.out.println("Interval removed " + e);
        for (int i = e.getIndex1(); i >= e.getIndex0(); i--) {
            Component component = interal_list.get(i);

            multiComponentPanel.remove(component);
            interal_list.remove(i);
            names.remove(i);

            mainComponent.remove(component);
        }
        multiComponentPanel.updateCurrentlySelected(itemModel.getSize());
    }

    @Override
    public void contentsChanged(ListDataEvent e) {
        System.out.println("Interval changed " + e);
        for (int i = e.getIndex1(); i >= e.getIndex0(); i--) {
            Component component = interal_list.get(i);
            if (mainComponent.get(component) != itemModel.getElementAt(i)) {
                interal_list.remove(i);
                names.remove(i);
                multiComponentPanel.remove(component);
                mainComponent.remove(component);

                Component newComponent = generator.renderModel(itemModel.getElementAt(i));
                String name = itemModel.getElementAt(i).toString();

                multiComponentPanel.add(newComponent, name);
//                CardLayout layout = (CardLayout) multiComponentPanel.getLayout();
//                layout.addLayoutComponent(newComponent, name);
                names.add(i, name);

                interal_list.add(i, newComponent);

                mainComponent.put(newComponent, itemModel.getElementAt(i));
            }
        }
        multiComponentPanel.updateCurrentlySelected(itemModel.getSize());
    }
}
