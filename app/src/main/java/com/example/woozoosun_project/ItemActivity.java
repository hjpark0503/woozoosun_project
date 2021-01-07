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
    String itemName;
    String itemPrice;
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
        itemPrice = intent.getStringExtra("itemPrice");

        //byte[] itemImageBytes = intent.getByteArrayExtra("itemImageBytes");
       // itemImage = BitmapFactory.decodeByteArray(itemImageBytes, 0, itemImageBytes.length);

        topImage = findViewById(R.id.topImage);
        bottomImage = findViewById(R.id.bottomImage);

        itemBrandText = findViewById(R.id.itemBrandText);
        itemNameText = findViewById(R.id.itemNameText);
        itemPriceText = findViewById(R.id.itemPriceText);
        itemInfoText = findViewById(R.id.itemInfoText);

        itemNameText.setText(itemName);
        itemPriceText.setText(itemPrice+"원");
        //topImage.setImageBitmap(itemImage); //메인 제품 이미지
        // bottomImage.setImageBitmap(itemImage); //아래 제품 이미지


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