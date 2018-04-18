package com.rootonelabs.vicky.lazeez.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;
import com.rootonelabs.vicky.lazeez.Model.Order;

import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteAssetHelper {

    private static final String DB_NAME = "LazeezDB.db";
    private static final int DB_VER=1;


    public Database(Context context) {
        super(context, DB_NAME, null, DB_VER);
    }

    public List<Order> getCarts()
    {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

        String[] sqlSelect = {"FoodName", " FoodId", "Quantity", "Price", "Discount"};
        String sqlTable = "OrderDetails";

        queryBuilder.setTables(sqlTable);
        Cursor c = queryBuilder.query(db, sqlSelect, null, null,null, null, null);

        final List<Order>result = new ArrayList<>();
        if(c.moveToFirst()){
            do {
                result.add(new Order(c.getString(c.getColumnIndex("FoodId")),
                        c.getString(c.getColumnIndex("FoodName")),
                        c.getString(c.getColumnIndex("Quantity")),
                        c.getString(c.getColumnIndex("Price")),
                        c.getString(c.getColumnIndex("Quantity"))
                ));
            }while (c.moveToNext());
        }

        return result;
    }

    public void addToCart(Order order)
    {
        SQLiteDatabase db = getReadableDatabase();
        String query = String.format("INSERT INTO OrderDetails(FoodId, FoodName, Quantity, Price, Discount) VALUES ('%s', '%s', '%s', '%s', '%s')",
                order.getFoodId(),
                order.getFoodName(),
                order.getQuantity(),
                order.getPrice(),
                order.getDiscount());
        db.execSQL(query);
    }


    public void cleanCart()
    {
        SQLiteDatabase db = getReadableDatabase();
        String query = String.format("DELETE FROM OrderDetails");
        db.execSQL(query);
    }

}
