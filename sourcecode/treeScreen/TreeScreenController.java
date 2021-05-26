package treeScreen;

import java.util.LinkedList;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
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
    private StackPane rootNode;

    @FXML
    private TextField tfDelete;

    @FXML
    private ToggleGroup toggleControl;

    @FXML
    private VBox boxControl;

    @FXML
    private TextField tfUpdateNewValue;

    @FXML
    private TextField tfChild;

    @FXML
    private Pane drawingTreePane;

    @FXML
    private TextField tfSearchFor;

    @FXML
    private HBox boxNevigate;

    @FXML
    private TextField tfUpdateOldValue;

    @FXML
    private ToggleGroup toggleTraversal;

    @FXML
    private TextField tfParent;
    
    @FXML
    private Pane bfsPseudoCode;

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
        if (!Node.listValue.contains(Integer.parseInt(this.tfChild.getText()))) {
            Node childNode = new Node(Integer.parseInt(this.tfChild.getText()));
            tree.insertNode(Integer.parseInt(tfParent.getText()), childNode);

            this.drawingTreePane.getChildren().add(childNode);
            this.drawingTreePane.getChildren().add(childNode.getParentLine());
        }
    }
    
    @FXML
    void btnRunPressed(ActionEvent event) {
    	boxControl.setVisible(true);
    	this.bfsPseudoCode.setVisible(true);
    	
    	KeyFrame step = new KeyFrame(Duration.seconds(1), 
				new EventHandler<ActionEvent>() {
			  		public void handle(ActionEvent event) {
			  			tree.forwardBFS();
			  		}
			} );
    	tree.setState(2);
    	tree.setQueue(new LinkedList<Node>());
    	tree.setTraveledNode(new LinkedList<Node>());
    	tree.getQueue().add(tree.getRootNode());
    	
    	Timeline timeline = new Timeline();
    	timeline.getKeyFrames().add(step);
    	timeline.setCycleCount(Timeline.INDEFINITE);
    	
    	tree.setTimeline(timeline);
    	tree.getTimeline().play();
    	
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
    	tree.backBFS();
    }

    @FXML
    void btnForwardPressed(ActionEvent event) {
    	tree.forwardBFS();
    }

    @FXML
    void btnDeleteNodePressed(ActionEvent event) {
        if (Node.listValue.indexOf(Integer.parseInt(this.tfDelete.getText())) != -1) {
            deleteNode(Integer.parseInt(this.tfDelete.getText()));
        }
    }

    public void deleteNode(int deletedValue) {
        Node deleteNode = tree.searchNode(deletedValue);
        Node.listValue.remove(Node.listValue.indexOf(deleteNode.getValue()));
        this.drawingTreePane.getChildren().remove(deleteNode.getParentLine());
        this.drawingTreePane.getChildren().remove(deleteNode);
        for (Node node:deleteNode.getChildNodes()){
            deleteNode(node.getValue());
        }
    }

    @FXML
    void buttonUpdate(ActionEvent event) {
        Node oldNode = tree.searchNode(Integer.parseInt(this.tfUpdateOldValue.getText()));
        Node newNode = new Node(Integer.parseInt(this.tfUpdateNewValue.getText()));

        this.drawingTreePane.getChildren().remove(oldNode);
        newNode.setLayoutX(oldNode.getLayoutX());
        newNode.setLayoutY(oldNode.getLayoutY());
        newNode.setChildNodes(oldNode.getChildNodes());
        this.drawingTreePane.getChildren().add(newNode);

    }

    @FXML
    void btnSearchPressed(ActionEvent event) {
        Node node = tree.searchNode(Integer.parseInt(this.tfSearchFor.getText()));
        node.getCircle().setFill(Color.LIGHTGREEN);

        Timeline timeline  = new Timeline();
    }
}
