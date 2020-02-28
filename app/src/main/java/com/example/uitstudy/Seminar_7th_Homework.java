package com.example.uitstudy;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.StringTokenizer;

public class Seminar_7th_Homework extends AppCompatActivity implements CheckBox.OnCheckedChangeListener{

    RadioGroup Menus;
    RadioButton ordermenu;
    CheckBox[] menu = new CheckBox[3];
    int [] name = {R.id.Rice, R.id.Egg, R.id.Cheese};
    EditText orderEdit, addressEdit, phonnumEdit, requestEdit;
    Button save;

    String [] orderstate = new String[8];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seminar_7th_homework);

        Menus = (RadioGroup)findViewById(R.id.Menus);

        for(int i = 0; i < menu.length; i++)
        {
            menu[i] = (CheckBox)findViewById(name[i]);
            menu[i].setOnCheckedChangeListener(this);
        }
        orderEdit = (EditText)findViewById(R.id.orderEdit);
        addressEdit = (EditText)findViewById(R.id.addressEdit);
        phonnumEdit = (EditText)findViewById(R.id.phonnumEdit);
        requestEdit = (EditText)findViewById(R.id.requestEdit);

        save = (Button)findViewById(R.id.save);

        for(int i = 0; i<orderstate.length;i++)
        {
            orderstate[i] = "";
        }

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog();
            }
        });


        phonnumEdit.addTextChangedListener(new PhoneNumberFormattingTextWatcher());

        Menus.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                ordermenu = (RadioButton)findViewById(i);
                orderstate[0] = ordermenu.getText().toString();
            }
        });
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

        for(int i = 1; i<3;i++)
        {
            orderstate[i] = " ";
        }

        if(menu[0].isChecked())
        {
            orderstate[1] = "공기밥";
        }
        if(menu[1].isChecked())
        {
            orderstate[2] = "계란후라이";
        }
        if(menu[2].isChecked())
        {
            orderstate[3] = "치즈";
        }

    }

    public void Dialog()
    {
        AlertDialog.Builder caution = new AlertDialog.Builder(Seminar_7th_Homework.this);
        caution.setTitle("저장");
        caution.setMessage("DB에 저장 하시겠습니까?").setCancelable(false)
                .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        orderstate[4] = orderEdit.getText().toString();
                        orderstate[5] = addressEdit.getText().toString();
                        orderstate[6] = phonnumEdit.getText().toString();
                        orderstate[7] = requestEdit.getText().toString();

                        String sidemenu ="";

                        for(int i = 1; i<4;i++)
                        {
                            sidemenu = sidemenu + orderstate[i]+" ";
                        }

                        StringTokenizer stringTokenizer = new StringTokenizer(sidemenu, " ");
                        String Sidemenu = "";
                        while(stringTokenizer.hasMoreTokens())
                        {
                            Sidemenu = Sidemenu + stringTokenizer.nextToken()+" ";
                        }

                        insertToDatabase("http://192.168.0.12:3000/insert_orderlist", Sidemenu);
                        finish();
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
    private void insertToDatabase(String url, final String Sidemenu) {

        class InsertData extends AsyncTask<String, Void, String>
        {
            @Override
            protected String doInBackground(String... strings) {

                String Url = (String) strings[0];
                String Sidemenu = (String) strings[1];
                try {
                    //JSONObject를 만들고 key value 형식으로 값을 저장해준다.
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.accumulate("mainmenu", orderstate[0]);
                    jsonObject.accumulate("submenu", Sidemenu);
                    jsonObject.accumulate("ordername", orderstate[4]);
                    jsonObject.accumulate("address", orderstate[5]);
                    jsonObject.accumulate("phone", orderstate[6]);
                    jsonObject.accumulate("request", orderstate[7]);

                    HttpURLConnection con = null;
                    BufferedReader reader = null;

                    try{
                        URL url = new URL(Url);
                        //연결을 함
                        con = (HttpURLConnection) url.openConnection();

                        con.setRequestMethod("POST");//POST방식으로 보냄
                        con.setRequestProperty("Cache-Control", "no-cache");//캐시 설정
                        con.setRequestProperty("Content-Type", "application/json; charset=utf-8");//application JSON 형식으로 전송
                        con.setRequestProperty("Accept", "text/html");//서버에 response 데이터를 html로 받음
                        con.setDoOutput(true);//Outstream으로 post 데이터를 넘겨주겠다는 의미
                        con.setDoInput(true);//Inputstream으로 서버로부터 응답을 받겠다는 의미
                        con.connect();

                        //서버로 보내기위해서 스트림 만듬, 버퍼를 생성하고 넣음
                        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(con.getOutputStream()));
                        String a = jsonObject.toString();
                        Log.e("token",a);
                        writer.write(jsonObject.toString());
                        writer.flush();
                        writer.close();//버퍼를 받아줌

                        //서버로 부터 데이터를 받음
                        InputStream stream = con.getInputStream();

                        reader = new BufferedReader(new InputStreamReader(stream));

                        StringBuffer buffer = new StringBuffer();

                        String line = "";
                        while((line = reader.readLine()) != null){
                            buffer.append(line);
                        }

                        return buffer.toString();//서버로 부터 받은 값을 리턴해줌 아마 OK!!가 들어올것임

                    } catch (MalformedURLException e){
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        if(con != null){
                            con.disconnect();
                        }
                        try {
                            if(reader != null){
                                reader.close();//버퍼를 닫아줌
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                Log.e("get",s);
            }
        }
        InsertData task = new InsertData();
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
        {
            task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,url, Sidemenu);
        }
        else {
            task.execute(url, Sidemenu);
        }

    }

}
