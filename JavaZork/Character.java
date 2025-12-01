import java.io.*;

public class Character implements Serializable {
    private String name;
    private Room currentRoom;
    public Inventory playerInventory;
    public Inventory playerPermissions;
    public int health;
    public Character(){}

    public Character(String name, Room startingRoom) {
        this.name = name;
        this.currentRoom = startingRoom;
        this.playerInventory = new Inventory();
        this.playerPermissions = new Inventory();
    }

    public Character(String name, Room startingRoom,int health) {
        this.name = name;
        this.currentRoom = startingRoom;
        this.playerInventory = new Inventory();
        this.playerPermissions = new Inventory();
        this.health = health;
    }

    public String getName() {
        return name;
    }

    public void attack(){
        int attackValue = (int)(Math.random()*20);

    }

    public void setFoeDamage(int damage ) {
       // Foe.setHealth((damage));
    }
    public void reduceHealth(int attackMultiplier){
        int attackValue = (int)(Math.random() * attackMultiplier);
        int newHealth = getHealth()- attackMultiplier;
        //System.out.println("Wolf Health: "+ health);
        //System.out.println("AttackValue: "+ attackValue);

        setHealth(newHealth);
        //System.out.println("New Health" + health);
    }

    public void setHealth(int newHealth){
        this.health =  newHealth;
    }

    public void increaseHealth(int healValue){
        System.out.println("Heal Value: "+ healValue);
        int newHealth = health + healValue;
        setHealth(newHealth);
        //System.out.println("New Health" + health);
    }

    public int getHealth(){ return health; }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room room) {
        this.currentRoom = room;
    }

    public void showInventory(){
        playerInventory.showItems();
    }

    public Inventory getInventory(){ return playerInventory;}

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

