package treeScreen.MainMenu;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import treeDataStructure.GenericTree;
import treeDataStructure.Node;
import treeScreen.TreeScreen;

public class MainMenuController {
	private MainMenuScreen mainMenuScreen;

    @FXML
    private ScrollPane helpPane;
    
    @FXML
    private ToggleGroup treeType;

    @FXML
    private Pane treeTypePane;
    
    @FXML
    private TextField tfRootValue;

    @FXML
    void btnCreatePressed(ActionEvent event) {
    	helpPane.setVisible(false);
    	treeTypePane.setVisible(true);
    }

    @FXML
    void btnHelpPressed(ActionEvent event) {
    	helpPane.setVisible(true);
    	treeTypePane.setVisible(false);
    }
    @FXML
    void btnCreateTreePressed(ActionEvent event) {
    	Node root = new Node(Integer.parseInt(this.tfRootValue.getText()));
    	GenericTree tree = new GenericTree(root);
        TreeScreen treeScreen = new TreeScreen(tree);    
        this.mainMenuScreen.setTreeScreen(treeScreen);
        treeScreen.setMainMenu(mainMenuScreen);
        
    	this.mainMenuScreen.setVisible(false);
		this.mainMenuScreen.getTreeScreen().setVisible(true);
		
    }

	public void setMainMenuScreen(MainMenuScreen mainMenuScreen) {
		this.mainMenuScreen = mainMenuScreen;
	}

    @FXML
    void btnQuitPressed(ActionEvent event) {
    	int result = JOptionPane.showConfirmDialog(null,
	          "Do you want to Exit ?", "Exit Confirmation : ",
	          JOptionPane.YES_NO_OPTION);
	      if (result == JOptionPane.YES_OPTION)
	        System.exit(0);
    }

}
