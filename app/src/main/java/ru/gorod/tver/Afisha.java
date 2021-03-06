/*
 * *
 *  * Created by DemonApps on 09.04.21 23:52
 *  * Copyright (c) 2021 . All rights reserved.
 *  * Last modified 27.03.21 8:42
 *
 */

package ru.gorod.tver;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.my.target.ads.MyTargetView;

import java.util.Objects;


public class Afisha extends AppCompatActivity {

    private MyTargetView adViewAfisha;
    private WebView mWebView2;
    String url="https://www.koncerttver.ru/";
    @TargetApi(Build.VERSION_CODES.ECLAIR_MR1)
    @RequiresApi(api = Build.VERSION_CODES.ECLAIR_MR1)
    @SuppressLint("SetJavaScriptEnabled")
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afisha);
        final RelativeLayout layout = findViewById(R.id.activityLayoutAfisha);

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
        //Проверка подключения
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (Objects.requireNonNull(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)).getState() == NetworkInfo.State.CONNECTED ||
                Objects.requireNonNull(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)).getState() == NetworkInfo.State.CONNECTED) {
            //Если есть интернет
            // указываем страницу загрузки
            mWebView2.loadUrl(url);
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
        adViewAfisha = new MyTargetView(this);

        // Создаем экземпляр MyTargetView, формат 300х250
        // adView = new MyTargetView(this, AdSize.BANNER_300x250);

        // Инициализируем экземпляр
        adViewAfisha.init(806297);


        // Устанавливаем слушатель событий
        adViewAfisha.setListener(new MyTargetView.MyTargetViewListener() {
            @Override
            public void onLoad(@NonNull MyTargetView myTargetView) {
                // Данные успешно загружены, запускаем показ объявлений
                layout.addView(adViewAfisha);
            }

            @Override
            public void onNoAd(@NonNull String reason, @NonNull MyTargetView myTargetView) {
            }
            @Override
            public void onShow(@NonNull MyTargetView myTargetView) {

            }
            @Override
            public void onClick(@NonNull MyTargetView myTargetView) {
            }
        });

        // Запускаем загрузку данных
        adViewAfisha.load();
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
    public void onBackPressed() {
        if(mWebView2.canGoBack()) {
            mWebView2.goBack();
        } else {
            super.onBackPressed();
        }
    }
    /*@Override
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
                mWebView2.loadUrl("http://volga-volga.dubna.ru/");
                return true;
            case R.id.action_afisha2:
                mWebView2.loadUrl("http://dkoctober.ru/?page_id=43");
                return true;
            case R.id.action_afisha3:
                mWebView2.loadUrl("https://dkmir-dubna.ru/");
                return true;
            case R.id.action_afisha4:
                mWebView2.loadUrl("http://radugasport.ru/");
                return true;
            case R.id.action_afisha5:
                mWebView2.loadUrl("http://arhimed.jinr.ru/asp_shedule.html");
                return true;
            case R.id.action_afisha6:
                mWebView2.loadUrl("https://dushdubna.ru/sports-facility/плавательный-бассейн-карасик/");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }*/
}
