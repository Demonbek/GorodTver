/*
 * *
 *  * Created by DemonApps on 14.07.20 20:03
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 14.07.20 18:59
 *
 */

package ru.gorod.tver;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ScrollView;
import android.widget.TextView;


public class History extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_history, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // получим идентификатор выбранного пункта меню
        int id = item.getItemId();
        ScrollView sv = findViewById(R.id.sv);

        TextView infoTextView = findViewById(R.id.textView);

        // Операции для выбранного пункта меню
        switch (id) {
            case R.id.action_settings:
                infoTextView.setText(R.string.dubna);
                setTitle(R.string.action_settings);
                sv.scrollTo(5, 10);
                return true;
            case R.id.action_settings2:
                infoTextView.setText(R.string.vehi_history);
                setTitle(R.string.action_settings2);
                sv.scrollTo(5, 10);
                return true;
            case R.id.action_settings3:
                infoTextView.setText(R.string.sozdanie);
                setTitle(R.string.action_settings3);
                sv.scrollTo(5, 10);
                return true;
            case R.id.action_settings4:
                infoTextView.setText(R.string.monument);
                setTitle(R.string.action_settings4);
                sv.scrollTo(5, 10);
                return true;
            case R.id.action_settings5:
                infoTextView.setText(R.string.lica);
                setTitle(R.string.action_settings5);
                sv.scrollTo(5, 10);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
