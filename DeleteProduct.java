package com.sakshi.inventorymanagementapp;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DeleteProduct extends AppCompatActivity implements DeleteAdapter.OnDeleteListener {
    private RecyclerView recyclerView;
    private DeleteAdapter adapter;
    private myDBHelper dbHelper;
    private ArrayList<Model> deleteItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_product);

        recyclerView = findViewById(R.id.recyclerview);
        dbHelper = new myDBHelper(this);

        deleteItem = dbHelper.getAllProducts();

        adapter = new DeleteAdapter(this, deleteItem, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        ImageButton backButton = findViewById(R.id.back);
        backButton.setOnClickListener(v -> finish());
    }

    @Override
    public void onDelete(int position) {
        Model itemToDelete = deleteItem.get(position);
        dbHelper.deletedata(itemToDelete.getId());
        deleteItem.remove(position);
        adapter.notifyItemRemoved(position);
    }
}