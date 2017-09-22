package com.example.micha.hangman;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.StrictMode;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class Game extends AppCompatActivity {

    public String[] textArray;
    public String randomStr;
    public int fails = 0;
    public int correct = 0;
    public int maxC = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        //random word
        if (textArray == null) {
            Resources res = getResources();
            String[] array = res.getStringArray(R.array.words);
            randomStr = array[new Random().nextInt(array.length)];
            textArray = randomStr.split("");
        }

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

    public void letterPress(View v){
        Button knapp = (Button) v;
        knapp.setEnabled(false);
        //0xFFE23F55 nice Deep red
        //0xFFE8E8E8 more of a faded look
        //this uses HEX8 for some reason
        if(letterCheck(v.getTag().toString())){             //right guess
            knapp.getBackground().setColorFilter(0xFFE8E8E8, PorterDuff.Mode.MULTIPLY);
            if (correct == (maxC)){
                //something something stats
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
                //something something stats
                dialogAlert("loss");
            }
        }

        //Toast.makeText(getApplicationContext(), v.getTag().toString(), Toast.LENGTH_SHORT).show();
    }

    public void dialogAlert(String type){
        String myTitle = "Hummm";
        String another = getResources().getString(R.string.another);
        String quitt = getResources().getString(R.string.quitt);


        if (type == "loss"){
           myTitle = getResources().getString(R.string.loss);
        }
        else if (type == "win"){
           myTitle = getResources().getString(R.string.win);
        }
        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle(myTitle).setMessage("It was: " + randomStr)
                .setPositiveButton(another, new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int id) {
                        Intent i = getBaseContext().getPackageManager().getLaunchIntentForPackage( getBaseContext().getPackageName() );
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(i);
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
}
