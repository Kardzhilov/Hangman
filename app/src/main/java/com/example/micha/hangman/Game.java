package com.example.micha.hangman;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Game extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
    }

    public void letterPress(View v){
        Button knapp = (Button) v;
        knapp.setEnabled(false);
        //0xFFE23F55 nice Deep red
        //0xFFE8E8E8 more of a faded look
        //this uses HEX8 for some reason
        knapp.getBackground().setColorFilter(0xF0E8E8E8, PorterDuff.Mode.MULTIPLY);

        Toast.makeText(getApplicationContext(), v.getTag().toString(), Toast.LENGTH_SHORT).show();
    }
}
