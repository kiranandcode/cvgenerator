package com.gopiandcode.graphics.components;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

public class FunctionalDocumentListener implements DocumentListener {

    private StringFieldSetter setter;

    public FunctionalDocumentListener(StringFieldSetter setter) {
        this.setter = setter;
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
            Document document = e.getDocument();
            String text = document.getText(0, document.getLength());
            setter.setField(text);
        } catch (BadLocationException e1) {
            e1.printStackTrace();
        }
    }
}
