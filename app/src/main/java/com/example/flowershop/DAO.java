package com.example.flowershop;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import androidx.room.Delete;
import java.util.List;

@Dao
public interface DAO {

    @Insert
    void insert(FlowerRoom flower);

    @Insert
    void insertAll(List<FlowerRoom> flowers);

    @Update
    void update(FlowerRoom flower);

    @Delete
    void delete(FlowerRoom flower);

    @Query("SELECT * FROM flower_cart")
    List<FlowerRoom> getAllFlowers();

    @Query("SELECT * FROM flower_cart WHERE id = :flowerId")
    FlowerRoom getFlowerById(int flowerId);

    @Query("DELETE FROM flower_cart")
    void deleteAllFlowers();
}
