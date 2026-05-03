package com.sakshi.inventorymanagementapp;

import android.os.Bundle;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CurrentStock extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ProductAdapter adapter;
    private myDBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_stock);

        dbHelper = new myDBHelper(this);
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ArrayList<Model> productList = dbHelper.getAllProducts();
        adapter = new ProductAdapter(productList);
        recyclerView.setAdapter(adapter);

        ImageButton backButton = findViewById(R.id.back);
        backButton.setOnClickListener(v -> finish());
    }
}