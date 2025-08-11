
package com.example.flowershop;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import java.util.List;

@Dao
public interface OrderDAO {

    @Insert
    long insertOrder(OrderRoom order);

    @Update
    void update(OrderRoom order);

    @Query("SELECT * FROM orders WHERE username = :username")
    List<OrderRoom> getOrdersForUser(String username);

    @Transaction
    @Query("SELECT * FROM orders WHERE username = :username")
    List<OrderWithFlowers> getOrdersWithFlowers(String username);

    @Query("SELECT * FROM orders ORDER BY id DESC LIMIT 1")
    OrderRoom getLastOrder();


}
