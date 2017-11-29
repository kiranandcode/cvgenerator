package com.gopiandcode.document;


import com.gopiandcode.latex.Latexizable;

import java.util.ArrayList;

public class Subsection implements Latexizable{
    private String title;
    private ArrayList<Entry> entries;

    public Subsection() {
        this("", new ArrayList<>());
    }
    public Subsection(String title, ArrayList<Entry> entries) {
        this.title = title;
        this.entries = entries;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<Entry> getEntries() {
        return entries;
    }

    public void setEntries(ArrayList<Entry> entries) {
        this.entries = entries;
    }

    @Override
    public String toLatex() {
        String startFormat = "\t\\textbf{" + this.title + "}\n" +
                "\t\\vspace{0.5cm}\n" +
                "\\begin{addmargin}[4em]{4em}\n" +
                "\t\\begin{achievementslist}[1.4in]";

        StringBuilder entriesBuilder = new StringBuilder();
        for(Entry e : entries) {
            entriesBuilder.append(e.toLatex());
        }

        String entriesFormat = entriesBuilder.toString();
        String endFormat = "\t\\end{achievementslist}\n" +
                "\\end{addmargin}";

        return startFormat + entriesFormat + endFormat;
    }
}
