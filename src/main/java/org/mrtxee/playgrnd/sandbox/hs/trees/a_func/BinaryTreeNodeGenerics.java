package org.mrtxee.playgrnd.sandbox.hs.trees.a_func;

public class BinaryTreeNodeGenerics<E> extends TreeNode<BinaryTreeNodeGenerics<E>, E> {


    BinaryTreeNodeGenerics(E value) {
        super(value);
    }
}