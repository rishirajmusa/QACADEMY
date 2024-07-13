package com.crio.jukebox.entities;

public abstract class BaseEntity {
    protected String id;
    protected String name;

    public String getId(){
        return id;
    }

    public String getName(){
        return name;
    }
}
