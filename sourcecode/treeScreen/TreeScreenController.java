package treeScreen;

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

import java.util.LinkedList;

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
    	tree.backBFS();
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
        Node parent = deleteNode.getParentNode();
        parent.getChildNodes().remove(deleteNode);
        for (Node node:deleteNode.getChildNodes()){
            deleteNode(node.getValue());
        }
    }

    @FXML
    void buttonUpdate(ActionEvent event) {
        if (!Node.listValue.contains(Integer.parseInt(this.tfUpdateNewValue.getText()))) {
            Node oldNode = tree.searchNode(Integer.parseInt(this.tfUpdateOldValue.getText()));
            Node newNode = new Node(Integer.parseInt(this.tfUpdateNewValue.getText()));

            this.drawingTreePane.getChildren().remove(oldNode);
            newNode.setLayoutX(oldNode.getLayoutX());
            newNode.setLayoutY(oldNode.getLayoutY());
            this.drawingTreePane.getChildren().add(newNode);

            newNode.setChildNodes(oldNode.getChildNodes());
            Node parent = oldNode.getParentNode();
            parent.getChildNodes().add(newNode);
            Node.listValue.remove(Integer.parseInt(this.tfUpdateOldValue.getText()));
        } else {
            // todo
        }
    }

    @FXML
    void btnSearchPressed(ActionEvent event) {           // the same as method traversalBFS in GenericTree, only add an extra condition
	    Node searchedNode = tree.searchNode(Integer.parseInt(this.tfSearchFor.getText()));
        Timeline timeline = new Timeline();
        tree.setState(1);
        KeyFrame popQueue = new KeyFrame(Duration.seconds(1),
                new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent event) {
                        if (tree.getState() == 1) {
                            if (!tree.getQueue().isEmpty()) {
                                tree.setCurrentNode(tree.getQueue().getFirst());
                                tree.getQueue().removeFirst();
                                tree.getTraveledNode().add(tree.getCurrentNode());
                                tree.getCurrentNode().getCircle().setFill(Color.LIGHTBLUE);
                                tree.setState(2);

                                if (tree.getCurrentNode() == searchedNode){         //extra condition
                                    for (int i:Node.listValue){
                                        Node node = tree.searchNode(i);
                                        node.getCircle().setFill(Color.WHITE);
                                    }
                                    tree.getCurrentNode().getCircle().setFill(Color.GREEN);
                                    timeline.stop();
                                }

                            } else {
                                timeline.stop();
                            }
                        }
                    }
                } );
        KeyFrame pushQueue = new KeyFrame(Duration.seconds(2),
                new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent event) {
                        if (tree.getState() == 2) {
                            if (!tree.getCurrentNode().getChildNodes().isEmpty()) {
                                for (Node node: tree.getCurrentNode().getChildNodes()) {
                                    tree.getQueue().add(node);
                                    node.getCircle().setFill(Color.LIGHTYELLOW);
                                }
                            }
                            tree.setState(1);
                        }
                    }
                } );

        tree.setQueue(new LinkedList<Node>());
        tree.setTraveledNode(new LinkedList<Node>());
        tree.getQueue().add(tree.getRootNode());

        timeline.getKeyFrames().add(popQueue);
        timeline.getKeyFrames().add(pushQueue);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }
}
