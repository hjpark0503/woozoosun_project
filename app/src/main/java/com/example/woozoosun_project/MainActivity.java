package com.example.woozoosun_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editTextPersonName, editTextPassword;
    String userId, userPw;

    Button loginBtn, joinBtn, idBtn, pwBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextPersonName = findViewById(R.id.editTextPersonName);
        userId = editTextPersonName.getText().toString(); //사용자가 입력한 아이디

        editTextPassword = findViewById(R.id.editTextPassword);
        userPw = editTextPassword.getText().toString(); //사용자가 입력한 비밀번호

        loginBtn = findViewById(R.id.loginBtn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*로그인 버튼 눌렀을 경우 회원정보가 DB에 있는지 확인 해야함
                if(입력아이디가 DB에 존재 && DB에 저장된 아이디의 비번과 입력 비번이 동일){
                   밑에 코드 여기에 복붙
                }
                else{
                   Toast.makeText(getApplicationContext(), "입력정보가 올바르지 않습니다.", Toast.LENGTH_SHORT).show();
                    //토스트 메세지 띄우기
                }
                 */

                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                intent.putExtra("userId",userId); //사용자 아이디와 함께 화면전환
                startActivity(intent);
            }
        });

        joinBtn = findViewById(R.id.joinBtn);
        joinBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, JoinActivity.class);
                startActivity(intent);
            }
        });

        idBtn = findViewById(R.id.idBtn);
        idBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SearchIDActivity.class);
                startActivity(intent);
            }
        });

        pwBtn =  findViewById(R.id.pwBtn);
        pwBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SearchPWActivity.class);
                startActivity(intent);
            }
        });
    }
}