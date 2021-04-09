/*
 * *
 *  * Created by DemonApps on 09.04.21 23:52
 *  * Copyright (c) 2021 . All rights reserved.
 *  * Last modified 09.04.21 23:24
 *
 */

package ru.gorod.tver.news_tver;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.my.target.ads.MyTargetView;
import com.squareup.picasso.Picasso;

import ru.gorod.tver.R;

public class ShowActivity extends AppCompatActivity {
    private TextView showDate, showTitle, showDescription, showText;
    private ImageView picNews;
    private String NEWS_KEY = "NewsTver";
    public String url;
    private MyTargetView adViewNews;
    String news;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_activity);
        init();
        getIntentMain();
        final RelativeLayout layout = findViewById(R.id.activityLayoutNews);

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

    private void init() {
        showDate = findViewById(R.id.showDate);
        showTitle = findViewById(R.id.showTitle);
        picNews = findViewById(R.id.picNews);
        showDescription = findViewById(R.id.showDescription);
        showText = findViewById(R.id.showText);
    }

    private void getIntentMain() {
        Intent i = getIntent();
        if (i != null) {
            showDate.setText(i.getStringExtra(Constant.NEWS_DATE));
            showTitle.setText(i.getStringExtra(Constant.NEWS_TITLE));
            this.url = i.getStringExtra(Constant.NEWS_PIC);
            Picasso.get().load(url).error(R.drawable.error).into(picNews);
            showDescription.setText(i.getStringExtra(Constant.NEWS_DESCRIPTION));
            showText.setText(i.getStringExtra(Constant.NEWS_TEXT));
            news = i.getStringExtra(Constant.NEWS_TITLE);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_news, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // получим идентификатор выбранного пункта меню
        int id = item.getItemId();
        // Операции для выбранного пункта меню
        if (id == R.id.action_share) {
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, "Прочитай новость:\n" +news+ "\n\nПередано из приложения 'Город Тверь' https://play.google.com/store/apps/details?id=ru.gorod.tver");
            sendIntent.setType("text/plain");
            startActivity(Intent.createChooser(sendIntent, "Поделиться новостью..."));
        }
        return super.onOptionsItemSelected(item);
    }
}

