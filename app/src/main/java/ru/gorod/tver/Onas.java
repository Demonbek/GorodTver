/*
 * *
 *  * Created by DemonApps on 09.04.21 23:52
 *  * Copyright (c) 2021 . All rights reserved.
 *  * Last modified 09.04.21 23:44
 *
 */

package ru.gorod.tver;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.my.target.ads.MyTargetView;


public class Onas extends AppCompatActivity implements View.OnClickListener {
    Button button2, button7;

    TextView textView4;
    private MyTargetView adViewOnas;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onas);
        final RelativeLayout layout = findViewById(R.id.activityLayoutOnas);

        button2 = findViewById(R.id.buttonGP);
        button2.setOnClickListener(this);


        button7 = findViewById(R.id.button7);
        button7.setOnClickListener(this);

        textView4 = findViewById(R.id.textView4);
        textView4.setText("Версия: " + BuildConfig.VERSION_NAME);


        // Включение режима отладки
        //MyTargetView.setDebugMode(true);

        // Создаем экземпляр MyTargetView, формат 320х50
        adViewOnas = new MyTargetView(this);

        // Создаем экземпляр MyTargetView, формат 300х250
        // adView = new MyTargetView(this, AdSize.BANNER_300x250);

        // Инициализируем экземпляр
        adViewOnas.init(806305);


        // Устанавливаем слушатель событий
        adViewOnas.setListener(new MyTargetView.MyTargetViewListener() {
            @Override
            public void onLoad(@NonNull MyTargetView myTargetView) {
                // Данные успешно загружены, запускаем показ объявлений
                layout.addView(adViewOnas);
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
        adViewOnas.load();


    }

    @Override
    protected void onDestroy() {
        if (adViewOnas != null) adViewOnas.destroy();
        super.onDestroy();


    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.buttonGP:
                Intent intent2 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/dev?id=6727613984034321120"));
                startActivity(intent2);
                break;

            case R.id.button7:
                Intent intent7 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://demonbek.github.io/privacypolicy/privacypolicytver.html"));
                startActivity(intent7);
                break;
            default:
                break;
        }
    }

}
