package com.example.woozoosun_project;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class JoinActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        /*회원가입 DB manual
        회원가입 버튼 클릭시 실행해야함!
        Join_DB 객체 생성하고 new Join_DB(파라미터 값)
        파라미터 값 = name, id, pw, phone, address1, address2, address3 순서 (입력받은거 넣으면 됨)
        phone는 int 값이니까 -같은거 입력되면 반복문 같은걸로 제거해서 숫자만 보내기 무조건 int로!!
        address2,3은 넣지 않을 수 있지만 파라미터 전송은 무조건 해야하므로 입력값이 비워져있다면 ""으로라도 보내기!
        객체 생성 후
        .execute() 메소드를 무조건 실행 해줘야 값들이 DB로 전송됨!!!

        //그외 패스워드 2개 확인하는거, null값인지 아닌지 확인하는거는 DB에서 못하고 코드로 먼저 검사해야해욤
        //혹시 코드 다 만들면 DB에 들어갔는지 확인해야하니까 실행 해보지 말고 알려주333
         */
    }
}