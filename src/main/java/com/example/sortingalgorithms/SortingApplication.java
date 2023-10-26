package com.example.sortingalgorithms;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.util.Hashtable;
import java.util.Optional;

public class SortingApplication extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Chart with Input List");

        // Create chart
        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();
        LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("Chart");
        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        lineChart.getData().add(series);

        // Create input controls
        Label countLabel = new Label("Enter the number of elements:");
        TextField countField = new TextField();

        ListView<String> dataListView = new ListView<>(FXCollections.observableArrayList());
        dataListView.setPrefHeight(200);
        dataListView.setEditable(true);

        Button addButton = new Button("Add Data");
        Button renderButton = new Button("Render Chart");

        addButton.setOnAction(e -> {
            int count = Integer.parseInt(countField.getText());
            if (count > 0) {
                for (int i = 1; i <= count; i++) {
                    TextInputDialog dialog = new TextInputDialog();
                    dialog.setTitle("Data Input");
                    dialog.setHeaderText("Enter data for element " + i + ":");
                    dialog.setContentText("Data:");
                    Optional<String> result = dialog.showAndWait();
                    result.ifPresent(dataListView.getItems()::add);
                }
            }
        });

        renderButton.setOnAction(e -> {
            renderChart(series, dataListView);
        });

        // Layout
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(lineChart);

        VBox inputVBox = new VBox(countLabel, countField, addButton, dataListView, renderButton);
        inputVBox.setSpacing(10);
        borderPane.setRight(inputVBox);
        // Get screen information
        Screen primaryScreen = Screen.getPrimary();
        double screenWidth = primaryScreen.getBounds().getWidth();
        double screenHeight = primaryScreen.getBounds().getHeight();
        double borderAndTitleBarHeight = screenHeight - primaryScreen.getVisualBounds().getHeight();

        // Create scene with adjusted size
        Scene scene = new Scene(borderPane, screenWidth, screenHeight - borderAndTitleBarHeight*1.5);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void renderChart(XYChart.Series<Number, Number> series, ListView<String> dataListView) {
        series.getData().clear();

        Hashtable<Number, Number> dataHashtable = new Hashtable<>();
        ObservableList<String> items = dataListView.getItems();

        for (int i = 0; i < items.size(); i++) {
            String data = items.get(i);
            try {
                double value = Double.parseDouble(data);
                dataHashtable.put(i + 1, value);
            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Data Error");
                alert.setHeaderText("Invalid Data");
                alert.setContentText("Data for element " + (i + 1) + " is not a valid number.");
                alert.showAndWait();
                return;
            }
        }

        for (Number key : dataHashtable.keySet()) {
            series.getData().add(new XYChart.Data<>(key, dataHashtable.get(key)));
        }
    }
}