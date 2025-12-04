package Control;


import Model.ChunderOfTheWild;
import Model.Command;
import View.MainGui;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class GuiController {

    private ChunderOfTheWild game;
    private MainGui gui;
    public GuiController(){
    }
    public void guiControllerInit(ChunderOfTheWild game, MainGui gui){
        this.game = game;
        this.gui = gui;
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
        printOut(sb.toString());
    }
    @FXML

//    public String processCommand(Command command){
//        String output = String.valueOf(game.processCommand(command));
//        if (output.equals("quit")){
//            return "quit";
//        }
//        return output;
//    }



    public void north(ActionEvent e){
        String response = game.processCommandString("go north");
        getRoom();
        printOut(response);
    }

    public void south(ActionEvent e){
        String response = game.processCommandString("go south");
        getRoom();
        printOut(response);
    }

    public void east(ActionEvent e){
        String response = game.processCommandString("go east");
        getRoom();
        printOut(response);
    }

    public void west(ActionEvent e){
        String response = game.processCommandString("go west");
        getRoom();
        printOut(response);
    }

    @FXML
    TextField inputField;

    @FXML
    TextArea outputArea;

    @FXML
    ImageView mainImageView;

   public void processCommand(ActionEvent event) {
        String input = inputField.getText().trim();
        if (!input.isEmpty()){
            inputField.clear();
            if (game != null){
                String response;
                response = game.processCommandString(input);
                getRoom();
                printOut(response);
            }
        }
   }

   private void printOut(String text) {

       outputArea.appendText(text + "\n");
   }


   public void getRoom(){
       if (game.player.getCurrentRoom() == game.allRooms.get("shrineofresurrection")){
            setImage("ShrineOfResurrectionTorch.jpg");
       }
       else if (game.player.getCurrentRoom() == game.allRooms.get("outside")){
           setImage("OutsideShrineOfResurrection.jpg");
       }
       else if (game.player.getCurrentRoom() == game.allRooms.get("tent")){
           setImage("TentApple.jpg");
       }
       else if (game.player.getCurrentRoom() == game.allRooms.get("bokoblincamp")){
           setImage("BokoblinCampTrousers.jpg");
       }
       else if (game.player.getCurrentRoom() == game.allRooms.get("riverofthedead")){
           setImage("BotW_River_of_the_Dead.jpg");
       }
       else if (game.player.getCurrentRoom() == game.allRooms.get("easternabbey")){
           setImage("EasternAbbeySword.jpg");
       }
       else if (game.player.getCurrentRoom() == game.allRooms.get("templeoftime")){
           setImage("TempleOfTime.jpg");
       }
       else if (game.player.getCurrentRoom() == game.allRooms.get("forest")){
           setImage("ForestOfSpiritsWolf.jpg");
       }
       else if (game.player.getCurrentRoom() == game.allRooms.get("forestShrine")){
           setImage("ForestShrineBirks.jpg");}
       else if (game.player.getCurrentRoom() == game.allRooms.get("mounthylia")){
           setImage("MountHylia.jpg");
       }

   }

    public void setImage(String imageName) {
        Image image = new Image(getClass().getResourceAsStream("/Images/" + imageName));
        mainImageView.setImage(image);
    }

}
