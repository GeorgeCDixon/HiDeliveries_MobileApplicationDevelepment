package com.example.hifooddeliveries;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddFoodItemActivity extends AppCompatActivity {

    EditText name, imageURL, price, description;
    Button addnewItem;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food_item);

        name = findViewById(R.id.foodItemName);
        imageURL = findViewById(R.id.imageUrl);
        price = findViewById(R.id.itemPrice);
        description = findViewById(R.id.itemDescription2);
        addnewItem = findViewById(R.id.btn_submit);
        dbHelper = new DBHelper(this);

        addnewItem.setOnClickListener(view -> {
            String nameText = name.getText().toString();
            String imageURLText = imageURL.getText().toString();
            String priceText = price.getText().toString();
            String descriptionText = description.getText().toString();

            if (nameText.equals("")||imageURLText.equals("")||priceText.equals("")||descriptionText.equals("")) {
                makeToast("Please fill all input fields");
            }else{
                boolean added = dbHelper.insertItem(String.valueOf(System.currentTimeMillis()), nameText, imageURLText, priceText, descriptionText);
                if (added){
                    makeToast("Item added successfully");
                    finish();
                }else
                    makeToast("An error occurred. Please try again.");
            }

        });
    }
    public void makeToast(String msg){
        Toast.makeText(AddFoodItemActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

}