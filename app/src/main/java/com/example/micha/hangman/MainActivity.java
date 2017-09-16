package com.example.micha.hangman;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.ToggleButton;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final ToggleButton toggle = (ToggleButton) findViewById(R.id.toggleButton_lang);
        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if ((null != toggle && toggle.isChecked())) {
                    CharSequence text = "Bytter til Engelsk";
                    Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG).show();
                } else {
                    CharSequence text = "Switching to Norwegian";
                    Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG).show();
                }
            }
        });

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

    }

}
