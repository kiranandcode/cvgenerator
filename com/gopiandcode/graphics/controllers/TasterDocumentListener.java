package com.gopiandcode.graphics.controllers;

import com.gopiandcode.document.Document;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;

/**
 * Created by gopia on 27/11/2017.
 */
public class TasterDocumentListener implements DocumentListener {
    private final Document document;

    public TasterDocumentListener(Document document) {
        this.document = document;
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        updateText(e);
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        updateText(e);
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        updateText(e);
    }


    public void updateText(DocumentEvent e) {
        try {
            javax.swing.text.Document textDocument = e.getDocument();
            String text = textDocument.getText(0, textDocument.getLength());
            document.setTaster(text);
        } catch (BadLocationException e1) {
            e1.printStackTrace();
        }
    }

}
