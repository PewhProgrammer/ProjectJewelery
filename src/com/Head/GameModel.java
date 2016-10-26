package com.Head;

import com.Pieces.Kind;
import com.Pieces.Piece;
import com.Pieces.PieceFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Thinh-Laptop on 13.09.2016.
 */
public class GameModel {

    private final Random mRand ;
    private Piece[][] mBoard;
    private int mRows , mColumns ;
    private boolean mGameOver = false;

    private int mPoints = 0;

    public GameModel(int seed,int rows, int columns){
        mRows = rows ;
        mColumns = columns ;
        mBoard = new Piece[rows][columns];
        mRand = new Random(seed);
        PieceFactory.injectRandomizer(mRand);
    }

    public void insertPiece(int i, int j,Piece p){
        mBoard[i][j] = p ;
    }

    public void GameOver(){
        mGameOver = true ;
    }

    @Override
    public String toString(){

        StringBuilder builder = new StringBuilder() ;

        final int constant = 10 ;

        for (int i = 0 ; i < mRows ; i++){
            for(int j = 0 ; j < mColumns; j++){
                int offset = 0 ;
                if(mBoard[i][j] == null)
                    offset = constant ;
                else {
                    offset = constant - mBoard[i][j].getKind().toString().length();
                    builder.append(mBoard[i][j]);
                }
                for(int append = 0  ; append < offset ; append++){
                    builder.append(" ");
                }

                builder.append("| ");
            }
            builder.append("\n");
        }

        return builder.append("\n").toString();
    }


    /**********************  SETTERS  *************************/

    public void incPoints(int i){
        this.mPoints += i ;
    }

    /**********************  GETTERS  *************************/

    public int getPoints(){return mPoints;}

    public boolean getGameOver(){return mGameOver;}

    public Random getRandomizer(){
        return this.mRand;
    }

    public Piece[][] getBoard(){
        return mBoard;
    }


    public int getRows(){
        return mRows;
    }

    public int getColumns(){
        return mColumns;
    }


    public List<Integer> checkForExplosion() {

        List<Integer> explodingFields = new ArrayList<>();
        int streak = 0;
        Kind oldcolor = null;

        for(int i=0; i<mRows; i++) {
            for (int j=0; j<mColumns; j++) {
                if (mBoard[i][j].getKind() == oldcolor) streak++;

                else {
                    if (streak >= 2) {
                        for(int k=0; k <= streak; k++) {
                           // TODO: ggf checken welche art von stein zerstört wird
                            explodingFields.add(i);
                            explodingFields.add(j-1-k);
                        }
                    }
                    streak = 0;
                    oldcolor = mBoard[i][j].getKind();
                }
            }
            if (streak >= 2) {
                for(int k=0; k <= streak; k++) {
                    // TODO: ggf checken welche art von stein zerstört wird
                    explodingFields.add(i);
                    explodingFields.add(mColumns-1-k);
                }
            }
            streak = 0;
            oldcolor = null;
        }

        for(int j=0; j<mColumns; j++) {
            for (int i=0; i<mRows; i++) {
                if (mBoard[i][j].getKind() == oldcolor) streak++;

                else {
                    if (streak >= 2) {
                        for(int k=0; k <= streak; k++) {
                            // TODO: ggf checken welche art von stein zerstört wird, ggf werden felder doppelt eingefügt
                            explodingFields.add(i-1-k);
                            explodingFields.add(j);
                        }
                    }
                    streak = 0;
                    oldcolor = mBoard[i][j].getKind();
                }
            }
            if (streak >= 2) {
                for(int k=0; k <= streak; k++) {
                    // TODO: ggf checken welche art von stein zerstört wird, ggf werden felder doppelt eingefügt
                    explodingFields.add(mRows -1-k);
                    explodingFields.add(j);
                }
            }
            streak = 0;
            oldcolor = null;
        }

        return explodingFields;
    }

}
