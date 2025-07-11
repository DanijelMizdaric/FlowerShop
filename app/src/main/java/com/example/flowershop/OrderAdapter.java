package com.example.flowershop;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {

    private List<OrderWithFlowers> orders;

    public OrderAdapter(List<OrderWithFlowers> orders) {
        this.orders = orders;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_order, parent, false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        OrderWithFlowers orderWithFlowers = orders.get(position);
        OrderRoom order = orderWithFlowers.order;
        List<OrderFlower> flowers = orderWithFlowers.flowers;
        Log.d("OrderAdapter", "Order ID: " + order.id + " has " + (flowers != null ? flowers.size() : 0) + " flowers.");
        // Set order date
        holder.orderDate.setText("Order Date: " + order.date); // Change to your actual date field

        // Build flowers list text
        StringBuilder flowersText = new StringBuilder();
        double total = 0;
        Log.d("OrderAdapter", "Order id: " + order.id + ", Flowers count: " + flowers.size());
        for (OrderFlower flower : flowers) {
            flowersText.append(String.format("• %s (Qty: %d) - $%.2f\n",
                    flower.getName(),
                    flower.getQuantity(),
                    flower.getPrice()));
            total += flower.getPrice() * flower.getQuantity();
        }

        holder.flowersList.setText(flowersText.toString());
        holder.orderTotal.setText(String.format("Total: $%.2f", total));
    }

    @Override
    public int getItemCount() {
        return orders == null ? 0 : orders.size();
    }

    static class OrderViewHolder extends RecyclerView.ViewHolder {
        TextView orderDate;
        TextView flowersList;
        TextView orderTotal;

        public OrderViewHolder(View itemView) {
            super(itemView);
            orderDate = itemView.findViewById(R.id.textOrderDate);
            flowersList = itemView.findViewById(R.id.textFlowersList);
            orderTotal = itemView.findViewById(R.id.textOrderTotal);
        }
    }
}