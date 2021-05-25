package treeScreen;

import javafx.animation.KeyFrame;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.util.Duration;
import treeDataStructure.GenericTree;
import treeDataStructure.Node;

public class TreeScreenController {
	private GenericTree tree;
	private TreeScreen treeScreen;
	
    @FXML
    private TextField tfChild;
    
    @FXML
    private VBox boxControl;
    
    @FXML
    private HBox boxNevigate;
    
    @FXML
    private ToggleGroup toggleControl;

    @FXML
    private Pane drawingTreePane;

    private StackPane rootNode;

    @FXML
    private TextField tfParent;

	public TreeScreenController(GenericTree tree) {
		super();
		this.tree = tree;
		
	}
	public void initialize() {
		this.rootNode = this.tree.getRootNode();
		this.drawingTreePane.getChildren().add(this.rootNode);
		this.rootNode.setLayoutX(this.drawingTreePane.getPrefWidth()/2);
	}
	
    @FXML
    void btnAddNodePressed(ActionEvent event) {

    	Node childNode = new Node(Integer.parseInt(this.tfChild.getText()));
    	tree.insertNode(Integer.parseInt(tfParent.getText()), childNode);
    	
    	this.drawingTreePane.getChildren().add(childNode);
    	this.drawingTreePane.getChildren().add(childNode.getParentLine());
    	
    }
    
    @FXML
    void btnRunPressed(ActionEvent event) {
    	boxControl.setVisible(true);
    	this.tree.traversalBFS();
    }
    @FXML
    void btnPausePressed(ActionEvent event) {
    	tree.getTimeline().pause();
    	this.boxNevigate.setVisible(true);
    }
    @FXML
    void btnContinuePressed(ActionEvent event) {
    	this.boxNevigate.setVisible(false);
    	tree.getTimeline().play();
    }
    @FXML
    void btnBackPressed(ActionEvent event) {
    	tree.forwardBFS();
    }
}
