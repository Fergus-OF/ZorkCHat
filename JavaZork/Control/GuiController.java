package Control;
//import package View;


import Model.ChunderOfTheWild;
import Model.Command;
import View.MainGui;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class GuiController {

    private ChunderOfTheWild game;
    private MainGui gui;
    public GuiController(){
    }
    public void guiControllerInit(ChunderOfTheWild game, MainGui gui){
        this.game = game;
        this.gui = gui;
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
        game.processCommandString("go north");
    }

    public void south(ActionEvent e){
        game.processCommandString("go south");
    }

    public void east(ActionEvent e){
        game.processCommandString("go east");
    }

    public void west(ActionEvent e){
        game.processCommandString("go west");
    }

    @FXML
    TextField inputField;

    @FXML
    TextArea outputArea;

   public void processCommand(ActionEvent event) {
        String input = inputField.getText().trim();
        if (!input.isEmpty()){
            inputField.clear();
            if (game != null){
                String response;
                response = game.processCommandString(input);
                printOut(response);
            }
        }
   }

   private void printOut(String text) {
       outputArea.appendText(text + "\n");
   }

}
