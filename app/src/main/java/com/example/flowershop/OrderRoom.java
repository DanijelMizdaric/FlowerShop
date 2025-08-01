package com.example.flowershop;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "orders")
public class OrderRoom {

    @PrimaryKey(autoGenerate = true)
    public int id;


    public String username;

    public String date;
    public String address;
    public int postalCode;
    public int phoneNumber;
    public OrderRoom(String username, String date, String address, int postalCode, int phoneNumber) {

        this.username = username;
        this.date = date;
        this.address = address;
        this.postalCode = postalCode;
        this.phoneNumber = phoneNumber;

    }


}
