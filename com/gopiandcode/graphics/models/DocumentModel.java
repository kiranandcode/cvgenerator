package com.gopiandcode.graphics.models;

import com.gopiandcode.document.Document;
import com.gopiandcode.graphics.controllers.TasterDocumentListener;
import com.gopiandcode.graphics.controllers.TitleDocumentListener;

import javax.swing.text.PlainDocument;

public class DocumentModel {
    private Document document;
    private javax.swing.text.Document titleDocument;
    private javax.swing.text.Document tasterDocument;
    
    public DocumentModel(Document document) {
        this.document = document;
        this.titleDocument = new PlainDocument();
        this.titleDocument.addDocumentListener(new TitleDocumentListener(this.document));

        this.tasterDocument = new PlainDocument();
        this.tasterDocument.addDocumentListener(new TasterDocumentListener(this.document));
    }

    public javax.swing.text.Document getTitleModel() {
       return titleDocument;
    }

    public javax.swing.text.Document getTasterModel() {
       return tasterDocument;
    }

    public Document getDocument() {
        return document;
    }
}

