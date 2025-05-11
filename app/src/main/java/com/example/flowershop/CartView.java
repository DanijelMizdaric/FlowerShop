package com.example.flowershop;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import java.util.ArrayList;
import java.util.List;

public class CartView extends AppCompatActivity {

    private ListView cartListView;
    private Button checkoutButton;
    private Button backbtn;
    private ArrayAdapter<String> adapter;
    private List<FlowerRoom> flowerList = new ArrayList<>();
    private List<String> displayList = new ArrayList<>();

    private CartManager cartManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_view);

        cartManager = new CartManager(this);

        cartListView = findViewById(R.id.cartViewID);
        checkoutButton = findViewById(R.id.checkoutBtnID);
        backbtn = findViewById(R.id.backBtn);

        backbtn.setOnClickListener(v -> {
            Intent intent = new Intent(CartView.this, HomeScreen.class);
            startActivity(intent);
            finish();
        });

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, displayList);
        cartListView.setAdapter(adapter);

        // Observe cart items from CartManager
        cartManager.getCartFlowers().observe(this, new Observer<List<FlowerRoom>>() {
            @Override
            public void onChanged(List<FlowerRoom> flowers) {
                flowerList.clear();
                displayList.clear();
                flowerList.addAll(flowers);

                for (FlowerRoom flower : flowers) {
                    displayList.add(flower.getName());  // Or .getTitle() depending on your model
                }

                adapter.notifyDataSetChanged();
            }
        });

        // Remove item on long click
        cartListView.setOnItemLongClickListener((parent, view, position, id) -> {
            FlowerRoom flower = flowerList.get(position);
            cartManager.removeFromCart(flower);
            Toast.makeText(this, flower.getName() + " removed from cart", Toast.LENGTH_SHORT).show();
            return true;
        });

        checkoutButton.setOnClickListener(v -> {
            List<FlowerRoom> cartItems = cartManager.getCartFlowers().getValue();
            if(cartItems!=null && !cartItems.isEmpty()) {
                for (FlowerRoom flower : cartItems) {
                    cartManager.checkoutFlower(flower);
                    cartManager.removeFromCart(flower);
                }
                Toast.makeText(this, "Pur" +
                        "chase completed!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(CartView.this, HomeScreen.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(this, "Cart is empty!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
