package org.mrtxee.playgrnd.sandbox.hs.trees.a_func;


public class RBTreeNode {

    final int value;
    public RBTreeNode left;
    public RBTreeNode right;
    public RBTreeNode parent;
    protected RBTreeNodeColors color;

    // TODO: ASK: как сделать этот конструктор частным случаем основного конструктора?

    RBTreeNode(int value) {
        this.value = value;
        this.right = null;
        this.left = null;
        this.parent = null;
        this.setColor(RBTreeNodeColors.BLACK);
    }

    RBTreeNode(RBTreeNode parent, RBTreeNodeColors color, int value) {
        this.value = value;
        this.right = null;
        this.left = null;
        this.parent = parent;
        this.setColor(color);
    }

    @Override
    public String toString() {
        String color = "B";
        if (RBTreeNodeColors.RED == this.color) color = "R";
        return value + color;
    }

    public RBTreeNodeColors getColor() {
        return this.color;
    }

    public void setColor(RBTreeNodeColors color) {
        this.color = color;
    }


}