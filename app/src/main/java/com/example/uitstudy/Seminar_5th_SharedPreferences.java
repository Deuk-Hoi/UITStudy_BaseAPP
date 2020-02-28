package com.example.uitstudy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class Seminar_5th_SharedPreferences extends AppCompatActivity implements Button.OnClickListener{

    EditText id_edit, pass_edit;
    Button login, exit;
    CheckBox saveid;
    Boolean check = false;
    String id = "";
    SharedPreferences save;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seminar_5th__shared_preferences);

        save = getSharedPreferences("login",MODE_PRIVATE);
        load();

        id_edit = (EditText)findViewById(R.id.id_edit);
        pass_edit = (EditText)findViewById(R.id.pass_edit);
        login = (Button)findViewById(R.id.login);
        exit = (Button)findViewById(R.id.exit);
        saveid = (CheckBox)findViewById(R.id.saveid);

        if(check == true)
        {
            id_edit.setText(id);
            saveid.setChecked(check);
        }

        exit.setOnClickListener(this);
        login.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.login:
                break;
            case R.id.exit:
                save();
                System.exit(1);
                break;
        }
    }

    public void save()
    {
        SharedPreferences.Editor editor = save.edit();
        editor.putBoolean("IDSAVE", saveid.isChecked());
        editor.putString("ID",id_edit.getText().toString());
        editor.commit();
    }

    public void load()
    {
        check = save.getBoolean("IDSAVE",false);
        id = save.getString("ID", "");
    }
}
