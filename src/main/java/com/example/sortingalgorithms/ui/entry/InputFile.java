package com.example.sortingalgorithms.ui.entry;

import java.io.*;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class InputFile {
    // Перечисление для статусов файла
    public enum FileStatus {
        OK,        // Файл существует, не пустой и можно читать
        NOT_EXIST,  // Файл не существует
        IS_DIRECTORY,  // Файл - директория
        CANNOT_READ,   // Файл нельзя прочитать
        IS_EMPTY    // Файл пустой
    }

    // Метод для валидации файла и возврата его статуса
    public static FileStatus validateFile(String fileName) {
        File file = new File(fileName);

        if (!file.exists()) {
            return FileStatus.NOT_EXIST;
        }

        if (file.isDirectory()) {
            return FileStatus.IS_DIRECTORY;
        }

        if (!file.canRead()) {
            return FileStatus.CANNOT_READ;
        }

        if (file.length() == 0) {
            return FileStatus.IS_EMPTY;
        }

        return FileStatus.OK;
    }

    // Метод для загрузки данных из файла
    public static List<Double> loadNumericDataFromFile(String fileName) {
        FileStatus status = validateFile(fileName);
        List<Double> data = new ArrayList<>();

        if (status != FileStatus.OK) {
            // Вывести сообщение об ошибке в зависимости от статуса
            System.err.println("Error: " + status);
            return data;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    double value = Double.parseDouble(line);
                    data.add(value);
                } catch (NumberFormatException e) {
                    //выводить Alert Dialog
                    System.err.println("Error parsing data: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    // Метод для загрузки строк из файла
    public static List<String> loadStringsFromFile(String fileName) {
        FileStatus status = validateFile(fileName);
        List<String> data = new ArrayList<>();

        if (status != FileStatus.OK) {
            // Вывести сообщение об ошибке в зависимости от статуса
            System.err.println("Error: " + status);
            return data;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                data.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    // Метод для загрузки целочисленных чисел из файла
    public static List<Integer> loadIntegersFromFile(String fileName) {
        FileStatus status = validateFile(fileName);
        List<Integer> data = new ArrayList<>();
        if (status != FileStatus.OK) {
            // Вывести сообщение об ошибке в зависимости от статуса
            System.err.println("Error: " + status);
            return data;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    int value = Integer.parseInt(line);
                    data.add(value);
                } catch (NumberFormatException e) {
                    System.err.println("Error parsing integer: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

}
