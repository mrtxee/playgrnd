package org.mrtxee.playgrnd.sandbox.hs.trees.c_gen;

import java.util.LinkedList;
import java.util.Queue;

public class RBTree<T extends Comparable<T>> extends BSTree<T> {
    @Override
    public RBTreeNode<T> getRoot() {
        return (RBTreeNode<T>) super.getRoot();
    }

    private void RBTreeInsertionFix(RBTreeNode<T> node) {
        RBTreeNode<T> uncle;
        while (null != node.parent && null != node.parent.parent && node.parent.color == RBTreeNodeColor.RED) {

            if (node.parent.parent.getRight() == node.parent) { // если ПРВЫЙ ОТЕЦ
                uncle = node.parent.parent.getLeft();
                if (null != uncle && RBTreeNodeColor.RED == uncle.color) { // если КРАСНЫЙ ДЯДЯ
                    // отца и дядю делаем черными, а дела - красным
                    node.parent.parent.getLeft().setColor(RBTreeNodeColor.BLACK);
                    node.parent.parent.getRight().setColor(RBTreeNodeColor.BLACK);
                    node.parent.parent.setColor(RBTreeNodeColor.RED);
                    node = node.parent.parent; // фокус внимания переводим на деда
                } else {
                    if (node == node.parent.getLeft()) { // если ЛЕВЫЙ СЫН
                        node = node.parent;
                        this.rotateRight(node);
                    }
                    node.parent.setColor(RBTreeNodeColor.BLACK);
                    if (null != node.parent.parent) {
                        node.parent.parent.setColor(RBTreeNodeColor.RED);
                        this.rotateLeft(node.parent.parent);
                    }
                }
            } else {  // если ЛЕВЫЙ ОТЕЦ
                uncle = node.parent.parent.getRight();
                if (null != uncle && RBTreeNodeColor.RED == uncle.color) {
                    node.parent.parent.getLeft().setColor(RBTreeNodeColor.BLACK);
                    node.parent.parent.getRight().setColor(RBTreeNodeColor.BLACK);
                    node.parent.parent.setColor(RBTreeNodeColor.RED);
                    node = node.parent.parent; // фокус внимания переводим на деда
                } else {
                    if (node == node.parent.getRight()) { // если ПРАВЫЙ СЫН
                        node = node.parent;
                        this.rotateLeft(node);
                    }
                    node.parent.setColor(RBTreeNodeColor.BLACK);
                    if (null != node.parent.parent) {
                        node.parent.parent.setColor(RBTreeNodeColor.RED);
                        this.rotateRight(node.parent.parent);
                    }
                }
            }
            if (node == this.getRoot()) {
                break;
            }
        }
        this.getRoot().setColor(RBTreeNodeColor.BLACK);
    }

    public void rotateLeft(RBTreeNode<T> node) {
        if (null == node || null == node.getRight()) return;
        RBTreeNode<T> rightSon = node.getRight();
        node.setRight(rightSon.getLeft()); // левое поддерево перевешиваем на место правого сына
        if (rightSon.getLeft() != null) {
            rightSon.getLeft().parent = node;
        }
        rightSon.parent = node.parent;
        if (node.parent == null) {
            this.setRoot(rightSon);
        } else if (node == node.parent.getLeft()) {
            node.parent.setLeft(rightSon);
        } else {
            node.parent.setRight(rightSon);
        }
        rightSon.setLeft(node); // узел становится ДЕВЫМ ПОТОМКА ПРРАВОГО СЫНА
        node.parent = rightSon;
    }

    public void rotateRight(RBTreeNode<T> node) {
        if (null == node || null == node.getLeft()) return;
        RBTreeNode<T> leftSon = node.getLeft();
        node.setLeft(leftSon.getRight()); // правое поддерево перевешиваем на место левого сына
        if (leftSon.getRight() != null) {
            leftSon.getRight().parent = node;
        }
        leftSon.parent = node.parent; // левого сына подвешиваем на место узла
        if (node.parent == null) {
            this.setRoot(leftSon);
        } else if (node == node.parent.getRight()) {
            node.parent.setRight(leftSon);
        } else {
            node.parent.setLeft(leftSon);
        }
        leftSon.setRight(node); // узел становится ПРАВЫМ ПОТОМКА ЛЕВОГО СЫНА
        node.parent = leftSon;
    }

