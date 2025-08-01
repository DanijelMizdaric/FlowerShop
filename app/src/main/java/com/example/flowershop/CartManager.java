// CartManager.java (updated to initialize OrderFlowerDao and support updating the order)
package com.example.flowershop;

import android.content.Context;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class CartManager {

    private static CartManager instance;

    private OrderDAO orderDao;
    private FlowerDAO flowerDao;
    private OrderFlowerDao orderFlowerDao;  // added
    private MutableLiveData<List<FlowerRoom>> cartFlowers;
    private ExecutorService executorService;

    // Constructor
    CartManager(Context context) {
        FlowerDB db = FlowerDB.getDatabase(context);
        flowerDao = db.flowerDao();
        orderDao = db.orderDao();
        orderFlowerDao = db.orderFlowerDao();  // initialize OrderFlowerDao

        cartFlowers = new MutableLiveData<>();
        executorService = Executors.newSingleThreadExecutor();  // background tasks
        loadCartFlowers();
    }

    // Load flowers from the database (cart items)
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

    // Checkout all flowers in the cart
    public void checkoutAllFlowers(List<FlowerRoom> flowers) {
        executorService.execute(() -> {
            if (flowers == null || flowers.isEmpty()) return;
            String currentDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());

            // Insert a new OrderRoom with placeholder totals
            OrderRoom order = new OrderRoom(flowers.get(0).getUsername(), currentDate, "Pick up in Store",0,0);
            long insertedOrderId = orderDao.insertOrder(order);
            // Room returns the new primary key (id) for the inserted order:contentReference[oaicite:3]{index=3}

            double totalPrice = 0;
            int totalQuantity = 0;

            // Insert each cart item into order_flower, link by order ID, and sum totals
            for (FlowerRoom flower : flowers) {
                OrderFlower orderFlower = new OrderFlower();
                orderFlower.setOrderID((int) insertedOrderId);  // link to OrderRoom.id
                orderFlower.setName(flower.getName());
                orderFlower.setQuantity(flower.getQuantity());
                orderFlower.setPrice(flower.getPrice());
                orderFlower.setUsername(flower.getUsername());

                totalPrice += flower.getQuantity() * flower.getPrice();
                totalQuantity += flower.getQuantity();

                orderFlowerDao.insert(orderFlower);  // now works because orderFlowerDao is initialized
                flowerDao.delete(flower);            // remove from cart
            }

            // Update the OrderRoom row with correct total quantity and price
            order.id = (int) insertedOrderId;
            orderDao.update(order);  // This requires @Update in OrderDAO:contentReference[oaicite:4]{index=4}

            loadCartFlowers();  // refresh cart (should now be empty)
        });
    }

    // Observe cart items
    public LiveData<List<FlowerRoom>> getCartFlowers() {
        return cartFlowers;
    }
}
