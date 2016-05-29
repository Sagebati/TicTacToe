package com.samblaise.tictactoe.models;

import com.google.android.gms.common.api.BooleanResult;
import com.samblaise.tictactoe.utils.JSONSerialisable;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Project : TicTacToe
 * com.samblaise.tictactoe.models
 * Created by sam on 29/05/16.
 */

public class Game extends JSONSerialisable {
    /* colones for the JSON object */
    public static final String cIDPLAYER1 = "player1";
    public static final String cIDPLAYER2 = "player2";
    public static final String cPLAYS = "plays";
    public static final String cID = "_id";

    /* Att decl */
    private final String idPlayer1;
    private String idPlayer2;
    private Moves plays;
    private final String gameId;

    public Game(String idPlayer1, String gameId) {
        this.idPlayer1 = idPlayer1;
        plays = new Moves(gameId);
        this.gameId = gameId;
    }

    public Game(JSONObject jo) throws JSONException {
        this(jo.getString(cIDPLAYER1), jo.getString(cID));
    }

    public void play(Player player, int x, int y){
        plays.addMove(new Move(x,y,player.getId()));
    }

    public Boolean canBegin(){
        boolean res = true;
        if (idPlayer1 == null || idPlayer2 == null) {
            res = false;
        }
        return res;
    }

    /**
     *
     * @return empty string if failed to parse the json
     */
    @Override
    public String toJSONString() {
        String res= "";
        try {
             res = new JSONObject().put(cIDPLAYER1, this.idPlayer1).put(cIDPLAYER2,idPlayer2).put(cPLAYS, plays.toJSONString()).toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return res;
    }

    public String getGameId() {
        return gameId;
    }

    public String getIdPlayer1() {
        return idPlayer1;
    }

    public String getIdPlayer2() {
        return idPlayer2;
    }

    public void setIdPlayer2(String idPlayer2) {
        this.idPlayer2 = idPlayer2;
    }
}
