package com.example.phoneapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SubActivity extends AppCompatActivity {

    Button btn_prev;
    Button btn_enter;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        btn_prev = (Button)findViewById(R.id.btn_prev);
        btn_prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //이전 페이지로 화면전환
                Intent intent = new Intent(SubActivity.this, MainActivity.class);

                startActivity( intent );
            }
        });

        btn_enter = (Button)findViewById(R.id.btn_enter);
        btn_enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //다음페이지로 화면을 전환
                //화면을 전환할때 사용하는 클래스 Intent
                Intent intent =
                        new Intent(
                                SubActivity.this, SubActivity2.class);

                //화면전환하기
                startActivity(intent);
            }
        });
    }
}
