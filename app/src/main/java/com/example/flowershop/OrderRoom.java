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
    public double price;
    public String date;
    public OrderRoom(int orderID, String name, int quantity, double price, String username, String date) {
        this.orderID = orderID;
        this.name = name;
        this.quantity = quantity;
        this.username = username;
        this.price = price;
        this.date = date;
    }


}
