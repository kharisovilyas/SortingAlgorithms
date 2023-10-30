package com.example.sortingalgorithms;

import com.example.sortingalgorithms.sorting.TreeSort;
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
    private Validators validators;
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

        // Создание и инициализация представления с передачей графика и списка данных
        addButton.setOnAction(e -> {
            int count;
            try {
                count = Integer.parseInt(countField.getText());
                if (count > 0) {
                    for (int i = 1; i <= count; i++) {
                        TextInputDialog dialog = new TextInputDialog();
                        dialog.setTitle("Data Input");
                        dialog.setHeaderText("Enter data for element " + i + ":");
                        dialog.setContentText("Data:");
                        Optional<String> result = dialog.showAndWait();


                        int finalI = i;
                        result.ifPresent(data -> {
                            dataListView.getItems().add(data);
                            viewModel.addData(Double.parseDouble(data), finalI);
                        });

                        //TODO: КОГДА БУДЕТ ОБНОВЛЕНИЕ ВЕТКИ РАЗКОМЕНТИТЬ
                        // tableDataCharts = algorithmSorting.sort(listForSorting);
                        // а выше закомментить

/*
                        result.ifPresent(data -> {
                            listForSorting.add(Double.parseDouble(data));
                        });
*/

                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Data Input Error");
                    alert.setHeaderText("Invalid Count");
                    alert.setContentText("Please enter a valid count (greater than 0).");
                    alert.showAndWait();
                }
            } catch (NumberFormatException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Data Input Error");
                alert.setHeaderText("Invalid Input");
                alert.setContentText("Please enter a valid number for the count.");
                alert.showAndWait();
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

        VBox inputVBox = new VBox(countLabel, countField, addButton, dataListView, renderButton);
        inputVBox.setSpacing(10);
        borderPane.setRight(inputVBox);

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
