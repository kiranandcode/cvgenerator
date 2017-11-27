package com.gopiandcode.graphics.models;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import java.util.List;

public class DetailsTableModel extends AbstractTableModel {
    private final static String[] COLUMN_NAMES = { "Details" };

    private List<String> detailsList;

    public DetailsTableModel(List<String> detailsList) {
        this.detailsList = detailsList;
    }

    @Override
    public int getRowCount() {
        return detailsList.size();
    }

    @Override
    public int getColumnCount() {
        return COLUMN_NAMES.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (columnIndex == 0) {
            return detailsList.get(rowIndex);
        } else {
            throw new IllegalArgumentException("Details Table only supports one column");
        }
    }


    @Override
    public String getColumnName(int column) {
        return COLUMN_NAMES[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        if(columnIndex == 1) {
            detailsList.remove(rowIndex);
            detailsList.add(rowIndex, (String) aValue);
        } else {
            throw new IllegalArgumentException("Details Table only supports one column");
        }
    }

    public void removeRow(int rowIndex) {
         detailsList.remove(rowIndex);
         fireTableRowsDeleted(rowIndex, rowIndex);
    }

    public void addRow(String text) {
        detailsList.add(text);
        fireTableRowsInserted(getRowCount(), getRowCount());
    }
}
