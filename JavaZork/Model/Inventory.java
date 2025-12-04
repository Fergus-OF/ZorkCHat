package Model;

import java.io.Serializable;
import java.util.ArrayList;

public class Inventory<T extends Item> implements Serializable {
    private ArrayList<T> items = new ArrayList<>();

    public void add(T item) {
        items.add(item);
    }

    public void remove(T item) {
        items.remove(item);
    }

    public ArrayList<T> getAll() {
        return items;
    }

    public String showAll() {
        if (items.isEmpty()) {
            return "Your inventory is empty.";
        }
        StringBuilder sb = new StringBuilder();
        for (T item : items) {
            sb.append("A ").append(item.name()).append(" ").append(item.description()).append("\n");
        }
        return sb.toString();
    }

    public int countMemory(){
        int memoryCount = 0;
        for (T item: items){
            if (item instanceof Memories){
                memoryCount++;
            }

        }
        return memoryCount;
    }

    public int countClothes(){
        int clothesCount = 0;
        for (T item: items){
            if (item instanceof Memories){
                clothesCount++;
            }

        }
        return clothesCount;
    }


}

    /*
    ArrayList<Item> inventory = new ArrayList<>();
    ArrayList<Friend> friends = new ArrayList<>();
    ArrayList<Foe> foes = new ArrayList<>();

    public Inventory(){

    }


    public ArrayList getInventories(){
        return inventory;
    }

    public void setCharacter(Friend characterSetting){
        friends.add(characterSetting);
    }

    public void setCharacter(Foe characterSetting){
        foes.add(characterSetting);
    }

    public void removeCharacter(Foe enemy){ foes.remove(enemy);    }

    public void getFriendCharacter() {
        for (Friend chats: friends) {
          System.out.println(chats.getFriendName());
        }
    }

    public ArrayList getFoeCharacter(){return foes; }
    public void showFoeCharacter(){
        for (Foe chats : foes){
            System.out.println(chats.getFoeName());
        }
    }

    public void showItems(){
        for(Item item: inventory) {
            System.out.println("A " +item.name() + " " + item.description());
        }
    }

    public void addTo(Item item){
        inventory.add(item);
    }

    public void removeFrom(Item item){
        inventory.remove(item);
    }




     */

