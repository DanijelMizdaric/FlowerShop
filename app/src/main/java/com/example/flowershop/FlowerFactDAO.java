package com.example.flowershop;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;


@Dao
public interface FlowerFactDAO {
@Insert
    void insert (FlowerFact fact);
@Query("SELECT * FROM flower_facts")
    List<FlowerFact> getAllFacts();
@Query("SELECT * FROM flower_facts WHERE id = :id LIMIT 1")
    FlowerFact getFactById(int id);
@Query("SELECT COUNT(*) FROM flower_facts")
    int getCount();
@Query("DELETE FROM flower_facts")
    void deleteAll();
}
