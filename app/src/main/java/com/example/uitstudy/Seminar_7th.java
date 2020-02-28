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

public class Seminar_7th extends AppCompatActivity {

    private static final String TAG_ORDERNUM = "ordernum";
    private static final String TAG_MAUNMENU= "mainmenu";
    private static final String TAG_SUBMENU = "submenu";
    private static final String TAG_ORDERNAME = "ordername";
    private static final String TAG_ADDRESS= "address";
    private static final String TAG_PHONE = "phone";
    private static final String TAG_REQUEST = "request";
    String myJSON;

    ListView foodorder;
    ArrayList<HashMap<String, String>> orderlist;

    Button callFood;

    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seminar_7th);

        foodorder = (ListView)findViewById(R.id.foodorder);
        callFood = (Button)findViewById(R.id.callFood);
        orderlist = new ArrayList<HashMap<String, String>>();
        getData("http://192.168.0.12:3000/get_orderlist");
        swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.swipelayout); //스와이프 새로고침 부분
        swipeRefreshLayout.setColorSchemeResources( //돌아가는 부분 색깔 지정
                android.R.color.holo_red_light,
                android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light
        );
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {//실질적 새로고침
            @Override
            public void onRefresh() {
                getData("http://192.168.0.12:3000/get_orderlist");
                swipeRefreshLayout.setRefreshing(false); //이거 안넣으면 안사라지고 계속 돈다.
            }
        });

        callFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent callFood = new Intent(Seminar_7th.this, Seminar_7th_Homework.class);
                startActivity(callFood);
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
        orderlist.clear();
        try {
            JSONArray jsonArray = new JSONArray(myJSON);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject c = jsonArray.getJSONObject(i);
                String ordernum = c.getString(TAG_ORDERNUM);
                String mainmenu = c.getString(TAG_MAUNMENU);
                String submenu = c.getString(TAG_SUBMENU);
                String ordername = c.getString(TAG_ORDERNAME);
                String address = c.getString(TAG_ADDRESS);
                String phone = c.getString(TAG_PHONE);
                String request = c.getString(TAG_REQUEST);


                HashMap<String, String> user = new HashMap<String, String>();
                user.put(TAG_ORDERNUM,ordernum);
                user.put(TAG_MAUNMENU,mainmenu);
                user.put(TAG_SUBMENU,submenu);
                user.put(TAG_ORDERNAME,ordername);
                user.put(TAG_ADDRESS,address);
                user.put(TAG_PHONE,phone);
                user.put(TAG_REQUEST,request);

                orderlist.add(user);
            }
            ListAdapter adapter = new SimpleAdapter(Seminar_7th.this, orderlist, R.layout.foodframe,
                    new String[]{TAG_ORDERNUM, TAG_MAUNMENU, TAG_SUBMENU, TAG_ORDERNAME, TAG_ADDRESS, TAG_PHONE, TAG_REQUEST}, new int[]{R.id.ordernum, R.id.Mainmenu, R.id.Submenu, R.id.ordername, R.id.address, R.id.phonenum, R.id.Requesttxt});
            foodorder.setAdapter(adapter);

        }catch (JSONException e)
        {
            e.printStackTrace();
        }
    }
}
