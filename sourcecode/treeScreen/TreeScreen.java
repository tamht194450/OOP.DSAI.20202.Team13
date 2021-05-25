package treeScreen;

import java.io.IOException;

import javax.swing.JFrame;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import treeDataStructure.GenericTree;

public class TreeScreen extends JFrame{
	private TreeScreenController treeController;
	
	public TreeScreen(GenericTree tree) {
		super();
		JFXPanel fxPanel = new JFXPanel();
		this.add(fxPanel);
		
		this.setSize(1024, 768);
		this.setTitle("Tree");
		this.setVisible(true);
		
		TreeScreenController controller = new TreeScreenController(tree);
		
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					FXMLLoader loader = new FXMLLoader(getClass()
							.getResource("Tree.fxml"));
					loader.setController(controller);
					Parent root = loader.load();
					fxPanel.setScene(new Scene(root));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		});
	}
	
}
