package com.example.flowershop;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "orders")
public class OrderRoom {

    @PrimaryKey(autoGenerate = true)
    public int id;
    public int orderID;
    public String name;
    public int quantity;
    public String username;
    public OrderRoom(int orderID, String name, int quantity, String username) {
        this.orderID=orderID;
        this.name = name;
        this.quantity = quantity;
        this.username = username;
    }
}
