package com.Head;

import com.Pieces.Piece;
import com.Pieces.PieceFactory;
import com.Utility.Log;
import com.Utility.Observable;

import java.util.Iterator;
import java.util.List;

import static java.lang.Math.abs;

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

        notifyUpdate(mGame);

        int availableMoves = 100 ;

        while(!mGame.getGameOver()){

            int[] input = gui.userInput() ;

            Piece[][] board = mGame.getBoard();

            Piece help = board[input[0]][input[1]];
            board[input[0]][input[1]] = board[input[2]][input[3]] ;
            board[input[2]][input[3]] = help ;

            if(!checkCoordinates(input) || mGame.checkForExplosion().size() == 0) {
                    Log.info("Please enter new coordinates biatch!");
                    //reswap
                    board[input[2]][input[3]] = board[input[0]][input[1]];
                    board[input[0]][input[1]] = help;
                    continue;
            }

            availableMoves-- ;

            while(true){

                List<Integer> explodingFields = mGame.checkForExplosion();
                if(explodingFields.size() == 0) break;

                executeExplosion(explodingFields);
                notifyUpdate(mGame);

                //wait(5000);
                if(movePieces())
                    notifyUpdate(mGame);

                fillBoard();
                notifyUpdate(mGame);
            }

            if(availableMoves == 0) mGame.GameOver();

        }

    }

    /**************************************  PRIVATE  **************************************/

    private boolean checkCoordinates(int[] input){

        if (input[0] >= mGame.getRows() || input[2] >= mGame.getRows()) return false;
        if (input[1] >= mGame.getColumns() || input[3] >= mGame.getColumns()) return false;

        if(input[0] == input[2]) {
            if(abs(input[1] - input[3])== 1) return true;
        }

        if(input[1] == input[3]) {
            if(abs(input[0] - input[2])== 1) return true;
        }

        return false ;
    }

    private boolean movePieces(){

        int nullCounter = 0;
        boolean moved = false;

        for(int j = 0; j < mGame.getColumns(); j++) {
            for (int i= mGame.getRows()-1; i >= 0; i--) {
                if(mGame.getBoard()[i][j] == null) {
                    nullCounter++;
                }
                else if (nullCounter != 0) {

                    for(int k = i+nullCounter; k > nullCounter -1; k--) {
                        mGame.getBoard()[k][j] = mGame.getBoard()[k - nullCounter][j];
                        moved = true;
                    }

                    for(int k = nullCounter -1; k>= 0; k--) {
                        mGame.getBoard()[k][j] = null;
                        moved = true;
                    }

                    nullCounter = 0;

                }
            }

            nullCounter = 0;
        }

        return moved;
    }

    private void fillBoard(){

        //TODO fill the Board in given Direction(?)

        Piece[][] board = mGame.getBoard();
        boolean break_flag = true ;

        for(int i = 0 ; i < mGame.getRows() ; i++){
            for(int j = 0 ; j < mGame.getColumns() ; j++){

                if(board[i][j] == null) {
                    board[i][j] = PieceFactory.createRandomPiece() ;
                    break_flag = false;
                }
            }
            if(break_flag) return ;
            break_flag = true ;
        }

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
