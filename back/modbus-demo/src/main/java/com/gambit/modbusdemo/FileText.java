package com.gambit.modbusdemo;

import org.springframework.stereotype.Component;

public class FileText {
    private String text;

    public FileText() {

    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
