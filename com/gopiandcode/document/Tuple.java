package com.gopiandcode.document;

import java.util.Map;

public class Tuple implements Map.Entry<String, String> {
    String a;
    String b;
    public Tuple() {
        this.a = "";
        this.b = "";
    }
    public Tuple(String a, String b) {
        this.a = a;
        this.b = b;
    }
    @Override
    public String getKey() {
        return a;
    }

    public void setKey(String key) {
        this.a = key;
    }

    @Override
    public String getValue() {
        return b;
    }

    @Override
    public String setValue(String value) {
        this.b = b;
        return b;
    }
}
