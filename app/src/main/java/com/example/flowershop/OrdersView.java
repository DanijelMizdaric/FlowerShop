package com.example.flowershop;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import java.util.List;

public class OrdersView extends AppCompatActivity {
    RecyclerView recyclerView;
    FlowerDB db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_orders_view);
        recyclerView = findViewById(R.id.recyclerViewOrders);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        db = Room.databaseBuilder(getApplicationContext(), FlowerDB.class, "flowerDB")
                .allowMainThreadQueries().build();

        String username = getIntent().getStringExtra("username");

        List<OrderRoom> orders = db.orderDao().getOrdersForUser(username);

        OrderAdapter adapter = new OrderAdapter(orders);
        recyclerView.setAdapter(adapter);

    }
}