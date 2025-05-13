package com.example.flowershop;

import android.annotation.SuppressLint;
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
    Button addBtn1;
    Button removeBtn1;
    Button goCart;
    int a=1;
    CartManager cartManager; // Declare CartManager

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        // Initialize the CartManager with the current context
        cartManager = new CartManager(this);

        backbtn = findViewById(R.id.backBtnID);
        cartBtn1 = findViewById(R.id.btnCart1ID);
        cartBtn2 = findViewById(R.id.btnCart2ID);
        addBtn1 = findViewById(R.id.addQuantityID);
        removeBtn1 = findViewById(R.id.subtractQuantityID);
        goCart = findViewById(R.id.gotoCartID);

        // Navigate to MainActivity when back button is clicked
        backbtn.setOnClickListener(v -> {
            Intent intent = new Intent(HomeScreen.this, MainActivity.class);
            startActivity(intent);
            finish();
        });

        addBtn1.setOnClickListener(v->{
            a++;
            Toast.makeText(HomeScreen.this, "Added quantity",Toast.LENGTH_SHORT).show();
        });
        removeBtn1.setOnClickListener(v->{
            if(a>1){
                a--;
            } else {
                Toast.makeText(HomeScreen.this, "Cannot reduce quantity", Toast.LENGTH_SHORT).show();
            }
        });
        // Add "Rose" to cart when cartBtn1 is clicked
        cartBtn1.setOnClickListener(v -> {
            FlowerRoom rose = new FlowerRoom("Rose", a); // Assuming FlowerRoom constructor (name, quantity)
            cartManager.addToCart(rose);  // Add "Rose" to the cart
            Toast.makeText(HomeScreen.this, "Rose added to cart", Toast.LENGTH_SHORT).show();
            a=1;
        });

        // Add "Lily" to cart when cartBtn2 is clicked
        cartBtn2.setOnClickListener(v -> {
            FlowerRoom lily = new FlowerRoom("Lily", a); // Assuming FlowerRoom constructor (name, quantity)
            cartManager.addToCart(lily);  // Add "Lily" to the cart
            Toast.makeText(HomeScreen.this, "Lily added to cart", Toast.LENGTH_SHORT).show();
            a=1;
        });

        // Navigate to CartView activity when goCart button is clicked
        goCart.setOnClickListener(v -> {
            Intent intent = new Intent(HomeScreen.this, CartView.class);
            startActivity(intent);
            finish();
        });
    }
}
