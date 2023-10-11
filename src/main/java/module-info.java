module com.example.sortingalgorithms {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.sortingalgorithms to javafx.fxml;
    exports com.example.sortingalgorithms;
}