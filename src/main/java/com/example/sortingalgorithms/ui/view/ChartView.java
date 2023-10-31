package com.example.sortingalgorithms.ui.view;

import com.example.sortingalgorithms.ui.viewmodel.ChartViewModel;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ListView;

import java.util.Hashtable;
import java.util.Map;

// Модуль Представления
public class ChartView {
    private final XYChart.Series<Number, Number> series;
    private final ChartViewModel viewModel;

    public ChartView(XYChart.Series<Number, Number> series, ListView<String> dataListView, ChartViewModel viewModel) {
        this.series = series;
        this.viewModel = viewModel;
    }

    public void renderChart() {
        Map<Integer, Long> data = viewModel.getData();
        for (Number key : data.keySet()) {
            series.getData().add(new XYChart.Data<>(key, data.get(key)));
        }
    }
}