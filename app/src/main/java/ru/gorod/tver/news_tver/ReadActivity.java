/*
 * *
 *  * Created by DemonApps on 09.04.21 23:52
 *  * Copyright (c) 2021 . All rights reserved.
 *  * Last modified 09.04.21 20:44
 *
 */

package ru.gorod.tver.news_tver;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import ru.gorod.tver.R;

public class ReadActivity extends AppCompatActivity {
    private static final String TAG ="MyApp";
    private ListView listView;
    private List<News> listTemp;
    private DatabaseReference mDataBase;
    private String NEWS_KEY = "NewsTver";
    final String FILENAME = "lastnews";
    ArrayList<News> news = new ArrayList<>();
    MyNewsAdapter myNewsAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);
        //Проверка подключения
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (Objects.requireNonNull(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)).getState() == NetworkInfo.State.CONNECTED ||
                Objects.requireNonNull(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)).getState() == NetworkInfo.State.CONNECTED) {
            //Если есть интернет
            // указываем страницу загрузки
            init();
            getDataFromDB();
            setOnClickItem();
            startService(new Intent(this, NewsService.class));
        } else {
            //Если нет  интернета
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Необходимо подключение к сети...", Toast.LENGTH_LONG);
            toast.show();
            // Закрываем
            finish();
        }


    }

    private void init() {
        myNewsAdapter = new MyNewsAdapter(this, news);
        listView = findViewById(R.id.listView);
        listTemp = new ArrayList<>();
        mDataBase = FirebaseDatabase.getInstance().getReference(NEWS_KEY);
    }
    public void onBackPressed() {
        super.onBackPressed();

    }
    private void getDataFromDB() {
        ValueEventListener vListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (news.size() > 0) news.clear();
                if (listTemp.size() > 0) listTemp.clear();
                for (DataSnapshot ds : snapshot.getChildren()) {
                    News newss = ds.getValue(News.class);
                    assert news != null;
                    news.add(0, newss);
                    listTemp.add(0, newss);
                }
                //Запись последней новости в файл
                writeFile(news.get(0).title);
                // Заполнение ListView
                listView.setAdapter(myNewsAdapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        mDataBase.addValueEventListener(vListener);
    }

    private void setOnClickItem() {
        listView.setOnItemClickListener((parent, view, position, id) -> {
            News news = listTemp.get(position);
            Intent i = new Intent(ReadActivity.this, ru.gorod.tver.news_tver.ShowActivity.class);
            i.putExtra(Constant.NEWS_DATE, news.date);
            i.putExtra(Constant.NEWS_TITLE, news.title);
            i.putExtra(Constant.NEWS_PIC, news.pic);
            i.putExtra(Constant.NEWS_DESCRIPTION, news.description);
            i.putExtra(Constant.NEWS_TEXT, news.text);
            i.putExtra(Constant.NEWS_URL, news.url_news);
            startActivity(i);
        });
    }
    void writeFile(String lastNews) {
        try {
            // отрываем поток для записи
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
                    openFileOutput(FILENAME, MODE_PRIVATE)));
            // пишем данные
            bw.write(lastNews);
            // закрываем поток
            bw.close();
            Log.d(TAG, "Файл записан На главной" + lastNews);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}