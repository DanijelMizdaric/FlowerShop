package com.example.flowershop;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class OrdersView extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FlowerDB db;
    private Button backBtn;
    private TextView emptyView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders_view);

        // Initialize views
        recyclerView = findViewById(R.id.recyclerViewOrders);
        backBtn = findViewById(R.id.buttonBackID);
        emptyView = findViewById(R.id.emptyOrdersView);

        // Setup RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);

        // Get database instance
        db = FlowerDB.getDatabase(getApplicationContext());
        String username = getIntent().getStringExtra("username");

        // Back button click handler
        backBtn.setOnClickListener(v -> {
            Intent intent = new Intent(OrdersView.this, HomeScreen.class);
            intent.putExtra("username", username);
            startActivity(intent);
            finish();
        });

        // Load orders in background thread
        new Thread(() -> {
            try {
                List<OrderRoom> orders = db.orderDao().getOrdersForUser(username);
                List<OrderWithFlowers> orderDetails = db.orderDao().getOrdersWithFlowers(username);
                Log.d("OrdersView", "Orders count: " + orders.size());

                runOnUiThread(() -> {
                    if (orderDetails == null || orderDetails.isEmpty()) {
                        recyclerView.setVisibility(View.GONE);
                        emptyView.setVisibility(View.VISIBLE);
                    } else {
                        emptyView.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);
                        OrderAdapter adapter = new OrderAdapter(orderDetails);
                        recyclerView.setAdapter(adapter);
                    }
                });
            } catch (Exception e) {
                runOnUiThread(() ->
                        Toast.makeText(this, "Error loading orders", Toast.LENGTH_SHORT).show());
            }
        }).start();
    }
}