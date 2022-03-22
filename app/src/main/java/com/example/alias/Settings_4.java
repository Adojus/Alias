package com.example.alias;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.fragment.NavHostFragment;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class Settings_4 extends AppCompatActivity {

    int time;

    boolean sound_state;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_4);
        MediaPlayer sound_effect=MediaPlayer.create(this,R.raw.sound_effect);
        Button Next = findViewById(R.id.button_settings_next_4);
       // Next.setOnClickListener(v -> startActivity(new Intent(Settings_4.this, Team_Scores_5.class)));

        Switch sound = findViewById(R.id.switch2);
        sound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sound_state=sound.isChecked();
                if(sound_state==true)
                    sound_effect.start();
            }
        });
        Switch penalty = findViewById(R.id.switch1);
        penalty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(sound_state){
                    sound_effect.start();
                }
            }
        });

        SeekBar seekBarLength = findViewById(R.id.seekBar_settings_round_length);
        seekBarLength.setProgress(10);
        seekBarLength.setMax(100);
        TextView seekBarL = findViewById(R.id.textView_seek_bar_length);

        //Laiko vienam ejimui seekbaras
        seekBarLength.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seekBarL.setText(String.valueOf(progress));
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });



        //Maximumo tasku seekbaras
        SeekBar seekBarPoints = findViewById(R.id.seekBar_settings_max_points);
        seekBarPoints.setProgress(10);
        seekBarPoints.setMax(100);
        TextView seekBarP = findViewById(R.id.textView_seek_bar_points);

        seekBarPoints.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seekBarP.setText(String.valueOf(progress));
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(sound_state){
                    sound_effect.start();
                }
                Intent x = new Intent(view.getContext(),Team_Scores_5.class);
                x.putExtra("timer",Integer.valueOf(seekBarL.getText().toString()));
                startActivity(x);
            }
        });

        Button Previous = findViewById(R.id.button_4_previous);
        //Previous.setOnClickListener(v -> startActivity(new Intent(Settings_4.this, Team_Count_3.class)));
        Previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(sound_state){
                    sound_effect.start();
                }
                Intent x = new Intent(view.getContext(),Team_Count_3.class);
                Bundle extra = new Bundle();
                extra.putBoolean("sound", sound_state);
                x.putExtra("extra", extra);
                startActivity(x);
            }
        });
    }
}