    @Override
    public void insert(T value) {
        RBTreeNode<T> currentNode = this.getRoot(), parentNode = null, newNode;
        boolean isLeftChild = false;

        if (null == currentNode) {
            newNode = new RBTreeNode<T>(null, RBTreeNodeColor.RED, value);
            this.setRoot(newNode);
        } else {
            while (null != currentNode) {
                parentNode = currentNode;
                if (currentNode.getValue().compareTo(value) <= 0) {
                    currentNode = currentNode.getRight();
                    isLeftChild = false;
                } else {
                    currentNode = currentNode.getLeft();
                    isLeftChild = true;
                }
            }
            newNode = new RBTreeNode<T>(parentNode, RBTreeNodeColor.RED, value);
            if (isLeftChild) parentNode.setLeft(newNode);
            else parentNode.setRight(newNode);
        }
        this.RBTreeInsertionFix(newNode);
        this.updateTreeDepth();
    }

    private void rbTransplant(RBTreeNode<T> u, RBTreeNode<T> v) {
        if (u.parent == null) {
            this.setRoot(v);
        } else if (u == u.parent.getLeft()) {
            u.parent.setLeft(v);
        } else {
            u.parent.setRight(v);
        }
        if (null != v) v.parent = u.parent;
    }

    private void fixDelete(RBTreeNode<T> node) {
        RBTreeNode<T> sibling;
        while (node != this.getRoot() && node.color == RBTreeNodeColor.BLACK) {
            if (node == node.parent.getLeft()) {
                sibling = node.parent.getRight();
                if (sibling.color == RBTreeNodeColor.RED) {
                    sibling.color = RBTreeNodeColor.BLACK;
                    node.parent.color = RBTreeNodeColor.RED;
                    rotateLeft(node.parent);
                    sibling = node.parent.getRight();
                }

                if (sibling.getLeft().color == RBTreeNodeColor.BLACK && sibling.getRight().color == RBTreeNodeColor.BLACK) {
                    sibling.color = RBTreeNodeColor.RED;
                    node = node.parent;
                } else {
                    if (sibling.getRight().color == RBTreeNodeColor.BLACK) {
                        sibling.getLeft().color = RBTreeNodeColor.BLACK;
                        sibling.color = RBTreeNodeColor.RED;
                        rotateRight(sibling);
                        sibling = node.parent.getRight();
                    }

                    sibling.color = node.parent.color;
                    node.parent.color = RBTreeNodeColor.BLACK;
                    sibling.getRight().color = RBTreeNodeColor.BLACK;
                    rotateLeft(node.parent);
                    node = this.getRoot();
                }
            } else {
                sibling = node.parent.getLeft();
                if (sibling.color == RBTreeNodeColor.RED) {
                    sibling.color = RBTreeNodeColor.BLACK;
                    node.parent.color = RBTreeNodeColor.RED;
                    rotateRight(node.parent);
                    sibling = node.parent.getLeft();
                }

                if (sibling.getRight().color == RBTreeNodeColor.BLACK) {
                    sibling.color = RBTreeNodeColor.BLACK;
                    node = node.parent;
                } else {
                    if (sibling.getLeft().color == RBTreeNodeColor.BLACK) {
                        sibling.getRight().color = RBTreeNodeColor.BLACK;
                        sibling.color = RBTreeNodeColor.RED;
                        rotateLeft(sibling);
                        sibling = node.parent.getLeft();
                    }

                    sibling.color = node.parent.color;
                    node.parent.color = RBTreeNodeColor.BLACK;
                    sibling.getLeft().color = RBTreeNodeColor.BLACK;
                    rotateRight(node.parent);
                    node = this.getRoot();
                }
            }
        }
        node.color = RBTreeNodeColor.BLACK;
    }

    private RBTreeNode<T> getMinimumNode(RBTreeNode<T> node) {
        while (node.getLeft() != null) {
            node = node.getLeft();
        }
        return node;
    }

    public T getMinimumValue() {
        return this.getMinimumNode(this.getRoot()).getValue();
    }

