package com.example.flowershop;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.MaterialAutoCompleteTextView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class CheckOut extends AppCompatActivity {

    private String selectedItem = null;
    String[] item = {"Deliver", "Pick up in Store"};
    MaterialAutoCompleteTextView autoCompleteTextView;
    TextInputLayout dropdownLayout, textInputLayout1, textInputLayout2, textInputLayout3;
    TextInputEditText editText1, editText2, editText3;
    TextView textView, textViewprice;

    Button btn1, btn2, btn3;

    FlowerDAO flowerDao;
    FlowerDB flowerDB;
    OrderDAO orderDao;
    private final Executor executor = Executors.newSingleThreadExecutor();
    private String Username; // Moved to class level for consistent access

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out);

        flowerDB = FlowerDB.getDatabase(this);
        flowerDao = flowerDB.flowerDao();
        orderDao = flowerDB.orderDao();

        btn1 = findViewById(R.id.backBtn);
        btn2 = findViewById(R.id.checkoutBtnID);
        btn3 = findViewById(R.id.viewCartID);
        textView = findViewById(R.id.textView2);
        textViewprice = findViewById(R.id.totalID);
        editText1 = findViewById(R.id.addressID);
        editText2 = findViewById(R.id.postalID);
        editText3 = findViewById(R.id.phoneID);
        textInputLayout1 = findViewById(R.id.addressLayout);
        textInputLayout2 = findViewById(R.id.postalLayout);
        textInputLayout3 = findViewById(R.id.phoneLayout);
        dropdownLayout = findViewById(R.id.textInputLayout);
        autoCompleteTextView = findViewById(R.id.auto_complete_txt);

        ArrayAdapter<String> adapterItems = new ArrayAdapter<>(this, R.layout.list_item, item);
        autoCompleteTextView.setAdapter(adapterItems);

        Username = getIntent().getStringExtra("username");
        String caller = getIntent().getStringExtra("caller");

        setVisibility(false);

        calculateTotal(textViewprice);

        autoCompleteTextView.setOnClickListener(v -> autoCompleteTextView.showDropDown());

        autoCompleteTextView.setOnItemClickListener((parent, view, position, id) -> {
            selectedItem = (String) parent.getItemAtPosition(position);
            setVisibility("Deliver".equals(selectedItem));
            if (!"Deliver".equals(selectedItem)) {
                editText1.setText("");
                editText2.setText("");
                editText3.setText("");
            }
        });

        btn1.setOnClickListener(v -> {
            Intent intent = null;
            switch (caller){
                case "HomeScreen":
                    intent = new Intent(CheckOut.this, HomeScreen.class);
                    break;
                case "BuyBouquetScreen":
                    intent = new Intent(CheckOut.this, BuyBouquetScreen.class);
                    break;
                default:
                    Toast.makeText(CheckOut.this,"Error, can't go back",Toast.LENGTH_SHORT).show();
                    break;
            }

            intent.putExtra("username", Username);
            startActivity(intent);
            finish();
        });

        btn2.setOnClickListener(v -> {
            if (selectedItem == null || selectedItem.trim().isEmpty()){
                Toast.makeText(CheckOut.this, "Please select a delivery option.", Toast.LENGTH_SHORT).show();
                return;
            } else if ("Deliver".equals(selectedItem) && (isItEmpty(editText1) || isItEmpty(editText2) || isItEmpty(editText3))){
                Toast.makeText(CheckOut.this, "All fields must be filled.", Toast.LENGTH_SHORT).show();
                return;
            } else {
                executor.execute(() -> {
                    List<FlowerRoom> cartItems = flowerDao.getCartForUser(Username);

                    if (cartItems != null && !cartItems.isEmpty()) {
                        handleCheckout();
                        runOnUiThread(() -> {
                            Intent intent = new Intent(CheckOut.this, HomeScreen.class);
                            intent.putExtra("username", Username);
                            startActivity(intent);
                            finish();
                        });
                    } else {
                        runOnUiThread(() ->
                                Toast.makeText(CheckOut.this, "Cart is empty!", Toast.LENGTH_SHORT).show()
                        );
                    }
                });
            }
        });

        btn3.setOnClickListener(v -> {
            Intent intent = new Intent(CheckOut.this, CartView.class);
            intent.putExtra("username", Username);
            if ("Homescreen" == caller) {
                intent.putExtra("caller", "Homescreen" );
            } else  {
                intent.putExtra("caller", "BuyBouquetScreen");
            }
            startActivity(intent);
            finish();
        });
    }

    private void setVisibility(boolean visible) {
        int visibility = visible ? View.VISIBLE : View.INVISIBLE;
        textView.setVisibility(visibility);
        editText1.setVisibility(visibility);
        editText2.setVisibility(visibility);
        editText3.setVisibility(visibility);
        textInputLayout1.setVisibility(visibility);
        textInputLayout2.setVisibility(visibility);
        textInputLayout3.setVisibility(visibility);
    }

    private void calculateTotal(TextView totalTextView) {
        executor.execute(() -> {
            try {
                List<FlowerRoom> flowers = flowerDao.getCartForUser(Username);
                double totalPrice = 0;
                for (FlowerRoom f : flowers) {
                    totalPrice += f.getQuantity() * f.getPrice();
                }

                final double finalTotalPrice = totalPrice;
                runOnUiThread(() -> {
                    totalTextView.setText("Your total is: $" + String.format("%.2f", finalTotalPrice));
                });
            } catch (Exception e) {
                runOnUiThread(() -> {
                    Toast.makeText(this, "Error calculating total", Toast.LENGTH_SHORT).show();
                });
            }
        });
    }

    private void handleCheckout() {
        new Thread(() -> {
            List<FlowerRoom> cartItems = flowerDao.getCartForUser(Username);

            if (cartItems == null || cartItems.isEmpty()) {
                runOnUiThread(() ->
                        Toast.makeText(this, "Cart is empty!", Toast.LENGTH_SHORT).show()
                );
                return;
            }

            String currentDate = null;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                currentDate = LocalDate.now().toString();
            }
            String address = "";
            int postalCode = 0;
            int phoneNumber = 0;

            if ("Deliver".equals(autoCompleteTextView.getText().toString())) {
                address = editText1.getText().toString();
                try {
                    postalCode = Integer.parseInt(editText2.getText().toString());
                    phoneNumber = Integer.parseInt(editText3.getText().toString());
                } catch (NumberFormatException ignored) {}
            }

            OrderRoom order = new OrderRoom(Username, currentDate, address, postalCode, phoneNumber);
            long orderRowId = orderDao.insertOrder(order);
            int orderID = (int) orderRowId;

            for (FlowerRoom flower : cartItems) {
                try {
                    OrderFlower orderFlower = new OrderFlower();
                    orderFlower.setOrderID(orderID);
                    orderFlower.setName(flower.getName());
                    orderFlower.setQuantity(flower.getQuantity());
                    orderFlower.setPrice(flower.getPrice());
                    orderFlower.setUsername(Username);

                    flowerDB.orderFlowerDao().insert(orderFlower);
                } catch (Exception e) {
                    Log.e("ORDERFLOWER", "Error creating/inserting order flower: ", e);
                }
            }

            flowerDao.deleteCartForUser(Username);

            runOnUiThread(() -> {
                Toast.makeText(this, "Order completed!", Toast.LENGTH_SHORT).show();
            });
        }).start();
    }

    private boolean isItEmpty(TextInputEditText editText) {
        return editText.getText() == null || editText.getText().toString().trim().isEmpty();
    }
}