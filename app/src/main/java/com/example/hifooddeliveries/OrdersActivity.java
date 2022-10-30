package com.example.hifooddeliveries;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class OrdersActivity extends AppCompatActivity {


    ListView listview;
    ArrayList<OrderModel> list;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);

        listview = findViewById(R.id.listView);
        dbHelper = new DBHelper(this);

        list = dbHelper.getOrders();
        ListAdapter adapter = new OrderListAdapter(list, OrdersActivity.this);
        listview.setAdapter(adapter);

    }
}