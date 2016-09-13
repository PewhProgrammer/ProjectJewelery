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
        switch (mKind){
            case RED: return (char)27 + "[31m"+mKind.toString() + (char)27 + "[0m";
            case BLUE: return (char)27 + "[34m"+mKind.toString() + (char)27 + "[0m";
            case YELLOW: return (char)27 + "[33m"+mKind.toString() + (char)27 + "[0m";
            case GREEN: return (char)27 + "[32m"+mKind.toString() + (char)27 + "[0m";
            case BLACK: return (char)27 + "[37m"+mKind.toString() + (char)27 + "[0m";
            default: break;
        }
        return mKind.toString();
    }

    public Kind getKind() {
        return this.mKind;
    }

    public Power getmPower() {
        return this.mPower;
    }

}
