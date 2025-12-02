
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


}
