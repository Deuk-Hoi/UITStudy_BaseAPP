package com.example.uitstudy;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class Seminar_2nd extends AppCompatActivity {

    Button id_btn, pass_btn, id_pass_btn;
    EditText id_edit, pass_edit, phone, Confirm_pass_edit;
    TextView Confirm_pass_state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seminar_2nd);

        id_btn = (Button)findViewById(R.id.id_btn);
        pass_btn = (Button)findViewById(R.id.pass_btn);
        id_pass_btn = (Button)findViewById(R.id.id_pass_btn);

        id_edit = (EditText)findViewById(R.id.id_edit);
        pass_edit = (EditText)findViewById(R.id.pass_edit);
        phone = (EditText)findViewById(R.id.phone_edit);
        Confirm_pass_edit = (EditText)findViewById(R.id.Confirm_pass_edit);

        Confirm_pass_state = (TextView) findViewById(R.id.Confirm_pass_state);

        final Button.OnClickListener onClickListener = new Button.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                switch (view.getId())
                {
                    case R.id.id_btn:
                        //content
                        Toast.makeText(getApplicationContext(),"아이디는 : "+ id_edit.getText(),Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.pass_btn:
                        //content
                        Snackbar.make(view,"비밀번호는 : " + pass_edit.getText(),Snackbar.LENGTH_SHORT).show();
                        break;
                    case R.id.id_pass_btn:
                        Toast.makeText(getApplicationContext(),"아이디 : "+id_edit.getText()+"\n비밀번호 : "+pass_edit.getText() + "\n전화번호 : "+phone.getText(),Toast.LENGTH_LONG).show();
                        break;
                }
            }
        };

        id_btn.setOnClickListener(onClickListener);
        pass_btn.setOnClickListener(onClickListener);
        id_pass_btn.setOnClickListener(onClickListener);

        phone.addTextChangedListener(new PhoneNumberFormattingTextWatcher());


        Confirm_pass_edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(pass_edit.getText().toString().equals(Confirm_pass_edit.getText().toString()))
                {
                    Confirm_pass_state.setText("비밀번호가 일치합니다.");
                    Confirm_pass_state.setTextColor(Color.GREEN);
                }
                else
                {
                    Confirm_pass_state.setText("비밀번호가 일치하지 않습니다.");
                    Confirm_pass_state.setTextColor(Color.RED);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

}
