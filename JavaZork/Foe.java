import java.io.*;
public class Foe extends Character implements Serializable{
    private String foeName;
    private Room currentRoomFoe;
    public Inventory foeInventory;
    public Foe(String foeName,Room room){
        this.foeName = foeName;
        this.foeInventory = new Inventory();
        this.currentRoomFoe = room;

    }
    public String getFoeName(){ return foeName;}

    public void foeInventory(){
        foeInventory.showItems();
    }

    public void foeSetItem(Item item){
        foeInventory.addTo(item);
    }

    public void foeRemoveItem(Item item){
        foeInventory.removeFrom(item);
    }
}
