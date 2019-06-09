package com.example.travelroute;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button bt_shopping, bt_food, bt_tea, bt_dessert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        bt_shopping = (Button) findViewById(R.id.bt_shopping);
        bt_food = (Button) findViewById(R.id.bt_food);
        bt_tea = (Button) findViewById(R.id.bt_tea);
        bt_dessert = (Button) findViewById(R.id.bt_dessert);


        bt_shopping.setOnClickListener(new View.OnClickListener() {
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
        bt_food.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //다음페이지로 화면을 전환
                //화면을 전환할때 사용하는 클래스 Intent

                Intent intent =
                        new Intent(
                                MainActivity.this, SortActivity.class);

                //화면전환하기
                startActivity(intent);
            }
        });
        bt_tea.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //다음페이지로 화면을 전환
                //화면을 전환할때 사용하는 클래스 Intent

                Intent intent =
                        new Intent(
                                MainActivity.this, SubActivity3.class);

                //화면전환하기
                startActivity(intent);
            }
        });
        bt_dessert.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //다음페이지로 화면을 전환
                //화면을 전환할때 사용하는 클래스 Intent

                Intent intent =
                        new Intent(
                                MainActivity.this, SubActivity4.class);

                //화면전환하기
                startActivity(intent);
            }
        });
    }
}
