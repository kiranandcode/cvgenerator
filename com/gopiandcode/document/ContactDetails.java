package com.gopiandcode.document;

import com.gopiandcode.latex.Latexisable;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by gopia on 20/11/2017.
 */
public class ContactDetails implements Latexisable {

    private ArrayList<Map.Entry<String, String>> contact_details;


    @Override
    public String toLatex() {
        //TODO(Kiran): Implement Latexisable methods for Contact Details
        return null;
    }
}
