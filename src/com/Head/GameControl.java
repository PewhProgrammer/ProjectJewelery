package com.Head;

import com.Pieces.PieceFactory;
import com.Utility.Log;

/**
 * Created by Thinh-Laptop on 13.09.2016.
 *
 * is responsible for Game flow
 */
public class GameControl {

    private final GameModel mGame ;

    public GameControl(GameModel game){
        mGame = game ;
    }

    public void startGame(){


        for (int i = 0 ; i < mGame.getRows() ; i++){
            for(int j = 0 ; j < mGame.getColumns(); j++){
                mGame.insertPiece(i,j, PieceFactory.createRandomPiece());
            }
        }

        Log.setLevel(Log.Level.INFO);
        Log.info("\n"+mGame.toString());



    }
}
