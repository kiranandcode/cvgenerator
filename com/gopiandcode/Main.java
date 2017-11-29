package com.gopiandcode;

import com.gopiandcode.document.Document;
import com.gopiandcode.graphics.MainFrame;

import javax.swing.*;
import java.awt.*;

public class Main {
    private static void configureStyle() {
        UIManager.LookAndFeelInfo[] installedLookAndFeels = UIManager.getInstalledLookAndFeels();
        for (UIManager.LookAndFeelInfo info : installedLookAndFeels) {
            if (info.getName().toLowerCase().contains("windows")) {
                try {
                    UIManager.setLookAndFeel(info.getClassName());
                    return;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        for (UIManager.LookAndFeelInfo info : installedLookAndFeels) {
            if (info.getName().toLowerCase().contains("metal")) {
                try {
                    UIManager.setLookAndFeel(info.getClassName());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
      }
    }

    public static void main(String[] args) {
        // write your code here
            configureStyle();
            EventQueue.invokeLater(() -> {
                MainFrame frame = new MainFrame(new Document());
                frame.setVisible(true);
            });
    }
}
