package com.example.phoneapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btn1, btn2, btn_next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //버튼 검색
        btn1 = (Button)findViewById(R.id.btn1);
        btn2 = (Button)findViewById(R.id.btn2);

        //버튼에 클릭이벤트 감지자 등록
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //버튼의 이벤트가 감지되었을 때 호출되는 메서드
                //버튼이 클릭되었을때 하고자하는 작업을 이곳에서 한다.
                Toast.makeText( getApplicationContext(), "BTN1 !!", Toast.LENGTH_SHORT).show();
            }
        });
        btn2.setOnClickListener( click );

        btn_next = (Button)findViewById(R.id.btn_next);
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //다음페이지로 화면을 전환
                //화면을 전환할때 사용하는 클래스 Intent
                Intent intent =
                        new Intent(
                                MainActivity.this, SubActivity.class);

                //화면전환하기
                startActivity(intent);
            }
        });
    }

    View.OnClickListener click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText( getApplicationContext(), "BTN2 !!", Toast.LENGTH_SHORT).show();
        }
    };
}
