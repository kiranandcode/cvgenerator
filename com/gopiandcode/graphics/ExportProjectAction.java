package com.gopiandcode.graphics;

import com.gopiandcode.document.Document;
import com.gopiandcode.latex.LatexSerializer;

import javax.print.Doc;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class ExportProjectAction extends AbstractAction implements Action {
    private MainFrame mainFrame;

    public ExportProjectAction(MainFrame mainFrame) {
        super("Export Project");
        this.mainFrame = mainFrame;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        Document document = mainFrame.getDocument();
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Export project to Latex");

        fileChooser.setFileFilter(new FileNameExtensionFilter("Latex File (.tex)", "tex"));
        int result = fileChooser.showSaveDialog(this.mainFrame);
        if(result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try (PrintWriter writer = new PrintWriter(selectedFile)){
                LatexSerializer serializer = new LatexSerializer(writer);
                serializer.serialize(document);
            } catch (Exception e1) {
                e1.printStackTrace();
                JOptionPane.showMessageDialog(mainFrame, "While trying to save your document an error occurred: " + e1, "CV Generator Export Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
