package com.example.uitstudy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.HashMap;

public class Seminar_5th_Listview extends AppCompatActivity {

    ListView list;
    ArrayList<HashMap<String,String>> keylist;

    private static final String TAG_TITLE = "title";
    private static final String TAG_CONTENTS = "contents";
    private static final String TAG_CLASSIFICATION = "classification";
    private  static  final String TAG_WRITERS = "writers";
    private static final String TAG_DATE = "date";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seminar_5th__listview);

        list = (ListView)findViewById(R.id.list);
        keylist = new ArrayList<HashMap<String, String>>();

        String [][] training = {{"김득회", "나는 김득회 입니다.", "UIT", "득회", "2020-01-08"},
                {"김우빈", "저는 김우빈 입니다.", "UIT", "우빈", "2020-01-07"},
                {"강동원", "나는 강동원 입니다.", "UIT", "동원", "2020-01-06"}};

        for(int i = 0; i<training.length;i++)
        {
            HashMap<String, String> h = new HashMap<String, String>();
            h.put(TAG_TITLE, training[i][0]);
            h.put(TAG_CONTENTS, training[i][1]);
            h.put(TAG_CLASSIFICATION, training[i][2]);
            h.put(TAG_WRITERS, training[i][3]);
            h.put(TAG_DATE, training[i][4]);

            keylist.add(h);
        }

        ListAdapter adapter = new SimpleAdapter(Seminar_5th_Listview.this, keylist, R.layout.myframe, new String[]{TAG_TITLE, TAG_CONTENTS, TAG_CLASSIFICATION, TAG_WRITERS, TAG_DATE},
                new int[]{R.id.title, R.id.content, R.id.classification, R.id.writers, R.id.date});

        list.setAdapter(adapter);


        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(), keylist.get(i).toString(),Toast.LENGTH_SHORT).show();
                Snackbar.make(view,"position : "+ i,Snackbar.LENGTH_SHORT).show();
            }
        });
    }
}
