package treeScreen.MainMenu;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import test.Test;

public class MainMenuController {

    @FXML
    private TextField tfKBalancedTree;

    @FXML
    private TextField tfKBalancedBinaryTree;


    @FXML
    void buttonGenericTreePressed(ActionEvent event) {
        new Test();
    }

    @FXML
    void buttonBinaryTreePressed(ActionEvent event) {

    }

    @FXML
    void buttonBalancedTreePressed(ActionEvent event) {

    }

    @FXML
    void buttonBalancedBinaryTreePressed(ActionEvent event) {

    }

}
