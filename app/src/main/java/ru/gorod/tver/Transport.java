/*
 * *
 *  * Created by DemonApps on 27.07.20 20:30
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 27.07.20 20:26
 *
 */

package ru.gorod.tver;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;

import com.my.target.ads.MyTargetView;


public class Transport extends AppCompatActivity {

    private MyTargetView adViewTrans;
    private WebView mWebView3;
    String url="https://rasp.yandex.ru/station/9603093/suburban/?direction=all";
    @TargetApi(Build.VERSION_CODES.ECLAIR_MR1)
    @RequiresApi(api = Build.VERSION_CODES.ECLAIR_MR1)
    @SuppressLint("SetJavaScriptEnabled")
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transport);
        final RelativeLayout layout = findViewById(R.id.activityLayoutTrans);

        mWebView3 = findViewById(R.id.webView3);
        // устанавливаем Zoom control
        mWebView3.getSettings().setBuiltInZoomControls(true);
        // создаем клиент
        mWebView3.setWebViewClient(new MyWebViewClient());
        // включаем поддержку JavaScript
        mWebView3.getSettings().setJavaScriptEnabled(true);
        // делаем чтобы страница помещалась в размер экрана
        mWebView3.setInitialScale(1);
        mWebView3.getSettings().setLoadWithOverviewMode(true);
        mWebView3.getSettings().setUseWideViewPort(true) ;
        //Чистим кэш
        mWebView3.clearCache(true);
        // указываем страницу загрузки
        mWebView3.loadUrl(url);


        // Включение режима отладки
        //MyTargetView.setDebugMode(true);

        // Создаем экземпляр MyTargetView, формат 320х50
        adViewTrans = new MyTargetView(this);

        // Создаем экземпляр MyTargetView, формат 300х250
        // adView = new MyTargetView(this, AdSize.BANNER_300x250);

        // Инициализируем экземпляр
        adViewTrans.init(806308);


        // Устанавливаем слушатель событий
        adViewTrans.setListener(new MyTargetView.MyTargetViewListener() {
            @Override
            public void onLoad(@NonNull MyTargetView myTargetView) {
                // Данные успешно загружены, запускаем показ объявлений
                layout.addView(adViewTrans);
            }

            @Override
            public void onNoAd(@NonNull String reason, @NonNull MyTargetView myTargetView) {
            }

            @Override
            public void onClick(@NonNull MyTargetView myTargetView) {
            }
        });

        // Запускаем загрузку данных
        adViewTrans.load();
    }

    private static class MyWebViewClient extends WebViewClient
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
                mWebView3.loadUrl("https://rasp.yandex.ru/station/9603093/suburban/?direction=all");
                setTitle(R.string.action_transport);
                return true;

            case R.id.action_transport1:
                mWebView3.loadUrl("https://rasp.yandex.ru/station/9603093/");
                setTitle(R.string.action_transport1);
                return true;

            case R.id.action_transport2:
                mWebView3.loadUrl("https://rasp.yandex.ru/station/9857316");
                setTitle(R.string.action_transport2);
                return true;
            case R.id.action_transport3:
                mWebView3.loadUrl("https://rasp.yandex.ru/station/9623435");
                setTitle(R.string.action_transport3);
                return true;
            case R.id.action_transport4:
                mWebView3.loadUrl("https://rasp.yandex.ru/station/9845047?span=schedule");
                setTitle(R.string.action_transport4);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
