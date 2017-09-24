package com.example.micha.s300378;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;


public class MainActivity extends AppCompatActivity {

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

        File win = new File("./win.txt");
        File loss = new File("./loss.txt");
        String z = "0";
        if(!win.exists()){
            FileOutputStream fileOutputStream = null;
            try {
                fileOutputStream = openFileOutput("win.txt",MODE_PRIVATE);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            try {
                fileOutputStream.write(z.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                FileInputStream fileInputStream= openFileInput("win.txt");
                InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                StringBuffer stringBuffer = new StringBuffer();
                String lines;
                while ((lines=bufferedReader.readLine())!=null) {
                    stringBuffer.append(lines+"\n");
                }
                Toast.makeText(getApplicationContext(),stringBuffer.toString(),Toast.LENGTH_LONG).show();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Toast.makeText(getApplicationContext(),"Made win file",Toast.LENGTH_LONG).show();
        }

        if (!loss.exists()){
            FileOutputStream fileOutputStream = null;
            try {
                fileOutputStream = openFileOutput("loss.txt",MODE_PRIVATE);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            try {
                fileOutputStream.write(z.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Toast.makeText(getApplicationContext(),"Made loss file",Toast.LENGTH_LONG).show();
        }
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


