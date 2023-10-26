package com.example.sortingalgorithms.viewmodel;

import com.example.sortingalgorithms.model.ChartDataModel;

import java.util.Hashtable;

// Модуль ViewModel
public class ChartViewModel {
    private ChartDataModel dataModel;

    public ChartViewModel(ChartDataModel dataModel) {
        this.dataModel = dataModel;
    }

    public void addData(Number key, Number value) {
        dataModel.addData(key, value);
    }

    public Hashtable<Number, Number> getData() {
        return dataModel.getData();
    }
}
