package ru.gorod_dubna.gorod_dubna;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.ViewGroup;
import android.widget.Button;

import com.my.target.ads.MyTargetView;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    Button button2;
    Button button3;
    Button button;
    Button button4;
    Button button5;
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

        final RelativeLayout layout = findViewById(R.id.activityLayout);


        // Включение режима отладки
        //MyTargetView.setDebugMode(true);


        // Создаем экземпляр MyTargetView, формат 320х50
        adView = new MyTargetView(this);

        // Создаем экземпляр MyTargetView, формат 300х250
        // adView = new MyTargetView(this, AdSize.BANNER_300x250);

        // Инициализируем экземпляр
        adView.init(378914);

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
            sendIntent.putExtra(Intent.EXTRA_TEXT, "Советую приложение 'Город Дубна'\n https://play.google.com/store/apps/details?id=ru.gorod_dubna.gorod_dubna" );
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.button:
                Intent intent = new Intent(this, News.class);
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
            default:
                break;
        }
    }
}
