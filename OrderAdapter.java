package com.sakshi.inventorymanagementapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {
    private ArrayList<Model> orderList;
    private OrderAdapter.OnDeleteListener onDeleteListener;
    private Context context;

    public interface OnDeleteListener {
        void onsale(int position);
    }

    public static class OrderViewHolder extends RecyclerView.ViewHolder {
        public TextView name, price;
        ImageView sale;

        public OrderViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.sell_name);
            price = itemView.findViewById(R.id.sell_price);
            sale = itemView.findViewById(R.id.sale);
        }
    }

    // Constructor for OrderAdapter
    public OrderAdapter(Context context, ArrayList<Model> orderList, OnDeleteListener onDeleteListener) {
        this.context = context;
        this.orderList = orderList;
        this.onDeleteListener = onDeleteListener;
    }

    @Override
    public OrderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_order_list, parent, false);
        return new OrderViewHolder(v);
    }

    @Override
    public void onBindViewHolder(OrderViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Model currentItem = orderList.get(position);
        holder.name.setText(currentItem.getName());
        holder.price.setText(String.format("Rs: %.2f", currentItem.getPrice()));

        holder.sale.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Warning");
            builder.setMessage("Are you sure?");
            builder.setCancelable(false);
            builder.setPositiveButton("Yes", (dialogInterface, i) -> {
                if (onDeleteListener != null) {
                    onDeleteListener.onsale(position);
                }
            });
            builder.setNegativeButton("No", (dialogInterface, i) -> dialogInterface.cancel());
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        });
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }
}
