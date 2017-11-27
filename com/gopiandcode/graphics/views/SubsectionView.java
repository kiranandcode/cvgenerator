package com.gopiandcode.graphics.views;

import com.gopiandcode.document.Entry;
import com.gopiandcode.graphics.components.JTabbedPaneList;
import com.gopiandcode.graphics.models.SubsectionModel;

import javax.swing.*;
import java.awt.*;

public class SubsectionView extends JPanel {

    private SubsectionModel model;

    public SubsectionView(SubsectionModel model) {
        this.model = model;
        JLabel titleLabel = new JLabel("Title:");
        JTextField titleField = new JTextField();
        titleField.setDocument(model.getTitleModel());
        titleLabel.setLabelFor(titleField);

        JTabbedPaneList<Entry> entryPane = new JTabbedPaneList<Entry>(model.getEntriesModel(), model.getEntryGenerator());

        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        constraints.fill = GridBagConstraints.NONE;
        constraints.weightx = 0.2;
        constraints.weighty = 0.01;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.gridx = 1;
        constraints.gridy = 0;
        add(titleLabel, constraints);

        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 1.0;
        constraints.weighty = 0.01;
        constraints.gridwidth = 3;
        constraints.gridheight = 1;
        constraints.gridx = 2;
        constraints.gridy = 0;
        add(titleField, constraints);

        JPanel editPanel = new JPanel();
        editPanel.setBorder(BorderFactory.createTitledBorder("Entries"));
        editPanel.setLayout(new GridBagLayout());
        {
            GridBagConstraints bagConstraints = new GridBagConstraints();
            bagConstraints.fill = GridBagConstraints.BOTH;
            bagConstraints.weightx = 1.0;
            bagConstraints.weighty = 0.8;
            bagConstraints.gridwidth = 3;
            bagConstraints.gridheight = 5;
            bagConstraints.gridx = 0;
            bagConstraints.gridy = 0;

            JList<Entry> entryJList = new JList<>(model.getEntriesModel());
            editPanel.add(new JScrollPane(entryJList), bagConstraints);
            JButton addEntryButton = new JButton("Add Entry");
            addEntryButton.addActionListener(model.getAddEntryActionListener());
            JPanel editButtons = new JPanel();
            editButtons.setLayout(new GridLayout(1, 2));
            editButtons.add(addEntryButton);
            JButton removeEntryButton = new JButton("Remove Entry");
            removeEntryButton.addActionListener(model.getRemoveEntryActionListener(entryJList));
            editButtons.add(removeEntryButton);

            bagConstraints.fill = GridBagConstraints.BOTH;
            bagConstraints.weightx = 1.0;
            bagConstraints.weighty = 0.02;
            bagConstraints.gridwidth = 3;
            bagConstraints.gridheight = 1;
            bagConstraints.gridx = 0;
            bagConstraints.gridy = 6;


            editPanel.add(editButtons, bagConstraints);
        }

        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 0.2;
        constraints.weighty = 0.5;
        constraints.gridwidth = 2;
        constraints.gridheight = 5;
        constraints.gridx = 0;
        constraints.gridy = 1;
        add(editPanel, constraints);


        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 1.0;
        constraints.weighty = 0.5;
        constraints.gridwidth = 3;
        constraints.gridheight = 5;
        constraints.gridx = 2;
        constraints.gridy = 1;
        add(entryPane, constraints);


    }

}
