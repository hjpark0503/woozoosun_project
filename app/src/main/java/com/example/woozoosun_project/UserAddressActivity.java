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

import java.io.ByteArrayOutputStream;
import java.util.List;

public class UserAddressActivity extends AppCompatActivity {

    String address;
    TextView userAddress;

    List<String> name; //제품이름
    List<String> price; //가격
    List<Bitmap> image; //사진

    TextView[] bestText, newText;
    LinearLayout[] bestLayout, newLayout;
    ImageView[] bestImage, newImage;

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

            bestImage[i].setImageBitmap(itemImage); //제품 이미지
            bestText[i].setText(itemName+"\n"+itemPrice+"원"); //제품 이름과 가격
            bestLayout[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   // ByteArrayOutputStream stream = new ByteArrayOutputStream();
                   // itemImage.compress(Bitmap.CompressFormat.PNG, 100, stream);
                   // byte[] itemImageBytes = stream.toByteArray();

                    Intent intent = new Intent(UserAddressActivity.this, ItemActivity.class);
                    intent.putExtra("itemName", itemName);
                    intent.putExtra("itemPrice", itemPrice);
                    //intent.putExtra("itemImage", itemImage); //바로 보내면 용량이 커서 에러남
                    //intent.putExtra("itemImageBytes", itemImageBytes);
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

                    //ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    //itemImage.compress(Bitmap.CompressFormat.PNG, 100, stream);
                    //byte[] itemImageBytes = stream.toByteArray();

                    Intent intent = new Intent(UserAddressActivity.this, ItemActivity.class);
                    intent.putExtra("itemName", itemName);
                    intent.putExtra("itemPrice", itemPrice);
                    //intent.putExtra("itemImage", itemImage); //바로 보내면 용량이 커서 에러남
                    //intent.putExtra("itemImageBytes", itemImageBytes);
                    startActivity(intent);
                }
            });

        }

    }
}