package com.example.hifooddeliveries;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {

    ListView listview;
    ArrayList<FoodModel> list;
    DBHelper dbHelper;
    Button confirm;
    double total = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        dbHelper = new DBHelper(this);
        listview = findViewById(R.id.listView);
        list = new ArrayList<>();
        confirm = findViewById(R.id.btn_submit2);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();

        String[] cart = preferences.getString("cart", null).split(",");
        for (String x:
             cart) {
            FoodModel item = dbHelper.getFoodItemById(x);
            total+=Double.parseDouble(item.getPrice());
            System.out.println(x +"   hfhhfhf");
            list.add(item);
        }
        ListAdapter adapter = new CartListItemAdapter(list, CartActivity.this);
        listview.setAdapter(adapter);



        confirm.setOnClickListener(view -> {
            if (total>0) {
                finish();
                Intent intent = new Intent(getApplicationContext(), OrderConfirmActivity.class);
                intent.putExtra("TOTAL", total);
                startActivity(intent);
            }
        });

    }
}