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

    private UserDAO userDao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        usernameInput = findViewById(R.id.usernameID);
        passwordInput = findViewById(R.id.passwordID);
        loginButton = findViewById(R.id.button);
        registerButton = findViewById(R.id.button2);

        loginButton.setOnClickListener(v -> {
            String inputUsername = usernameInput.getText().toString().trim();
            String inputPassword = passwordInput.getText().toString().trim();

            // Use a background thread to query Room
            new Thread(() -> {
                FlowerDB db = FlowerDB.getDatabase(getApplicationContext());
                UserDAO userDao = db.userDAO();

                User userData = userDao.getUsername(inputUsername);

                runOnUiThread(() -> {
                    if (userData != null && userData.password.equals(inputPassword)) {
                        // Login successful
                        Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, HomeScreen.class);
                        intent.putExtra("username",userData.username);
                        startActivity(intent);
                        finish();
                    } else {
                        // Login failed
                        Toast.makeText(MainActivity.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                    }
                });
            }).start();
        });



        registerButton.setOnClickListener(v -> {
            Intent intent = new Intent (MainActivity.this, Register.class);
            startActivity(intent);
            finish();
        });
    }
}