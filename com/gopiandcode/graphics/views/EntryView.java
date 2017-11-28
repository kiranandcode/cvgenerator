package com.gopiandcode.graphics.views;

import com.gopiandcode.graphics.components.DisplayTextAction;
import com.gopiandcode.graphics.models.EntryModel;

import javax.swing.*;
import javax.swing.event.SwingPropertyChangeSupport;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class EntryView extends JPanel {

    private final JLabel dateLabel;
    private final JTextField dateField;
    private final JLabel locationLabel;
    private final JTextField locationField;
    private final JLabel titleLabel;
    private final JTextField titleField;
    private final JTable detailsTable;
    private EntryModel model;
    private SwingPropertyChangeSupport support = new SwingPropertyChangeSupport(this, false);

    public EntryView(EntryModel model) {
        this.model = model;

        // TODO: Refactor this?
        // Necessary Evil as Component doesn't implement getModel() so
        // I have to propagate changes to the model via the view.
        this.model.addPropertyChangeListener(evt -> {
            support.firePropertyChange(evt.getPropertyName(), evt.getOldValue(), evt.getNewValue());
        });

        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        dateLabel = new JLabel("Date:");
        dateField = new JTextField();
        dateLabel.setLabelFor(dateField);
        dateField.setDocument(model.getDateModel());

        locationLabel = new JLabel("Location:");
        locationField = new JTextField();
        locationLabel.setLabelFor(locationField);
        locationField.setDocument(model.getLocationModel());


        titleLabel = new JLabel("Title:");
        titleField = new JTextField();
        titleLabel.setLabelFor(titleField);
        titleField.setDocument(model.getTitleModel());

        detailsTable = new JTable(model.getDetailsModel());


        constraints.fill = GridBagConstraints.NONE;
        constraints.weightx = 0.2;
        constraints.weighty = 0.01;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.gridx = 0;
        constraints.gridy = 0;
        add(titleLabel, constraints);

        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 1.0;
        constraints.weighty = 0.01;
        constraints.gridwidth = 4;
        constraints.gridheight = 1;
        constraints.gridx = 1;
        constraints.gridy = 0;
        add(titleField, constraints);



        constraints.fill = GridBagConstraints.NONE;
        constraints.weightx = 0.2;
        constraints.weighty = 0.01;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.gridx = 0;
        constraints.gridy = 1;
        add(dateLabel, constraints);

        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 1.0;
        constraints.weighty = 0.01;
        constraints.gridwidth = 4;
        constraints.gridheight = 1;
        constraints.gridx = 1;
        constraints.gridy = 1;
        add(dateField, constraints);


        constraints.fill = GridBagConstraints.NONE;
        constraints.weightx = 0.2;
        constraints.weighty = 0.01;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.gridx = 0;
        constraints.gridy = 2;
        add(locationLabel, constraints);

        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 1.0;
        constraints.weighty = 0.01;
        constraints.gridwidth = 4;
        constraints.gridheight = 1;
        constraints.gridx = 1;
        constraints.gridy = 2;
        add(locationField, constraints);



        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 0.8;
        constraints.weighty = 0.8;
        constraints.gridwidth = 5;
        constraints.gridheight = 5;
        constraints.gridx = 0;
        constraints.gridy = 3;
        JScrollPane detailsListField = new JScrollPane(detailsTable);

        JTextArea addTextArea = new JTextArea();
        JScrollPane addDetailField = new JScrollPane(addTextArea);

        JSplitPane pane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        pane.add(detailsListField);
        pane.add(addDetailField);

        add(pane, constraints);


        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 0.8;
        constraints.weighty = 0.02;
        constraints.gridwidth = 5;
        constraints.gridheight = 1;
        constraints.gridx = 0;
        constraints.gridy = 10;

        JButton addButton = new JButton("Add");
        addButton.addActionListener(model.getAddActionListener(detailsTable, addTextArea));
        add(addButton, constraints);

        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 0.8;
        constraints.weighty = 0.02;
        constraints.gridwidth = 5;
        constraints.gridheight = 1;
        constraints.gridx = 0;
        constraints.gridy = 11;

        JButton removeButton = new JButton("Remove");
        removeButton.addActionListener(model.getRemoveActionListener(detailsTable));
        add(removeButton, constraints);

        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 0.8;
        constraints.weighty = 0.02;
        constraints.gridwidth = 5;
        constraints.gridheight = 1;
        constraints.gridx = 0;
        constraints.gridy = 12;
        JButton generatedEntry = new JButton(new DisplayTextAction("Generated Entry", () -> this.model.getEntry().toString()));
        add(generatedEntry, constraints);


    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
       support.addPropertyChangeListener(listener);
    }
}
