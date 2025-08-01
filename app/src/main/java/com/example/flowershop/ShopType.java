package com.example.flowershop;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ShopType extends AppCompatActivity {

    Button btn1, btn2, btn3, backbtn;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_type);
        btn1= findViewById(R.id.singleFlowerID);
        btn2=findViewById(R.id.bouquetID);
        btn3=findViewById(R.id.customBouquetID);
        backbtn=findViewById(R.id.backScreenID);

        String Username = getIntent().getStringExtra("username");


        backbtn.setOnClickListener(v->{
            Intent intent = new Intent(ShopType.this, HomePage.class);
            intent.putExtra("username", Username);
            startActivity(intent);
            finish();
        });

        btn1.setOnClickListener(v->{
            Intent intent = new Intent(ShopType.this, HomeScreen.class);
            intent.putExtra("username", Username);
            startActivity(intent);
            finish();
        });
        btn2.setOnClickListener(v->{
            Intent intent = new Intent(ShopType.this, BuyBouquetScreen.class);
            intent.putExtra("username", Username);
            startActivity(intent);
            finish();
        });
        btn3.setOnClickListener(v->{
            Intent intent = new Intent(ShopType.this, MakeCustomBouquetScreen.class);
            intent.putExtra("username", Username);
            startActivity(intent);
            finish();
        });
        }
    }