    private void deleteNode(RBTreeNode<T> node, T value) {
        RBTreeNode<T> xNode, yNode, zNode = null;
        while (node != null) {
            if (node.getValue() == value) {
                zNode = node;
            }

            if (node.getValue().compareTo(value) <= 0) {
                node = node.getRight();
            } else {
                node = node.getLeft();
            }
        }

        if (zNode == null) {
            System.out.println("Couldn't find value in the tree");
            return;
        }

        yNode = zNode;
        RBTreeNodeColor yOriginalColor = yNode.color;
        if (zNode.getLeft() == null) {
            xNode = zNode.getRight();
            rbTransplant(zNode, zNode.getRight());
        } else if (zNode.getRight() == null) {
            xNode = zNode.getLeft();
            rbTransplant(zNode, zNode.getLeft());
        } else {
            yNode = getMinimumNode(zNode.getRight());
            yOriginalColor = yNode.color;
            xNode = yNode.getRight();
            if (yNode.parent == zNode) {
                xNode.parent = yNode;
            } else {
                rbTransplant(yNode, yNode.getRight());
                yNode.setRight(zNode.getRight());
                yNode.getRight().parent = yNode;
            }

            rbTransplant(zNode, yNode);
            yNode.setLeft(zNode.getLeft());
            yNode.getLeft().parent = yNode;
            yNode.color = zNode.color;
        }
        if (yOriginalColor == RBTreeNodeColor.BLACK) {
            fixDelete(xNode);
        }
    }

    @Override
    public void delete(T value) {
        deleteNode(this.getRoot(), value);
        this.updateTreeDepth();
    }


    @Override
    public void printTree() {
        final String emptyNodeString = "∙B"; //⋮
        final int treeDepth = this.getTreeDepth();
        int currentDepth = 1;
        Queue<RBTreeNode<T>> queueThisLevel, queueNextLevel = new LinkedList<>();
        queueNextLevel.add(this.getRoot());
        do {
            int currentLineDelimiters = (int) (Math.pow(2, (treeDepth - currentDepth))) - 1;
            queueThisLevel = queueNextLevel;
            queueNextLevel = new LinkedList<>();
            while (!queueThisLevel.isEmpty()) {
                RBTreeNode<T> currentNode = queueThisLevel.poll();
                if (null == currentNode) {
                    printStringFixedLength(emptyNodeString);
                    queueNextLevel.add(null);
                    queueNextLevel.add(null);
                } else {
                    printStringFixedLength(currentNode.toString());
                    queueNextLevel.add(currentNode.getLeft());
                    queueNextLevel.add(currentNode.getRight());
                }
                for (int i = 0; i < currentLineDelimiters; i++) printStringFixedLength("");
            }
            System.out.println();
            currentDepth++;
        } while (!(1 == queueNextLevel.stream().distinct().count() && queueNextLevel.element() == null));
    }

    private enum RBTreeNodeColor {
        RED("R"),
        BLACK("B");

        final private String label;

        RBTreeNodeColor(String label) {
            this.label = label;
        }
    }

    protected static class RBTreeNode<T extends Comparable<T>> extends BTreeNode<T> {
        private RBTreeNode<T> parent;
        private RBTreeNodeColor color;

        protected RBTreeNode(T value) {
            super(value);
            this.color = RBTreeNodeColor.BLACK;
        }

        protected RBTreeNode(RBTreeNode<T> parent, RBTreeNodeColor color, T value) {
            super(value);
            this.color = color;
            this.parent = parent;
        }

        @Override
        public String toString() {
            return getValue() + color.label;
        }

        public RBTreeNodeColor getColor() {
            return this.color;
        }

        public void setColor(RBTreeNodeColor color) {
            this.color = color;
        }

        public RBTreeNode<T> getParent() {
            return parent;
        }

        public void setParent(RBTreeNode<T> parent) {
            this.parent = parent;
        }

        @Override
        public RBTreeNode<T> getLeft() {
            return (RBTreeNode<T>) super.getLeft();
        }

        @Override
        public RBTreeNode<T> getRight() {
            return (RBTreeNode<T>) super.getRight();
        }
    }

}
