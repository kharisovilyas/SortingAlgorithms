package com.example.sortingalgorithms.ui.viewmodel;

import com.example.sortingalgorithms.model.ChartDataModel;
import javafx.scene.control.Alert;

import java.util.Map;

// Модуль ViewModel
public class ChartViewModel {
    private final ChartDataModel dataModel;

    public ChartViewModel(ChartDataModel dataModel) {
        this.dataModel = dataModel;
    }

    public void addChartDataElement(Integer key, Long value) {
        dataModel.addDataHashTable(key, value);
    }

    public void addChartDataList() {
        dataModel.updateChartData();
    }

    public Map<Integer, Long> getData() {
        return dataModel.getChartData();
    }

    public void addListSortingElement(Double value) {
        dataModel.addDataListSorting(value);
    }

    public void showErrorAlert(String title, String headerText, String contentText) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.showAndWait();
    }
}
