package org.mrtxee.playgrnd.sandbox.hs.trees.b_oop;

public class OtherTree extends BSTree {
    private BTreeNode reflectNodesRecursive(BTreeNode node) {
        if (node == null) return null;
        //BTreeNode1<Long> node1 = new BTreeNode1<>(1L);
        BTreeNode right = node.getRight();
        node.setRight(reflectNodesRecursive(node.getLeft()));
        node.setLeft(reflectNodesRecursive(right));
        return node;
    }

    public void reflectNodes() {
        this.reflectNodesRecursive(this.getRoot());
    }

}