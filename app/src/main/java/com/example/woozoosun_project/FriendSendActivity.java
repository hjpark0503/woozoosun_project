package com.example.woozoosun_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import java.util.List;

public class FriendSendActivity extends AppCompatActivity {

    String friendName;
    List<String> addressList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_send);

        Intent intent = getIntent();
        friendName = intent.getStringExtra("name"); //친구 이름
        addressList = (List<String>) intent.getSerializableExtra("addressList");


    }
}