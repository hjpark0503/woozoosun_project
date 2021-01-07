package com.example.woozoosun_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ItemActivity extends AppCompatActivity {
    String itemName, itemPrice, itemBrand, itemInfo;
    Bitmap itemImage;

    ImageView topImage, bottomImage;
    TextView itemBrandText, itemNameText, itemPriceText, itemInfoText;
    ImageButton itemHeartBtn;
    boolean isHeart = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        Intent intent = getIntent();
        itemName = intent.getStringExtra("itemName");

        topImage = findViewById(R.id.topImage);
        bottomImage = findViewById(R.id.bottomImage);

        itemBrandText = findViewById(R.id.itemBrandText);
        itemNameText = findViewById(R.id.itemNameText);
        itemPriceText = findViewById(R.id.itemPriceText);
        itemInfoText = findViewById(R.id.itemInfoText);


        //제품 정보
        Item_Info_DB test = new Item_Info_DB(itemName);
        test.start();
        while(test.flag == false){}
        test.flag = false;
        itemBrand = test.brand; //브랜드 이름
        itemPrice = test.price; //가격
        itemImage = test.photo; //사진
        itemInfo = test.comment; //설명
        //test.name // 제품이름(필요하면 사용)



        itemNameText.setText(itemName); //제품 이름
        itemPriceText.setText(itemPrice+"원"); //제품 가격
        itemBrandText.setText(itemBrand); //제품 브랜드
        itemInfoText.setText(itemInfo); //제품 설명
        topImage.setImageBitmap(itemImage); //메인 제품 이미지
        bottomImage.setImageBitmap(itemImage); //아래 제품 이미지


        itemHeartBtn = findViewById(R.id.itemHeartBtn);
        itemHeartBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(isHeart == false){
                    itemHeartBtn.setImageResource(R.drawable.heart2);
                    Toast.makeText(getApplicationContext(), "찜 ! ", Toast.LENGTH_SHORT).show();
                    isHeart = true;
                }
                else{
                    itemHeartBtn.setImageResource(R.drawable.heartgray);
                    Toast.makeText(getApplicationContext(), "찜 해제", Toast.LENGTH_SHORT).show();
                    isHeart = false;
                }
            }
        });
    }
}