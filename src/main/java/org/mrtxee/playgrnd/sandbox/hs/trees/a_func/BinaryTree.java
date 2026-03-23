package org.mrtxee.playgrnd.sandbox.hs.trees.a_func;

public class BinaryTree extends BinarySearchTree {
    private BinaryTreeNode reflectNodesRecursive(BinaryTreeNode node) {
        if (node == null) return null;
        //BinaryTreeNode1<Long> node1 = new BinaryTreeNode1<>(1L);
        BinaryTreeNode right = node.right;
        node.right = reflectNodesRecursive(node.left);
        node.left = reflectNodesRecursive(right);
        return node;
    }

    public void reflectNodes() {
        this.reflectNodesRecursive(this.getRoot());
    }
}