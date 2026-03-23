package org.mrtxee.playgrnd.sandbox.hs.trees.a_func;

public abstract class TreeNode<T, E> {
    final E value;
    T left;
    T right;

    TreeNode(E value) {
        this.value = value;
        right = null;
        left = null;
    }
}