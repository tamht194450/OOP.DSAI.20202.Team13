package treeDataStructure;

public class BalancedTree extends GenericTree {

    private int maximumDiff;

    public BalancedTree(Node node, int maximumDiff) {
        super(node);
        this.maximumDiff = maximumDiff;
    }

    public boolean checkBalance() {
        for (int i : Node.listValue) {
            for (int j : Node.listValue) {
                if (i != j) {
                    Node node1 = this.searchNode(i);
                    Node node2 = this.searchNode(j);
                    if (node1.isLeave()) {
                        if (node2.isLeave()) {
                            int diff = node1.getDepth() - node2.getDepth();
                            if (Math.abs(diff) >  maximumDiff)  {
                                this.getController().setFailBalance(true);
                                return false;
                            }else{
                                continue;
                            }
                        }
                    }
                }
            }
        }
        this.getController().setFailBalance(false);
        return true;
    }

    public boolean insertNode(int parentValue, Node childNode) {
        boolean b = super.insertNode(parentValue, childNode);
        checkBalance();
        return b;
    }

    public boolean deleteNode(int nodeValue) {
        boolean b = super.deleteNode(nodeValue);
        checkBalance();
        return b;
    }

    public void backBFS() {
        super.backBFS();
        checkBalance();
    }

    public void forwardBFS() {
        super.forwardBFS();
        checkBalance();
    }

    public void backDFS() {
        super.backDFS();
        checkBalance();
    }

    public void forwardDFS() {
        super.forwardDFS();
        checkBalance();
    }
}
