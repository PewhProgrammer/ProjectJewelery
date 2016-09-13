package com.Pieces;

/**
 * Created by Thinh-Laptop on 13.09.2016.
 */
public class Piece {

    private final Kind mKind;
    private final Power mPower;

    Piece(Kind k, Power p){
        this.mKind = k ;
        this.mPower = p ;
    }

    @Override
    public String toString(){
        return mKind.toString();
    }

    public Kind getKind() {
        return this.mKind;
    }

    public Power getmPower() {
        return this.mPower;
    }

}
