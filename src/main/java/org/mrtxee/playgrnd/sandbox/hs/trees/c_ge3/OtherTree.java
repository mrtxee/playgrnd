package org.mrtxee.playgrnd.sandbox.hs.trees.c_ge3;

public class OtherTree extends BSTree<Integer> {
    private BTreeNode<Integer> reflectNodesRecursive(BTreeNode<Integer> node) {
        if (node == null) return null;
        //BTreeNode1<Long> node1 = new BTreeNode1<>(1L);
        BTreeNode<Integer> right = node.getRight();
        node.setRight(reflectNodesRecursive(node.getLeft()));
        node.setLeft(reflectNodesRecursive(right));
        return node;
    }

    public void reflectNodes() {
        this.reflectNodesRecursive(this.getRoot());
    }

}