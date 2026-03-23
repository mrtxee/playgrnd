package org.mrtxee.playgrnd.sandbox.hs.trees.a_func;

import java.util.LinkedList;
import java.util.Queue;


public class RBTree {
    /*
     * 1. Каждый узел промаркирован красным или чёрным цветом
     * 2. Корень и конечные узлы (листья, NULL, `nil-node`) дерева — чёрные
     * 3. У красного узла родительский узел — чёрный
     * 4. Все простые пути из любого узла x до листьев содержат одинаковое количество чёрных узлов `black height`
     * 5. Чёрный узел может иметь чёрного родителя
     * */
    private RBTreeNode root;

    public RBTree() {
        this.setRoot(null);
    }

    // todo: унаследуй это!
    protected static void printStringFixedLength(String string) {
        int length = 4;
        System.out.printf("%1$" + length + "s", string);
    }

    public RBTreeNode getRoot() {
        return this.root;
    }

    public void setRoot(RBTreeNode root) {
        this.root = root;
    }

    private void RBTreeInsertionFix(RBTreeNode node) {
        RBTreeNode uncle;
        while (null != node.parent && null != node.parent.parent && node.parent.color == RBTreeNodeColors.RED) {

            if (node.parent.parent.right == node.parent) { // если ПРВЫЙ ОТЕЦ
                uncle = node.parent.parent.left;
                if (null != uncle && RBTreeNodeColors.RED == uncle.color) { // если КРАСНЫЙ ДЯДЯ
                    // отца и дядю делаем черными, а дела - красным
                    node.parent.parent.left.setColor(RBTreeNodeColors.BLACK);
                    node.parent.parent.right.setColor(RBTreeNodeColors.BLACK);
                    node.parent.parent.setColor(RBTreeNodeColors.RED);
                    node = node.parent.parent; // фокус внимания переводим на деда
                } else {
                    if (node == node.parent.left) { // если ЛЕВЫЙ СЫН
                        node = node.parent;
                        this.rotateRight(node);
                    }
                    node.parent.setColor(RBTreeNodeColors.BLACK);
                    if (null != node.parent.parent) {
                        node.parent.parent.setColor(RBTreeNodeColors.RED);
                        this.rotateLeft(node.parent.parent);
                    }
                }
            } else {  // если ЛЕВЫЙ ОТЕЦ
                uncle = node.parent.parent.right;
                if (null != uncle && RBTreeNodeColors.RED == uncle.color) {
                    node.parent.parent.left.setColor(RBTreeNodeColors.BLACK);
                    node.parent.parent.right.setColor(RBTreeNodeColors.BLACK);
                    node.parent.parent.setColor(RBTreeNodeColors.RED);
                    node = node.parent.parent; // фокус внимания переводим на деда
                } else {
                    if (node == node.parent.right) { // если ПРАВЫЙ СЫН
                        node = node.parent;
                        this.rotateLeft(node);
                    }
                    node.parent.setColor(RBTreeNodeColors.BLACK);
                    if (null != node.parent.parent) {
                        node.parent.parent.setColor(RBTreeNodeColors.RED);
                        this.rotateRight(node.parent.parent);
                    }
                }
            }
            if (node == this.getRoot()) {
                break;
            }
        }
        this.getRoot().setColor(RBTreeNodeColors.BLACK);
    }

    public void insertValue(int value) {
        RBTreeNode currentNode = this.root, parentNode = null, newNode;
        boolean isLeftChild = false;

        if (null == currentNode) {
            newNode = new RBTreeNode(null, RBTreeNodeColors.RED, value);
            this.setRoot(newNode);
        } else {
            while (null != currentNode) {
                parentNode = currentNode;
                if (currentNode.value <= value) {
                    currentNode = currentNode.right;
                    isLeftChild = false;
                } else {
                    currentNode = currentNode.left;
                    isLeftChild = true;
                }
            }
            newNode = new RBTreeNode(parentNode, RBTreeNodeColors.RED, value);
            if (isLeftChild) parentNode.left = newNode;
            else parentNode.right = newNode;
        }
        this.RBTreeInsertionFix(newNode);
    }

    private void rbTransplant(RBTreeNode u, RBTreeNode v) {
        if (u.parent == null) {
            root = v;
        } else if (u == u.parent.left) {
            u.parent.left = v;
        } else {
            u.parent.right = v;
        }
        if (null != v) v.parent = u.parent;
    }

