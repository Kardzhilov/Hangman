package com.example.micha.s300378;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Game extends AppCompatActivity {

    public String[] textArray;
    public String randomStr;
    public ArrayList<String> words;
    public int fails = 0;
    public int correct = 0;
    public int maxC = 0;
    public int presses[] = new int[30];
    public int pressNr = 0;
    public Stats stat = new Stats();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);


        Resources res = getResources();
        String[] array = res.getStringArray(R.array.words);
        words = new ArrayList<String>(Arrays.asList(array));
        
        start();
    }


    public void buttonPressed(int i){
        presses[pressNr] = i;
        pressNr++;
    }

    public void letterPress(View v){
        Button knapp = (Button) v;
        knapp.setEnabled(false);
        buttonPressed(v.getId());
        //0xFFE23F55 nice Deep red
        //0xFFE8E8E8 more of a faded look
        //this uses HEX8 for some reason
        if(letterCheck(v.getTag().toString())){             //right guess
            knapp.getBackground().setColorFilter(0xFFE8E8E8, PorterDuff.Mode.MULTIPLY);
            if (correct == (maxC)){
                stat.winP();
                dialogAlert("win");
            }
        } else {                                            //wrong guess
            knapp.getBackground().setColorFilter(0xFFE23F55, PorterDuff.Mode.MULTIPLY);
            fails++;
            ImageView hang = (ImageView) findViewById(R.id.imageViewAttempts);
            if (fails == 1){
                hang.setImageResource(R.drawable.hang2);
            }
            else if (fails == 2){
                hang.setImageResource(R.drawable.hang3);
            }
            else if (fails == 3){
                hang.setImageResource(R.drawable.hang4);
            }
            else if (fails == 4){
                hang.setImageResource(R.drawable.hang5);
            }
            else if (fails == 5){
                hang.setImageResource(R.drawable.hang6);
            }
            else if (fails == 6){
                hang.setImageResource(R.drawable.hang7);
                stat.lossP();
                dialogAlert("loss");
            }
        }

        //Toast.makeText(getApplicationContext(), v.getTag().toString(), Toast.LENGTH_SHORT).show();
    }

    public void dialogAlert(String type){
        String myTitle = "Hummm";
        String another = getResources().getString(R.string.another);
        String quitt = getResources().getString(R.string.quitt);
        String msg =  getResources().getString(R.string.msg);


        if (type == "loss"){
           myTitle = getResources().getString(R.string.loss);
        }
        else if (type == "win"){
           myTitle = getResources().getString(R.string.win);
        }
        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle(myTitle).setMessage(msg + randomStr)
                .setPositiveButton(another, new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int id) {
                        Intent i = getBaseContext().getPackageManager().getLaunchIntentForPackage( getBaseContext().getPackageName() );
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        again();
                        dialog.dismiss();
                    }
                })
                .setNegativeButton(quitt, new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int id){
                        finish();
                        dialog.dismiss();
                    }
                })
        .setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialogInterface) {
                dialog.show();
            }
        });
        dialog.show();
    }

    public Boolean letterCheck(String s){
        boolean res =false;
        for( int i = 1; i < textArray.length; i++ )
        {
         if (textArray[i].contains(s)){
             TextView textView = (TextView) findViewById(900000+i);
             textView.setText(s);
             correct++;
             res = true;
         }
        }
        return res;
    }

    private void again(){
        ImageView hang = (ImageView) findViewById(R.id.imageViewAttempts);
        hang.setImageResource(R.drawable.hang1);
        fails = 0;
        correct = 0;
        maxC = 0;

        for( int i = 1; i < textArray.length; i++ )
        {
            LinearLayout ll = (LinearLayout)findViewById(R.id.blank_space_row);
            TextView textView = (TextView)findViewById(900000+i);
            ll.removeView(textView);
        }
        for (int i = 0; i < pressNr; i++){
            Button knapp = (Button)findViewById(presses[i]);
            knapp.setEnabled(true);
            knapp.getBackground().clearColorFilter();
        }
        pressNr =0;
        start();
    }

    private void start(){

        //random word

        //makes sure you dont crash from lack of words
        if (words.size() < 2){
            Toast.makeText(getApplicationContext(),getResources().getString(R.string.emp), Toast.LENGTH_SHORT).show();
            finish();
        }

            Random rand = new Random();
            int numb = rand.nextInt(words.size() - 0);

            randomStr = words.get(numb);
            words.remove(numb);
            textArray = randomStr.split("");

        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.blank_space_row);
        for( int i = 1; i < textArray.length; i++ )
        {

            TextView textView = new TextView(this);
            textView.setTextSize(30);
            textView.setTextColor(0xFF000000);
            textView.setPadding(8,0,8,0);
            textView.setId(900000+i);
            if(textArray[i].contains(" ")){
                textView.setText(" ");
            }else{
                textView.setText("_");
                maxC++;
            }
            linearLayout.addView(textView);
        }
    }
}
