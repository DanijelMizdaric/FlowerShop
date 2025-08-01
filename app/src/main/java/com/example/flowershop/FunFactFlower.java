package com.example.flowershop;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class FunFactFlower extends AppCompatActivity {

    private TextView textFact;
    private FactManager factManager;
    private Button backbtn;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fun_fact_flower);
        String Username = getIntent().getStringExtra("username");
        textFact = findViewById(R.id.factText);
        backbtn= findViewById(R.id.goBackID);
        factManager = new FactManager(this);

        SharedPreferences prefs = getSharedPreferences("db_prefs", MODE_PRIVATE);
        prefs.edit().putBoolean("db_initialized", false).apply();

        if (!prefs.getBoolean("db_initialized", false)) {
            new Thread(() -> {
                FlowerFactDAO dao = FlowerDB.getDatabase(getApplicationContext()).flowerFactDAO();

                if (dao.getCount() == 0) {
                    InitializeDatabase.populateFacts(dao);
                }

                prefs.edit().putBoolean("db_initialized", true).apply();

                // Wait until facts are inserted before fetching
                try {
                    Thread.sleep(500); // Wait a short time for inserts to complete
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // Now fetch the daily fact
                factManager.getDailyFact(fact -> runOnUiThread(() -> {
                    if (fact != null) {
                        textFact.setText(fact.factText);
                    } else {
                        textFact.setText("No facts available.");
                    }
                }));
            }).start();
        } else {
            factManager.getDailyFact(fact -> runOnUiThread(() -> {
                if (fact != null) {
                    textFact.setText(fact.factText);
                } else {
                    textFact.setText("No facts available.");
                }
            }));
        }

        backbtn.setOnClickListener(v->{
            Intent intent = new Intent(FunFactFlower.this, HomePage.class);
            intent.putExtra("username",Username);
            startActivity(intent);
            finish();
        });

    }
}
