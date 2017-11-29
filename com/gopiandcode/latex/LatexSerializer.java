package com.gopiandcode.latex;

import com.gopiandcode.document.Document;

import java.io.PrintWriter;

public class LatexSerializer {
    private PrintWriter writer;

    public LatexSerializer(PrintWriter writer) {
        this.writer = writer;
    }

    public void serialize(Latexizable item) {
        writer.write(item.toLatex());
    }
}
