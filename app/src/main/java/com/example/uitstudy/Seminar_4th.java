package com.example.uitstudy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Seminar_4th extends AppCompatActivity {

    Button apple, web, scroll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seminar_4th);

        apple = (Button)findViewById(R.id.apple);
        web = (Button)findViewById(R.id.web);
        scroll = (Button)findViewById(R.id.scroll);

        Button.OnClickListener onClickListener = new Button.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                switch (view.getId())
                {
                    case R.id.apple:
                        Intent apple = new Intent(Seminar_4th.this, Seminar_4th_activitychange.class);
                        apple.putExtra("name","사과");
                        apple.putExtra("cost", 1000);
                        startActivity(apple);
                        break;
                    case R.id.web:
                        Intent web = new Intent(Seminar_4th.this, Seminar_4th_web.class);
                        startActivity(web);
                        break;
                    case R.id.scroll:
                        Intent scroll = new Intent(Seminar_4th.this, Seminar_4th_scroll.class);
                        startActivity(scroll);
                        break;
                }
            }
        };

        apple.setOnClickListener(onClickListener);
        web.setOnClickListener(onClickListener);
        scroll.setOnClickListener(onClickListener);
    }
}
