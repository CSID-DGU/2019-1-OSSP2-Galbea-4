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

public class SubActivity4 extends AppCompatActivity {

    Button btn_prev;
    Button btn_d1, btn_d2, btn_d3;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub4);


        btn_prev = (Button)findViewById(R.id.bt_prev);
        btn_prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //이전 페이지로 화면전환
                Intent intent = new Intent(SubActivity4.this, MainActivity.class);

                startActivity( intent );
            }
        });

        btn_d1 = (Button)findViewById(R.id.btn_d1);
        btn_d1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //다음페이지로 화면을 전환
                //화면을 전환할때 사용하는 클래스 Intent
                Intent intent =
                        new Intent(
                                SubActivity4.this, MapActivity.class);

                //화면전환하기
                startActivity(intent);
            }
        });

        btn_d2 = (Button)findViewById(R.id.btn_d2);
        btn_d2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //다음페이지로 화면을 전환
                //화면을 전환할때 사용하는 클래스 Intent
                Intent intent =
                        new Intent(
                                SubActivity4.this, MapActivity.class);

                //화면전환하기
                startActivity(intent);
            }
        });

        btn_d3 = (Button)findViewById(R.id.btn_d3);
        btn_d3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //다음페이지로 화면을 전환
                //화면을 전환할때 사용하는 클래스 Intent
                Intent intent =
                        new Intent(
                                SubActivity4.this, MapActivity.class);

                //화면전환하기
                startActivity(intent);
            }
        });
    }
}

