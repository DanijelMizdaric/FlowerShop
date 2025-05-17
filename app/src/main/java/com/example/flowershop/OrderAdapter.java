package com.example.flowershop;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {

    private List<OrderRoom> orders;

    public OrderAdapter(List<OrderRoom> orders) {
        this.orders = orders;
    }

    @Override
    public OrderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_item, parent, false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(OrderViewHolder holder, int position) {
        OrderRoom order = orders.get(position);

        holder.orderIdText.setText("Order ID: " + order.orderID);
        holder.flowerNameText.setText("Flower: " + order.name);
        holder.quantityText.setText("Quantity: " + order.quantity);

        // add more if needed
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    public static class OrderViewHolder extends RecyclerView.ViewHolder {

        TextView orderIdText, flowerNameText, quantityText;

        public OrderViewHolder(View itemView) {
            super(itemView);
            orderIdText = itemView.findViewById(R.id.orderIdText);
            flowerNameText = itemView.findViewById(R.id.flowerNameText);
            quantityText = itemView.findViewById(R.id.quantityText);
        }
    }
}
