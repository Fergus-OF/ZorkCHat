import java.io.Serializable;

public class Friend extends Character implements Serializable {
    private String friendName;
    public Inventory friendInventory;
    public Friend(String friendName){
        this.friendName = friendName;
        this.friendInventory = new Inventory();

    }

    public void friendInventory(){
        friendInventory.showItems();
    }

    public void friendSetItem(Item item){
        friendInventory.addTo(item);
    }

    public void friendRemoveItem(Item item){
        friendInventory.removeFrom(item);
    }
}
