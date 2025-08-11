package com.example.flowershop;

import java.util.Arrays;
import java.util.List;

public class InitializeDatabase {
    public static void populateDatabase(FlowerDAO flowerDao) {
        List<Flowers> all = Arrays.asList(
                new Flowers("Rose", 6.99),
                new Flowers("Lily", 4.99),
                new Flowers("Tulip", 7.99),
                new Flowers("Daisy", 3.99),
                new Flowers("Iris", 5.99),
                new Flowers("Peony", 5.99),
                new Flowers("Forget me not",5.99),
                new Flowers("Orchid",6.99),
                new Flowers("Magnolia",5.99),
                new Flowers("Bluebell",6.99),
                new Flowers("Amaryllis",8.99),
                new Flowers("Camellia",4.99),
                new Flowers("Dahlia",6.99),
                new Flowers("Zinnia",7.99),
                new Flowers("Aster",7.99),
                new Flowers("Gladiolus",2.99),
                new Flowers("Marigold",4.99),
                new Flowers("Freesia",3.99),
                new Flowers("Phlox",4.99),
                new Flowers("Poppy",3.99),

                new Flowers("Rose (Bouquet)", 12.99),
                new Flowers("Lily (Bouquet)", 10.99),
                new Flowers("Tulip (Bouquet)", 15.99),
                new Flowers("Daisy (Bouquet)", 10.99),
                new Flowers("Iris (Bouquet)", 11.99),
                new Flowers("Peony (Bouquet)", 11.99),
                new Flowers("Forget me not (Bouquet)",11.99),
                new Flowers("Orchid (Bouquet)",12.99),
                new Flowers("Magnolia (Bouquet)",11.99),
                new Flowers("Bluebell (Bouquet)",12.99),
                new Flowers("Amaryllis (Bouquet)",18.99),
                new Flowers("Camellia (Bouquet)",10.99),
                new Flowers("Dahlia (Bouquet)",12.99),
                new Flowers("Zinnia (Bouquet)",15.99),
                new Flowers("Aster (Bouquet)",15.99),
                new Flowers("Gladiolus (Bouquet)",9.99),
                new Flowers("Marigold (Bouquet)",10.99),
                new Flowers("Freesia (Bouquet)",10.99),
                new Flowers("Phlox (Bouquet)",10.99),
                new Flowers("Poppy (Bouquet)",10.99)
        );

        try {
            flowerDao.deleteAll();
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
                new FlowerFact("Moon flowers bloom only at night, closing during the day."),
                new FlowerFact("During the Middle Ages, lady’s mantle was thought to have magic healing properties."),
                new FlowerFact("Ancient civilizations burned aster leaves to ward off evil spirits."),
                new FlowerFact("Broccoli is actually a flower."),
                new FlowerFact("Surprisingly, some plants are actually used to make pesticides."),
                new FlowerFact("Sunflowers absorb radioactive elements."),
                new FlowerFact("Delphiniums are named after dolphins."),
                new FlowerFact("Petunias used to be lanky with small flowers that were either white and purple."),
                new FlowerFact("Marigolds were used to treat hiccups."),
                new FlowerFact("Zinnias used to be considered hard on the eyes."),
                new FlowerFact("Lilies are toxic to cats")
        );

        new Thread(() -> {
            flowerFactDAO.deleteAll();
            for (FlowerFact fact : defaultFacts) {
                flowerFactDAO.insert(fact);
            }
        }).start();
    }
}

