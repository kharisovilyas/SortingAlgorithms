package com.example.sortingalgorithms.ui.viewmodel;

import com.example.sortingalgorithms.model.ChartDataModel;
import javafx.scene.control.Alert;
import test.TestFileCreator;

import java.util.Map;

// Модуль ViewModel
public class ChartViewModel {
    private final ChartDataModel dataModel; // Модель данных
    private Integer numberOfTest = 0;

    // Конструктор ViewModel, принимающий модель данных
    public ChartViewModel(ChartDataModel dataModel) {
        this.dataModel = dataModel;
    }

    // Получение данных для графика
    public Map<Integer, Long> getData() {
        return dataModel.getChartData();
    }

    // Очистка списка данных для сортировки
    public void removeDataSortingList() {
        dataModel.removeDataListSorting();
    }

    // Очистка данных для графика
    public void removeChartData() {
        dataModel.removeChartData();
    }

    // Добавление элемента в список данных для сортировки
    public void addListSortingElement(Double value) {
        dataModel.addDataListSorting(value);
    }

    // Отображение окна с сообщением об ошибке
    public void showErrorAlert(String title, String headerText, String contentText) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.showAndWait();
    }

    // Создание тестовых файлов
    public void createTestFiles() {
        TestFileCreator testFileCreator = new TestFileCreator();
        testFileCreator.creatingTestFile(fileName(), 100, true);
        testFileCreator.creatingTestFile(fileName(), 1000, true);
        testFileCreator.creatingTestFile(fileName(), 100, false);
        testFileCreator.creatingTestFile(fileName(), 1000, false);
        testFileCreator.creatingTestFile(fileName(), 10, false);
        numberOfTest = 0;
    }

    // Удаление тестовых файлов
    public void deleteTestFiles() {
        TestFileCreator testFileCreator = new TestFileCreator();
        testFileCreator.deleteTestFiles();
    }

    // Генерация имени файла с учетом увеличения числа теста
    private String fileName() {
        numberOfTest++;
        return numberOfTest + ".txt";
    }
}
