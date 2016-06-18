package com.samblaise.tictactoe.Models;

import org.json.JSONObject;

/**
 * Project : TicTacToe
 * com.samblaise.tictactoe.Models
 * Created by sam on 18/06/16.
 */

public class PlayerModel implements IModel<JSONObject>{

    @Override
    public JSONObject post() {
        return post(null);
    }

    @Override
    public JSONObject post(JSONObject params) {
        return  post(params,null);
    }

    @Override
    public JSONObject post(JSONObject params, String urlParams) {
        return null;
    }

    @Override
    public JSONObject put() {
        return null;
    }

    @Override
    public JSONObject put(JSONObject params) {
        return null;
    }

    @Override
    public JSONObject put(JSONObject params, String urlParams) {
        return null;
    }

    @Override
    public JSONObject remove() {
        return null;
    }

    @Override
    public JSONObject remove(JSONObject params) {
        return null;
    }

    @Override
    public JSONObject removei(JSONObject params, String urlParams) {
        return null;
    }

    @Override
    public JSONObject get() {
        return null;
    }

    @Override
    public JSONObject get(JSONObject params) {
        return null;
    }

    @Override
    public JSONObject get(JSONObject prams, String urlParams) {
        return null;
    }
}
