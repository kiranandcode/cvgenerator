package com.gopiandcode.graphics.components;

import javax.swing.*;
import javax.swing.text.TextAction;
import java.awt.event.ActionEvent;

/**
 * Created by gopia on 27/11/2017.
 */
public class DisplayTextAction extends TextAction {

    private final StringGenerator generator;
    private final String name;

    /**
     * Creates a new JTextAction object.
     *
     * @param name the name of the action
     */
    public DisplayTextAction(String name, StringGenerator generator) {
        super(name);
        this.name = name;
        this.generator = generator;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,
                 generator.generate_text(),
                name,
                JOptionPane.PLAIN_MESSAGE);

    }
}
