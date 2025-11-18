import java.io.Serializable;
import java.util.ArrayList;

public class Inventory implements Serializable {
    ArrayList<Item> inventory = new ArrayList<>();

    public Inventory(){

    }


    public ArrayList getInventories(){
        return inventory;
    }

    public void showItems(){
        for(Item item: inventory)
            System.out.println(item.getName());
    }

    public void addTo(Item item){
        inventory.add(item);
    }

    public void removeFrom(Item item){
        inventory.remove(item);
    }


    }

