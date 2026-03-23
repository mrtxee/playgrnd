package org.mrtxee.playgrnd.sandbox.hs.trees.a_func;

import java.util.Random;

public class Main {
    public static void BTTest() {
        BinaryTree bt = new BinaryTree();
        fillTreeRandom(bt, 8);
        bt.printTree();
        bt.reflectNodes();
        System.out.println("Reflect tree:");
        bt.printTree();
    }

    public static void BSTTest() {
        BinarySearchTree bst = new BinarySearchTree();
        setTreeTestData(bst);
        //fillTreeRandom(bst, 6);
        bst.printTree();
        System.out.println("Tree depth: " + bst.getTreeDepth());
        System.out.println("Delete node 3:");
        bst.deleteNodeByVal(3);
        bst.printTree();
    }

    public static void RBTree_RotationTest() {
        RBTree rbt = new RBTree();
        rbt.insertValue(50);

        rbt.insertValue(20);
        rbt.insertValue(70);

        rbt.insertValue(8);
        rbt.insertValue(16);
        rbt.insertValue(42);
        rbt.insertValue(49);

        rbt.insertValue(7);
        rbt.insertValue(6);
        rbt.insertValue(15);
        rbt.insertValue(17);
        rbt.insertValue(41);
        rbt.insertValue(69);
        rbt.insertValue(71);

        System.out.println("= = = = = = ");
        rbt.printTree();
        //
        RBTreeNode rotationNode;

        rotationNode = rbt.getRoot();
        System.out.println("try ROOT left, right rotation " + rotationNode.toString());
        rbt.rotateRight(rotationNode);
        rbt.rotateLeft(rotationNode);
        rbt.printTree();

        rotationNode = rbt.getRoot().right;
        System.out.println("try ROOT SON left rotation " + rotationNode.toString());
        rbt.rotateLeft(rotationNode);
        rbt.printTree();

        rotationNode = rbt.getRoot().left.left.right;
        System.out.println("try left rotation " + rotationNode.toString());
        rbt.rotateLeft(rotationNode);
        rbt.printTree();

        rotationNode = rbt.getRoot().left.left.left;
        System.out.println("try right rotation " + rotationNode.toString());
        rbt.rotateRight(rotationNode);
        rbt.printTree();
    }

    public static void RBTree_InsertDeleteTest() {
        System.out.println("apply to build test tree");
        RBTree rbt = new RBTree();
        rbt.insertValue(30);
        rbt.insertValue(20);
        rbt.insertValue(40);
        rbt.insertValue(10);
        rbt.insertValue(20);
        rbt.insertValue(30);
        rbt.insertValue(30); // <-
        rbt.insertValue(20);
        rbt.insertValue(21);
        rbt.insertValue(22);
        rbt.insertValue(23);
        rbt.insertValue(24);
        rbt.insertValue(25);
        rbt.insertValue(21);
        rbt.insertValue(19);
        rbt.insertValue(19);
        rbt.insertValue(19);
        rbt.insertValue(18);
        rbt.printTree();
        System.out.println("= = = = = = = = = = = = ");
        int deleteValue = 25;
        System.out.println("apply to delete " + deleteValue);
        rbt.deleteValue(deleteValue);
        rbt.printTree();
    }

    public static void RBTree_RotationTest2() {
        RBTree rbt = new RBTree();
        rbt.insertValue(3);
        rbt.insertValue(2);
        rbt.insertValue(4);
        rbt.insertValue(1);
        rbt.insertValue(2);
        rbt.insertValue(3);
        rbt.insertValue(4); // <-

        RBTreeNode rotationNode = rbt.getRoot().right.right;
        System.out.println("try left rotation " + rotationNode.toString());
        rbt.rotateLeft(rotationNode);
        rbt.printTree();
    }

    public static void RBTree_CommonTest() {
        RBTree rbt = new RBTree();
        fillTreeRandom(rbt, 40);
        rbt.printTree();
    }

    public static void main(String[] args) {
        //RBTree_RotationTest();
        //RBTree_RotationTest2();
        //RBTree_InsertDeleteTest();
        //        RBTree_CommonTest();

        //        BTTest();
        BSTTest();
    }

    static public void setTreeTestData(BinarySearchTree bn) {
        bn.insertValue(5);
        bn.insertValue(3);
        bn.insertValue(7);
        bn.insertValue(1);
        bn.insertValue(4);
        bn.insertValue(6);
        bn.insertValue(8);
        bn.insertValue(11);
        bn.insertValue(2);
    }

    static public void fillTreeRandom(BinarySearchTree bn, int count) {
        Random rand = new Random();
        int min = 1, max = 99;

        for (int i = 0; i < count; i++) {
            int randomNum = rand.nextInt((max - min) + 1) + min;
            bn.insertValue(randomNum);
        }
    }

    static public void fillTreeRandom(RBTree bn, int count) {
        Random rand = new Random();
        int min = 1, max = 99;

        for (int i = 0; i < count; i++) {
            int randomNum = rand.nextInt((max - min) + 1) + min;
            bn.insertValue(randomNum);
        }
    }
}
