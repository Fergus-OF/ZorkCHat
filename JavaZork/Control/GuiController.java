package Control;
//import package View;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class GuiController {

    @FXML

    public void north(ActionEvent e){
        System.out.println("North");
    }

    public void south(ActionEvent e){
        System.out.println("South");
    }

    public void east(ActionEvent e){
        System.out.println("East");
    }

    public void west(ActionEvent e){
        System.out.println("West");
    }
}
