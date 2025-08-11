package com.example.flowershop;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

public class FactManager {
    private final FlowerFactDAO factDao;
    private final SharedPreferences prefs;
    private final ExecutorService executor;

    public FactManager(Context context) {
        factDao = FlowerDB.getDatabase(context).flowerFactDAO();
        prefs = context.getSharedPreferences("fact_prefs", Context.MODE_PRIVATE);
        executor = Executors.newSingleThreadExecutor();
    }

    public void getDailyFact(Consumer<FlowerFact> callback) {
        executor.execute(() -> {
            int count = factDao.getCount();
            if (count == 0) return;

            long lastTime = prefs.getLong("lastFactTime", 0);
            int currentFactId = prefs.getInt("currentFactId", 1);

            long now = System.currentTimeMillis();
            if (now - lastTime > 86400000) {
                currentFactId = (currentFactId % count) + 1;
                prefs.edit()
                        .putLong("lastFactTime", now)
                        .putInt("currentFactId", currentFactId)
                        .apply();
            }

            FlowerFact fact = factDao.getFactById(currentFactId);
            callback.accept(fact);
        });
    }
}
