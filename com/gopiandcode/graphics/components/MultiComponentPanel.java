package com.gopiandcode.graphics.components;

import com.gopiandcode.graphics.controllers.MultiComponentPanelListListener;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import java.awt.*;
import java.util.*;

public class MultiComponentPanel<T> extends JPanel {
    private final ListModel<T> itemModel;
    private final SingleSelectionModel selectionModel;
    private final ComponentGenerator<T> generator;

    private HashMap<Component, T> mainComponent = new HashMap<>();
    private java.util.List<Component> interal_list = new ArrayList<>();
    private java.util.List<String> names = new ArrayList<>();

    private Optional<Integer> currently_selected = Optional.empty();
    private CardLayout cardLayout = new CardLayout();

    public MultiComponentPanel(ListModel<T> itemModel, ComponentGenerator<T> generator) {
        this(itemModel, new DefaultSingleSelectionModel(), generator);
    }

    public MultiComponentPanel(ListModel<T> itemModel, SingleSelectionModel selectionModel, ComponentGenerator<T> generator) {
        this.itemModel = itemModel;
        this.selectionModel = selectionModel;
        this.generator = generator;

        configureUI();
        initializeInternalItems();
        this.itemModel.addListDataListener(new MultiComponentPanelListListener<T>(this, generator, interal_list, itemModel, mainComponent, names));
        this.selectionModel.addChangeListener((ChangeEvent e) -> {
            currently_selected = Optional.of(selectionModel.getSelectedIndex());
            updateCurrentlySelected(itemModel.getSize());
        });
        updateCurrentlySelected(itemModel.getSize());
    }

    private void configureUI() {
        setLayout(cardLayout);

    }

    private void initializeInternalItems() {
        for (int i = 0; i < this.itemModel.getSize(); i++) {
            Component generatedComponent = generator.renderModel(itemModel.getElementAt(i));
            this.interal_list.add(generatedComponent);
            String name = itemModel.getElementAt(i).toString();
            System.out.println("Adding Component " + name);
            add(generatedComponent, name);
//            cardLayout.addLayoutComponent(generatedComponent, name);
            this.names.add(name);
            mainComponent.put(generatedComponent, itemModel.getElementAt(i));
        }

        JPanel jPanel = new JPanel();
        jPanel.setBackground(new Color(250, 250, 250));
        add(jPanel, "");
    }


    public void updateCurrentlySelected(int size) {
        System.out.println("Currently Selected is " +  selectionModel.getSelectedIndex() + ", and size is " + size);
        if (size != 0) {
            currently_selected.map(integer -> {
                if (integer >= size) {
                    return size - 1;
                } else {
                    return integer;
                }
            });
        } else {
            currently_selected = Optional.empty();
        }
        if(currently_selected.isPresent()){
            int i = currently_selected.get();
            System.out.println("Showing panel " + names.get(i));
            cardLayout.show(this, names.get(i));
        } else {
            cardLayout.show(this, "");
        }
        this.revalidate();
    }
}


