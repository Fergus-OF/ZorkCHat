package Model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;

public class Room implements Serializable {
    private String description;
    private Map<String, Room> exits; // Map direction to neighboring Room
    public Inventory<Item> roomInventory;
    public CharInventory characters;
    public String name;

    public Room(String name, String description) {
        this.name = name;
        this.description = description;
        exits = new HashMap<>();
        this.roomInventory = new Inventory();
    }

    public Room(String name, String description, Model.Character characters){
        this.name = name;
        this.description = description;
        exits = new HashMap<>();
        this.roomInventory = new Inventory();
        this.characters = new CharInventory();
    }

    public String getName() { return name;}

    public String getDescription() {
        return description;
    }

    public void setExit(String direction, Room neighbor) {
        exits.put(direction, neighbor);
    }

    public Room getExit(String direction) {
        return exits.get(direction);
    }

    public ArrayList getInventory(){ return roomInventory.getAll(); }

    public void setCharacters(Friend fella){characters.setCharacter(fella);}

    public void setCharacters(Foe enemy){characters.setCharacter(enemy);}

    public void removeCharacter(Character enemy){characters.removeCharacter(enemy);}

    public void getFriendCharacters(){ characters.getFriendCharacter() ; }

    public ArrayList getFoeCharacters(){ return characters.getFoeCharacter();}

    public void showFoeCharacters(){characters.showFoeCharacter();}

    public void getItems(){  roomInventory.showAll();}

    public void takeItem(Item item){ roomInventory.remove(item);}

    public void dropItem(Item item){ roomInventory.add(item);}


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
