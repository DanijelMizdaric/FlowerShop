package com.example.flowershop;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "flower_facts")
public class FlowerFact {

        @PrimaryKey(autoGenerate = true)
        public int id;

        @ColumnInfo(name = "fact_text")
        public String factText;


        public FlowerFact (String factText){
            this.factText=factText;
        }
        public FlowerFact(){}
    }

