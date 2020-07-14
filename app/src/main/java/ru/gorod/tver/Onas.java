/*
 * *
 *  * Created by DemonApps on 14.07.20 20:03
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 14.07.20 18:59
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
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.my.target.ads.MyTargetView;


public class Onas extends AppCompatActivity implements View.OnClickListener {
    ImageButton imageButton, imageButton2, imageButton3, imageButton4, imageButton5, imageButton6;
    Button button7;

    TextView textView4;
    private MyTargetView adViewOnas;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onas);
        final RelativeLayout layout = findViewById(R.id.activityLayoutOnas);

        imageButton = findViewById(R.id.imageButton);
        imageButton.setOnClickListener(this);

        imageButton2 = findViewById(R.id.imageButton2);
        imageButton2.setOnClickListener(this);

        imageButton3 = findViewById(R.id.imageButton3);
        imageButton3.setOnClickListener(this);


        imageButton4 = findViewById(R.id.imageButton4);
        imageButton4.setOnClickListener(this);


        imageButton5 = findViewById(R.id.imageButton5);
        imageButton5.setOnClickListener(this);


        imageButton6 = findViewById(R.id.imageButton6);
        imageButton6.setOnClickListener(this);


        button7 = findViewById(R.id.button7);
        button7.setOnClickListener(this);

        textView4 = findViewById(R.id.textView4);
        textView4.setText("Версия: "+ BuildConfig.VERSION_NAME);

        // Включение режима отладки
        //MyTargetView.setDebugMode(true);

        // Создаем экземпляр MyTargetView, формат 320х50
        adViewOnas = new MyTargetView(this);

        // Создаем экземпляр MyTargetView, формат 300х250
        // adView = new MyTargetView(this, AdSize.BANNER_300x250);

        // Инициализируем экземпляр
        adViewOnas.init(380167);


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
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageButton:
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://demonapps.ru"));
                startActivity(intent);
                break;

            case R.id.imageButton2:
                Intent intent2 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/dev?id=6727613984034321120"));
                startActivity(intent2);
                break;
            case R.id.imageButton3:
                Intent intent3 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://vk.com/public169514835"));
                startActivity(intent3);
                break;
            case R.id.imageButton4:
                Intent intent4 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/Dimasiek"));
                startActivity(intent4);
                break;
            case R.id.imageButton5:
                Intent intent5 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/demonapps"));
                startActivity(intent5);
                break;
            case R.id.imageButton6:
                Intent intent6 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/demonapps2018"));
                startActivity(intent6);
                break;
            case R.id.button7:
                Intent intent7 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://demonbek.github.io/privacypolicy/privacypolicydub.html"));
                startActivity(intent7);
                break;
            default:
                break;
        }
     }
}
