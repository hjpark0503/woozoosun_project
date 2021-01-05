package com.example.woozoosun_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class ItemActivity extends AppCompatActivity {
    String itemName;
    String itemPrice;
    Bitmap itemImage;

    ImageView topImage, bottomImage;
    TextView itemBrandText, itemNameText, itemPriceText, itemInfoText;

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

    }
}