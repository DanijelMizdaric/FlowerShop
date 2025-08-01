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
import java.util.List;
import java.util.Map;

public class HomeScreen extends AppCompatActivity {
    private static final String PREFS_KEY_FLOWERS_INITIALIZED = "flowers_initialized";

    Button backbtn;
    Button cartBtn1, cartBtn2, cartBtn3, cartBtn4, cartBtn5, cartBtn6, cartBtn7, cartBtn8,cartBtn9,cartBtn10,
            cartBtn11,cartBtn12,cartBtn13,cartBtn14,cartBtn15,cartBtn16,cartBtn17 ,cartBtn18,cartBtn19,cartBtn20;
    ImageButton info1, info2, info3, info4, info5, info6, info7,info8,info9,info10,
    info11,info12,info13,info14,info15,info16,info17,info18,info19,info20;
    Button addBtn1, addBtn2, addBtn3, addBtn4, addBtn5, addBtn6, addBtn7, addBtn8,addBtn9,addBtn10,
            addBtn11,addBtn12,addBtn13,addBtn14,addBtn15,addBtn16,addBtn17,addBtn18,addBtn19,addBtn20;
    Button removeBtn1, removeBtn2, removeBtn3, removeBtn4, removeBtn5, removeBtn6, removeBtn7,removeBtn8,removeBtn9,removeBtn10,
            removeBtn11,removeBtn12,removeBtn13,removeBtn14,removeBtn15,removeBtn16,removeBtn17,removeBtn18,removeBtn19,removeBtn20;
    Button goCart, goOrders;
    EditText searchBar;
    ScrollView scrollview;
    int[] quantities = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
                        1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
    CartManager cartManager;
    private FlowerDAO flowerDao;
    private Map<String, Integer> flowerMap = new HashMap<>();

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
        cartBtn7 = findViewById(R.id.addToCartButton7);
        cartBtn8 = findViewById(R.id.addToCartButton8);
        cartBtn9 = findViewById(R.id.addToCartButton9);
        cartBtn10 = findViewById(R.id.addToCartButton10);
        cartBtn11 = findViewById(R.id.addToCartButton11);
        cartBtn12 = findViewById(R.id.addToCartButton12);
        cartBtn13 = findViewById(R.id.addToCartButton13);
        cartBtn14 = findViewById(R.id.addToCartButton14);
        cartBtn15 = findViewById(R.id.addToCartButton15);
        cartBtn16 = findViewById(R.id.addToCartButton16);
        cartBtn17 = findViewById(R.id.addToCartButton17);
        cartBtn18 = findViewById(R.id.addToCartButton18);
        cartBtn19 = findViewById(R.id.addToCartButton19);
        cartBtn20 = findViewById(R.id.addToCartButton20);
        addBtn1 = findViewById(R.id.plusButton1);
        addBtn2 = findViewById(R.id.plusButton2);
        addBtn3 = findViewById(R.id.plusButton3);
        addBtn4 = findViewById(R.id.plusButton4);
        addBtn5 = findViewById(R.id.plusButton5);
        addBtn6 = findViewById(R.id.plusButton6);
        addBtn7 = findViewById(R.id.plusButton7);
        addBtn8 = findViewById(R.id.plusButton8);
        addBtn9 = findViewById(R.id.plusButton9);
        addBtn10 = findViewById(R.id.plusButton10);
        addBtn11 = findViewById(R.id.plusButton11);
        addBtn12 = findViewById(R.id.plusButton12);
        addBtn13 = findViewById(R.id.plusButton13);
        addBtn14 = findViewById(R.id.plusButton14);
        addBtn15 = findViewById(R.id.plusButton15);
        addBtn16 = findViewById(R.id.plusButton16);
        addBtn17 = findViewById(R.id.plusButton17);
        addBtn18 = findViewById(R.id.plusButton18);
        addBtn19 = findViewById(R.id.plusButton19);
        addBtn20 = findViewById(R.id.plusButton20);
        removeBtn1 = findViewById(R.id.minusButton1);
        removeBtn2 = findViewById(R.id.minusButton2);
        removeBtn3 = findViewById(R.id.minusButton3);
        removeBtn4 = findViewById(R.id.minusButton4);
        removeBtn5 = findViewById(R.id.minusButton5);
        removeBtn6 = findViewById(R.id.minusButton6);
        removeBtn7 = findViewById(R.id.minusButton7);
        removeBtn8 = findViewById(R.id.minusButton8);
        removeBtn9 = findViewById(R.id.minusButton9);
        removeBtn10 = findViewById(R.id.minusButton10);
        removeBtn11 = findViewById(R.id.minusButton11);
        removeBtn12 = findViewById(R.id.minusButton12);
        removeBtn13 = findViewById(R.id.minusButton13);
        removeBtn14 = findViewById(R.id.minusButton14);
        removeBtn15 = findViewById(R.id.minusButton15);
        removeBtn16 = findViewById(R.id.minusButton16);
        removeBtn17 = findViewById(R.id.minusButton17);
        removeBtn18 = findViewById(R.id.minusButton18);
        removeBtn19 = findViewById(R.id.minusButton19);
        removeBtn20 = findViewById(R.id.minusButton20);
        info1 = findViewById(R.id.infoButton1);
        info2 = findViewById(R.id.infoButton2);
        info3 = findViewById(R.id.infoButton3);
        info4 = findViewById(R.id.infoButton4);
        info5 = findViewById(R.id.infoButton5);
        info6 = findViewById(R.id.infoButton6);
        info7 = findViewById(R.id.infoButton7);
        info8 = findViewById(R.id.infoButton8);
        info9 = findViewById(R.id.infoButton9);
        info10 = findViewById(R.id.infoButton10);
        info11 = findViewById(R.id.infoButton11);
        info12 = findViewById(R.id.infoButton12);
        info13 = findViewById(R.id.infoButton13);
        info14 = findViewById(R.id.infoButton14);
        info15 = findViewById(R.id.infoButton15);
        info16 = findViewById(R.id.infoButton16);
        info17 = findViewById(R.id.infoButton17);
        info18 = findViewById(R.id.infoButton18);
        info19 = findViewById(R.id.infoButton19);
        info20 = findViewById(R.id.infoButton20);
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
        flowerMap.put("forget me not", R.id.flowerItem7);
        flowerMap.put("orchid", R.id.flowerItem8);
        flowerMap.put("magnolia", R.id.flowerItem9);
        flowerMap.put("bluebell", R.id.flowerItem10);
        flowerMap.put("amaryllis", R.id.flowerItem11);
        flowerMap.put("camellia", R.id.flowerItem12);
        flowerMap.put("dahlia", R.id.flowerItem13);
        flowerMap.put("zinnia", R.id.flowerItem14);
        flowerMap.put("aster", R.id.flowerItem15);
        flowerMap.put("gladiolus", R.id.flowerItem16);
        flowerMap.put("marigold", R.id.flowerItem17);
        flowerMap.put("freesia", R.id.flowerItem18);
        flowerMap.put("phlox", R.id.flowerItem19);
        flowerMap.put("poppy", R.id.flowerItem20);


