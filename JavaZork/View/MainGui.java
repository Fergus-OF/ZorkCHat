package View;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.*;
//import javafx.fxml.FXMLLoader;
import javafx.fxml.*;

import java.awt.*;

public class MainGui extends Application {


    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("ChunderGuiSB.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
