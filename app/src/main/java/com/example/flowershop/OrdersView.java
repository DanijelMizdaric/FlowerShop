package com.example.flowershop;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class OrdersView extends AppCompatActivity {

    RecyclerView recyclerView;
    FlowerDB db;

    Button backBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders_view);

        recyclerView = findViewById(R.id.recyclerViewOrders);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        backBtn = findViewById(R.id.buttonBackID);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
        db = FlowerDB.getDatabase(getApplicationContext());

        String Username = getIntent().getStringExtra("username");

        backBtn.setOnClickListener(v->{
            Intent intent = new Intent(OrdersView.this, HomeScreen.class);
            intent.putExtra("username", Username);
            startActivity(intent);
            finish();
        });


        new Thread(() -> {
            List<OrderRoom> orders = db.orderDao().getOrdersForUser(Username);

            // Update UI on main thread
            runOnUiThread(() -> {
                OrderAdapter adapter = new OrderAdapter(orders);
                recyclerView.setAdapter(adapter);
            });
        }).start();
    }
}
