package com.example.uitstudy;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Seminar_3rd_Homework extends AppCompatActivity {

    Button up, down;
    ImageView first, second;
    int []image = {R.drawable.daram, R.drawable.bird, R.drawable.letona};
    int i = 0, count = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seminar_3rd__homework);

        up = (Button)findViewById(R.id.up);
        down = (Button)findViewById(R.id.down);
        first = (ImageView)findViewById(R.id.first);
        second = (ImageView)findViewById(R.id.second);

        up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(i>=2)
                {
                    i=-1;
                    change(++i);
                }
                else
                {
                    i++;
                    change(i);
                }
                if(count==3)
                {
                    Dialog();
                }
            }
        });

        down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(i<0)
                {
                    i=2;
                    change(--i);
                }
                else
                {
                    i--;
                    change(i);
                }

                if(count == 3)
                {
                    Dialog();
                }
            }
        });
    }

    public void change(int i)
    {
        if(i == 0)
        {
            first.setImageResource(R.drawable.daram);
            second.setImageResource(R.drawable.bird);
        }
        else if(i == 1)
        {
            first.setImageResource(R.drawable.bird);
            second.setImageResource(R.drawable.letona);
        }
        else
        {
            first.setImageResource(R.drawable.letona);
            second.setImageResource(R.drawable.daram);
        }
        count++;
    }
    public void Dialog()
    {
        AlertDialog.Builder caution = new AlertDialog.Builder(Seminar_3rd_Homework.this);
        caution.setTitle("버튼 3회 클릭");
        caution.setMessage("버튼을 3번 누르셨습니다. 더 누르시겠습니까?").setCancelable(false)
                .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        count=0;
                    }
                })
                .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        System.exit(0);
                    }
                });
        caution.show();
    }
}
