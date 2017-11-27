package com.gopiandcode.graphics;

import com.gopiandcode.document.ContactDetails;
import com.gopiandcode.document.Document;
import com.gopiandcode.graphics.components.AboutAction;
import com.gopiandcode.graphics.models.ContactDetailsModel;
import com.gopiandcode.graphics.models.DocumentModel;
import com.gopiandcode.graphics.views.ContactDetailsView;
import com.gopiandcode.graphics.views.DocumentView;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame{

    private AboutAction aboutAction = new AboutAction();

    public MainFrame() {

        setupLayout();
        setupMenu();
        setupComponents();
        setupFrame();
    }

    private void setupFrame() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(640, 360);
    }


    private void setupLayout() {
    }
    private void setupMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem newProject = new JMenuItem("New Project");
        JMenuItem saveProject = new JMenuItem("Save Project");
        JMenuItem exportProject = new JMenuItem("Export Project");

        fileMenu.add(newProject);
        fileMenu.add(saveProject);
        fileMenu.add(exportProject);
        menuBar.add(fileMenu);

        JMenu aboutMenu = new JMenu("About");
        JMenuItem about = new JMenuItem(aboutAction);

        aboutMenu.add(about);
        menuBar.add(aboutMenu);


        setJMenuBar(menuBar);
    }

    private void setupComponents() {
        JSplitPane pane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        Document document = new Document();
        JPanel panel = new JPanel(new GridLayout(1,2));
        panel.add(new DocumentView(new DocumentModel(document)));
        panel.add(new ContactDetailsView(new ContactDetailsModel(document.getDetails())));
        pane.add(panel);
        pane.add(new JPanel());

        add(pane, BorderLayout.CENTER);
    }


}
