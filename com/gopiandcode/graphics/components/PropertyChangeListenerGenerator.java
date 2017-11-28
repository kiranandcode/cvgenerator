package com.gopiandcode.graphics.components;

import javax.swing.*;
import java.beans.PropertyChangeListener;

@FunctionalInterface
public interface PropertyChangeListenerGenerator<T> {
    PropertyChangeListener generatePropertyChangeListener(ListModel<T> model, Integer index);
}
