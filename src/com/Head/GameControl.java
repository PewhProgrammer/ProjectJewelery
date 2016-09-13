package com.Head;

import com.Pieces.Piece;
import com.Pieces.PieceFactory;
import com.Utility.Log;
import com.Utility.Observable;

import java.util.Iterator;
import java.util.List;

/**
 * Created by Thinh-Laptop on 13.09.2016.
 *
 * is responsible for Game flow
 */
public class GameControl extends Observable {

    private final GameModel mGame ;

    public GameControl(GameModel game){
        mGame = game ;
    }

    public void startGame() throws InterruptedException{

        initGame();
        GUI gui = new GUI();
        attach(gui);

        int availableMoves = 100 ;

        while(!mGame.getGameOver()){

            int[] input = gui.userInput() ;
            availableMoves-- ;

            if(!checkMove()) {
                Log.info("Please enter new coordinates biatch!");
                continue;
            }
            while(true){

                List<Integer> explodingFields = mGame.checkForExplosion();
                if(explodingFields.size() == 0) break;

                executeExplosion(explodingFields);
                notifyUpdate(mGame);

                wait(5000);
                movePieces(explodingFields);

                fillBoard();
                notifyUpdate(mGame);
            }

            if(availableMoves == 0) mGame.GameOver();

        }

    }

    /**************************************  PRIVATE  **************************************/

    private boolean checkMove(){

        return false ;
    }
    private void movePieces(List<Integer> explodingFields){

    }

    private void fillBoard(){

        //TODO fill the Board in given Direction(?)



    }

    private void executeExplosion(List<Integer> explodingFields){
        Piece[][] board = mGame.getBoard();

        if(explodingFields.size() % 2 != 0){
            Log.debug("explodingFields hat ungerade Anzahl an Einträgen");
            System.exit(1);
        }

        Iterator<Integer> it = explodingFields.iterator();

        while(it.hasNext()){
            board[it.next()][it.next()] = null ;
        }

    }

    private void initGame(){
        for (int i = 0 ; i < mGame.getRows() ; i++){
            for(int j = 0 ; j < mGame.getColumns(); j++){
                mGame.insertPiece(i,j, PieceFactory.createRandomPiece());
            }
        }
    }
}
