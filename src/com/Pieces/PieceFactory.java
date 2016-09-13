package com.Pieces;

import java.util.Random;

/**
 * Created by Thinh-Laptop on 13.09.2016.
 */
public class PieceFactory {

    private static Kind[] pieceArray = {Kind.BLACK,Kind.RED,Kind.YELLOW,Kind.GREEN,Kind.BLACK,Kind.WHITE};
    private static Random mRand ;

    public static void injectRandomizer(Random rand){
        mRand = rand ;
    }

    public static Piece createPiece(Kind k){
        return createPiece(k,Power.Standard);
    }

    public static Piece createPiece(Kind k,Power p){
        return new Piece(k,p);
    }

    public static Piece createRandomPiece(){
        int number = mRand.nextInt(100);
        if(number == 100) return createPiece(Kind.ALL);

        return createPiece(pieceArray[number % 6]);
    }
}
