package com.sakshi.inventorymanagementapp;

import android.os.Bundle;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class ProductList extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ProductAdapter adapter;
    private myDBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

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