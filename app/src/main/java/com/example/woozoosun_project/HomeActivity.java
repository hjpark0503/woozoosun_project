package com.example.woozoosun_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    Button addressbtn1;
    ImageButton friendBtn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        /* 친구이름 리스트
        Friends friend = new Friends("ryeong"); //""안에 사용자 id 넣을것
        ArrayList friends = friend.showList(); //"ryeong"의 친구들이 리스트로 리턴됨
        이 메소드 활용해서 친구들 이름 바꿔서 띄우면 됩니다!
        */

        addressbtn1 = findViewById(R.id.addressbtn1);
        addressbtn1.setOnClickListener(new View.OnClickListener() {  //사용자 주소 버튼 눌렀을 때
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, UserAddressActivity.class);
                startActivity(intent);
            }
        });


        friendBtn1 = findViewById(R.id.friendBtn1);
        friendBtn1.setOnClickListener(new View.OnClickListener() {  //첫번째 친구 버튼 눌렀을 때
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, FriendSendActivity.class);
                startActivity(intent);
            }
        });

    }
}