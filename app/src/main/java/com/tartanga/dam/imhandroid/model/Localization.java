package com.tartanga.dam.imhandroid.model;

import java.io.Serializable;

/**
 * Created by 2dam on 20/02/2017.
 */

public class Localization implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private int id;
    private String name;

    public Localization(int id, String name){
        this.id = id;
        this.name = name;
    }

    public Localization(){

    }

    public int getId() {
        return id;
    }

    public String toString(){
        String s = this.name;
        return s;
    }
}
