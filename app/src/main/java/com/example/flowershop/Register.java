package com.example.flowershop;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Register extends AppCompatActivity {

    Button backbtn;
    Button registerBtn;
    private UserDAO userDAO;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userDAO = FlowerDB.getDatabase(getApplicationContext()).userDAO();

        setContentView(R.layout.activity_register);
        backbtn = findViewById(R.id.backBtnID2);
        registerBtn = findViewById(R.id.regBtnID);

        backbtn.setOnClickListener(v -> {
            Intent intent = new Intent(Register.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
        registerBtn.setOnClickListener(v -> {
            String inputUsername = ((EditText) findViewById(R.id.userTextID)).getText().toString();
            String inputEmail = ((EditText) findViewById(R.id.emailTextiD)).getText().toString();
            String inputPassword = ((EditText) findViewById(R.id.passTextID)).getText().toString();
            new Thread(() -> {
                User exists = userDAO.getUsername(inputUsername);
                if (exists == null) {
                    User newUser = new User();
                    newUser.username = inputUsername;
                    newUser.email = inputEmail;
                    newUser.password = inputPassword;

                    userDAO.insert(newUser);

                    runOnUiThread(() -> {
                        Toast.makeText(this, "User Registered Successfully!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(this, MainActivity.class));
                        finish();
                    });
                } else {
                    runOnUiThread(() ->
                            Toast.makeText(this, "User already exists!", Toast.LENGTH_SHORT).show()
                    );
                }
            }).start();
        });
    }
}
