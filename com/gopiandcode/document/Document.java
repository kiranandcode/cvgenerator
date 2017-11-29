package com.gopiandcode.document;


import com.gopiandcode.latex.Latexizable;

import java.util.ArrayList;

public class Document implements Latexizable {

    private String title;
    private String taster;

    private ContactDetails details;
    private ArrayList<Subsection> content;

    public Document() {
        title = "";
        taster = "";
        details = new ContactDetails();
        content = new ArrayList<>();
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTaster() {
        return taster;
    }

    public void setTaster(String taster) {
        this.taster = taster;
    }

    public ContactDetails getDetails() {
        return details;
    }

    public void setDetails(ContactDetails details) {
        this.details = details;
    }

    public ArrayList<Subsection> getContent() {
        return content;
    }

    public void setContent(ArrayList<Subsection> content) {
        this.content = content;
    }


    @Override
    public String toString() {
        return "Document{" +
                "title='" + title + '\'' +
                ", taster='" + taster + '\'' +
                ", details=" + details +
                ", content=" + content +
                '}';
    }

    @Override
    public String toLatex() {
        String formattingStart = "\\documentclass{article}\n" +
                "\\usepackage[a4paper, total={8in,11in}]{geometry}\n" +
                "\\usepackage{graphicx}\n" +
                "\\usepackage{scrextend}\n" +
                "\n" +
                "\\usepackage{calc} % needed to use + in \\setlength\n" +
                "\\newenvironment{achievementslist}[1][1in]%\n" +
                " {\\begin{list}{}{%\n" +
                "   \\renewcommand{\\makelabel}[1]{\\indent\\ \\textit{##1}\\ }%\n" +
                "   \\setlength{\\labelwidth}{#1}%\n" +
                "   \\setlength{\\leftmargin}{\\labelwidth+\\labelsep}%\n" +
                "   }}\n" +
                "{\\end{list} \t\\vspace{0.5cm}}\n" +
                "\n" +
                "\n" +
                "\\begin{document}\n" +
                "{";
        String formattingDetails = details.toLatex();
        String titleFormatting = "\t\\begin{centering}\n" +
                "\t{\\LARGE " + this.title + " \\par}\n" +
                "\\vspace{0.2cm}\t\n" +
                "{\\Large " + this.taster + " \\par}\n" +
                "\t\\end{centering}";
        StringBuilder entriesPanel = new StringBuilder();
        for(Subsection s : this.content) {
            entriesPanel.append(s.toLatex());
        }
        String contentFormat = entriesPanel.toString();
        return formattingStart + formattingDetails + titleFormatting + contentFormat + "\n\\end{document}";
    }
}
