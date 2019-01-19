package ru.gorod_dubna.gorod_dubna;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ScrollView;
import android.widget.TextView;

public class Telefony extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_telefony);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_telefony, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // получим идентификатор выбранного пункта меню
        int id = item.getItemId();
        ScrollView sv1 = findViewById(R.id.sv1);

        TextView telTextView = findViewById(R.id.textView3);

        // Операции для выбранного пункта меню
        switch (id) {
            case R.id.action_tel:
                telTextView.setText(R.string.extr_tel);
                setTitle(R.string.action_tel);
                sv1.scrollTo(5, 10);
                return true;
            case R.id.action_tel2:
                telTextView.setText(R.string.administr);
                setTitle(R.string.action_tel2);
                sv1.scrollTo(5, 10);
                return true;
            case R.id.action_tel3:
                telTextView.setText(R.string.gos);
                setTitle(R.string.action_tel3);
                sv1.scrollTo(5, 10);
                return true;
            case R.id.action_tel4:
                telTextView.setText(R.string.med);
                setTitle(R.string.action_tel4);
                sv1.scrollTo(5, 10);
                return true;
            case R.id.action_tel5:
                telTextView.setText(R.string.obrazovanie);
                setTitle(R.string.action_tel5);
                sv1.scrollTo(5, 10);
                return true;
            case R.id.action_tel6:
                telTextView.setText(R.string.transport);
                setTitle(R.string.action_tel6);
                sv1.scrollTo(5, 10);
                return true;
            case R.id.action_tel7:
                telTextView.setText(R.string.razvl);
                setTitle(R.string.action_tel7);
                sv1.scrollTo(5, 10);
                return true;
            case R.id.action_tel8:
                telTextView.setText(R.string.finansy);
                setTitle(R.string.action_tel8);
                sv1.scrollTo(5, 10);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
