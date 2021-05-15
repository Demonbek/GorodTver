/*
 * *
 *  * Created by DemonApps on 09.04.21 23:52
 *  * Copyright (c) 2021 . All rights reserved.
 *  * Last modified 25.07.20 9:28
 *
 */

package ru.gorod.tver;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;

import com.my.target.ads.MyTargetView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;



public class Telefony extends AppCompatActivity {

    private MyTargetView adViewNews;

    private WebView mWebView;
    String url="https://m.spravkatver.ru/#catalog";
    String currentUrl=url;
    @TargetApi(Build.VERSION_CODES.ECLAIR_MR1)
    @RequiresApi(api = Build.VERSION_CODES.ECLAIR_MR1)
    @SuppressLint("SetJavaScriptEnabled")
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        final RelativeLayout layout = findViewById(R.id.activityLayoutNews);

        mWebView = findViewById(R.id.webView);
        // устанавливаем Zoom control
        mWebView.getSettings().setBuiltInZoomControls(true);
        // создаем клиент
        mWebView.setWebViewClient(new MyWebViewClient());
        // включаем поддержку JavaScript
        mWebView.getSettings().setJavaScriptEnabled(true);
        // делаем чтобы страница помещалась в размер экрана
        mWebView.setInitialScale(1);
        mWebView.getSettings().setLoadWithOverviewMode(true);
        mWebView.getSettings().setUseWideViewPort(true) ;
        //Чистим кэш
        mWebView.clearCache(true);
        // указываем страницу загрузки
        mWebView.loadUrl(url);

        // Включение режима отладки
        //MyTargetView.setDebugMode(true);

        // Создаем экземпляр MyTargetView, формат 320х50
        adViewNews = new MyTargetView(this);

        // Создаем экземпляр MyTargetView, формат 300х250
        // adView = new MyTargetView(this, AdSize.BANNER_300x250);

