package com.example.woozoosun_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class FriendSendActivity extends AppCompatActivity {

    String name; //친구 이름
    TextView selectFriendName;
    List<String> addressList; //친구의 주소목록

    Button[] friendAddressBtn;
    TextView friendAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_send);

        Intent intent = getIntent();
        name = intent.getStringExtra("name"); //친구 이름

        selectFriendName = findViewById(R.id.selectFriendName);
        selectFriendName.setText("친구 "+name+" 님의 우주선");

        //Return_info_DB친구 정보 리턴
        Return_info_DB info = new Return_info_DB(name); //친구 정보 객체 생성
        info.start();
        while(info.flag == false){}
        addressList = info.get_address(); //리스트로 리턴 됨 .get(0~2) 친구의 주소목록


        friendAddressBtn = new Button[3];
        friendAddressBtn[0] = findViewById(R.id.friendAddressBtn1);
        friendAddressBtn[1] = findViewById(R.id.friendAddressBtn2);
        friendAddressBtn[2] = findViewById(R.id.friendAddressBtn3);

        //Return_info_DB친구 정보 리턴
        Return_info_DB friendInfo = new Return_info_DB(name); //사용자 정보 객체 생성
        friendInfo.start();
        while(friendInfo.flag == false){}
        List<String> addressList = friendInfo.get_address(); //리스트로 리턴 됨 .get(0~2)


        friendAddress = findViewById(R.id.friendAddress);
        friendAddress.setText(addressList.get(0)); //디폴트로 첫번째 주소로 보여주기

        for(int i=0; i<3; i++){ //주소는 최대 3개까지
            String address = addressList.get(i);
            System.out.println("주소"+address);
            Log.d("주소",address);
            friendAddressBtn[i].setVisibility(View.VISIBLE);
            friendAddressBtn[i].setText(address);

            friendAddressBtn[i].setOnClickListener(new View.OnClickListener() {  //주소 버튼 눌렀을 때
                @Override
                public void onClick(View v) {
                    friendAddress.setText(address);
                }
            });

            if(i+1 != 3){ //get(i+1) 오버플로우 안나기 위해서
                if(addressList.get(i+1).equals("null")){ //다음 주소가 null이면
                    break;
                }
            }
        }


    }
}