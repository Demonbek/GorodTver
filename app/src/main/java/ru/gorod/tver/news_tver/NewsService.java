/*
 * *
 *  * Created by DemonApps on 09.04.21 23:52
 *  * Copyright (c) 2021 . All rights reserved.
 *  * Last modified 09.04.21 23:24
 *
 */

package ru.gorod.tver.news_tver;

import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

import ru.gorod.tver.R;

public class NewsService extends Service {
    final String FILENAME = "lastnews";
    private static final String TAG = "MyApps";
    private static String lastNews;
    Timer mTimer;
    MyTimerTask mMyTimerTask;
    DatabaseReference mDataBase;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate");
        mDataBase = FirebaseDatabase.getInstance().getReference("Task");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "Запущен NewsService");

        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
        }
        mTimer = new Timer();
        mMyTimerTask = new MyTimerTask();
        mTimer.schedule(mMyTimerTask, 10000, 3600000);

        return NewsService.START_STICKY;
    }


    public NewsService() {
        Log.d(TAG, "Работает NewsService");
    }

    public class MyTimerTask extends TimerTask {
        @Override
        public void run() {
            //Проверка подключения
            ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            if (Objects.requireNonNull(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)).getState() == NetworkInfo.State.CONNECTED ||
                    Objects.requireNonNull(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)).getState() == NetworkInfo.State.CONNECTED) {
                //Если есть интернет
                // Здесь трудоемкие задачи переносятся в дочерний поток.
                Log.i(TAG, "фон");
                LastTaskClass lastTaskClass = new LastTaskClass();
                Thread thread = new Thread(lastTaskClass);
                thread.start();
            } else {
                //Если нет  интернета
                Log.i(TAG, "Нет сети");
            }

        }
    }

    class LastTaskClass implements Runnable {
        private String oldNews;

        @Override
        public void run() {

            // открываем поток для чтения
            BufferedReader br = null;
            try {
                br = new BufferedReader(new InputStreamReader(
                        openFileInput(FILENAME)));
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }

            // читаем содержимое
            while (true) {
                String oldNews1 = "";
                try {
                    if (br != null) {
                        if ((oldNews1 = br.readLine()) == null) break;
                    } else {
                        writeFile("Пусть будет не пусто...");
                        Log.d(TAG, "файла нет записали null");
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                Log.d(TAG, oldNews1 + "(прочитано)");
                oldNews = oldNews1;
            }


            DatabaseReference mDataBase = FirebaseDatabase.getInstance().getReference();
            Query lastQuery = mDataBase.child("NewsTver").orderByKey().limitToLast(1);
            lastQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    for (DataSnapshot data : dataSnapshot.getChildren()) {
                        News objTask = data.getValue(News.class);
                        assert objTask != null;
                        Log.i(TAG, data.getKey() + " = " + objTask.title);
                        lastNews = objTask.title;
                        Log.i(TAG, "lastNews - в onDataChange " + lastNews);
                        Log.i(TAG, "OldNews - в onDataChange " + oldNews);

                        //Отправка сообщения...
                        Log.i(TAG, "lastTask  перед сообщением-" + lastNews);
                        if (lastNews != null) {
                            if (Objects.equals(oldNews, lastNews)) {
                                Log.i(TAG, "Нет Новостей");
                            } else {
                                Log.i(TAG, "oldNews - " + oldNews);
                                Log.i(TAG, "Сообщение отправка");
                                // Create an explicit intent for an Activity in your app
                                Intent intent = new Intent(getApplicationContext(), ReadActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent, 0);
                                Log.d(TAG, lastNews + "(сообщение)");
                                NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), Constant.CHANNEL_ID)
                                        .setSmallIcon(R.drawable.ic_stat_news)
                                        .setTicker("Новость")
                                        .setContentTitle("Новость")
                                        .setContentText(lastNews)
                                        .setStyle(new NotificationCompat.BigTextStyle()
                                                .bigText(lastNews))
                                        .setLargeIcon(BitmapFactory.decodeResource(getResources(),
                                                R.drawable.tver))
                                        .setPriority(NotificationCompat.PRIORITY_MAX)
                                        // Set the intent that will fire when the user taps the notification
                                        .setContentIntent(pendingIntent)
                                        .setAutoCancel(true);
                                NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getApplicationContext());

                                // notificationId is a unique int for each notification that you must define
                                int notificationId = 4455;
                                notificationManager.notify(notificationId, builder.build());
                                writeFile(lastNews);
                            }
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    //Handle possible errors.
                }
            });

        }

        public void writeFile(String lastNews) {
            try {
                // отрываем поток для записи
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
                        openFileOutput(FILENAME, MODE_PRIVATE)));
                // пишем данные
                bw.write(lastNews);
                // закрываем поток
                bw.close();
                Log.d(TAG, "Файл записан");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}