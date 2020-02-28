package com.example.uitstudy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class Seminar_3rd_ImageTest extends AppCompatActivity {

    Button btn1;
    ImageView flower;
    ImageButton imagebtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seminar_3rd__image_test);

        btn1 = (Button)findViewById(R.id.btn1);
        flower = (ImageView)findViewById(R.id.flower);
        imagebtn = (ImageButton)findViewById(R.id.imagebtn);


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flower.setImageResource(R.drawable.windflower);
                imagebtn.setImageResource(R.drawable.windflower);
            }
        });
    }
}
