package com.example.woozoosun_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class UserAddressActivity extends AppCompatActivity {

    String address;
    TextView userAddress;

    List<String> name; //제품이름
    List<String> price; //가격
    List<Bitmap> image; //사진

    Button[] bestBtn;
    Button[] newBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_address);


        Intent intent = getIntent();
        address = intent.getStringExtra("address"); //사용자가 클릭한 주소

        userAddress = findViewById(R.id.userAddress);
        userAddress.setText(address);


        /*
        //new_list 띄우기방법
        Store_Image_DB test = new Store_Image_DB();
        test.start();
        while(test.flag == false){}
        test.flag = false;
        List<String> name = test.name; //제품이름
        List<String> price = test.price; //가격
        List<Bitmap> image = test.image; //사진
        //인덱스별로 제품 똑같음!
         */



        //new_list 띄우기방법
        New_item_DB itemDB = new New_item_DB();
        itemDB.start();
        while(itemDB.flag == false){}
        itemDB.flag = false;
        name = itemDB.name; //제품이름
        price = itemDB.price; //가격
        image = itemDB.image; //사진
        //인덱스별로 제품 똑같음!


        /*
        bestBtn = new Button[5];
        newBtn = new Button[5];

        bestBtn[0] = findViewById(R.id.bestBtn);
        bestBtn[1] = findViewById(R.id.bestBtn2);
        bestBtn[2] = findViewById(R.id.bestBtn3);
        bestBtn[3] = findViewById(R.id.bestBtn4);
        bestBtn[4] = findViewById(R.id.bestBtn5);

        newBtn[0] = findViewById(R.id.newBtn);
        newBtn[1] = findViewById(R.id.newBtn2);
        newBtn[2] = findViewById(R.id.newBtn3);
        newBtn[3] = findViewById(R.id.newBtn4);
        newBtn[4] = findViewById(R.id.newBtn5);


        //Best list의 버튼들
        for (int i = 0; i < 5; i++) { //친구 수 만큼 반복
            String itemName = name.get(i);
            String itemPrice = price.get(i);
            Bitmap itemImage = image.get(i);


            bestBtn[i].setText(itemName+"\n"+itemPrice);
            bestBtn[i].setD
            bestBtn[i].setOnClickListener(new View.OnClickListener() {  //i번째 친구 버튼 눌렀을 때
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(UserAddressActivity.this, ItemActivity.class);
                   // intent.putExtra("name", name); //친구 이름 전달
                    startActivity(intent);
                }
            });
        }

        //New list의 버튼들
*/
    }
}