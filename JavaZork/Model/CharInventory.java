package Model;

import java.util.ArrayList;

public class CharInventory {
    ArrayList<Friend> friends = new ArrayList<>();
    ArrayList<Foe> foes = new ArrayList<>();

    public CharInventory(){

    }


    public void setCharacter(Friend characterSetting){
        friends.add(characterSetting);
    }

    public void setCharacter(Foe characterSetting){
        foes.add(characterSetting);
    }

    public void removeCharacter(Character enemy){ foes.remove(enemy);    }

    public String getFriendCharacter() {
        if (friends.isEmpty()) return "No friends in this room.";
        StringBuilder sb = new StringBuilder();
        for (Friend chats : friends) {
            sb.append(chats.getFriendName()).append("\n");
        }
        return sb.toString().trim();
    }

    public ArrayList getFoeCharacter(){return foes; }
    public String showFoeCharacter() {
        if (foes.isEmpty()) return "No foes in this room.";
        StringBuilder sb = new StringBuilder();
        for (Foe chats : foes) {
            sb.append(chats.getFoeName()).append("\n");
        }
        return sb.toString().trim();
    }


}
