package com.example.hifooddeliveries;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class OrderConfirmActivity extends AppCompatActivity {

    TextView subTotal, totals;
    Button confirm, back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_confirm);

        subTotal = findViewById(R.id.rs1);
        totals = findViewById(R.id.rs3);
        confirm = findViewById(R.id.confirmbutton4);

        String total = getIntent().getStringExtra("TOTAL");

        subTotal.setText(total);
        totals.setText(total+129);

        confirm.setOnClickListener(view -> {
            makeToast("Order confirmed Succesfully");
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("cart",null);
            editor.apply();
            finish();
//            Intent intent = new Intent(getApplicationContext(), MostlyOrderedActivity.class);
//            startActivity(intent);
        });
        findViewById(R.id.backButton).setOnClickListener(view->{
            finish();
        });
    }


    public void makeToast(String msg){
        Toast.makeText(OrderConfirmActivity.this, msg, Toast.LENGTH_SHORT).show();
    }
}