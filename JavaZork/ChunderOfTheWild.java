/* This game is a classic text-based adventure set in a university environment.
   The player starts outside the main entrance and can navigate through different rooms like a 
   lecture theatre, campus pub, computing lab, and admin office using simple text commands (e.g., "go east", "go west").
    The game provides descriptions of each location and lists possible exits.

Key features include:
Room navigation: Moving among interconnected rooms with named exits.
Simple command parser: Recognizes a limited set of commands like "go", "help", and "quit".
Player character: Tracks current location and handles moving between rooms.
Text descriptions: Provides immersive text output describing the player's surroundings and available options.
Help system: Lists valid commands to guide the player.
Overall, it recreates the classic Zork interactive fiction experience with a university-themed setting, 
emphasizing exploration and simple command-driven gameplay
*/

//import java.util.ArrayList;
//import java.util.Arrays;
import java.io.*;
import java.util.HashMap;

public class ChunderOfTheWild {
    private Parser parser;
    private Character player;
    private HashMap<String,Room> allRooms = new HashMap<>();
    private HashMap<String, Item> allItems = new HashMap<>();
    File file = new File("player.ser");
    private Friend oldMan;
    private Foe wolf;

    public void savePlayer() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("player.ser"))) {
            out.writeObject(player);
            System.out.println("Object has been serialized to person.ser");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public ChunderOfTheWild() {
        createItems();
        createRooms();
        parser = new Parser();
    }

    private void createRooms() {
        Room shrineOfResurrection, outsideShrineOfResurrection, forestShrine, bokoblinCamp, forest, tent, templeOfTime, easternAbbey, mountHylia, riverOfTheDead;

        // create rooms
        shrineOfResurrection = new Room("in Shrine of Resurrection");
        outsideShrineOfResurrection = new Room("Outside the Shrine of Resurrection");
        forestShrine = new Room("in the Shrine in the Forest of Spirits");
        bokoblinCamp = new Room("in an Old Bokoblin Camp");
        forest = new Room("in the Forest of Spirits", wolf);
        tent = new Room("Standing infront of a tent", oldMan);
        templeOfTime = new Room("in the Temple of Time");
        easternAbbey = new Room("in the Eastern Abbey");
        mountHylia = new Room("Standing on the peak of Mount Hylia");
        riverOfTheDead = new Room("Standing on the frozen surface of The River Of The Dead");



        allRooms.put("shrineOfResurrection",shrineOfResurrection);
        allRooms.put("outside",outsideShrineOfResurrection);
        allRooms.put("forestShrine",forestShrine);
        allRooms.put("bokoblinCamp",bokoblinCamp);
        allRooms.put("forest",forest);
        allRooms.put("tent",tent);
        allRooms.put("templeOfTime",templeOfTime);
        allRooms.put("easternAbbey",easternAbbey);
        allRooms.put("mountHylia",mountHylia);
        allRooms.put("riverOfTheDead",riverOfTheDead);

        // initialise room exits
        shrineOfResurrection.setExit("north", outsideShrineOfResurrection);

        outsideShrineOfResurrection.setExit("west", forest);
        outsideShrineOfResurrection.setExit("east", tent);
        outsideShrineOfResurrection.setExit("south", shrineOfResurrection);

        forest.setExit("east",outsideShrineOfResurrection);
        forest.setExit("north",bokoblinCamp);
        forest.setExit("west",forestShrine);

        bokoblinCamp.setExit("south",forest);

        forestShrine.setExit("east",forest);

        tent.setExit("north", templeOfTime);
        tent.setExit("west", outsideShrineOfResurrection);
        tent.setExit("south",mountHylia);

        mountHylia.setExit("south",riverOfTheDead);
        mountHylia.setExit("north", tent);

        riverOfTheDead.setExit("north",mountHylia);

        templeOfTime.setExit("south",tent);
        templeOfTime.setExit("east",easternAbbey);

        easternAbbey.setExit("west", templeOfTime);


        // create the player character and start outside
        if (file.exists()){
            try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("player.ser"))) {
                player = (Character) in.readObject();
                System.out.println("Object has been deserialized:");
                } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        else {
            player = new Character("player", shrineOfResurrection);
            oldMan = new Friend("Old Man",tent);
            wolf = new Foe("Wolf",forest);

            oldMan.friendInventory.addTo(allItems.get("shirt"));
            shrineOfResurrection.roomInventory.addTo(allItems.get("torch"));
            forest.roomInventory.addTo(allItems.get("memory1"));
            forest.setCharacters(wolf);
            bokoblinCamp.roomInventory.addTo(allItems.get("trousers"));
            forestShrine.roomInventory.addTo(allItems.get("shoes"));
            tent.setCharacters(oldMan);
           // tent.roomInventory.addTo(allItems.get("shirt"));
            tent.roomInventory.addTo(allItems.get("apple"));
            templeOfTime.roomInventory.addTo(allItems.get("memory2"));
            easternAbbey.roomInventory.addTo(allItems.get("sword"));
            mountHylia.roomInventory.addTo(allItems.get("memory3"));
            riverOfTheDead.roomInventory.addTo(allItems.get("memory4"));
        }




    }

    private void createItems(){
        Item torch,shirt,shoes,trousers,sword,apple, memory1, memory2, memory3, memory4;

        torch = new Item("torch", "Illuminates and wards off the dark");
        shirt = new Item("shirt", "Keeps you warm and honest");
        shoes = new Item("shoes","protect your feet from environmental hazards");
        trousers = new Item("trousers", "covers your calves and shins");
        sword = new Item("sword","The Swords mighty glow inspires you");
        apple = new Item("apple","a red apple");
        memory1 = new Item("memory","N/A");
        memory2 = new Item("memory2","N/A");
        memory3 = new Item("memory3","N/A");
        memory4 = new Item("memory4","N/A");
        allItems.put("torch", torch);
        allItems.put("shirt",shirt);
        allItems.put("shoes",shoes);
        allItems.put("trousers",trousers);
        allItems.put("sword",sword);
        allItems.put("apple",apple);
        allItems.put("memory1", memory1);
        allItems.put("memory2", memory2);
        allItems.put("memory3", memory3);
        allItems.put("memory4", memory4);
    }

    public void play() {
        printWelcome();

        boolean finished = false;
        while (!finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing. Goodbye.");
    }

    private void printWelcome() {
        System.out.println();
        System.out.println("Welcome to The Legend Of The Hangover: Chunder Of The Wild!");
        System.out.println("You are playing as Link. The evil Ganon has been defeated. Now however you must fight against something much worse.");
        System.out.println("Celebrations took place last night and you are extremely hungover.");
        System.out.println("You must piece together the events of last night while gathering your clothing from around the Great Plateau.");
        System.out.println("You must be able to answer Zelda's interrogation to win");
        System.out.println("Best of Luck!");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        System.out.println(player.getCurrentRoom().getLongDescription());
    }

    private boolean processCommand(Command command) {
        String commandWord = command.getCommandWord();

        if (commandWord == null) {
            System.out.println("I don't understand your command...");
            return false;
        }

        switch (commandWord) {
            case "help":
                printHelp();
                break;
            case "go":
                goRoom(command);
                break;
            case "quit":
                if (command.hasSecondWord()) {
                    System.out.println("Quit what?");
                    return false;
                } else {
                return true; // signal to quit
                }
            case "teleport":
                teleport(command);
                break;
            case "look":
                look(command);
                break;
            case "take":
                take(command);
                break;
            case "inventory":
                inventory(command);
                break;
            case ("drop"):
                drop(command);
                break;
            case ("save"):
                savePlayer();
                break;
            case("talkto"):
                talkto(command);
            default:
                System.out.println("I don't know what you mean...");
                break;
        }
        return false;
    }

    private void printHelp() {
        System.out.println("Your head is banging. Your stomach is brewing up a storm. Last night is a blur. \n You need to find your clothes and regain your memories ");
        System.out.print("Your command words are: ");
        parser.showCommands();
    }

    private void goRoom(Command command) {
        if (!command.hasSecondWord()) {
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        Room nextRoom = player.getCurrentRoom().getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        } else {
            player.setCurrentRoom(nextRoom);
            System.out.println(player.getCurrentRoom().getLongDescription());
        }
    }

    private void teleport(Command command) {


        if (!command.hasSecondWord()) {
            System.out.println("Teleport where?");
            return;
        }

        String stringTPRoom = command.getSecondWord();
        if(allRooms.containsKey(stringTPRoom)){
            player.setCurrentRoom(allRooms.get(stringTPRoom));
            System.out.println(player.getCurrentRoom().getLongDescription());

        }
        }

    private void look(Command command){
        if (!command.hasSecondWord()) {
            player.getCurrentRoom().getItems();
            if ((player.getCurrentRoom() == allRooms.get("tent")) ){
                player.getCurrentRoom().getFriendCharacters();
            }
            else if ((player.getCurrentRoom() ==  allRooms.get("forest"))){
                player.getCurrentRoom().getFoeCharacters();
            }

        }

    }

    private void take(Command command){
        if (!command.hasSecondWord()) {
            System.out.println("Take what?");
            return;
        }
        String item = command.getSecondWord();
        if (player.getCurrentRoom().getInventory().contains(allItems.get(item))) {
            player.getCurrentRoom().takeItem(allItems.get(item));
            player.takeItem(allItems.get(item));
            System.out.println(item + " taken");
        }
        else{
            System.out.println("Object either does not exist or is not in room");
        }

    }

    private void drop(Command command){
        if (!command.hasSecondWord()) {
            System.out.println("drop what?");
            return;
        }
        String item = command.getSecondWord();
        player.dropItem(allItems.get(item));
        player.getCurrentRoom().dropItem(allItems.get(item));
        System.out.println(item +" dropped");
    }

    private void inventory(Command command){
        if (!command.hasSecondWord()){
            player.showInventory();
        }
    }

    private void talkto(Command command){

    }

    public static void main(String[] args) {
        ChunderOfTheWild game = new ChunderOfTheWild();
        game.play();

    }
}