package com.example.hifooddeliveries;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Set;

public class AddToCartActivity extends AppCompatActivity {

    EditText instructions;
    Button addToCart;
    TextView name;
    ImageView image;
    CheckBox option1, option2;
    DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_cart);

        instructions = findViewById(R.id.comment_cart);
        addToCart = findViewById(R.id.btn_cart);
        name = findViewById(R.id.textView27);
        image = findViewById(R.id.imageView28);
        option1 = findViewById(R.id.checkBox_cart);
        option2 = findViewById(R.id.caeckbox_cartt);
        dbHelper = new DBHelper(this);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();


        String itemId = getIntent().getStringExtra("ITEM_ID");

        if (itemId==null) {
            makeToast("Something went wrong");
            finish();
        }

        FoodModel item = dbHelper.getFoodItemById(itemId);
        if (item!=null){
            name.setText(item.getName());

            int imageResource = getResources().getIdentifier(item.getImgURL(), null, getPackageName());
            if (imageResource>0) {
                image.setImageDrawable(getDrawable(imageResource));
            }
        }

        addToCart.setOnClickListener(view -> {
            String comments = instructions.getText().toString();
            String userId = preferences.getString("userId", null);
            double total = Double.parseDouble(item.getPrice());
            if (option1.isChecked())
                total += 650;
            if (option2.isChecked())
                total += 700;
            String id=String.valueOf(System.currentTimeMillis());
            dbHelper.insertOrder(id, item.getId(), total, userId, comments);
            String cart = preferences.getString("cart", null);

            if (cart!=null)
                cart += ","+item.getId();
            else
                cart = item.getId();
            System.out.println(cart);

            editor.putString("cart", cart);
            editor.apply();

            makeToast("Item added to cart successfully");

            finish();
            Intent intent = new Intent(getApplicationContext(), CartActivity.class);
            startActivity(intent);

        });




    }


    public void makeToast(String msg){
        Toast.makeText(AddToCartActivity.this, msg, Toast.LENGTH_SHORT).show();
    }
}