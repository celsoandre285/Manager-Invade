package com.example.desenvolvedor2015.invatedmanager.entities;

/**
 * Created by desenvolvedor on 18/01/18.
 */

public class GuestEntity {
    private int id;
    private String name;
    private int confirmed;
    private String doc;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(int confirmed) {
        this.confirmed = confirmed;
    }

    public String getDoc() {
        return doc;
    }

    public void setDoc(String doc) {
        this.doc = doc;
    }
}
