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
        //ID_check 회원가입시 id 중복 check
        ID_check test = new ID_check("가입할 id");
        test.execute();
        while(test.flag == false){}
        String result = test.result;
        //id 중복 시 result = "error" / 중복 X result = "pass"
         */

        /*
        //Login_DB 로그인
        Login_DB test = new Login_DB("name", "park");
        test.execute(); //이거 꼭 해줘야 디비 데이터랑 비교가능
        while(test.flag == false){}
        String user_name = get_name(); //로그인한 유저 이름
        String user_id = get_id(); //로그인한 유저의 id(친구목록 검색 등에 사용)
        //id, pw 길이 검사 등은 다 코드에서 해야함
        //Login_DB 클래스는 오로지 id, pw를 디비와 비교해서 일치하는지 안하는지만 check
        //로그인 성공 실패 확인은 get_name()==null 이면 실패, 아니면 성공임(성공하면 이름이 리텀됨)
         */


        /*
        //Join_DB 회원가입
        Join_DB test = new Join_DB("이름", "id","pw","전화번호","주소","주소2","주소3");
        test.execute(); //이거 꼭 해줘야 디비로 전송됨
        while(test.flag == false){}
        String result = test.result; //정상적으로 디비에 전송되면 success 아니면 error 리턴함
        //DB에 들어가는 정보는 주소2, 주소3만 비울 수 있고 나머지는 비우면 전송안됨
        //주소2, 주소3 비워져있는지 체크하고 !!꼭!! 그자리에 null 넣어서 전송해야함 꼭!!!!! 대문자 NULL 안됨 꼭 null
         */


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
        Friends_DB friends = new Friends_DB("ryeong");
        friends.start();
        while(friends.flag == false){}
        List<String> list = friends.list;
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