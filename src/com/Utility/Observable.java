package com.Utility;

import com.Head.GameModel;
import com.Pieces.Piece;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Thinh-Laptop on 13.09.2016.
 */

public abstract class Observable {
    private List<IObserver> observers = new ArrayList<>();

    /**
     * adds a new observer
     *
     * @param observer
     */
    public void attach(IObserver observer) {
        observers.add(observer);
    }

    /**
     * removes an observer
     *
     * @param observer
     */
    public void detach(IObserver observer) {
        observers.remove(observer);
    }


    public void notifyUpdate(GameModel model) {
        for (IObserver observer : observers) {
            observer.update(model);
        }
    }
}

