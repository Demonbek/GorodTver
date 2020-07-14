/*
 * *
 *  * Created by DemonApps on 14.07.20 20:03
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 14.07.20 18:59
 *
 */

package ru.gorod.tver;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;


public class Zastavka extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zastavka);

        //Создаем новый поток:
        Thread splash_time = new Thread()
        {
            public void run()
            {
                try
                {
                    //Целое значение время отображения картинки:
                    int SplashTimer = 0;
                    //Запускаем цикл длиной в 3 секунды:
                    while(SplashTimer < 3000) {
                        sleep(100);
                        SplashTimer = SplashTimer +100;
                    }
                }
                catch (InterruptedException e) {
                    e.printStackTrace(); }
                finally {
                    //Закрываем activity:
                    finish();
                    startActivity(new Intent(Zastavka.this,HomeActivity.class));
                }
            }
        };
        splash_time.start();

    }
}

