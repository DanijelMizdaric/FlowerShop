package com.example.flowershop;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity (tableName = "Flowers")
public class Flowers {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "flower_name")
    public String flower;

    @ColumnInfo(name = "flower_type")
    public String type;

    @ColumnInfo(name = "price")
    public double price;

    public Flowers(String flower, double price){
        this.flower=flower;
        this.price=price;
    }
    public Flowers(){}
}
