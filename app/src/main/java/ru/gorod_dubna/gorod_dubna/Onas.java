package ru.gorod_dubna.gorod_dubna;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;


public class Onas extends AppCompatActivity implements View.OnClickListener {
    ImageButton imageButton;
    ImageButton imageButton2;
    TextView textView4;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onas);

        imageButton = findViewById(R.id.imageButton);
        imageButton.setOnClickListener(this);

        imageButton2 = findViewById(R.id.imageButton2);
        imageButton2.setOnClickListener(this);

        textView4 = findViewById(R.id.textView4);
        textView4.setText("Версия: "+BuildConfig.VERSION_NAME);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageButton:
                Intent intent2 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/dev?id=6727613984034321120"));
                startActivity(intent2);
                break;

            case R.id.imageButton2:
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "Советую приложение 'Город Дубна'\n https://play.google.com/store/apps/details?id=ru.gorod_dubna.gorod_dubna" );
                sendIntent.setType("text/plain");
                startActivity(Intent.createChooser(sendIntent,"Поделиться приложением..."));
            break;
            default:
                break;
        }
     }
}
