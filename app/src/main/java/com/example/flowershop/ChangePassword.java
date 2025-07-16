package com.example.flowershop;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class ChangePassword extends AppCompatActivity {

    Button backBtn, confirmBtn;
    UserDAO userDAO;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userDAO = FlowerDB.getDatabase(getApplicationContext()).userDAO();
        setContentView(R.layout.activity_change_password);

        backBtn= findViewById(R.id.BackID);
        confirmBtn = findViewById(R.id.changeID);

        backBtn.setOnClickListener(v->{
            Intent intent = new Intent(ChangePassword.this, MainActivity.class);
            startActivity(intent);
            finish();
        });

        confirmBtn.setOnClickListener(v->{

            String inputUsername = ((EditText) findViewById(R.id.usernameID)).getText().toString();
            String inputEmail = ((EditText)findViewById(R.id.emailID)).getText().toString();
            String inputPass = ((EditText)findViewById(R.id.passwordID)).getText().toString();
            String inputConfirmPass = ((EditText)findViewById(R.id.newPassID)).getText().toString();
            if (inputUsername.isEmpty() || inputEmail.isEmpty() || inputPass.isEmpty() || inputConfirmPass.isEmpty()){
                Toast.makeText(this, "Please fill in the fields", Toast.LENGTH_SHORT).show();
                return;
            }

            if(!inputPass.equals(inputConfirmPass)){
                Toast.makeText(this, "Passwords do not match!", Toast.LENGTH_SHORT).show();
                return;
            }

            new Thread(()->{
                User user = userDAO.getUsername(inputUsername);
                if(user == null || !user.email.equalsIgnoreCase(inputEmail)){
                    runOnUiThread(()->{
                        Toast.makeText(this,"Invalid username or email", Toast.LENGTH_SHORT).show();
                    });


                } else {
                    user.password = inputPass;
                    userDAO.update(user);
                    runOnUiThread(()->{
                        Toast.makeText(this,"Password changed successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(ChangePassword.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    });
                }
            }).start();
        });
    }
}