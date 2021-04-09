/*
 * *
 *  * Created by DemonApps on 09.04.21 23:52
 *  * Copyright (c) 2021 . All rights reserved.
 *  * Last modified 25.09.20 21:28
 *
 */

package ru.gorod.tver;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.my.target.ads.MyTargetView;

import java.util.Objects;


public class Ads extends AppCompatActivity {

    private MyTargetView adViewNews;

    private WebView mWebView;
    String url="https://тверь.бесплатныеобъявления.рф";
    String currentUrl=url;
    @TargetApi(Build.VERSION_CODES.ECLAIR_MR1)
    @RequiresApi(api = Build.VERSION_CODES.ECLAIR_MR1)
    @SuppressLint("SetJavaScriptEnabled")
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        final RelativeLayout layout = findViewById(R.id.activityLayoutNews);

        mWebView = findViewById(R.id.webView);
        // устанавливаем Zoom control
        mWebView.getSettings().setBuiltInZoomControls(true);
        // создаем клиент
        mWebView.setWebViewClient(new MyWebViewClient());
        // включаем поддержку JavaScript
        mWebView.getSettings().setJavaScriptEnabled(true);
        // делаем чтобы страница помещалась в размер экрана
        mWebView.setInitialScale(1);
        mWebView.getSettings().setLoadWithOverviewMode(true);
        mWebView.getSettings().setUseWideViewPort(true) ;
        //Чистим кэш
        mWebView.clearCache(true);
        //Проверка подключения
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (Objects.requireNonNull(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)).getState() == NetworkInfo.State.CONNECTED ||
                Objects.requireNonNull(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)).getState() == NetworkInfo.State.CONNECTED) {
            //Если есть интернет
            // указываем страницу загрузки
            mWebView.loadUrl(url);
        } else {
            //Если нет  интернета
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Необходимо подключение к сети...", Toast.LENGTH_LONG);
            toast.show();
            // Закрываем
            finish();
        }

        // Включение режима отладки
        //MyTargetView.setDebugMode(true);

        // Создаем экземпляр MyTargetView, формат 320х50
         adViewNews = new MyTargetView(this);

        // Создаем экземпляр MyTargetView, формат 300х250
        // adView = new MyTargetView(this, AdSize.BANNER_300x250);

        // Инициализируем экземпляр
        adViewNews.init(806294);


        // Устанавливаем слушатель событий
        adViewNews.setListener(new MyTargetView.MyTargetViewListener() {
            @Override
            public void onLoad(@NonNull MyTargetView myTargetView) {
                // Данные успешно загружены, запускаем показ объявлений
                layout.addView(adViewNews);
            }

            @Override
            public void onNoAd(@NonNull String reason, @NonNull MyTargetView myTargetView) {
            }

            @Override
            public void onClick(@NonNull MyTargetView myTargetView) {
            }
        });

        // Запускаем загрузку данных
        adViewNews.load();



    }

    private class MyWebViewClient extends WebViewClient
    {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url)
        {

            currentUrl = url;
            view.loadUrl(url);
            return true;
        }
    }
    @Override
    public void onBackPressed() {
        if(mWebView.canGoBack()) {
            mWebView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}
