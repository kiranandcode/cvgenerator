package com.gopiandcode.graphics.panels;

import com.gopiandcode.graphics.components.EditListComponent;
import com.gopiandcode.graphics.components.EditListController;
import com.gopiandcode.graphics.components.EditListModel;
import com.gopiandcode.graphics.components.EditListView;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Created by gopia on 21/11/2017.
 */
public class DocumentPanel extends JPanel {

    public DocumentPanel(){
        setup();
    }

    private void setup() {
        EditListComponent<String> component = new EditListComponent<String>("SubSections", new StringEditListController());
        setLayout(new GridLayout(1,1));
        add(component);
    }
}

class StringEditListController extends EditListController<String> {

    private EditListModel<String> model;
    private EditListView<String> view;

    @Override
    public void connect(EditListModel<String> model, EditListView<String> view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        System.out.println(e.getActionCommand());
        switch(e.getActionCommand()){
            case "Add":
                String current;
                try {
                    current = this.model.getNewModel().getText(0, this.model.getNewModel().getLength());
                } catch (BadLocationException e1) {
                    e1.printStackTrace();
                    break;
                }
                this.model.getFieldsModel().addElement(current);
                break;
            case "Remove":
                int index = this.view.getListComponent().getSelectedIndex();
                try {
                    this.model.getFieldsModel().removeElementAt(index);
                } catch(IndexOutOfBoundsException f) {
                    f.printStackTrace();
                    break;
                }
                break;
        }

        this.view.updateUI();
    }
}