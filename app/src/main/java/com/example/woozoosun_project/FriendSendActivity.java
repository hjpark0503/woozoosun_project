package com.example.woozoosun_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class FriendSendActivity extends AppCompatActivity {

    String friendName; //친구 이름
    TextView selectFriendName;
    List<String> addressList; //친구의 주소목록

    Button[] friendAddressBtn;
    TextView friendAddress;


    List<String> name; //제품이름
    List<String> price; //가격
    List<Bitmap> image; //사진

    TextView[] bestText, newText;
    LinearLayout[] bestLayout, newLayout;
    ImageView[] bestImage, newImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_send);

        Intent intent = getIntent();
        friendName = intent.getStringExtra("name"); //친구 이름

        selectFriendName = findViewById(R.id.selectFriendName);
        selectFriendName.setText("친구 "+ friendName+" 님의 우주선");

        /*
        //Return_info_DB친구 정보 리턴
        Return_info_DB info = new Return_info_DB(friendName); //친구 정보 객체 생성
        info.start();
        while(info.flag == false){}
        addressList = info.get_address(); //리스트로 리턴 됨 .get(0~2) 친구의 주소목록
        */

        friendAddressBtn = new Button[3];
        friendAddressBtn[0] = findViewById(R.id.friendAddressBtn1);
        friendAddressBtn[1] = findViewById(R.id.friendAddressBtn2);
        friendAddressBtn[2] = findViewById(R.id.friendAddressBtn3);

        //Return_info_DB친구 정보 리턴
        Return_info_DB friendInfo = new Return_info_DB(friendName); //사용자 정보 객체 생성
        friendInfo.start();
        while(friendInfo.flag == false){}
        friendInfo.flag = false;
        addressList = friendInfo.get_address(); //리스트로 리턴 됨 .get(0~2)


        friendAddress = findViewById(R.id.friendAddress);
        friendAddress.setText(addressList.get(0)); //디폴트로 첫번째 주소로 보여주기

        for(int i=0; i<3; i++){ //주소는 최대 3개까지
            String address = addressList.get(i);
            System.out.println("주소 : "+address);
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


        //new_list 띄우기방법
        New_item_DB itemDB = new New_item_DB();
        itemDB.start();
        while(itemDB.flag == false){}
        itemDB.flag = false;
        name = itemDB.name; //제품이름
        price = itemDB.price; //가격
        image = itemDB.image; //사진
        //인덱스별로 제품 똑같음!

        bestLayout = new LinearLayout[5];
        bestText = new TextView[5];
        bestImage = new ImageView[5];

        bestLayout[0] = findViewById(R.id.bestLayout);
        bestLayout[1] = findViewById(R.id.bestLayout2);
        bestLayout[2] = findViewById(R.id.bestLayout3);
        bestLayout[3] = findViewById(R.id.bestLayout4);
        bestLayout[4] = findViewById(R.id.bestLayout5);

        bestText[0] = findViewById(R.id.bestText);
        bestText[1] = findViewById(R.id.bestText2);
        bestText[2] = findViewById(R.id.bestText3);
        bestText[3] = findViewById(R.id.bestText4);
        bestText[4] = findViewById(R.id.bestText5);

        bestImage[0] = findViewById(R.id.bestImage);
        bestImage[1] = findViewById(R.id.bestImage2);
        bestImage[2] = findViewById(R.id.bestImage3);
        bestImage[3] = findViewById(R.id.bestImage4);
        bestImage[4] = findViewById(R.id.bestImage5);


        //Best list
        for (int i = 0; i < 5; i++) { //친구 수 만큼 반복
            String itemName = name.get(i);
            String itemPrice = price.get(i);
            Bitmap itemImage = image.get(i);

            bestImage[i].setImageBitmap(itemImage); //제품 이미지
            bestText[i].setText(itemName+"\n"+itemPrice+"원"); //제품 이름과 가격
            bestLayout[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(FriendSendActivity.this, ItemActivity.class);
                    intent.putExtra("itemName", itemName);
                    startActivity(intent);
                }
            });

        }


        newLayout = new LinearLayout[5];
        newText = new TextView[5];
        newImage = new ImageView[5];

        newLayout[0] = findViewById(R.id.newLayout);
        newLayout[1] = findViewById(R.id.newLayout2);
        newLayout[2] = findViewById(R.id.newLayout3);
        newLayout[3] = findViewById(R.id.newLayout4);
        newLayout[4] = findViewById(R.id.newLayout5);

        newText[0] = findViewById(R.id.newText);
        newText[1] = findViewById(R.id.newText2);
        newText[2] = findViewById(R.id.newText3);
        newText[3] = findViewById(R.id.newText4);
        newText[4] = findViewById(R.id.newText5);

        newImage[0] = findViewById(R.id.newImage);
        newImage[1] = findViewById(R.id.newImage2);
        newImage[2] = findViewById(R.id.newImage3);
        newImage[3] = findViewById(R.id.newImage4);
        newImage[4] = findViewById(R.id.newImage5);

        //New list의 버튼들
        for (int i = 0; i < 5; i++) { //친구 수 만큼 반복
            String itemName = name.get(i);
            String itemPrice = price.get(i);
            Bitmap itemImage = image.get(i);

            newImage[i].setImageBitmap(itemImage); //제품 이미지
            newText[i].setText(itemName+"\n"+itemPrice+"원"); //제품 이름과 가격
            newLayout[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(FriendSendActivity.this, ItemActivity.class);
                    intent.putExtra("itemName", itemName);
                    intent.putExtra("itemPrice", itemPrice);
                    startActivity(intent);
                }
            });

        }



    }
}