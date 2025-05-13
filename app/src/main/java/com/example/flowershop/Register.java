package com.example.flowershop;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Register extends AppCompatActivity {


    Button backbtn;
    Button registerBtn;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_register);
       backbtn=findViewById(R.id.backBtnID2);
       registerBtn=findViewById(R.id.regBtnID);

       backbtn.setOnClickListener(v->{
           Intent intent = new Intent(Register.this, MainActivity.class);
           startActivity(intent);
           finish();
       });
        }
    }
