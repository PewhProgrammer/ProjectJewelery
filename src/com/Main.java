package com;

import com.Head.GameControl;
import com.Head.GameModel;
import com.Head.GUI;

/**
 * Created by Thinh-Laptop on 13.09.2016.
 */
public class Main {


    public static void main(String[] args){

        /*
            ..Argument passing here..
         */
        new Main().commandGameExecution(0);
    }

    private void commandGameExecution(int seed){

        GameModel game = new GameModel(seed,4,4);
        GameControl ctrl = new GameControl(game);
        GUI gui = new GUI();
        ctrl.startGame();

    }
}
