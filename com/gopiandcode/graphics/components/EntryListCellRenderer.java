package com.gopiandcode.graphics.components;

import com.gopiandcode.document.Entry;
import com.gopiandcode.document.Subsection;

import javax.swing.*;
import java.awt.*;

public class EntryListCellRenderer extends DefaultListCellRenderer {
    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        JLabel listCellRendererComponent = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

        String title = "[New Entry]";
        if (value != null) {
            Entry item = (Entry) value;
            title = item.getTitle();
            if(title.isEmpty()) {
                title = "[Untitled Entry]";
            }
        }

        listCellRendererComponent.setText(title);
        return listCellRendererComponent;

    }
}