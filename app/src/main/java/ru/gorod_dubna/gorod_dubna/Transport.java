package ru.gorod_dubna.gorod_dubna;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;

import com.my.target.ads.MyTargetView;

public class Transport extends AppCompatActivity {

    private MyTargetView adViewTrans;
    private WebView mWebView3;
    String url="http://город-дубна.рф//timetable/citybus";
    @TargetApi(Build.VERSION_CODES.ECLAIR_MR1)
    @RequiresApi(api = Build.VERSION_CODES.ECLAIR_MR1)
    @SuppressLint("SetJavaScriptEnabled")
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transport);
        final RelativeLayout layout = findViewById(R.id.activityLayoutTrans);

        mWebView3 = findViewById(R.id.webView3);
        // устанавливаем Zoom control
        mWebView3.getSettings().setBuiltInZoomControls(true);
        // создаем клиент
        mWebView3.setWebViewClient(new MyWebViewClient());
        // включаем поддержку JavaScript
        mWebView3.getSettings().setJavaScriptEnabled(true);
        // делаем чтобы страница помещалась в размер экрана
        mWebView3.setInitialScale(1);
        mWebView3.getSettings().setLoadWithOverviewMode(true);
        mWebView3.getSettings().setUseWideViewPort(true) ;
        //Чистим кэш
        mWebView3.clearCache(true);
        // указываем страницу загрузки
        mWebView3.loadUrl(url);


        // Включение режима отладки
        //MyTargetView.setDebugMode(true);

        // Создаем экземпляр MyTargetView, формат 320х50
        adViewTrans = new MyTargetView(this);

        // Создаем экземпляр MyTargetView, формат 300х250
        // adView = new MyTargetView(this, AdSize.BANNER_300x250);

        // Инициализируем экземпляр
        adViewTrans.init(380770);


        // Устанавливаем слушатель событий
        adViewTrans.setListener(new MyTargetView.MyTargetViewListener() {
            @Override
            public void onLoad(@NonNull MyTargetView myTargetView) {
                // Данные успешно загружены, запускаем показ объявлений
                layout.addView(adViewTrans);
            }

            @Override
            public void onNoAd(@NonNull String reason, @NonNull MyTargetView myTargetView) {
            }

            @Override
            public void onClick(@NonNull MyTargetView myTargetView) {
            }
        });

        // Запускаем загрузку данных
        adViewTrans.load();
    }

    private class MyWebViewClient extends WebViewClient
    {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url)
        {
            view.loadUrl(url);
            return true;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_transport, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // получим идентификатор выбранного пункта меню
        int id = item.getItemId();
        // Операции для выбранного пункта меню
        switch (id) {

            case R.id.action_transport:
                mWebView3.loadUrl("http://город-дубна.рф//timetable/citybus");
                setTitle(R.string.action_transport);
                return true;

            case R.id.action_transport1:
                mWebView3.loadUrl("https://t.rasp.yandex.ru/city/215");
                setTitle(R.string.action_transport1);
                return true;

            case R.id.action_transport2:
                mWebView3.loadUrl("https://t.rasp.yandex.ru/station/9601639/suburban/?filter=all");
                setTitle(R.string.action_transport2);
                return true;
            case R.id.action_transport3:
                mWebView3.loadUrl("https://t.rasp.yandex.ru/station/9601720/suburban/?direction=all");
                setTitle(R.string.action_transport3);
                return true;
            case R.id.action_transport4:
                mWebView3.loadUrl("https://t.rasp.yandex.ru/station/9739655/");
                setTitle(R.string.action_transport4);
                return true;
            case R.id.action_transport5:
                mWebView3.loadUrl("https://t.rasp.yandex.ru/station/9748264/");
                setTitle(R.string.action_transport5);
                return true;
            case R.id.action_transport6:
                mWebView3.loadUrl("https://t.rasp.yandex.ru/station/9858021/?filter=all");
                setTitle(R.string.action_transport6);
                return true;
            case R.id.action_transport7:
                mWebView3.loadUrl("https://t.rasp.yandex.ru/station/9822157/");
                setTitle(R.string.action_transport7);
                return true;
            case R.id.action_transport8:
                mWebView3.loadUrl("https://t.rasp.yandex.ru/station/9739654/");
                setTitle(R.string.action_transport8);
                return true;
            case R.id.action_transport9:
                mWebView3.loadUrl("https://t.rasp.yandex.ru/station/9739657/");
                setTitle(R.string.action_transport9);
                return true;
            case R.id.action_transport10:
                mWebView3.loadUrl("file:///android_asset/avtobus_dubna_mos.jpg");
                setTitle(R.string.action_transport10);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