    private void fixDelete(RBTreeNode node) {
        RBTreeNode sibling;
        while (node != root && node.color == RBTreeNodeColors.BLACK) {
            if (node == node.parent.left) {
                sibling = node.parent.right;
                if (sibling.color == RBTreeNodeColors.RED) {
                    sibling.color = RBTreeNodeColors.BLACK;
                    node.parent.color = RBTreeNodeColors.RED;
                    rotateLeft(node.parent);
                    sibling = node.parent.right;
                }

                if (sibling.left.color == RBTreeNodeColors.BLACK && sibling.right.color == RBTreeNodeColors.BLACK) {
                    sibling.color = RBTreeNodeColors.RED;
                    node = node.parent;
                } else {
                    if (sibling.right.color == RBTreeNodeColors.BLACK) {
                        sibling.left.color = RBTreeNodeColors.BLACK;
                        sibling.color = RBTreeNodeColors.RED;
                        rotateRight(sibling);
                        sibling = node.parent.right;
                    }

                    sibling.color = node.parent.color;
                    node.parent.color = RBTreeNodeColors.BLACK;
                    sibling.right.color = RBTreeNodeColors.BLACK;
                    rotateLeft(node.parent);
                    node = root;
                }
            } else {
                sibling = node.parent.left;
                if (sibling.color == RBTreeNodeColors.RED) {
                    sibling.color = RBTreeNodeColors.BLACK;
                    node.parent.color = RBTreeNodeColors.RED;
                    rotateRight(node.parent);
                    sibling = node.parent.left;
                }

                if (sibling.right.color == RBTreeNodeColors.BLACK) {
                    sibling.color = RBTreeNodeColors.BLACK;
                    node = node.parent;
                } else {
                    if (sibling.left.color == RBTreeNodeColors.BLACK) {
                        sibling.right.color = RBTreeNodeColors.BLACK;
                        sibling.color = RBTreeNodeColors.RED;
                        rotateLeft(sibling);
                        sibling = node.parent.left;
                    }

                    sibling.color = node.parent.color;
                    node.parent.color = RBTreeNodeColors.BLACK;
                    sibling.left.color = RBTreeNodeColors.BLACK;
                    rotateRight(node.parent);
                    node = root;
                }
            }
        }
        node.color = RBTreeNodeColors.BLACK;
    }

    public RBTreeNode minimum(RBTreeNode node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    private void deleteNodeHelper(RBTreeNode node, int key) {
        RBTreeNode z = null;
        RBTreeNode x, y;
        while (node != null) {
            if (node.value == key) {
                z = node;
            }

            if (node.value <= key) {
                node = node.right;
            } else {
                node = node.left;
            }
        }

        if (z == null) {
            System.out.println("Couldn't find key in the tree");
            return;
        }

        y = z;
        RBTreeNodeColors yOriginalColor = y.color;
        if (z.left == null) {
            x = z.right;
            rbTransplant(z, z.right);
        } else if (z.right == null) {
            x = z.left;
            rbTransplant(z, z.left);
        } else {
            y = minimum(z.right);
            yOriginalColor = y.color;
            x = y.right;
            if (y.parent == z) {
                x.parent = y;
            } else {
                rbTransplant(y, y.right);
                y.right = z.right;
                y.right.parent = y;
            }

            rbTransplant(z, y);
            y.left = z.left;
            y.left.parent = y;
            y.color = z.color;
        }
        if (yOriginalColor == RBTreeNodeColors.BLACK) {
            fixDelete(x);
        }
    }

    public void deleteValue(int value) {
        deleteNodeHelper(this.getRoot(), value);
    }

    public int getTreeDepth() {
        return getTreeDepthRecursive(this.getRoot());
    }

    public void rotateLeft(RBTreeNode node) {
        if (null == node || null == node.right) return;
        RBTreeNode rightSon = node.right;
        node.right = rightSon.left; // левое поддерево перевешиваем на место правого сына
        if (rightSon.left != null) {
            rightSon.left.parent = node;
        }
        rightSon.parent = node.parent;
        if (node.parent == null) {
            this.root = rightSon;
        } else if (node == node.parent.left) {
            node.parent.left = rightSon;
        } else {
            node.parent.right = rightSon;
        }
        rightSon.left = node; // узел становится ДЕВЫМ ПОТОМКА ПРРАВОГО СЫНА
        node.parent = rightSon;
    }

    public void rotateRight(RBTreeNode node) {
        if (null == node || null == node.left) return;
        RBTreeNode leftSon = node.left;
        node.left = leftSon.right; // правое поддерево перевешиваем на место левого сына
        if (leftSon.right != null) {
            leftSon.right.parent = node;
        }
        leftSon.parent = node.parent; // левого сына подвешиваем на место узла
        if (node.parent == null) {
            this.root = leftSon;
        } else if (node == node.parent.right) {
            node.parent.right = leftSon;
        } else {
            node.parent.left = leftSon;
        }
        leftSon.right = node; // узел становится ПРАВЫМ ПОТОМКА ЛЕВОГО СЫНА
        node.parent = leftSon;
    }

    public void printTree() {
        final String emptyNodeString = "∙B"; //⋮
        final int treeDepth = this.getTreeDepth();
        int currentDepth = 1;
        Queue<RBTreeNode> queueThisLevel, queueNextLevel = new LinkedList<RBTreeNode>();
        queueNextLevel.add(this.getRoot());
        do {
            int currentLineDelimiters = (int) (Math.pow(2, (treeDepth - currentDepth))) - 1;
            queueThisLevel = queueNextLevel;
            queueNextLevel = new LinkedList<>();
            while (!queueThisLevel.isEmpty()) {
                RBTreeNode currentNode = queueThisLevel.poll();
                if (null == currentNode) {
                    printStringFixedLength(emptyNodeString);
                    queueNextLevel.add(null);
                    queueNextLevel.add(null);
                } else {
                    printStringFixedLength(currentNode.toString());
                    queueNextLevel.add(currentNode.left);
                    queueNextLevel.add(currentNode.right);
                }
                for (int i = 0; i < currentLineDelimiters; i++) printStringFixedLength("");
            }
            System.out.println();
            currentDepth++;
        } while (!(1 == queueNextLevel.stream().distinct().count() && queueNextLevel.element() == null));
    }

    protected int getTreeDepthRecursive(RBTreeNode node) {
        if (node == null) return 0;
        return Math.max(getTreeDepthRecursive(node.left), getTreeDepthRecursive(node.right)) + 1;
    }

}