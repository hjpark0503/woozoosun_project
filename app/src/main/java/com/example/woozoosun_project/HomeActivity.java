package com.example.woozoosun_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TextView;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    String userId;
    Friends_DB friends;
    List<String> list;

    Button addressbtn1;
    ImageButton[] friendBtn;
    TextView[] friendName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Intent intent = getIntent();
        userId = intent.getStringExtra("userId"); //사용자가 입력한 아이디


        //Friends_DB 친구이름 리스트 (수정)
        friends = new Friends_DB(userId);
        friends.start();
        while(friends.flag == false){}
        list = friends.list;
        //list에 친구목록 담겨있음 사용자 id 외에 나머지는 바꿀필요 X


        friendBtn[1] = findViewById(R.id.friendBtn1);
        friendBtn[2] = findViewById(R.id.friendBtn2);
        friendBtn[3] = findViewById(R.id.friendBtn3);
        friendBtn[4] = findViewById(R.id.friendBtn4);

        friendName[1] = findViewById(R.id.friendName1);
        friendName[2] = findViewById(R.id.friendName2);
        friendName[3] = findViewById(R.id.friendName3);
        friendName[4] = findViewById(R.id.friendName4);


        for(int i=0; i<4; i++){ //친구 목록 지금 4개 디폴트
            friendBtn[i].setVisibility(View.INVISIBLE); //친구 목록 버튼 안보이게
            friendName[i].setVisibility(View.INVISIBLE); //친구 목록 테스트 안보이게
        }


        for(int i=0; i<list.size(); i++){ //친구 수 만큼 반복
            friendBtn[i].setVisibility(View.INVISIBLE); //친구 목록 버튼 보이게
            friendName[i].setVisibility(View.INVISIBLE); //친구 목록 테스트 보이게

            friendName[i].setText(list.get(i)); //친구 이름 목록에 띄우기


            //Return_info_DB친구 정보 리턴
            Return_info_DB info = new Return_info_DB(list.get(i));
            info.start();
            while(info.flag == false){};
            String name =info.get_name(); //친구 이름
            String id = info.get_id(); //친구 아이디
            String phone = info.get_phone();
            List<String> addressList = info.get_address(); //리스트로 리턴 됨 .get(0~2)
            //주소 빼고 나머지 다 string임

            friendBtn[i].setOnClickListener(new View.OnClickListener() {  //i번째 친구 버튼 눌렀을 때
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(HomeActivity.this, FriendSendActivity.class);
                    intent.putExtra("name", name); //친구 이름 전달
                    intent.putExtra("addressList", (Parcelable) addressList); //친구 주소 리스트 전달
                    startActivity(intent);
                }
            });
        }



        addressbtn1 = findViewById(R.id.addressbtn1);
        addressbtn1.setOnClickListener(new View.OnClickListener() {  //사용자 주소 버튼 눌렀을 때
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, UserAddressActivity.class);
                startActivity(intent);
            }
        });



    }
}