package com.example.sortingalgorithms.ui.model;

import java.util.Hashtable;

// Модуль Модели
public class ChartDataModel {
    private Hashtable<Double, Integer> dataHashtable = new Hashtable<>();

    public void addData(Double timeOfImpl, Integer numberOfPermutations) {
        dataHashtable.put(timeOfImpl, numberOfPermutations);
    }

    public Hashtable<Double, Integer> getData() {
        return dataHashtable;
    }

    public void updateDataList(Hashtable<Double, Integer> list) {
        dataHashtable = list;
    }
}