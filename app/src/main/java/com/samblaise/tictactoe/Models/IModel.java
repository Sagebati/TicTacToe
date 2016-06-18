package com.samblaise.tictactoe.Models;


import org.json.JSONObject;

/**
 * Interaface implementing méthods for the api
 *
 * Project : TicTacToe
 * com.samblaise.tictactoe.utils
 * Created by sam on 18/06/16.
 */

public interface IModel<I> {

   I post();
   I post(I params);
   I post(I params,String urlParams);
   I put();
   I put(I params);
   I put(I params, String urlParams);
   I remove();
   I remove(I params);
   I removei(I params, String urlParams);
   I get();
   I get(I params);
   I get(I prams, String urlParams);
}
