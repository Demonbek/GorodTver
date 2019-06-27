/*
 * *
 *  * Created by DemonApps on 27.06.19 18:12
 *  * Copyright (c) 2019 . All rights reserved.
 *  * Last modified 27.06.19 18:09
 *
 */

package ru.gorod_dubna.gorod_dubna;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.RelativeLayout;

import com.my.target.ads.MyTargetView;


public class CamWeb extends AppCompatActivity {
    String url = "file:///android_asset/webCam1.html";
    WebView camWeb;
    private MyTargetView adViewWebcam;
    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cam_web);
        final RelativeLayout layout = findViewById(R.id.activityLayoutWebcam);
        camWeb = findViewById(R.id.camWeb);

        camWeb.getSettings().setJavaScriptEnabled(true);
        camWeb.getSettings().setUseWideViewPort(true);
        camWeb.getSettings().setLoadWithOverviewMode(true);
        camWeb.loadUrl(url);
// Включение режима отладки
        //MyTargetView.setDebugMode(true);

        // Создаем экземпляр MyTargetView, формат 320х50
        adViewWebcam = new MyTargetView(this);

        // Создаем экземпляр MyTargetView, формат 300х250
        // adView = new MyTargetView(this, AdSize.BANNER_300x250);

        // Инициализируем экземпляр
        adViewWebcam.init(416740);


        // Устанавливаем слушатель событий
        adViewWebcam.setListener(new MyTargetView.MyTargetViewListener() {
            @Override
            public void onLoad(@NonNull MyTargetView myTargetView) {
                // Данные успешно загружены, запускаем показ объявлений
                layout.addView(adViewWebcam);
            }

            @Override
            public void onNoAd(@NonNull String reason, @NonNull MyTargetView myTargetView) {
            }

            @Override
            public void onClick(@NonNull MyTargetView myTargetView) {
            }
        });

        // Запускаем загрузку данных
        adViewWebcam.load();



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_webcam, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // получим идентификатор выбранного пункта меню
        int id = item.getItemId();
        // Операции для выбранного пункта меню
        switch (id) {
            case R.id.action_webcan1:
                camWeb.loadUrl("file:///android_asset/webCam1.html");
                setTitle(R.string.action_webcam1);
                return true;
            case R.id.action_webcan2:
                camWeb.loadUrl("file:///android_asset/webCam2.html");
                setTitle(R.string.action_webcam2);
                return true;
            case R.id.action_webcan3:
                camWeb.loadUrl("file:///android_asset/webCam3.html");
                setTitle(R.string.action_webcam3);
                return true;
            case R.id.action_webcan4:
                camWeb.loadUrl("file:///android_asset/webCam4.html");
                setTitle(R.string.action_webcam4);
                return true;
            case R.id.action_webcan5:
                camWeb.loadUrl("file:///android_asset/webCam5.html");
                setTitle(R.string.action_webcam5);
                return true;
            case R.id.action_webcan6:
                camWeb.loadUrl("file:///android_asset/webCam6.html");
                setTitle(R.string.action_webcam6);
                return true;
            case R.id.action_webcan7:
                camWeb.loadUrl("file:///android_asset/webCam7.html");
                setTitle(R.string.action_webcam7);
                return true;
            case R.id.action_webcan8:
                camWeb.loadUrl("file:///android_asset/webCam8.html");
                setTitle(R.string.action_webcam8);
                return true;
            case R.id.action_webcan9:
                camWeb.loadUrl("file:///android_asset/webCam9.html");
                setTitle(R.string.action_webcam9);
                return true;
            case R.id.action_webcan10:
                camWeb.loadUrl("file:///android_asset/webCam10.html");
                setTitle(R.string.action_webcam10);
                return true;
            case R.id.action_webcan11:
                camWeb.loadUrl("file:///android_asset/webCam11.html");
                setTitle(R.string.action_webcam11);
                return true;
            case R.id.action_webcan12:
                camWeb.loadUrl("file:///android_asset/webCam12.html");
                setTitle(R.string.action_webcam12);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
