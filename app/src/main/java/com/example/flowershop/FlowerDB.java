package com.example.flowershop;


import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
@Database(entities = {FlowerRoom.class, OrderRoom.class, User.class}, version = 5)
public abstract class FlowerDB extends RoomDatabase{

    private static FlowerDB instance;

    public abstract DAO flowerDao();
    public abstract OrderDAO orderDao();
    public abstract UserDAO userDAO();

    public static FlowerDB getDatabase(final Context context) {
        if (instance == null) {
            synchronized (FlowerDB.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context.getApplicationContext(),
                                    FlowerDB.class, "flower_database")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return instance;
    }
}