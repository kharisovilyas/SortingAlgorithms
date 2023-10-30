module com.example.sortingalgorithms {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.junit.jupiter.api;
    requires junit;
    exports test;
    exports com.example.sortingalgorithms.sorting;
    opens com.example.sortingalgorithms to javafx.fxml;
    exports com.example.sortingalgorithms;
}