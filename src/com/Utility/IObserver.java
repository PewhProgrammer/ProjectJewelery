package com.Utility;

import com.Head.GameModel;

/**
 * Created by Thinh-Laptop on 13.09.2016.
 */
public interface IObserver {

    public void update(GameModel model);

    public int[] userInput();
}
