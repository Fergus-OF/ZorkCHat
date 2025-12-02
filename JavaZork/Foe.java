import java.io.*;
public class Foe extends Character implements Serializable{
    private String foeName;
    private Room currentRoomFoe;
    public Inventory foeInventory;
    public int health;
    public Foe(String foeName,Room room, int health){
        this.foeName = foeName;
        this.foeInventory = new Inventory();
        this.currentRoomFoe = room;
        this.health = health;

    }
    public String getFoeName(){ return foeName;}

    public void reduceHealth(int attackMultiplier){
        int attackValue = (int)((Math.random() * attackMultiplier));
        int newHealth = getHealth()- attackValue;
        System.out.println("The wolf has: "+ health+" health");
        System.out.println("You swing bravely at the wolf and deal "+ attackValue + " damage");

        setHealth(newHealth);
        System.out.println("The wolf, now wounded, has " + health+ " health");
    }

    public void setHealth(int newHealth){
        this.health =  newHealth;
        //return health;
    }

    public int getHealth(){ return health;}

    public void foeInventory(){
        foeInventory.showAll();
    }

    public void foeSetItem(Item item){
        foeInventory.add(item);
    }

    public void foeRemoveItem(Item item){
        foeInventory.remove(item);
    }
}
