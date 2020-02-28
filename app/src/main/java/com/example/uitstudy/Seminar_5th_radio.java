package com.example.uitstudy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class Seminar_5th_radio extends AppCompatActivity {

    RadioGroup radioGroup;
    RadioButton radioButton;
    TextView result;
    int radio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seminar_5th_radio);

        radioGroup = (RadioGroup)findViewById(R.id.radiogroup);
        result = (TextView)findViewById(R.id.result);

        RadioGroup.OnCheckedChangeListener onCheckedChangeListener = new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i == R.id.man)
                {
                    radioButton = (RadioButton)findViewById(i);
                }
                else{
                    radioButton = (RadioButton)findViewById(i);
                }
               result.setText(radioButton.getText());
            }
        };

        radioGroup.setOnCheckedChangeListener(onCheckedChangeListener);

    }
}
