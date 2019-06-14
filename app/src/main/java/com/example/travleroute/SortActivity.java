package com.example.travelroute;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class SortActivity extends AppCompatActivity {

    Button bt_next, bt_1, bt_2, bt_3, bt_4;
    Button bt_5, bt_6, bt_7, bt_8, bt_9, bt_10, bt_11, bt_12;
    Button bt_13, bt_14, bt_15, bt_16, bt_17, bt_18, bt_19;
    Button bt_20, bt_21, bt_22, bt_23, bt_24;
    boolean [] response;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sort);

        bt_1 = (Button)findViewById(R.id.bt_1);
        bt_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //다음페이지로 화면을 전환
                //화면을 전환할때 사용하는 클래스 Intent
                Intent intent =
                        new Intent(
                                SortActivity.this, SubActivity3.class);

                //화면전환하기
                intent.putExtra("category",bt_1.getText().toString());
                startActivity(intent);
            }
        });

        bt_2 = (Button)findViewById(R.id.bt_2);
        bt_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //다음페이지로 화면을 전환
                //화면을 전환할때 사용하는 클래스 Intent
                Intent intent =
                        new Intent(
                                SortActivity.this, SubActivity3.class);

                //화면전환하기
                intent.putExtra("category",bt_2.getText().toString());
                startActivity(intent);
            }
        });

        bt_3 = (Button)findViewById(R.id.bt_3);
        bt_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //다음페이지로 화면을 전환
                //화면을 전환할때 사용하는 클래스 Intent
                Intent intent =
                        new Intent(
                                SortActivity.this, SubActivity3.class);

                //화면전환하기
                intent.putExtra("category",bt_3.getText().toString());
                startActivity(intent);
            }
        });

        bt_4 = (Button)findViewById(R.id.bt_4);
        bt_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //다음페이지로 화면을 전환
                //화면을 전환할때 사용하는 클래스 Intent
                Intent intent =
                        new Intent(
                                SortActivity.this, SubActivity3.class);

                //화면전환하기
                intent.putExtra("category",bt_4.getText().toString());
                startActivity(intent);
            }
        });
        bt_5 = (Button)findViewById(R.id.bt_5);
        bt_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //다음페이지로 화면을 전환
                //화면을 전환할때 사용하는 클래스 Intent
                Intent intent =
                        new Intent(
                                SortActivity.this, SubActivity3.class);

                //화면전환하기
                intent.putExtra("category",bt_5.getText().toString());
                startActivity(intent);
            }
        });
        bt_6 = (Button)findViewById(R.id.bt_6);
        bt_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //다음페이지로 화면을 전환
                //화면을 전환할때 사용하는 클래스 Intent
                Intent intent =
                        new Intent(
                                SortActivity.this, SubActivity3.class);

                //화면전환하기
                intent.putExtra("category",bt_6.getText().toString());
                startActivity(intent);
            }
        });
        bt_7 = (Button)findViewById(R.id.bt_7);
        bt_7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //다음페이지로 화면을 전환
                //화면을 전환할때 사용하는 클래스 Intent
                Intent intent =
                        new Intent(
                                SortActivity.this, SubActivity3.class);

                //화면전환하기
                intent.putExtra("category",bt_7.getText().toString());
                startActivity(intent);
            }
        });
        bt_8 = (Button)findViewById(R.id.bt_8);
        bt_8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //다음페이지로 화면을 전환
                //화면을 전환할때 사용하는 클래스 Intent
                Intent intent =
                        new Intent(
                                SortActivity.this, SubActivity3.class);

                //화면전환하기
                intent.putExtra("category",bt_8.getText().toString());
                startActivity(intent);
            }
        });
        bt_9 = (Button)findViewById(R.id.bt_9);
        bt_9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //다음페이지로 화면을 전환
                //화면을 전환할때 사용하는 클래스 Intent
                Intent intent =
                        new Intent(
                                SortActivity.this, SubActivity3.class);

                //화면전환하기
                intent.putExtra("category",bt_9.getText().toString());
                startActivity(intent);
            }
        });
        bt_10 = (Button)findViewById(R.id.bt_10);
        bt_10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //다음페이지로 화면을 전환
                //화면을 전환할때 사용하는 클래스 Intent
                Intent intent =
                        new Intent(
                                SortActivity.this, SubActivity3.class);

                //화면전환하기
                intent.putExtra("category",bt_10.getText().toString());
                startActivity(intent);
            }
        });
        bt_11 = (Button)findViewById(R.id.bt_11);
        bt_11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //다음페이지로 화면을 전환
                //화면을 전환할때 사용하는 클래스 Intent
                Intent intent =
                        new Intent(
                                SortActivity.this, SubActivity3.class);

                //화면전환하기
                intent.putExtra("category",bt_11.getText().toString());
                startActivity(intent);
            }
        });
        bt_12 = (Button)findViewById(R.id.bt_12);
        bt_12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //다음페이지로 화면을 전환
                //화면을 전환할때 사용하는 클래스 Intent
                Intent intent =
                        new Intent(
                                SortActivity.this, SubActivity3.class);

                //화면전환하기
                intent.putExtra("category",bt_12.getText().toString());
                startActivity(intent);
            }
        });
        bt_13 = (Button)findViewById(R.id.bt_13);
        bt_13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //다음페이지로 화면을 전환
                //화면을 전환할때 사용하는 클래스 Intent
                Intent intent =
                        new Intent(
                                SortActivity.this, SubActivity3.class);

                //화면전환하기
                intent.putExtra("category",bt_13.getText().toString());
                startActivity(intent);
            }
        });
        bt_14 = (Button)findViewById(R.id.bt_14);
        bt_14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //다음페이지로 화면을 전환
                //화면을 전환할때 사용하는 클래스 Intent
                Intent intent =
                        new Intent(
                                SortActivity.this, SubActivity3.class);

                //화면전환하기
                intent.putExtra("category",bt_14.getText().toString());
                startActivity(intent);
            }
        });
        bt_15 = (Button)findViewById(R.id.bt_15);
        bt_15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //다음페이지로 화면을 전환
                //화면을 전환할때 사용하는 클래스 Intent
                Intent intent =
                        new Intent(
                                SortActivity.this, SubActivity3.class);

                //화면전환하기
                intent.putExtra("category",bt_15.getText().toString());
                startActivity(intent);
            }
        });
        bt_16 = (Button)findViewById(R.id.bt_16);
        bt_16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //다음페이지로 화면을 전환
                //화면을 전환할때 사용하는 클래스 Intent
                Intent intent =
                        new Intent(
                                SortActivity.this, SubActivity3.class);

                //화면전환하기
                intent.putExtra("category",bt_16.getText().toString());
                startActivity(intent);
            }
        });
        bt_17 = (Button)findViewById(R.id.bt_17);
        bt_17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //다음페이지로 화면을 전환
                //화면을 전환할때 사용하는 클래스 Intent
                Intent intent =
                        new Intent(
                                SortActivity.this, SubActivity3.class);

                //화면전환하기
                intent.putExtra("category",bt_17.getText().toString());
                startActivity(intent);
            }
        });
        bt_18 = (Button)findViewById(R.id.bt_18);
        bt_18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //다음페이지로 화면을 전환
                //화면을 전환할때 사용하는 클래스 Intent
                Intent intent =
                        new Intent(
                                SortActivity.this, SubActivity3.class);

                //화면전환하기
                intent.putExtra("category",bt_18.getText().toString());
                startActivity(intent);
            }
        });
        bt_19 = (Button)findViewById(R.id.bt_19);
        bt_19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //다음페이지로 화면을 전환
                //화면을 전환할때 사용하는 클래스 Intent
                Intent intent =
                        new Intent(
                                SortActivity.this, SubActivity3.class);

                //화면전환하기
                intent.putExtra("category",bt_19.getText().toString());
                startActivity(intent);
            }
        });
        bt_20 = (Button)findViewById(R.id.bt_20);
        bt_20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //다음페이지로 화면을 전환
                //화면을 전환할때 사용하는 클래스 Intent
                Intent intent =
                        new Intent(
                                SortActivity.this, SubActivity3.class);

                //화면전환하기
                intent.putExtra("category",bt_20.getText().toString());
                startActivity(intent);
            }
        });
        bt_21 = (Button)findViewById(R.id.bt_21);
        bt_21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //다음페이지로 화면을 전환
                //화면을 전환할때 사용하는 클래스 Intent
                Intent intent =
                        new Intent(
                                SortActivity.this, SubActivity3.class);

                //화면전환하기
                intent.putExtra("category",bt_21.getText().toString());
                startActivity(intent);
            }
        });
        bt_22 = (Button)findViewById(R.id.bt_22);
        bt_22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //다음페이지로 화면을 전환
                //화면을 전환할때 사용하는 클래스 Intent
                Intent intent =
                        new Intent(
                                SortActivity.this, SubActivity3.class);

                //화면전환하기
                intent.putExtra("category",bt_22.getText().toString());
                startActivity(intent);
            }
        });
        bt_23 = (Button)findViewById(R.id.bt_23);
        bt_23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //다음페이지로 화면을 전환
                //화면을 전환할때 사용하는 클래스 Intent
                Intent intent =
                        new Intent(
                                SortActivity.this, SubActivity3.class);

                //화면전환하기
                intent.putExtra("category",bt_23.getText().toString());
                startActivity(intent);
            }
        });
        bt_24 = (Button)findViewById(R.id.bt_24);
        bt_24.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //다음페이지로 화면을 전환
                //화면을 전환할때 사용하는 클래스 Intent
                Intent intent =
                        new Intent(
                                SortActivity.this, SubActivity3.class);

                //화면전환하기
                intent.putExtra("category",bt_24.getText().toString());
                startActivity(intent);
            }
        });
        
    }

    /*private Button.OnClickListener btnClickListner = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (!(radio_k.isChecked() || radio_c.isChecked() || radio_j.isChecked() || radio_u.isChecked()) && view.getId() == R.id.bt_next)
                Toast.makeText(SortActivity.this, "please check", Toast.LENGTH_SHORT).show();
        }
    };*/
}

