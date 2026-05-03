package com.sakshi.inventorymanagementapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    DeleteAdapter adapter;
    myDBHelper dbHelper;
    ArrayList<Model> dataholder = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new myDBHelper(this);

        ImageButton addProduct = findViewById(R.id.add_btn);
        ImageButton deleteProduct = findViewById(R.id.delete_btn);
        ImageButton listProduct = findViewById(R.id.list_btn);
        ImageButton currentStock = findViewById(R.id.stock_btn);
        ImageButton PlaceOrder = findViewById(R.id.placeorder_btn);

        addProduct.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddProduct.class);
            startActivity(intent);
        });

        deleteProduct.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, DeleteProduct.class);
            startActivity(intent);
        });

        listProduct.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ProductList.class);
            startActivity(intent);
        });

        currentStock.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CurrentStock.class);
            startActivity(intent);
        });

       PlaceOrder.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, PlaceOrder.class);
            startActivity(intent);
        });
    }

    private void deletedata(int position) {
        if (position < 0 || position >= dataholder.size()) {
            Toast.makeText(this, "Invalid position", Toast.LENGTH_SHORT).show();
            return;
        }

        Model modelToDelete = dataholder.get(position);

        boolean isDeleted = dbHelper.deletedata(modelToDelete.getId());
        if (isDeleted) {
            dataholder.remove(position);
            if (adapter != null) {
                adapter.notifyItemRemoved(position);
            }
            Toast.makeText(this, "Product deleted", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Error deleting product", Toast.LENGTH_SHORT).show();
        }
    }
}
