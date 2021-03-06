/*
 * *
 *  * Created by DemonApps on 09.04.21 23:52
 *  * Copyright (c) 2021 . All rights reserved.
 *  * Last modified 09.04.21 20:48
 *
 */

package ru.gorod.tver;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import com.google.android.material.navigation.NavigationView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.widget.Toolbar;
import android.view.ViewGroup;
import android.widget.Button;

import com.my.target.ads.MyTargetView;

import ru.gorod.tver.news_tver.Constant;
import ru.gorod.tver.news_tver.ReadActivity;


public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    Button button2;
    Button button3;
    Button button;
    Button button4;
    Button button5;
    Button button6;
    private MyTargetView adView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        createNotificationChannel();

        final RelativeLayout layout = findViewById(R.id.activityLayout);


        // Включение режима отладки
        //MyTargetView.setDebugMode(true);


        // Создаем экземпляр MyTargetView, формат 320х50
        adView = new MyTargetView(this);

        // Создаем экземпляр MyTargetView, формат 300х250
        // adView = new MyTargetView(this, AdSize.BANNER_300x250);

        // Инициализируем экземпляр
        adView.init(806114);

        button2 = findViewById(R.id.button2);
        button2.setOnClickListener(this);

        button3 = findViewById(R.id.button3);
        button3.setOnClickListener(this);

        button = findViewById(R.id.button);
        button.setOnClickListener(this);

        button4 = findViewById(R.id.button4);
        button4.setOnClickListener(this);

        button5 = findViewById(R.id.button5);
        button5.setOnClickListener(this);

        button6 = findViewById(R.id.button6);
        button6.setOnClickListener(this);


        // Устанавливаем LayoutParams
        RelativeLayout.LayoutParams adViewLayoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        adViewLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        adView.setLayoutParams(adViewLayoutParams);

        // Устанавливаем слушатель событий
        adView.setListener(new MyTargetView.MyTargetViewListener()
        {
            @Override
            public void onLoad(@NonNull MyTargetView myTargetView)
            {
                // Данные успешно загружены, запускаем показ объявлений
                layout.addView(adView);
            }

            @Override
            public void onNoAd(@NonNull String reason, @NonNull MyTargetView myTargetView)
            {
            }

            @Override
            public void onShow(@NonNull MyTargetView myTargetView) {

            }

            @Override
            public void onClick(@NonNull MyTargetView myTargetView)
            {
            }
        });

        // Запускаем загрузку данных
        adView.load();
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_history) {
            Intent intent2 = new Intent(this, History.class);
            startActivity(intent2);
        } else if (id == R.id.nav_pogoda) {
            Intent intent8 = new Intent(this, Pogoda.class);
            startActivity(intent8);
        } else if (id == R.id.nav_ads) {
            Intent intent9 = new Intent(this, Ads.class);
            startActivity(intent9);
        } else if (id == R.id.nav_jobs) {
            Intent intent10 = new Intent(this, Jobs.class);
            startActivity(intent10);
        } else if (id == R.id.nav_share) {
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, "Советую приложение 'Город Тверь'\n https://play.google.com/store/apps/details?id=ru.gorod.tver" );
            sendIntent.setType("text/plain");
            startActivity(Intent.createChooser(sendIntent,"Поделиться приложением..."));
        } else if (id == R.id.nav_onas) {
            Intent intent6 = new Intent(this, Onas.class);
            startActivity(intent6);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    @Override
    protected void onDestroy()
    {
        if (adView != null) adView.destroy();
        super.onDestroy();
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.button:
                Intent intent = new Intent(this, ReadActivity.class);
                startActivity(intent);
                break;
            case R.id.button2:
                Intent intent2 = new Intent(this, Afisha.class);
                startActivity(intent2);
                break;
            case R.id.button3:
                Intent intent3 = new Intent(this, Telefony.class);
                startActivity(intent3);
                break;
            case R.id.button4:
                Intent intent4 = new Intent(this, Transport.class);
                startActivity(intent4);
                break;
            case R.id.button5:
                Intent intent5 = new Intent(this, CamWeb.class);
                startActivity(intent5);
                break;
            case R.id.button6:
                Intent intent6 = new Intent(this, Video.class);
                startActivity(intent6);
                break;
            default:
                break;
        }
    }
    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel(Constant.CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}
