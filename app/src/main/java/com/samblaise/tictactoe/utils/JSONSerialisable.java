package com.samblaise.tictactoe.utils;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Map;

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

    public abstract Map<String,String> getParams();

    public abstract ArrayList<String> getJSONNames();
}
