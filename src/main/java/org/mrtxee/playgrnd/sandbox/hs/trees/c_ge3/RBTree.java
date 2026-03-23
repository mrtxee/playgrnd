package org.mrtxee.playgrnd.sandbox.hs.trees.c_ge3;

import java.util.LinkedList;
import java.util.Queue;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

public class RBTree<T extends Comparable<T>> extends BSTree<T> {
    @Override
    public RBTreeNode<T> getRoot() {
        return (RBTreeNode<T>) super.getRoot();
    }

    private void RBTreeInsertionFix(RBTreeNode<T> node) {
        while (node != this.getRoot() && null != node.parent && null != node.parent.parent && node.parent.color == RBTreeNodeColor.RED) {
            boolean isRightFather = node.parent.parent.getRight() == node.parent;

            Function<RBTreeNode<T>, RBTreeNode<T>> uncleGetter = isRightFather ? RBTreeNode::getLeft : RBTreeNode::getRight;
            Consumer<RBTreeNode<T>> rotateRightForRightFather = isRightFather ? this::rotateRight : this::rotateLeft;
            Consumer<RBTreeNode<T>> rotateLeftForRightFather = isRightFather ? this::rotateLeft : this::rotateRight;

            RBTreeNode<T> uncle = uncleGetter.apply(node.parent.parent);
            if (null != uncle && RBTreeNodeColor.RED == uncle.color) { // если КРАСНЫЙ ДЯДЯ
                // отца и дядю делаем черными, а дела - красным
                node.parent.parent.getLeft().setColor(RBTreeNodeColor.BLACK);
                node.parent.parent.getRight().setColor(RBTreeNodeColor.BLACK);
                node.parent.parent.setColor(RBTreeNodeColor.RED);
                node = node.parent.parent; // фокус внимания переводим на деда
            } else {
                if (node == uncleGetter.apply(node.parent)) { // если ЛЕВЫЙ СЫН
                    node = node.parent;
                    rotateRightForRightFather.accept(node);
                }
                if (null != node.parent.parent) {
                    node.parent.parent.setColor(RBTreeNodeColor.RED);
                    rotateLeftForRightFather.accept(node.parent.parent);
                }

                node.parent.setColor(RBTreeNodeColor.BLACK);
            }
        }
        this.getRoot().setColor(RBTreeNodeColor.BLACK);
    }

    private void rotate(RBTreeNode<T> node, boolean doRightRotation) {
        Function<RBTreeNode<T>, Boolean> hasSideSon = doRightRotation ? RBTreeNode::hasRightSon : RBTreeNode::hasLeftSon;
        Function<RBTreeNode<T>, Boolean> hasOppositeSideSon = doRightRotation ? RBTreeNode<T>::hasLeftSon : RBTreeNode<T>::hasRightSon;
        BiConsumer<RBTreeNode<T>, RBTreeNode<T>> sideSonParentSetter = doRightRotation ? (s, n) -> s.getRight().setParent(n) : (s, n) -> s.getLeft().setParent(n);
        BiConsumer<RBTreeNode<T>, RBTreeNode<T>> sideSonSetter = doRightRotation ? BTreeNode::setRight : (s, n) -> s.setLeft(n);
        BiConsumer<RBTreeNode<T>, RBTreeNode<T>> grandsonAsSonSetter = doRightRotation ? (n, s) -> n.setLeft(s.getRight()) : (n, s) -> n.setRight(s.getLeft());

        if (null == node || !hasOppositeSideSon.apply(node)) return;
        RBTreeNode<T> son = doRightRotation ? node.getLeft() : node.getRight();
        grandsonAsSonSetter.accept(node, son); // правого(левого) внука на место левого(правого) сына
        if (hasSideSon.apply(node)) {
            sideSonParentSetter.accept(son, node);
        }
        son.parent = node.parent;
        if (node.parent == null) {
            this.setRoot(son);
        } else if (node == node.parent.getRight()) {
            node.parent.setRight(son);
        } else {
            node.parent.setLeft(son);
        }
        sideSonSetter.accept(son, node); // узел становится ПРАВЫМ (левым) потомковм ЛЕВОГО (правого) СЫНА
        node.parent = son;
    }

    public void rotateRight(RBTreeNode<T> node) {
        rotate(node, true);
    }

    public void rotateLeft(RBTreeNode<T> node) {
        rotate(node, false);
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
        while (null != this.getRoot() && node != this.getRoot() && node.getColor() == RBTreeNodeColor.BLACK) {
            if (node == node.parent.getLeft()) {
                sibling = node.parent.getRight(); //*getLeft
                if (sibling.color == RBTreeNodeColor.RED) {
                    sibling.color = RBTreeNodeColor.BLACK;
                    node.parent.color = RBTreeNodeColor.RED;
                    rotateLeft(node.parent);//*rotateRight
                    sibling = node.parent.getRight();//*getLeft
                }

                if (sibling.getLeft().color == RBTreeNodeColor.BLACK && sibling.getRight().color == RBTreeNodeColor.BLACK) {//*(sibling.getRight().color == RBTreeNodeColor.BLACK)
                    sibling.color = RBTreeNodeColor.RED;//*BLACK
                    node = node.parent;
                } else {
                    if (sibling.getRight().color == RBTreeNodeColor.BLACK) {//*getLeft
                        sibling.getLeft().color = RBTreeNodeColor.BLACK;//*getRight
                        sibling.color = RBTreeNodeColor.RED;
                        rotateRight(sibling);//*rotateLeft
                        sibling = node.parent.getRight();//*getLeft
                    }

                    sibling.color = node.parent.color;
                    node.parent.color = RBTreeNodeColor.BLACK;
                    sibling.getRight().color = RBTreeNodeColor.BLACK;//*getLeft
                    rotateLeft(node.parent);//*rotateRight
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
            if (null != xNode && null != xNode.parent && yNode.parent == zNode) {
                xNode.parent = yNode;
            } else {
                rbTransplant(yNode, yNode.getRight());
                yNode.setRight(zNode.getRight());
                if (null != yNode.getRight() && null != yNode.getRight().parent)
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
