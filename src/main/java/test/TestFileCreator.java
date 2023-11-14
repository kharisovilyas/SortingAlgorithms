package test;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.*;
import java.util.*;

public class TestFileCreator {
    private static final double HEIGHT_BORDER_RANDOM = 1000f;
    private static final int LOW_BORDER = 0;
    private static final int LOW_NUMBER_TEST = 1;
    private static final int NUMBER_OF_TESTS = 5; // Количество тестов

    public void creatingTestFile(String fileName, int size, boolean isSorted) {
        try (FileWriter writer = new FileWriter(fileName)) {
            Random random = new Random();
            List<Double> dataList = new ArrayList<>();

            // Заполняем список случайными значениями
            for (int i = LOW_BORDER; i < size; i++) {
                dataList.add(random.nextDouble() * HEIGHT_BORDER_RANDOM); // Просто для примера, можно изменить границы
            }

            // Сортируем список, если необходимо
            if (isSorted) {
                Collections.sort(dataList);
            }

            // Записываем значения в файл
            for (Double data : dataList) {
                writer.write(String.valueOf(data));
                writer.write(System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteTestFiles() {
        for (int i = LOW_NUMBER_TEST; i <= NUMBER_OF_TESTS; i++) {
            String fileName = i + ".txt";
            try {
                Files.deleteIfExists(Paths.get(fileName));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
