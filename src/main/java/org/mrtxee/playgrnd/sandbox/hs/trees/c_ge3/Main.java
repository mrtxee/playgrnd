package org.mrtxee.playgrnd.sandbox.hs.trees.c_ge3;

import java.util.Objects;
import java.util.Random;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class Main {

    public static void OTTest() {
        OtherTree ot = new OtherTree();
        fillTreeRandom(ot, 8);
        ot.printTree();
        ot.reflectNodes();
        System.out.println("Reflect tree:");
        ot.printTree();
        System.out.println("Tree depth: " + ot.getTreeDepth());
    }

    public static void BSTTest() {
        BSTree<Integer> bst = new BSTree<Integer>();
        setTreeTestData(bst);
        //fillTreeRandom2(bst, 6);
        bst.printTree();
        System.out.println("Tree depth: " + bst.getTreeDepth());
        System.out.println("Delete node 3:");
        bst.delete(3);
        bst.printTree();
    }

    public static void RBTree_RotationTest() {
        RBTree<Integer> rbt = new RBTree<Integer>();
        rbt.insert(50);

        rbt.insert(20);
        rbt.insert(70);

        rbt.insert(8);
        rbt.insert(16);
        rbt.insert(42);
        rbt.insert(49);

        rbt.insert(7);
        rbt.insert(6);
        rbt.insert(15);
        rbt.insert(17);
        rbt.insert(41);
        rbt.insert(69);
        rbt.insert(71);

        System.out.println("= = = = = = ");
        rbt.printTree();
        //

        RBTree.RBTreeNode rotationNode;

        rotationNode = rbt.getRoot();
        System.out.println("try ROOT left, right rotation " + rotationNode.toString());
        rbt.rotateRight(rotationNode);
        rbt.rotateLeft(rotationNode);
        rbt.printTree();

        rotationNode = rbt.getRoot().getRight();
        System.out.println("try ROOT SON left rotation " + rotationNode.toString());
        rbt.rotateLeft(rotationNode);
        rbt.printTree();

        rotationNode = rbt.getRoot().getLeft().getLeft().getRight();
        System.out.println("try left rotation " + rotationNode.toString());
        rbt.rotateLeft(rotationNode);
        rbt.printTree();

        rotationNode = rbt.getRoot().getLeft().getLeft().getLeft();
        System.out.println("try right rotation " + rotationNode.toString());
        rbt.rotateRight(rotationNode);
        rbt.printTree();
    }

    public static void RBTree_InsertDeleteTest() {
        System.out.println("apply to build test tree");
        RBTree<Integer> rbt = new RBTree<Integer>();
        rbt.insert(30);
        rbt.insert(20);
        rbt.insert(40);
        rbt.insert(10);
        rbt.insert(20);
        rbt.insert(30);
        rbt.insert(30); // <-
        rbt.insert(20);
        rbt.insert(21);
        rbt.insert(22);
        rbt.insert(23);
        rbt.insert(24);
        rbt.insert(25);
        rbt.insert(21);
        rbt.insert(19);
        rbt.insert(19);
        rbt.insert(19);
        rbt.insert(18);
        rbt.printTree();
        System.out.println("= = = = = = = = = = = = ");
        int deleteValue = 25;
        System.out.println("apply to delete " + deleteValue);
        rbt.delete(deleteValue);
        rbt.printTree();
    }

    public static void RBTree_RotationTest2() {
        RBTree<Integer> rbt = new RBTree<>();
        rbt.insert(3);
        rbt.insert(2);
        rbt.insert(4);
        rbt.insert(1);
        rbt.insert(2);
        rbt.insert(3);
        rbt.insert(4); // <-

        RBTree.RBTreeNode<Integer> rotationNode = rbt.getRoot().getRight().getRight();
        System.out.println("try left rotation " + rotationNode.toString());
        rbt.rotateLeft(rotationNode);
        rbt.printTree();
    }

    public static void RBTree_CommonTest() {
        RBTree<Integer> rbt = new RBTree<>();
        int deleteValue = 3;
        rbt.insert(5);
        rbt.insert(2);
        rbt.insert(8);
        rbt.insert(1);
        rbt.insert(2);
        rbt.insert(9);
        rbt.insert(4); // <-
        rbt.insert(deleteValue);
        //fillTreeRandom(rbt, 16);
        rbt.printTree();
        System.out.println("Tree depth: " + rbt.getTreeDepth());
        System.out.println("delete test val=" + deleteValue);
        rbt.delete(deleteValue);
        rbt.printTree();
    }

    public static void RBTree_StringTest() {
        RBTree<String> rbtStrings = new RBTree<>();
        rbtStrings.insert("qw");
        rbtStrings.insert("er");
        rbtStrings.insert("bb");
        rbtStrings.insert("no");
        rbtStrings.insert("w");
        rbtStrings.insert("i");
        rbtStrings.insert("know");
        rbtStrings.insert("ge");
        rbtStrings.insert("ner");
        rbtStrings.insert("ics");
        rbtStrings.printTree();
        System.out.printf("Tree min val is %s %n", rbtStrings.getMinimumValue());
    }

    static public <T extends BTree<Integer>> void setTreeTestData(T bn) {
        bn.insert(5);
        bn.insert(3);
        bn.insert(7);
        bn.insert(1);
        bn.insert(4);
        bn.insert(6);
        bn.insert(8);
        bn.insert(11);
        bn.insert(2);
    }

    static public <T extends BTree<Integer>> void fillTreeRandom(T tree, int count) {
        Random rand = new Random();
        int min = 1, max = 99;

        for (int i = 0; i < count; i++) {
            int randomNum = rand.nextInt((max - min) + 1) + min;
            tree.insert(randomNum);
        }
    }

    public static void main(String[] args) {
        Consumer<String> printConsumer = t -> {
            if (Objects.equals(t, "New York")) {
                System.out.println("some usa city");
            } else {
                System.out.println(t);
            }
        };
        Stream<String> cities = Stream.of("Sydney", "Dhaka", "New York", "London");
        cities.forEach(printConsumer);

        //RBTree_CommonTest();
        //        OTTest();
        //        BSTTest();

        //        RBTree_RotationTest();
        //        RBTree_RotationTest2();
        //        RBTree_InsertDeleteTest();
    }
}
