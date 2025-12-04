package Model;

import java.io.Serializable;

public class Memories implements Item, Serializable {
    private String name;
    private String description;

    public Memories(String name, String description){
        this.name = name;
        this.description = description;
    }

    @Override
    public String name(){
        return name;
    }

    @Override
    public String description(){
        return description;
    }

    @Override
    public int getHealingValue(){ return 0;}
}

