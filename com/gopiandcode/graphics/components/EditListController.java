package com.gopiandcode.graphics.components;

import java.awt.event.ActionListener;

/**
 * Created by gopia on 21/11/2017.
 */
public abstract class EditListController<T> implements ActionListener {

    public abstract void connect(EditListModel<T> model, EditListView<T> view);

}

