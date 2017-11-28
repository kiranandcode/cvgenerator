package com.gopiandcode.graphics.components;

import com.gopiandcode.document.Subsection;

import javax.swing.*;
import java.awt.*;

public class SubsectionListCellRenderer extends DefaultListCellRenderer {
    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        JLabel listCellRendererComponent = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

        String title = "";
        if (value != null) {
            Subsection item = (Subsection) value;
            title = item.getTitle();
        }

        listCellRendererComponent.setText(title);
        return listCellRendererComponent;

    }
}
