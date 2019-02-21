package ru.gorod_dubna.gorod_dubna;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.my.target.ads.MyTargetView;

public class MainActivity extends AppCompatActivity implements OnClickListener {

    Button button2;
    Button button3;
    Button button;
    Button button4;
    Button button5;
    Button button6;
    private MyTargetView adView;


    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final RelativeLayout layout = findViewById(R.id.activityLayout);


// Включение режима отладки
        // MyTargetView.setDebugMode(true);
        MyTargetView.setDebugMode(true);

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

        button6 = findViewById(R.id.button6);
        button6.setOnClickListener(this);


        // Устанавливаем слушатель событий
        adView.setListener(new MyTargetView.MyTargetViewListener() {
            @Override
            public void onLoad(@NonNull MyTargetView myTargetView) {
                // Данные успешно загружены, запускаем показ объявлений
                layout.addView(adView);
            }

            @Override
            public void onNoAd(@NonNull String reason, @NonNull MyTargetView myTargetView) {
            }

            @Override
            public void onClick(@NonNull MyTargetView myTargetView) {
            }
        });

        // Запускаем загрузку данных
        adView.load();
    }

    @Override
    protected void onDestroy() {
        if (adView != null) adView.destroy();
        super.onDestroy();
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button2:
                Intent intent2 = new Intent(this, History.class);
                startActivity(intent2);
                break;

            case R.id.button3:
                Intent intent3 = new Intent(this, Telefony.class);
                startActivity(intent3);
                break;

            case R.id.button:
                Intent intent = new Intent(this, News.class);
                startActivity(intent);
                break;

            case R.id.button4:
                Intent intent4 = new Intent(this, Afisha.class);
                startActivity(intent4);
                break;
            case R.id.button5:
                Intent intent5 = new Intent(this, Onas.class);
                startActivity(intent5);
                break;
            case R.id.button6:
                Intent intent6 = new Intent(this, Transport.class);
                startActivity(intent6);
                break;
            default:
                break;
        }
    }

}
