package com.example.uitstudy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Seminar_4th_activitychange extends AppCompatActivity {

    TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seminar_4th_activitychange);

        result = (TextView)findViewById(R.id.result);

        Intent intent = getIntent();

        String name = intent.getExtras().getString("name");
        int cost = intent.getExtras().getInt("cost");

        result.setText(name+"의 가격은 "+Integer.toString(cost)+"원 입니다.");

    }
}
