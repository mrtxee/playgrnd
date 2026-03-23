package org.mrtxee.playgrnd.sandbox.hs.trees.a_func;

public class BinaryTreeNode {
    final int value;
    BinaryTreeNode left;
    BinaryTreeNode right;

    BinaryTreeNode(int value) {
        this.value = value;
        right = null;
        left = null;
    }
}