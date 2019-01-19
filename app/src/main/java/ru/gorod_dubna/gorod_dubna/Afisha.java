package ru.gorod_dubna.gorod_dubna;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Afisha extends AppCompatActivity {


    private WebView mWebView2;
    String url="https://volga-volga.dubna.ru/";
    @TargetApi(Build.VERSION_CODES.ECLAIR_MR1)
    @RequiresApi(api = Build.VERSION_CODES.ECLAIR_MR1)
    @SuppressLint("SetJavaScriptEnabled")
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afisha);

        mWebView2 = findViewById(R.id.webView2);
        // устанавливаем Zoom control
        mWebView2.getSettings().setBuiltInZoomControls(true);
        // создаем клиент
        mWebView2.setWebViewClient(new MyWebViewClient());
        // включаем поддержку JavaScript
        mWebView2.getSettings().setJavaScriptEnabled(true);
        // делаем чтобы страница помещалась в размер экрана
        mWebView2.setInitialScale(1);
        mWebView2.getSettings().setLoadWithOverviewMode(true);
        mWebView2.getSettings().setUseWideViewPort(true) ;
        //Чистим кэш
        mWebView2.clearCache(true);
        // указываем страницу загрузки
        mWebView2.loadUrl(url);
    }
    private class MyWebViewClient extends WebViewClient
    {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url)
        {
            view.loadUrl(url);
            return true;
        }
    }
    @Override
    public void onBackPressed() {
        if(mWebView2.canGoBack()) {
            mWebView2.goBack();
        } else {
            super.onBackPressed();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_afisha, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // получим идентификатор выбранного пункта меню
        int id = item.getItemId();
        // Операции для выбранного пункта меню
        switch (id) {
            case R.id.action_afisha1:
                mWebView2.loadUrl("https://volga-volga.dubna.ru/");
                return true;
            case R.id.action_afisha2:
                mWebView2.loadUrl("http://dubna.ru/afisha");
                return true;
            case R.id.action_afisha3:
                mWebView2.loadUrl("http://museum.jinr.ru/index.html");
                return true;
            case R.id.action_afisha4:
                mWebView2.loadUrl("http://www.radugasport.ru/novo/");
                return true;
            case R.id.action_afisha5:
                mWebView2.loadUrl("http://www.biblioteka-dubna.ru/");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
