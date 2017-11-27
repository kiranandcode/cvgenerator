package com.gopiandcode.graphics.components;


import java.awt.*;

public interface JTabbedPaneComponentGenerator<T> {
   Component renderModel(T model);
}
