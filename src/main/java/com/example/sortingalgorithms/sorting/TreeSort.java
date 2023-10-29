package com.example.sortingalgorithms.sorting;

import java.util.List;

public class TreeSort <T extends Comparable<T>>  {
    public List<T> sort (List<T> inputList) {
        Tree<T> tree = new Tree<>();
        tree = tree.toCreateTreeFromList(inputList);
        return tree.inorder();
    }
}