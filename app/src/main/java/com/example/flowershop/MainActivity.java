package com.example.flowershop;

import android.os.Bundle;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText usernameInput, passwordInput;
    Button loginButton, registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usernameInput = findViewById(R.id.usernameID);
        passwordInput = findViewById(R.id.passwordID);
        loginButton = findViewById(R.id.button);
        registerButton = findViewById(R.id.button2);

        loginButton.setOnClickListener(v -> {
            String user = usernameInput.getText().toString();
            String pass = passwordInput.getText().toString();

            // Simple check — replace this with real authentication later
            if (user.equals("admin") && pass.equals("1234")) {
                Intent intent = new Intent(MainActivity.this, HomeScreen.class);
                startActivity(intent);
                Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                finish(); // Optional: closes login screen
            } else {
                Toast.makeText(MainActivity.this, "Invalid credentials", Toast.LENGTH_SHORT).show();
            }
        });

        registerButton.setOnClickListener(v -> {
            // You can later link this to a registration activity
            Toast.makeText(MainActivity.this, "Register button clicked", Toast.LENGTH_SHORT).show();
        });
    }
}