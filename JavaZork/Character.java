import java.io.*;

public class Character implements Serializable {
    private String name;
    private Room currentRoom;
    public Inventory playerInventory;
    public Inventory playerPermissions;
    public Character(){}

    public Character(String name, Room startingRoom) {
        this.name = name;
        this.currentRoom = startingRoom;
        this.playerInventory = new Inventory();
        this.playerPermissions = new Inventory();
    }

    public String getName() {
        return name;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room room) {
        this.currentRoom = room;
    }

    public void showInventory(){
        playerInventory.showItems();
    }

    public void takeItem(Item item){
       playerInventory.addTo(item);
    }

    public void dropItem(Item item){
        playerInventory.removeFrom(item);
    }

    public void move(String direction) {
        Room nextRoom = currentRoom.getExit(direction);
        if (nextRoom != null) {
            currentRoom = nextRoom;
            System.out.println("You moved to: " + currentRoom.getDescription());
        } else {
            System.out.println("You can't go that way!");
        }
    }


    }

