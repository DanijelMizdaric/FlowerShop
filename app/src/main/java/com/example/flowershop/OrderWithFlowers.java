package com.example.flowershop;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class OrderWithFlowers {
    @Embedded
    public OrderRoom order;

    @Relation(
            parentColumn = "id",
            entityColumn = "orderID" ,
            entity = OrderFlower.class
    )
    public List<OrderFlower> flowers;
}