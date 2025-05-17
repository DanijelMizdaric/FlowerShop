package com.example.flowershop;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ScrollView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Map;

public class HomeScreen extends AppCompatActivity {

    Button backbtn;
    Button cartBtn1, cartBtn2, cartBtn3, cartBtn4, cartBtn5, cartBtn6;

    Button addBtn1, addBtn2, addBtn3, addBtn4, addBtn5, addBtn6;
    Button removeBtn1, removeBtn2, removeBtn3, removeBtn4, removeBtn5, removeBtn6;
    Button goCart, goOrders;
    EditText searchBar;
    ScrollView scrollview;
    int a=1;
    CartManager cartManager; // Declare CartManager

    @SuppressLint({"MissingInflatedId", "CutPasteId", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        // Initialize the CartManager with the current context
        cartManager = new CartManager(this);

        backbtn = findViewById(R.id.backBtnID);
        cartBtn1 = findViewById(R.id.btnCart3ID);
        cartBtn2 = findViewById(R.id.btnCartID);
        cartBtn3 = findViewById(R.id.btnCart5ID);
        cartBtn4 = findViewById(R.id.btnCart6ID);
        cartBtn5 = findViewById(R.id.btnCart7ID);
        cartBtn6 = findViewById(R.id.btnCart8ID);
        addBtn1 = findViewById(R.id.addQuantity3ID);
        addBtn2 = findViewById(R.id.addQuantityID);
        addBtn3 = findViewById(R.id.addQuantity5ID);
        addBtn4 = findViewById(R.id.addQuantity6ID);
        addBtn5 = findViewById(R.id.addQuantity7ID);
        addBtn6 = findViewById(R.id.addQuantity8ID);
        removeBtn1 = findViewById(R.id.subtractQuantity2ID);
        removeBtn2 = findViewById(R.id.subtractQuantity4ID);
        removeBtn3 = findViewById(R.id.subtractQuantity5ID);
        removeBtn4 = findViewById(R.id.subtractQuantity6ID);
        removeBtn5 = findViewById(R.id.subtractQuantity7ID);
        removeBtn6 = findViewById(R.id.subtractQuantity8ID);
        searchBar = findViewById(R.id.searchbarID);
        scrollview = findViewById(R.id.scrollView);
        goCart = findViewById(R.id.gotoCartID);
        goOrders = findViewById(R.id.checkOrderID);

        Map<String, Integer> flowerMap = new HashMap<>();
        flowerMap.put("rose", R.id.textView3);
        flowerMap.put("lily", R.id.textView4);
        flowerMap.put("tulip", R.id.textView5);
        flowerMap.put("peony", R.id.textView6);
        flowerMap.put("daisy", R.id.textView7);
        flowerMap.put("iris", R.id.textView8);
        String Username = getIntent().getStringExtra("username");

        // Navigate to MainActivity when back button is clicked
        backbtn.setOnClickListener(v -> {
            Intent intent = new Intent(HomeScreen.this, MainActivity.class);
            startActivity(intent);
            finish();
        });

        searchBar.setOnEditorActionListener((v, actionId, event) -> {
            String search = searchBar.getText().toString().trim().toLowerCase();

            if (flowerMap.containsKey(search)) {
                int viewId = flowerMap.get(search);
                TextView flowerLabel = findViewById(viewId);

                // Scroll to the flower
                scrollview.post(() -> {
                    scrollview.scrollTo(0, flowerLabel.getTop());

                    // Optional: Highlight the text view briefly
                    flowerLabel.setBackgroundColor(Color.YELLOW);
                    new Handler().postDelayed(() -> {
                        flowerLabel.setBackgroundColor(Color.TRANSPARENT);
                    }, 5000);
                });

            } else {
                Toast.makeText(this, "Flower not found", Toast.LENGTH_SHORT).show();
            }

            return true;
        });

        addBtn1.setOnClickListener(v->{
           addQuantity();
        });
        addBtn2.setOnClickListener(v->{
            addQuantity();
        });
        addBtn3.setOnClickListener(v->{
            addQuantity();
        });
        addBtn4.setOnClickListener(v->{
            addQuantity();
        });
        addBtn5.setOnClickListener(v->{
            addQuantity();
        });
        addBtn6.setOnClickListener(v->{
            addQuantity();
        });
        removeBtn1.setOnClickListener(v->{
            removeQuantity();
        });
        removeBtn2.setOnClickListener(v->{
            removeQuantity();
        });
        removeBtn3.setOnClickListener(v->{
            removeQuantity();
        });
        removeBtn4.setOnClickListener(v->{
            removeQuantity();
        });
        removeBtn5.setOnClickListener(v->{
            removeQuantity();
        });
        removeBtn6.setOnClickListener(v->{
            removeQuantity();
        });

        // Add "Rose" to cart when cartBtn1 is clicked
        cartBtn1.setOnClickListener(v -> {
            FlowerRoom rose = new FlowerRoom("Rose", a, Username); // Assuming FlowerRoom constructor (name, quantity)
            cartManager.addToCart(rose);  // Add "Rose" to the cart
            Toast.makeText(HomeScreen.this, "Rose added to cart", Toast.LENGTH_SHORT).show();
            a=1;
        });

        // Add "Lily" to cart when cartBtn2 is clicked
        cartBtn2.setOnClickListener(v -> {
            FlowerRoom lily = new FlowerRoom("Lily", a, Username); // Assuming FlowerRoom constructor (name, quantity)
            cartManager.addToCart(lily);  // Add "Lily" to the cart
            Toast.makeText(HomeScreen.this, "Lily added to cart", Toast.LENGTH_SHORT).show();
            a=1;
        });
        cartBtn3.setOnClickListener(v -> {
            FlowerRoom tulip = new FlowerRoom("Tulip", a, Username); // Assuming FlowerRoom constructor (name, quantity)
            cartManager.addToCart(tulip);  // Add "Lily" to the cart
            Toast.makeText(HomeScreen.this, "Tulip added to cart", Toast.LENGTH_SHORT).show();
            a=1;
        });
        cartBtn4.setOnClickListener(v -> {
            FlowerRoom peony = new FlowerRoom("Peony", a, Username); // Assuming FlowerRoom constructor (name, quantity)
            cartManager.addToCart(peony);  // Add "Lily" to the cart
            Toast.makeText(HomeScreen.this, "Peony added to cart", Toast.LENGTH_SHORT).show();
            a=1;
        });
        cartBtn5.setOnClickListener(v -> {
            FlowerRoom daisy = new FlowerRoom("Daisy", a, Username); // Assuming FlowerRoom constructor (name, quantity)
            cartManager.addToCart(daisy);  // Add "Lily" to the cart
            Toast.makeText(HomeScreen.this, "Daisy added to cart", Toast.LENGTH_SHORT).show();
            a=1;
        });
        cartBtn6.setOnClickListener(v -> {
            FlowerRoom iris = new FlowerRoom("Iris", a, Username); // Assuming FlowerRoom constructor (name, quantity)
            cartManager.addToCart(iris);  // Add "Lily" to the cart
            Toast.makeText(HomeScreen.this, "Iris added to cart", Toast.LENGTH_SHORT).show();
            a=1;
        });

        // Navigate to CartView activity when goCart button is clicked
        goCart.setOnClickListener(v -> {
            Intent intent = new Intent(HomeScreen.this, CartView.class);
            intent.putExtra("username", Username);
            startActivity(intent);
            finish();
        });
        goOrders.setOnClickListener(v->{
            Intent intent = new Intent(HomeScreen.this, OrdersView.class);
            intent.putExtra("username", Username);
            startActivity(intent);
            finish();
        });
    }
    public void addQuantity(){
        a++;
        Toast.makeText(HomeScreen.this, "Added quantity",Toast.LENGTH_SHORT).show();

    }
    public void removeQuantity(){
        if(a>1){
            a--;
            Toast.makeText(HomeScreen.this, "Reduced quantity",Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(HomeScreen.this, "Cannot reduce quantity", Toast.LENGTH_SHORT).show();
        }
    }
}
