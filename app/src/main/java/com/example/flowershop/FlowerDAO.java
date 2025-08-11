package com.example.flowershop;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import androidx.room.Delete;
import java.util.List;

@Dao
public interface FlowerDAO {


    @Insert
    void insert(FlowerRoom flower);

    @Insert
    void insertAll(List<FlowerRoom> flowers);

    @Update
    void update(FlowerRoom flower);

    @Delete
    void delete(FlowerRoom flower);

    @Delete
    void deleteFromCart(FlowerRoom flowerRoom);

    @Query("SELECT * FROM flower_cart")
    List<FlowerRoom> getAllFlowers();

    @Query("SELECT * FROM flower_cart WHERE id = :flowerId")
    FlowerRoom getFlowerById(int flowerId);

    @Query("DELETE FROM flower_cart")
    void deleteAllFlowers();


    @Insert
    void insert(Flowers flowers);

    @Update
    void update(Flowers flowers);

    @Query("SELECT * FROM Flowers WHERE flower_name = :flower_name")
    Flowers getFlowerByName(String flower_name);

    @Query("SELECT price FROM Flowers WHERE flower_name = :flower_name LIMIT 1")
    Double getFlowerPrice(String flower_name);

    @Query("DELETE FROM flowers")
    void deleteAll();

    @Query("SELECT COUNT(*) FROM Flowers")
    int getFlowerCount();
    @Query("SELECT * FROM Flowers")
    List<Flowers> debugGetAll();

    @Query("SELECT * FROM flower_cart WHERE username = :username")
    List<FlowerRoom> getCartForUser(String username);

    @Query("DELETE FROM flower_cart WHERE username = :username")
    void deleteCartForUser(String username);

}
