package com.example.hifooddeliveries;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "database.db";

    public DBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create Table users(id TEXT primary key,role Text, name TEXT, email TEXT, phone TEXT, password TEXT)");
        MyDB.execSQL("create Table items(id TEXT primary key,name TEXT, price TEXT, image TEXT, description TEXT)");
        MyDB.execSQL("create Table orders(id TEXT primary key,itemId TEXT, total REAL, customerId TEXT, riderId TEXT, instructions TEXT, confirmed TEXT)");
        MyDB.execSQL("create Table riders(id TEXT primary key,name TEXT, rating REAL)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {

        MyDB.execSQL("drop Table if exists users");

        MyDB.execSQL("drop Table if exists items");

        onCreate(MyDB);
    }

    public boolean insertUser(String id, String role, String name, String email, String phone, String password) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", id);
        contentValues.put("role", role);
        contentValues.put("name", name);
        contentValues.put("email", email);
        contentValues.put("phone", phone);
        contentValues.put("password", password);
        try {
            long result = MyDB.insert("users", null, contentValues);
            return result != -1;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean insertItem(String id, String name, String imageURL, String price, String description) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", id);
        contentValues.put("name", name);
        contentValues.put("price", price);
        contentValues.put("image", imageURL);
        contentValues.put("description", description);
        try {
            long result = MyDB.insert("items", null, contentValues);
            return result != -1;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean insertOrder(String id, String itemId, double total, String customerId, String instructions) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", id);
        contentValues.put("itemId", itemId);
        contentValues.put("total", total);
        contentValues.put("customerId", customerId);
        contentValues.put("instructions", instructions);
        contentValues.put("confirmed", "false");
        try {
            long result = MyDB.insert("orders", null, contentValues);
            return result != -1;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean checkEmail(String email) {
        SQLiteDatabase MyDB = getWritableDatabase();
        System.out.println(MyDB.getPath());
        try {
            Cursor cursor = MyDB.rawQuery("select * from users where email=?", new String[]{email});
            System.out.println(cursor.getColumnName(0));
            return cursor.getCount() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @SuppressLint("Range")
    public String getUserId(String email) {
        SQLiteDatabase MyDB = getWritableDatabase();
        System.out.println(MyDB.getPath());
        try {
            Cursor cursor = MyDB.rawQuery("select id from users where email=?", new String[]{email});
            cursor.moveToFirst();
            return cursor.getString(cursor.getColumnIndex("id"));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
//
//    @SuppressLint("Range")
//    public FoodModel getFoodByOrderId(String id) {
//        SQLiteDatabase MyDB = getWritableDatabase();
//        System.out.println(MyDB.getPath());
//        try {
//            Cursor cursor = MyDB.rawQuery("select * from orders o INNER JOIN items i ON  o.itemId=i.id where id=?", new String[]{id});
//            cursor.moveToFirst();
//            String idd = cursor.getString(cursor.getColumnIndex("id"));
//            String name = cursor.getString(cursor.getColumnIndex("name"));
//            String price = cursor.getString(cursor.getColumnIndex("price"));
//            String image = cursor.getString(cursor.getColumnIndex("image"));
//            String description = cursor.getString(cursor.getColumnIndex("description"));
//
//            return new FoodModel(idd, name, description, price, image);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }

    @SuppressLint("Range")
    public String checkEmailPassword(String email, String password) {
        SQLiteDatabase MyDB = getWritableDatabase();
        try {
            Cursor cursor = MyDB.rawQuery("select role from users where email=? AND password=?", new String[]{email, password});
            System.out.println(cursor.getCount());
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                return cursor.getString(cursor.getColumnIndex("role"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @SuppressLint("Range")
    public ArrayList<FoodModel> getFoodItems() {
        ArrayList<FoodModel> items = new ArrayList<>();
        SQLiteDatabase MyDB = getWritableDatabase();
        try {
            Cursor cursor = MyDB.rawQuery("select * from items", null);

            if (cursor.moveToFirst()) {
                while (!cursor.isAfterLast()) {
                    String id = cursor.getString(cursor.getColumnIndex("id"));
                    String name = cursor.getString(cursor.getColumnIndex("name"));
                    String price = cursor.getString(cursor.getColumnIndex("price"));
                    String image = cursor.getString(cursor.getColumnIndex("image"));
                    String description = cursor.getString(cursor.getColumnIndex("description"));

                    FoodModel item = new FoodModel(id, name, description, price, image);
                    System.out.println(id);
                    items.add(item);
                    cursor.moveToNext();
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }

    @SuppressLint("Range")
    public ArrayList<OrderModel> getOrders() {
        ArrayList<OrderModel> items = new ArrayList<>();
        SQLiteDatabase MyDB = getWritableDatabase();
        try {
            Cursor cursor = MyDB.rawQuery("select * from orders", null);

            if (cursor.moveToFirst()) {
                while (!cursor.isAfterLast()) {
                    String id = cursor.getString(cursor.getColumnIndex("id"));
                    String customerId = cursor.getString(cursor.getColumnIndex("customerId"));
                    String itemId = cursor.getString(cursor.getColumnIndex("itemId"));
                    String riderId = cursor.getString(cursor.getColumnIndex("riderId"));
                    String total = cursor.getString(cursor.getColumnIndex("total"));
                    String confirmed = cursor.getString(cursor.getColumnIndex("confirmed"));

                    OrderModel item = new OrderModel(id, customerId, itemId, riderId, total, confirmed);
                    System.out.println(id);
                    items.add(item);
                    cursor.moveToNext();
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return items;
    }

    @SuppressLint("Range")
    public FoodModel getFoodItemById(String id) {
        FoodModel item;
        SQLiteDatabase MyDB = getWritableDatabase();
        try {
            Cursor cursor = MyDB.rawQuery("select * from items where id=?", new String[]{id});

            if (cursor.moveToFirst()) {
                while (!cursor.isAfterLast()) {
                    id = cursor.getString(cursor.getColumnIndex("id"));
                    String name = cursor.getString(cursor.getColumnIndex("name"));
                    String price = cursor.getString(cursor.getColumnIndex("price"));
                    String image = cursor.getString(cursor.getColumnIndex("image"));
                    String description = cursor.getString(cursor.getColumnIndex("description"));

                    item = new FoodModel(id, name, description, price, image);
                    return item;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }


    @SuppressLint("Range")
    public String getRiderByName(String name) {
        SQLiteDatabase MyDB = getWritableDatabase();
        System.out.println(MyDB.getPath());
        try {
            Cursor cursor = MyDB.rawQuery("select * from riders where name=?", new String[]{name});
            cursor.moveToFirst();
            if (cursor.getCount()>0) {
                return cursor.getString(cursor.getColumnIndex("id"));
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    @SuppressLint("Range")
    public Boolean confirmOrder(String id) {
        SQLiteDatabase MyDB = getWritableDatabase();
        System.out.println(MyDB.getPath());
        try {
            Cursor cursor = MyDB.rawQuery("UPDATE orders set confirmed='true' where id=?", new String[]{id});
            cursor.moveToFirst();
            return cursor.getCount()>0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    @SuppressLint("Range")
    public Boolean setRider(String orderId, String riderId) {
        SQLiteDatabase MyDB = getWritableDatabase();
        System.out.println(MyDB.getPath());
        try {
            Cursor cursor = MyDB.rawQuery("UPDATE orders set riderId=? where id=?", new String[]{riderId,orderId});
            cursor.moveToFirst();
            return cursor.getCount()>0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
