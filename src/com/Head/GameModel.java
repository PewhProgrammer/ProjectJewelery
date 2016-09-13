package com.Head;

import com.Pieces.Piece;
import com.Pieces.PieceFactory;

import java.io.PrintStream;
import java.util.Random;

/**
 * Created by Thinh-Laptop on 13.09.2016.
 */
public class GameModel {

    private final Random mRand ;
    private Piece[][] mBoard;
    private int mRows , mColumns ;

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



    /**********************  GETTERS *************************/

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

    @Override
    public String toString(){

        StringBuilder builder = new StringBuilder() ;


        for (int i = 0 ; i < mRows ; i++){
            for(int j = 0 ; j < mColumns; j++){
                builder.append(mBoard[i][j]).append(" | ");
            }
            builder.append("\n");
        }

        return builder.toString();
    }

}
