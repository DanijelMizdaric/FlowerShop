package com.example.flowershop;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface OrderFlowerDao {
    @Insert
    void insert(OrderFlower orderFlower);

    @Query("SELECT * FROM order_flower WHERE orderID = :orderId")
    List<OrderFlower> getFlowersForOrder(int orderId);
}

