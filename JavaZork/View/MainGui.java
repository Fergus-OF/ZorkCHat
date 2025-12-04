package View;

import Control.GuiController;
import Model.ChunderOfTheWild;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;

public class MainGui extends Application {

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("ChunderGuiSB.fxml"));
        Parent root = loader.load();


        GuiController controller = loader.getController();


        controller.guiControllerInit(new ChunderOfTheWild(), this);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
