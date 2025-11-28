import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;

public class Room implements Serializable {
    private String description;
    private Map<String, Room> exits; // Map direction to neighboring Room
    public Inventory roomInventory;
    public Inventory characters;

    public Room(String description) {
        this.description = description;
        exits = new HashMap<>();
        this.roomInventory = new Inventory();
    }

    public Room(String description, Character characters){
        this.description = description;
        exits = new HashMap<>();
        this.roomInventory = new Inventory();
        this.characters = new Inventory();
    }

    public String getDescription() {
        return description;
    }

    public void setExit(String direction, Room neighbor) {
        exits.put(direction, neighbor);
    }

    public Room getExit(String direction) {
        return exits.get(direction);
    }

    public ArrayList getInventory(){ return roomInventory.getInventories(); }

    public void setCharacters(Friend fella){characters.setCharacter(fella);}

    public void setCharacters(Foe enemy){characters.setCharacter(enemy);}

    public void removeCharacter(Foe enemy){characters.removeCharacter(enemy);}

    public void getFriendCharacters(){ characters.getFriendCharacter() ; }

    public void getFoeCharacters(){characters.getFoeCharacter();}

    public void getItems(){  roomInventory.showItems();}

    public void takeItem(Item item){ roomInventory.removeFrom(item);}

    public void dropItem(Item item){ roomInventory.addTo(item);}


    public String getExitString() {
        StringBuilder sb = new StringBuilder();
        for (String direction : exits.keySet()) {
            sb.append(direction).append(" ");
        }
        return sb.toString().trim();
    }

    public String getLongDescription() {
        return "You are " + description + ".\nExits: " + getExitString();
    }
}
