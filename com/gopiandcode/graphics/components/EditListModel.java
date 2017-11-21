package com.gopiandcode.graphics.components;

import javax.swing.*;
import javax.swing.text.PlainDocument;
import javax.swing.text.StringContent;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gopia on 21/11/2017.
 */
public class EditListModel<T> {
    private DefaultListModel<T> fields_model;
    private PlainDocument new_model;

    public EditListModel(ArrayList<T> existing) {
        fields_model = new DefaultListModel<>();
        for(T field : existing){
            fields_model.addElement(field);
        }
        new_model = new PlainDocument(new StringContent(0));
   }

   public EditListModel(){
        this(new ArrayList<>());
    }

    // For retrieving the list after completion
    public List<T> getList(){
        return new ArrayList<>((List<T>) fields_model);
    }

   public void connectView(EditListView<T> view) {
        view.getListComponent().setModel(fields_model);
        view.getNewTextField().setDocument(new_model);
   }

   public DefaultListModel<T> getFieldsModel() {return fields_model;}
   public PlainDocument getNewModel() {return new_model;}
}
