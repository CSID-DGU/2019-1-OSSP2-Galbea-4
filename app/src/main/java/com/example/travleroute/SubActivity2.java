package com.example.phoneapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SubActivity2 extends AppCompatActivity {

    Button btn_prev1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub3);

        btn_prev1 = (Button)findViewById(R.id.btn_prev1);
        btn_prev1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //이전 페이지로 화면전환
                Intent intent = new Intent(SubActivity2.this, SubActivity.class);

                startActivity( intent );
            }
        });

    }
}
