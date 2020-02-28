package com.example.uitstudy;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ProgressBar;

import com.google.android.material.snackbar.Snackbar;

public class Seminar_3rd extends AppCompatActivity {

    Button test, progressBar, progressSpin, imageTest,homework;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seminar_3rd);

        test = (Button) findViewById(R.id.test);
        progressBar = (Button) findViewById(R.id.progressBar);
        progressSpin = (Button) findViewById(R.id.progressSpin);
        imageTest = (Button) findViewById(R.id.imageTest);
        homework = (Button) findViewById(R.id.homework);

        progressDialog = new ProgressDialog(Seminar_3rd.this);

        Button.OnClickListener onClickListener = new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.test:
                        Dialog();
                        break;
                    case R.id.progressBar:
                        BarTask b = new BarTask();
                        b.execute();
                        break;
                    case R.id.progressSpin:
                        SpinTask s = new SpinTask();
                        s.execute();
                        break;
                    case R.id.imageTest:
                        Intent imageTest = new Intent(Seminar_3rd.this, Seminar_3rd_ImageTest.class);
                        startActivity(imageTest);
                        break;
                    case R.id.homework:
                        Intent homework = new Intent(Seminar_3rd.this, Seminar_3rd_Homework.class);
                        startActivity(homework);
                        break;
                }
            }
        };
        test.setOnClickListener(onClickListener);
        progressBar.setOnClickListener(onClickListener);
        progressSpin.setOnClickListener(onClickListener);
        imageTest.setOnClickListener(onClickListener);
        homework.setOnClickListener(onClickListener);

    }

    class SpinTask extends AsyncTask<Void, Void, Void>
    {
        @Override
        protected void onPreExecute() {
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setMessage("스피너 모양 입니다");
            progressDialog.show();
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try{
                for(int i = 0; i<2; i++)
                {
                    Thread.sleep(1000);
                }
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            progressDialog.dismiss();
            super.onPostExecute(aVoid);
        }

    }



    class BarTask extends AsyncTask<Void, Void, Void>
    {

        @Override
        protected void onPreExecute() {
            progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progressDialog.setMessage("패치 진행중 입니다");
            progressDialog.show();
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try{
                for(int i = 0; i<5;i++)
                {
                    progressDialog.setProgress(i * 30);
                    Thread.sleep(500);
                }
            }catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            progressDialog.dismiss();
            super.onPostExecute(aVoid);
        }
    }
    public void Dialog() {
        AlertDialog.Builder caution = new AlertDialog.Builder(Seminar_3rd.this);
        caution.setTitle("테스트");
        caution.setMessage("UIT여러분 안녕하셔요?").setCancelable(false)
                .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                        progressDialog.setMessage("회원가입 처리중");
                        progressDialog.show();
                        progressDialog.dismiss();
                    }
                })
                .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        caution.show();
    }



}