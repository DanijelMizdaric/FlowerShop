package com.example.flowershop;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class HomeScreen extends AppCompatActivity {

    Button backbtn;
    Button cartBtn1;
    Button cartBtn2;
    Button goCart;
    CartManager cartManager; // Declare CartManager

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        // Initialize the CartManager with the current context
        cartManager = new CartManager(this);

        backbtn = findViewById(R.id.backBtnID);
        cartBtn1 = findViewById(R.id.btnCart1ID);
        cartBtn2 = findViewById(R.id.btnCart2ID);
        goCart = findViewById(R.id.gotoCartID);

        // Navigate to MainActivity when back button is clicked
        backbtn.setOnClickListener(v -> {
            Intent intent = new Intent(HomeScreen.this, MainActivity.class);
            startActivity(intent);
            finish();
        });

        // Add "Rose" to cart when cartBtn1 is clicked
        cartBtn1.setOnClickListener(v -> {
            FlowerRoom rose = new FlowerRoom("Rose", 1); // Assuming FlowerRoom constructor (name, quantity)
            cartManager.addToCart(rose);  // Add "Rose" to the cart
            Toast.makeText(HomeScreen.this, "Rose added to cart", Toast.LENGTH_SHORT).show();
        });

        // Add "Lily" to cart when cartBtn2 is clicked
        cartBtn2.setOnClickListener(v -> {
            FlowerRoom lily = new FlowerRoom("Lily", 1); // Assuming FlowerRoom constructor (name, quantity)
            cartManager.addToCart(lily);  // Add "Lily" to the cart
            Toast.makeText(HomeScreen.this, "Lily added to cart", Toast.LENGTH_SHORT).show();
        });

        // Navigate to CartView activity when goCart button is clicked
        goCart.setOnClickListener(v -> {
            Intent intent = new Intent(HomeScreen.this, CartView.class);
            startActivity(intent);
            finish();
        });
    }
}
