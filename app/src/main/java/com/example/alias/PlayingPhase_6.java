package com.example.alias;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;
import java.util.Timer;

public class PlayingPhase_6 extends AppCompatActivity {
    //timer variables
    CountDownTimer cdTimer;
    //long timeLeftInMillis;
    TextView Timer;
    long timer=0;
    Button Start_Timer;
    Button continue2;
    Button button_guessed;
    Button button_skipped;
    int guessed_amount=0;
    int skipped_amount=0;
    TextView label_num_of_guessed_words;
    TextView label_num_of_skipped_words;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playing_phase_6);

        MediaPlayer sound_effect=MediaPlayer.create(this,R.raw.sound_effect);
        Bundle extra = getIntent().getBundleExtra("extra");
        boolean sound_state = extra.getBoolean("sound");

        Bundle timeris = getIntent().getExtras();
        //int timer;
        timer=timeris.getInt("timer");
        Timer=findViewById(R.id.timer);
        Timer.setText(String.valueOf(timer));


        label_num_of_guessed_words=findViewById(R.id.label_num_of_guessed_words_6);
        button_guessed =findViewById(R.id.button_guessed);
        button_guessed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(sound_state){
                    sound_effect.start();
                }
                guessed_amount++;
                label_num_of_guessed_words.setText(Integer.toString(guessed_amount));
            }
        });
        label_num_of_skipped_words=findViewById(R.id.label_num_of_skipped_words_6);
        button_skipped=findViewById(R.id.button_skipped);
        button_skipped.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(sound_state){
                    sound_effect.start();
                }
                skipped_amount++;
                label_num_of_skipped_words.setText(Integer.toString(skipped_amount));
            }
        });

        button_guessed.setEnabled(false);
        button_skipped.setEnabled(false);
        //mygtukas pradeti timer
        Start_Timer = findViewById(R.id.Start_True);
        Start_Timer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(sound_state){
                    sound_effect.start();
                }
                //enabling button "stop"
                ((Button) findViewById(R.id.button_stop)).setEnabled(true);
                button_guessed.setEnabled(true);
                button_skipped.setEnabled(true);
                Start_Timer.setEnabled(false);
                startCDTimer();
            }
        });
        //pabaiga
        //Bundle bundle = new Bundle();
        //int timer = bundle.getInt(timer);
        //timer start
       // new CountDownTimer(timer*1000, 1000) { // 1 - sekundziu skaicius, 2- mazejimo zingsnis



        //STOP mygtukas
        Button stop_button =findViewById(R.id.button_stop);
        //Continue button
        continue2=(Button) findViewById(R.id.button_continue2);

        stop_button.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(sound_state){
                sound_effect.start();
            }
            stopCDTimer();
            //Intent intent = new Intent(PlayingPhase_6.this, StoppedPhase_7.class);
            //startActivity(intent);

            //continue2=(Button) findViewById(R.id.button_continue2);
            continue2.setVisibility(View.VISIBLE);
            stop_button.setVisibility(View.INVISIBLE);
            button_guessed.setEnabled(false);
            button_skipped.setEnabled(false);
        }
        });

        //button "continue" to resume timer
        continue2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(sound_state){
                    sound_effect.start();
                }
                startCDTimer();
                //Intent intent = new Intent(PlayingPhase_6.this, StoppedPhase_7.class);
                //startActivity(intent);
                button_guessed.setEnabled(true);
                button_skipped.setEnabled(true);
                //continue2=(Button) findViewById(R.id.button_continue2);
                continue2.setVisibility(View.INVISIBLE);
                stop_button.setVisibility(View.VISIBLE);
            }
        });

    }
    //timer end

    //timer starts counting/resumes counting
    private void startCDTimer(){

        String language= String.valueOf(getResources().getConfiguration().locale);
       cdTimer= new CountDownTimer(timer*1000+50, 1000) {

            public void onTick(long millisUntilFinished) {
                Timer.setText(" "+millisUntilFinished / 1000+" ");
                Start_Timer.setVisibility(View.INVISIBLE);
                timer=millisUntilFinished/1000;
            }
            public void onFinish() {
                if(language.equals("lt_LT"))
                    Timer.setText("Paskutinis žodis!");
                else
                    Timer.setText("Last word!");
            }

        }.start();
    }

    //timer is stopped
    private void stopCDTimer(){

        cdTimer.cancel();

    }

}