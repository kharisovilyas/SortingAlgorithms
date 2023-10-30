package com.example.sortingalgorithms.ui.entry;

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

    // Валидатор для проверки, что строка не является пустой
    public boolean isNotEmpty(String str) {
        return !str.isEmpty();
    }
}