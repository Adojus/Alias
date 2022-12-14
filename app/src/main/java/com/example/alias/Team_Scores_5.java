package com.example.alias;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ActivityManager;
import android.app.FragmentManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class Team_Scores_5 extends AppCompatActivity{

    public void onBackPressed() {

        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            getSupportFragmentManager().popBackStack();
        } else {
            finish();
        }
    }
    private String getCallerFragment(){
        FragmentManager fm = getFragmentManager();
        int count = getFragmentManager().getBackStackEntryCount();
        return fm.getBackStackEntryAt(count - 2).getName();
    }
    @Override
    protected void onPause() {
        super.onPause();
        if (Main.game.getMusic() != null){
            Main.game.getMusic().pause();
            if (isFinishing()){
                Main.game.getMusic().pause();
            }
        }
        Context context = getApplicationContext();
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> taskInfo = am.getRunningTasks(1);
        if (!taskInfo.isEmpty()) {
            ComponentName topActivity = taskInfo.get(0).topActivity;
            if (!topActivity.getPackageName().equals(context.getPackageName())) {
                Main.game.getMusic().pause();
            }
        }
        super.onPause();
    }
    @Override
    protected void onResume() {

        if(!Main.game.getIsMusic()){
            Main.game.getMusic().stop();
        }
        if(Main.game.getMusic() != null && !Main.game.getMusic().isPlaying() && Main.game.getIsMusic());
        Main.game.getMusic().start();
        super.onResume();
    }
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_scores_5);

        MediaPlayer sound_effect=MediaPlayer.create(this,R.raw.sound_effect);



        RecyclerView rvTeams = (RecyclerView) findViewById(R.id.rv_teams);

        Team_Adapter adapter = new Team_Adapter(Main.game.getAll_teams(), false);
        rvTeams.setAdapter(adapter);
        rvTeams.setLayoutManager(new LinearLayoutManager(this));

        // -----------------------------------------------------------------------------------------
        if(Main.game.Visit_of_fragment_6())
        Main.game.updateCurrentRoundNum(); // ---------------------- veeeery important method
        // -----------------------------------------------------------------------------------------


        TextView label_next_team = findViewById(R.id.textView_nextTeam);
        label_next_team.setText(Main.game.getCurrentlyPlayingTeam().getName());

        TextView label_round = findViewById(R.id.textView_round);
        String round_name;
        if(Main.game.getIsEnglish()){
            round_name = "ROUND ";
        } else{
            round_name = "RAUNDAS ";
        }

        if(Main.game.Visit_of_fragment_6())
        label_round.setText(round_name + Integer.toString(Main.game.getCurrentRoundNum()));
        else {
            label_round.setText(round_name + Integer.toString(1));
        }


        // START button ----------------------------------------------------------------------------
        Button Start = findViewById(R.id.button_start_game_5);
        Start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Main.game.getIsSound()){
                    sound_effect.start();
                }

                Intent x = new Intent(view.getContext(), Playing_Phase_6.class);
                Bundle extras = getIntent().getExtras();
                x.putExtra("timer",Main.game.getTimeOfOneRound());
                Bundle extra = new Bundle();
                extra.putBoolean("sound", Main.game.getIsSound());
                x.putExtra("extra", extra);
                startActivity(x);
            }
        });

    }
}
