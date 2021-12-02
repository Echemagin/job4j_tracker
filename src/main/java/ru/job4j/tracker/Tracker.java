package ru.job4j.tracker;

import java.util.Arrays;

public class Tracker {
    private final Item[] items = new Item[100];
    private int ids = 1;
    private int size = 0;

    public Item add(Item item) {
        item.setId(ids++);
        items[size++] = item;
        return item;
    }

    public Item[] findAll() {
        Item[] tempItems = new Item[size];
        int counter = 0;
        for (int index = 0; index < size; index++) {
            Item item = items[index];
            if (item != null) {
                tempItems[counter] = item;
                counter++;
            }
        }
        tempItems = Arrays.copyOf(tempItems, counter);
        return tempItems;
    }

    public Item[] findByName(String key) {
        Item[] tempItems = new Item[size];
        int counter = 0;
        for (int index = 0; index < size; index++) {
            Item item = items[index];
            if (item.getName().equals(key)) {
                tempItems[counter] = item;
                counter++;
            }
        }
        tempItems = Arrays.copyOf(tempItems, counter);
        return tempItems;
    }

    public Item findById(int id) {
        Item rsl = null;
        for (int index = 0; index < size; index++) {
            Item item = items[index];
            if (item.getId() == id) {
                rsl = item;
                break;
            }
        }
        return rsl;
    }
}