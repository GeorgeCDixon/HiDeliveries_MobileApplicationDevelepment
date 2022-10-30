package com.example.hifooddeliveries;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class AdminActivity extends AppCompatActivity {

    Button menuList, OrderList, riders, deliveryStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        menuList = findViewById(R.id.menuList);
        OrderList = findViewById(R.id.orderList);
        riders = findViewById(R.id.manageDeliveryStaff);
        deliveryStatus = findViewById(R.id.deliveryStatus);

        menuList.setOnClickListener(view-> {
            Intent intent = new Intent(getApplicationContext(), MenuListActivity.class);
            startActivity(intent);
        });
        OrderList.setOnClickListener(view-> {
            Intent intent = new Intent(getApplicationContext(), OrdersActivity.class);
            startActivity(intent);
        });
        riders.setOnClickListener(view-> {
            Intent intent = new Intent(getApplicationContext(), ViewRidersActivity.class);
            startActivity(intent);
        });
        deliveryStatus.setOnClickListener(view-> {
            Intent intent = new Intent(getApplicationContext(), OrdersActivity.class);
            startActivity(intent);
        });

    }
}