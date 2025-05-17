package com.example.flowershop;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "flower_cart")
public class FlowerRoom {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;
    private int quantity;
    private String username;

    // Constructor
    public FlowerRoom(String name, int quantity, String username) {
        this.name = name;
        this.quantity = quantity;
        this.username = username;
    }

    // Getters and Setters
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

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
