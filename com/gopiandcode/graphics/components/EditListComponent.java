package com.gopiandcode.graphics.components;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gopia on 21/11/2017.
 */
public class EditListComponent<T> extends JPanel {

    private EditListController<T> controller;
    private EditListView<T> view;
    private EditListModel<T> model;


    EditListComponent(String name, ArrayList<T> existing, EditListController<T> controller){
        this.model = new EditListModel<>(existing);
        this.view = new EditListView<T>(name);
        this.controller = controller;
        controller.connect(this.model, this.view);

        model.connectView(view);
        view.connectController(this.controller);


        view.addToPanel(this);
   }

   public EditListComponent(String name, EditListController<T> controller) {
        this(name, new ArrayList<>(), controller);
   }

}



