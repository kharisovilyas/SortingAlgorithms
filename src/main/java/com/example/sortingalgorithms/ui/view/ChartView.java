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
import java.util.Map;
import java.util.Optional;

// Модуль Представления
public class ChartView {
    private XYChart.Series<Number, Number> series; // Серия данных для графика
    private final ChartViewModel viewModel; // ViewModel, связанная с представлением
    private LineChart<Number, Number> lineChart; // График
    private Label countLabel;
    private TextField countField;
    private Button addButton;
    private Button renderButton;
    private ListView<String> dataListView;
    private Label fileInputLabel;
    private TextField fileInputField;
    private Button readFromFileButton;
    private Button startTestsButton;
    private Button nextButton;
    private int nextButtonClickCount; // Счетчик нажатий на кнопку "Next"
    private final Integer NUMBER_OF_TESTS = 5; // Количество тестов

    // Конструктор, принимающий ViewModel
    public ChartView(ChartViewModel viewModel) {
        this.viewModel = viewModel;
        nextButtonClickCount = 0; // Инициализация счетчика
    }

    // Создание элементов управления для тестов
    public void createTestControls() {
        startTestsButton = new Button("Start Module Tests");
        nextButton = new Button("Next");
        nextButton.setVisible(false);
        startTestsButton.setOnAction(e -> handleStartTests());
        nextButton.setOnAction(e -> handleNextButton());
    }

    // Отрисовка графика
    public void renderChart() {
        Map<Integer, Long> data = viewModel.getData();
        for (Number key : data.keySet()) {
            series.getData().add(new XYChart.Data<>(key, data.get(key)));
        }
    }

    // Создание графика
    public void creatingChart() {
        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();
        lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("Сomplexity chart of the tree sorting algorithm");
        // Подписи для осей
        xAxis.setLabel("Number of Swaps");
        yAxis.setLabel("Execution Time (microseconds)");
        series = new XYChart.Series<>();
        series.setName("Algorithm Notation");
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

    // Обработчик нажатия кнопки "Render Chart"
    public void handleRenderChart() {
        series.getData().clear();
        renderChart();
        viewModel.removeChartData();
        viewModel.removeDataSortingList();
    }

    // Обработчик нажатия кнопки "Add Data"
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

    // Создание элементов управления для ввода данных из файла
    public void createFileInputControls() {
        fileInputLabel = new Label("Enter the path to the data file");
        fileInputField = new TextField();
        readFromFileButton = new Button("Read Data from File");
        readFromFileButton.setOnAction(e -> handleReadDataFromFile(fileInputField));
    }

    // Обработчик нажатия кнопки "Read Data from File"
    public void handleReadDataFromFile(TextField fileInputField) {
        InputFile inputFile = new InputFile(viewModel);
        String fileName = fileInputField.getText();
        inputFile.loadNumericDataFromFile(fileName);
    }

    // Обработчик нажатия кнопки "Start Module Tests"
    public void handleStartTests() {
        InputFile inputFile = new InputFile(viewModel);
        nextButtonClickCount++;
        viewModel.createTestFiles();
        inputFile.loadNumericDataFromFile(nextButtonClickCount + ".txt");
        handleRenderChart();
        startTestsButton.setVisible(false);
        nextButton.setVisible(true);
    }

    // Обработчик нажатия кнопки "Next"
    public void handleNextButton() {
        InputFile inputFile = new InputFile(viewModel);
        nextButtonClickCount++;
        viewModel.createTestFiles();
        inputFile.loadNumericDataFromFile(nextButtonClickCount + ".txt");
        handleRenderChart();
        if (nextButtonClickCount == NUMBER_OF_TESTS) {
            nextButton.setVisible(false);
            startTestsButton.setVisible(true);
            nextButtonClickCount = 0;
            viewModel.deleteTestFiles();
        }
    }

    // Создание интерфейса приложения
    public BorderPane interfaceLayout(){
        // Размещение элементов в интерфейсе
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(lineChart);
        // Размещение окон ввода данных и чтения данных из файла
        VBox inputVBox = new VBox(countLabel, countField, addButton, dataListView);
        VBox fileInputVBox = new VBox(fileInputLabel, fileInputField, readFromFileButton);
        VBox testVBox = new VBox(startTestsButton, nextButton);
        inputVBox.setSpacing(20);
        fileInputVBox.setSpacing(20);
        testVBox.setSpacing(10);
        VBox vbox = new VBox(inputVBox, fileInputVBox, renderButton, testVBox);
        vbox.setSpacing(100);
        borderPane.setRight(vbox); // Размещаем оба VBox справа друг под другом
        return borderPane;
    }
}
