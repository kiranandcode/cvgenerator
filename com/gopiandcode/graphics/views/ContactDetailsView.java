package com.gopiandcode.graphics.views;

import com.gopiandcode.graphics.components.DisplayTextAction;
import com.gopiandcode.graphics.models.ContactDetailsModel;

import javax.swing.*;
import java.awt.*;

public class ContactDetailsView extends JPanel {
    private ContactDetailsModel model;
    private final JTable table;
    private final JTextField typeField = new JTextField();
    private final JLabel typeLabel = new JLabel("Type:");
    private final JTextField valueField = new JTextField();
    private final JLabel valueLabel = new JLabel("Value:");
    private final JButton addButton = new JButton("Add");
    private final JButton removeButton = new JButton("Remove");

    public ContactDetailsView(ContactDetailsModel model) {
        this.model = model;
        this.table = new JTable(model.getTableModel());

        setBorder(BorderFactory.createTitledBorder("Contact Details"));
        setLayout(new GridBagLayout());

        GridBagConstraints bagConstraints = new GridBagConstraints();

        bagConstraints.fill = GridBagConstraints.BOTH;
        bagConstraints.weightx = 1.0;
        bagConstraints.weighty = 1.0;
        bagConstraints.gridwidth = 5;
        bagConstraints.gridheight = 5;
        bagConstraints.gridx = 0;
        bagConstraints.gridy = 0;

        add(new JScrollPane(this.table), bagConstraints);


        typeLabel.setLabelFor(typeField);

        bagConstraints.fill = GridBagConstraints.NONE;
        bagConstraints.weightx = 0.2;
        bagConstraints.weighty = 0.02;
        bagConstraints.gridwidth = 1;
        bagConstraints.gridheight = 1;
        bagConstraints.gridx = 0;
        bagConstraints.gridy = 5;
        add(typeLabel, bagConstraints);

        bagConstraints.fill = GridBagConstraints.BOTH;
        bagConstraints.weightx = 1.0;
        bagConstraints.weighty = 0.02;
        bagConstraints.gridwidth = 4;
        bagConstraints.gridheight = 1;
        bagConstraints.gridx = 1;
        bagConstraints.gridy = 5;
        add(typeField, bagConstraints);

        valueLabel.setLabelFor(valueField);


        bagConstraints.fill = GridBagConstraints.NONE;
        bagConstraints.weightx = 0.2;
        bagConstraints.weighty = 0.02;
        bagConstraints.gridwidth = 1;
        bagConstraints.gridheight = 1;
        bagConstraints.gridx = 0;
        bagConstraints.gridy = 6;
        add(valueLabel, bagConstraints);

        bagConstraints.fill = GridBagConstraints.BOTH;
        bagConstraints.weightx = 1.0;
        bagConstraints.weighty = 0.02;
        bagConstraints.gridwidth = 4;
        bagConstraints.gridheight = 1;
        bagConstraints.gridx = 1;
        bagConstraints.gridy = 6;
        add(valueField, bagConstraints);


        addButton.addActionListener(this.model.getAddActionListener(table, typeField, valueField));
        bagConstraints.fill = GridBagConstraints.BOTH;
        bagConstraints.weightx = 1.0;
        bagConstraints.weighty = 0.02;
        bagConstraints.gridwidth = 5;
        bagConstraints.gridheight = 1;
        bagConstraints.gridx = 0;
        bagConstraints.gridy = 7;
        add(addButton, bagConstraints);


        removeButton.addActionListener(this.model.getRemoveActionListener(this.table));
        bagConstraints.fill = GridBagConstraints.BOTH;
        bagConstraints.weightx = 1.0;
        bagConstraints.weighty = 0.02;
        bagConstraints.gridwidth = 5;
        bagConstraints.gridheight = 1;
        bagConstraints.gridx = 0;
        bagConstraints.gridy = 8;
        add(removeButton, bagConstraints);

        JButton displayButton = new JButton(new DisplayTextAction("Generated Details", () -> this.model.getDetails().toString()));
        bagConstraints.fill = GridBagConstraints.BOTH;
        bagConstraints.weightx = 1.0;
        bagConstraints.weighty = 0.02;
        bagConstraints.gridwidth = 5;
        bagConstraints.gridheight = 1;
        bagConstraints.gridx = 0;
        bagConstraints.gridy = 9;
//        add(displayButton, bagConstraints);



    }
}
