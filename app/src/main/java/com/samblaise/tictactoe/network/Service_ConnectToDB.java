package com.samblaise.tictactoe.network;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.samblaise.tictactoe.activities.ListPlayersActivity;
import com.samblaise.tictactoe.activities.MainActivity;
import com.samblaise.tictactoe.Classes.Game;
import com.samblaise.tictactoe.Classes.Player;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


/**
 * Service who is the intermediate between all the Async actions
 * Created by samuel on 08/08/15.
 */
public class Service_ConnectToDB extends Service {
    //TODO Finish this class
    private  Map<String, Player> playerMap;
    private  Map<String, Player> cache;
    private Player me;
    private RequestQueue requestQueue;
    private Game game;


    @Override
    public void onCreate() {
        super.onCreate();
        playerMap = new HashMap<>();
        cache = new HashMap<>();
        requestQueue = Volley.newRequestQueue(getApplicationContext());
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinder();
    }

    /**
     * Method who returns a Map of players connected to the webService
     *
     * @return Map<StringURL> who contains all players able to play
     */
    public Map<String,Player> getPlayerOnline(ListPlayersActivity lpa) {
        if (playerMap.isEmpty()){
            getAllPlayers(lpa);
            return playerMap;
        }else {
            getAllPlayers(lpa);
            if (playerMap.equals(cache)){
                return cache;
            }else{
                cache.clear();
                cache.putAll(playerMap);
                return cache;
            }
        }
    }

    /**
     * Method who add players on the web service
     */
    public void connect() {
        JsonObjectRequest jsonObjectRequest = null;
        try {
            JSONObject jsonObject = new JSONObject().put(Game.cIDPLAYER1, me.getId());
            jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, Urls.GAME.getUrlStr(), jsonObject,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                game = new Game(response);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getApplicationContext(), "Error of connection", Toast.LENGTH_SHORT).show();
                        }
                    }
            );
        } catch (JSONException e) {
            e.printStackTrace();
        }
        requestQueue.add(jsonObjectRequest);
    }

    /**
     * Method to remove lines on the table games on the bd
     *
     * @param id of the game to del
     */
    public void removeLineOnGame(Integer id) {
        //TODO
    }

    /**
     * Method for joining a game
     *
     * @param name name on the player
     */
    public void joinAGame(String name) {
        //TODO
    }

    /**
     * Add à player on the db
     *
     * @param name name of the player or nickname
     */
    public void addPlayer(final MainActivity ma, String name) {
        JsonObjectRequest jsonObjectRequest = null;
        try {
            JSONObject jsonObject = new JSONObject(new Player(name).toJSONString());
            jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,Urls.PLAYER.getUrlStr(),jsonObject,
                    new Response.Listener<JSONObject>(){
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                me = new Player(response);
                                ma.changeActivity();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener(){
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getApplicationContext(),"Error of connection", Toast.LENGTH_SHORT).show();
                        }
                    });
        } catch (JSONException e) {
            e.printStackTrace();
        }
        requestQueue.add(jsonObjectRequest);
    }

    public Game getGame() {
        return game;
    }

    public class MyBinder extends Binder {
        public Service_ConnectToDB getMyService() {
            return Service_ConnectToDB.this;
        }
    }

    private void getAllPlayers(final ListPlayersActivity lpa){
         JsonArrayRequest jsonObjectRequest;

        jsonObjectRequest = new JsonArrayRequest(Request.Method.GET,Urls.PLAYER.getUrlStr(),null,
                new Response.Listener<JSONArray>(){
                    @Override
                    public void onResponse(JSONArray response) {
                        playerMap.clear();
                        for (int i = 0;!response.isNull(i); i++) {
                            try {
                                JSONObject jo = response.getJSONObject(i);
                                playerMap.put(jo.getString(Player.cID), new Player(jo));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        lpa.listPlayersReady();
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),"Error of connection", Toast.LENGTH_SHORT).show();
                    }
                });
        this.requestQueue.add(jsonObjectRequest);
    }

    public Player getMe() {
        return me;
    }
}

