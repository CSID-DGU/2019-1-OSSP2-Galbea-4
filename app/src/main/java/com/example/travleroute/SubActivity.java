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

public class SubActivity extends AppCompatActivity {

    Button btn_prev;
    Button btn_1, btn_2, btn_3;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);


        btn_prev = (Button)findViewById(R.id.bt_prev);
        btn_prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //이전 페이지로 화면전환
                Intent intent = new Intent(SubActivity.this, MainActivity.class);

                startActivity( intent );
            }
        });

        btn_1 = (Button)findViewById(R.id.btn_1);
        btn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //다음페이지로 화면을 전환
                //화면을 전환할때 사용하는 클래스 Intent
                Intent intent =
                        new Intent(
                                SubActivity.this, MapActivity.class);

                //화면전환하기
                startActivity(intent);
            }
        });

        btn_2 = (Button)findViewById(R.id.btn_2);
        btn_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //다음페이지로 화면을 전환
                //화면을 전환할때 사용하는 클래스 Intent
                Intent intent =
                        new Intent(
                                SubActivity.this, MapActivity.class);

                //화면전환하기
                startActivity(intent);
            }
        });

        btn_3 = (Button)findViewById(R.id.btn_3);
        btn_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //다음페이지로 화면을 전환
                //화면을 전환할때 사용하는 클래스 Intent
                Intent intent =
                        new Intent(
                                SubActivity.this, MapActivity.class);

                //화면전환하기
                startActivity(intent);
            }
        });
    }
}
