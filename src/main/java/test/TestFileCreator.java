package test;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class TestFileCreator {
    public void creatingTestFile(String fileName, int size, boolean isSorted) {
        try (FileWriter writer = new FileWriter(fileName)) {
            Random random = new Random();
            List<Double> dataList = new ArrayList<>();

            // Заполняем список случайными значениями
            for (int i = 0; i < size; i++) {
                dataList.add(random.nextDouble() * 1000); // Просто для примера, можно изменить границы
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
        for (int i = 1; i <= 5; i++) {
            String fileName = i + ".txt";
            try {
                Files.deleteIfExists(Paths.get(fileName));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
