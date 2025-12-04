package Model;

import java.util.HashMap;
import java.util.Scanner;

public class ChunderOfTheWild {
    private Parser parser;
    public Model.Character player;
    public HashMap<String, Room> allRooms = new HashMap<>();
    private HashMap<String, Item> allItems = new HashMap<>();
    private HashMap<String, Friend> allFriends = new HashMap<>();
    private HashMap<String, Foe> allFoes = new HashMap<>();
    private Friend oldMan;
    private Foe wolf;
    private Friend zelda;

    public ChunderOfTheWild() {
        createItems();
        createRooms();
        parser = new Parser();
    }

    private void createRooms() {
        Room shrineOfResurrection, outsideShrineOfResurrection, forestShrine, bokoblinCamp, forest, tent, templeOfTime, easternAbbey, mountHylia, riverOfTheDead;

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

        player = new Character("player", shrineOfResurrection, 100);

        allFriends.put("oldman", oldMan);
        allFoes.put("wolf", wolf);
        allFriends.put("zelda", zelda);

        oldMan.friendInventory.add(allItems.get("shirt"));
        shrineOfResurrection.roomInventory.add(allItems.get("torch"));
        forest.roomInventory.add(allItems.get("memory1"));
        bokoblinCamp.roomInventory.add(allItems.get("trousers"));
        forestShrine.roomInventory.add(allItems.get("shoes"));
        tent.roomInventory.add(allItems.get("apple"));
        templeOfTime.roomInventory.add(allItems.get("memory2"));
        easternAbbey.roomInventory.add(allItems.get("sword"));
        mountHylia.roomInventory.add(allItems.get("memory3"));
        riverOfTheDead.roomInventory.add(allItems.get("memory4"));
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

    public String printWelcome() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n\n" +
                "██╗    ██╗███████╗██╗      ██████╗ ██████╗ ███╗   ███╗███████╗\n" +
                "██║    ██║██╔════╝██║     ██╔════╝██╔═══██╗████╗ ████║██╔════╝\n" +
                "██║ █╗ ██║█████╗  ██║     ██║     ██║   ██║██╔████╔██║█████╗  \n" +
                "██║███╗██║██╔══╝  ██║     ██║     ██║   ██║██║╚██╔╝██║██╔══╝  \n" +
                "╚███╔███╔╝███████╗███████╗╚██████╗╚██████╔╝██║ ╚═╝ ██║███████╗\n");
        sb.append("You are playing as Link. The evil Ganon has been defeated. Now however you must fight against something much worse.\n");
        sb.append("Celebrations took place last night and you are extremely hungover.\n");
        sb.append("You must piece together the events of last night while gathering your clothing from around the Great Plateau.\n");
        sb.append("You must be able to answer Zelda's interrogation to win\n");
        sb.append("Best of Luck!\n");
        sb.append("Type 'help' if you need help.\n");
        sb.append(player.getCurrentRoom().getLongDescription());
        return sb.toString();
    }

    public String processCommandString(String input) {
        Command command = parser.getGuiCommand(input);
        return processCommand(command);
    }

    public String processCommand(Command command) {
        Commands commandWord = command.getCommandWord();
        if (commandWord == null) return "I don't understand your command...";

        switch (commandWord) {
            case help:
                return printHelp();
            case go:
                return goRoom(command);
            case quit:
                if (command.hasSecondWord()) return "Quit what?";
                return gameOver();
            case teleport:
                return teleport(command);
            case look:
                return look(command);
            case take:
                return take(command);
            case inventory:
                return inventory(command);
            case drop:
                return drop(command);
            case talkto:
                return talkto(command);
            case fight:
                return fight(command);
            default:
                return "I don't know what you mean...";
        }
    }

    private String printHelp() {
        StringBuilder sb = new StringBuilder();
        sb.append("Your head is banging. Your stomach is brewing up a storm. Last night is a blur. \nYou need to find your clothes and regain your memories.\n");
        sb.append("Your command words are: ").append(parser.showCommands()).append("\n");
        return sb.toString();
    }

    private String goRoom(Command command) {
        if (!command.hasSecondWord()) return "Go where?";
        String direction = command.getSecondWord();
        Room nextRoom = player.getCurrentRoom().getExit(direction);
        if (nextRoom == null) return "There is nothing in this direction!";
        if ((player.getCurrentRoom() == allRooms.get("forest") && player.getCurrentRoom().getFoeCharacters().contains(allFoes.get("wolf")))
                && nextRoom == allRooms.get("forestshrine")) {
            return "The wolf snarls as it stands in between you and the west";
        }
        if ((player.getCurrentRoom() == allRooms.get("mounthylia") && !player.getInventory().getAll().contains(allItems.get("shoes")))
                && nextRoom == allRooms.get("riverofthedead")) {
            return "You walk a few steps forward but quickly realise the ground is too cold.\nYou turn around back to Mount Hylia. Maybe you need something to protect your feet???";
        }
        player.setCurrentRoom(nextRoom);
        return player.getCurrentRoom().getLongDescription();
    }

    private String teleport(Command command) {
        if (!command.hasSecondWord()) return "Teleport where?";
        String stringTPRoom = command.getSecondWord();
        if (allRooms.containsKey(stringTPRoom)) {
            player.setCurrentRoom(allRooms.get(stringTPRoom));
            return player.getCurrentRoom().getLongDescription();
        }
        return "Invalid teleport location!";
    }

    private String look(Command command) {
        StringBuilder sb = new StringBuilder();
        if (!command.hasSecondWord()) {
            sb.append("In the ").append(player.getCurrentRoom().getName()).append(" you can see...\n");
            sb.append(player.getCurrentRoom().getItems()).append("\n");
            if ((player.getCurrentRoom() == allRooms.get("tent")) || (player.getCurrentRoom() == allRooms.get("shrineofresurrection"))) {
                sb.append("Oh! you look around and there seems to be someone here.\n");
                sb.append(player.getCurrentRoom().getFriendCharacters()).append("\n");
            } else if (player.getCurrentRoom() == allRooms.get("forest")) {
                sb.append("You are startled to see a wolf in the western direction. It seems to be guarding something.\n");
                sb.append(player.getCurrentRoom().showFoeCharacters()).append("\n");
            }
        }
        return sb.toString();
    }

    private String take(Command command) {
        if (!command.hasSecondWord()) return "Take what?";
        String item = command.getSecondWord();
        if (player.getCurrentRoom().getInventory().contains(allItems.get(item))) {
            player.getCurrentRoom().takeItem(allItems.get(item));
            player.takeItem(allItems.get(item));
            return item + " taken";
        } else {
            return "Object either does not exist or is not in room";
        }
    }

    private String drop(Command command) {
        if (!command.hasSecondWord()) return "Drop what?";
        String item = command.getSecondWord();
        player.dropItem(allItems.get(item));
        player.getCurrentRoom().dropItem(allItems.get(item));
        return item + " dropped";
    }


    private String inventory(Command command) {
        return player.showInventory();
    }



    private String talkto(Command command) {
        if (!command.hasSecondWord()) return "Talk to who?";
        String npc = command.getSecondWord();
        StringBuilder sb = new StringBuilder();
        Scanner input = new Scanner(System.in);

        if (player.getCurrentRoom() == allRooms.get("tent")) {
            try {
                if (!player.getInventory().getAll().contains(allItems.get("shirt"))) {
                    sb.append(allFriends.get(npc).getFriendName()).append(": I have your shirt\n");
                    sb.append("...\n");
                    sb.append("After a wild back and forth, the old man gives you back your shirt. It came at a cost though.\nThe shirt smells like chunder!\n");
                    player.takeItem(allItems.get("shirt"));
                } else {
                    sb.append("I already gave you my shirt, now scram!!!\n");
                }
            } catch (Exception e) {
                sb.append("Something went wrong. Please try again\n");
            }
            return sb.toString();
        }

        if (player.getCurrentRoom() == allRooms.get("shrineofresurrection")) {
            try {
                sb.append(allFriends.get(npc).getFriendName())
                        .append(": You better have a good excuse for what happened last night. Where are your clothes??\n");
            } catch (Exception e) {}

            sb.append("[1] Explain what happened last night \n[2] Leave and try to figure out what happened last night\n");

            int talkChoice = input.nextInt();
            if (talkChoice == 2) {
                sb.append("You can leave and try figure out what happened last night.\n");
                return sb.toString();
            }

            // Choice 1: explaining to Zelda
            int memories = player.getInventory().countMemory();
            int clothes = player.getInventory().countClothes();

            if (memories == 4) {
                sb.append("Zelda: You have all your clothes!!!\n");
                if (clothes == 4) sb.append("Zelda: No way!!!! Now that you have explained everything to me, I understand\nI'm very sorry for being annoyed at you earlier\n\nZelda is not angry at you. You won the game without chundering\n");
                else if (clothes == 3) sb.append("Zelda: ... We must find your missing clothes though.\nZelda is not angry at you. You won the game without chundering. Slight headache.\n");
                else if (clothes == 2) sb.append("Zelda: I'm slightly annoyed at you, where are the clothes I bought you?\nZelda is somewhat angry at you. You won with bad headache.\n");
                else if (clothes == 1) sb.append("Zelda: I'm annoyed at you, where are the clothes I bought you?\nYou passed with bad headache and sore stomach.\n");
                else sb.append("Zelda: I'm not happy with you, where are the clothes I bought you?\nZelda is angry at you. Bad headache and sore stomach.\n");
            } else if (memories == 3) {
                if (clothes == 4) sb.append("Zelda: I mostly get what happened, there's still a piece missing. Not annoyed. Slight headache.\n");
                else if (clothes == 3) sb.append("Zelda: Somewhat annoyed. Missing some clothes. Slight headache.\n");
                else if (clothes == 2) sb.append("Zelda: Annoyed. Missing a few items of clothing. Bad headache and sore stomach.\n");
                else if (clothes == 1) sb.append("Zelda: Annoyed. Lost 3 items of clothing. Bad headache, upset stomach. Might chunder.\n");
                else sb.append("Zelda: Not annoyed. Lost a memory. Migraine, gurgling stomach. Might chunder.\n");
            } else if (memories == 2) {
                if (clothes == 4) sb.append("Zelda: Barely explained everything, very annoyed but clothes ok. Migraine and gurgling stomach. Chunder everywhere.\n");
                else sb.append("Zelda: Barely explained. Very annoyed. Chunder everywhere.\n");
            } else if (memories == 1) {
                sb.append("Zelda: You seriously don't remember last night? Only a little bit??? You lose and chunder everywhere!\n");
            } else {
                sb.append("Zelda: You seriously don't remember last night? You lose and chunder everywhere!\n");
            }

            // End the game for GUI
            sb.append(gameOver());
            return sb.toString();
        }

        return "Please check spelling or that there is an NPC in your current room.";
    }


    private String fight(Command command) {
        if (!command.hasSecondWord()) return "Fight what?";
        if (!allFoes.containsKey(command.getSecondWord())) return "No such foe!";
        if (player.getCurrentRoom() != allRooms.get("forest")) return "No enemies to fight here!";

        StringBuilder sb = new StringBuilder();
        Scanner input = new Scanner(System.in);
        boolean engaged = true;
        while (player.getHealth() > 0 && allFoes.get(command.getSecondWord()).getHealth() > 0 && engaged) {
            sb.append("Player Health: ").append(player.getHealth()).append("\n");
            sb.append("Wolf Health: ").append(allFoes.get(command.getSecondWord()).getHealth()).append("\n");
            sb.append("What would you like to do?: \nAttack[1] \nEat[2] \nRun Away[3]\n");
            int fightChoice = input.nextInt();
            int wolfDamage = (int) ((Math.random() * 20) + 5);
            player.reduceHealth(wolfDamage);
            sb.append("The wolf lunges at you! It bites you and deals ").append(wolfDamage).append(" damage!\n");

            switch (fightChoice) {
                case 1:
                    if (player.getInventory().getAll().contains(allItems.get("sword"))) {
                        allFoes.get(command.getSecondWord()).reduceHealth(25);
                    } else {
                        allFoes.get(command.getSecondWord()).reduceHealth(5);
                    }
                    if (allFoes.get(command.getSecondWord()).getHealth() < 0) {
                        sb.append("The wolf is dead, you may now continue to the west.\n");
                        player.getCurrentRoom().removeCharacter(allFoes.get(command.getSecondWord()));
                    }
                    if (player.getHealth() < 0) {
                        sb.append("The wolf ravaged you into bits\n");
                        sb.append(gameOver());
                    }
                    break;
                case 2:
                    if (player.getInventory().getAll().contains(allItems.get("apple"))) {
                        player.increaseHealth(allItems.get("apple").getHealingValue());
                        player.getInventory().getAll().remove(allItems.get("apple"));
                    } else sb.append("No consumables in your inventory.\n");
                    break;
                case 3:
                    sb.append("You successfully ran away from the Wolf\n");
                    engaged = false;
                    break;
                default:
                    sb.append("Something went wrong, please try again\n");
            }
        }
        return sb.toString();
    }

    private String gameOver() {
        return "\n\nGAME OVER!\nThanks for playing.\n";
    }
}
