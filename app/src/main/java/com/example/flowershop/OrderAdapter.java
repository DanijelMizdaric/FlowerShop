package com.example.flowershop;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {

    List<OrderRoom> orderList; // Use OrderRoom instead of Order

    public OrderAdapter(List<OrderRoom> orders) {
        this.orderList = orders;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_item, parent, false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        OrderRoom order = orderList.get(position);
        holder.orderDetails.setText(order.name + " x" + order.quantity); // Use OrderRoom fields
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    static class OrderViewHolder extends RecyclerView.ViewHolder {
        TextView orderDetails;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            orderDetails = itemView.findViewById(R.id.textOrderDetails);
        }
    }
}