        String Username = getIntent().getStringExtra("username");

        SharedPreferences prefs = getSharedPreferences("app_prefs", MODE_PRIVATE);

        boolean isDebug = (getApplicationInfo().flags & android.content.pm.ApplicationInfo.FLAG_DEBUGGABLE) != 0;
        if (isDebug) {
            prefs.edit().putBoolean(PREFS_KEY_FLOWERS_INITIALIZED, false).apply();
        }


        boolean flowersInitDone = prefs.getBoolean(PREFS_KEY_FLOWERS_INITIALIZED, false);

        if (!flowersInitDone) {
            new Thread(() -> {
                InitializeDatabase.populateDatabase(flowerDao); // Synchronous now

                // ✅ Debug: Verify what's in DB
                List<Flowers> rows = flowerDao.debugGetAll();
                for (Flowers f : rows) {
                    android.util.Log.d("InitDB", "Row: " + f.flower + " $" + f.price);
                }

                // ✅ Also print total count
                int count = flowerDao.getFlowerCount();
                android.util.Log.d("InitDB", "Flowers inserted: " + count);

                // ✅ Mark init done
                prefs.edit().putBoolean(PREFS_KEY_FLOWERS_INITIALIZED, true).apply();
            }).start();
        }




        backbtn.setOnClickListener(v -> {
            Intent intent = new Intent(HomeScreen.this, ShopType.class);
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
                        cardView.setCardBackgroundColor(getResources().getColor(R.color.primary_container));
                    }, 2000);
                });




            } else {
                Toast.makeText(this, "Flower not found", Toast.LENGTH_SHORT).show();
            }

            return true;
        });

        addBtn1.setOnClickListener(v->{
            quantities[0]++;
            Toast.makeText(this, "Quantity: " + quantities[0], Toast.LENGTH_SHORT).show();
        });
        addBtn2.setOnClickListener(v->{
            quantities[1]++;
            Toast.makeText(this, "Quantity: " + quantities[1], Toast.LENGTH_SHORT).show();
        });
        addBtn3.setOnClickListener(v->{
            quantities[2]++;
            Toast.makeText(this, "Quantity: " + quantities[2], Toast.LENGTH_SHORT).show();
        });
        addBtn4.setOnClickListener(v->{
            quantities[3]++;
            Toast.makeText(this, "Quantity: " + quantities[3], Toast.LENGTH_SHORT).show();
        });
        addBtn5.setOnClickListener(v->{
            quantities[4]++;
            Toast.makeText(this, "Quantity: " + quantities[4], Toast.LENGTH_SHORT).show();
        });
        addBtn6.setOnClickListener(v->{
            quantities[5]++;
            Toast.makeText(this, "Quantity: " + quantities[5], Toast.LENGTH_SHORT).show();
        });
        addBtn7.setOnClickListener(v->{
            quantities[6]++;
            Toast.makeText(this, "Quantity: " + quantities[6], Toast.LENGTH_SHORT).show();
        });
        addBtn8.setOnClickListener(v->{
            quantities[7]++;
            Toast.makeText(this, "Quantity: " + quantities[7], Toast.LENGTH_SHORT).show();
        });
        addBtn9.setOnClickListener(v->{
            quantities[8]++;
            Toast.makeText(this, "Quantity: " + quantities[8], Toast.LENGTH_SHORT).show();
        });
        addBtn10.setOnClickListener(v->{
            quantities[9]++;
            Toast.makeText(this, "Quantity: " + quantities[9], Toast.LENGTH_SHORT).show();
        });
        addBtn11.setOnClickListener(v->{
            quantities[10]++;
            Toast.makeText(this, "Quantity: " + quantities[10], Toast.LENGTH_SHORT).show();
        });
        addBtn12.setOnClickListener(v->{
            quantities[11]++;
            Toast.makeText(this, "Quantity: " + quantities[11], Toast.LENGTH_SHORT).show();
        });
        addBtn13.setOnClickListener(v->{
            quantities[12]++;
            Toast.makeText(this, "Quantity: " + quantities[12], Toast.LENGTH_SHORT).show();
        });
        addBtn14.setOnClickListener(v->{
            quantities[13]++;
            Toast.makeText(this, "Quantity: " + quantities[13], Toast.LENGTH_SHORT).show();
        });
        addBtn15.setOnClickListener(v->{
            quantities[14]++;
            Toast.makeText(this, "Quantity: " + quantities[14], Toast.LENGTH_SHORT).show();
        });
        addBtn16.setOnClickListener(v->{
            quantities[15]++;
            Toast.makeText(this, "Quantity: " + quantities[15], Toast.LENGTH_SHORT).show();
        });
        addBtn17.setOnClickListener(v->{
            quantities[16]++;
            Toast.makeText(this, "Quantity: " + quantities[16], Toast.LENGTH_SHORT).show();
        });
        addBtn18.setOnClickListener(v->{
            quantities[17]++;
            Toast.makeText(this, "Quantity: " + quantities[17], Toast.LENGTH_SHORT).show();
        });
        addBtn19.setOnClickListener(v->{
            quantities[18]++;
            Toast.makeText(this, "Quantity: " + quantities[18], Toast.LENGTH_SHORT).show();
        });
        addBtn20.setOnClickListener(v->{
            quantities[19]++;
            Toast.makeText(this, "Quantity: " + quantities[19], Toast.LENGTH_SHORT).show();
        });
        removeBtn1.setOnClickListener(v->{
            if (quantities[0] > 1) {
                quantities[0]--;
            }
            Toast.makeText(this, "Quantity: " + quantities[0], Toast.LENGTH_SHORT).show();
        });
        removeBtn2.setOnClickListener(v->{
            if (quantities[1] > 1) {
                quantities[1]--;
            }
            Toast.makeText(this, "Quantity: " + quantities[1], Toast.LENGTH_SHORT).show();
        });
        removeBtn3.setOnClickListener(v->{
            if (quantities[2] > 1) {
                quantities[2]--;
            }
            Toast.makeText(this, "Quantity: " + quantities[2], Toast.LENGTH_SHORT).show();
        });
        removeBtn4.setOnClickListener(v->{
            if (quantities[3] > 1) {
                quantities[3]--;
            }
            Toast.makeText(this, "Quantity: " + quantities[3], Toast.LENGTH_SHORT).show();
        });
        removeBtn5.setOnClickListener(v->{
            if (quantities[4] > 1) {
                quantities[4]--;
            }
            Toast.makeText(this, "Quantity: " + quantities[4], Toast.LENGTH_SHORT).show();
        });
        removeBtn6.setOnClickListener(v->{
            if (quantities[5] > 1) {
                quantities[5]--;
            }
            Toast.makeText(this, "Quantity: " + quantities[5], Toast.LENGTH_SHORT).show();
        });
        removeBtn7.setOnClickListener(v -> {
            if (quantities[6] > 1) {
                quantities[6]--;
            }
            Toast.makeText(this, "Quantity: " + quantities[6], Toast.LENGTH_SHORT).show();
        });

        removeBtn8.setOnClickListener(v -> {
            if (quantities[7] > 1) {
                quantities[7]--;
            }
            Toast.makeText(this, "Quantity: " + quantities[7], Toast.LENGTH_SHORT).show();
        });

        removeBtn9.setOnClickListener(v -> {
            if (quantities[8] > 1) {
                quantities[8]--;
            }
            Toast.makeText(this, "Quantity: " + quantities[8], Toast.LENGTH_SHORT).show();
        });

        removeBtn10.setOnClickListener(v -> {
            if (quantities[9] > 1) {
                quantities[9]--;
            }
            Toast.makeText(this, "Quantity: " + quantities[9], Toast.LENGTH_SHORT).show();
        });

        removeBtn11.setOnClickListener(v -> {
            if (quantities[10] > 1) {
                quantities[10]--;
            }
            Toast.makeText(this, "Quantity: " + quantities[10], Toast.LENGTH_SHORT).show();
        });

        removeBtn12.setOnClickListener(v -> {
            if (quantities[11] > 1) {
                quantities[11]--;
            }
            Toast.makeText(this, "Quantity: " + quantities[11], Toast.LENGTH_SHORT).show();
        });

        removeBtn13.setOnClickListener(v -> {
            if (quantities[12] > 1) {
                quantities[12]--;
            }
            Toast.makeText(this, "Quantity: " + quantities[12], Toast.LENGTH_SHORT).show();
        });

        removeBtn14.setOnClickListener(v -> {
            if (quantities[13] > 1) {
                quantities[13]--;
            }
            Toast.makeText(this, "Quantity: " + quantities[13], Toast.LENGTH_SHORT).show();
        });

        removeBtn15.setOnClickListener(v -> {
            if (quantities[14] > 1) {
                quantities[14]--;
            }
            Toast.makeText(this, "Quantity: " + quantities[14], Toast.LENGTH_SHORT).show();
        });

        removeBtn16.setOnClickListener(v -> {
            if (quantities[15] > 1) {
                quantities[15]--;
            }
            Toast.makeText(this, "Quantity: " + quantities[15], Toast.LENGTH_SHORT).show();
        });

        removeBtn17.setOnClickListener(v -> {
            if (quantities[16] > 1) {
                quantities[16]--;
            }
            Toast.makeText(this, "Quantity: " + quantities[16], Toast.LENGTH_SHORT).show();
        });

        removeBtn18.setOnClickListener(v -> {
            if (quantities[17] > 1) {
                quantities[17]--;
            }
            Toast.makeText(this, "Quantity: " + quantities[17], Toast.LENGTH_SHORT).show();
        });

        removeBtn19.setOnClickListener(v -> {
            if (quantities[18] > 1) {
                quantities[18]--;
            }
            Toast.makeText(this, "Quantity: " + quantities[18], Toast.LENGTH_SHORT).show();
        });

        removeBtn20.setOnClickListener(v -> {
            if (quantities[19] > 1) {
                quantities[19]--;
            }
            Toast.makeText(this, "Quantity: " + quantities[19], Toast.LENGTH_SHORT).show();
        });


        // Add "Rose" to cart when cartBtn1 is clicked
        cartBtn1.setOnClickListener(v -> {
            new Thread(() -> {
                double price = flowerDao.getFlowerPrice("Rose");
                FlowerRoom rose = new FlowerRoom("Rose", quantities[0], Username, price);
                cartManager.addToCart(rose);
                runOnUiThread(() -> {
                    Toast.makeText(HomeScreen.this,
                            "Rose (Qty: " + quantities[0] + ", $" + price + ") added to cart",
                            Toast.LENGTH_SHORT).show();
                    quantities[0] = 1;
                });
            }).start();
        });

        // Add "Lily" to cart when cartBtn2 is clicked
        cartBtn2.setOnClickListener(v -> {
            new Thread(() -> {
                double price = flowerDao.getFlowerPrice("Lily");
                FlowerRoom lily = new FlowerRoom("Lily", quantities[1], Username, price);
                cartManager.addToCart(lily);
                runOnUiThread(() -> {
                    Toast.makeText(HomeScreen.this,
                            "Lily (Qty: " + quantities[1] + ", $" + price + ") added to cart",
                            Toast.LENGTH_SHORT).show();
                    quantities[1] = 1;
                });
            }).start();
        });

        cartBtn3.setOnClickListener(v -> {
            new Thread(() -> {
                double price = flowerDao.getFlowerPrice("Tulip");
                FlowerRoom tulip = new FlowerRoom("Tulip", quantities[2], Username, price);
                cartManager.addToCart(tulip);
                runOnUiThread(() -> {
                    Toast.makeText(HomeScreen.this,
                            "Tulip (Qty: " + quantities[2] + ", $" + price + ") added to cart",
                            Toast.LENGTH_SHORT).show();
                    quantities[2] = 1;
                });
            }).start();
        });

        cartBtn4.setOnClickListener(v -> {
            new Thread(() -> {
                double price = flowerDao.getFlowerPrice("Daisy");
                FlowerRoom daisy = new FlowerRoom("Daisy", quantities[3], Username, price);
                cartManager.addToCart(daisy);
                runOnUiThread(() -> {
                    Toast.makeText(HomeScreen.this,
                            "Daisy (Qty: " + quantities[3] + ", $" + price + ") added to cart",
                            Toast.LENGTH_SHORT).show();
                    quantities[3] = 1;
                });
            }).start();
        });

        cartBtn5.setOnClickListener(v -> {
            new Thread(() -> {
                double price = flowerDao.getFlowerPrice("Iris");
                FlowerRoom iris = new FlowerRoom("Iris", quantities[4], Username, price);
                cartManager.addToCart(iris);
                runOnUiThread(() -> {
                    Toast.makeText(HomeScreen.this,
                            "Iris (Qty: " + quantities[4] + ", $" + price + ") added to cart",
                            Toast.LENGTH_SHORT).show();
                    quantities[4] = 1;
                });
            }).start();
        });

        cartBtn6.setOnClickListener(v -> {
            new Thread(() -> {
                double price = flowerDao.getFlowerPrice("Peony");
                FlowerRoom peony = new FlowerRoom("Peony", quantities[5], Username, price);
                cartManager.addToCart(peony);
                runOnUiThread(() -> {
                    Toast.makeText(HomeScreen.this,
                            "Peony (Qty: " + quantities[5] + ", $" + price + ") added to cart",
                            Toast.LENGTH_SHORT).show();
                    quantities[5] = 1;
                });
            }).start();
        });

        cartBtn7.setOnClickListener(v -> {
            new Thread(() -> {
                double price = flowerDao.getFlowerPrice("Forget me not");
                FlowerRoom forget_me_not = new FlowerRoom("Forget me not", quantities[6], Username, price);
                cartManager.addToCart(forget_me_not);
                runOnUiThread(() -> {
                    Toast.makeText(HomeScreen.this,
                            "Forget me not (Qty: " + quantities[6] + ", $" + price + ") added to cart",
                            Toast.LENGTH_SHORT).show();
                    quantities[6] = 1;
                });
            }).start();
        });

        cartBtn8.setOnClickListener(v -> {
            new Thread(() -> {
                double price = flowerDao.getFlowerPrice("Orchid");
                FlowerRoom orchid = new FlowerRoom("Orchid", quantities[7], Username, price);
                cartManager.addToCart(orchid);
                runOnUiThread(() -> {
                    Toast.makeText(HomeScreen.this,
                            "Orchid (Qty: " + quantities[7] + ", $" + price + ") added to cart",
                            Toast.LENGTH_SHORT).show();
                    quantities[7] = 1;
                });
            }).start();
        });

        cartBtn9.setOnClickListener(v -> {
            new Thread(() -> {
                double price = flowerDao.getFlowerPrice("Magnolia");
                FlowerRoom magnolia = new FlowerRoom("Magnolia", quantities[8], Username, price);
                cartManager.addToCart(magnolia);
                runOnUiThread(() -> {
                    Toast.makeText(HomeScreen.this,
                            "Magnolia (Qty: " + quantities[8] + ", $" + price + ") added to cart",
                            Toast.LENGTH_SHORT).show();
                    quantities[8] = 1;
                });
            }).start();
        });

        cartBtn10.setOnClickListener(v -> {
            new Thread(() -> {
                double price = flowerDao.getFlowerPrice("Bluebell");
                FlowerRoom bluebell = new FlowerRoom("Bluebell", quantities[9], Username, price);
                cartManager.addToCart(bluebell);
                runOnUiThread(() -> {
                    Toast.makeText(HomeScreen.this,
                            "Bluebell (Qty: " + quantities[9] + ", $" + price + ") added to cart",
                            Toast.LENGTH_SHORT).show();
                    quantities[9] = 1;
                });
            }).start();
        });

        cartBtn11.setOnClickListener(v -> {
            new Thread(() -> {
                double price = flowerDao.getFlowerPrice("Amaryllis");
                FlowerRoom amaryllis = new FlowerRoom("Amaryllis", quantities[10], Username, price);
                cartManager.addToCart(amaryllis);
                runOnUiThread(() -> {
                    Toast.makeText(HomeScreen.this,
                            "Amaryllis (Qty: " + quantities[10] + ", $" + price + ") added to cart",
                            Toast.LENGTH_SHORT).show();
                    quantities[10] = 1;
                });
            }).start();
        });

        cartBtn12.setOnClickListener(v -> {
            new Thread(() -> {
                double price = flowerDao.getFlowerPrice("Camellia");
                FlowerRoom camellia = new FlowerRoom("Camellia", quantities[11], Username, price);
                cartManager.addToCart(camellia);
                runOnUiThread(() -> {
                    Toast.makeText(HomeScreen.this,
                            "Camellia (Qty: " + quantities[11] + ", $" + price + ") added to cart",
                            Toast.LENGTH_SHORT).show();
                    quantities[11] = 1;
                });
            }).start();
        });

        cartBtn13.setOnClickListener(v -> {
            new Thread(() -> {
                double price = flowerDao.getFlowerPrice("Dahlia");
                FlowerRoom dahlia = new FlowerRoom("Dahlia", quantities[12], Username, price);
                cartManager.addToCart(dahlia);
                runOnUiThread(() -> {
                    Toast.makeText(HomeScreen.this,
                            "Dahlia (Qty: " + quantities[12] + ", $" + price + ") added to cart",
                            Toast.LENGTH_SHORT).show();
                    quantities[12] = 1;
                });
            }).start();
        });

        cartBtn14.setOnClickListener(v -> {
            new Thread(() -> {
                double price = flowerDao.getFlowerPrice("Zinnia");
                FlowerRoom zinnia = new FlowerRoom("Zinnia", quantities[13], Username, price);
                cartManager.addToCart(zinnia);
                runOnUiThread(() -> {
                    Toast.makeText(HomeScreen.this,
                            "Zinnia (Qty: " + quantities[13] + ", $" + price + ") added to cart",
                            Toast.LENGTH_SHORT).show();
                    quantities[13] = 1;
                });
            }).start();
        });

        cartBtn15.setOnClickListener(v -> {
            new Thread(() -> {
                double price = flowerDao.getFlowerPrice("Aster");
                FlowerRoom aster = new FlowerRoom("Aster", quantities[14], Username, price);
                cartManager.addToCart(aster);
                runOnUiThread(() -> {
                    Toast.makeText(HomeScreen.this,
                            "Aster (Qty: " + quantities[14] + ", $" + price + ") added to cart",
                            Toast.LENGTH_SHORT).show();
                    quantities[14] = 1;
                });
            }).start();
        });

        cartBtn16.setOnClickListener(v -> {
            new Thread(() -> {
                double price = flowerDao.getFlowerPrice("Gladiolus");
                FlowerRoom gladiolus = new FlowerRoom("Gladiolus", quantities[15], Username, price);
                cartManager.addToCart(gladiolus);
                runOnUiThread(() -> {
                    Toast.makeText(HomeScreen.this,
                            "Gladiolus (Qty: " + quantities[15] + ", $" + price + ") added to cart",
                            Toast.LENGTH_SHORT).show();
                    quantities[15] = 1;
                });
            }).start();
        });

        cartBtn17.setOnClickListener(v -> {
            new Thread(() -> {
                double price = flowerDao.getFlowerPrice("Marigold");
                FlowerRoom marigold = new FlowerRoom("Marigold", quantities[16], Username, price);
                cartManager.addToCart(marigold);
                runOnUiThread(() -> {
                    Toast.makeText(HomeScreen.this,
                            "Marigold (Qty: " + quantities[16] + ", $" + price + ") added to cart",
                            Toast.LENGTH_SHORT).show();
                    quantities[16] = 1;
                });
            }).start();
        });

        cartBtn18.setOnClickListener(v -> {
            new Thread(() -> {
                double price = flowerDao.getFlowerPrice("Freesia");
                FlowerRoom freesia = new FlowerRoom("Freesia", quantities[17], Username, price);
                cartManager.addToCart(freesia);
                runOnUiThread(() -> {
                    Toast.makeText(HomeScreen.this,
                            "Freesia (Qty: " + quantities[17] + ", $" + price + ") added to cart",
                            Toast.LENGTH_SHORT).show();
                    quantities[17] = 1;
                });
            }).start();
        });

        cartBtn19.setOnClickListener(v -> {
            new Thread(() -> {
                double price = flowerDao.getFlowerPrice("Phlox");
                FlowerRoom phlox = new FlowerRoom("Phlox", quantities[18], Username, price);
                cartManager.addToCart(phlox);
                runOnUiThread(() -> {
                    Toast.makeText(HomeScreen.this,
                            "Phlox (Qty: " + quantities[18] + ", $" + price + ") added to cart",
                            Toast.LENGTH_SHORT).show();
                    quantities[18] = 1;
                });
            }).start();
        });

        cartBtn20.setOnClickListener(v -> {
            new Thread(() -> {
                double price = flowerDao.getFlowerPrice("Poppy");
                FlowerRoom poppy = new FlowerRoom("Poppy", quantities[19], Username, price);
                cartManager.addToCart(poppy);
                runOnUiThread(() -> {
                    Toast.makeText(HomeScreen.this,
                            "Poppy (Qty: " + quantities[19] + ", $" + price + ") added to cart",
                            Toast.LENGTH_SHORT).show();
                    quantities[19] = 1;
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

        info7.setOnClickListener(v -> {
            Dialog dialog = new Dialog(this);
            dialog.setContentView(R.layout.pop_up_dialog7);
            dialog.show();
            Window window = dialog.getWindow();
            if (window != null) {
                window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

            }
            // Handle back button click
            ImageButton backButton = dialog.findViewById(R.id.backButton);
            backButton.setOnClickListener(v1 -> dialog.dismiss());
        });

        info8.setOnClickListener(v -> {
            Dialog dialog = new Dialog(this);
            dialog.setContentView(R.layout.pop_up_dialog8);
            dialog.show();
            Window window = dialog.getWindow();
            if (window != null) {
                window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

            }
            // Handle back button click
            ImageButton backButton = dialog.findViewById(R.id.backButton);
            backButton.setOnClickListener(v1 -> dialog.dismiss());
        });

        info9.setOnClickListener(v -> {
            Dialog dialog = new Dialog(this);
            dialog.setContentView(R.layout.pop_up_dialog9);
            dialog.show();
            Window window = dialog.getWindow();
            if (window != null) {
                window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

            }
            // Handle back button click
            ImageButton backButton = dialog.findViewById(R.id.backButton);
            backButton.setOnClickListener(v1 -> dialog.dismiss());
        });

        info10.setOnClickListener(v -> {
            Dialog dialog = new Dialog(this);
            dialog.setContentView(R.layout.pop_up_dialog10);
            dialog.show();
            Window window = dialog.getWindow();
            if (window != null) {
                window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

            }
            // Handle back button click
            ImageButton backButton = dialog.findViewById(R.id.backButton);
            backButton.setOnClickListener(v1 -> dialog.dismiss());
        });

        info11.setOnClickListener(v -> {
            Dialog dialog = new Dialog(this);
            dialog.setContentView(R.layout.pop_up_dialog11);
            dialog.show();
            Window window = dialog.getWindow();
            if (window != null) {
                window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

            }
            // Handle back button click
            ImageButton backButton = dialog.findViewById(R.id.backButton);
            backButton.setOnClickListener(v1 -> dialog.dismiss());
        });

        info12.setOnClickListener(v -> {
            Dialog dialog = new Dialog(this);
            dialog.setContentView(R.layout.pop_up_dialog12);
            dialog.show();
            Window window = dialog.getWindow();
            if (window != null) {
                window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

            }
            // Handle back button click
            ImageButton backButton = dialog.findViewById(R.id.backButton);
            backButton.setOnClickListener(v1 -> dialog.dismiss());
        });

        info13.setOnClickListener(v -> {
            Dialog dialog = new Dialog(this);
            dialog.setContentView(R.layout.pop_up_dialog13);
            dialog.show();
            Window window = dialog.getWindow();
            if (window != null) {
                window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

            }
            // Handle back button click
            ImageButton backButton = dialog.findViewById(R.id.backButton);
            backButton.setOnClickListener(v1 -> dialog.dismiss());
        });

        info14.setOnClickListener(v -> {
            Dialog dialog = new Dialog(this);
            dialog.setContentView(R.layout.pop_up_dialog14);
            dialog.show();
            Window window = dialog.getWindow();
            if (window != null) {
                window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

            }
            // Handle back button click
            ImageButton backButton = dialog.findViewById(R.id.backButton);
            backButton.setOnClickListener(v1 -> dialog.dismiss());
        });

        info15.setOnClickListener(v -> {
            Dialog dialog = new Dialog(this);
            dialog.setContentView(R.layout.pop_up_dialog15);
            dialog.show();
            Window window = dialog.getWindow();
            if (window != null) {
                window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

            }
            // Handle back button click
            ImageButton backButton = dialog.findViewById(R.id.backButton);
            backButton.setOnClickListener(v1 -> dialog.dismiss());
        });

        info16.setOnClickListener(v -> {
            Dialog dialog = new Dialog(this);
            dialog.setContentView(R.layout.pop_up_dialog16);
            dialog.show();
            Window window = dialog.getWindow();
            if (window != null) {
                window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

            }
            // Handle back button click
            ImageButton backButton = dialog.findViewById(R.id.backButton);
            backButton.setOnClickListener(v1 -> dialog.dismiss());
        });

        info17.setOnClickListener(v -> {
            Dialog dialog = new Dialog(this);
            dialog.setContentView(R.layout.pop_up_dialog17);
            dialog.show();
            Window window = dialog.getWindow();
            if (window != null) {
                window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

            }
            // Handle back button click
            ImageButton backButton = dialog.findViewById(R.id.backButton);
            backButton.setOnClickListener(v1 -> dialog.dismiss());
        });

        info18.setOnClickListener(v -> {
            Dialog dialog = new Dialog(this);
            dialog.setContentView(R.layout.pop_up_dialog18);
            dialog.show();
            Window window = dialog.getWindow();
            if (window != null) {
                window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

            }
            // Handle back button click
            ImageButton backButton = dialog.findViewById(R.id.backButton);
            backButton.setOnClickListener(v1 -> dialog.dismiss());
        });

        info19.setOnClickListener(v -> {
            Dialog dialog = new Dialog(this);
            dialog.setContentView(R.layout.pop_up_dialog19);
            dialog.show();
            Window window = dialog.getWindow();
            if (window != null) {
                window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

            }
            // Handle back button click
            ImageButton backButton = dialog.findViewById(R.id.backButton);
            backButton.setOnClickListener(v1 -> dialog.dismiss());
        });

        info20.setOnClickListener(v -> {
            Dialog dialog = new Dialog(this);
            dialog.setContentView(R.layout.pop_up_dialog20);
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
            Intent intent = new Intent(HomeScreen.this, CheckOut.class);
            intent.putExtra("username", Username);
            intent.putExtra("caller", "HomeScreen");
            startActivity(intent);

        });
        goOrders.setOnClickListener(v->{
            Intent intent = new Intent(HomeScreen.this, OrdersView.class);
            intent.putExtra("username", Username);
            intent.putExtra("caller", "HomeScreen");
            startActivity(intent);

        });
    }

}
