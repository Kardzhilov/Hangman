package com.example.micha.s300378;

/**
 * Created by micha on 24/09/2017.
 */

public class Stats {
    private static int win;
    private static int loss;

    //Resets the stats
    public void reStat(){
        win = 0;
        loss = 0;
    }


    //Increases the stats
    public void winP(){
        win++;
    }
    public void lossP(){
        loss++;
    }


    //Returns the states
    public int getWin(){
        return win;
    }
    public int getLoss(){
        return loss;
    }

}
