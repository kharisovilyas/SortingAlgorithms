package com.example.sortingalgorithms.ui.viewmodel;

import com.example.sortingalgorithms.model.ChartDataModel;
import javafx.scene.control.Alert;
import test.TestFileCreator;

import java.util.Map;

// Модуль ViewModel
public class ChartViewModel {
    private final ChartDataModel dataModel;
    private Integer numberOfTest = 0;

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

    public void removeDataSortingList() {
        dataModel.removeDataListSorting();
    }

    public void removeChartData() {
        dataModel.removeChartData();
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

    public void createTestFiles() {
        TestFileCreator testFileCreator = new TestFileCreator();
        testFileCreator.creatingTestFile(fileName(), 100, true);
        testFileCreator.creatingTestFile(fileName(), 1000, true);
        testFileCreator.creatingTestFile(fileName(), 100, false);
        testFileCreator.creatingTestFile(fileName(), 1000, false);
        testFileCreator.creatingTestFile(fileName(), 10, false);
        numberOfTest = 0;
    }

    public void deleteTestFiles() {
        TestFileCreator testFileCreator = new TestFileCreator();
        testFileCreator.deleteTestFiles();
    }

    private String fileName() {
        numberOfTest++;
        return numberOfTest + ".txt";
    }
}
