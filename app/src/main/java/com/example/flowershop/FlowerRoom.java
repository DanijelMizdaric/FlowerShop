package com.example.flowershop;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "flower_cart")
public class FlowerRoom {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private int quantity;
    private String username;
    private double price;
    private int orderID;
@Ignore
    public FlowerRoom(String name, int quantity, String username, double price) {
        this(name, quantity, username, price, 0);
    }

    public FlowerRoom(String name, int quantity, String username, double price, int orderID) {
        this.name = name;
        this.quantity = quantity;
        this.username = username;
        this.price = price;
        this.orderID = orderID;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getQuantity() {
        return quantity;
    }
    public String getUsername(){
        return username;
    }
    public void setUsername(String username){
        this.username=username;
    }
    public double getPrice() {return price;}
    public void setPrice(double price){this.price=price;}
    public int getOrderID(){return orderID;}
    public void setOrderID(int orderID){this.orderID=orderID;}
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
