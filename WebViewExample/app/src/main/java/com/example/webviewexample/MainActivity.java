package com.example.webviewexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {

    private WebView webView;
    private String url = "http://www.surfingnet.co.kr/";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = (WebView)findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true); //javascript 허용 가능하도록 설정
        webView.loadUrl(url);

        webView.setWebChromeClient(new WebChromeClient()); //크롬셋팅
        webView.setWebViewClient(new WebClientClass()); //그냥 만들면 에러 => alt + Enter , 내부클래스 만들기 (1)




    }

    @Override // 어떤 키를 입력했을때 일으킬 동작 설정
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()){ // 뒤로가기를 눌렀을때 뒤로 갈수 있게되면 => webView 에서 뒤로가게 해라
            webView.goBack();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    private class WebClientClass extends WebViewClient { // 위에서 만들어진 내부 클래스 (2)
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) { // 내부에 만들기 ctrl + o => shouldoverride~ : 현재페이지의 URL 을 읽어옴
            view.loadUrl(url); //바로 윗줄의 view 를 받음
            return true;
            // mainfests 에서 <uses-permission android:name="android.permission.INTERNET"/>로 권한 추가해주기
        }
    }
}
