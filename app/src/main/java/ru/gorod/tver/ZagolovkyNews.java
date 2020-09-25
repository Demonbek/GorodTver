/*
 * *
 *  * Created by DemonApps on 25.09.20 21:28
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 25.09.20 16:59
 *
 */

package ru.gorod.tver;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

@SuppressLint("Registered")
public class ZagolovkyNews extends Activity{

    // благодоря этому классу мы будет разбирать данные на куски
    public Elements aticle;
    public Elements pics;
    // то в чем будем хранить данные пока не передадим адаптеру
    public ArrayList<String> aticleList = new ArrayList<>();
    public ArrayList<String> urlList = new ArrayList<>();
    public ArrayList<String> picList = new ArrayList<>();
    private ProgressBar progressBar;
    private ImageView pic0, pic1, pic2, pic3, pic4, pic5, pic6, pic7, pic8, pic9, pic10, pic11;
    private Button text0, text1, text2, text3, text4, text5, text6, text7, text8, text9, text10, text11;
    // List view
    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zagolovky_news);
        progressBar = findViewById(R.id.progressBar);
        pic0 = findViewById(R.id.pic0);
        pic1 = findViewById(R.id.pic1);
        pic2 = findViewById(R.id.pic2);
        pic3 = findViewById(R.id.pic3);
        pic4 = findViewById(R.id.pic4);
        pic5 = findViewById(R.id.pic5);
        pic6 = findViewById(R.id.pic6);
        pic7 = findViewById(R.id.pic7);
        pic8 = findViewById(R.id.pic8);
        pic9 = findViewById(R.id.pic9);
        pic10 = findViewById(R.id.pic10);
        pic11 = findViewById(R.id.pic11);
        text0 = findViewById(R.id.text0);
        text1 = findViewById(R.id.text1);
        text2 = findViewById(R.id.text2);
        text3 = findViewById(R.id.text3);
        text4 = findViewById(R.id.text4);
        text5 = findViewById(R.id.text5);
        text6 = findViewById(R.id.text6);
        text7 = findViewById(R.id.text7);
        text8 = findViewById(R.id.text8);
        text9 = findViewById(R.id.text9);
        text10 = findViewById(R.id.text10);
        text11 = findViewById(R.id.text11);
        //Проверка подключения
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (Objects.requireNonNull(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)).getState() == NetworkInfo.State.CONNECTED ||
                Objects.requireNonNull(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)).getState() == NetworkInfo.State.CONNECTED) {
            //Если есть интернет
            // Здесь трудоемкие задачи переносятся в дочерний поток.
            WorkingClass workingClass = new WorkingClass();
            Thread thread = new Thread(workingClass);
            thread.start();
        } else {
            //Если нет  интернета
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Необходимо подключение к сети...", Toast.LENGTH_LONG);
            toast.show();
            // Закрываем
            finish();
        }
    }

    // Фоновый класс
    class WorkingClass implements Runnable {
        @Override
        public void run() {
            // класс который захватывает страницу
            Document doc;
            try {
                // определяем откуда будем воровать данные
                doc = Jsoup.connect("https://newssearch.yandex.ru/yandsearch?text=тверь&rpt=nnews2&rel=tm&picture=1&within=9").get();
                aticleList.clear();
                urlList.clear();
                picList.clear();
                // задаем с какого места, я выбрал заголовке статей
                aticle = doc.getElementsByAttributeValue("class", "media-document__title");
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    aticle.forEach(aticle -> {
                        Element aElement = aticle.child(0);
                        String url = aElement.attr("href");
                        String title = aElement.text();
                        aticleList.add(title);
                        urlList.add(url);
                    });
                }
                pics = doc.getElementsByAttributeValue("class", "image media-document__image-i");
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    pics.forEach(pics -> {
                        String urlPic = pics.attr("src");
                        picList.add(urlPic);
                    });
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            ZagolovkyNews.this.runOnUiThread(() -> {
                // Заполняем страницу
                Picasso.get().load(picList.get(0)).into(pic0);
                text0.setText(aticleList.get(0));
                text0.setOnClickListener(v -> {
                    Intent intent = new Intent(ZagolovkyNews.this, News.class);
                    intent.putExtra("url", urlList.get(0));
                    startActivity(intent);
                });

                Picasso.get().load(picList.get(1)).into(pic1);
                text1.setText(aticleList.get(1));
                text1.setOnClickListener(v -> {
                    Intent intent = new Intent(ZagolovkyNews.this, News.class);
                    intent.putExtra("url", urlList.get(1));
                    startActivity(intent);
                });
                Picasso.get().load(picList.get(2)).into(pic2);
                text2.setText(aticleList.get(2));
                text2.setOnClickListener(v -> {
                    Intent intent = new Intent(ZagolovkyNews.this, News.class);
                    intent.putExtra("url", urlList.get(2));
                    startActivity(intent);
                });
                Picasso.get().load(picList.get(3)).into(pic3);
                text3.setText(aticleList.get(3));
                text3.setOnClickListener(v -> {
                    Intent intent = new Intent(ZagolovkyNews.this, News.class);
                    intent.putExtra("url", urlList.get(3));
                    startActivity(intent);
                });
                Picasso.get().load(picList.get(4)).into(pic4);
                text4.setText(aticleList.get(4));
                text4.setOnClickListener(v -> {
                    Intent intent = new Intent(ZagolovkyNews.this, News.class);
                    intent.putExtra("url", urlList.get(4));
                    startActivity(intent);
                });
                Picasso.get().load(picList.get(5)).into(pic5);
                text5.setText(aticleList.get(5));
                text5.setOnClickListener(v -> {
                    Intent intent = new Intent(ZagolovkyNews.this, News.class);
                    intent.putExtra("url", urlList.get(5));
                    startActivity(intent);
                });
                Picasso.get().load(picList.get(6)).into(pic6);
                text6.setText(aticleList.get(6));
                text6.setOnClickListener(v -> {
                    Intent intent = new Intent(ZagolovkyNews.this, News.class);
                    intent.putExtra("url", urlList.get(6));
                    startActivity(intent);
                });
                Picasso.get().load(picList.get(7)).into(pic7);
                text7.setText(aticleList.get(7));
                text7.setOnClickListener(v -> {
                    Intent intent = new Intent(ZagolovkyNews.this, News.class);
                    intent.putExtra("url", urlList.get(7));
                    startActivity(intent);
                });
                Picasso.get().load(picList.get(8)).into(pic8);
                text8.setText(aticleList.get(8));
                text8.setOnClickListener(v -> {
                    Intent intent = new Intent(ZagolovkyNews.this, News.class);
                    intent.putExtra("url", urlList.get(8));
                    startActivity(intent);
                });
                Picasso.get().load(picList.get(9)).into(pic9);
                text9.setText(aticleList.get(9));
                text9.setOnClickListener(v -> {
                    Intent intent = new Intent(ZagolovkyNews.this, News.class);
                    intent.putExtra("url", urlList.get(9));
                    startActivity(intent);
                });
                Picasso.get().load(picList.get(10)).into(pic10);
                text10.setText(aticleList.get(10));
                text10.setOnClickListener(v -> {
                    Intent intent = new Intent(ZagolovkyNews.this, News.class);
                    intent.putExtra("url", urlList.get(10));
                    startActivity(intent);
                });
                Picasso.get().load(picList.get(11)).into(pic11);
                text11.setText(aticleList.get(11));
                text11.setOnClickListener(v -> {
                    Intent intent = new Intent(ZagolovkyNews.this, News.class);
                    intent.putExtra("url", urlList.get(11));
                    startActivity(intent);
                });
                // Сворачиваем прогрессбар
                progressBar.setVisibility(View.GONE);
            });
        }
    }
}
