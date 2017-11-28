package com.gopiandcode.graphics.views;

import com.gopiandcode.document.Subsection;
import com.gopiandcode.graphics.components.SubsectionListCellRenderer;
import com.gopiandcode.graphics.models.SubsectionListModel;
import sun.java2d.windows.GDIRenderer;

import javax.swing.*;
import java.awt.*;

public class SubsectionListView extends JPanel {
        private SubsectionListModel model;

        public SubsectionListView(SubsectionListModel model) {
                this.model = model;

                setBorder(BorderFactory.createTitledBorder("Subsections"));
                setLayout(new GridBagLayout());

                JList<Subsection> subsectionList = new JList<>(model);

                JButton addButton = new JButton("Add Subsection");
                addButton.addActionListener(model.getAddSubsectionListener(subsectionList));
                JButton removeButton = new JButton("Remove Subsection");
                removeButton.addActionListener(model.getRemoveSubsectionListener(subsectionList));


                GridBagConstraints constraints = new GridBagConstraints();
                constraints.fill = GridBagConstraints.BOTH;
                constraints.weighty = 0.8;
                constraints.weightx = 1.0;
                constraints.gridwidth = 3;
                constraints.gridheight = 5;
                constraints.gridx = 1;
                constraints.gridy = 0;

                subsectionList.setCellRenderer(new SubsectionListCellRenderer());
                add(new JScrollPane(subsectionList), constraints);

                constraints.ipadx = 0;
                constraints.fill = GridBagConstraints.NONE;
                constraints.weighty = 0.02;
                constraints.weightx = 0.51;
                constraints.gridwidth = 5;
                constraints.gridheight = 1;
                constraints.gridx = 0;
                constraints.gridy = 6;
                add(addButton, constraints);

                constraints.fill = GridBagConstraints.NONE;
                constraints.weighty = 0.02;
                constraints.weightx = 0.51;
                constraints.gridwidth = 5;
                constraints.gridheight = 1;
                constraints.gridx = 0;
                constraints.gridy = 7;
                add(removeButton, constraints);

        }
}
