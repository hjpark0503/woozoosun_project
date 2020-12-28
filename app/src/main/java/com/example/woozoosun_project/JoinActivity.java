package com.example.woozoosun_project;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class JoinActivity extends AppCompatActivity {
    Button idCheckBtn;
    boolean idFlag=false;//false = 중복
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("시작", "join activity 시작 ");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        idCheckBtn = (Button) findViewById(R.id.join_idCheckBtn);
        idCheckBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String id;
                EditText text = (EditText) findViewById(R.id.join_id) ;
                id = text.getText().toString() ;
                idFlag = checkID(id);
            }
        });

        Button join_completeBtn = (Button) findViewById(R.id.join_completeBtn);
        join_completeBtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name, id, pw, pwCheck, phone, addr1, addr2, addr3;

                EditText text = (EditText) findViewById(R.id.join_name) ;
                name = text.getText().toString() ;
                text = (EditText) findViewById(R.id.join_id) ;
                id = text.getText().toString() ;
                text = (EditText) findViewById(R.id.join_pw) ;
                pw = text.getText().toString() ;
                text = (EditText) findViewById(R.id.join_pwCheck) ;
                pwCheck = text.getText().toString() ;
                text = (EditText) findViewById(R.id.join_phone) ;
                phone = text.getText().toString() ;
                text = (EditText) findViewById(R.id.join_address1) ;
                addr1 = text.getText().toString() ;
                text = (EditText) findViewById(R.id.join_address2) ;
                addr2 = text.getText().toString() ;
                text = (EditText) findViewById(R.id.join_address3) ;
                addr3 = text.getText().toString() ;

//                checkPhone(phone);
//                checkPW(pw,pwCheck);
//                nullCheck(id,pw,name,addr1);

