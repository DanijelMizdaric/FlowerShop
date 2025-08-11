package com.example.flowershop;

import android.content.Context;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CartManager {

    private final FlowerDAO flowerDao;
    private final MutableLiveData<List<FlowerRoom>> cartFlowers;
    private final ExecutorService executorService;

    public CartManager(Context context) {

        FlowerDB db = FlowerDB.getDatabase(context.getApplicationContext());
        flowerDao = db.flowerDao();
        cartFlowers = new MutableLiveData<>();
        executorService = Executors.newSingleThreadExecutor();
    }

    public void loadCartFlowers(String username) {
        executorService.execute(() -> {
            List<FlowerRoom> flowers = flowerDao.getCartForUser(username);
            cartFlowers.postValue(flowers);
        });
    }

    public void addToCart(FlowerRoom flower) {
        executorService.execute(() -> {
            flowerDao.insert(flower);
            loadCartFlowers(flower.getUsername());
        });
    }

    public void removeFromCart(FlowerRoom flower) {
        executorService.execute(() -> {
            flowerDao.delete(flower);
            loadCartFlowers(flower.getUsername());
        });
    }

    public LiveData<List<FlowerRoom>> getCartFlowers() {
        return cartFlowers;
    }

}