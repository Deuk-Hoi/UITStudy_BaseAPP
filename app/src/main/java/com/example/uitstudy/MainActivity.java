package com.example.uitstudy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button s1, s2, s3, s4, s5, s6, s7, s8;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        s1 = (Button)findViewById(R.id.s1);
        s2 = (Button)findViewById(R.id.s2);
        s3 = (Button)findViewById(R.id.s3);
        s4 = (Button)findViewById(R.id.s4);
        s5 = (Button)findViewById(R.id.s5);
        s6 = (Button)findViewById(R.id.s6);
        s7 = (Button)findViewById(R.id.s7);
        s8 = (Button)findViewById(R.id.s8);

        Button.OnClickListener onClickListener = new Button.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                switch (view.getId())
                {
                    case R.id.s1:
                        Intent s1 = new Intent(MainActivity.this,Seminar_1st.class);
                        startActivity(s1);
                        break;
                    case R.id.s2:
                        Intent s2 = new Intent(MainActivity.this,Seminar_2nd.class);
                        startActivity(s2);
                        break;
                    case R.id.s3:
                        Intent s3 = new Intent(MainActivity.this,Seminar_3rd.class);
                        startActivity(s3);
                        break;
                    case R.id.s4:
                        Intent s4 = new Intent(MainActivity.this, Seminar_4th.class);
                        startActivity(s4);
                        break;
                    case R.id.s5:
                        Intent s5 = new Intent(MainActivity.this, Seminar_5th.class);
                        startActivity(s5);
                        break;
                    case R.id.s6:
                        Intent s6 = new Intent(MainActivity.this, Seminar_6th.class);
                        startActivity(s6);
                        break;
                    case R.id.s7:
                        Intent s7 = new Intent(MainActivity.this, Seminar_7th.class);
                        startActivity(s7);
                        break;
                    case R.id.s8:
                        Intent s8 = new Intent(MainActivity.this, Seminar_8th.class);
                        startActivity(s8);
                        break;
                }
            }
        };
        s1.setOnClickListener(onClickListener);
        s2.setOnClickListener(onClickListener);
        s3.setOnClickListener(onClickListener);
        s4.setOnClickListener(onClickListener);
        s5.setOnClickListener(onClickListener);
        s6.setOnClickListener(onClickListener);
        s7.setOnClickListener(onClickListener);
        s8.setOnClickListener(onClickListener);
    }
}
