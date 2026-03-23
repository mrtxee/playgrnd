package org.mrtxee.playgrnd.sandbox.hs.trees.b_oop;

import java.util.LinkedList;
import java.util.Queue;

public abstract class BTree implements Tree {
    protected int depth = 0;
    private BTreeNode root;

    protected static void printStringFixedLength(String string) {
        int length = 4;
        System.out.printf("%1$" + length + "s", string);
    }

    protected int getTreeDepthRecursive(BTreeNode node) {
        if (node == null) return 0;
        return Math.max(getTreeDepthRecursive(node.left), getTreeDepthRecursive(node.right)) + 1;
    }

    protected void updateTreeDepth() {
        this.depth = getTreeDepthRecursive(this.getRoot());
    }

    @Override
    public int getTreeDepth() {
        return this.depth;
    }

    @Override
    public void printTree() {
        final String emptyNodeString = "∙"; //⋮
        final int treeDepth = this.getTreeDepth();
        int currentDepth = 1;
        Queue<BTreeNode> queueThisLevel, queueNextLevel = new LinkedList<>();
        queueNextLevel.add(this.getRoot());
        do {
            int currentLineDelimiters = (int) (Math.pow(2, (treeDepth - currentDepth))) - 1;
            queueThisLevel = queueNextLevel;
            queueNextLevel = new LinkedList<>();
            while (!queueThisLevel.isEmpty()) {
                BTreeNode currentNode = queueThisLevel.poll();
                if (null == currentNode) {
                    printStringFixedLength(emptyNodeString);
                    queueNextLevel.add(null);
                    queueNextLevel.add(null);
                } else {
                    printStringFixedLength(Integer.valueOf(currentNode.value).toString());
                    queueNextLevel.add(currentNode.left);
                    queueNextLevel.add(currentNode.right);
                }
                for (int i = 0; i < currentLineDelimiters; i++) printStringFixedLength("");
            }
            System.out.println();
            currentDepth++;
        } while (!(1 == queueNextLevel.stream().distinct().count() && queueNextLevel.element() == null));
    }

    public BTreeNode getRoot() {
        return root;
    }

    protected void setRoot(BTreeNode root) {
        this.root = root;
    }

    public static class BTreeNode {
        final private int value;
        private BTreeNode left;
        private BTreeNode right;

        protected BTreeNode(int value) {
            this.value = value;
            this.right = null;
            this.left = null;
        }

        public int getValue() {
            return value;
        }

        public BTreeNode getLeft() {
            return left;
        }

        public void setLeft(BTreeNode left) {
            this.left = left;
        }

        public BTreeNode getRight() {
            return right;
        }

        public void setRight(BTreeNode right) {
            this.right = right;
        }
    }

}
