package com.example.sortingalgorithms.view;

import com.example.sortingalgorithms.viewmodel.ChartViewModel;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ListView;

import java.util.Hashtable;

// Модуль Представления
public class ChartView {
    private XYChart.Series<Number, Number> series;
    private ListView<String> dataListView;

    public ChartView(XYChart.Series<Number, Number> series, ListView<String> dataListView) {
        this.series = series;
        this.dataListView = dataListView;
    }

    public void renderChart(ChartViewModel viewModel) {
        series.getData().clear();
        Hashtable<Number, Number> data = viewModel.getData();

        for (Number key : data.keySet()) {
            series.getData().add(new XYChart.Data<>(key, data.get(key)));
        }
    }
}