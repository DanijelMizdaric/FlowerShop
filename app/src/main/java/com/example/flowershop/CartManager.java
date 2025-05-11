package com.example.flowershop;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CartManager {

    private static CartManager instance;
    private DAO flowerDao;
    private OrderDAO orderDao;
    private MutableLiveData<List<FlowerRoom>> cartFlowers;
    private ExecutorService executorService;

    // Private constructor to prevent instantiation
    CartManager(Context context) {
        FlowerDB db = FlowerDB.getDatabase(context);
        flowerDao = db.flowerDao();
        orderDao = db.orderDao();
        cartFlowers = new MutableLiveData<>();
        executorService = Executors.newSingleThreadExecutor();  // Create an executor for background tasks
        loadCartFlowers();  // Load cart items
    }

    // Method to get the instance of CartManager


    // Load flowers from the database (cart items)
    private void loadCartFlowers() {
        executorService.execute(() -> {
            List<FlowerRoom> flowers = flowerDao.getAllFlowers();
            cartFlowers.postValue(flowers); // Update LiveData in the main thread
        });
    }

    // Add a flower to the cart
    public void addToCart(FlowerRoom flower) {
        executorService.execute(() -> {
            flowerDao.insert(flower);  // Perform database operation off the main thread
            loadCartFlowers();  // Refresh cart after insertion
        });
    }

    // Remove a flower from the cart
    public void removeFromCart(FlowerRoom flower) {
        executorService.execute(() -> {
            flowerDao.delete(flower);  // Perform database operation off the main thread
            loadCartFlowers();  // Refresh cart after deletion
        });
    }
    
    public void checkoutFlower(FlowerRoom flower) {
        executorService.execute(()->{
            OrderRoom order = new OrderRoom(flower.getName(), flower.getQuantity()); // You may want to check nulls
            orderDao.insertOrder(order);
            flowerDao.delete(flower); // Optional: clear it from the cart after ordering
            loadCartFlowers(); // Refresh cart l
        });
    }

    // Get the current cart flowers (LiveData for observing changes)
    public LiveData<List<FlowerRoom>> getCartFlowers() {
        return cartFlowers;
    }
}
