package com.example.woozoosun_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class UserAddressActivity extends AppCompatActivity {

    String address;
    TextView userAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_address);


        Intent intent = getIntent();
        address = intent.getStringExtra("address"); //사용자가 클릭한 주소

        userAddress = findViewById(R.id.userAddress);
        userAddress.setText(address);


    }
}