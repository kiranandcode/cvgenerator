package com.gopiandcode.graphics.models;

import com.gopiandcode.document.ContactDetails;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class ContactDetailsModel extends AbstractTableModel {
    private ContactDetails details;
    private static final String[] COLUMN_NAMES = {
            "Type",
            "Value"
    };

    public ContactDetailsModel(ContactDetails details) {
        this.details = details;

   }

    public TableModel getTableModel() {
        return this;
    }

    public ContactDetails getDetails() {
        return details;
    }

    public ActionListener getRemoveActionListener(JTable table) {
        return e -> {
            int selectedRow = table.getSelectedRow();
            if(selectedRow != -1 && selectedRow < this.getRowCount())
                removeRow(selectedRow);
            table.updateUI();
        };
    }

    public ActionListener getAddActionListener(JTable table, JTextField typeField, JTextField valueField) {
        return (ActionEvent e) -> {
            addRow(new String[] {typeField.getText(), valueField.getText()});
            typeField.setText("");
            valueField.setText("");
            table.updateUI();
        };

    }


    public void removeRow(int row) {
        this.details.getContact_details().remove(row);
        fireTableRowsUpdated(row, this.getRowCount());
    }

    @Override
    public int getRowCount() {
        int size = this.details.getContact_details().size();
        return size;
    }

    @Override
    public int getColumnCount() {
        return COLUMN_NAMES.length;
    }

    @Override
    public String getColumnName(int column) {
        return COLUMN_NAMES[column];
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return true;
    }

    @Override
    public Object getValueAt(int row, int column) {
        return details.getValueAt(row,column);
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }


    @Override
    public void setValueAt(Object aValue, int row, int column) {
        details.setValueAt(row, column, (String) aValue);
        fireTableCellUpdated(row, column);
    }

    public void addRow(Object[] rowData) {
        details.addDetails((String) rowData[0], (String) rowData[1]);
        fireTableRowsUpdated(this.getRowCount()-1, this.getRowCount());
    }

        public void insertRow(int row, Vector rowData) {
        details.addDetails(row, (String) rowData.get(0), (String) rowData.get(1));
        fireTableRowsUpdated(this.getRowCount()-1, this.getRowCount());
    }

    public void insertRow(int row, Object[] rowData) {
        details.addDetails(row, (String) rowData[0], (String) rowData[1]);
        fireTableRowsUpdated(this.getRowCount()-1, this.getRowCount());
    }



}
