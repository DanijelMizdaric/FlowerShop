package com.example.flowershop;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity(tableName = "order_flower")
public class OrderFlower {

        @PrimaryKey(autoGenerate = true)
        private int id;

        @ColumnInfo(name = "orderID")
        private int orderID;
        @ColumnInfo(name = "name")
        private String name;
        @ColumnInfo(name = "quantity")
        private int quantity;
        @ColumnInfo(name = "username")
        private String username;
        @ColumnInfo(name = "price")
        private double price;


        // Getters and setters for all fields

        public int getId() {
                return id;
        }
        public void setId(int id) {
                this.id = id;
        }

        public int getOrderID() {
                return orderID;
        }
        public void setOrderID(int orderID) {
                this.orderID = orderID;
        }

        public String getName() {
                return name;
        }
        public void setName(String name) {
                this.name = name;
        }

        public int getQuantity() {
                return quantity;
        }
        public void setQuantity(int quantity) {
                this.quantity = quantity;
        }

        public String getUsername() {
                return username;
        }
        public void setUsername(String username) {
                this.username = username;
        }

        public double getPrice() {
                return price;
        }
        public void setPrice(double price) {
                this.price = price;
        }

}
