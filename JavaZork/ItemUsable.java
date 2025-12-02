import java.io.Serializable;

public class ItemUsable implements Item,Serializable{
    private String name;
    private String description;

    public ItemUsable(String name, String description){
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
