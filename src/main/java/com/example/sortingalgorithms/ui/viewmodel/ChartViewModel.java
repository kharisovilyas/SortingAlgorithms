package com.example.sortingalgorithms.ui.viewmodel;

import com.example.sortingalgorithms.ui.model.ChartDataModel;

import java.util.Hashtable;

// Модуль ViewModel
public class ChartViewModel {
    private final ChartDataModel dataModel;

    public ChartViewModel(ChartDataModel dataModel) {
        this.dataModel = dataModel;
    }

    public void addData(Double key, Integer value) {
        dataModel.addData(key, value);
    }
    public void addData(Hashtable<Double, Integer> list) {
        dataModel.updateDataList(list);
    }

    public Hashtable<Double, Integer> getData() {
        return dataModel.getData();
    }
}
