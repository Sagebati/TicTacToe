package com.samblaise.tictactoe.Classes;

import com.samblaise.tictactoe.utils.JSONSerialisable;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Map;

public class Moves extends JSONSerialisable{
    private static final int nbrCases = 9;
    private final Move[] moves;
    private final String gameId;
    private int iter;

    /**
     * this class represents the moves of a game
     * @param gameId of the game
     */
    public Moves(String gameId) {
        moves = new Move[nbrCases];
        this.gameId = gameId;
        iter = 0;
    }

    public Moves addMove(Move move){
        moves[iter] = move;
        return this;
    }

    public Move[] getMoves() {
        return moves;
    }

    public String getGameId() {
        return gameId;
    }


    /**
     *
     * @return return a String formatted like a JSON array
     */
    @Override
    public String toJSONString() {
        JSONArray res = new JSONArray();
        for (int i = 0; i < iter; i++) {
            res.put(moves[i]);
        }
        return res.toString();
    }

    @Override
    public Map<String, String> getParams() {
        return null;
    }

    @Override
    public ArrayList<String> getJSONNames() {
        return null;
    }
}
