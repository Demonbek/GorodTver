/*
 * *
 *  * Created by DemonApps on 11.04.20 10:17
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 11.04.20 10:17
 *
 */

package ru.gorod_dubna.gorod_dubna;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import com.squareup.picasso.Picasso;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class ZagolovkyNews extends Activity{

    // благодоря этому классу мы будет разбирать данные на куски
    public Elements aticle;
    // то в чем будем хранить данные пока не передадим адаптеру
    public ArrayList<String> aticleList = new ArrayList<>();
    public ArrayList<String> urlList = new ArrayList<>();
    public ArrayList<String> picList = new ArrayList<>();
    private ImageView pic0, pic1, pic2, pic3, pic4, pic5, pic6, pic7, pic8, pic9, pic10, pic11, pic12, pic13, pic14;
    private Button text0, text1, text2, text3, text4, text5, text6, text7, text8, text9, text10,text11, text12, text13, text14;
    // List view
    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zagolovky_news);
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
        pic12 = findViewById(R.id.pic12);
        pic13 = findViewById(R.id.pic13);
        pic14 = findViewById(R.id.pic14);
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
        text12 = findViewById(R.id.text12);
        text13 = findViewById(R.id.text13);
        text14 = findViewById(R.id.text14);
        // запрос к нашему отдельному поток на выборку данных
        new NewThread().execute();
    }


    /**
     * А вот и внутрений класс который делает запросы, если вы не читали статьи у меня в блоге про отдельные
     * потоки советую почитать
     */
    @SuppressLint("StaticFieldLeak")
    public class NewThread extends AsyncTask<String, Void, String> {

        // Метод выполняющий запрос в фоне, в версиях выше 4 андроида, запросы в главном потоке выполнять
        // нельзя, поэтому все что вам нужно выполнять - выносите в отдельный тред
        @SuppressLint("NewApi")
        @Override
        protected String doInBackground(String... arg) {

            // класс который захватывает страницу
            Document doc;
            try {
                // определяем откуда будем воровать данные
                doc = Jsoup.connect("http://город-дубна.рф/all_news/all_news").get();
                aticleList.clear();
                urlList.clear();
                picList.clear();
                // задаем с какого места, я выбрал заголовке статей
                aticle = doc.getElementsByAttributeValue("class", "all_15_news");
                aticle.forEach(aticle -> {
                    Element aElement = aticle.child(0);
                    String url = "http://город-дубна.рф" + aElement.attr("href");
                    String title = aElement.child(0).text();
                    Element divElement = aticle.child(1);
                    Element noindexElement = divElement.child(0);
                    Element picElement = noindexElement.child(0);
                    String pic = picElement.attr("src");
                    if (pic.indexOf("/") == 0) {
                        pic = "http://город-дубна.рф" + pic;
                    }
                    aticleList.add(title);
                    urlList.add(url);
                    picList.add(pic);
                });
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            // ничего не возвращаем потому что я так захотел)
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
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
            Picasso.get().load(picList.get(12)).into(pic12);
            text12.setText(aticleList.get(12));
            text12.setOnClickListener(v -> {
                Intent intent = new Intent(ZagolovkyNews.this, News.class);
                intent.putExtra("url", urlList.get(12));
                startActivity(intent);
            });
            Picasso.get().load(picList.get(13)).into(pic13);
            text13.setText(aticleList.get(13));
            text13.setOnClickListener(v -> {
                Intent intent = new Intent(ZagolovkyNews.this, News.class);
                intent.putExtra("url", urlList.get(13));
                startActivity(intent);
            });
            Picasso.get().load(picList.get(14)).into(pic14);
            text14.setText(aticleList.get(14));
            text14.setOnClickListener(v -> {
                Intent intent = new Intent(ZagolovkyNews.this, News.class);
                intent.putExtra("url", urlList.get(14));
                startActivity(intent);
            });
        }

    }
}