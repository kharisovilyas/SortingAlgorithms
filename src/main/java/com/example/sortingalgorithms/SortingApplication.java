package com.example.sortingalgorithms;

import com.example.sortingalgorithms.model.ChartDataModel;
import com.example.sortingalgorithms.ui.view.ChartView;
import com.example.sortingalgorithms.ui.viewmodel.ChartViewModel;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

// Основной класс приложения, расширяющий Application
public class SortingApplication extends Application {
    private static final double CONST_HEIGHT_OF_SCENE = 1.5;

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
        // Модель данных
        ChartDataModel dataModel = new ChartDataModel();
        // ViewModel
        ChartViewModel viewModel = new ChartViewModel(dataModel);
        // Представление
        ChartView chartView = new ChartView(viewModel);

        // Получение данных об элементах UI
        BorderPane borderPane = chartView.interfaceLayout();

        // Получение информации о экране
        Screen primaryScreen = Screen.getPrimary();
        double screenWidth = primaryScreen.getBounds().getWidth();
        double screenHeight = primaryScreen.getBounds().getHeight();
        double borderAndTitleBarHeight = screenHeight - primaryScreen.getVisualBounds().getHeight();

        // Создание сцены с учетом размеров экрана
        Scene scene = new Scene(borderPane, screenWidth, screenHeight - borderAndTitleBarHeight * CONST_HEIGHT_OF_SCENE);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
