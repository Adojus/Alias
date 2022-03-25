package com.example.alias;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class Team_Count_3 extends AppCompatActivity {
    public static int num_of_teams = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_count_3);

        MediaPlayer sound_effect=MediaPlayer.create(this,R.raw.sound_effect);
        Bundle extra = getIntent().getBundleExtra("extra");
        boolean sound_state = extra.getBoolean("sound");
        //mygtukas Atgal
        Button Atgal = findViewById(R.id.button_MainMenu);
        //Atgal.setOnClickListener(v -> startActivity(new Intent(Team_Count_3.this, MainMenu_1.class)));
        Atgal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(sound_state)
                    sound_effect.start();
                Intent intent = new Intent(getApplicationContext(), MainMenu_1.class);
                startActivity(intent);
                finish();
            }
        });
        //mygtukas Atgal

        EditText num_of_teams_label = findViewById(R.id.num_of_teams);
        Button next_num_of_teams = findViewById(R.id.button_next_num_of_teams);

        // button next
        next_num_of_teams.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(sound_state){
                    sound_effect.start();
                }
                // netrinti!!!
                // create team arrayList
                MainMenu_1.teams = Team.createTeamList(Team_Count_3.num_of_teams - 1);

                num_of_teams = Integer.parseInt(num_of_teams_label.getText().toString());
                TextView validation = findViewById(R.id.num_of_teams_validation_label);

                if(num_of_teams >= 1 && num_of_teams <= 10){
                    validation.setVisibility(View.INVISIBLE);

                    // Button to go settings page
                    Intent intent = new Intent(getApplicationContext(), Settings_4.class);
                    Bundle extra = new Bundle();
                    extra.putBoolean("sound", sound_state);
                    intent.putExtra("extra", extra);
                    startActivity(intent);
                    finish();

                } else
                {
                    validation.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}