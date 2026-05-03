package com.sakshi.inventorymanagementapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AddProduct extends AppCompatActivity {
    private myDBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        dbHelper = new myDBHelper(this);

        EditText nameInput = findViewById(R.id.product_name);
        EditText priceInput = findViewById(R.id.product_price);
        Button saveButton = findViewById(R.id.save);
        ImageButton backButton = findViewById(R.id.back);

        saveButton.setOnClickListener(v -> {
            String name = nameInput.getText().toString();
            String priceStr = priceInput.getText().toString();

            if (name.isEmpty() || priceStr.isEmpty()) {
                Toast.makeText(AddProduct.this, "Please enter both name and price", Toast.LENGTH_SHORT).show();
                return;
            }

            double price;
            try {
                price = Double.parseDouble(priceStr);
            } catch (NumberFormatException e) {
                Toast.makeText(AddProduct.this, "Invalid price format", Toast.LENGTH_SHORT).show();
                return;
            }

            dbHelper.addProduct(name, price);

            Toast.makeText(AddProduct.this, "Product Added", Toast.LENGTH_SHORT).show();
            finish();
        });

        backButton.setOnClickListener(v -> finish());
    }
}
