package treeDataStructure;

import java.util.LinkedList;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class GenericTree {
	private Node rootNode;
	private LinkedList<Node> queue;
	private LinkedList<Node> traveledNode;
	private Node currentNode;
	private Timeline timeline;
	private int numberOfNodes;
	private int state;
	
	public GenericTree(Node node) {
		this.rootNode = node;
		this.numberOfNodes = 1;
	}
	
	public Node searchNode(int nodeValue) {
		if (rootNode.getValue() == nodeValue) {
			return rootNode;
		}
		
		LinkedList<Node> queue = new LinkedList<Node>();
		queue.add(rootNode);
		Node currentNode;
		
		while(!queue.isEmpty()) {
			currentNode = queue.getFirst();
			
			if (!currentNode.getChildNodes().isEmpty()) {
				for (Node node: currentNode.getChildNodes()) {
					if (node.getValue() == nodeValue) {
						return node;
					} else {
						queue.add(node);
					}
				}
			}
			queue.removeFirst();
		}
		return new Node(0);
	}
	
	public boolean insertNode(int parentValue, Node childNode) {
		Node parentNode = this.searchNode(parentValue);
		parentNode.addChild(childNode);
		numberOfNodes ++;
		return true;
	}

	public Node getRootNode() {
		return rootNode;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public LinkedList<Node> getTraveledNode() {
		return traveledNode;
	}

	public void setTraveledNode(LinkedList<Node> traveledNode) {
		this.traveledNode = traveledNode;
	}

	public LinkedList<Node> getQueue() {
		return queue;
	}

	public Node getCurrentNode() {
		return currentNode;
	}

	public void setCurrentNode(Node currentNode) {
		this.currentNode = currentNode;
	}

	public void setQueue(LinkedList<Node> queue) {
		this.queue = queue;
	}

	public void traversalBFS() {
		state = 1;
		KeyFrame popQueue = new KeyFrame(Duration.seconds(1), 
				new EventHandler<ActionEvent>() {
			  		public void handle(ActionEvent event) {
			  			if (state == 1) { 
			  				if (!queue.isEmpty()) {
				  				currentNode = queue.getFirst();
				  				queue.removeFirst();
				  				traveledNode.add(currentNode);
				  				currentNode.getCircle().setFill(Color.LIGHTBLUE);
				  				state = 2;
			  				} else {
			  					timeline.stop();
			  				}
			  			}
			  		}
			} );
		KeyFrame pushQueue = new KeyFrame(Duration.seconds(2), 
				new EventHandler<ActionEvent>() {
			  		public void handle(ActionEvent event) {
			  			if (state == 2) {
			  				if (!currentNode.getChildNodes().isEmpty()) {
			  					for (Node node: currentNode.getChildNodes()) {
			  						queue.add(node);
			  						node.getCircle().setFill(Color.LIGHTYELLOW);
			  					}
			  				}
			  				state = 1;
			  			}
			  		}
			} );	
		
		queue = new LinkedList<Node>();
		traveledNode = new LinkedList<Node>();
		queue.add(rootNode);
		
		timeline = new Timeline();

		timeline.getKeyFrames().add(popQueue);
		timeline.getKeyFrames().add(pushQueue);
		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.play();
		}
	
	public void nextStepBFS() {
		if (!traveledNode.isEmpty()) {
			traveledNode.getLast().getCircle().setFill(Color.LIGHTGREEN);
		}
		if (!queue.isEmpty()) {
			currentNode = this.queue.getFirst();
			currentNode.getCircle().setFill(Color.LIGHTBLUE);
	    	
			if (!currentNode.getChildNodes().isEmpty()) {
				for (Node node: currentNode.getChildNodes()) {
					node.getCircle().setFill(Color.LIGHTYELLOW);
					queue.add(node);
				}
			}
			traveledNode.add(currentNode);
			queue.removeFirst();
		}
	}
	public void backBFS() {
		if (state == 1) {
			if (!currentNode.getChildNodes().isEmpty()) {
				for (Node node: currentNode.getChildNodes()) {
					queue.removeLast();
					node.getCircle().setFill(Color.WHITE);
				}
			}
			timeline.setCycleCount(timeline.getCycleCount()+1);
			state = 2;
		} else if (state == 2) {
			queue.addFirst(currentNode);
			currentNode.getCircle().setFill(Color.LIGHTYELLOW);
			traveledNode.removeLast();
			currentNode = traveledNode.getLast();
			state = 1;
		}
	}

	public Timeline getTimeline() {
		return timeline;
	}

	
	
}
