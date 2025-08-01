package com.example.flowershop;


import android.content.Context;
import androidx.room.Database;
import androidx.room.Index;
import androidx.room.Room;
import androidx.room.RoomDatabase;
@Database(entities = {FlowerRoom.class, OrderRoom.class, User.class, Flowers.class, OrderFlower.class, FlowerFact.class}, version = 14)
public abstract class FlowerDB extends RoomDatabase{

    private static FlowerDB instance;

    public abstract DAO DAO();
    public abstract OrderDAO orderDao();
    public abstract UserDAO userDAO();

    public abstract FlowerDAO flowerDao();
    public abstract OrderFlowerDao orderFlowerDao();
    public abstract FlowerFactDAO flowerFactDAO();
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