package com.gopiandcode.graphics;

import com.gopiandcode.document.Document;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;

public class NewProjectAction extends AbstractAction implements Action {
    private MainFrame mainFrame;

    public NewProjectAction(MainFrame mainFrame) {
        super("New Project");
        this.mainFrame = mainFrame;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        int i = JOptionPane.showConfirmDialog(mainFrame, "Are you sure you want to create a new project?", "Confirm new project", JOptionPane.YES_NO_OPTION);
        if(i == JOptionPane.YES_OPTION) {
            mainFrame.reset(new Document());
        }
    }
}
