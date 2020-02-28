package com.example.uitstudy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class Seminar_6th extends AppCompatActivity{

    Button userenroll;
    private static final String TAG_NUM = "num";
    private static final String TAG_NAME= "name";
    private static final String TAG_MAJOR = "major";
    String myJSON;

    ListView Userlist;
    ArrayList<HashMap<String, String>> userlist;
    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seminar_6th);

        userenroll = (Button)findViewById(R.id.userenroll);
        Userlist = (ListView)findViewById(R.id.Userlist);
        swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.Swipelayout);
        swipeRefreshLayout.setColorSchemeResources( //돌아가는 부분 색깔 지정
                android.R.color.holo_red_light,
                android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light
        );
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {//실질적 새로고침
            @Override
            public void onRefresh() {
                getData("http://192.168.0.12:3000/uit_user");
                swipeRefreshLayout.setRefreshing(false); //이거 안넣으면 안사라지고 계속 돈다.
            }
        });
        userlist = new ArrayList<HashMap<String, String>>();
        getData("http://192.168.0.12:3000/uit_user");

        userenroll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent userenroll = new Intent(Seminar_6th.this, Seminar_6th_AsyncTask.class);
                startActivity(userenroll);
            }
        });

    }

    public void getData(String url)
    {
        class GetDataJSON extends AsyncTask<String,Void,String>
        {
            @Override
            protected String doInBackground(String... strings) {
                String Url = strings[0];
                BufferedReader bufferedReader = null;
                try{
                    URL url = new URL(Url);
                    HttpURLConnection connection = (HttpURLConnection)url.openConnection();
                    Log.e("1",connection.toString());
                    StringBuilder stringBuilder = new StringBuilder();
                    bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                    String json;
                    while((json = bufferedReader.readLine())!=null)
                    {
                        stringBuilder.append(json);
                    }
                    return stringBuilder.toString().trim();
                }
                catch (Exception e)
                {
                    return null;
                }
            }

            @Override
            protected void onPostExecute(String s) {
                myJSON = s;
                new Handler().postDelayed(new Runnable() { // 안튕기나 지켜봐야함.
                    @Override
                    public void run() {
                        showList();
                    }
                },500);
            }
        }
        GetDataJSON g = new GetDataJSON();
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
        {
            g.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,url);
        }
        else {
            g.execute(url);
        }
    }

    protected void showList()
    {
        userlist.clear();
        try {
            JSONArray jsonArray = new JSONArray(myJSON);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject c = jsonArray.getJSONObject(i);
                String num = c.getString(TAG_NUM);
                String name = c.getString(TAG_NAME);
                String major = c.getString(TAG_MAJOR);
                Log.e("result ", num +", "+name +", "+major);

                HashMap<String, String> user = new HashMap<String, String>();
                user.put(TAG_NUM,num);
                user.put(TAG_NAME,name);
                user.put(TAG_MAJOR,major);

                userlist.add(user);
            }
            ListAdapter adapter = new SimpleAdapter(Seminar_6th.this, userlist, R.layout.myframe2,
                    new String[]{TAG_NUM, TAG_NAME, TAG_MAJOR}, new int[]{R.id.num, R.id.name, R.id.major});
            Userlist.setAdapter(adapter);

        }catch (JSONException e)
        {
            e.printStackTrace();
        }
    }
}
