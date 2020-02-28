package com.example.uitstudy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Seminar_8th extends AppCompatActivity {

    Button spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seminar_8th);

        spinner = (Button)findViewById(R.id.spinner);

        spinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent spinner = new Intent(Seminar_8th.this, Seminar_8th_Spinner.class);
                startActivity(spinner);
            }
        });
    }
}
