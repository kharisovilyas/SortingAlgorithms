package com.example.sortingalgorithms.sorting;

import java.util.List;
import java.util.Map;

public class TreeSort <T extends Comparable<T>>  {
    // метод просто сортирует лист Древесной сортировкой
    public List<T> sort (List<T> inputList) {
        Tree<T> tree = new Tree<>();
        tree = tree.toCreateTreeFromList(inputList);
        return tree.toMakeInorder();
    }

    //метод, который предоставляет таблицу,
    // где ключ - кол-во отсортированных элементов, а значение - время сортировки
    public Map<Integer,Long> createDataAboutSorting(List<T> inputList){
        Tree<T> tree = new Tree<>();
        tree = tree.toCreateTreeFromList(inputList);
        tree.toMakeInorder();
        return tree.getToSaveDataAboutSorting();
    }
}