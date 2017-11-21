package com.gopiandcode.graphics;

import com.gopiandcode.graphics.panels.DocumentPanel;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class MainFrame extends JFrame {
    private static final int DEFAULT_HEIGHT = 600;
    private static final int DEFAULT_WIDTH = 800;
    private DocumentPanel documentPanel;

    public MainFrame(){
        this(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }

    public MainFrame(int width, int height) {
        setSize(width, height);
        setup();
    }

    private void setup() {
       setLocationRelativeTo(null);

       setupMenu();
       setupMainPane();
       setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }


    private void setupMenu() {
        JMenuBar mainMenu = new JMenuBar();
        setupFileMenu(mainMenu);
        setupAboutOption(mainMenu);

        setJMenuBar(mainMenu);
    }

    private void setupAboutOption(JMenuBar mainMenu) {
        JMenu aboutMenu = new JMenu("About");
        JMenuItem aboutItem = new JMenuItem("About CV Generator");
        aboutItem.addActionListener(e -> JOptionPane.showMessageDialog(MainFrame.this,
               "Simple CV Generator made by Gopiandcode. Use latex to compile resulting file into a nice CV." ,
               "About - CV Generator",
               JOptionPane.INFORMATION_MESSAGE));

        aboutMenu.add(aboutItem);
        mainMenu.add(aboutMenu);
    }

    private void setupFileMenu(JMenuBar mainMenu) {
        JMenu fileMenu = new JMenu("File");
        JMenuItem new_cv = new JMenuItem("New Project", 'n');
        JMenuItem open_cv = new JMenuItem("Open Project", 'o');
        JMenuItem save_cv = new JMenuItem("Save Project", 's');
        JMenuItem export_cv = new JMenuItem("Export CV", 'e');
        JMenuItem exit = new JMenuItem("Exit", 'x');

        open_cv.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        fileMenu.add(new_cv);
        fileMenu.add(open_cv);
        fileMenu.add(save_cv);
        fileMenu.add(export_cv);
        fileMenu.add(exit);
        mainMenu.add(fileMenu);
    }

    private void setupMainPane() {
        JTabbedPane tabbedPane = new JTabbedPane();

        documentPanel = new DocumentPanel();

        tabbedPane.addTab("Document", null, documentPanel, "Modify Document Properties (d)");
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_D);

        setLayout(new GridLayout(1,1));
        add(tabbedPane);
        pack();
    }



}
