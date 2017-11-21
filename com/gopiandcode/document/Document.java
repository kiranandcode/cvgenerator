package com.gopiandcode.document;

import com.gopiandcode.latex.Latexisable;

import java.util.ArrayList;

/**
 * Created by gopia on 20/11/2017.
 */
public class Document implements Latexisable {

    private String title;
    private String taster;

    private ContactDetails details;
    private ArrayList<InformationList> content;

    @Override
    public String toLatex() {
        //TODO(Kiran): Implement TOLATEX for Document
        return null;
    }
}
