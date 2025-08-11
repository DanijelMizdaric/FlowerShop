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
        // Use application context to prevent memory leaks
        FlowerDB db = FlowerDB.getDatabase(context.getApplicationContext());
        flowerDao = db.flowerDao();
        cartFlowers = new MutableLiveData<>();
        executorService = Executors.newSingleThreadExecutor();
    }

    // Load cart flowers for specific user
    public void loadCartFlowers(String username) {
        executorService.execute(() -> {
            List<FlowerRoom> flowers = flowerDao.getCartForUser(username);
            cartFlowers.postValue(flowers);
        });
    }

    // Add to cart with automatic user association
    public void addToCart(FlowerRoom flower) {
        executorService.execute(() -> {
            flowerDao.insert(flower);
            loadCartFlowers(flower.getUsername()); // Refresh for the specific user
        });
    }

    // Remove from cart with user awareness
    public void removeFromCart(FlowerRoom flower) {
        executorService.execute(() -> {
            flowerDao.delete(flower);
            loadCartFlowers(flower.getUsername()); // Refresh for the specific user
        });
    }

    // Get LiveData for UI observation
    public LiveData<List<FlowerRoom>> getCartFlowers() {
        return cartFlowers;
    }

    // Synchronous getter (use with caution - prefer LiveData)
    public List<FlowerRoom> getCartForUser(String username) {
        return flowerDao.getCartForUser(username);
    }

    // Clear entire cart for a user
    public void clearCartForUser(String username) {
        executorService.execute(() -> {
            flowerDao.deleteCartForUser(username);
            cartFlowers.postValue(null); // Clear the LiveData
        });
    }

    // Update quantity for an item
    public void updateQuantity(FlowerRoom flower) {
        executorService.execute(() -> {
            flowerDao.update(flower);
            loadCartFlowers(flower.getUsername());
        });
    }

    // Helper method to get total price
    public LiveData<Double> getCartTotal(String username) {
        MutableLiveData<Double> total = new MutableLiveData<>();
        executorService.execute(() -> {
            double sum = 0;
            List<FlowerRoom> items = flowerDao.getCartForUser(username);
            for (FlowerRoom item : items) {
                sum += item.getPrice() * item.getQuantity();
            }
            total.postValue(sum);
        });
        return total;
    }
}