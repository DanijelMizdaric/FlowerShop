package com.example.flowershop;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "orders")
public class OrderRoom {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public String name;
    public int quantity;

    public OrderRoom(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }
}
