package com.example.flowershop;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface OrderDAO {

    @Insert
    void insertOrder(OrderRoom order);

    @Query("SELECT * FROM orders")
    List<OrderRoom> getAllOrders();

    @Query("DELETE FROM orders")
    void deleteAllOrders();
}
