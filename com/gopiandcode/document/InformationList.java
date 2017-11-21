package com.gopiandcode.document;

import com.gopiandcode.latex.Latexisable;

import java.util.ArrayList;

/**
 * Created by gopia on 20/11/2017.
 */
public class InformationList implements Latexisable{
    private String title;
    private DisplayStrategy strategy;
    private ArrayList<DetailField> details;

    @Override
    public String toLatex() {
        //TODO(Kiran): Implement methods to latexise information lists
        return null;
    }
}
