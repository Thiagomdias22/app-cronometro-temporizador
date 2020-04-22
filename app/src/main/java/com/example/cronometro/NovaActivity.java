package com.example.cronometro;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class NovaActivity extends AppCompatActivity {

    private static final long Start_Time_in_milles = 600000;
    private TextView ViewText;
    private Button bStart;
    private Button Reset;
    private CountDownTimer countDownTimer;
    private boolean mTimerrunning;
    private long mTimerleftinMilles = Start_Time_in_milles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nova);

        ViewText = findViewById(R.id.TV_temporizador);
        bStart = findViewById(R.id.b_start_pause);
        Reset = findViewById(R.id.button_reset);

        bStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mTimerrunning){
                    onClickStop();
                }else{
                    onClickStart();
                }
            }
        });
        Reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickReset();
            }
        });
        updateCountDownNext();

    }
    public void onClickStart(){
        countDownTimer = new CountDownTimer(mTimerleftinMilles, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimerleftinMilles = millisUntilFinished;
                updateCountDownNext();
            }

            @Override
            public void onFinish() {
                mTimerrunning = false;
                bStart.setText("Start");
                bStart.setVisibility(View.INVISIBLE);
                Reset.setVisibility(View.VISIBLE);
            }
        }.start();
        mTimerrunning = true;
        bStart.setText("pause");
        Reset.setVisibility(View.INVISIBLE);
    }
    public void onClickStop(){
        countDownTimer.cancel();
        mTimerrunning = false;
        bStart.setText("Start");
        Reset.setVisibility(View.VISIBLE);
    }
    public void onClickReset(){
        mTimerleftinMilles = Start_Time_in_milles;
        updateCountDownNext();
        Reset.setVisibility(View.INVISIBLE);
        bStart.setVisibility(View.VISIBLE);
    }
    public void updateCountDownNext(){
        //int h = (int) (mTimerleftinMilles / 1000) * 60;
        int m = (int) (mTimerleftinMilles / 1000) / 60;
        int s = (int) (mTimerleftinMilles / 1000) % 60;

        String time = String.format("%02d:%02d", m,s);
        ViewText.setText(time);
    }

}
