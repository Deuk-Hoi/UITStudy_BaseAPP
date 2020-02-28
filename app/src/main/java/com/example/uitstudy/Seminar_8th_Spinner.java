package com.example.uitstudy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Locale;


public class Seminar_8th_Spinner extends AppCompatActivity {

    Spinner userSpinner;
    String user = "";
    int userindex = 0;
    TextView spinnerNumber, spinnerName;

    Locale locale = Locale.getDefault();
    String language =locale.getLanguage(); //언어 추출
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seminar_8th__spinner);

        spinnerNumber = (TextView)findViewById(R.id.spinnerNumber);
        spinnerName = (TextView)findViewById(R.id.spinnerName);
        userSpinner = (Spinner)findViewById(R.id.userSpinner);

        userSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Object object = (Object)adapterView.getAdapter().getItem(i);
                user = object.toString();
                userindex = i;
                if(language.equals("ko")) {
                    spinnerNumber.setText("번호 : " + Integer.toString(userindex));
                    spinnerName.setText("이름 : " + user);
                }
                else
                {
                    spinnerNumber.setText("Number : " + Integer.toString(userindex));
                    spinnerName.setText("Name : " + user);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}
