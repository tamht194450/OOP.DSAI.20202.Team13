package test;

import treeDataStructure.GenericTree;
import treeDataStructure.Node;
import treeScreen.TreeScreen;

public class Test {
    public static void main(String[] args) {
        Node n1 = new Node(1);
        GenericTree tree = new GenericTree(n1);


        TreeScreen treeScreen = new TreeScreen(tree);
    }
}
