package Model;

import java.util.HashMap;
import java.util.Map;

public class CommandWords {
     Map<String, Commands> validCommands;

    public CommandWords() {
        validCommands = new HashMap<>();
        validCommands.put("go",Commands.go);
        validCommands.put("quit",Commands.quit);
        validCommands.put("help",Commands.help);
        validCommands.put("look",Commands.look);
        validCommands.put("eat",Commands.eat);
        validCommands.put("teleport",Commands.teleport);
        validCommands.put("take",Commands.take);
        validCommands.put("drop",Commands.drop);
        validCommands.put("inventory",Commands.inventory);
        validCommands.put("save",Commands.save);
        validCommands.put("talkto",Commands.talkto);
        validCommands.put("fight",Commands.fight);
        validCommands.put("open", Commands.open);
    }

    public boolean isCommand(String commandWord) {
        return validCommands.containsKey(commandWord);
    }

    public String showAll() {
        return("Valid commands are: " + validCommands.keySet());


    }
    public Commands getCommands(String x){return validCommands.get(x);}
}
