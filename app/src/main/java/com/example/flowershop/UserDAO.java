package com.example.flowershop;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface UserDAO {
@Insert
void insert(User user);
@Update
void update (User user);
    @Query("SELECT * FROM users WHERE username =:username AND password =:password")
    User login(String username, String password);

    @Query("SELECT * FROM users WHERE username =:username")
    User getUsername(String username);

    @Query("SELECT * FROM users WHERE email =:email")
    User getEmail(String email);
}
