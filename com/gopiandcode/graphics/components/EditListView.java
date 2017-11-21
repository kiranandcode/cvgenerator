package com.gopiandcode.graphics.components;

import javax.swing.*;
import java.awt.*;

/**
 * Created by gopia on 21/11/2017.
 */
public class EditListView<T> {
    private JButton addButton;
    private JButton removeButton;
    private JLabel nameLabel;
    private JTextField newTextField;
    private JList<T> listComponent;

    public EditListView() {
        this("");
    }

    public EditListView(String name) {
        this.addButton = new JButton("Add");
        this.removeButton = new JButton("Remove");
        this.listComponent = new JList<>();
        System.out.println("Creating a name of " + name);
        nameLabel = new JLabel(name);
        newTextField = new JTextField("");
    }

    public void addToPanel(JPanel panel) {
        GridLayout layout = new GridLayout(0, 1);
        panel.setLayout(layout);

        panel.add(nameLabel);
        panel.add(this.newTextField);
        panel.add(this.listComponent);

        JPanel subPanel = new JPanel();
        subPanel.setLayout(new GridLayout(0,2));
        subPanel.add(this.addButton);
        subPanel.add(this.removeButton);

        panel.add(subPanel);

    }

    public JButton getAddButton(){
        return this.addButton;
    }

    public JButton getRemoveButton(){
        return this.removeButton;
    }

    public JList<T> getListComponent(){
        return this.listComponent;
    }

    public JTextField getNewTextField() {
        return this.newTextField;
    }

    public void connectController(EditListController<T> controller) {
        this.addButton.addActionListener(controller);
        this.removeButton.addActionListener(controller);
    }


    public void updateUI() {
        addButton.updateUI();
        removeButton.updateUI();
        nameLabel.updateUI();
        newTextField.updateUI();
        listComponent.updateUI();
    }
}
