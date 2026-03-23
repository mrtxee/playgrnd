package org.mrtxee.playgrnd.sandbox.hs.trees.c_gen;

public interface Tree<T extends Comparable<T>> {

    void insert(T value);

    void delete(T value);

    int getTreeDepth();

    void printTree();

}
