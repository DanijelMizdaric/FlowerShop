package com.example.flowershop;

import java.util.Arrays;
import java.util.List;

public class InitializeDatabase {
    public static void populateDatabase(FlowerDAO flowerDao) {
        List<Flowers> all = Arrays.asList(
                new Flowers("Rose", 12.99),
                new Flowers("Lily", 9.99),
                new Flowers("Tulip", 14.99),
                new Flowers("Daisy", 7.99),
                new Flowers("Iris", 11.99),
                new Flowers("Peony", 15.99),
                new Flowers("Forget me not",9.99),
                new Flowers("Orchid",13.99),
                new Flowers("Magnolia",10.99),
                new Flowers("Bluebell",11.99),
                new Flowers("Amaryllis",15.99),
                new Flowers("Camellia",9.99),
                new Flowers("Dahlia",15.99),
                new Flowers("Zinnia",14.99),
                new Flowers("Aster",14.99),
                new Flowers("Gladiolus",6.99),
                new Flowers("Marigold",9.99),
                new Flowers("Freesia",7.99),
                new Flowers("Phlox",11.99),
                new Flowers("Poppy",7.99),

                new Flowers("Rose (Bouquet)", 32.99),
                new Flowers("Lily (Bouquet)", 29.99),
                new Flowers("Tulip (Bouquet)", 44.99),
                new Flowers("Daisy (Bouquet)", 34.99),
                new Flowers("Iris (Bouquet)", 42.99),
                new Flowers("Peony (Bouquet)", 39.99),
                new Flowers("Forget me not (Bouquet)",29.99),
                new Flowers("Orchid (Bouquet)",35.99),
                new Flowers("Magnolia (Bouquet)",35.99),
                new Flowers("Bluebell (Bouquet)",40.99),
                new Flowers("Amaryllis (Bouquet)",32.99),
                new Flowers("Camellia (Bouquet)",42.99),
                new Flowers("Dahlia (Bouquet)",40.99),
                new Flowers("Zinnia (Bouquet)",38.99),
                new Flowers("Aster (Bouquet)",38.99),
                new Flowers("Gladiolus (Bouquet)",29.99),
                new Flowers("Marigold (Bouquet)",35.99),
                new Flowers("Freesia (Bouquet)",32.99),
                new Flowers("Phlox (Bouquet)",42.99),
                new Flowers("Poppy (Bouquet)",32.99)
        );

        try {
            flowerDao.deleteAll(); // clear once
            for (Flowers f : all) {
                flowerDao.insert(f);
            }
        } catch (Exception e) {
            android.util.Log.e("InitDB", "populateDatabase error", e);
        }
    }

    public static void populateFacts(FlowerFactDAO flowerFactDAO) {
        List<FlowerFact> defaultFacts = Arrays.asList(
                new FlowerFact("Roses are related to apples, raspberries, cherries, peaches, plums, nectarines, pears and almonds."),
                new FlowerFact("Tulip bulbs were more valuable than gold in Holland in the 1600s."),
                new FlowerFact("Tulip bulbs can be substituted for onions in a recipe."),
                new FlowerFact("The juice from bluebell flowers was used historically to make glue."),
                new FlowerFact("Dandelions might seem like weeds, but they are a good source of vitamins A and C, iron, calcium and potassium."),
                new FlowerFact("Moon flowers bloom only at night, closing during the day.")
        );

        new Thread(() -> {
            flowerFactDAO.deleteAll(); // Optional: clear old ones
            for (FlowerFact fact : defaultFacts) {
                flowerFactDAO.insert(fact);
            }
        }).start();
    }
}

