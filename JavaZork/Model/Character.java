package Model;

import java.io.*;

public class Character implements Serializable {
    private String name;
    private Room currentRoom;
    public Inventory<Item> playerInventory;
    public int health;
    public Character(){}

    public Character(String name, Room startingRoom) {
        this.name = name;
        this.currentRoom = startingRoom;
        this.playerInventory = new Inventory();
    }

    public Character(String name, Room startingRoom,int health) {
        this.name = name;
        this.currentRoom = startingRoom;
        this.playerInventory = new Inventory();
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
        int newHealth = getHealth()- attackMultiplier;
        setHealth(newHealth);
    }

    public void setHealth(int newHealth){
        this.health =  newHealth;
    }

    public String increaseHealth(int healValue) {
        int newHealth = health + healValue;
        setHealth(newHealth);
        return "Heal Value: " + healValue + "\nNew Health: " + health;
    }

    public int getHealth(){ return health; }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room room) { this.currentRoom = room;}

    public String showInventory() {
        return playerInventory.showAll();
    }
    public Inventory getInventory(){ return playerInventory;}

    public void takeItem(Item item){
       playerInventory.add(item);
    }

    public void dropItem(Item item){
        playerInventory.remove(item);
    }


    }

