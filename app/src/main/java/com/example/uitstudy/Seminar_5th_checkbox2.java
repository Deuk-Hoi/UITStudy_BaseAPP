package com.example.uitstudy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

public class Seminar_5th_checkbox2 extends AppCompatActivity implements CheckBox.OnClickListener{

    CheckBox [] fruit = new CheckBox[4];
    int [] name = {R.id.apple, R.id.banana, R.id.kiwi, R.id.cherry};
    int sum = 0;
    TextView cost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seminar_5th_checkbox2);

        for(int i = 0;i<fruit.length;i++)
        {
            fruit[i] = (CheckBox)findViewById(name[i]);
            fruit[i].setOnClickListener(this);
        }
        cost = (TextView)findViewById(R.id.cost);
    }

    @Override
    public void onClick(View view) {
        sum = 0;

        if(fruit[0].isChecked())
        {
            sum = sum + 500;
        }
        if(fruit[1].isChecked())
        {
            sum = sum + 1000;
        }
        if(fruit[2].isChecked())
        {
            sum = sum + 5000;
        }
        if(fruit[3].isChecked())
        {
            sum = sum + 10000;
        }
        cost.setText(sum+" 원");
    }
}
