package com.sakshi.inventorymanagementapp;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    private ArrayList<Model> productList;

    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        public TextView name, price;

        public ProductViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.product_name);
            price = itemView.findViewById(R.id.product_price);
        }
    }

    public ProductAdapter(ArrayList<Model> productList) {

        this.productList = productList;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_item_product, parent, false);
        return new ProductViewHolder(v);

    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        Model currentItem = productList.get(position);
        holder.name.setText(currentItem.getName());
        holder.price.setText(String.format("Rs:%.2f", currentItem.getPrice()));
    }

    @Override
    public int getItemCount(){

        return productList.size();
    }

}