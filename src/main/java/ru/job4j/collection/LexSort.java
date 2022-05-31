package ru.job4j.collection;

import java.util.Comparator;

public class LexSort implements Comparator<String> {
    @Override
    public int compare(String left, String right) {
        return Integer.compare(getNumber(left), getNumber(right));
    }

    private int getNumber(String taskString) {
        String[] array = taskString.split("\\.");
        return Integer.parseInt(array[0]);
    }
}
