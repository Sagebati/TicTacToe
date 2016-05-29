package com.samblaise.tictactoe.utils;

/**
 * Project : TicTacToe
 * com.samblaise.tictactoe.utils
 * Created by sam on 28/05/16.
 */

public abstract class JSONSerialisable {
    /**
     * Method who return the object on a String json
     * @return the String foramtted on JSON
     */
    public abstract String toJSONString();

    @Override
    public String toString() {
        return toJSONString();
    }
}
