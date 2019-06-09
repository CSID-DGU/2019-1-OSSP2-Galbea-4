package com.example.travelroute;

import android.content.Intent;
import android.graphics.Color;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

public class SubActivity3 extends AppCompatActivity {

    Button btn_prev;
    Button btn_t1, btn_t2, btn_t3;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub3);


        btn_prev = (Button)findViewById(R.id.bt_prev);
        btn_prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //이전 페이지로 화면전환
                Intent intent = new Intent(SubActivity3.this, MainActivity.class);

                startActivity( intent );
            }
        });

        btn_t1 = (Button)findViewById(R.id.btn_t1);
        btn_t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //다음페이지로 화면을 전환
                //화면을 전환할때 사용하는 클래스 Intent
                Intent intent =
                        new Intent(
                                SubActivity3.this, MapActivity.class);

                //화면전환하기
                startActivity(intent);
            }
        });

        btn_t2 = (Button)findViewById(R.id.btn_t2);
        btn_t2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //다음페이지로 화면을 전환
                //화면을 전환할때 사용하는 클래스 Intent
                Intent intent =
                        new Intent(
                                SubActivity3.this, MapActivity.class);

                //화면전환하기
                startActivity(intent);
            }
        });

        btn_t3 = (Button)findViewById(R.id.btn_t3);
        btn_t3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //다음페이지로 화면을 전환
                //화면을 전환할때 사용하는 클래스 Intent
                Intent intent =
                        new Intent(
                                SubActivity3.this, MapActivity.class);

                //화면전환하기
                startActivity(intent);
            }
        });
    }
}
