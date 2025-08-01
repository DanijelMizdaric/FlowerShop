package com.example.flowershop;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class HomePage extends AppCompatActivity {

    Button btn1, btn2, btn3, backBtn;
    TextView textView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        btn1 = findViewById(R.id.orderID);
        btn2 = findViewById(R.id.checkOrderID);
        btn3 = findViewById(R.id.FactID);
        backBtn = findViewById(R.id.goBackbtn);
        textView = findViewById(R.id.totalID);
        String Username = getIntent().getStringExtra("username");
        textView.setText("Welcome back, " + Username + "!");


        backBtn.setOnClickListener(v->{
            Intent intent = new Intent(HomePage.this, MainActivity.class);
            startActivity(intent);
            finish();
        });

        btn1.setOnClickListener(v->{
            Intent intent = new Intent(HomePage.this, ShopType.class);
            intent.putExtra("username",Username);
            startActivity(intent);
            finish();
        });
        btn2.setOnClickListener(v->{
            Intent intent = new Intent (HomePage.this, OrdersView.class);
            intent.putExtra("username",Username);
            startActivity(intent);

        });
        btn3.setOnClickListener(v->{
            Intent intent = new Intent(HomePage.this, FunFactFlower.class);
            intent.putExtra("username",Username);
            startActivity(intent);
        });
    }
}