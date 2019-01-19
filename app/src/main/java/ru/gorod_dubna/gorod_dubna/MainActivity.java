package ru.gorod_dubna.gorod_dubna;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements OnClickListener {

    Button button2;
    Button button3;
    Button button;
    Button button4;
    Button button5;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
            default:
                break;
        }
    }

}
