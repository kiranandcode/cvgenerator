package com.gopiandcode.graphics.views;

import com.gopiandcode.document.Subsection;
import com.gopiandcode.graphics.components.ComponentGenerator;
import com.gopiandcode.graphics.components.MultiComponentPanel;
import com.gopiandcode.graphics.components.SubsectionListCellRenderer;
import com.gopiandcode.graphics.models.SubsectionListModel;
import com.gopiandcode.graphics.models.SubsectionModel;

import javax.swing.*;
import java.awt.*;

public class ModifySubsectionListView extends JPanel {
    private SubsectionListModel model;

    public ModifySubsectionListView(SubsectionListModel model){
        this.model = model;
        setLayout(new GridBagLayout());


        JComboBox<Subsection> comboBox = new JComboBox<>(model);
        comboBox.setRenderer(new SubsectionListCellRenderer());
        comboBox.addItemListener(model.getItemListener());

        ComponentGenerator<Subsection> componentGenerator = model.getSubsectionComponentGenerator();
        MultiComponentPanel<Subsection> componentPanel = new MultiComponentPanel<>(model, model, componentGenerator);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.NONE;
        constraints.weightx = 1.0;
        constraints.weighty = 0.01;
        constraints.gridwidth = 3;
        constraints.gridheight = 1;
        constraints.gridx = 1;
        constraints.gridy = 0;
        add(comboBox, constraints);

        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 0.2;
        constraints.weighty = 0.2;
        constraints.gridwidth = 5;
        constraints.gridheight = 5;
        constraints.gridx = 0;
        constraints.gridy = 1;

        add(componentPanel,constraints);
    }


}
