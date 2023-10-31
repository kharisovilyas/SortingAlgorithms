package com.example.sortingalgorithms.entry;

public class Validators {
    // Валидатор для проверки, что строка является числом
    public boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean validateSizeInput(String countText) {
        try {
            int count = Integer.parseInt(countText);
            return count > 0;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    public boolean validateIntegerInput(String countText) {
        try {
            int count = Integer.parseInt(countText);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    // Валидатор для проверки, что строка не является пустой
    public boolean isNotEmpty(String str) {
        return !str.isEmpty();
    }
}