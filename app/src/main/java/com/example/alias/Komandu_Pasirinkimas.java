package com.example.alias;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Komandu_Pasirinkimas extends AppCompatActivity {
    public static int num_of_teams = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_komandu_pasirinkimas);

        //mygtukas Atgal
        Button Atgal = findViewById(R.id.button_MainMenu);
        Atgal.setOnClickListener(v -> finish());
        //mygtukas Atgal

        EditText num_of_teams_label = findViewById(R.id.num_of_teams);
        Button next_num_of_teams = findViewById(R.id.button_next_num_of_teams);

        // button next
        next_num_of_teams.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num_of_teams = Integer.parseInt(num_of_teams_label.getText().toString());
                TextView validation = findViewById(R.id.num_of_teams_validation_label);

                if(num_of_teams >= 1 && num_of_teams <= 10){
                    validation.setVisibility(View.INVISIBLE);

                    // let go to another page
                    // COMPLETE THIS PLACE
                }
                else
                {
                    validation.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}