package ru.gorod_dubna.gorod_dubna;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;


public class News extends AppCompatActivity {
    private WebView mWebView;
    String url="http://город-дубна.рф/all_news/all_news";
    String currentUrl=url;
    @TargetApi(Build.VERSION_CODES.ECLAIR_MR1)
    @RequiresApi(api = Build.VERSION_CODES.ECLAIR_MR1)
    @SuppressLint("SetJavaScriptEnabled")
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

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
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_news, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // получим идентификатор выбранного пункта меню
        int id = item.getItemId();
        // Операции для выбранного пункта меню
        switch (id) {
            case R.id.action_share:
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "Прочитай интересную новость:\n "+currentUrl+"\n\nПередано из приложения 'Город Дубна'" );
                sendIntent.setType("text/plain");
                startActivity(Intent.createChooser(sendIntent,"Поделиться"));

            case R.id.action_news:
                mWebView.loadUrl("http://город-дубна.рф/all_news/all_news");
                return true;
            case R.id.action_news1:
                mWebView.loadUrl("http://город-дубна.рф/all_news/gornov");
                return true;
            case R.id.action_news2:
                mWebView.loadUrl("http://город-дубна.рф/all_news/polek");
                return true;
            case R.id.action_news3:
                mWebView.loadUrl("http://город-дубна.рф/all_news/ludi");
                return true;
            case R.id.action_news4:
                mWebView.loadUrl("http://город-дубна.рф/all_news/priob");
                return true;
            case R.id.action_news5:
                mWebView.loadUrl("http://город-дубна.рф/all_news/kulrel");
                return true;
            case R.id.action_news6:
                mWebView.loadUrl("http://город-дубна.рф/all_news/tehteh");
                return true;
            case R.id.action_news7:
                mWebView.loadUrl("http://gorod-kimry.ru/all_news/krim");
                return true;
            case R.id.action_news8:
                mWebView.loadUrl("http://город-дубна.рф/all_news/istor");
                return true;
            case R.id.action_news9:
                mWebView.loadUrl("http://город-дубна.рф/all_news/sporot");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
