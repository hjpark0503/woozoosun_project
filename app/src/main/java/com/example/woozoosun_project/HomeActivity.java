package com.example.woozoosun_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
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

    String userId, userName;
    TextView textViewName;

    Friends_DB friends;
    List<String> list;

    Button[] addressBtn;
    ImageButton[] friendBtn;
    TextView[] friendName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Intent intent = getIntent();
        userId = intent.getStringExtra("userId"); //사용자 아이디
        userName = intent.getStringExtra("userName"); //사용자 이름

        textViewName =findViewById(R.id.textViewName);
        textViewName.setText(userName+" 님의 우주선");

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


        addressBtn = new Button[3];
        addressBtn[0] = findViewById(R.id.addressBtn1);
        addressBtn[1] = findViewById(R.id.addressBtn2);
        addressBtn[2] = findViewById(R.id.addressBtn3);

        //Return_info_DB친구 정보 리턴
        Return_info_DB info = new Return_info_DB(userName); //사용자 정보 객체 생성
        info.start();
        while(info.flag == false){}
        List<String> addressList = info.get_address(); //리스트로 리턴 됨 .get(0~2)



        for(int i=0; i<3; i++){ //주소는 최대 3개까지
            String address = addressList.get(i);
            System.out.println("주소"+address);
            Log.d("주소",address);
            addressBtn[i].setVisibility(View.VISIBLE);
            addressBtn[i].setText(address);

            addressBtn[i].setOnClickListener(new View.OnClickListener() {  //사용자 주소 버튼 눌렀을 때
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(HomeActivity.this, UserAddressActivity.class);
                    intent.putExtra("address", address);
                    startActivity(intent);
                }
            });

            if(i+1 != 3){ //get(i+1) 오버플로우 안나기 위해서
                if(addressList.get(i+1).equals("null")){ //다음 주소가 null이면
                    break;
                }
            }
        }


        //***************친구목록********************
        //Friends_DB 친구 아이디 리스트 (수정)
        friends = new Friends_DB(userId);
        friends.start();
        while(friends.flag == false){}
        list = friends.list;
        //list에 친구목록 담겨있음 사용자 id 외에 나머지는 바꿀필요 X


        friendBtn = new ImageButton[4];
        friendName = new TextView[4];

        friendBtn[0] = findViewById(R.id.friendBtn1);
        friendBtn[1] = findViewById(R.id.friendBtn2);
        friendBtn[2] = findViewById(R.id.friendBtn3);
        friendBtn[3] = findViewById(R.id.friendBtn4);

        friendName[0] = findViewById(R.id.friendName1);
        friendName[1] = findViewById(R.id.friendName2);
        friendName[2] = findViewById(R.id.friendName3);
        friendName[3] = findViewById(R.id.friendName4);



        for(int i=0; i<list.size(); i++){ //친구 수 만큼 반복
            String name = list.get(i);
            friendBtn[i].setVisibility(View.VISIBLE); //친구 목록 버튼 보이게
            friendName[i].setVisibility(View.VISIBLE); //친구 목록 텍스트 보이게

            friendName[i].setText(name); //친구 이름 목록에 띄우기


           friendBtn[i].setOnClickListener(new View.OnClickListener() {  //i번째 친구 버튼 눌렀을 때
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(HomeActivity.this, FriendSendActivity.class);
                    intent.putExtra("name", name); //친구 이름 전달
                    startActivity(intent);
                }
            });
        }


    }
}