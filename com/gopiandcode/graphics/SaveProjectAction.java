package com.gopiandcode.graphics;

import com.google.gson.Gson;
import com.gopiandcode.document.Document;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class SaveProjectAction extends AbstractAction implements  Action {
    private MainFrame mainFrame;

    public SaveProjectAction(MainFrame mainFrame) {
        super("Save Project");
        this.mainFrame = mainFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Document document = mainFrame.getDocument();
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save Project as");
        fileChooser.setFileFilter(new FileNameExtensionFilter("JSON (.json)", "json"));
        int result = fileChooser.showSaveDialog(this.mainFrame);
        if(result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try (PrintWriter writer = new PrintWriter(selectedFile)){
                Gson serializer = new Gson();
                writer.write(serializer.toJson(document));
            } catch (Exception e1) {
                e1.printStackTrace();
                JOptionPane.showMessageDialog(mainFrame, "While trying to save your document an error occurred: " + e1, "CV Generator Export Error", JOptionPane.ERROR_MESSAGE);
            }
        }

    }
}
