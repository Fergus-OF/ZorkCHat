import java.io.Serializable;

public class Clothes implements Item, Serializable{
    private String name;
    private String description;

    public Clothes(String name, String description){
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
}

/*
        public Item(String name, String description) {
        this.name = name;
        this.description = description;
        this.isVisible = true;
    }*/