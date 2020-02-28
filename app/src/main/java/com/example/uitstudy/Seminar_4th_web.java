package com.example.uitstudy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Seminar_4th_web extends AppCompatActivity {

    WebView webtest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seminar_4th_web);

        webtest = (WebView)findViewById(R.id.webtest);
        WebSettings webset = webtest.getSettings(); //웹세팅
        webset.setJavaScriptEnabled(true);//자바스크립트 허용
        webset.setLoadWithOverviewMode(true);//컨텐츠가 웹뷰 보다 클경우 스크린크기에 맞춤
        webtest.setWebChromeClient(new WebChromeClient());
        webtest.loadUrl("https://www.naver.com/");

        webtest.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
    }
    @Override
    public void onBackPressed() {
        if(webtest.canGoBack())
        {
            webtest.goBack();
        }
        else {
            super.onBackPressed();
        }
    }
}
