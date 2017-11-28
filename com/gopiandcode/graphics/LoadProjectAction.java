package com.gopiandcode.graphics;

import com.google.gson.Gson;
import com.gopiandcode.document.Document;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.FileReader;

public class LoadProjectAction extends AbstractAction implements Action {
    private MainFrame mainFrame;

    public LoadProjectAction(MainFrame mainFrame) {
        super("Load Project");
        this.mainFrame = mainFrame;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Open project from");
        fileChooser.setFileFilter(new FileNameExtensionFilter("JSON (.json)", "json"));
        int result = fileChooser.showOpenDialog(this.mainFrame);
        if(result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try (FileReader reader = new FileReader(selectedFile)){
                Gson serializer = new Gson();
                Document document = serializer.fromJson(reader, Document.class);
                mainFrame.reset(document);
            } catch (Exception e1) {
                e1.printStackTrace();
                JOptionPane.showMessageDialog(mainFrame, "While trying to load your document an error occurred: " + e1, "CV Generator Load Error", JOptionPane.ERROR_MESSAGE);
            }
        }

    }
}
