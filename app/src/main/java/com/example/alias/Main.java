package com.example.alias;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import java.util.Locale;

public class Main extends AppCompatActivity {

    @Override
    public void onBackPressed() {

    }
    // --------main game variable ----- public, thus we can use it everywhere----
    public static Game game = new Game();
    //--------------------------------------------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu_1);
        loadLocale();

       MediaPlayer sound_effect=MediaPlayer.create(this,R.raw.sound_effect);
        MediaPlayer music_effect=MediaPlayer.create(this,R.raw.music_effect);

        Button button_lang = findViewById(R.id.button_lang);

        // START button ----------------------------------------------------------------------------
        Button Start = findViewById(R.id.button_start);
        Start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(game.getIsSound() == true)
                    sound_effect.start();
                Intent intent = new Intent(getApplicationContext(), Team_Count_3.class);
                Bundle extra = new Bundle();
                extra.putBoolean("sound", game.getIsSound());
                intent.putExtra("extra", extra);
                startActivity(intent);
                finish();
            }
        });


        // LANGUAGE button -------------------------------------------------------------------------
        button_lang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                changeLanguage(button_lang);
                if(game.getIsSound())
                    sound_effect.start();
            }
        });


        // HOW TO PLAY button ----------------------------------------------------------------------
        Button HowToPlay =findViewById(R.id.button_howtoplay);

        HowToPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), How_To_Play_2.class);
                if(game.getIsSound())
                    sound_effect.start();
                Bundle extra = new Bundle();
                extra.putBoolean("sound", game.getIsSound());
                intent.putExtra("extra", extra);
                startActivity(intent);
            }
        });
        String language= String.valueOf(getResources().getConfiguration().locale);

        // SOUND switch ----------------------------------------------------------------------------
        TextView sound_off = findViewById(R.id.sounds_off_textview);
        Switch sound = findViewById(R.id.sound_effect_switch);
        sound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                game.setIsSound(sound.isChecked());
             //   sound_state=sound.isChecked();
                if(game.getIsSound()) {
                    if(language.equals("lt_LT")){
                        sound_effect.start();
                        sound_off.setText("Įjungti");}
                    else {
                        sound_effect.start();
                        sound_off.setText("ON");
                    }
                }
                else{
                    if(language.equals("lt_LT"))
                        sound_off.setText("Išjungti");
                    else
                    sound_off.setText("OFF");
                }
            }
        });

        // MUSIC switch ----------------------------------------------------------------------------
        TextView music_off = findViewById(R.id.music_off_textview);
        Switch music = findViewById(R.id.Music_switch);
        music.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                game.setIsMusic(music.isChecked());
               // sound_state=music.isChecked();
                if(game.getIsMusic()) {
                    if(language.equals("lt_LT"))
                        music_off.setText("Įjungta");
                    else
                        music_off.setText("ON");

                    music_effect.start();
                    music_effect.setLooping(true);
                }
                else {
                    if(language.equals("lt_LT"))
                        music_off.setText("Išjungta");
                    else
                    music_off.setText("OFF");

                    music_effect.pause();
                }
            }
        });
        if(music_effect.isPlaying()){
            music.setChecked(true);
        }
    }

    private void changeLanguage(Button button) {
        if (button.getText().equals("LT")){

            setLocale("lt");
            game.setLanguage(false);
            recreate();
        }
        else {
            setLocale("en");
            game.setLanguage(true);
            recreate();
        }
    }

    private void setLocale(String lang) {
        String country;
        if (lang.equals("lt"))
            country = "LT";
        else country = "US";

        Locale locale = new Locale(lang, country);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        SharedPreferences.Editor editor = getSharedPreferences("Settings", MODE_PRIVATE).edit();
        editor.putString("My_Lang", lang);
        editor.apply();

    }

    public void loadLocale(){
        SharedPreferences prefs = getSharedPreferences("Settings", Activity.MODE_PRIVATE);
        String language = prefs.getString("My_Lang", "");
        setLocale(language);
    }
}