package com.gopiandcode.graphics.components;

import javax.swing.*;
import javax.swing.text.TextAction;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Created by gopia on 27/11/2017.
 */
public class AboutAction extends TextAction {
    /**
     * Creates a new JTextAction object.
     */
    public AboutAction() {
        super("About this Application");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(null,
                "This application was developed by GopiandCode(Kiran Gopinathan) "  +
                "for producing custom CV elements. Supporting Reorderable components " +
                "saving and loading projects and exporting to latex " +
                "and all sorts of other fancy features. Enjoy!",
                 "About CVGenerator",
                JOptionPane.INFORMATION_MESSAGE);
    }
}
