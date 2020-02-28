package com.example.uitstudy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class Seminar_5th_Homework_Bill extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seminar_5th__homework__bill);

        Intent intent = getIntent();
        String [] orderlist = intent.getExtras().getStringArray("orderlist");

    }
}
