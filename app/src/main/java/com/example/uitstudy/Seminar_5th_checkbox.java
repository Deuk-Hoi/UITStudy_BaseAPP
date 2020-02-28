package com.example.uitstudy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class Seminar_5th_checkbox extends AppCompatActivity implements CheckBox.OnCheckedChangeListener{

    CheckBox [] menu = new CheckBox[3];
    int [] name = {R.id.rice, R.id.egg, R.id.cheese};
    TextView orderlist;
    String order="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seminar_5th_checkbox);

        for(int i = 0;i<menu.length;i++)
        {
            menu[i] =  (CheckBox)findViewById(name[i]);
            menu[i].setOnCheckedChangeListener(this);
        }
        orderlist = (TextView)findViewById(R.id.orderlist);

    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        order = "";

        if(menu[0].isChecked())
        {
            order = order + "공기밥 ";
        }
        if(menu[1].isChecked())
        {
            order = order + "계란 후라이 ";
        }

        if(menu[2].isChecked())
        {
            order = order + "치즈 ";
        }

        orderlist.setText(order);

    }
}
