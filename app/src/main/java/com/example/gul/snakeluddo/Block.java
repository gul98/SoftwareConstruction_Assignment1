package com.example.gul.snakeluddo;

/**
 * Created by gul on 3/16/17.
 */

public class Block {

    public String description;

    public boolean light;

    public boolean user;

    public boolean computer;

    public Block(){

        light=false;
    }

    public Block(String description){

        this.description=description;
        light=false;
    }

    public Block(String description, boolean light){

        this.description=description;
        this.light=light;
    }

}
