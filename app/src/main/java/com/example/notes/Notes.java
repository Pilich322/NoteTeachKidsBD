package com.example.notes;

import android.provider.ContactsContract;

public class Notes {
    private int id;
    private String title;
    private String text;

    public Notes(String title, String text) {
        this.title = title;
        this.text = text;
    }

    public Notes(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