//
                if(checkPhone(phone)&&checkPW(pw,pwCheck)&&nullCheck(id,pw,name,addr1)&&idFlag){
                    if(addr2==null)
                        addr2="";
                    if(addr3==null)
                        addr3="";

                    Join_DB joinDB = new Join_DB(name, id, pw, phone, addr1, addr2, addr3);
                    joinDB.execute();

                    AlertDialog.Builder builder = new AlertDialog.Builder(JoinActivity.this); // AlertDialog를 띄울 activity를 argument로 지정해야 한다.
                    builder.setTitle("회원가입이 완료되었습니다.\n"); // AlertDialog.builder를 통해 Title text를 입력
                    builder.setPositiveButton("확인", new DialogInterface.OnClickListener() { // AlertDialog.Builder에 Positive Button을 생성
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            onBackPressed();
                            //확인 버튼 클릭 시 동작
                        }
                    });
                    AlertDialog dialog = builder.create(); // 위의 builder를 생성할 AlertDialog 객체 생성
                    dialog.show(); // dialog를 화면에 뿌려 줌

                }
                if(!idFlag){
                    AlertDialog.Builder builder = new AlertDialog.Builder(JoinActivity.this); // AlertDialog를 띄울 activity를 argument로 지정해야 한다.
                    builder.setTitle("아이디 중복확인을 해주십시오."); // AlertDialog.builder를 통해 Title text를 입력
                    builder.setPositiveButton("확인", new DialogInterface.OnClickListener() { // AlertDialog.Builder에 Positive Button을 생성
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //확인 버튼 클릭 시 동작
                        }
                    });
                    AlertDialog dialog = builder.create(); // 위의 builder를 생성할 AlertDialog 객체 생성
                    dialog.show(); // dialog를 화면에 뿌려 줌
                }


            }
        });
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



    public boolean nullCheck(String id, String pw, String name, String addr1){
        if(id.equals("")||pw.equals("")||name.equals("")||addr1.equals("")){
            AlertDialog.Builder builder = new AlertDialog.Builder(JoinActivity.this); // AlertDialog를 띄울 activity를 argument로 지정해야 한다.
            builder.setTitle("정보를 모두 입력해주십시오.\n(주소2,3은 선택사항)"); // AlertDialog.builder를 통해 Title text를 입력
            builder.setPositiveButton("확인", new DialogInterface.OnClickListener() { // AlertDialog.Builder에 Positive Button을 생성
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //확인 버튼 클릭 시 동작
                }
            });
            AlertDialog dialog = builder.create(); // 위의 builder를 생성할 AlertDialog 객체 생성
            dialog.show(); // dialog를 화면에 뿌려 줌

            return false;
        }
        return true;
    }

    public boolean checkID(String id){
        ID_check idCheck = new ID_check(id);
        idCheck.execute();
        while(idCheck.flag == false){}
        idCheck.flag = false;
        String result = idCheck.result;

        if(result.equals("error")){
            idCheck.flag=false;
            AlertDialog.Builder builder = new AlertDialog.Builder(JoinActivity.this); // AlertDialog를 띄울 activity를 argument로 지정해야 한다.
            builder.setTitle("존재하는 아이디 입니다.\n다른 아이디를 입력해 주십시오."); // AlertDialog.builder를 통해 Title text를 입력
            builder.setPositiveButton("확인", new DialogInterface.OnClickListener() { // AlertDialog.Builder에 Positive Button을 생성
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //확인 버튼 클릭 시 동작
                }
            });
            AlertDialog dialog = builder.create(); // 위의 builder를 생성할 AlertDialog 객체 생성
            dialog.show(); // dialog를 화면에 뿌려 줌
            return false;
        }

        if (result.equals("pass")){
            AlertDialog.Builder builder = new AlertDialog.Builder(JoinActivity.this); // AlertDialog를 띄울 activity를 argument로 지정해야 한다.
            builder.setTitle("사용이 가능한 아이디 입니다."); // AlertDialog.builder를 통해 Title text를 입력
            builder.setPositiveButton("확인", new DialogInterface.OnClickListener() { // AlertDialog.Builder에 Positive Button을 생성
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //확인 버튼 클릭 시 동작
                }
            });
            AlertDialog dialog = builder.create(); // 위의 builder를 생성할 AlertDialog 객체 생성
            dialog.show(); // dialog를 화면에 뿌려 줌
            idFlag=true;
        }
        return true;
    }
    public boolean checkPW(String pw, String pwCheck){
        if(!pw.equals(pwCheck)){
            AlertDialog.Builder builder = new AlertDialog.Builder(JoinActivity.this); // AlertDialog를 띄울 activity를 argument로 지정해야 한다.
            builder.setTitle("비밀번호 확인을 동일하게 입력해주십시오."); // AlertDialog.builder를 통해 Title text를 입력
            builder.setPositiveButton("확인", new DialogInterface.OnClickListener() { // AlertDialog.Builder에 Positive Button을 생성
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //확인 버튼 클릭 시 동작
                }
            });
            AlertDialog dialog = builder.create(); // 위의 builder를 생성할 AlertDialog 객체 생성
            dialog.show(); // dialog를 화면에 뿌려 줌

            return false;
        }

        return true;
    }
    public boolean checkPhone(String phone){//전화번호 길이, 타입 확인해서 오류 메시지 띄움, 오류 없을 시 true 반환

        if(phone.length()!=11) {
            AlertDialog.Builder builder = new AlertDialog.Builder(JoinActivity.this); // AlertDialog를 띄울 activity를 argument로 지정해야 한다.
            builder.setTitle("전화번호를 다시 입력해주십시오."); // AlertDialog.builder를 통해 Title text를 입력
            builder.setPositiveButton("확인", new DialogInterface.OnClickListener() { // AlertDialog.Builder에 Positive Button을 생성
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    EditText phone = (EditText) findViewById(R.id.join_phone);
                    phone.setText("");
                }
            });
            AlertDialog dialog = builder.create(); // 위의 builder를 생성할 AlertDialog 객체 생성
            dialog.show(); // dialog를 화면에 뿌려 줌

            return false;
        }


        try {//int 타입이 아니면 오류 메시지
            for(int i = 0; i < 11; i++) {
                int j = Integer.parseInt(phone);
                System.out.println(j);
            }
        }
        catch(NumberFormatException e) {
            AlertDialog.Builder builder = new AlertDialog.Builder(JoinActivity.this); // AlertDialog를 띄울 activity를 argument로 지정해야 한다.
            builder.setTitle("전화번호를 다시 입력해주십시오."); // AlertDialog.builder를 통해 Title text를 입력
            builder.setPositiveButton("확인", new DialogInterface.OnClickListener() { // AlertDialog.Builder에 Positive Button을 생성
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    EditText phone = (EditText) findViewById(R.id.join_phone);
                    phone.setText("");
                }
            });
            AlertDialog dialog = builder.create(); // 위의 builder를 생성할 AlertDialog 객체 생성
            dialog.show(); // dialog를 화면에 뿌려 줌
            return false;
        }

        return true;

    }
}