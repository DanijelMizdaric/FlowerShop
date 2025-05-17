package com.example.flowershop;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface UserDAO {
@Insert
    void insert(User user);
    @Query("SELECT * FROM users WHERE username =:username AND password =:password")
    User login(String username, String password);

    @Query("SELECT * FROM users WHERE username =:username")
    User getUsername(String username);
}
