package com.example.flowershop;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import java.util.List;

public class CartView extends AppCompatActivity {

    private ListView cartListView;
    private Button backbtn;
    private CartManager cartManager;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_view);


        cartManager = new CartManager(getApplicationContext());
        username = getIntent().getStringExtra("username");

        cartListView = findViewById(R.id.cartViewID);
        backbtn = findViewById(R.id.backBtn);

        String caller = getIntent().getStringExtra("caller");
        backbtn.setOnClickListener(v -> {
            Intent intent = new Intent(CartView.this, CheckOut.class);
            intent.putExtra("username", username);
            intent.putExtra("caller", caller);
            startActivity(intent);
            finish();
        });


        cartManager.getCartFlowers().observe(this, new Observer<List<FlowerRoom>>() {
            @Override
            public void onChanged(List<FlowerRoom> flowers) {

                if (flowers != null && !flowers.isEmpty()) {
                    CartAdapter adapter = new CartAdapter(CartView.this, flowers, cartManager);
                    cartListView.setAdapter(adapter);
                } else {
                    Toast.makeText(CartView.this, "Your cart is empty", Toast.LENGTH_SHORT).show();
                }
            }
        });


        cartManager.loadCartFlowers(username);
    }
}