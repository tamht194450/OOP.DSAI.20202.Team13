package mainMenu;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import treeDataStructure.GenericTree;
import treeScreen.TreeScreen;
import treeScreen.TreeScreenController;

public class MainMenuScreen extends JFrame{
	private TreeScreen treeScreen;
	
	public MainMenuScreen() {
		super();
		JFXPanel fxPanel = new JFXPanel();
		this.add(fxPanel);
		
		this.setSize(600, 400);
		this.setTitle("Main Menu");
		this.setVisible(true);
	    this.addWindowListener(new WindowAdapter() {
	        public void windowClosing(WindowEvent we) {
	          int result = JOptionPane.showConfirmDialog(null,
	              "Do you want to Exit ?", "Exit Confirmation : ",
	              JOptionPane.YES_NO_OPTION);
	          if (result == JOptionPane.YES_OPTION)
	            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	          else if (result == JOptionPane.NO_OPTION)
	            setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	        }
	      });
		MainMenuController controller = new MainMenuController();
		controller.setMainMenuScreen(this);
		
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					FXMLLoader loader = new FXMLLoader(getClass()
							.getResource("MainMenu.fxml"));
					loader.setController(controller);
					Parent root = loader.load();
					fxPanel.setScene(new Scene(root));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		});
	}

	public void setTreeScreen(TreeScreen treeScreen) {
		this.treeScreen = treeScreen;
	}

	public TreeScreen getTreeScreen() {
		return treeScreen;
	}
}
