package com.sakshi.inventorymanagementapp;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class myDBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "app.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_PRODUCT = "product";
    private static final String P_ID = "id";
    private static final String P_NAME = "name";
    private static final String P_PRICE = "price";

    public myDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_PRODUCT + " (" + P_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + P_NAME + " TEXT, "  + P_PRICE + " REAL)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCT);
        onCreate(db);
    }

    public void addProduct(String name, double price) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(P_NAME, name);
        values.put(P_PRICE, price);
        db.insert(TABLE_PRODUCT, null, values);
    }
    @SuppressLint("Range")
    public ArrayList<Model> getAllProducts() {
        ArrayList<Model> products = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_PRODUCT, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex(P_ID));
                String name = cursor.getString(cursor.getColumnIndex(P_NAME));
                double price = cursor.getDouble(cursor.getColumnIndex(P_PRICE));

                products.add(new Model(id, name, price));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return products;
    }

    public boolean deletedata(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_PRODUCT, P_ID + " = ?", new String[]{String.valueOf(id)}) > 0;
    }
    @SuppressLint("Range")
    public Model getProductByName(String name) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_PRODUCT, new String[]{P_ID, P_NAME, P_PRICE}, P_NAME + "=?", new String[]{name}, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            int id = cursor.getInt(cursor.getColumnIndex(P_ID));
            double price = cursor.getDouble(cursor.getColumnIndex(P_PRICE));
            cursor.close();
            return new Model(id, name, price);
        }
        return null;
    }
    @SuppressLint("Range")
    public Model getProductById(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_PRODUCT, new String[]{P_ID, P_NAME, P_PRICE}, P_ID + "=?", new String[]{String.valueOf(id)}, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            String name = cursor.getString(cursor.getColumnIndex(P_NAME));
            double price = cursor.getDouble(cursor.getColumnIndex(P_PRICE));
            cursor.close();
            return new Model(id, name, price);
        }
        return null;
    }

}