        // Инициализируем экземпляр
        //adViewNews.init(380216);


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
            public void onShow(@NonNull MyTargetView myTargetView) {

            }
            @Override
            public void onClick(@NonNull MyTargetView myTargetView) {
            }
        });

        // Запускаем загрузку данных
        adViewNews.load();



    }

    private class MyWebViewClient extends WebViewClient
    {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url)
        {

            currentUrl = url;
            view.loadUrl(url);
            return true;
        }
    }
    @Override
    public void onBackPressed() {
        if(mWebView.canGoBack()) {
            mWebView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}




   /* static String tablename = "SELECT * FROM extrtel";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_telefony);

      //Переменная для работы с БД
        DatabaseHelper mDBHelper = new DatabaseHelper(this);

        try {
            mDBHelper.updateDataBase();
        } catch (IOException mIOException) {
            throw new Error("UnableToUpdateDatabase");
        }

        SQLiteDatabase mDb = mDBHelper.getWritableDatabase();


//Список телефонов
        ArrayList<HashMap<String, Object>> phones;
        phones = new ArrayList<>();

//Список параметров конкретного клиента
        HashMap<String, Object> phone;

//Отправляем запрос в БД
        Cursor cursor = mDb.rawQuery(tablename, null);
        cursor.moveToFirst();

//Пробегаем по всем памятникам
        while (!cursor.isAfterLast()) {
            phone = new HashMap<>();

            //Заполняем клиента
            phone.put("name", cursor.getString(1));
            phone.put("adress", cursor.getString(2));
            phone.put("phone", cursor.getString(3));
            //Закидываем клиента в список клиентов
            phones.add(phone);

            //Переходим к следующему клиенту
            cursor.moveToNext();
        }
        cursor.close();

//Какие параметры клиента мы будем отображать в соответствующих
//элементах из разметки adapter_item.xml
        String[] from = {"name", "adress", "phone"};
        int[] to = {R.id.textView, R.id.textView2, R.id.textView3};

//Создаем адаптер
        SimpleAdapter adapter = new SimpleAdapter(this, phones, R.layout.adapter_item, from, to);
        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_telefony, menu);
        return true;
    }

    @SuppressLint("NewApi")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // получим идентификатор выбранного пункта меню
        int id = item.getItemId();

        // Операции для выбранного пункта меню
        switch (id) {
            case R.id.action_tel:
                tablename= "SELECT * FROM extrtel";
                setTitle(R.string.action_tel);
                //Переменная для работы с БД
                DatabaseHelper mDBHelper = new DatabaseHelper(this);

                try {
                    mDBHelper.updateDataBase();
                } catch (IOException mIOException) {
                    throw new Error("UnableToUpdateDatabase");
                }

                SQLiteDatabase mDb = mDBHelper.getWritableDatabase();


//Список телефонов
                ArrayList<HashMap<String, Object>> phones;
                phones = new ArrayList<>();

//Список параметров конкретного клиента
                HashMap<String, Object> phone;

//Отправляем запрос в БД
                Cursor cursor = mDb.rawQuery(tablename, null);
                cursor.moveToFirst();

//Пробегаем по всем памятникам
                while (!cursor.isAfterLast()) {
                    phone = new HashMap<>();

                    //Заполняем клиента
                    phone.put("name", cursor.getString(1));
                    phone.put("adress", cursor.getString(2));
                    phone.put("phone", cursor.getString(3));
                    //Закидываем клиента в список клиентов
                    phones.add(phone);

                    //Переходим к следующему клиенту
                    cursor.moveToNext();
                }
                cursor.close();

//Какие параметры клиента мы будем отображать в соответствующих
//элементах из разметки adapter_item.xml
                String[] from = {"name", "adress", "phone"};
                int[] to = {R.id.textView, R.id.textView2, R.id.textView3};

//Создаем адаптер
                SimpleAdapter adapter = new SimpleAdapter(this, phones, R.layout.adapter_item, from, to);
                ListView listView = findViewById(R.id.listView);
                listView.setAdapter(adapter);

                return true;
            case R.id.action_tel2:
                tablename = "SELECT * FROM administracia";
                setTitle(R.string.action_tel2);
                //Переменная для работы с БД
                DatabaseHelper mDBHelper2 = new DatabaseHelper(this);

                try {
                    mDBHelper2.updateDataBase();
                } catch (IOException mIOException) {
                    throw new Error("UnableToUpdateDatabase");
                }

                SQLiteDatabase mDb2 = mDBHelper2.getWritableDatabase();


//Список телефонов
                ArrayList<HashMap<String, Object>> phones2;
                phones2 = new ArrayList<>();

//Список параметров конкретного клиента

//Отправляем запрос в БД
                Cursor cursor2 = mDb2.rawQuery(tablename, null);
                cursor2.moveToFirst();

//Пробегаем по всем памятникам
                while (!cursor2.isAfterLast()) {
                    phone = new HashMap<>();

                    //Заполняем клиента
                    phone.put("name", cursor2.getString(1));
                    phone.put("adress", cursor2.getString(2));
                    phone.put("phone", cursor2.getString(3));
                    //Закидываем клиента в список клиентов
                    phones2.add(phone);

                    //Переходим к следующему клиенту
                    cursor2.moveToNext();
                }
                cursor2.close();

//Какие параметры клиента мы будем отображать в соответствующих
//элементах из разметки adapter_item.xml
                from = new String[]{"name", "adress", "phone"};
                to = new int[]{R.id.textView, R.id.textView2, R.id.textView3};

//Создаем адаптер
                SimpleAdapter adapter2 = new SimpleAdapter(this, phones2, R.layout.adapter_item, from, to);
                ListView listView2 = findViewById(R.id.listView);
                listView2.setAdapter(adapter2);


                return true;

            case R.id.action_tel3:
                tablename = "SELECT * FROM gostel";
                setTitle(R.string.action_tel3);
                //Переменная для работы с БД
                DatabaseHelper mDBHelper3 = new DatabaseHelper(this);

                try {
                    mDBHelper3.updateDataBase();
                } catch (IOException mIOException) {
                    throw new Error("UnableToUpdateDatabase");
                }

                SQLiteDatabase mDb3 = mDBHelper3.getWritableDatabase();


//Список телефонов
                ArrayList<HashMap<String, Object>> phones3;
                phones3 = new ArrayList<>();

//Список параметров конкретного клиента

//Отправляем запрос в БД
                Cursor cursor3 = mDb3.rawQuery(tablename, null);
                cursor3.moveToFirst();

//Пробегаем по всем памятникам
                while (!cursor3.isAfterLast()) {
                    phone = new HashMap<>();

                    //Заполняем клиента
                    phone.put("name", cursor3.getString(1));
                    phone.put("adress", cursor3.getString(2));
                    phone.put("phone", cursor3.getString(3));
                    //Закидываем клиента в список клиентов
                    phones3.add(phone);

                    //Переходим к следующему клиенту
                    cursor3.moveToNext();
                }
                cursor3.close();

//Какие параметры клиента мы будем отображать в соответствующих
//элементах из разметки adapter_item.xml
                from = new String[]{"name", "adress", "phone"};
                to = new int[]{R.id.textView, R.id.textView2, R.id.textView3};

//Создаем адаптер
                SimpleAdapter adapter3 = new SimpleAdapter(this, phones3, R.layout.adapter_item, from, to);
                ListView listView3 = findViewById(R.id.listView);
                listView3.setAdapter(adapter3);
                return true;


            case R.id.action_tel4:
                tablename = "SELECT * FROM medtel";
                setTitle(R.string.action_tel4);
                //Переменная для работы с БД
                DatabaseHelper mDBHelper4 = new DatabaseHelper(this);

                try {
                    mDBHelper4.updateDataBase();
                } catch (IOException mIOException) {
                    throw new Error("UnableToUpdateDatabase");
                }

                SQLiteDatabase mDb4 = mDBHelper4.getWritableDatabase();


//Список телефонов
                ArrayList<HashMap<String, Object>> phones4;
                phones4 = new ArrayList<>();

//Список параметров конкретного клиента

//Отправляем запрос в БД
                Cursor cursor4 = mDb4.rawQuery(tablename, null);
                cursor4.moveToFirst();

//Пробегаем по всем памятникам
                while (!cursor4.isAfterLast()) {
                    phone = new HashMap<>();

                    //Заполняем клиента
                    phone.put("name", cursor4.getString(1));
                    phone.put("adress", cursor4.getString(2));
                    phone.put("phone", cursor4.getString(3));
                    //Закидываем клиента в список клиентов
                    phones4.add(phone);

                    //Переходим к следующему клиенту
                    cursor4.moveToNext();
                }
                cursor4.close();

//Какие параметры клиента мы будем отображать в соответствующих
//элементах из разметки adapter_item.xml
                from = new String[]{"name", "adress", "phone"};
                to = new int[]{R.id.textView, R.id.textView2, R.id.textView3};

//Создаем адаптер
                SimpleAdapter adapter4 = new SimpleAdapter(this, phones4, R.layout.adapter_item, from, to);
                ListView listView4 = findViewById(R.id.listView);
                listView4.setAdapter(adapter4);
                return true;

            case R.id.action_tel5:
                tablename = "SELECT * FROM obrazovanie";
                setTitle(R.string.action_tel5);
                //Переменная для работы с БД
                DatabaseHelper mDBHelper5 = new DatabaseHelper(this);

                try {
                    mDBHelper5.updateDataBase();
                } catch (IOException mIOException) {
                    throw new Error("UnableToUpdateDatabase");
                }

                SQLiteDatabase mDb5 = mDBHelper5.getWritableDatabase();


//Список телефонов
                ArrayList<HashMap<String, Object>> phones5;
                phones5 = new ArrayList<>();

//Список параметров конкретного клиента

//Отправляем запрос в БД
                Cursor cursor5 = mDb5.rawQuery(tablename, null);
                cursor5.moveToFirst();

//Пробегаем по всем памятникам
                while (!cursor5.isAfterLast()) {
                    phone = new HashMap<>();

                    //Заполняем клиента
                    phone.put("name", cursor5.getString(1));
                    phone.put("adress", cursor5.getString(2));
                    phone.put("phone", cursor5.getString(3));
                    //Закидываем клиента в список клиентов
                    phones5.add(phone);

                    //Переходим к следующему клиенту
                    cursor5.moveToNext();
                }
                cursor5.close();

//Какие параметры клиента мы будем отображать в соответствующих
//элементах из разметки adapter_item.xml
                from = new String[]{"name", "adress", "phone"};
                to = new int[]{R.id.textView, R.id.textView2, R.id.textView3};

//Создаем адаптер
                SimpleAdapter adapter5 = new SimpleAdapter(this, phones5, R.layout.adapter_item, from, to);
                ListView listView5 = findViewById(R.id.listView);
                listView5.setAdapter(adapter5);
                return true;


            case R.id.action_tel6:
                tablename = "SELECT * FROM transport";
                setTitle(R.string.action_tel6);
                //Переменная для работы с БД
                DatabaseHelper mDBHelper6 = new DatabaseHelper(this);

                try {
                    mDBHelper6.updateDataBase();
                } catch (IOException mIOException) {
                    throw new Error("UnableToUpdateDatabase");
                }

                SQLiteDatabase mDb6 = mDBHelper6.getWritableDatabase();


//Список телефонов
                ArrayList<HashMap<String, Object>> phones6;
                phones6 = new ArrayList<>();

//Список параметров конкретного клиента

//Отправляем запрос в БД
                Cursor cursor6 = mDb6.rawQuery(tablename, null);
                cursor6.moveToFirst();

//Пробегаем по всем памятникам
                while (!cursor6.isAfterLast()) {
                    phone = new HashMap<>();

                    //Заполняем клиента
                    phone.put("name", cursor6.getString(1));
                    phone.put("adress", cursor6.getString(2));
                    phone.put("phone", cursor6.getString(3));
                    //Закидываем клиента в список клиентов
                    phones6.add(phone);

                    //Переходим к следующему клиенту
                    cursor6.moveToNext();
                }
                cursor6.close();

//Какие параметры клиента мы будем отображать в соответствующих
//элементах из разметки adapter_item.xml
                from = new String[]{"name", "adress", "phone"};
                to = new int[]{R.id.textView, R.id.textView2, R.id.textView3};

//Создаем адаптер
                SimpleAdapter adapter6 = new SimpleAdapter(this, phones6, R.layout.adapter_item, from, to);
                ListView listView6 = findViewById(R.id.listView);
                listView6.setAdapter(adapter6);
                return true;


            case R.id.action_tel7:
                tablename = "SELECT * FROM dosug";
                setTitle(R.string.action_tel7);
                //Переменная для работы с БД
                DatabaseHelper mDBHelper7 = new DatabaseHelper(this);

                try {
                    mDBHelper7.updateDataBase();
                } catch (IOException mIOException) {
                    throw new Error("UnableToUpdateDatabase");
                }

                SQLiteDatabase mDb7 = mDBHelper7.getWritableDatabase();


//Список телефонов
                ArrayList<HashMap<String, Object>> phones7;
                phones7 = new ArrayList<>();

//Список параметров конкретного клиента

//Отправляем запрос в БД
                Cursor cursor7 = mDb7.rawQuery(tablename, null);
                cursor7.moveToFirst();

//Пробегаем по всем памятникам
                while (!cursor7.isAfterLast()) {
                    phone = new HashMap<>();

                    //Заполняем клиента
                    phone.put("name", cursor7.getString(1));
                    phone.put("adress", cursor7.getString(2));
                    phone.put("phone", cursor7.getString(3));
                    //Закидываем клиента в список клиентов
                    phones7.add(phone);

                    //Переходим к следующему клиенту
                    cursor7.moveToNext();
                }
                cursor7.close();

//Какие параметры клиента мы будем отображать в соответствующих
//элементах из разметки adapter_item.xml
                from = new String[]{"name", "adress", "phone"};
                to = new int[]{R.id.textView, R.id.textView2, R.id.textView3};

//Создаем адаптер
                SimpleAdapter adapter7 = new SimpleAdapter(this, phones7, R.layout.adapter_item, from, to);
                ListView listView7 = findViewById(R.id.listView);
                listView7.setAdapter(adapter7);
                return true;



            case R.id.action_tel8:
                tablename = "SELECT * FROM finans";
                setTitle(R.string.action_tel8);
                //Переменная для работы с БД
                DatabaseHelper mDBHelper8 = new DatabaseHelper(this);

                try {
                    mDBHelper8.updateDataBase();
                } catch (IOException mIOException) {
                    throw new Error("UnableToUpdateDatabase");
                }

                SQLiteDatabase mDb8 = mDBHelper8.getWritableDatabase();


//Список телефонов
                ArrayList<HashMap<String, Object>> phones8;
                phones8 = new ArrayList<>();

//Список параметров конкретного клиента

//Отправляем запрос в БД
                Cursor cursor8 = mDb8.rawQuery(tablename, null);
                cursor8.moveToFirst();

//Пробегаем по всем памятникам
                while (!cursor8.isAfterLast()) {
                    phone = new HashMap<>();

                    //Заполняем клиента
                    phone.put("name", cursor8.getString(1));
                    phone.put("adress", cursor8.getString(2));
                    phone.put("phone", cursor8.getString(3));
                    //Закидываем клиента в список клиентов
                    phones8.add(phone);

                    //Переходим к следующему клиенту
                    cursor8.moveToNext();
                }
                cursor8.close();

//Какие параметры клиента мы будем отображать в соответствующих
//элементах из разметки adapter_item.xml
                from = new String[]{"name", "adress", "phone"};
                to = new int[]{R.id.textView, R.id.textView2, R.id.textView3};

//Создаем адаптер
                SimpleAdapter adapter8 = new SimpleAdapter(this, phones8, R.layout.adapter_item, from, to);
                ListView listView8 = findViewById(R.id.listView);
                listView8.setAdapter(adapter8);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}*/