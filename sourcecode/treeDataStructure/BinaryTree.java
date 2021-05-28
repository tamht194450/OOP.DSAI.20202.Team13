package treeDataStructure;

import java.util.LinkedList;
import java.util.Queue;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class BinaryTree extends GenericTree {
    private Node root;
    private LinkedList<Node> queue;
    private LinkedList<Node> traveledNode;
    private Node current;
    private int numberOfNodes;
    private int state;


    public BinaryTree(Node node) {
        super();
        this.root = node;
        this.numberOfNodes = 1;
    }

    public Node searchNode(int nodeValue) {
        if (root.getValue() == nodeValue) {
            return root;
        }
        LinkedList<Node> queue = new LinkedList<Node>();
        queue.add(root);
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

    private Node addRecursive(Node current, int value) {
        if (current == null) {
            return new Node(value);
        }

        if (value < current.getValue()) {
            current.left = addRecursive(current.left, value);
        } else if (value > current.getValue()) {
            current.right = addRecursive(current.right, value);
        } else {return current;}

        return current;
    }

    /**
     * Them node
     * @param value
     */
    public void add(int value) {
        root = addRecursive(root, value);
    }

    private boolean containsNodeRecursive(Node current, int value) {
        if (current == null) {
            return false;
        }
        if (value == current.getValue()) {
            return true;
        }
        return value < current.getValue()
                ? containsNodeRecursive(current.left, value)
                : containsNodeRecursive(current.right, value);
    }

    /**
     * tim kiem node
     * @param value gia tri node
     * @return
     */

    public boolean containsNode(int value) {
        return containsNodeRecursive(root, value);
    }

    private Node deleteRecursive(Node current, int value) {
        if (current == null) {
            return null;
        }
        if (value == current.getValue()) {
            if (current.left == null && current.right == null) {
                return null;
            }
            if (current.right == null) {
                return current.left;
            }

            if (current.left == null) {
                return current.right;
            }
        }
        if (value < current.getValue() ) {
            current.left = deleteRecursive(current.left, value);
            return current;
        }
        current.right = deleteRecursive(current.right, value);
        return current;
    }

    /**
     * xoa node
     * @param value: gia tri node
     */
    public void delete(int value) {
        root = deleteRecursive(root, value);
    }

    public void inorder(Node temp)
    {
        if (temp == null)
            return;

        inorder(temp.left);
        System.out.print(temp.getValue() + " ");
        inorder(temp.right);
    }

    /**
     * Insert node
     * @param temp: node
     * @param value
     */
    public void insert(Node temp, int value)
    {

        if (temp == null) {
            root = new Node(value);
            return;
        }
        Queue<Node> q = new LinkedList<Node>();
        q.add(temp);

        while (!q.isEmpty()) {
            temp = q.peek();
            q.remove();

            if (temp.left == null) {
                temp.left = new Node(value);
                break;
            }
            else
                q.add(temp.left);

            if (temp.right == null) {
                temp.right = new Node(value);
                break;
            }
            else
                q.add(temp.right);
        }
    }
    // left - root - right : DFS
    public void traverseInOrder(Node node) {
        if (node != null) {
            traverseInOrder(node.left);
            System.out.print(" " + node.getValue());
            traverseInOrder(node.right);
        }
    }
    // root - left - right: DFS
    public void traversePreOrder(Node node) {
        if (node != null) {
            System.out.print(" " + node.getValue());
            traversePreOrder(node.left);
            traversePreOrder(node.right);
        }
    }
    // left - right - root : DFS
    public void traversePostOrder(Node node) {
        if (node != null) {
            traversePostOrder(node.left);
            traversePostOrder(node.right);
            System.out.print(" " + node.getValue());
        }
    }

    // BFS
    public void traverseLevelOrder() {
        if (root == null) {
            return;
        }

        Queue<Node> nodes = new LinkedList<>();
        nodes.add(root);

        while (!nodes.isEmpty()) {

            Node node = nodes.remove();

            System.out.print(" " + node.getValue());

            if (node.left != null) {
                nodes.add(node.left);
            }

            if (node.right != null) {
                nodes.add(node.right);
            }
        }
    }

}




