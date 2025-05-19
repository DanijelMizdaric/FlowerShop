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


    CartManager(Context context) {
        FlowerDB db = FlowerDB.getDatabase(context);
        flowerDao = db.flowerDao();
        orderDao = db.orderDao();
        cartFlowers = new MutableLiveData<>();
        executorService = Executors.newSingleThreadExecutor();
        loadCartFlowers();
    }


    private void loadCartFlowers() {
        executorService.execute(() -> {
            List<FlowerRoom> flowers = flowerDao.getAllFlowers();
            cartFlowers.postValue(flowers);
        });
    }

    // Add a flower to the cart
    public void addToCart(FlowerRoom flower) {
        executorService.execute(() -> {
            flowerDao.insert(flower);
            loadCartFlowers();
        });
    }

    // Remove a flower from the cart
    public void removeFromCart(FlowerRoom flower) {
        executorService.execute(() -> {
            flowerDao.delete(flower);
            loadCartFlowers();
        });
    }

    public void checkoutAllFlowers(List<FlowerRoom> flowers) {
        executorService.execute(() -> {
            Integer lastOrderID = orderDao.getLastOrderID();
            int newOrderID = (lastOrderID == null) ? 1 : lastOrderID + 1;

            for (FlowerRoom flower : flowers) {
                OrderRoom order = new OrderRoom(newOrderID, flower.getName(), flower.getQuantity(), flower.getUsername());
                orderDao.insertOrder(order);
                flowerDao.delete(flower);
            }

            loadCartFlowers();
        });
    }


    public LiveData<List<FlowerRoom>> getCartFlowers() {
        return cartFlowers;
    }
}
