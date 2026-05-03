package com.sakshi.inventorymanagementapp;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class PlaceOrder extends AppCompatActivity implements OrderAdapter.OnDeleteListener {
    private RecyclerView recyclerView;
    private OrderAdapter adapter;
    private ArrayList<Model> orderList;
    private myDBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_order);

        dbHelper = new myDBHelper(this);
        recyclerView = findViewById(R.id.recyclerview1);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        orderList = dbHelper.getAllProducts();
        adapter = new OrderAdapter(this, orderList, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onsale(int position) {
        Model itemToSale = orderList.get(position);
        dbHelper.deletedata(itemToSale.getId());
        orderList.remove(position);
        adapter.notifyItemRemoved(position);
    }
}
