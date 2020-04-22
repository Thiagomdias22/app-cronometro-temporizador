package com.example.cronometro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Runnable r;
    Handler h;
    boolean execucao = false;
    TextView timeView;
    int s=0,m=0,hr=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        runTimer();
    }
    public void onClickStart(View view){
        if(!execucao){
            r.run();
            execucao=true;
        }
    }
    public void onClickStop(View view){
        execucao=false;
        h.removeCallbacks(r);


    }
    public void onClickReset(View view){
        s=0;
        m=0;
        hr=0;
        String time = String.format("%d:%02d:%02d", hr,s,m);
        timeView.setText(time);
    }
    private void runTimer() {
        timeView = (TextView) findViewById(R.id.TV_cronometro);
        h = new Handler();
        r = new Runnable() {
            @Override
            public void run() {
                String time = String.format("%d:%02d:%02d", hr, m, s);
                timeView.setText(time);
                s++;
                h.postDelayed(this, 10);
                if (s == 59) {
                    m++;
                    s = 0;
                }
                if (m == 59) {
                    hr++;
                    m = 0;
                }
            }
        };
    }
    public void onClickTemp(View view){
        Intent intent = new Intent(MainActivity.this, NovaActivity.class);
        startActivity(intent);
        }
}
