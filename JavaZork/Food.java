import java.io.Serializable;

public class Food implements Item, Serializable{
    private String name;
    private String description;
    private int healingValue;

    public Food(String name, String description, int healingValue){
        this.name = name;
        this.description = description;
        this.healingValue = healingValue;
    }

    @Override
    public String name(){
        return name;
    }

    @Override
    public String description(){
        return description;
    }

    //@Override
    //public int getHealingValue(){ return healingValue;}
}
