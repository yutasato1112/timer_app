package com.example.timer_app;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.Locale;


public class MainActivity extends AppCompatActivity {
    private static final long START_TIME_IN_MILLIS=30000;

    private TextView mTextViewCountDown;
    private Button mButtonStartPause;
    private Button mButtonReset;

    private CountDownTimer mCountDownTimer;
    private boolean mTimerRunning;

    private  long mTimerLeftInMillis = START_TIME_IN_MILLIS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextViewCountDown = findViewById(R.id.text_view_countdown);
        mButtonStartPause = findViewById(R.id.button_start_pause);
        mButtonReset = findViewById(R.id.buttonreset);

        mButtonStartPause.setOnClickListener (new View.OnClickListener(){
              @Override
              public void onClick(View v){
                  System.out.println(mTimerRunning);
                  if(mTimerRunning){
                      pauseTimer();
                  }else{
                      startTimer();
                  }
              }
        });
        mButtonReset.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                resetTimer();
            }
        });

        updateCountDownText();
    }
    private void startTimer(){
        mCountDownTimer = new CountDownTimer(mTimerLeftInMillis,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimerLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }
            @Override
            public void onFinish() {
                mTimerRunning = false;
                mButtonStartPause.setText("スタート");
                mButtonReset.setVisibility(View.INVISIBLE);
            }
        }.start();
        mTimerRunning = true;
        mButtonStartPause.setText("一時停止");
        mButtonReset.setVisibility(View.INVISIBLE);
    }
    private void pauseTimer(){
        mCountDownTimer.cancel();
        mTimerRunning = false;
        mButtonStartPause.setText("スタート");
        mButtonReset.setVisibility(View.VISIBLE);
    }
    private void resetTimer(){
        mTimerLeftInMillis = START_TIME_IN_MILLIS;
        updateCountDownText();
        mButtonStartPause.setVisibility(View.INVISIBLE);
        mButtonReset.setVisibility(View.INVISIBLE);
    }
    private void updateCountDownText(){
        int minutes = (int)(mTimerLeftInMillis/1000)/60;
        int seconds = (int)(mTimerLeftInMillis/1000)%60;
        String timerLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
        mTextViewCountDown.setText(timerLeftFormatted);
    }
}
