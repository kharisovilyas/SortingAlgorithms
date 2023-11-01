package com.example.sortingalgorithms.ui.view;

import com.example.sortingalgorithms.entry.InputFile;
import com.example.sortingalgorithms.entry.Validators;
import com.example.sortingalgorithms.ui.viewmodel.ChartViewModel;
import javafx.collections.FXCollections;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;
import java.util.Optional;

// Модуль Представления
public class ChartView {
    private XYChart.Series<Number, Number> series;
    private final ChartViewModel viewModel;
    private LineChart<Number, Number> lineChart;
    private Label countLabel;
    private TextField countField;
    private Button addButton;
    private Button renderButton;
    private ListView<String> dataListView;
    private Label fileInputLabel;
    private TextField fileInputField;
    private Button readFromFileButton;

    public ChartView(ChartViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public void renderChart() {
        Map<Integer, Long> data = viewModel.getData();
        for (Number key : data.keySet()) {
            series.getData().add(new XYChart.Data<>(key, data.get(key)));
        }
    }

    public void creatingChart() {
        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();
        lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("Сomplexity chart of the tree sorting algorithm");
        series = new XYChart.Series<>();
        lineChart.getData().add(series);
    }
    // Создание элементов управления для ввода данных
    public void createDataInputControls() {
        countLabel = new Label("Enter the number of elements:");
        countField = new TextField();

        dataListView = new ListView<>(FXCollections.observableArrayList());
        dataListView.setPrefHeight(200);
        dataListView.setEditable(true);

        addButton = new Button("Add Data");
        renderButton = new Button("Render Chart");

        addButton.setOnAction(e -> handleAddData(countField, dataListView));
        renderButton.setOnAction(e -> handleRenderChart());
    }

    public void handleRenderChart() {
        series.getData().clear();
        renderChart();
        viewModel.removeChartData();
        viewModel.removeDataSortingList();
    }

    public void handleAddData(TextField countField, ListView<String> dataListView) {
        String countText = countField.getText();
        Validators validators = new Validators();
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
    }

    // Создание окна ввода данных из файла
    public void createFileInputControls() {
        fileInputLabel = new Label("Enter the path to the data file");
        fileInputField = new TextField();
        readFromFileButton = new Button("Read Data from File");
        readFromFileButton.setOnAction(e -> handleReadDataFromFile(fileInputField));
    }

    public void handleReadDataFromFile(TextField fileInputField) {
        InputFile inputFile = new InputFile(viewModel);
        String fileName = fileInputField.getText();
        inputFile.loadNumericDataFromFile(fileName);
    }

    public BorderPane interfaceLayout(){
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
        return borderPane;
    }
}