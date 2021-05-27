package test;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TestMainMenu extends Application {

    @Override
    public  void start(Stage stage) throws Exception{
        Parent root  = FXMLLoader.load(getClass().getResource("/treeScreen/MainMenu/MainMenu.fxml"));

        Scene scene = new Scene(root);
        stage.setTitle("Main Menu");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args){
        launch(args);
    }
}
