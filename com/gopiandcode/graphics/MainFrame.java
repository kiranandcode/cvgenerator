package com.gopiandcode.graphics;

import com.gopiandcode.document.Document;
import com.gopiandcode.graphics.components.AboutAction;
import com.gopiandcode.graphics.models.ContactDetailsModel;
import com.gopiandcode.graphics.models.DocumentModel;
import com.gopiandcode.graphics.models.SubsectionListModel;
import com.gopiandcode.graphics.views.ContactDetailsView;
import com.gopiandcode.graphics.views.DocumentView;
import com.gopiandcode.graphics.views.ModifySubsectionListView;
import com.gopiandcode.graphics.views.SubsectionListView;

import javax.print.Doc;
import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private Document document;
    private AboutAction aboutAction = new AboutAction();
    private JSplitPane pane;

    public MainFrame(Document document) {
        setupLayout();
        setupMenu();
        this.document = document;
        setupComponents(this.document);
        setupFrame();
    }

   private void setupFrame() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(640, 360);
    }


    private void setupLayout() {
    }

    public void reset(Document document) {
        this.document = document;
        remove(pane);
        setupComponents(document);
        SwingUtilities.updateComponentTreeUI(this);
    }

    public Document getDocument(){
        return this.document;
    }

    private void setupMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem newProject = new JMenuItem(new NewProjectAction(this));
        JMenuItem loadProject = new JMenuItem(new LoadProjectAction(this));
        JMenuItem saveProject = new JMenuItem(new SaveProjectAction(this));
        JMenuItem exportProject = new JMenuItem(new ExportProjectAction(this));

        fileMenu.add(newProject);
        fileMenu.add(saveProject);
        fileMenu.add(loadProject);
        fileMenu.add(exportProject);
        menuBar.add(fileMenu);

        JMenu aboutMenu = new JMenu("About");
        JMenuItem about = new JMenuItem(aboutAction);

        aboutMenu.add(about);
        menuBar.add(aboutMenu);


        setJMenuBar(menuBar);
    }

    private void setupComponents(Document document) {
        pane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        JPanel panel = new JPanel(new GridLayout(1, 2));
        panel.add(new DocumentView(new DocumentModel(document)));
        panel.add(new ContactDetailsView(new ContactDetailsModel(document.getDetails())));

        pane.add(panel);

        JTabbedPane tabbedPane = new JTabbedPane();
        SubsectionListModel model = new SubsectionListModel(document.getContent());
        tabbedPane.add("All Subsections", pane.add(new SubsectionListView(model)));
        tabbedPane.add("Edit Subsection",new ModifySubsectionListView(model));
        pane.add(tabbedPane);

        add(pane, BorderLayout.CENTER);
    }


}
