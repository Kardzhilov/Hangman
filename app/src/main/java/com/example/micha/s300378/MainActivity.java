package com.example.micha.s300378;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import java.util.Locale;


public class MainActivity extends AppCompatActivity {

    static Stats stat = new Stats();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final View start = findViewById(R.id.button_start);
        start.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent startGame = new Intent(v.getContext(), Game.class);
                startActivity(startGame);
            }
        });

        final View rule = findViewById(R.id.button_rule);
        rule.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent showRule = new Intent(v.getContext(), GRules.class);
                startActivity(showRule);
            }
        });

        final View stat = findViewById(R.id.button_stats);
        stat.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent showRule = new Intent(v.getContext(), GStatistics.class);
                startActivity(showRule);
            }
        });


    }

    public void changeLanguage(View v) {
        String langu = v.getTag().toString();
        Log.d("MainActivity", "Language button was clicked!");
        Configuration config = getBaseContext().getResources().getConfiguration();
        Locale locale = new Locale(langu);
        Locale.setDefault(locale);
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        recreate();
    }
}


