package com.example.uitstudy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class Seminar_5th_Homework extends AppCompatActivity implements CheckBox.OnCheckedChangeListener{

    RadioGroup menus;
    RadioButton ordermenu;
    RadioButton jeayuk, bulgogi, kimchi, budea;

    Button logsave;

    CheckBox [] addmenu = new CheckBox[3];
    int []name = {R.id.rice, R.id.egg, R.id.cheese};

    CheckBox menusave;
    Boolean check;

    TextView main, add;
    String [] orderlist = new String[4];

    String menu = "";

    SharedPreferences save;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seminar_5th__homework);

        save = getSharedPreferences("menu",MODE_PRIVATE);
        load();

        menus = (RadioGroup)findViewById(R.id.menus);

        jeayuk = (RadioButton)findViewById(R.id.jeayuk);
        bulgogi = (RadioButton)findViewById(R.id.bulgogi);
        kimchi = (RadioButton)findViewById(R.id.kimchi);
        budea = (RadioButton)findViewById(R.id.budea);

        menusave = (CheckBox)findViewById(R.id.menusave);

        logsave = (Button)findViewById(R.id.logsave);

        main = (TextView)findViewById(R.id.main);
        add = (TextView)findViewById(R.id.add);

        for(int i = 0; i<addmenu.length;i++)
        {
            addmenu[i] = (CheckBox)findViewById(name[i]);
            addmenu[i].setOnCheckedChangeListener(this);
        }

        if(check== true)
        {
            loadmenu();
        }

        RadioGroup.OnCheckedChangeListener onCheckedChangeListener = new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                orderlist[0]="";
                ordermenu = (RadioButton)findViewById(i);
                orderlist[0] = ordermenu.getText().toString();
                main.setText(orderlist[0]);
            }
        };

        menus.setOnCheckedChangeListener(onCheckedChangeListener);

        Button.OnClickListener onClickListener = new Button.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                switch (view.getId())
                {
                    case R.id.logsave:
                        save();
                        System.exit(1);
                        break;
                }
            }
        };

        logsave.setOnClickListener(onClickListener);

    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

        menu = "";

        for(int i = 1;i<orderlist.length;i++)
        {
            orderlist[i]= "";
        }

        if(addmenu[0].isChecked())
        {
            orderlist[1] = addmenu[0].getText().toString();
            menu = menu + "공기밥 ";
        }
        if(addmenu[1].isChecked())
        {
            orderlist[2] = addmenu[1].getText().toString();
            menu = menu + "계란후라이 ";
        }
        if(addmenu[2].isChecked())
        {
            orderlist[3] = addmenu[2].getText().toString();
            menu = menu + "치즈 ";
        }

        add.setText(menu);

    }

    public void save()
    {
        SharedPreferences.Editor editor = save.edit();
        editor.putBoolean("MENUSAVE", menusave.isChecked());
        editor.putBoolean("JEAYUK", jeayuk.isChecked());
        editor.putBoolean("BULGOGI", bulgogi.isChecked());
        editor.putBoolean("KIMCHI", kimchi.isChecked());
        editor.putBoolean("BUDEA", budea.isChecked());
        editor.putBoolean("RICE", addmenu[0].isChecked());
        editor.putBoolean("EGG", addmenu[1].isChecked());
        editor.putBoolean("CHEESE", addmenu[2].isChecked());
        editor.putString("MAINMENU",main.getText().toString());
        editor.putString("SIDEMENU",add.getText().toString());
        editor.commit();
    }

    public void load()
    {
        check = save.getBoolean("MENUSAVE",false);
    }

    public void loadmenu()
    {
        menusave.setChecked(check);
        jeayuk.setChecked(save.getBoolean("JEAYUK",false));
        bulgogi.setChecked(save.getBoolean("BULGOGI",false));
        kimchi.setChecked(save.getBoolean("KIMCHI",false));
        budea.setChecked(save.getBoolean("BUDEA",false));
        addmenu[0].setChecked(save.getBoolean("RICE",false));
        addmenu[1].setChecked(save.getBoolean("EGG",false));
        addmenu[2].setChecked(save.getBoolean("CHEESE",false));
        main.setText(save.getString("MAINMENU","메뉴 이름"));
        add.setText(save.getString("SIDEMENU","추가메뉴 이름"));
    }
}
