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

public class DeleteAdapter extends RecyclerView.Adapter<DeleteAdapter.DeleteViewHolder> {
    private ArrayList<Model> deleteItem;
    private OnDeleteListener onDeleteListener;
    private Context context;

    public interface OnDeleteListener {
        void onDelete(int position);
    }

    public DeleteAdapter(Context context, ArrayList<Model> deleteItem, OnDeleteListener onDeleteListener) {
        this.context = context;
        this.deleteItem = deleteItem;
        this.onDeleteListener = onDeleteListener;
    }

    public static class DeleteViewHolder extends RecyclerView.ViewHolder {
        public TextView name, price;
        ImageView delete;

        public DeleteViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.product_name_);
            price = itemView.findViewById(R.id.product_price_);
            delete = itemView.findViewById(R.id.delete_item);
        }
    }

    @Override
    public DeleteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_delete_item, parent, false);
        return new DeleteViewHolder(v);
    }

    @Override
    public void onBindViewHolder(DeleteViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Model currentItem = deleteItem.get(position);
        holder.name.setText(currentItem.getName());
        holder.price.setText(String.format("Rs:%.2f", currentItem.getPrice()));

        holder.delete.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Warning");
            builder.setMessage("Are you sure?");
            builder.setCancelable(false);
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    if (onDeleteListener != null) {
                        onDeleteListener.onDelete(position);
                    }
                }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                }
            });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        });
    }

    @Override
    public int getItemCount() {
        return deleteItem.size();
    }
}
