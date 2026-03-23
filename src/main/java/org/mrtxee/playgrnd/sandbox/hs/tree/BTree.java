package org.mrtxee.playgrnd.sandbox.hs.tree;

import java.util.LinkedList;
import java.util.Queue;

public abstract class BTree<T extends Comparable<T>> implements Tree<T> {
  protected int depth = 0;
  private BTreeNode<T> root;

  protected static void printStringFixedLength(String string) {
    int length = 8;
    System.out.printf("%1$" + length + "s", string);
  }

  protected int getTreeDepthRecursive(BTreeNode<T> node) {
    if (node == null) {
      return 0;
    }
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
    final String emptyNodeString = "∙";
    final int treeDepth = this.getTreeDepth();
    int currentDepth = 1;
    Queue<BTreeNode<T>> queueThisLevel;
    Queue<BTreeNode<T>> queueNextLevel = new LinkedList<>();
    queueNextLevel.add(this.getRoot());
    do {
      int currentLineDelimiters = (int) (Math.pow(2, (treeDepth - currentDepth))) - 1;
      queueThisLevel = queueNextLevel;
      queueNextLevel = new LinkedList<>();
      while (!queueThisLevel.isEmpty()) {
        BTreeNode<T> currentNode = queueThisLevel.poll();
        if (null == currentNode) {
          printStringFixedLength(emptyNodeString);
          queueNextLevel.add(null);
          queueNextLevel.add(null);
        } else {
          printStringFixedLength(currentNode.value.toString());
          queueNextLevel.add(currentNode.left);
          queueNextLevel.add(currentNode.right);
        }
        for (int i = 0; i < currentLineDelimiters; i++) {
          printStringFixedLength("");
        }
      }
      System.out.println();
      currentDepth++;
    } while (!(1 == queueNextLevel.stream().distinct().count()
        && queueNextLevel.element() == null));
  }

  public BTreeNode<T> getRoot() {
    return root;
  }

  protected void setRoot(BTreeNode<T> root) {
    this.root = root;
  }

  protected static class BTreeNode<T extends Comparable<T>> {
    final T value;
    private BTreeNode<T> left;
    private BTreeNode<T> right;

    protected BTreeNode(T value) {
      this.value = value;
      this.right = null;
      this.left = null;
    }

    public T getValue() {
      return value;
    }

    public BTreeNode<T> getLeft() {
      return left;
    }

    public void setLeft(BTreeNode<T> left) {
      this.left = left;
    }

    public BTreeNode<T> getRight() {
      return right;
    }

    public void setRight(BTreeNode<T> right) {
      this.right = right;
    }

    public boolean hasRightSon() {
      return (this.right != null);
    }

    public boolean hasLeftSon() {
      return (this.left != null);
    }

  }

}
