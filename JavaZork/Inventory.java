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

    public void showAll() {
        for (T item : items) {
            System.out.println("A "+item.name()+ " " + item.description());
        }
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

