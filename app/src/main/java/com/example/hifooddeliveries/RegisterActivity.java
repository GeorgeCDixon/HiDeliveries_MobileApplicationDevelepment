package com.example.hifooddeliveries;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    EditText email, password, name, phone, repassword;
    Button login, create;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        email =  findViewById(R.id.email);
        password =  findViewById(R.id.password);
        repassword =  findViewById(R.id.repassword);
        name=  findViewById(R.id.name);
        phone =  findViewById(R.id.phone);
        create =  findViewById(R.id.btn_createAcc);
        login =  findViewById(R.id.btn_login);

        dbHelper = new DBHelper(this);

        create.setOnClickListener(view -> {
            String emailText = email.getText().toString();
            String passwordText = password.getText().toString();
            String repasswordText = repassword.getText().toString();
            String nameText = name.getText().toString();
            String phoneText = phone.getText().toString();

            if (emailText.equals("")||passwordText.equals("")||repasswordText.equals("")||nameText.equals("")||phoneText.equals(""))
                makeToast("Please fill all fields");
            else if (!passwordText.equals(repasswordText))
                makeToast("Password don't match");
            else{
                boolean exists = dbHelper.checkEmail(emailText);
                if (exists){
                    makeToast("The user already exists. Please login");
                }else {
                  boolean registered = dbHelper.insertUser(String.valueOf(System.currentTimeMillis()), "customer", nameText, emailText, phoneText, passwordText);
                  if (registered) {
                      makeToast("Registered Successfully");
                      finish();
                      Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                      startActivity(intent);
                  }
                  else
                      makeToast("Error while registering");
                }
            }

        });

        login.setOnClickListener(view -> {
            finish();
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
        });
    }

    public void makeToast(String msg){
        Toast.makeText(RegisterActivity.this, msg, Toast.LENGTH_SHORT).show();
    }
}