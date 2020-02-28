package com.example.uitstudy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Seminar_5th extends AppCompatActivity {

    Button listview, radio, checkbox, checkbox2, shared, homework;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seminar_5th);

        listview = (Button)findViewById(R.id.listview);
        radio = (Button)findViewById(R.id.radio);
        checkbox = (Button)findViewById(R.id.checkbox);
        checkbox2 = (Button)findViewById(R.id.checkbox2);
        shared = (Button)findViewById(R.id.shared);
        homework = (Button)findViewById(R.id.homework);

        Button.OnClickListener onClickListener = new Button.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                switch(view.getId())
                {
                    case R.id.listview:
                        Intent listview = new Intent(Seminar_5th.this, Seminar_5th_Listview.class);
                        startActivity(listview);
                        break;
                    case R.id.radio:
                        Intent radio = new Intent(Seminar_5th.this, Seminar_5th_radio.class);
                        startActivity(radio);
                        break;
                    case R.id.checkbox:
                        Intent checkbox = new Intent(Seminar_5th.this,Seminar_5th_checkbox.class);
                        startActivity(checkbox);
                        break;
                    case R.id.checkbox2:
                        Intent checkbox2 = new Intent(Seminar_5th.this,Seminar_5th_checkbox2.class);
                        startActivity(checkbox2);
                        break;
                    case R.id.shared:
                        Intent shared = new Intent(Seminar_5th.this,Seminar_5th_SharedPreferences.class);
                        startActivity(shared);
                        break;
                    case R.id.homework:
                        Intent homework = new Intent(Seminar_5th.this,Seminar_5th_Homework.class);
                        startActivity(homework);
                        break;
                }
            }
        };

        listview.setOnClickListener(onClickListener);
        radio.setOnClickListener(onClickListener);
        checkbox.setOnClickListener(onClickListener);
        checkbox2.setOnClickListener(onClickListener);
        shared.setOnClickListener(onClickListener);
        homework.setOnClickListener(onClickListener);
    }
}
