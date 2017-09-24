package com.example.micha.s300378;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class GStatistics extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gstatistics);


        Stats stat = new Stats();

        if (stat.getWin()+stat.getLoss()!=0){
            TextView needToPlay = (TextView)findViewById(R.id.twEmpty);
            needToPlay.setText("");
        }

        TextView win = (TextView)findViewById(R.id.twWinsScore);
        TextView loss = (TextView)findViewById(R.id.twLossScore);
        win.setText(stat.getWin()+"");
        loss.setText(stat.getLoss()+"");
    }
}
