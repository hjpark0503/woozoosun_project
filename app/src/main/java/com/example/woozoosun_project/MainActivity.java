package com.example.woozoosun_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editTextPersonName, editTextPassword;
    String userId, userPw, userName;

    Button loginBtn, joinBtn, idBtn, pwBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextPersonName = (EditText)findViewById(R.id.editTextPersonName);
        editTextPassword = (EditText)findViewById(R.id.editTextPassword);

        loginBtn = findViewById(R.id.loginBtn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                userId = editTextPersonName.getText().toString(); //사용자가 입력한 아이디
                System.out.println("사용자 입력 아이디"+userId);

                userPw = editTextPassword.getText().toString(); //사용자가 입력한 비밀번호
                System.out.println("사용자 입력 비밀번호"+userPw);

                //Login_DB 로그인
                Login_DB test = new Login_DB(userId, userPw);
                test.execute(); //이거 꼭 해줘야 디비 데이터랑 비교가능
                while(test.flag == false){}
                test.flag = false;
                userName = test.get_name(); //로그인한 유저 이름 => 성공시 이름, 실패시 null
                System.out.println("사용자 유저 이름"+userName);

                if(userName != null){ //로그인 성공하면
                    Log.d("유저이름", userName);
                    Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                    intent.putExtra("userId", userId); //사용자 아이디와 함께 화면전환
                    intent.putExtra("userName", userName); //사용자 이름과 함께 화면 전환
                    startActivity(intent);
                }
                else{ //로그인 실패하면
                    Toast.makeText(getApplicationContext(), "입력정보가 올바르지 않습니다.", Toast.LENGTH_SHORT).show();
                    //토스트 메세지 띄우기
                }

                //id, pw 길이 검사 등은 다 코드에서 해야함
                //Login_DB 클래스는 오로지 id, pw를 디비와 비교해서 일치하는지 안하는지만 check
                //로그인 성공 실패 확인은 get_name()==null 이면 실패, 아니면 성공임(성공하면 이름이 리턴됨)

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