package org.mrtxee.playgrnd.sandbox.hs.trees.b_oop;

import java.util.LinkedList;
import java.util.Queue;

public class BSTree extends BTree {
    protected BTreeNode insertRecursive(BTreeNode node, int value) {
        if (null == node) {
            return new BTree.BTreeNode(value);
        } else {
            if (node.getValue() < value) {
                node.setRight(insertRecursive(node.getRight(), value));
            } else {
                node.setLeft(insertRecursive(node.getLeft(), value));
            }
        }
        return node;
    }

    @Override
    public void insert(int value) {
        this.setRoot(this.insertRecursive(this.getRoot(), value));
        this.updateTreeDepth();
    }

    @Override
    public void delete(int value) {
        BTreeNode nodeToDelete = this.getRoot();
        BTreeNode parentNodeToDelete = null;
        boolean isLeftNodeToDelete = false;

        while (nodeToDelete != null && nodeToDelete.getValue() != value) {
            parentNodeToDelete = nodeToDelete;
            if (nodeToDelete.getValue() < value) {
                nodeToDelete = nodeToDelete.getRight();
                isLeftNodeToDelete = false;
            } else {
                nodeToDelete = nodeToDelete.getLeft();
                isLeftNodeToDelete = true;
            }
        }

        // если не узел удаления отсутствует, выходим
        if (null == nodeToDelete) return;

        // если у удаляемого узла нет сыновей, то удаляем ссылку на него
        if (nodeToDelete.getLeft() == null && nodeToDelete.getRight() == null) {
            if (null != parentNodeToDelete) if (isLeftNodeToDelete) parentNodeToDelete.setLeft(null);
            else parentNodeToDelete.setRight(null);
                // если нет потомков и родителя, удаляем корень
            else setRoot(null);
        }

        // если у удаляемого узла есть только один сын, его ссылку переназначаем на сына
        if (nodeToDelete.getLeft() == null ^ nodeToDelete.getRight() == null) {
            BTreeNode substituteNode = null;
            if (nodeToDelete.getLeft() != null) substituteNode = nodeToDelete.getLeft();
            if (nodeToDelete.getRight() != null) substituteNode = nodeToDelete.getRight();
            if (parentNodeToDelete != null && parentNodeToDelete.getValue() < substituteNode.getValue())
                parentNodeToDelete.setRight(substituteNode);
        }

        // если у удаляемого узла есть сыновья, то находим приемника и подвершиваем поддерево к нему
        if (nodeToDelete.getLeft() != null && nodeToDelete.getRight() != null) {
            // ищем узел-приемник
            BTreeNode substituteNode = nodeToDelete.getRight();
            while (null != substituteNode.getLeft()) {
                substituteNode = substituteNode.getLeft();
            }
            substituteNode.setLeft(nodeToDelete.getLeft());
            if (null != parentNodeToDelete) {
                if (isLeftNodeToDelete) parentNodeToDelete.setLeft(substituteNode);
                else parentNodeToDelete.setRight(substituteNode);
            } else this.setRoot(nodeToDelete.getRight());
        }
        this.updateTreeDepth();
    }

    public void printTreeAsLine() {
        Queue<BTreeNode> queue = new LinkedList<>();
        queue.add(this.getRoot());
        while (!queue.isEmpty()) {
            BTreeNode tempNode = queue.poll();
            System.out.print(tempNode.getValue() + " ");

            if (tempNode.getLeft() != null) queue.add(tempNode.getLeft());
            if (tempNode.getRight() != null) queue.add(tempNode.getRight());
        }
    }

    public void printTreeAsLineWithBreaks() {
        Queue<BTreeNode> queueThisLevel, queueNextLevel = new LinkedList<>();
        queueNextLevel.add(this.getRoot());
        do {
            queueThisLevel = queueNextLevel;
            queueNextLevel = new LinkedList<>();
            while (!queueThisLevel.isEmpty()) {
                BTreeNode currentNode = queueThisLevel.poll();
                System.out.print(currentNode.getValue() + " ");
                if (currentNode.getLeft() != null) queueNextLevel.add(currentNode.getLeft());
                if (currentNode.getRight() != null) queueNextLevel.add(currentNode.getRight());
            }
            System.out.println();
        } while (!queueNextLevel.isEmpty());
    }
}
