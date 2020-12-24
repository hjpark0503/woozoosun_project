package com.example.woozoosun_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    Button addressbtn1;
    ImageButton friendBtn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        /*
        //Return_info_DB친구 정보 리턴
        Return_info_DB info = new Return_info_DB("친구이름");
        info.start();
        while(info.flag == false){}
        이름 = get_name()
        아이디 = get_id()
        폰 = get_phone()
        주소 = info.get_address(); //리스트로 리턴 됨 .get(0~2)
        //주소 빼고 나머지 다 string임
         */

        /*
        //Friends_DB 친구이름 리스트 (수정)
        Friends_DB friends = new Friends_DB("사용자 id");
        friends.start();
        while(friends.flag == false){}
        List list = friends.list;
        //list에 친구목록 담겨있음 사용자 id 외에 나머지는 바꿀필요 X
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