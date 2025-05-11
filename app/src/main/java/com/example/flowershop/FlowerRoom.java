package com.example.flowershop;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "flower_cart")
public class FlowerRoom {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;
    private int quantity;

    // Constructor
    public FlowerRoom(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
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

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
