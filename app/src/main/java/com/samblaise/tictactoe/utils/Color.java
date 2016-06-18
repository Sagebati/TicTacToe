package com.samblaise.tictactoe.utils;

/**
 * Project : TicTacToe
 * com.samblaise.tictactoe.utils
 * Created by sam on 18/06/16.
 */

public enum Color {

    Cross(1),
    CIRCLE(2);

    private String color;
    Color(Integer i){
        if (i ==1){
            color = "Cross";
        }else{
            color = "Circle";
        }
    }

    public String getColor() {
        return color;
    }

    public String getColor(Integer i ){
         if (i ==1){
            return "Cross";
        }else {
             return "Circle";
        }
    }
}
