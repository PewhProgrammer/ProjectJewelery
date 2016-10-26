package com;

import com.Gui.GraphicalGUI;
import com.Head.GameControl;
import com.Head.GameModel;
import com.Utility.Log;

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

    synchronized private void commandGameExecution(int seed){

        GameModel game = new GameModel(seed,4,4);
        GameControl ctrl = new GameControl(game);

        Log.setLevel(Log.Level.INFO);
        Thread thread = new Thread() {
            @Override
            public void run() {
                launchGUI(ctrl);
                return ;
            }
        };
        thread.start();


        try {
            wait(2000);
            ctrl.startGame();
        }catch(Exception e){
            Log.debug("Highest Level Main Exception for startGame ");
            e.printStackTrace();
        }

    }


    private void launchGUI(GameControl ctrl) {
        Log.info("Launching ConsoleGUI...");
        try {
            String[] strings = {};
            new GraphicalGUI().setCtrl(ctrl);
            new GraphicalGUI().main(strings);
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
