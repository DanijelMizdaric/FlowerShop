package com.example.flowershop;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ScrollView;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.card.MaterialCardView;

import java.util.HashMap;
import java.util.Map;

public class HomeScreen extends AppCompatActivity {

    Button backbtn;
    Button cartBtn1, cartBtn2, cartBtn3, cartBtn4, cartBtn5, cartBtn6;
    ImageButton info1, info2, info3, info4, info5, info6;
    Button addBtn1, addBtn2, addBtn3, addBtn4, addBtn5, addBtn6;
    Button removeBtn1, removeBtn2, removeBtn3, removeBtn4, removeBtn5, removeBtn6;
    Button goCart, goOrders;
    EditText searchBar;
    ScrollView scrollview;
    int a=1;
    CartManager cartManager;
    private FlowerDAO flowerDao;
    private Map<String, Integer> flowerMap = new HashMap<>();
    private Map<String, String> flowerNameMap = new HashMap<>();
    @SuppressLint({"MissingInflatedId", "CutPasteId", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        flowerDao= FlowerDB.getDatabase(this).flowerDao();

        cartManager = new CartManager(this);

        backbtn = findViewById(R.id.backBtnID);
        cartBtn1 = findViewById(R.id.addToCartButton1);
        cartBtn2 = findViewById(R.id.addToCartButton2);
        cartBtn3 = findViewById(R.id.addToCartButton3);
        cartBtn4 = findViewById(R.id.addToCartButton4);
        cartBtn5 = findViewById(R.id.addToCartButton5);
        cartBtn6 = findViewById(R.id.addToCartButton6);
        addBtn1 = findViewById(R.id.plusButton1);
        addBtn2 = findViewById(R.id.plusButton2);
        addBtn3 = findViewById(R.id.plusButton3);
        addBtn4 = findViewById(R.id.plusButton4);
        addBtn5 = findViewById(R.id.plusButton5);
        addBtn6 = findViewById(R.id.plusButton6);
        removeBtn1 = findViewById(R.id.minusButton1);
        removeBtn2 = findViewById(R.id.minusButton2);
        removeBtn3 = findViewById(R.id.minusButton3);
        removeBtn4 = findViewById(R.id.minusButton4);
        removeBtn5 = findViewById(R.id.minusButton5);
        removeBtn6 = findViewById(R.id.minusButton6);
        info1 = findViewById(R.id.infoButton1);
        info2 = findViewById(R.id.infoButton2);
        info3 = findViewById(R.id.infoButton3);
        info4 = findViewById(R.id.infoButton4);
        info5 = findViewById(R.id.infoButton5);
        info6 = findViewById(R.id.infoButton6);
        searchBar = findViewById(R.id.searchBar);
        scrollview = findViewById(R.id.scrollView);
        goCart = findViewById(R.id.viewCartButton);
        goOrders = findViewById(R.id.viewOrdersButton);

        flowerMap.put("rose", R.id.flowerItem1);
        flowerMap.put("lily", R.id.flowerItem2);
        flowerMap.put("tulip", R.id.flowerItem3);
        flowerMap.put("daisy", R.id.flowerItem4);
        flowerMap.put("iris", R.id.flowerItem5);
        flowerMap.put("peony", R.id.flowerItem6);

        flowerNameMap.put("rose", "Rose");
        flowerNameMap.put("lily", "Lily");
        flowerNameMap.put("tulip", "Tulip");
        flowerNameMap.put("daisy", "Daisy");
        flowerNameMap.put("iris", "Iris");
        flowerNameMap.put("peony", "Peony");

        String Username = getIntent().getStringExtra("username");

        SharedPreferences prefs = getSharedPreferences("app_prefs", MODE_PRIVATE);

        if (!prefs.getBoolean("db_initialized", false)) {
            new Thread(() -> {
                if (flowerDao.getFlowerCount() == 0) {
                    InitializeDatabase.populateDatabase(flowerDao);


                    runOnUiThread(() -> {
                        prefs.edit().putBoolean("db_initialized", true).apply();
                    });
                }
            }).start();
        }


        backbtn.setOnClickListener(v -> {
            Intent intent = new Intent(HomeScreen.this, HomePage.class);
            intent.putExtra("username", Username);
            startActivity(intent);
            finish();
        });

        searchBar.setOnEditorActionListener((v, actionId, event) -> {
            String search = searchBar.getText().toString().trim().toLowerCase();

            if (flowerMap.containsKey(search)) {
                int cardViewId = flowerMap.get(search);
                MaterialCardView cardView = findViewById(cardViewId);

                scrollview.post(() -> {
                    int scrollToY = cardView.getTop() - scrollview.getPaddingTop();
                    scrollview.smoothScrollTo(0, scrollToY);

                    cardView.setCardBackgroundColor(Color.YELLOW);
                    new Handler().postDelayed(() -> {
                        cardView.setCardBackgroundColor(Color.WHITE);
                    }, 2000);
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
            new Thread(() -> {
                double price = flowerDao.getFlowerPrice("Rose");
                FlowerRoom rose = new FlowerRoom("Rose", a, Username, price);
                cartManager.addToCart(rose);
                runOnUiThread(() -> {
                    Toast.makeText(HomeScreen.this,
                            "Rose (Qty: " + a + ", $" + price + ") added to cart",
                            Toast.LENGTH_SHORT).show();
                    a = 1;
                });
            }).start();
        });

        // Add "Lily" to cart when cartBtn2 is clicked
        cartBtn2.setOnClickListener(v -> {
            new Thread(() -> {
                double price = flowerDao.getFlowerPrice("Lily");
                FlowerRoom lily = new FlowerRoom("Lily", a, Username, price);
                cartManager.addToCart(lily);
                runOnUiThread(() -> {
                    Toast.makeText(HomeScreen.this,
                            "Lily (Qty: " + a + ", $" + price + ") added to cart",
                            Toast.LENGTH_SHORT).show();
                    a = 1;
                });
            }).start();
        });

        cartBtn3.setOnClickListener(v -> {
            new Thread(() -> {
                double price = flowerDao.getFlowerPrice("Tulip");
                FlowerRoom tulip = new FlowerRoom("Tulip", a, Username, price);
                cartManager.addToCart(tulip);
                runOnUiThread(() -> {
                    Toast.makeText(HomeScreen.this,
                            "Tulip (Qty: " + a + ", $" + price + ") added to cart",
                            Toast.LENGTH_SHORT).show();
                    a = 1;
                });
            }).start();
        });

        cartBtn4.setOnClickListener(v -> {
            new Thread(() -> {
                double price = flowerDao.getFlowerPrice("Daisy");
                FlowerRoom daisy = new FlowerRoom("Daisy", a, Username, price);
                cartManager.addToCart(daisy);
                runOnUiThread(() -> {
                    Toast.makeText(HomeScreen.this,
                            "Daisy (Qty: " + a + ", $" + price + ") added to cart",
                            Toast.LENGTH_SHORT).show();
                    a = 1;
                });
            }).start();
        });

        cartBtn5.setOnClickListener(v -> {
            new Thread(() -> {
                double price = flowerDao.getFlowerPrice("Iris");
                FlowerRoom iris = new FlowerRoom("Iris", a, Username, price);
                cartManager.addToCart(iris);
                runOnUiThread(() -> {
                    Toast.makeText(HomeScreen.this,
                            "Iris (Qty: " + a + ", $" + price + ") added to cart",
                            Toast.LENGTH_SHORT).show();
                    a = 1;
                });
            }).start();
        });

        cartBtn6.setOnClickListener(v -> {
            new Thread(() -> {
                double price = flowerDao.getFlowerPrice("Peony");
                FlowerRoom peony = new FlowerRoom("Peony", a, Username, price);
                cartManager.addToCart(peony);
                runOnUiThread(() -> {
                    Toast.makeText(HomeScreen.this,
                            "Peony (Qty: " + a + ", $" + price + ") added to cart",
                            Toast.LENGTH_SHORT).show();
                    a = 1;
                });
            }).start();
        });

        info1.setOnClickListener(v -> {
            Dialog dialog = new Dialog(this);
            dialog.setContentView(R.layout.pop_up_dialog);
            dialog.show();
            Window window = dialog.getWindow();
            if (window != null) {
                window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

            }
            // Handle back button click
            ImageButton backButton = dialog.findViewById(R.id.backButton);
            backButton.setOnClickListener(v1 -> dialog.dismiss());
        });

        info2.setOnClickListener(v -> {
            Dialog dialog = new Dialog(this);
            dialog.setContentView(R.layout.pop_up_dialog2);
            dialog.show();
            Window window = dialog.getWindow();
            if (window != null) {
                window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

            }
            // Handle back button click
            ImageButton backButton = dialog.findViewById(R.id.backButton);
            backButton.setOnClickListener(v1 -> dialog.dismiss());
        });
        info3.setOnClickListener(v -> {
            Dialog dialog = new Dialog(this);
            dialog.setContentView(R.layout.pop_up_dialog3);
            dialog.show();
            Window window = dialog.getWindow();
            if (window != null) {
                window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

            }
            // Handle back button click
            ImageButton backButton = dialog.findViewById(R.id.backButton);
            backButton.setOnClickListener(v1 -> dialog.dismiss());
        });

        info4.setOnClickListener(v -> {
            Dialog dialog = new Dialog(this);
            dialog.setContentView(R.layout.pop_up_dialog4);
            dialog.show();
            Window window = dialog.getWindow();
            if (window != null) {
                window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

            }
            // Handle back button click
            ImageButton backButton = dialog.findViewById(R.id.backButton);
            backButton.setOnClickListener(v1 -> dialog.dismiss());
        });

        info5.setOnClickListener(v -> {
            Dialog dialog = new Dialog(this);
            dialog.setContentView(R.layout.pop_up_dialog5);
            dialog.show();
            Window window = dialog.getWindow();
            if (window != null) {
                window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

            }
            // Handle back button click
            ImageButton backButton = dialog.findViewById(R.id.backButton);
            backButton.setOnClickListener(v1 -> dialog.dismiss());
        });

        info6.setOnClickListener(v -> {
            Dialog dialog = new Dialog(this);
            dialog.setContentView(R.layout.pop_up_dialog6);
            dialog.show();
            Window window = dialog.getWindow();
            if (window != null) {
                window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

            }
            // Handle back button click
            ImageButton backButton = dialog.findViewById(R.id.backButton);
            backButton.setOnClickListener(v1 -> dialog.dismiss());
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
            intent.putExtra("caller", "HomeScreen");
            startActivity(intent);

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
