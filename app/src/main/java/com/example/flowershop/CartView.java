package com.example.flowershop;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CartView extends AppCompatActivity {

    private ListView cartListView;
    private Button checkoutButton, backbtn;

    private ArrayAdapter<String> adapter;
    private List<FlowerRoom> flowerList = new ArrayList<>();
    private List<String> displayList = new ArrayList<>();

    private CartManager cartManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_view);

        cartManager = new CartManager(this);
        String Username = getIntent().getStringExtra("username");
        cartListView = findViewById(R.id.cartViewID);
        checkoutButton = findViewById(R.id.checkoutBtnID);
        backbtn = findViewById(R.id.backBtn);
        String username = getIntent().getStringExtra("username");
        backbtn.setOnClickListener(v -> {
            Intent intent = new Intent(CartView.this, HomeScreen.class);
            intent.putExtra("username", Username);
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
                    String itemDisplay = String.format("%s - $%.2f (Qty: %d)",
                            flower.getName(),
                            flower.getPrice(),
                            flower.getQuantity());
                    displayList.add(itemDisplay);
                }

                adapter.notifyDataSetChanged();
            }
        });

        // Remove item on long click
        cartManager.getCartFlowers().observe(this, flowers -> {
            CartAdapter adapter = new CartAdapter(this, flowers, cartManager);
            cartListView.setAdapter(adapter);
        });

        checkoutButton.setOnClickListener(v -> {
            List<FlowerRoom> cartItems = cartManager.getCartFlowers().getValue();
            if (cartItems != null && !cartItems.isEmpty()) {
                cartManager.checkoutAllFlowers(cartItems); // <- now this method is actually used
                Intent intent = new Intent(CartView.this, HomeScreen.class);
                intent.putExtra("username", username);
                startActivity(intent);
                finish();
                Toast.makeText(this, "Purchase completed!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Cart is empty!", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
