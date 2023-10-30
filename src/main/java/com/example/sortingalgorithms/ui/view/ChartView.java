package com.example.sortingalgorithms.ui.view;

import com.example.sortingalgorithms.ui.viewmodel.ChartViewModel;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ListView;

import java.util.Hashtable;

// Модуль Представления
public class ChartView {
    private final XYChart.Series<Number, Number> series;
    private ListView<String> dataListView;
    private ChartViewModel viewModel;

    public ChartView(XYChart.Series<Number, Number> series, ListView<String> dataListView, ChartViewModel viewModel) {
        this.series = series;
        this.dataListView = dataListView;
        this.viewModel = viewModel;
    }

    public void renderChart() {
        series.getData().clear();
        Hashtable<Double, Integer> data = viewModel.getData();
        for (Number key : data.keySet()) {
            series.getData().add(new XYChart.Data<>(key, data.get(key)));
        }
    }
}