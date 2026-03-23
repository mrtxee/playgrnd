package org.mrtxee.playgrnd.sandbox.hs.trees.a_func;

import java.util.LinkedList;
import java.util.Queue;

public class BinarySearchTree {
    protected BinaryTreeNode root;

    BinarySearchTree() {
        this.setRoot(null);
    }

    protected static void printStringFixedLength(String string) {
        int length = 3;
        System.out.printf("%1$" + length + "s", string);
    }

    public BinaryTreeNode getRoot() {
        return this.root;
    }

    public void setRoot(BinaryTreeNode root) {
        this.root = root;
    }

    protected BinaryTreeNode insertValueRecursive(BinaryTreeNode node, int value) {
        if (null == node) {
            //BinaryTreeNode1<Long> node1 = new BinaryTreeNode1<>(1L);
            return new BinaryTreeNode(value);
        } else {
            if (node.value < value) {
                node.right = insertValueRecursive(node.right, value);
            } else {
                node.left = insertValueRecursive(node.left, value);
            }
        }
        return node;
    }

    public void insertValue(int value) {
        this.root = this.insertValueRecursive(this.root, value);
    }

    protected BinaryTreeNode getNodeByValueRecursiveOrNull(BinaryTreeNode node, int value) {
        if (null == node) return null;
        else if (node.value == value) return node;
        else if (node.value < value) {
            return getNodeByValueRecursiveOrNull(node.right, value);
        } else return getNodeByValueRecursiveOrNull(node.left, value);
    }

    public BinaryTreeNode getNodeByValOrNull(int value) {
        return this.getNodeByValueRecursiveOrNull(this.root, value);
    }

    public void deleteNodeByVal(int value) {
        BinaryTreeNode nodeToDelete = this.getRoot();
        BinaryTreeNode parentNodeToDelete = null;
        boolean isLeftNodeToDelete = false;

        while (nodeToDelete != null && nodeToDelete.value != value) {
            parentNodeToDelete = nodeToDelete;
            if (nodeToDelete.value < value) {
                nodeToDelete = nodeToDelete.right;
                isLeftNodeToDelete = false;
            } else {
                nodeToDelete = nodeToDelete.left;
                isLeftNodeToDelete = true;
            }
        }

        // если не узел удаления отсутствует, выходим
        if (null == nodeToDelete) return;

        // если у удаляемого узла нет сыновей, то удаляем ссылку на него
        if (nodeToDelete.left == null && nodeToDelete.right == null) {
            if (null != parentNodeToDelete) if (isLeftNodeToDelete) parentNodeToDelete.left = null;
            else parentNodeToDelete.right = null;
                // если нет потомков и родителя, удаляем корень
            else setRoot(null);
        }

        // если у удаляемого узла есть только один сын, его ссылку переназначаем на сына
        if (nodeToDelete.left == null ^ nodeToDelete.right == null) {
            BinaryTreeNode substituteNode = null;
            if (nodeToDelete.left != null) substituteNode = nodeToDelete.left;
            if (nodeToDelete.right != null) substituteNode = nodeToDelete.right;
            if (parentNodeToDelete != null && parentNodeToDelete.value < substituteNode.value)
                parentNodeToDelete.right = substituteNode;
        }

        // если у удаляемого узла есть сыновья, то находим приемника и подвершиваем поддерево к нему
        if (nodeToDelete.left != null && nodeToDelete.right != null) {
            // ищем узел-приемник
            BinaryTreeNode substituteNode = nodeToDelete.right;
            while (null != substituteNode.left) {
                substituteNode = substituteNode.left;
            }
            substituteNode.left = nodeToDelete.left;
            if (null != parentNodeToDelete) {
                if (isLeftNodeToDelete) parentNodeToDelete.left = substituteNode;
                else parentNodeToDelete.right = substituteNode;
            } else this.setRoot(nodeToDelete.right);
        }
    }

    public void printTreeAsLine() {
        Queue<BinaryTreeNode> queue = new LinkedList<BinaryTreeNode>();
        queue.add(this.getRoot());
        while (!queue.isEmpty()) {
            BinaryTreeNode tempNode = queue.poll();
            System.out.print(tempNode.value + " ");

            if (tempNode.left != null) queue.add(tempNode.left);
            if (tempNode.right != null) queue.add(tempNode.right);
        }
    }

    public void printTreeAsLineWithBreaks() {
        Queue<BinaryTreeNode> queueThisLevel, queueNextLevel = new LinkedList<BinaryTreeNode>();
        queueNextLevel.add(this.getRoot());
        do {
            queueThisLevel = queueNextLevel;
            queueNextLevel = new LinkedList<>();
            while (!queueThisLevel.isEmpty()) {
                BinaryTreeNode currentNode = queueThisLevel.poll();
                System.out.print(currentNode.value + " ");
                if (currentNode.left != null) queueNextLevel.add(currentNode.left);
                if (currentNode.right != null) queueNextLevel.add(currentNode.right);
            }
            System.out.println();
        } while (!queueNextLevel.isEmpty());
    }

    protected int getTreeDepthRecursive(BinaryTreeNode node) {
        if (node == null) return 0;
        return Math.max(getTreeDepthRecursive(node.left), getTreeDepthRecursive(node.right)) + 1;
    }

    public int getTreeDepth() {
        return getTreeDepthRecursive(this.getRoot());
    }

    public void printTree() {
        final String emptyNodeString = "∙"; //⋮
        final int treeDepth = this.getTreeDepth();
        int currentDepth = 1;
        Queue<BinaryTreeNode> queueThisLevel, queueNextLevel = new LinkedList<BinaryTreeNode>();
        queueNextLevel.add(this.getRoot());
        do {
            int currentLineDelimiters = (int) (Math.pow(2, (treeDepth - currentDepth))) - 1;
            queueThisLevel = queueNextLevel;
            queueNextLevel = new LinkedList<>();
            while (!queueThisLevel.isEmpty()) {
                BinaryTreeNode currentNode = queueThisLevel.poll();
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
}