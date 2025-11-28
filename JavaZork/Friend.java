import java.io.Serializable;

public class Friend extends Character implements Serializable {
    private String friendName;
    private Room currentRoomFriend;
    public Inventory friendInventory;
    public Friend(String friendName,Room room){
        this.friendName = friendName;
        this.friendInventory = new Inventory();
        this.currentRoomFriend = room;

    }

    public String getFriendName(){ return friendName;}


    public void getFriendInventory(){
        friendInventory.showItems();
    }

    public void friendSetItem(Item item){
        friendInventory.addTo(item);
    }

    public void friendRemoveItem(Item item){
        friendInventory.removeFrom(item);
    }


}
