package com.example.sortingalgorithms;

import com.example.sortingalgorithms.entry.InputFile;
import com.example.sortingalgorithms.entry.Validators;
import com.example.sortingalgorithms.model.ChartDataModel;
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

import java.util.Optional;

// Основной класс приложения, расширяющий Application
public class SortingApplication extends Application {
    // Точка входа в приложение
    public static void main(String[] args) {
        launch(args);
    }

    // Метод, вызываемый при запуске приложения
    @Override
    public void start(Stage primaryStage) {
        // Устанавливаем заголовок окна
        primaryStage.setTitle("Chart with Input List");

        // Создание и инициализация модели данных и ViewModel
        // Модель данных, ViewModel и View
        // Модель данных
        ChartDataModel dataModel = new ChartDataModel();
        // ViewModel
        ChartViewModel viewModel = new ChartViewModel(dataModel);
        // Представление
        ChartView chartView = new ChartView(viewModel);
        // Создание графика
        chartView.creatingChart();
        chartView.createDataInputControls();
        chartView.createFileInputControls();
        chartView.createTestControls();
        //Получение данных об элементах ui
        BorderPane borderPane = chartView.interfaceLayout();

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
