package Model;

import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

public class ChunderOfTheWild {
    private Parser parser;
    private Model.Character player;
    private HashMap<String, Room> allRooms = new HashMap<>();
    private HashMap<String, Item> allItems = new HashMap<>();
    private HashMap<String, Friend> allFriends = new HashMap<>();
    private HashMap<String, Foe> allFoes = new HashMap<>();
    File file = new File("game.ser");
    private Friend oldMan;
    private Foe wolf;
    private Friend zelda;

    public void savePlayer() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("game.ser"))) {
            out.writeObject(player);
            System.out.println("Object has been serialized to game.ser");
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
        shrineOfResurrection = new Room("Shrine of Resurrection","in Shrine of Resurrection", zelda);
        outsideShrineOfResurrection = new Room("Outside the Shrine of Resurrection","Outside the Shrine of Resurrection");
        forestShrine = new Room("Shrine in the forest of Spirits","in the Shrine in the Forest of Spirits");
        bokoblinCamp = new Room("Bokoblin Camp","in an Old Bokoblin Camp");
        forest = new Room("Forest of Spirits","in the Forest of Spirits", wolf);
        tent = new Room("tent","Standing infront of a tent", oldMan);
        templeOfTime = new Room("Temple of Time","in the Temple of Time");
        easternAbbey = new Room("Eastern Abbey","in the Eastern Abbey");
        mountHylia = new Room("Mount Hylia","Standing on the peak of Mount Hylia");
        riverOfTheDead = new Room("River of the Dead","Standing on the frozen surface of The River Of The Dead");


        allRooms.put("shrineofresurrection", shrineOfResurrection);
        allRooms.put("outside", outsideShrineOfResurrection);
        allRooms.put("forestshrine", forestShrine);
        allRooms.put("bokoblincamp", bokoblinCamp);
        allRooms.put("forest", forest);
        allRooms.put("tent", tent);
        allRooms.put("templeOftime", templeOfTime);
        allRooms.put("easternabbey", easternAbbey);
        allRooms.put("mounthylia", mountHylia);
        allRooms.put("riverofthedead", riverOfTheDead);

        // initialise room exits
        shrineOfResurrection.setExit("north", outsideShrineOfResurrection);

        outsideShrineOfResurrection.setExit("west", forest);
        outsideShrineOfResurrection.setExit("east", tent);
        outsideShrineOfResurrection.setExit("south", shrineOfResurrection);

        forest.setExit("east", outsideShrineOfResurrection);
        forest.setExit("north", bokoblinCamp);
        forest.setExit("west", forestShrine);

        bokoblinCamp.setExit("south", forest);

        forestShrine.setExit("east", forest);

        tent.setExit("north", templeOfTime);
        tent.setExit("west", outsideShrineOfResurrection);
        tent.setExit("south", mountHylia);

        mountHylia.setExit("south", riverOfTheDead);
        mountHylia.setExit("north", tent);

        riverOfTheDead.setExit("north", mountHylia);

        templeOfTime.setExit("south", tent);
        templeOfTime.setExit("east", easternAbbey);

        easternAbbey.setExit("west", templeOfTime);

        oldMan = new Friend("oldMan", tent);
        tent.setCharacters(oldMan);
        wolf = new Foe("wolf", forest, 50);
        forest.setCharacters(wolf);
        zelda = new Friend("zelda", shrineOfResurrection);
        shrineOfResurrection.setCharacters(zelda);

        // create the player character and start outside
        if (file.exists()) {
            try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("player.ser"))) {
                player = (Model.Character) in.readObject();
                System.out.println("Object has been deserialized:");
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            player = new Character("player", shrineOfResurrection, 100);




            allFriends.put("oldman", oldMan);
            allFoes.put("wolf", wolf);
            allFriends.put("zelda", zelda);

            oldMan.friendInventory.add(allItems.get("shirt"));
            shrineOfResurrection.roomInventory.add(allItems.get("torch"));

            forest.roomInventory.add(allItems.get("memory1"));

            bokoblinCamp.roomInventory.add(allItems.get("trousers"));
            forestShrine.roomInventory.add(allItems.get("shoes"));

            // tent.roomInventory.add(allItems.get("shirt"));
            tent.roomInventory.add(allItems.get("apple"));
            templeOfTime.roomInventory.add(allItems.get("memory2"));
            easternAbbey.roomInventory.add(allItems.get("sword"));
            mountHylia.roomInventory.add(allItems.get("memory3"));
            riverOfTheDead.roomInventory.add(allItems.get("memory4"));
        }


    }

    private void createItems() {
        Item torch, shirt, shoes, trousers, sword, apple, memory1, memory2, memory3, memory4;

        torch = new ItemUsable("torch", " lluminates and wards off the dark");
        shirt = new Clothes("shirt", " keeps you warm and modest. Theres a faint smell of old crusty body odour wafting from it.");
        shoes = new Clothes("shoes", " pair of shoes hanging off the shrine. They protect your feet from environmental hazards");
        trousers = new Clothes("trousers", " ");
        sword = new ItemUsable("sword", " Sword calls to you! Your sword stands cleanly upright in the middle of vomit. ");
        apple = new Food("apple", " glistens on the ground, waiting to be eaten", 10);
        memory1 = new Memories("memory1", "Suddenly comes flooding back to you. \n");
        memory2 = new Memories("memory2", "Suddenly comes flooding back to you.");
        memory3 = new Memories("memory3", "Suddenly comes flooding back to you.");
        memory4 = new Memories("memory4", "Suddenly comes flooding back to you.");
        allItems.put("torch", torch);
        allItems.put("shirt", shirt);
        allItems.put("shoes", shoes);
        allItems.put("trousers", trousers);
        allItems.put("sword", sword);
        allItems.put("apple", apple);
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
        System.out.println("\n" +
                "\n" +
                "██╗    ██╗███████╗██╗      ██████╗ ██████╗ ███╗   ███╗███████╗                 \n" +
                "██║    ██║██╔════╝██║     ██╔════╝██╔═══██╗████╗ ████║██╔════╝                 \n" +
                "██║ █╗ ██║█████╗  ██║     ██║     ██║   ██║██╔████╔██║█████╗                   \n" +
                "██║███╗██║██╔══╝  ██║     ██║     ██║   ██║██║╚██╔╝██║██╔══╝                   \n" +
                "╚███╔███╔╝███████╗███████╗╚██████╗╚██████╔╝██║ ╚═╝ ██║███████╗                 \n" +
                " ╚══╝╚══╝ ╚══════╝╚══════╝ ╚═════╝ ╚═════╝ ╚═╝     ╚═╝╚══════╝                 \n" +
                "                                                                               \n" +
                "████████╗ ██████╗     ██╗     ███████╗ ██████╗ ███████╗███╗   ██╗██████╗       \n" +
                "╚══██╔══╝██╔═══██╗    ██║     ██╔════╝██╔════╝ ██╔════╝████╗  ██║██╔══██╗      \n" +
                "   ██║   ██║   ██║    ██║     █████╗  ██║  ███╗█████╗  ██╔██╗ ██║██║  ██║      \n" +
                "   ██║   ██║   ██║    ██║     ██╔══╝  ██║   ██║██╔══╝  ██║╚██╗██║██║  ██║      \n" +
                "   ██║   ╚██████╔╝    ███████╗███████╗╚██████╔╝███████╗██║ ╚████║██████╔╝      \n" +
                "   ╚═╝    ╚═════╝     ╚══════╝╚══════╝ ╚═════╝ ╚══════╝╚═╝  ╚═══╝╚═════╝       \n" +
                "                                                                               \n" +
                " ██████╗ ███████╗    ████████╗██╗  ██╗███████╗                                 \n" +
                "██╔═══██╗██╔════╝    ╚══██╔══╝██║  ██║██╔════╝                                 \n" +
                "██║   ██║█████╗         ██║   ███████║█████╗                                   \n" +
                "██║   ██║██╔══╝         ██║   ██╔══██║██╔══╝                                   \n" +
                "╚██████╔╝██║            ██║   ██║  ██║███████╗                                 \n" +
                " ╚═════╝ ╚═╝            ╚═╝   ╚═╝  ╚═╝╚══════╝                                 \n" +
                "                                                                               \n" +
                "██╗  ██╗ █████╗ ███╗   ██╗ ██████╗  ██████╗ ██╗   ██╗███████╗██████╗           \n" +
                "██║  ██║██╔══██╗████╗  ██║██╔════╝ ██╔═══██╗██║   ██║██╔════╝██╔══██╗██╗       \n" +
                "███████║███████║██╔██╗ ██║██║  ███╗██║   ██║██║   ██║█████╗  ██████╔╝╚═╝       \n" +
                "██╔══██║██╔══██║██║╚██╗██║██║   ██║██║   ██║╚██╗ ██╔╝██╔══╝  ██╔══██╗██╗       \n" +
                "██║  ██║██║  ██║██║ ╚████║╚██████╔╝╚██████╔╝ ╚████╔╝ ███████╗██║  ██║╚═╝       \n" +
                "╚═╝  ╚═╝╚═╝  ╚═╝╚═╝  ╚═══╝ ╚═════╝  ╚═════╝   ╚═══╝  ╚══════╝╚═╝  ╚═╝          \n" +
                "                                                                               \n" +
                " ██████╗██╗  ██╗██╗   ██╗███╗   ██╗██████╗ ███████╗██████╗                     \n" +
                "██╔════╝██║  ██║██║   ██║████╗  ██║██╔══██╗██╔════╝██╔══██╗                    \n" +
                "██║     ███████║██║   ██║██╔██╗ ██║██║  ██║█████╗  ██████╔╝                    \n" +
                "██║     ██╔══██║██║   ██║██║╚██╗██║██║  ██║██╔══╝  ██╔══██╗                    \n" +
                "╚██████╗██║  ██║╚██████╔╝██║ ╚████║██████╔╝███████╗██║  ██║                    \n" +
                " ╚═════╝╚═╝  ╚═╝ ╚═════╝ ╚═╝  ╚═══╝╚═════╝ ╚══════╝╚═╝  ╚═╝                    \n" +
                "                                                                               \n" +
                " ██████╗ ███████╗    ████████╗██╗  ██╗███████╗    ██╗    ██╗██╗██╗     ██████╗ \n" +
                "██╔═══██╗██╔════╝    ╚══██╔══╝██║  ██║██╔════╝    ██║    ██║██║██║     ██╔══██╗\n" +
                "██║   ██║█████╗         ██║   ███████║█████╗      ██║ █╗ ██║██║██║     ██║  ██║\n" +
                "██║   ██║██╔══╝         ██║   ██╔══██║██╔══╝      ██║███╗██║██║██║     ██║  ██║\n" +
                "╚██████╔╝██║            ██║   ██║  ██║███████╗    ╚███╔███╔╝██║███████╗██████╔╝\n" +
                " ╚═════╝ ╚═╝            ╚═╝   ╚═╝  ╚═╝╚══════╝     ╚══╝╚══╝ ╚═╝╚══════╝╚═════╝ \n" +
                "\n");
        System.out.println("You are playing as Link. The evil Ganon has been defeated. Now however you must fight against something much worse.");
        System.out.println("Celebrations took place last night and you are extremely hungover.");
        System.out.println("You must piece together the events of last night while gathering your clothing from around the Great Plateau.");
        System.out.println("You must be able to answer Zelda's interrogation to win");
        System.out.println("Best of Luck!");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        System.out.println(player.getCurrentRoom().getLongDescription());
    }

    public String processCommandString(String input){
        Command command = parser.getGuiCommand(input);
        return processCommand(command);

    }

    public boolean processCommand(Command command) {
        Commands commandWord = command.getCommandWord();

        if (commandWord == null) {
            System.out.println("I don't understand your command...");
            return false;
        }

        switch (commandWord) {
            case help:
                printHelp();
                break;
            case go:
                goRoom(command);
                break;
            case quit:
                if (command.hasSecondWord()) {
                    System.out.println("Quit what?");
                    return false;
                } else {
                    gameOver();
                    return true; // signal to quit
                }
            case teleport:
                teleport(command);
                break;
            case look:
                look(command);
                break;
            case take:
                take(command);
                break;
            case inventory:
                inventory(command);
                break;
            case drop:
                drop(command);
                break;
            case save:
                savePlayer();
                break;
            case talkto:
                talkto(command);
                break;
            case fight:
                fight(command);
                break;
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

    private String goRoom(Command command) {
        if (!command.hasSecondWord()) {
            return("Go where?");
        }

        String direction = command.getSecondWord();

        Room nextRoom = player.getCurrentRoom().getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is nothing in this direction!");
        } else {
            if (((player.getCurrentRoom() == allRooms.get("forest")) && (player.getCurrentRoom().getFoeCharacters().contains(allFoes.get("wolf"))))&& (nextRoom == allRooms.get("forestshrine"))) {
                System.out.println("The wolf snarls as it stands inbetween you and the west");
            }
            else if (((player.getCurrentRoom() == allRooms.get("mounthylia"))&&(!player.getInventory().getAll().contains(allItems.get("shoes"))))&& (nextRoom == allRooms.get("riverofthedead"))){
                System.out.println("You walk a few steps forward but quickly realise the ground is too cold. \nYou turn around back to Mount Hylia. Maybe you need something to protect your feet???");
            }

            else {
                player.setCurrentRoom(nextRoom);
                System.out.println(player.getCurrentRoom().getLongDescription());
            }
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
            System.out.println("In the " + player.getCurrentRoom().getName() + " you can see..");
            player.getCurrentRoom().getItems();
            //System.out.println((player.getCurrentRoom().getFoeCharacters().contains(allFoes.get("wolf"))));
           // System.out.println((player.getCurrentRoom().getFoeCharacters().contains(allFoes.get("wolf"))));
           // System.out.println(!player.getInventory().getInventories().contains(allItems.get("shoes")));
            //System.out.println(player.getCurrentRoom() == allRooms.get("mountHylia"));
            //System.out.println((player.getCurrentRoom() == allRooms.get("mountHylia"))&&(!player.getInventory().getInventories().contains(allItems.get("shoes"))));
            if ((player.getCurrentRoom() == allRooms.get("tent")) || (player.getCurrentRoom() == allRooms.get("shrineofresurrection"))){
                System.out.println("Oh! you look around and there seems to be someone here ");
                player.getCurrentRoom().getFriendCharacters();
            }
            else if ((player.getCurrentRoom() ==  allRooms.get("forest"))){
                System.out.println("You are startled to see a wolf in the western direction. It seems to be guarding something.");
                player.getCurrentRoom().showFoeCharacters();
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
            //System.out.println(player.getHealth());
        }
    }

    private void talkto(Command command){
        if (!command.hasSecondWord()) {
            System.out.println("Talk to who?");
            return;
        }
        String npc = command.getSecondWord();
        if (player.getCurrentRoom() == allRooms.get("tent")){

            try {
                if (!player.getInventory().getAll().contains(allItems.get("shirt"))){
                System.out.println(allFriends.get(npc).getFriendName() + ": I have your shirt");
                System.out.println("...");
                System.out.println("After a wild back and forth, the old man gives you back your shirt. It came at a cost though.\n The shirt smells like chunder!");
                player.takeItem(allItems.get("shirt"));}
                else{
                    System.out.println("I already gave you my shirt, now scram!!!");
                }
            } catch (Exception e) {
                System.out.print("Something went wrong. Please try again");
            }

        }
        else if (player.getCurrentRoom() == allRooms.get("shrineofresurrection")){
            Scanner input = new Scanner(System.in);
            try {
                System.out.println(allFriends.get(npc).getFriendName()+": You better have a good excuse for what happened last night. Where are your clothes??");
            } catch (Exception e) {
                System.out.print("");
            }
            System.out.println("[1] Explain what happened last night \n[2] Leave and try to figure out what happened last night");
            int talkChoice = input.nextInt();
            switch(talkChoice){
                case 1:
                    if (player.getInventory().countMemory() == 4 ){
                        System.out.println("Zelda: You have all your clothes!!!");
                        if (player.getInventory().countClothes() == 4 ){
                            System.out.println("Zelda: No way!!!! Now that you have explained everything to me, I understand\n I'm very sorry for being annoyed at you earlier");
                            System.out.println("\n\n\n Zelda is not angry at you. You won the game without chundering");
                            gameOver();
                        }
                        else if (player.getInventory().countClothes() == 3 ){
                            System.out.println("Zelda: No way!!!! Now that you have explained everything to me, I understand\n I'm sorry for being annoyed at you earlier\n We must find your missing clothes though.");
                            System.out.println("\n\n\n Zelda is not angry at you. You won the game without chundering. \n There is a slight banging in your head still.");
                            gameOver();
                        }
                        else if (player.getInventory().countClothes() == 2 ){
                            System.out.println("Zelda: No way!!!! Now that you have explained everything to me, I understand\n I'm slightly annoyed at you, where are the clothes I bought you.");
                            System.out.println("\n\n\n Zelda is somewhat angry at you. You won the game with a bad headache.");
                            gameOver();
                        }
                        else if (player.getInventory().countClothes() == 1 ){
                            System.out.println("Zelda: No way!!!! Now that you have explained everything to me, I understand\n I'm annoyed at you, where are the clothes I bought you. You cant be serious.");
                            System.out.println("\n\n\n Zelda is somewhat angry at you. You passed the game with a bad headache and a sore stomach.");
                            gameOver();
                        }
                        else if (player.getInventory().countClothes() == 0 ){
                            System.out.println("Zelda: No way!!!! Now that you have explained everything to me, I understand\n I'm not happy with you, where are the clothes I bought you. You're never allowed to drink again.");
                            System.out.println("\n\n\n Zelda is angry at you. You passed the game with a bad headache and sore stomach.");
                            gameOver();
                        }
                    }
                    else if (player.getInventory().countMemory() == 3 ){
                        if (player.getInventory().countClothes() == 4 ){
                            System.out.println("Zelda: No way!!!! Now that you have mostly explained everything to me, I mostly get what happened, there's still a piece missing.\n I'm not annoyed at you.");
                            System.out.println("\n\n\n Zelda is not angry at you.!!! You won the game with a slight headache.");
                            gameOver();
                        }
                        else if (player.getInventory().countClothes() == 3 ){
                            System.out.println("Zelda: No way!!!! Now that you have mostly explained everything to me, I mostly get what happened, there's still a piece missing.\n I'm somewhat annoyed at you. You're missing some of your clothes");
                            System.out.println("\n\n\n Zelda is not angry at you.!!! You won the game with a slight headache.");
                            gameOver();
                        }
                        else if (player.getInventory().countClothes() == 2 ){
                            System.out.println("Zelda: No way!!!! Now that you have mostly explained everything to me, I mostly get what happened, there's still a piece missing.\n I'm annoyed at you. You're missing a few items of clothing. ");
                            System.out.println("\n\n\n Zelda is kind of angry at you.!!! You passed the game with a bad headache and a sore stomach.");
                            gameOver();
                        }
                        else if (player.getInventory().countClothes() == 1 ){
                            System.out.println("Zelda: No way!!!! Now that you have mostly explained everything to me, I mostly get what happened, there's still a piece missing.\n I'm annoyed at you. How the hell have you lost 3 items of clothing!!!");
                            System.out.println("\n\n\n Zelda is angry at you.!!! You lost the game with a bad headache and a really upset stomach!!! You feel like you might chunder.");
                            gameOver();

                        }
                        else if (player.getInventory().countClothes() == 0 ){
                            System.out.println("Zelda: No way!!!! Now that you have mostly explained everything to me, I mostly get what happened, there's still a piece missing.\n I'm not annoyed at you.");
                            System.out.println("\n\n\n Zelda is angry at you.!!! You lost the game with a Migraine and gurgling stomach. You feel like chundering!!");
                            gameOver();
                        }
                    }
                    else if(player.getInventory().countMemory() == 2 ){
                        if (player.getInventory().countClothes() == 4 ){
                            System.out.println("Zelda: No way!!!! Now that you have barely explained everything to me, I hardly get what happened, there's still many pieces missing.\n I'm very annoyed at you but atleast you have your clothes.");
                            System.out.println("\n\n\n Zelda is angry at you.!!!  You lost the game with a Migraine and gurgling stomach. You chunder everywhere!!");
                            gameOver();
                        }
                        else if (player.getInventory().countClothes() == 3 ){
                            System.out.println("Zelda: No way!!!! Now that you have barely explained everything to me, I hardly get what happened, there's still many pieces missing.\n I'm very annoyed at you but at least you have most of your clothes.");
                            System.out.println("\n\n\n Zelda is angry at you.!!! You lost the game with a Migraine and gurgling stomach. You chunder everywhere!!!!!.");
                            gameOver();
                        }
                        else if (player.getInventory().countClothes() == 2 ){

                            System.out.println("Zelda: No way!!!! Now that you have barely explained everything to me, I hardly get what happened, there's still many pieces missing.\n I'm very annoyed at you but at least you have most of your clothes.");
                            System.out.println("\n\n\n Zelda is angry at you.!!! You lost the game with a Migraine and gurgling stomach. You chunder everywhere!!!!!.");
                            gameOver();
                        }
                        else if (player.getInventory().countClothes() == 1 ){

                            System.out.println("Zelda: No way!!!! Now that you have barely explained everything to me, I hardly get what happened, there's still many pieces missing.\n I'm very annoyed at you but and you're missing most of your clothes");
                            System.out.println("\n\n\n Zelda is angry at you.!!! You lost the game with a Migraine and gurgling stomach. You chunder everywhere!!!!!.");
                            gameOver();
                        }
                        else if (player.getInventory().countClothes() == 0 ){

                            System.out.println("Zelda: No way!!!! Now that you have barely explained everything to me, I hardly get what happened, there's still many pieces missing.\n I'm very annoyed at you and youve lost all of your clothes!!!.");
                            System.out.println("\n\n\n Zelda is angry at you.!!! You lost the game with a Migraine and gurgling stomach. You chunder everywhere!!!!!.");
                            gameOver();
                        }
                    }
                    else if (player.getInventory().countMemory() == 1 ){
                        System.out.println("Zelda: You seriously don't remember last night? \n Zelda: Only a little bit??? I cant believe you. Don't even bother talking to me");
                        System.out.println("\n\n\n Zelda is FUMING at you. The stress of the future mixes poorly with the events of last night which you don't remember. \n YOU LOSE THE GAME AND CHUNDER EVERYWHERE!!!! ");
                        gameOver();
                    }
                    else if (player.getInventory().countMemory() == 0 ){
                        System.out.println("Zelda: You seriously don't remember last night? \n Zelda: I cant believe you. Don't even bother talking to me");
                        System.out.println("\n\n\n Zelda is RAGING at you. The stress of the future mixes poorly with the events of last night which you don't remember. \n YOU LOSE THE GAME AND CHUNDER EVERYWHERE!!!! ");
                        gameOver();
                    }
                    break;
                case 2:
                    System.out.println("You can leave and try figure out what happened last night.");
                    break;
                default:
                    break;
            }

        }
        else{
            System.out.println("Please check Spelling or that there is an NPC in your current room.");
            return;
        }

    }

    private void fight(Command command){
        if (!command.hasSecondWord()){
          System.out.println("Fight what?");
          return;
        }
        if (allFoes.containsKey(command.getSecondWord())) {
            if (player.getCurrentRoom() == allRooms.get("forest")) {
                Scanner input = new Scanner(System.in);
                Boolean engaged = true;
                while (((player.getHealth() > 0) && (allFoes.get(command.getSecondWord()).getHealth() > 0)) && (engaged)) {
                    System.out.println("Player Health: " + player.getHealth());
                    System.out.println("Wolf Health: " + allFoes.get(command.getSecondWord()).getHealth());
                    System.out.println("What would you like to do?: \nAttack[1] \nEat[2] \nRun Away[3]\n");
                    int fightChoice = 0;
                    try {
                        fightChoice = (input.nextInt());
                    } catch (Exception e) {
                        System.out.println("Please enter a valid number.");
                        continue;
                    }
                    int wolfDamage = (int)((Math.random() * 20)+ 5);
                    System.out.println("The wolf lunges at you! It bites you and deals " +wolfDamage + " damage!");
                    player.reduceHealth(wolfDamage);
                    switch (fightChoice) {
                        case (1):
                            if (player.getInventory().getAll().contains(allItems.get("sword"))){
                            allFoes.get(command.getSecondWord()).reduceHealth(25);
                            }
                            else {
                                allFoes.get(command.getSecondWord()).reduceHealth(5);
                            }

                            if (allFoes.get(command.getSecondWord()).getHealth() < 0) {
                                System.out.println("The wolf is dead, you may now continue to the west.");
                                player.getCurrentRoom().removeCharacter(allFoes.get(command.getSecondWord()));
                            }
                            if (player.getHealth() <0){
                                System.out.println("The wolf ravaged you into bits");
                                gameOver();
                            }
                            continue;
                        case (2):
                            if (player.getInventory().getAll().contains(allItems.get("apple"))) {
                                player.increaseHealth(allItems.get("apple").getHealingValue());
                                player.getInventory().getAll().remove(allItems.get("apple"));
                            }
                            else{
                                System.out.println("No consumables in your inventory. ");
                            }
                            continue;
                        case (3):
                            System.out.println("You successfully ran away from the Wolf");
                            engaged = false;
                            continue;
                        default:
                            System.out.println("Something went wrong, please try again");
                            continue;
                    }
                }

            }
        }
        else {

        }
    }


    private void gameOver(){
        System.out.println(" \n \n \n \n" +
                "\n" +
                "/========================================================\\\n" +
                "||   ▄██████▄     ▄████████   ▄▄▄▄███▄▄▄▄      ▄████████||\n" +
                "||  ███    ███   ███    ███ ▄██▀▀▀███▀▀▀██▄   ███    ███||\n" +
                "||  ███    █▀    ███    ███ ███   ███   ███   ███    █▀ ||\n" +
                "|| ▄███          ███    ███ ███   ███   ███  ▄███▄▄▄    ||\n" +
                "||▀▀███ ████▄  ▀███████████ ███   ███   ███ ▀▀███▀▀▀    ||\n" +
                "||  ███    ███   ███    ███ ███   ███   ███   ███    █▄ ||\n" +
                "||  ███    ███   ███    ███ ███   ███   ███   ███    ███||\n" +
                "||  ████████▀    ███    █▀   ▀█   ███   █▀    ██████████||\n" +
                "||                                                      ||\n" +
                "|| ▄██████▄   ▄█    █▄     ▄████████    ▄████████       ||\n" +
                "||███    ███ ███    ███   ███    ███   ███    ███       ||\n" +
                "||███    ███ ███    ███   ███    █▀    ███    ███       ||\n" +
                "||███    ███ ███    ███  ▄███▄▄▄      ▄███▄▄▄▄██▀       ||\n" +
                "||███    ███ ███    ███ ▀▀███▀▀▀     ▀▀███▀▀▀▀▀         ||\n" +
                "||███    ███ ███    ███   ███    █▄  ▀███████████       ||\n" +
                "||███    ███ ███    ███   ███    ███   ███    ███       ||\n" +
                "|| ▀██████▀   ▀██████▀    ██████████   ███    ███       ||\n" +
                "||                                     ███    ███       ||\n" +
                "\\========================================================/\n" +
                "\n");
                //processCommand();
    }

    public static void main(String[] args) {
        ChunderOfTheWild game = new ChunderOfTheWild();
        game.play();

    }
}