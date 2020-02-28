package com.example.uitstudy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Seminar_6th_AsyncTask extends AppCompatActivity {

    EditText usernumedit, usernameedit, usermajoredit;
    Button usersave;
    String state = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seminar_6th__async_task);

        usernumedit = (EditText)findViewById(R.id.usernumedit);
        usernameedit = (EditText)findViewById(R.id.usernameedit);
        usermajoredit = (EditText)findViewById(R.id.usermajoredit);
        usersave = (Button)findViewById(R.id.usersave);

        usersave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String num = usernumedit.getText().toString();
                String name = usernameedit.getText().toString();
                String major = usermajoredit.getText().toString();
                insertToDatabase("http://192.168.0.12:3000/insert_userlist", num, name, major);
            }
        });
    }

    private void insertToDatabase(String url, String num, String name, String major) {

        class InsertData extends AsyncTask<String, Void, String>
        {
            @Override
            protected String doInBackground(String... strings) {

                String Url = (String) strings[0];
                String Num = (String) strings[1];
                String Name = (String) strings[2];
                String Major = (String) strings[3];


                try {
                    //JSONObject를 만들고 key value 형식으로 값을 저장해준다.
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.accumulate("num", Num);
                    jsonObject.accumulate("name", Name);
                    jsonObject.accumulate("major", Major);


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
                state = s;
                Log.e("ok?",s);
                if(state.equals("ok"))
                {
                    finish();
                    Toast.makeText(getApplicationContext(),"정상등록 됬습니다.", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getApplicationContext(),"서버와 통신이 원활하지 않습니다.", Toast.LENGTH_SHORT).show();
                }

            }
        }
        InsertData task = new InsertData();
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
        {
            task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,url, num, name, major);
        }
        else {
            task.execute(url, num, name, major);
        }

    }
}
