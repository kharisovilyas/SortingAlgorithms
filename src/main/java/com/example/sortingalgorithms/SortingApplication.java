package com.example.sortingalgorithms;

import com.example.sortingalgorithms.sorting.TreeSort;
import com.example.sortingalgorithms.ui.entry.InputFile;
import com.example.sortingalgorithms.ui.entry.Validators;
import com.example.sortingalgorithms.ui.model.ChartDataModel;
import com.example.sortingalgorithms.ui.view.ChartView;
import com.example.sortingalgorithms.ui.viewmodel.ChartViewModel;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Optional;

// Основной класс приложения, расширяющий Application
public class SortingApplication extends Application {
    // Точка входа в приложение
    public static void main(String[] args) {
        launch(args);
    }

    // Модель данных, ViewModel и View
    private ChartDataModel dataModel; // Модель данных
    private ChartViewModel viewModel; // ViewModel
    private ChartView chartView; // Представление
    private InputFile inputFile;
    private Validators validators = new Validators();
    private ArrayList<Double> listForSorting = new ArrayList<>();
    private TreeSort<Double> algorithmSorting = new TreeSort<>();
    private Hashtable<Integer, Double> tableDataCharts = new Hashtable<>();
    // Метод, вызываемый при запуске приложения
    @Override
    public void start(Stage primaryStage) {
        // Устанавливаем заголовок окна
        primaryStage.setTitle("Chart with Input List");

        // Создание и инициализация модели данных и ViewModel
        dataModel = new ChartDataModel();
        viewModel = new ChartViewModel(dataModel);
        // Создание графика
        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();
        LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("Chart");
        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        lineChart.getData().add(series);

        // Создание элементов управления вводом данных
        Label countLabel = new Label("Enter the number of elements:");
        TextField countField = new TextField();

        ListView<String> dataListView = new ListView<>(FXCollections.observableArrayList());
        dataListView.setPrefHeight(200);
        dataListView.setEditable(true);

        // Создание и инициализация представления с передачей графика и списка данных
        Button addButton = new Button("Add Data");
        Button renderButton = new Button("Render Chart");

        // Создание окна ввода данных из файла
        Label fileInputLabel = new Label("Enter the path to the data file");
        TextField fileInputField = new TextField();
        Button readFromFileButton = new Button("Read Data from File");
        inputFile = new InputFile(viewModel);
        readFromFileButton.setOnAction(e -> {
            String fileName = fileInputField.getText();
            inputFile.loadNumericDataFromFile(fileName);
            // Здесь вы можете добавить логику для чтения данных из файла и обновления вашей модели данных
            // Например, используя InputFile.loadNumericDataFromFile(fileName)
            // Затем обновить ViewModel с прочитанными данными
        });


        // Создание и инициализация представления с передачей графика и списка данных
        addButton.setOnAction(e -> {
            String countText = countField.getText();
            if (validators.validateSizeInput(countText)) {
                int count = Integer.parseInt(countText);
                for (int i = 1; i <= count; i++) {
                    Optional<String> result;
                    do {
                        TextInputDialog dialog = new TextInputDialog();
                        dialog.setTitle("Data Input");
                        dialog.setHeaderText("Enter data for element " + i + ":");
                        dialog.setContentText("Data:");
                        result = dialog.showAndWait();
                        result.ifPresent(data -> {
                            if (validators.validateIntegerInput(data)) {
                                dataListView.getItems().add(data);
                                viewModel.addListSortingElement(Double.parseDouble(data));
                            } else {
                                viewModel.showErrorAlert("Data Input Error", "Invalid Data", "Please enter a valid number.");
                            }
                        });
                    } while (result.get().isEmpty()); // Зацикливаем ввод, пока не введены верные данные
                }
            } else {
                viewModel.showErrorAlert("Data Input Error", "Invalid Count", "Please enter a valid count (greater than 0).");
            }
        });

        //TODO: КОГДА БУДЕТ ОБНОВЛЕНИЕ ВЕТКИ РАЗКОМЕНТИТЬ
        // tableDataCharts = algorithmSorting.sort(listForSorting);
        chartView = new ChartView(series, dataListView, viewModel);
        // Обработчик кнопки для рендеринга графика
        renderButton.setOnAction(e -> {
            chartView.renderChart();
        });

        // Размещение элементов в интерфейсе
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(lineChart);

        // Размещение окон ввода данных и чтения данных из файла
        VBox inputVBox = new VBox(countLabel, countField, addButton, dataListView);
        VBox fileInputVBox = new VBox(fileInputLabel, fileInputField, readFromFileButton);
        inputVBox.setSpacing(20);
        fileInputVBox.setSpacing(20);
        VBox vbox = new VBox(inputVBox, fileInputVBox, renderButton);
        vbox.setSpacing(100);
        borderPane.setRight(vbox); // Размещаем оба VBox справа друг под другом



        // Получение информации о экране
        Screen primaryScreen = Screen.getPrimary();
        double screenWidth = primaryScreen.getBounds().getWidth();
        double screenHeight = primaryScreen.getBounds().getHeight();
        double borderAndTitleBarHeight = screenHeight - primaryScreen.getVisualBounds().getHeight();

        // Создание сцены с учетом размеров экрана
        Scene scene = new Scene(borderPane, screenWidth, screenHeight - borderAndTitleBarHeight * 1.5);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
