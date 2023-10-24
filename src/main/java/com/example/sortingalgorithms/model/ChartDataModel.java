package com.example.sortingalgorithms.model;

import java.util.Hashtable;

// Модуль Модели
public class ChartDataModel {
    private final Hashtable<Number, Number> dataHashtable = new Hashtable<>();

    public void addData(Number key, Number value) {
        dataHashtable.put(key, value);
    }

    public Hashtable<Number, Number> getData() {
        return dataHashtable;
    }
}