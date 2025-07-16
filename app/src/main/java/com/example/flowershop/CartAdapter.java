package com.example.flowershop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import java.util.List;

public class CartAdapter extends ArrayAdapter<FlowerRoom> {
    private final List<FlowerRoom> flowers;
    private final CartManager cartManager;

    public CartAdapter(Context context, List<FlowerRoom> flowers, CartManager cartManager) {
        super(context, 0, flowers);
        this.flowers = flowers;
        this.cartManager = cartManager;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        FlowerRoom flower = flowers.get(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.cart_item, parent, false);
        }

        TextView flowerName = convertView.findViewById(R.id.itemName);
        Button removeBtn = convertView.findViewById(R.id.removeButton);
        double total = 0;
        total += flower.getQuantity() * flower.getPrice();
        flowerName.setText(flower.getName() + " (Qty: " + flower.getQuantity() + ", Price: " + total + ")");


        removeBtn.setOnClickListener(v -> {
            cartManager.removeFromCart(flower);
            flowers.remove(position);
            notifyDataSetChanged();
            Toast.makeText(getContext(), flower.getName() + " removed", Toast.LENGTH_SHORT).show();
        });

        return convertView;
    }
}
