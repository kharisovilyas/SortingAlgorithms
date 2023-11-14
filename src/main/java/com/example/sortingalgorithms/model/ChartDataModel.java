package com.example.sortingalgorithms.model;

import com.example.sortingalgorithms.sorting.TreeSort;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

// Модуль Модели
public class ChartDataModel {
    private final TreeSort<Double> treeSort = new TreeSort<>(); // Инициализация объекта для сортировки
    private final Map<Integer, Long> dataHashtable = new Hashtable<>(); // Хранение данных о времени выполнения и количестве перестановок
    private final List<Double> listForSorting = new ArrayList<>(); // Хранение данных для сортировки

    // Получение данных для построения графика
    public Map<Integer, Long> getChartData() {
        return treeSort.createDataAboutSorting(listForSorting);
    }

    // Добавление данных для сортировки
    public void addDataListSorting(Double value) {
        listForSorting.add(value);
    }

    // Очистка списка данных для сортировки
    public void removeDataListSorting() {
        this.listForSorting.clear();
    }

    // Очистка данных о времени выполнения и количестве перестановок
    public void removeChartData() {
        this.dataHashtable.clear();
    }
}
