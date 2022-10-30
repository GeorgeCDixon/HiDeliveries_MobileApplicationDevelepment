package com.example.hifooddeliveries;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText email, password;
    Button login, create;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email =  findViewById(R.id.email);
        password = findViewById(R.id.password);
        login = findViewById(R.id.btn_login);
        create =  findViewById(R.id.btn_createAcc);
        dbHelper = new DBHelper(this);


        login.setOnClickListener(view -> {
            String emailText = email.getText().toString();
            String passwordText = password.getText().toString();

            if(emailText.equals("")||passwordText.equals(""))
                makeToast("Please input all fields");
            else{
                String  role = dbHelper.checkEmailPassword(emailText,passwordText);
                System.out.println(role);
                if (role==null)
                    makeToast("Either the user doesn't exist or the password is incorrect");
                else {
                    if (role.equals("customer")){
                        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.clear();
                        editor.putString("userId",dbHelper.getUserId(emailText));
                        editor.apply();

                        finish();
                        Intent intent = new Intent(getApplicationContext(), MostlyOrderedActivity.class);
                        startActivity(intent);
                    }else if(role.equals("admin")){
                        finish();
                        Intent intent = new Intent(getApplicationContext(), AdminActivity.class);
                        startActivity(intent);
                    }
                    makeToast("Successfully logged in");
                }

            }
        });

        create.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
            startActivity(intent);

        });


    }

    public void makeToast(String msg){
        Toast.makeText(LoginActivity.this, msg, Toast.LENGTH_SHORT).show();
    }
}