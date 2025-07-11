package com.example.flowershop;

import java.util.Arrays;
import java.util.List;

public class InitializeDatabase {
    public static void populateDatabase(FlowerDAO flowerDao) {
        List<Flowers> defaultFlowers = Arrays.asList(
                new Flowers("Rose", 12.99),
                new Flowers("Lily", 9.99),
                new Flowers("Tulip", 14.99),
                new Flowers("Daisy", 7.99),
                new Flowers("Iris", 11.99),
                new Flowers("Peony", 15.99)
        );

        new Thread(() -> {
            flowerDao.deleteAll(); // Clear existing data
            for (Flowers flower : defaultFlowers) {
                flowerDao.insert(flower);
            }
        }).start();
    }
}
