package com.gopiandcode.graphics.views;

import com.gopiandcode.graphics.components.DisplayTextAction;
import com.gopiandcode.graphics.models.DocumentModel;

import javax.swing.*;
import java.awt.*;

public class DocumentView extends JPanel {
    private JTextField title;
    private JTextArea taster;
    private JLabel titleLabel;
    private JLabel tasterLabel;

    public DocumentView(DocumentModel model) {
        setBorder(BorderFactory.createTitledBorder("Document"));
        title = new JTextField();
        title.setDocument(model.getTitleModel());
        taster = new JTextArea(model.getTasterModel());

        title.setMaximumSize(title.getPreferredSize());
        taster.setMaximumSize(taster.getPreferredSize());

        titleLabel = new JLabel("Title:");
        titleLabel.setLabelFor(title);


        tasterLabel = new JLabel("Taster");
        tasterLabel.setLabelFor(taster);

        setLayout(new GridBagLayout());
        GridBagConstraints bagConstraints = new GridBagConstraints();

        bagConstraints.fill = GridBagConstraints.NONE;
        bagConstraints.weightx = 0.2;
        bagConstraints.gridwidth = 1;
        bagConstraints.gridheight = 1;
        bagConstraints.gridx = 1;
        bagConstraints.gridy = 0;

//        JPanel titlePanel = new JPanel();
//        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.LINE_AXIS));
        add(titleLabel, bagConstraints);

        bagConstraints.fill = GridBagConstraints.BOTH;
        bagConstraints.weightx = 1.0;
        bagConstraints.gridwidth = 100;
        bagConstraints.gridheight = 1;
        bagConstraints.gridx = 2;
        bagConstraints.gridy = 0;

        add(title, bagConstraints);
//        add(titlePanel);

//        JPanel tasterPanel = new JPanel();
//        tasterPanel.setLayout(new BoxLayout(tasterPanel,BoxLayout.LINE_AXIS));

        bagConstraints.fill = GridBagConstraints.NONE;
        bagConstraints.weightx = 0.2;
        bagConstraints.gridwidth = 1;
        bagConstraints.gridheight = 1;
        bagConstraints.gridx = 1;
        bagConstraints.gridy = 1;

        add(tasterLabel, bagConstraints);

        bagConstraints.fill = GridBagConstraints.BOTH;
        bagConstraints.weightx = 1.0;
        bagConstraints.weighty = 1.0;
        bagConstraints.gridwidth = 100;
        bagConstraints.gridheight = 2;
        bagConstraints.gridx = 2;
        bagConstraints.gridy = 1;

        add(new JScrollPane(taster), bagConstraints);
//        add(tasterPanel);


         bagConstraints.fill = GridBagConstraints.BOTH;
        bagConstraints.weightx = 0.5;
        bagConstraints.weighty = 0.01;
        bagConstraints.gridwidth = 102;
        bagConstraints.gridheight = 1;
        bagConstraints.gridx = 0;
        bagConstraints.gridy = 3;
        bagConstraints.ipadx = 1;
        bagConstraints.ipady = 1;

        JButton button = new JButton(new DisplayTextAction("Generated Document", () -> model.getDocument().toString() ));
       add(button, bagConstraints);


    }

}
