package com.example.woozoosun_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class UserAddressActivity extends AppCompatActivity {

    String address;
    TextView userAddress;

    List<String> name; //제품이름
    List<String> price; //가격
    List<Bitmap> image; //사진

    TextView[] bestText;
    LinearLayout[] bestLayout;
    ImageView[] bestImage;

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

            bestImage[i].setImageBitmap(image.get(i)); //제품 이미지
            bestText[i].setText(itemName+"\n"+itemPrice); //제품 이름과 가격
            bestLayout[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(UserAddressActivity.this, ItemActivity.class);
                    // intent.putExtra("name", name); //친구 이름 전달
                    startActivity(intent);
                }
            });

        }

        //New list의 버튼들

    }
}