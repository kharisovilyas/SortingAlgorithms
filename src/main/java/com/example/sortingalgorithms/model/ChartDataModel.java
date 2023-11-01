package com.example.sortingalgorithms.model;

import com.example.sortingalgorithms.sorting.TreeSort;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

// Модуль Модели
public class ChartDataModel {
    private TreeSort<Double> treeSort = new TreeSort<>();
    private Map<Integer, Long> dataHashtable = new Hashtable<>();
    private List<Double> listForSorting = new ArrayList<>();

    public void addDataHashTable(Integer timeOfImpl, Long numberOfPermutations) {
        dataHashtable.put(timeOfImpl, numberOfPermutations);
    }

    public Map<Integer, Long> getChartData() {
        return treeSort.createDataAboutSorting(listForSorting);
    }

    public void updateChartData() {
        dataHashtable = treeSort.createDataAboutSorting(listForSorting);
    }

    public void addDataListSorting(Double value) {
        listForSorting.add(value);
    }

    public List<Double> getListData() {
        return listForSorting;
    }

    public void updateListData(List<Double> list) {
        listForSorting = list;
    }

    public List<Double> getListForSorting() {
        return listForSorting;
    }

    public void setListForSorting(List<Double> listForSorting) {
        this.listForSorting = listForSorting;
    }
    public void removeDataListSorting(){
        this.listForSorting.clear();
    }
    public void removeChartData(){
        this.dataHashtable.clear();
    }
}