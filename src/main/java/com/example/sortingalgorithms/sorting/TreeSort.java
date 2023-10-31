package com.example.sortingalgorithms.sorting;

import java.util.List;
import java.util.Map;

public class TreeSort<T extends Comparable<T>> {

    public List<T> sort(List<T> inputList) {
        Tree<T> tree = new Tree<>();
        tree = tree.toCreateTreeFromList(inputList);
        return tree.toMakeInorder();
    }

    public Map<Integer, Long> createDataAboutSorting(List<T> inputList) {
        Tree<T> tree = new Tree<>();
        tree = tree.toCreateTreeFromList(inputList);
        tree.toMakeInorder();
        return tree.getToSaveDataAboutSorting();
    }

}
