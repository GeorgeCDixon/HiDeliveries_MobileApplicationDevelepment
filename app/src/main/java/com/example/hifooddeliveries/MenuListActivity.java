package com.example.hifooddeliveries;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.ListResourceBundle;

public class MenuListActivity extends AppCompatActivity {

    Button addNew;
    ListView listview;
    ArrayList<FoodModel> list;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_list);

        addNew = findViewById(R.id.add_new);
        dbHelper = new DBHelper(this);
        listview = findViewById(R.id.listView);


        list = dbHelper.getFoodItems();
        ListAdapter adapter = new FoodItemMenuAdapter(list, MenuListActivity.this);
        listview.setAdapter(adapter);


        addNew.setOnClickListener(vies->{
            Intent intent = new Intent(getApplicationContext(), AddFoodItemActivity.class);
            startActivity(intent);
        });
    }
}