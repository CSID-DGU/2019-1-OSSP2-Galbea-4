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

    Button bt_food;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bt_food = (Button) findViewById(R.id.bt_food);

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
    }
}
