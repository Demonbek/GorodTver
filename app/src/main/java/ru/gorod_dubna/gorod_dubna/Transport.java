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

public class Transport extends AppCompatActivity {


    private WebView mWebView2;
    String url="http://город-дубна.рф//timetable/citybus";
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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_transport, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // получим идентификатор выбранного пункта меню
        int id = item.getItemId();
        // Операции для выбранного пункта меню
        switch (id) {
            case R.id.action_transport:
                mWebView2.loadUrl("http://город-дубна.рф//timetable/citybus");
                setTitle(R.string.action_transport);
                return true;

            case R.id.action_transport1:
                mWebView2.loadUrl("https://t.rasp.yandex.ru/city/215");
                setTitle(R.string.action_transport1);
                return true;

            case R.id.action_transport2:
                mWebView2.loadUrl("https://t.rasp.yandex.ru/station/9601639/suburban/?filter=all");
                setTitle(R.string.action_transport2);
                return true;
            case R.id.action_transport3:
                mWebView2.loadUrl("https://t.rasp.yandex.ru/station/9601720/suburban/?direction=all");
                setTitle(R.string.action_transport3);
                return true;
            case R.id.action_transport4:
                mWebView2.loadUrl("https://t.rasp.yandex.ru/station/9739655/");
                setTitle(R.string.action_transport4);
                return true;
            case R.id.action_transport5:
                mWebView2.loadUrl("https://t.rasp.yandex.ru/station/9748264/");
                setTitle(R.string.action_transport5);
                return true;
            case R.id.action_transport6:
                mWebView2.loadUrl("https://t.rasp.yandex.ru/station/9858021/?filter=all");
                setTitle(R.string.action_transport6);
                return true;
            case R.id.action_transport7:
                mWebView2.loadUrl("https://t.rasp.yandex.ru/station/9822157/");
                setTitle(R.string.action_transport7);
                return true;
            case R.id.action_transport8:
                mWebView2.loadUrl("https://t.rasp.yandex.ru/station/9739654/");
                setTitle(R.string.action_transport8);
                return true;
            case R.id.action_transport9:
                mWebView2.loadUrl("https://t.rasp.yandex.ru/station/9739657/");
                setTitle(R.string.action_transport9);
                return true;
            case R.id.action_transport10:
                mWebView2.loadUrl("file:///android_asset/avtobus_dubna_mos.jpg");
                setTitle(R.string.action_transport10);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
