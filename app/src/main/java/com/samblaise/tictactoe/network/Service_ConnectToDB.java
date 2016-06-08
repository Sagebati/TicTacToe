package com.samblaise.tictactoe.network;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.RequestFuture;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.samblaise.tictactoe.models.Game;
import com.samblaise.tictactoe.models.Player;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;


/**
 * Service who is the intermediate between all the Async actions
 * Created by samuel on 08/08/15.
 */
public class Service_ConnectToDB extends Service {
    //TODO Finish this class
    private Map<String, Player> playerMap;
    private Player me;
    private RequestQueue requestQueue;
    private Game game;
    private RequestFuture<JSONObject> requestFuture;
    private Boolean waiting;


    @Override
    public void onCreate() {
        super.onCreate();
        playerMap = new HashMap<>();
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
    public Map<Integer, String> getNamesAndIp() {
        //TODO
        return null;
    }

    /**
     * Method who add players on the web service
     */
    public void addLineOnGame() {
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
    public void addPlayer(String name) {
        requestFuture = RequestFuture.newFuture();
        CustomRequestJSON customRequestJSON = new CustomRequestJSON(Request.Method.POST,Urls.PLAYER.getUrlStr(),new Player(name).getParams(), requestFuture, requestFuture);

        requestQueue.add(customRequestJSON);

        try {
            me = new Player(requestFuture.get());
        } catch (JSONException | InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            getMe();
        }
//        JsonObjectRequest jsonObjectRequest = null;
//        try {
//            JSONObject jsonObject = new JSONObject(new Player(name).toJSONString());
//            jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,Urls.PLAYER.getUrlStr(),jsonObject,
//                    new Response.Listener<JSONObject>(){
//                        @Override
//                        public void onResponse(JSONObject response) {
//                            try {
//                                playerMap.put(response.getString(Player.cID),new Player(response));
//                                me = new Player(response);
//
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }
//                        }
//                    },
//                    new Response.ErrorListener(){
//                        @Override
//                        public void onErrorResponse(VolleyError error) {
//                            Toast.makeText(getApplicationContext(),"Error of connection", Toast.LENGTH_SHORT).show();
//                        }
//                    });
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        requestQueue.add(jsonObjectRequest);

//        String  stringPost = null;
//        try {
//            stringPost = new JSONObject(new Player(name).toJSONString()).toString();
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        StringRequest stringRequest = new StringRequest(Request.Method.POST,Urls.PLAYER.getUrlStr(),stringPost,requestFuture,requestFuture);
//        this.requestQueue.add(jsonObjectRequest);
//        try {
//            me = new Player(requestFuture.get(100, TimeUnit.SECONDS));
//        } catch (InterruptedException | ExecutionException | JSONException | TimeoutException e) {
//            e.printStackTrace();
//            Toast.makeText(this.getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
//        }

    }

    public JSONObject waitSrvRes() {
        RequestFuture<JSONObject> requestFuture = RequestFuture.newFuture();
        return null;
    }

    public Game getGame() {
        return game;
    }

    public class MyBinder extends Binder {
        public Service_ConnectToDB getMyService() {
            return Service_ConnectToDB.this;
        }
    }

    /*public void getAllPlayers(){
         JsonObjectRequest jsonObjectRequest;
        try {
            JSONObject jsonObject = new JSONObject(new Player("").toJSONString());

            jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,Urls.PLAYER.getUrlStr(),jsonObject,
                    new Response.Listener<JSONObject>(){
                        @Override
                        public void onResponse(JSONObject response) {
                            Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_LONG).show();
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
            jsonObjectRequest = null;
        }
        this.requestQueue.add(jsonObjectRequest);
    }*/

    public Player getMe() {
        return me;
    }
}

